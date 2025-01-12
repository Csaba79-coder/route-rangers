package hu.pannonuni.routerangers.util;

import hu.pannonuni.routerangers.entity.cargo.Box;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

class BoxSorterTest {

    @Test
    void testSortBoxes() {
        // Tesztadatok
        List<Box> boxes = List.of(
                new Box(10, 5, 8, 50),   // width: 10, length: 5, height: 8, weight: 50
                new Box(8, 7, 12, 60),   // width: 8, length: 7, height: 12, weight: 60
                new Box(10, 5, 10, 55),  // width: 10, length: 5, height: 10, weight: 55
                new Box(8, 7, 10, 70)    // width: 8, length: 7, height: 10, weight: 70
        );

        // A sortBoxes metódus hívása
        List<Box> sortedBoxes = BoxSorter.sortBoxes(boxes);

        // Először nézd meg a rendezés előtti listát
        System.out.println("Original Boxes: ");
        boxes.forEach(box -> System.out.println("Width: " + box.getWidth() + ", Length: " + box.getLength() +
                ", Height: " + box.getHeight() + ", Weight: " + box.getWeight()));

        // Nézd meg a rendezett listát
        System.out.println("\nSorted Boxes: ");
        sortedBoxes.forEach(box -> System.out.println("Width: " + box.getWidth() + ", Length: " + box.getLength() +
                ", Height: " + box.getHeight() + ", Weight: " + box.getWeight()));

        // Ellenőrzés
        // Az első elemnek szélessége 10-nek kell lennie
        assertEquals(Integer.valueOf(10), sortedBoxes.get(0).getWidth());
        // A második elemnek is szélessége 10-nek kell lennie
        assertEquals(Integer.valueOf(10), sortedBoxes.get(1).getWidth());
        // A harmadik elem szélessége 8
        assertEquals(Integer.valueOf(8), sortedBoxes.get(2).getWidth());
        // A negyedik elem szélessége szintén 8
        assertEquals(Integer.valueOf(8), sortedBoxes.get(3).getWidth());
    }

}