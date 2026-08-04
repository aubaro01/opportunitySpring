package aubaro.auth.core.Message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public enum AuthMessage implements Serializable {

    AUTH_MESSAGE_0001("0001", "User not found"),
    AUTH_MESSAGE_0002("0002", "Error creating access for user"),
    AUTH_MESSAGE_0003("0003", "Error create new user");


    private final String code;
    private final String message;

    @Override
    public String toString() {
        return this.code;
    }
}
