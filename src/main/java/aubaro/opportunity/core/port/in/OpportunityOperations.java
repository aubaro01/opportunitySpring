package aubaro.opportunity.core.port.in;

import aubaro.opportunity.Shared.core.models.PageModel;
import aubaro.opportunity.core.model.OpportunityModel;

public interface OpportunityOperations {

    PageModel<OpportunityModel> getAllOpportunities();
    OpportunityModel getOpportunityById(Long id);
    OpportunityModel saveOpportunity(OpportunityModel opportunity);


}
