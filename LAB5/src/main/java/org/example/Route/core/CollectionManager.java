package org.example.Route.core;

import org.example.Route.models.Route;

import java.time.ZonedDateTime;
import java.util.Collection; // <-- Added this import
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.TreeSet;


public class CollectionManager {
    private LinkedHashSet<Route> routeSet;
    private ZonedDateTime initializationDate;


    public CollectionManager() {
        this.routeSet = new LinkedHashSet<>();
        this.initializationDate = ZonedDateTime.now();
    }


    public void addRoute(Route route) {
        routeSet.add(route);
    }


    public Collection<Route> getRoutes() { // <-- Changed return type to Collection
        return Collections.unmodifiableCollection(new TreeSet<>(routeSet));
    }


    public boolean updateRoute(long id, Route newRoute) { // <-- Changed parameter to long
        for (Route r : routeSet) {
            // Use .equals() for safe comparison of Long objects
            if (r.getId().equals(id)) {
                routeSet.remove(r);
                // The newRoute already has a new ID, this logic just replaces the old one.
                routeSet.add(newRoute);
                return true;
            }
        }
        return false;
    }


    public boolean removeById(Long id) { // <-- Changed parameter to Long
        // Use .equals() for safe comparison of Long objects
        return routeSet.removeIf(route -> route.getId().equals(id));
    }


    public void clear() {
        routeSet.clear();
    }


    public int size() {
        return routeSet.size();
    }


    public ZonedDateTime getInitializationDate() {
        return initializationDate;
    }
}