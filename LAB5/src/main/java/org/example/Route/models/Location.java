package org.example.Route.models;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Location {
    private float x;
    private Long y;    // Not null
    private Double z;  // Not null

    private Location() {}

    public Location(float x, Long y, Double z) {
        if (y == null || z == null) throw new IllegalArgumentException("Location fields y and z cannot be null.");
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() { return x; }
    public Long getY() { return y; }
    public Double getZ() { return z; }
    @Override
    public String toString() { return String.format("Location{x=%.2f, y=%d, z=%.2f}", x, y, z); }
}