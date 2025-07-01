package org.example.Route.utils;

import org.example.Route.models.Route;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Collection;


@XmlRootElement(name = "routes")
public class RoutesWrapper { 
    private Collection<Route> routes;

    @XmlElement(name = "route")
    public Collection<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Collection<Route> routes) {
        this.routes = routes;
    }
}
