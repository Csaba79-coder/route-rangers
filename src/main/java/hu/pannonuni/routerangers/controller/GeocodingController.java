package hu.pannonuni.routerangers.controller;

import hu.pannonuni.routerangers.entity.address.Address;
import hu.pannonuni.routerangers.service.GeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/geocoding")
@RequiredArgsConstructor
public class GeocodingController {

    private final GeocodingService geocodingService;

    @PostMapping("/coordinates")
    public ResponseEntity<Double[]> getCoordinates(@RequestBody Address address) {
        String fullAddress = String.format("%s %s, %s, %s",
                address.getStreet(),
                address.getHouseNumber(),
                address.getCity(),
                address.getPostalCode());

        Double[] coordinates = geocodingService.fetchCoordinates(fullAddress);
        if (coordinates == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coordinates);
    }

    @GetMapping("/coordinates")
    public ResponseEntity<Double[]> getCoordinates(@RequestParam String address) {
        Double[] coordinates = geocodingService.fetchCoordinates(address);
        if (coordinates == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coordinates);
    }
}
