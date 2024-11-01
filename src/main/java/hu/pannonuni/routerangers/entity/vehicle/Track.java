package hu.pannonuni.routerangers.entity.vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "track")
@Getter
@Setter
public class Track extends Vehicle{

    private boolean hasTrailer;
    private double trailerLoadCapacity;
}
