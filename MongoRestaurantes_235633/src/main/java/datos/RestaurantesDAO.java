package datos;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.regex;
import com.mongodb.client.result.DeleteResult;
import java.util.ArrayList;
import java.util.Arrays;
import modelos.Restaurante;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author adria
 */
public class RestaurantesDAO {
    
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;
    
    public RestaurantesDAO() {
        mongoClient = new MongoClient();
        database = mongoClient.getDatabase("ejercicios");
        collection = database.getCollection("restaurantes");
    }
    
    public void agregarRestaurante(Restaurante r){
        Document d = new Document("nombre", r.getNombre())
                .append("rating", r.getRating())
                .append("fechainauguracion", r.getFechaInauguracion())
                .append("categorias", Arrays.asList(r.getCategorias())
                );
        collection.insertOne(d);
    }
    
    public ArrayList<Restaurante> obtenerRestaurantes() {
        ArrayList<Restaurante> restaurantes = new ArrayList();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document d = cursor.next();
                Restaurante r = new Restaurante();
                r.setNombre(d.getString("nombre"));
                r.setRating(d.getInteger("rating"));
                r.setFechaInauguracion(d.getDate("fecha"));
                ArrayList<String> categorias = (ArrayList<String>) d.get("categorias");
                r.setCategorias(categorias.toArray(new String[categorias.size()]));
                restaurantes.add(r);
            }
        } finally {
            cursor.close();
        }
        return restaurantes;
    }
    
//    public void eliminarRestaurante(String id){
//        collection.deleteOne(new Document("_id", new ObjectId()));
//    }
    
    public ArrayList<Restaurante> consultarRestaurantesRating() {
        ArrayList<Restaurante> restaurantes = new ArrayList();
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
        collection.find(gt("rating", 4)).forEach(printBlock);
        return restaurantes;
    }
    
    public ArrayList<Restaurante> consultarRestaurantesCategoria() {
        ArrayList<Restaurante> restaurantes = new ArrayList();
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
        collection.find(eq("categorias", "Pizza")).forEach(printBlock);
        return restaurantes;
    }
    
    public ArrayList<Restaurante> consultarRestaurantesNombre() {
        ArrayList<Restaurante> restaurantes = new ArrayList();
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };
        collection.find(regex("nombre", "sushi","i")).forEach(printBlock);
        return restaurantes;
    }
    
    public void agregarCategoria(){
        collection.updateOne(eq("nombre", "sushilito"),
                new Document("$addToSet", new Document("categorias", "Familiar")));
        Document doc = collection.find(eq("nombre", "sushilito")).first();
        System.out.println(doc.toJson());
    }
    
    public boolean eliminarId(String id) {
        try {
            DeleteResult result = collection.deleteOne(eq("_id", new ObjectId(id)));
            return result.getDeletedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarRating(){
        try {
            DeleteResult result = collection.deleteMany(lte("rating", 3));
            System.out.println(result.getDeletedCount());
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }        
    }
    
}
