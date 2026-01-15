package org.example.Route.models;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
    private Integer x; // Not null
    private Double y;  // Max 271, Not null

    private Coordinates() {}

    public Coordinates(Integer x, Double y) {
        if (x == null) throw new IllegalArgumentException("Coordinate x cannot be null.");
        if (y == null) throw new IllegalArgumentException("Coordinate y cannot be null.");
        if (y > 271) throw new IllegalArgumentException("y coordinate cannot be greater than 271.");
        this.x = x;
        this.y = y;
    }

    public Integer getX() { return x; }
    public Double getY() { return y; }
    @Override
    public String toString() { return String.format("Coordinates{x=%d, y=%.2f}", x, y); }
}