package aubaro.auth.adapter.controller;

import aubaro.Shared.adapter.response.UserResponse;
import aubaro.auth.adapter.dto.request.createLogin;
import aubaro.auth.adapter.dto.request.loginRequest;
import aubaro.auth.adapter.dto.response.loginResponse;
import aubaro.Shared.core.models.UserModel;
import aubaro.auth.core.model.loginModel;
import aubaro.auth.core.model.rel.tokenModel;
import aubaro.auth.core.ports.in.authOperations;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@Description( "Auth controller")
@RequestMapping("/api/v1/auth")

public class authController {

    private final authOperations authOperation;

    @PostMapping("/login")
    @Description("Creating access for a user")
    public ResponseEntity<loginResponse> userAuth(@RequestBody loginRequest logRequest){

        log.debug("authController.userAuth :: log user with the userName: {}", logRequest.getUserName());

        loginModel auth = loginModel.builder()
                .userLog(logRequest.getUserName())
                .password(logRequest.getPassword())
                .build();

        loginModel login = authOperation.userLogin(auth);

        UserResponse userResponse = UserResponse.builder()
                .id(login.getUserId())
                .userLog(login.getUserLog())
                .clientId(login.getClientId())
                .build();

        loginResponse response = loginResponse.builder()
                .user(userResponse)
                .tokens(login.getTokens())
                .build();

        log.debug("authController.userAuth :: Auth login result for user: {} :: end", login.getUserLog());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public tokenModel refreshToken(@RequestBody tokenModel request) {
        return authOperation.refreshToken(request.getRefreshToken());
    }

    @PostMapping("/createLog")
    @Description("Creating a new user")
    public ResponseEntity<UserModel> createNewUser(@Valid @RequestBody createLogin createLog){

        log.debug("authController.createNewUser :: creating a new user with the data: {}", createLog);

        UserModel user = UserModel.builder()
                .name(createLog.getUserName())
                .userLog(createLog.getUserLog())
                .password(createLog.getPassword())
                .clientId(createLog.getClientId())
                .build();

        log.debug("authController.createNewUser :: creating a new user with the model: {} ", user);

        UserModel createUser = authOperation.createUser(user);

        log.debug("authController.createNewUser :: Creating a new user result : {} end", createUser);

        createUser.setPassword(null); // never send the password or the hash

        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);

    }

}
