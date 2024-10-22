package hu.pannonuni.routerangers.controller;

import hu.pannonuni.api.UserApi;
import hu.pannonuni.model.UserModel;
import hu.pannonuni.model.UserRegistrationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController implements UserApi {

    @Override
    public ResponseEntity<UserModel> createUser(UserRegistrationModel userRegistrationModel) {
        return ResponseEntity.status(201).body(new hu.pannonuni.model.UserModel());
    }

    @Override
    public ResponseEntity<List<UserModel>> renderAllUsers() {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
