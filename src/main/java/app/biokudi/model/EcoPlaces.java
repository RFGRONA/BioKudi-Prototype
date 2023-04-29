package app.biokudi.model;

public class EcoPlaces extends Places{

    public EcoPlaces() {
    }

    public EcoPlaces(String name, String address, String coordinate, String activity, String description, String information) {
        super(name, address, coordinate, activity, description, information);
    }

    public EcoPlaces(int idPlace, String name, String address, String coordinate, String activity, String description, String information) {
        super(idPlace, name, address, coordinate, activity, description, information);
    }
    
}
