package aubaro.opportunity.adapter.persistenceImpl;

import aubaro.opportunity.Shared.core.models.PageModel;
import aubaro.opportunity.adapter.persistenceImpl.helper.queryHelper;
import aubaro.opportunity.core.message.MsgOpportunityErrors;
import aubaro.opportunity.core.model.OpportunityModel;
import aubaro.opportunity.core.port.out.OpportunityPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor

public class OpportunityPersistenceImpl implements OpportunityPersistence {

   private final NamedParameterJdbcTemplate jdbcTemplate;
    private final queryHelper helper;

    @Transactional(readOnly = true)
    @Override
    public PageModel<OpportunityModel> getAllOpportunities(int pageNo, int pageSize, String sortBy, String filterBy) {

        log.debug("OpportunityPersistenceImpl.getAllOpportunities :: getting all opportunities with the parameters: pageNo: {}, pageSize: {}, sortBy: {}, filterBy: {}", pageNo, pageSize, sortBy, filterBy);

      /*  PageRequest pageRequest = PageRequest.of(pageNo > 0 ? pageNo - 1 : pageNo, pageSize);

        StringBuilder whereClause = new StringBuilder(" WHERE so.FK_StateId = 179 AND so.FK_ClientId = :clientId");
        MapSqlParameterSource params = new MapSqlParameterSource();
        //params.addValue("clientId", getCurrentClientId()); // ajusta conforme o teu contexto

        try {

            helper.appendFilters(filterBy, whereClause, params);
            String orderByClause = helper.buildOrderByClause(sortBy);

            String countSql = "SELECT COUNT(*) FROM opportunity so" + whereClause;


            String mainSql = "SELECT so.* FROM opportunity so"
                    + whereClause
                    + orderByClause
                    + " LIMIT :limit OFFSET :offset";

            params.addValue("limit", pageRequest.getPageSize());
            params.addValue("offset", pageRequest.getOffset());

            Long total = jdbcTemplate.queryForObject(countSql, params, Long.class);

            List<OpportunityModel> content = jdbcTemplate.query(mainSql, params, opportunityRowMapper);

            PageModel<OpportunityModel> pageModel = new PageModel<>();
            pageModel.setPage(content);
            pageModel.setTotalElements(total != null ? total : 0L);
            pageModel.setCurrentPage(pageNo);
            pageModel.setTotalPages(pageSize);

            return pageModel;

        } catch (IllegalArgumentException e) {
            log.warn("OpportunityPersistenceImpl.getAllOpportunities :: parâmetros inválidos: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("OpportunityPersistenceImpl.getAllOpportunities :: error getting all opportunities", e);
            throw new RuntimeException(e);
        }*/

        return null;
    }

    @Override
    public OpportunityModel getOpportunityById(Long id) {
        return null;
    }

    @Override
    public OpportunityModel saveOpportunity(OpportunityModel opportunity) {
        return null;
    }

    @Override
    public boolean deleteOpportunity(Long id) {

        String sql = """
                DELETE FROM opportunity
                WHERE id = :id AND clientId = :clientId
                """;

        try{

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", id);

            jdbcTemplate.update(sql, params);

        } catch (Exception e) {
            log.error("OpportunityPersistenceImpl.deleteOpportunity :: error deleting opportunity", e);
            throw new RuntimeException(String.valueOf(MsgOpportunityErrors.OPT_0003));
        }

        log.debug("OpportunityPersistenceImpl.deleteOpportunity :: opportunity deleted");
        return true;
    }

    @Override
    public boolean deleteOpportunityComments(Long id) {

        log.debug("OpportunityPersistenceImpl.deleteComments :: deleting comments for opportunity id: {}", id);

        String query = """
                DELETE FROM opportunity_comment
                WHERE opportunityId = :id
                """;

        try{

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", id);

            jdbcTemplate.update(query, params);

            log.debug("OpportunityPersistenceImpl.deleteComments :: comments deleted for opportunity id: {}", id);

        } catch (Exception e) {
            log.error("OpportunityPersistenceImpl.deleteComments :: error deleting comments", e);
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public boolean deleteOpportunityMovements(Long id) {

        log.debug("OpportunityPersistenceImpl.deleteOpportunityMovements :: deleting movements for opportunity id: {}", id);

        String query = """
                DELETE FROM opportunity_movement
                WHERE opportunityId = :id
                """;

        try {

            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", id);

            jdbcTemplate.update(query, params);

            log.debug("OpportunityPersistenceImpl.deleteOpportunityMovements :: movements deleted for opportunity id: {}", id);

        } catch (Exception e) {
            log.error("OpportunityPersistenceImpl.deleteOpportunityMovements :: error deleting movements for opportunity id: {}", id, e);
            throw new RuntimeException(String.valueOf(MsgOpportunityErrors.OPT_0003));
        }
        return false;
    }

}
