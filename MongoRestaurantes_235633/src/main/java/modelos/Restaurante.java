package modelos;

import java.util.ArrayList;
import java.util.Date;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author adria
 */
public class Restaurante {
    
    private ObjectId id;
    private String nombre;
    private int rating;
    private Date fechaInauguracion;
    private String categorias[];

    public Restaurante() {
    }

    public Restaurante(ObjectId id) {
        this.id = id;
    }

    public Restaurante(String nombre, int rating, Date fechaInauguracion, String[] categorias) {
        this.nombre = nombre;
        this.rating = rating;
        this.fechaInauguracion = fechaInauguracion;
        this.categorias = categorias;
    }

    public Restaurante(ObjectId id, String nombre, int rating, Date fechaInauguracion, String[] categorias) {
        this.id = id;
        this.nombre = nombre;
        this.rating = rating;
        this.fechaInauguracion = fechaInauguracion;
        this.categorias = categorias;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getFechaInauguracion() {
        return fechaInauguracion;
    }

    public void setFechaInauguracion(Date fechaInauguracion) {
        this.fechaInauguracion = fechaInauguracion;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Restaurante{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", rating=").append(rating);
        sb.append(", fechaInauguracion=").append(fechaInauguracion);
        sb.append(", categorias=").append(categorias);
        sb.append('}');
        return sb.toString();
    }
    
}
