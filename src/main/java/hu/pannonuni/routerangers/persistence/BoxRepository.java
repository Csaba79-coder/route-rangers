package hu.pannonuni.routerangers.persistence;

import hu.pannonuni.routerangers.entity.cargo.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoxRepository extends JpaRepository<Box, UUID> {
}