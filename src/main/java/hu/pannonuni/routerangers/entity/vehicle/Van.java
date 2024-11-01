package hu.pannonuni.routerangers.entity.vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "van")
@Getter
@Setter
public class Van extends Vehicle{

    private boolean refrigerated;
    private double maxLoadVolume;
}
