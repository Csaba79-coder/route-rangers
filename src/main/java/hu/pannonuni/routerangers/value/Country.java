package hu.pannonuni.routerangers.value;

import lombok.Getter;

@Getter
public enum Country {

    HUNGARY("Hungary");

    private final String name;

    Country(String name) {
        this.name = name;
    }
}
