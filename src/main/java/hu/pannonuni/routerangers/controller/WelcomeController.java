package hu.pannonuni.routerangers.controller;

import com.csaba79coder.api.WelcomeApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController implements WelcomeApi {

    @Override
    public ResponseEntity<String> welcomeMessage() {
        return ResponseEntity.status(200).body( "Welcome to RouteRangers!");
    }
}
