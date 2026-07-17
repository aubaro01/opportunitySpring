package aubaro.opportunity.adapter.mapper;

import aubaro.opportunity.Shared.core.models.PageModel;
import aubaro.opportunity.adapter.dto.response.OpportunityResponse;
import aubaro.opportunity.core.model.OpportunityModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)

public interface OpportunityMapper {

    OpportunityResponse modelToResponse(OpportunityModel opportunity);

    /*default List<OpportunityResponse> modelToResponse(List<OpportunityModel> opportunities) {
        return opportunities.stream()
                .map(this::modelToResponse)
                .toList();
    }

    default List<OpportunityResponse> modelToResponse(PageModel<OpportunityModel> page) {
        return modelToResponse((PageModel<OpportunityModel>) page.getPage()); // ajusta getContent() ao método real da tua PageModel
    }*/

}
