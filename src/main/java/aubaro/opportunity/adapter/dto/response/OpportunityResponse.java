package aubaro.opportunity.adapter.dto.response;

import aubaro.opportunity.Shared.core.models.StateModel;
import aubaro.opportunity.Shared.core.models.TypeModel;
import aubaro.opportunity.core.model.rel.OpportunityBrandModel;
import aubaro.opportunity.core.model.rel.OpportunityEquipmentModel;
import aubaro.opportunity.core.model.rel.OpportunitySegmentModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpportunityResponse {

    private Long id;
    private String opportunityName;
    private String opportunityReference;
    private LocalDateTime opportunityDate;
    private LocalDateTime opportunityStartDate;
    private LocalDateTime opportunityEndDate;

    private BigDecimal opportunityAmount;
    private BigDecimal opportunityEstimatedAmount;
    private String opportunityCurrency;

    private StateModel state;
    private TypeModel type;
    private TypeModel priority;
    private TypeModel probability;
    private TypeModel typeOpportunity;
    private OpportunityBrandModel brand;
    private OpportunityEquipmentModel equipment;
    private OpportunitySegmentModel segment;

}
