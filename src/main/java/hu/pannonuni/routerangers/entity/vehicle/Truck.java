package hu.pannonuni.routerangers.entity.vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "truck")
@Getter
@Setter
public class Truck extends Vehicle {

    private boolean hasTrailer;
    private double trailerLoadCapacity;
}
