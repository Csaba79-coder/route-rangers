package hu.pannonuni.routerangers.entity.address;

import hu.pannonuni.routerangers.entity.base.Identifier;
import hu.pannonuni.routerangers.value.StreetType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends Identifier {

    private String street;
    @Enumerated(EnumType.STRING)
    private StreetType streetType;
    private String houseNumber;
    private String city;
    private String postalCode;
    private String country;
    private Double latitude;
    private Double longitude;
}
