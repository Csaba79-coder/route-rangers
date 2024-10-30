package hu.pannonuni.routerangers.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@MappedSuperclass
@Getter
public class Identifier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
}
