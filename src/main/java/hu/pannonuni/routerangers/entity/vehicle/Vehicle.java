package hu.pannonuni.routerangers.entity.vehicle;

import hu.pannonuni.routerangers.entity.base.Identifier;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends Identifier {

    @NotNull(message = "License plate must not be null.")
    private String licensePlate;

    @Min(1)
    private int numberOfAxles;

    private double maxLoadHeight;
    private double maxLoadWeight;
}
