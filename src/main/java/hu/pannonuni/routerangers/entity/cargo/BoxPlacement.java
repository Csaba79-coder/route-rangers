package hu.pannonuni.routerangers.entity.cargo;

import java.util.UUID;

public class BoxPlacement {
    private UUID boxId;  // UUID típusú azonosító
    private int x, y, z;
    private int layer;

    // Constructor, getters, and setters
    public BoxPlacement(Box box, int x, int y, int z, int layer) {
        this.boxId = box.getId();
        this.x = x;
        this.y = y;
        this.z = z;
        this.layer = layer;
    }

    // Getters and setters
    public UUID getBoxId() {
        return boxId;
    }

    public void setBoxId(UUID boxId) {
        this.boxId = boxId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }
}

