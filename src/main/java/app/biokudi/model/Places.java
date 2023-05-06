package app.biokudi.model;

public abstract class Places {
    
    // Abstract class that sets the minimal attributes and methods for creating places
    
    private int idPlace;
    private String name;
    private String address;
    private String coordinate;
    private String activity;
    private String description;
    private String information;

    public Places() {
    }
    
    // Constructor overloaded without ID
    public Places(String name, String address, String coordinate, String activity, String description, String information) {
        this.name = name;
        this.address = address;
        this.coordinate = coordinate;
        this.activity = activity;
        this.description = description;
        this.information = information;
    }
    
    // Constructor overloaded without ID
    public Places(String name, String address, String activity, String description, String information) {
        this.name = name;
        this.address = address;
        this.activity = activity;
        this.description = description;
        this.information = information;
    }
    
    // Constructor overloaded with ID
    public Places(int idPlace, String name, String address, String coordinate, String activity, String description, String information) {
        this.idPlace = idPlace;
        this.name = name;
        this.address = address;
        this.coordinate = coordinate;
        this.activity = activity;
        this.description = description;
        this.information = information;
    }
    
    // Constructor overloaded with data for markers
    public Places(int idPlace, String name, String coordinate) {
        this.idPlace = idPlace;
        this.name = name;
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Places{" + "idPlace=" + idPlace + ", name=" + name + ", address=" + address + ", coordinate=" + coordinate + ", activity=" + activity + ", description=" + description + ", information=" + information + '}';
    }
    
    // Getters and Setters
    
    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idLugar) {
        this.idPlace = idLugar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
   
}