package aubaro.opportunity.core.model;


import aubaro.opportunity.Shared.core.models.StateModel;
import aubaro.opportunity.Shared.core.models.TypeModel;
import aubaro.opportunity.core.model.rel.OpportunityBrandModel;
import aubaro.opportunity.core.model.rel.OpportunityEquipmentModel;
import aubaro.opportunity.core.model.rel.OpportunitySegmentModel;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpportunityModel {

    private Long id;
    private String opportunityName;
    private String opportunityReference;
    private LocalDateTime opportunityDate;
    private LocalDateTime opportunityStartDate;
    private LocalDateTime opportunityEndDate;
    private String competitorName;
    private String OpportunityContestNumber;

    private BigDecimal opportunityAmount;
    private BigDecimal opportunityEstimatedAmount;
    private BigDecimal opportunityFinishedAmount;
    private String opportunityCurrency;

    private List<OpportunityCommentModel> opportunityComments;
    private List<OpportunityMovementModel> opportunityMovements;
    private StateModel state;
    private TypeModel type;
    private TypeModel priority;
    private TypeModel probability;
    private TypeModel typeOpportunity;
    private OpportunityBrandModel brand;
    private OpportunityEquipmentModel equipment;
    private OpportunitySegmentModel segment; // mudar para um nome melhor
    private OpportunityClosedDetailModel closedDetail;
}
