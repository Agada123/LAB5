package org.example.Route.models;

import org.example.Route.utils.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@XmlRootElement(name = "route")
@XmlAccessorType(XmlAccessType.FIELD)
public class Route implements Comparable<Route> {
    private static final AtomicLong idGenerator = new AtomicLong(1);

    @XmlAttribute
    private Long id;

    private String name;
    private Coordinates coordinates;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime creationDate;

    private Location from;
    private Location to;
    private int distance;

    private Route() {}

    public Route(String name, Coordinates coordinates, Location from, Location to, int distance) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty.");
        if (coordinates == null || from == null || to == null) throw new IllegalArgumentException("Coordinates and locations cannot be null.");
        if (distance <= 1) throw new IllegalArgumentException("Distance must be greater than 1.");

        this.id = idGenerator.getAndIncrement();
        this.creationDate = LocalDateTime.now();
        this.name = name;
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public Location getFrom() { return from; }
    public Location getTo() { return to; }
    public int getDistance() { return distance; }

    @Override
    public int compareTo(Route other) { return this.id.compareTo(other.id); }

    @Override
    public String toString() { return String.format("Route{id=%d, name='%s', distance=%d}", id, name, distance); }

    @Override
    public int hashCode() { return id.hashCode(); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Route)) return false;
        Route other = (Route) obj;
        return this.id.equals(other.id);
    }
}