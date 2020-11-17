package database;


import com.mongodb.Block;
import com.mongodb.MongoClientSettings;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import models.ToDo;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DBConnector implements TodoRepo {
    private MongoClient mongoClient;
    private MongoCollection<ToDo> toDoCollection;

    @Override
    public void startConnector() {
        // Creating a Mongo client
        mongoClient = MongoClients.create("mongodb+srv://anhpham:x8iO2rRwuztxBLPr@cluster0.jlkxx.mongodb.net/networklab?retryWrites=true&w=majority");

        // Config POJO
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();


        // Accessing the database
        MongoDatabase database = mongoClient.getDatabase("networklab")
                                            .withCodecRegistry(pojoCodecRegistry);

        // Retrieving the todo collection
        toDoCollection = database.getCollection("todo", ToDo.class);
        System.out.println("Collection myCollection selected successfully");
    }

    @Override
    public void stopConnector() {
        mongoClient.close();
    }

    @Override
    public void addToDo(ToDo toDo) {
        toDoCollection.insertOne(toDo);

//        Document document = new Document("title", toDo.getTitle())
//            .append("description", toDo.getDescription())
//            .append("do_date", toDo.getDateInDisplayFormat("EEE, MMM d", toDo.getDoDate()))
//            .append("due_date", toDo.getDateInDisplayFormat("EEE, MMM d", toDo.getDueDate()));

//        toDoCollection.insertOne(document);
        System.out.println("Document inserted successfully");
    }

    @Override
    public void deleteToDo(String id) {

    }

    @Override
    public void showToDoList() {
        toDoCollection.find().forEach(toDo -> System.out.println(toDo.toString()));
    }
}
