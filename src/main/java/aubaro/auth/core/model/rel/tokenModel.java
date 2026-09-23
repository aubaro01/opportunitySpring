package aubaro.auth.core.model.rel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public class tokenModel {

    private String token;

    private String accessToken;

    private String refreshToken;
}
