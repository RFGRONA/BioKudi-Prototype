package app.biokudi.model;

public class Lugares {
    
    private int idLugar;
    private String nombre;
    private String direccion;
    private String coordenada;
    private String actividad;
    private String descripcion;
    private String informacion;

    public Lugares() {
    }
    
    //Sobrecargado sin ID
    public Lugares(String nombre, String direccion, String coordenada, String actividad, String descripcion, String informacion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.actividad = actividad;
        this.descripcion = descripcion;
        this.informacion = informacion;
    }
    
    //Sobrecargado con ID
    public Lugares(int idLugar, String nombre, String direccion, String coordenada, String actividad, String descripcion, String informacion) {
        this.idLugar = idLugar;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.actividad = actividad;
        this.descripcion = descripcion;
        this.informacion = informacion;
    }

    @Override
    public String toString() {
        return "Lugares{" + "idLugar=" + idLugar + ", nombre=" + nombre + ", direccion=" + direccion + ", coordenada=" + coordenada + ", actividad=" + actividad + ", descripcion=" + descripcion + ", informacion=" + informacion + '}';
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
   
}
