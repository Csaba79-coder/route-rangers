package hu.pannonuni.routerangers.persistence;

import hu.pannonuni.routerangers.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    Optional<Address> findByStreetAndHouseNumberAndCityAndPostalCode(String street, String houseNumber, String city, String postalCode);
}
