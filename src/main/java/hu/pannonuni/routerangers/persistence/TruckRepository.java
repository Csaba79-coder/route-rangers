package hu.pannonuni.routerangers.persistence;

import hu.pannonuni.routerangers.entity.vehicle.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TruckRepository extends JpaRepository <Truck, UUID> {
}