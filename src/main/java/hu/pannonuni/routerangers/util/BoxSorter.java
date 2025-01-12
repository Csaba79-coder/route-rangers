package hu.pannonuni.routerangers.util;

import hu.pannonuni.routerangers.entity.cargo.Box;

import java.util.Comparator;
import java.util.List;

public class BoxSorter {

    public static List<Box> sortBoxes(List<Box> boxes) {
        // Comparator-t, amely mindegyik szempontot figyelembe veszi. Metódus unit teszttel ellenőrizve.
        return boxes.stream()
                .sorted(Comparator.comparingInt(Box::getWidth).reversed() // Szélesség csökkenő
                        .thenComparing(Comparator.comparingInt(Box::getLength).reversed()) // Hosszúság csökkenő
                        .thenComparing(Comparator.comparingInt(Box::getHeight).reversed()) // Magasság csökkenő
                        .thenComparing(Comparator.comparingInt(Box::getWeight).reversed())) // Súly csökkenő
                .toList();
    }

    private BoxSorter() {

    }
}

