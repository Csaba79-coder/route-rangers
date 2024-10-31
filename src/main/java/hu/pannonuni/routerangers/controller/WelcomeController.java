package hu.pannonuni.routerangers.controller;

import hu.pannonuni.api.WelcomeApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class WelcomeController implements WelcomeApi {

    @Override
    public ResponseEntity<String> welcomeMessage() {
        return ResponseEntity.status(200).body( "Welcome to RouteRangers!");
    }
}
