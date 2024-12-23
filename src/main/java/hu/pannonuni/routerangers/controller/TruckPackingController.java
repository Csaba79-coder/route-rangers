package hu.pannonuni.routerangers.controller;

import hu.pannonuni.routerangers.entity.cargo.Box;
import hu.pannonuni.routerangers.entity.cargo.BoxPlacement;
import hu.pannonuni.routerangers.entity.vehicle.Truck;
import hu.pannonuni.routerangers.service.TruckPackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class TruckPackingController {

    private final TruckPackingService truckPackingService;

    @PostMapping("/truck-packing")
    public ResponseEntity<List<BoxPlacement>> loadingCargo(@RequestBody List<Box> boxes, @RequestBody Truck truck) {
        return ResponseEntity.status(200).body(truckPackingService.packBoxes(boxes, truck));
    }
}
