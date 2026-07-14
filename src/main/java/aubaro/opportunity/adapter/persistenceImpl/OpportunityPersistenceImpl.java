package aubaro.opportunity.adapter.persistenceImpl;

import aubaro.opportunity.Shared.core.models.PageModel;
import aubaro.opportunity.core.model.OpportunityModel;
import aubaro.opportunity.core.port.out.OpportunityPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor

public class OpportunityPersistenceImpl implements OpportunityPersistence {

   private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public PageModel<OpportunityModel> getAllOpportunities() {
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
            throw new RuntimeException(e);
        }

        log.debug("OpportunityPersistenceImpl.deleteOpportunity :: opportunity deleted");
        return true;
    }

    @Override
    public boolean deleteOpportunityComments(Long id) {
        return false;
    }

    @Override
    public boolean deleteOpportunityMovements(Long id) {
        return false;
    }

    private void deleteComments() {

    }
}
