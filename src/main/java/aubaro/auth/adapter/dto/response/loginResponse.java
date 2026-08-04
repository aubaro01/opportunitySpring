package aubaro.auth.adapter.dto.response;

import aubaro.auth.core.model.rel.tokenModel;
import aubaro.Shared.adapter.response.UserResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties()
public class loginResponse {

    private UserResponse user;
    private tokenModel tokens;
}
