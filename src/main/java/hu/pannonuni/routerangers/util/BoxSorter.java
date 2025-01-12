package hu.pannonuni.routerangers.util;

import hu.pannonuni.routerangers.entity.cargo.Box;

import java.util.Comparator;
import java.util.List;

public class BoxSorter {

    // updating the logic with reversed order! as the hugest and heaviest boxes should be placed first
    public static List<Box> sortBoxes(List<Box> boxes) {
        List<Box> sortedByWidth = boxes.stream()
                .sorted(Comparator.comparingInt(Box::getWidth).reversed())
                .toList();

        List<Box> sortedByLength = sortedByWidth.stream()
                .sorted(Comparator.comparingInt(Box::getLength).reversed())
                .toList();


        List<Box> sortedByHeight = sortedByLength.stream()
                .sorted(Comparator.comparingInt(Box::getHeight).reversed())
                .toList();

        return sortedByHeight.stream()
                .sorted(Comparator.comparing(Box::getWeight).reversed())
                .toList();
    }

    private BoxSorter() {

    }
}

