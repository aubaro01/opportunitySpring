package aubaro.auth.core.Utils;

import aubaro.auth.core.Message.AuthMessage;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final AuthMessage authMessage;

    public BusinessException(AuthMessage authMessage) {
        super(authMessage.getMessage());
        this.authMessage = authMessage;
    }
}