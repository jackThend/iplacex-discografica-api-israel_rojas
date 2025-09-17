package org.iplacex.proyectos.discografia;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("artistas")
public class Artista {

    @Id
    private String id;

    private String nombre;

    private List<String> estilos;

    @Field("anioFundacion")
    private int anioFundacion;

    private boolean estaActivo;

     // constructor
     
    public Artista(String id, String nombre, List<String> estilos, int anioFundacion, boolean estaActivo) {
        this.id = id;
        this.nombre = nombre;
        this.estilos = estilos;
        this.anioFundacion = anioFundacion;
        this.estaActivo = estaActivo;
    }

    // getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getEstilos() {
        return estilos;
    }

    public void setEstilos(List<String> estilos) {
        this.estilos = estilos;
    }

    public int getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(int anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }

    // sobreescribir
    @Override
    public String toString() {
        return "Artista{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", estilos=" + estilos +
               ", anioFundacion=" + anioFundacion +
               ", estaActivo=" + estaActivo +
               '}';
    }
}