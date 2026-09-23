package aubaro.auth.core.Service;

import aubaro.Shared.core.models.UserModel;
import aubaro.auth.adapter.persistence.authPersistenceImpl;
import aubaro.auth.core.Message.AuthMessage;
import aubaro.auth.core.Utils.BusinessException;
import aubaro.auth.core.Utils.jwtUtil;
import aubaro.auth.core.model.loginModel;
import aubaro.auth.core.model.rel.tokenModel;
import aubaro.auth.core.ports.in.authOperations;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class authService implements authOperations {

    private final authPersistenceImpl persistence;
    private final PasswordEncoder passwordEncoder;
    private final jwtUtil jwtUtil;

    private final static Long MAX_NAME_LENGTH = 50L;


    @Override
    public loginModel userLogin(loginModel model) { // sem throws também aqui

        log.debug("authService.userLogin :: start checking user accesses with the credentials: userName={} :: ", model.getUserLog());

        if (model.getUserLog() == null || model.getPassword() == null) {
            throw new BusinessException(AuthMessage.AUTH_MESSAGE_0002);
        }

        loginModel user = persistence.getUserByUserName(model.getUserLog())
                .orElseThrow(() -> new BusinessException(AuthMessage.AUTH_MESSAGE_0001)); // "User not found"

        // match the passwords
        boolean passwordMatches = passwordEncoder.matches(model.getPassword(), user.getPassword());

        if (!passwordMatches) {
            log.warn("authService.userLogin :: invalid password for userName={}", model.getUserLog());
            throw new BusinessException(AuthMessage.AUTH_MESSAGE_0002); // credenciais inválidas
        }
        // generate tokens for access
        String accessToken = jwtUtil.generateAccessToken(user.getUserId(), user.getClientId());
        String refreshToken = jwtUtil.generateRefreshToken(user.getUserId());

        tokenModel tokens = new tokenModel();
        tokens.setAccessToken(accessToken);
        tokens.setRefreshToken(refreshToken);
        user.setTokens(tokens);

        log.debug("authService.userLogin :: login successful for userName={}", model.getUserLog());

        user.setPassword(null); // never send the password or the hash
        return user;
    }

    @Override
    public UserModel createUser(UserModel create) {

        log.debug("authService.createUser :: Creating a new user with the model: {} :: ", create);

        String hashedPassword = passwordEncoder.encode(create.getPassword());
        create.setPassword(hashedPassword);

        log.info("authService.createUser :: Creating password hash for the user: {} :: ", create);

        return persistence.createUser(create);
    }

    @Override
    public tokenModel refreshToken(String refreshToken) {

        Claims claims = jwtUtil.validateToken(refreshToken);

        if (!"refresh".equals(claims.get("type"))) {
            throw new BusinessException(AuthMessage.AUTH_MESSAGE_0002);
        }

        Long userId = Long.valueOf(claims.getSubject());


        String newAccessToken = jwtUtil.generateAccessToken(userId, null); // ajusta clientId conforme precisares

        tokenModel tokens = new tokenModel();
        tokens.setAccessToken(newAccessToken);

        return tokens;
    }

}
