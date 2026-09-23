package aubaro.auth.adapter.dto.response;

import aubaro.auth.core.model.rel.tokenModel;
import aubaro.Shared.adapter.response.UserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"user", "tokens"})
public class loginResponse {

    private UserResponse user;
    private tokenModel tokens;
}
