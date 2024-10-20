package hu.pannonuni.routerangers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController implements hu.pannonuni.api.UsersApi {

    @Override
    public ResponseEntity<hu.pannonuni.model.UserModel> createUser(hu.pannonuni.model.UserRegistrationModel userRegistrationModel) {
        return ResponseEntity.status(201).body(new hu.pannonuni.model.UserModel());
    }

    @Override
    public ResponseEntity<List<hu.pannonuni.model.UserModel>> renderAllUsers() {
        return ResponseEntity.ok(new ArrayList<>());
    }
}
