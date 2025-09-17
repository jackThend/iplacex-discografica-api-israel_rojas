package org.iplacex.proyectos.discografia;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document("discos")
public class Disco { 

    @Id
    private String id;

    @Field("idArtista")
    private String idArtista; 

    private String nombre;

    @Field("anioLanzamiento")
    private int anioLanzamiento; 

    private List<String> canciones;

    public Disco(String id, String idArtista, String nombre, int anioLanzamiento, List<String> canciones) { 
        this.id = id;
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.anioLanzamiento = anioLanzamiento;
        this.canciones = canciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdArtista() { 
        return idArtista;
    }

    public void setIdArtista(String idArtista) { 
        this.idArtista = idArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioLanzamiento() { 
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) { 
        this.anioLanzamiento = anioLanzamiento;
    }

    public List<String> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<String> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        return "Disco{" +
               "id='" + id + '\'' +
               ", idArtista='" + idArtista + '\'' +
               ", nombre='" + nombre + '\'' +
               ", anioLanzamiento=" + anioLanzamiento +
               ", canciones=" + canciones +
               '}';
    }
}