package aubaro.opportunity.adapter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class createOpportunity {

    private String name;
    private String reference;

    private LocalDateTime opportunityStartDate;
    private LocalDateTime opportunityEndDate;


    private BigDecimal opportunityAmount;
    private BigDecimal opportunityEstimatedAmount;
    private String opportunityCurrency;

    private String opportunityComments;
    private Long type;
    private Long priority;
    private Long probability;
    private Long typeOpportunity;

}
