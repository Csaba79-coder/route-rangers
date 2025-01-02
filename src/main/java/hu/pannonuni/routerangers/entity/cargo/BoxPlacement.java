package hu.pannonuni.routerangers.entity.cargo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoxPlacement {
    private UUID boxId;
    private int x, y, z;
    private int endX, endY, endZ;
    private int layer;
}

