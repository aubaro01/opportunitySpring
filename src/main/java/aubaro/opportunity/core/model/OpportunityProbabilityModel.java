package aubaro.opportunity.core.model;

import aubaro.Shared.core.models.StateModel;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OpportunityProbabilityModel {

    private Long id;
    private String comment;
    private Long probability;
    private LocalDateTime createDate;

    private StateModel stateId;
    private Long clientId;
}
