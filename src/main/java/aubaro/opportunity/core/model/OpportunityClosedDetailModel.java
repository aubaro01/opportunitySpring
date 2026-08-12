package aubaro.opportunity.core.model;

import aubaro.Shared.core.models.StateModel;
import aubaro.Shared.core.models.TypeModel;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OpportunityClosedDetailModel {

    private String comment;
    private String contestNumberClosure;
    private LocalDateTime closureDate;
    private BigDecimal amountClosure;

    private StateModel state;
    private TypeModel closureType;

}
