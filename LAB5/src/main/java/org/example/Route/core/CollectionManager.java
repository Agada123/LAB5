package org.example.Route.core;

import org.example.Route.models.Route;

import java.time.ZonedDateTime;
import java.util.Collection; 
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


    public Collection<Route> getRoutes() { 
        return Collections.unmodifiableCollection(new TreeSet<>(routeSet));
    }


    public boolean updateRoute(long id, Route newRoute) { 
        for (Route r : routeSet) {
            
            if (r.getId().equals(id)) {
                routeSet.remove(r);
                
                routeSet.add(newRoute);
                return true;
            }
        }
        return false;
    }


    public boolean removeById(Long id) { 
        
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
