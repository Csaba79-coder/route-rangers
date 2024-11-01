package hu.pannonuni.routerangers.controller;

import hu.pannonuni.api.UserApi;
import hu.pannonuni.model.UserLoginModel;
import hu.pannonuni.model.UserModel;
import hu.pannonuni.model.UserRegistrationModel;
import hu.pannonuni.routerangers.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<UserModel> register(UserRegistrationModel userRegistrationModel) {
        return ResponseEntity.ok(authenticationService.register(userRegistrationModel));
    }

    @Override
    public ResponseEntity<UserModel> login(UserLoginModel userLoginModel) {
        return ResponseEntity.ok(authenticationService.login(userLoginModel));
    }

    @Override
    public ResponseEntity<String> logout(String token) {
        authenticationService.logout(token);
        return ResponseEntity.ok("Logout was succesful!");
    }

    @Override
    public ResponseEntity<List<UserModel>> renderAllUsers() {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
