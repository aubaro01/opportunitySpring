package aubaro.opportunity.core.model;

import aubaro.opportunity.Shared.core.models.UserModel;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OpportunityCommentModel {

    private Long id;
    private String comment;
    private LocalDateTime date;

    private UserModel createUser;
}
