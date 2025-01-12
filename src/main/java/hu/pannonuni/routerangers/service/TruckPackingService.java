package hu.pannonuni.routerangers.service;

import hu.pannonuni.routerangers.entity.cargo.Box;
import hu.pannonuni.routerangers.entity.cargo.BoxPlacement;
import hu.pannonuni.routerangers.entity.vehicle.Truck;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static hu.pannonuni.routerangers.util.BoxSorter.sortBoxes;

@Service
public class TruckPackingService {

    public List<BoxPlacement> packBoxes(List<Box> boxesInfo, Truck truck) {
        List<BoxPlacement> placements = new ArrayList<>();
        List<Box> boxes = sortBoxes(boxesInfo); // Dobozok rendezése a heurisztikus algoritmus részére
        int x = 0, y = 0, z = 0; // Kezdő koordináták
        int layer = 1; // Kezdő réteg
        double currentWeight = 0; // Az összesített súly

        // Iterálj a dobozokon, hogy pakold őket
        for (Box box : boxes) {
            // Ellenőrizd, hogy a doboz belefér-e a jelenlegi rétegbe (szélesség és hosszúság ellenőrzése)
            if (x + box.getWidth() > truck.getWidth()) {
                x = 0; // Visszaállítjuk az x koordinátát (lépés a következő sorra)
                y += box.getLength(); // Lépés a következő sorra a jelenlegi rétegen

                // Ellenőrizd, hogy a doboz belefér-e a teherautó hosszába
                if (y + box.getLength() > truck.getLength()) {
                    y = 0; // Visszaállítjuk a y koordinátát (lépés a következő oszlopra)
                    z += box.getHeight(); // Lépés az új rétegre
                    layer++; // Növeljük a réteg számát

                    // Ha nincs több hely az új rétegben, leállunk
                    if (z + box.getHeight() > truck.getHeight()) {
                        System.out.println("Nincs több hely a teherautón a(z) " + box.getId() + " doboznak.");
                        break;
                    }
                }
            }

            // Ellenőrizzük, hogy a súlyterhelés nem lépi-e túl a maximális terhelést
            if (currentWeight + box.getWeight() > truck.getWeight()) {
                System.out.println("A teherautó súlyhatárát meghaladja a(z) " + box.getId() + " doboz.");
                continue; // Ha túlmegy a súlyhatáron, lépjünk a következő dobozra
            }

            // Kiszámítjuk a doboz végi koordinátáit
            int endX = x + box.getWidth();
            int endY = y + box.getLength();
            int endZ = z + box.getHeight();

            // A doboz elhelyezkedésének hozzáadása az eredményhez (kezdő és vég koordináták)
            placements.add(new BoxPlacement(box.getId(), x, y, z, endX, endY, endZ, layer));

            // Frissítjük az x koordinátát a következő dobozhoz
            x = endX; // Az aktuális doboz vége legyen a következő doboz kezdete

            // Frissítjük az összesített súlyt
            currentWeight += box.getWeight();
        }

        return placements;
    }
}
