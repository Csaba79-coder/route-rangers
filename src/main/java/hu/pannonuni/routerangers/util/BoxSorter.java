package hu.pannonuni.routerangers.util;

import hu.pannonuni.routerangers.entity.cargo.Box;

import java.util.Comparator;
import java.util.List;

public class BoxSorter {

    public static List<Box> sortBoxes(List<Box> boxes) {
        return boxes.stream()
                .sorted(Comparator.comparingInt(Box::getWidth) // Először szélesség szerint
                        .thenComparingInt(Box::getLength)       // Utána hosszúság szerint
                        .thenComparingInt(Box::getHeight)      // Végül magasság szerint
                        .thenComparing(Box::getWeight))       // Legvégül súly szerint
                .toList(); // Eredményként rendezett listát ad vissza
    }

    private BoxSorter() {

    }
}

