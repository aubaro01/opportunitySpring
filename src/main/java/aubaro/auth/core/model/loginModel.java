package aubaro.auth.core.model;

import aubaro.auth.core.model.rel.tokenModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public class loginModel {

    private String userLog;

    private String password;

    private Long clientId;

    private tokenModel tokens;

}
