package aubaro.auth.adapter.persistence;

import aubaro.Shared.core.models.UserModel;
import aubaro.auth.core.Message.AuthMessage;
import aubaro.auth.core.Utils.BusinessException;
import aubaro.auth.core.model.loginModel;
import aubaro.auth.core.ports.out.authPersistence;
import aubaro.opportunity.adapter.persistenceImpl.helper.queryHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
@Repository
public class authPersistenceImpl implements authPersistence {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final queryHelper helper;

    @Override
    @Transactional(readOnly = true)
    public  Optional<loginModel> getUserByUserName(@NonNull String userName) {

        log.debug("authPersistenceImpl.getUserLogin :: check if user with the userName: {} exist", userName);

        // language=SQL
        String query = """
                SELECT ru."PK_UserId" AS id, ru."userName", ru.name,  ru."userPassword" AS password,
                rc."PK_ClientId" AS clientId, rc.name
                from ref_user AS ru
                left join ref_user_client As ruc
                on ruc."FK_UserId" = ru."PK_UserId"
                left join ref_client As rc
                on ruc."FK_ClientId" = rc."PK_ClientId"
                WHERE ru."userName" = :username
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("username", userName);

        List<loginModel> results = jdbcTemplate.query(query, params, (rs, rowNum) -> {
            loginModel user = new loginModel();
            user.setUserId(rs.getLong("id"));
            user.setUserLog(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            user.setClientId(rs.getLong("clientId"));
            return user;
        });

        log.debug("authPersistenceImpl.getUserByUserName :: user find with the name: {}", userName);

        // TODO: If a user can have more than 1 client switch to catch the right
        return results.stream().findFirst();
    }

    @Transactional(readOnly = false)
    @Override
    public UserModel createUser(UserModel create) throws BusinessException {

        log.debug("authPersistenceImpl.createUser :: Creating user with the following data: {}", create.getName());

        // language=SQL
        final String query = """
                INSERT INTO ref_user
                    ("userName", "userPassword", "name", "fullName", "is_Active")
                VALUES
                    (:userName, :userPassword, :name, :fullName, :isActive)
                """;

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userName", create.getUserLog())
                .addValue("userPassword", create.getPassword())
                .addValue("name", create.getName())
                .addValue("fullName", create.getFullName())
                .addValue("isActive", false);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(query, parameters, keyHolder, new String[]{"PK_UserId"});
        long userId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        log.debug("authPersistenceImpl.createUser :: UserId:{} generate for the user with the name: {} :: ", userId, create.getName());

        if (create.getClientId() != null){
            log.debug("authPersistenceImpl.createUser :: Associate user with the client with the id: {}", create.getClientId());
            createUserAndClientRelation(userId, create.getClientId());
        }

        create.setId(userId);
        return create;
    }

    @Transactional(readOnly = false)
    private boolean createUserAndClientRelation(Long userId, Long clientId) {

        log.debug("authPersistence.createUserAndClientRelation :: linking userId={} to clientId={}", userId, clientId);

        // language=SQL
        String query = """
            INSERT INTO ref_user_client
                ("FK_ClientId", "FK_UserId", "FK_CreateUser")
            VALUES
                (:clientId, :userId, :userId)
            """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("clientId", clientId)
                .addValue("userId", userId);

        try {
            int rowsAffected = jdbcTemplate.update(query, params);
            log.debug("authPersistenceImlp.createUserAndClientRelation :: User associated to a client");
            return rowsAffected > 0;

        } catch (DataIntegrityViolationException e) {
            log.warn("authPersistence.createUserAndClientRelation :: relation already exists for userId={} and clientId={}", userId, clientId);
            throw new BusinessException(AuthMessage.AUTH_MESSAGE_0004);
        }
    }
}
