package org.example.Route.utils;

import org.example.Route.core.CollectionManager;
import org.example.Route.models.Route;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;






public class XMLParser {


    public static String serializeCollection(CollectionManager collectionManager) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(RoutesWrapper.class, Route.class);


        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        RoutesWrapper wrapper = new RoutesWrapper();
        wrapper.setRoutes(new ArrayList<>((Collection<Route>)collectionManager.getRoutes()));
        StringWriter writer = new StringWriter();
        marshaller.marshal(wrapper, writer);
        return writer.toString();
    }


    public static void populateCollection(String xmlData, CollectionManager collectionManager) throws JAXBException {

        if (xmlData == null || xmlData.trim().isEmpty()) {
            System.out.println("XML data is empty, starting with an empty collection.");
            return;
        }


        JAXBContext context = JAXBContext.newInstance(RoutesWrapper.class, Route.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(xmlData);
        RoutesWrapper wrapper = (RoutesWrapper) unmarshaller.unmarshal(reader);


        if (wrapper.getRoutes() != null) {
            for (Route route : wrapper.getRoutes()) {
                collectionManager.addRoute(route);
            }
            System.out.println("Successfully loaded " + wrapper.getRoutes().size() + " routes from the file.");
        }
    }
}