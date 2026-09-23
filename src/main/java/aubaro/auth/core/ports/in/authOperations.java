package aubaro.auth.core.ports.in;

import aubaro.Shared.core.models.UserModel;
import aubaro.auth.core.model.loginModel;
import aubaro.auth.core.model.rel.tokenModel;

public interface authOperations {

    loginModel userLogin (loginModel model);

    UserModel createUser (UserModel create);

    tokenModel refreshToken(String refreshToken);
}
