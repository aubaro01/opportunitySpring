package aubaro.auth.adapter.controller;

import aubaro.auth.adapter.dto.request.createLogin;
import aubaro.auth.adapter.dto.request.loginRequest;
import aubaro.auth.adapter.dto.response.loginResponse;
import aubaro.Shared.core.models.UserModel;
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@Description( "Auth controller")
@RequestMapping("/api/v1/auth")

public class authController {

    @PostMapping()
    @Description("Creating access for a user")
    public loginResponse userAuth (@RequestBody loginRequest logRequest){

        log.debug("authController.userAuth :: log user with the userName: {}", logRequest.getUserName());

        return null;
    }

    @PostMapping("/createLog")
    @Description("Creating a new user")
    public UserModel createNewUser(@RequestBody createLogin createLog){

        log.debug("authController.createNewUser :: creating a new user with the data: {}");

        UserModel user = UserModel.builder()
                .name(createLog.getUserName())
                .userLog(createLog.getUserLog())
                .password(createLog.getPassword())
                .clientId(createLog.getClientId())
                .build();

        log.debug("authController.createNewUser :: creating a new user with the model: {} ", user);

        return null;
    }

    @PutMapping("/inactive/{userId}/user")
    @Description("inactive a user")
    public UserModel inactiveUser(@PathVariable ("userId") Long userId){

        log.debug("authController.inactiveUser :: Inactive user with the id: {}", userId);


        return null;
    }



}
