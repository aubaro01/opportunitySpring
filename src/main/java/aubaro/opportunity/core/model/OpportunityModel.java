package aubaro.opportunity.core.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpportunityModel {

    private Long id;
    private String opportunityName;
}
