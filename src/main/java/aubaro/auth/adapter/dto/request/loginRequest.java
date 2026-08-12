package aubaro.auth.adapter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor

public class loginRequest {

    @NonNull
    private String userName;

    @NonNull
    private String password;
}
