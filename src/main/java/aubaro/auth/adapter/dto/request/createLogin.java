package aubaro.auth.adapter.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@AllArgsConstructor

public class createLogin {

    @NotBlank(message = "userName is required")
    private String userName;

    private String fullName;

    @NotBlank(message = "user Log is required")
    private String userLog;

    @NotBlank(message = "password is required")
    @Size(min = 4, message = "password must have at least 8 characters")
    private String password;

    @NotNull(message = "clientId is required")
    private Long clientId;

}
