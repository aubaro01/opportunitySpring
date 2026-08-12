package aubaro.opportunity.core.port.out;

import aubaro.Shared.core.models.PageModel;
import aubaro.opportunity.core.model.OpportunityModel;

public interface OpportunityPersistence {

    PageModel<OpportunityModel> getAllOpportunities(int pageNo, int pageSize, String sortBy, String FilterBy);
    OpportunityModel getOpportunityById(Long id);
    OpportunityModel saveOpportunity(OpportunityModel opportunity);

    boolean deleteOpportunity(Long id);

    boolean deleteOpportunityComments(Long id);
    boolean deleteOpportunityMovements(Long id);


}
