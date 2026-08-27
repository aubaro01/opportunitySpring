package aubaro.auth.adapter.dto.request;

import lombok.*;

@Getter
@AllArgsConstructor

public class createLogin {

    @NonNull
    private String userName;

    @NonNull
    private String userLog;

    @NonNull
    private String password;

    @NonNull
    private Long clientId;

}
