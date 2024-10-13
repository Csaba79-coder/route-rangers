package hu.pannonuni.routerangers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController implements hu.pannonuni.api.WelcomeApi {

    @Override
    public ResponseEntity<String> welcomeMessage() {
        return ResponseEntity.status(200).body( "Welcome to RouteRangers!");
    }
}
