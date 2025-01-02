package hu.pannonuni.routerangers.entity.vehicle;

import hu.pannonuni.routerangers.entity.base.Identifier;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "truck")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Truck extends Identifier {

    private Integer weigh;
    private Integer width;
    private Integer length;
    private Integer height;

    @NotNull(message = "License plate must not be null.")
    private String licensePlate;
}
