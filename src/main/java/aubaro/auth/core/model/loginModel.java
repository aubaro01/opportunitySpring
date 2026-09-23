package aubaro.auth.core.model;

import aubaro.auth.core.model.rel.tokenModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class loginModel {

    private Long userId;

    private String userLog;

    private String password;

    private Long clientId;

    private tokenModel tokens;

}
