package aubaro.opportunity.adapter.mapper;

import aubaro.opportunity.adapter.dto.response.OpportunityResponse;
import aubaro.opportunity.core.model.OpportunityModel;

public interface OpportunityMapper {

    OpportunityResponse modelToResponse(OpportunityModel opportunity);
}
