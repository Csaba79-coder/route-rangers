package hu.pannonuni.routerangers.persistence;

import hu.pannonuni.routerangers.entity.vehicle.Van;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VanRepository extends JpaRepository<Van, UUID> {
}
