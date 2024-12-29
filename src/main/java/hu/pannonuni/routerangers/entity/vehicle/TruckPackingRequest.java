package hu.pannonuni.routerangers.entity.vehicle;

import hu.pannonuni.routerangers.entity.cargo.Box;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TruckPackingRequest {

    private List<Box> boxes;
    private Truck truck;
}
