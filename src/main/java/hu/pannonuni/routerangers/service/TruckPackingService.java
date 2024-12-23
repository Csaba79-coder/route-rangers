package hu.pannonuni.routerangers.service;

import hu.pannonuni.routerangers.entity.cargo.Box;
import hu.pannonuni.routerangers.entity.cargo.BoxPlacement;
import hu.pannonuni.routerangers.entity.vehicle.Truck;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TruckPackingService {

    public List<BoxPlacement> packBoxes(List<Box> boxes, Truck truck) {
        List<BoxPlacement> placements = new ArrayList<>();
        int x = 0, y = 0, z = 0; // Starting coordinates
        int layer = 1; // Truck layer

        // Iterate through the boxes to pack them
        for (Box box : boxes) {
            // Check if the box fits in the current layer (width and length check)
            if (x + box.getWidth() > truck.getWidth()) {
                x = 0; // Reset x-coordinate (move to the next row)
                y += box.getLength(); // Move to the next row in the current layer

                // Check if the box fits in the truck's length
                if (y + box.getLength() > truck.getLength()) {
                    y = 0; // Reset y-coordinate (move to the next column)
                    z += box.getHeight(); // Move to a new layer
                    layer++; // Increase layer number

                    // If there's no space in the next layer, stop
                    if (z + box.getHeight() > truck.getHeight()) {
                        System.out.println("No more space in the truck for Box #" + box.getId());
                        break;
                    }
                }
            }

            // Add box placement to the result
            placements.add(new BoxPlacement(box, x, y, z, layer));

            // Update the x-coordinate for the next box
            x += box.getWidth();
        }

        return placements;
    }
}
