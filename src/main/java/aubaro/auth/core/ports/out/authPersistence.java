package aubaro.auth.core.ports.out;

import aubaro.Shared.core.models.UserModel;
import aubaro.auth.core.Utils.BusinessException;
import aubaro.auth.core.model.loginModel;
import lombok.NonNull;

import java.util.Optional;

public interface authPersistence {

    Optional<loginModel> getUserByUserName (@NonNull String userName);

    UserModel createUser(UserModel create) throws BusinessException;

}
