package Modelos;

public class Juego {

    private int id;
    private int yearpublicacion;
    private String nombre, descripcion, desarrollador, genero, plataforma;
    private float precio;



    public Juego(){

    }

    public Juego(int id, int yearpublicacion, String nombre, String descripcion, String desarrollador, String genero, String plataforma, float precio) {
        this.id = id;
        this.yearpublicacion = yearpublicacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.desarrollador = desarrollador;
        this.genero = genero;
        this.plataforma = plataforma;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYearpublicacion() {
        return yearpublicacion;
    }

    public void setYearpublicacion(int yearpublicacion) {
        this.yearpublicacion = yearpublicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Juego{" +
                "id=" + id +
                ", yearpublicacion=" + yearpublicacion +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", desarrollador='" + desarrollador + '\'' +
                ", genero='" + genero + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", precio=" + precio +
                '}';
    }
}



