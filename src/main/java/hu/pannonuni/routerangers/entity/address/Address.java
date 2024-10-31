package hu.pannonuni.routerangers.entity.address;

import hu.pannonuni.routerangers.entity.base.Identifier;
import hu.pannonuni.routerangers.value.Country;
import hu.pannonuni.routerangers.value.StreetType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static hu.pannonuni.routerangers.value.Country.HUNGARY;
import static hu.pannonuni.routerangers.value.StreetType.STREET;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends Identifier {

    @NotNull(message = "Street must not be null.")
    private String street;

    @Enumerated(EnumType.STRING)
    private StreetType streetType = STREET;

    @NotNull(message = "House number must not be null.")
    private String houseNumber;

    @NotNull(message = "City must not be null.")
    private String city;

    @NotNull(message = "Postal code must not be null.")
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private Country country = HUNGARY;

    private Double latitude;
    private Double longitude;
}
