package aubaro.opportunity.core.model.rel;

import aubaro.opportunity.Shared.core.models.StateModel;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OpportunityBrandModel {

    private Long id;
    private String name;
    private String description;
    private StateModel State;
    private Long clientId;
}
