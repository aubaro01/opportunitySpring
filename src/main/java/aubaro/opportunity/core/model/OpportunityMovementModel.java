package aubaro.opportunity.core.model;

import aubaro.Shared.core.models.EntityModel;
import aubaro.Shared.core.models.StateModel;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OpportunityMovementModel {

    private Long id;
    private String comment;
    private LocalDateTime createDate;

    private OpportunityModel opportunity;
    private StateModel stateId;
    private EntityModel entityId;

}
