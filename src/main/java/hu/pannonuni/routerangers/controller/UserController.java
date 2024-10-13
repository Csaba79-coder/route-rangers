package hu.pannonuni.routerangers.controller;

import io.swagger.model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements io.swagger.api.UserApi {

    @Override
    public ResponseEntity<UserModel> createUser(UserModel body) {
        return null;
    }

    @Override
    public ResponseEntity<List<UserModel>> renderAllUsers() {
        return null;
    }
}
