package database;

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

public class DBConnector implements TodoRepo{
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
        System.out.println("*** Connected to the database");
    }

    @Override
    public void stopConnector() {
        mongoClient.close();
        System.out.println("*** Closed connection to the database");
    }

    @Override
    public void addToDo(ToDo toDo) {
        toDoCollection.insertOne(toDo);
        System.out.println("*** Added a new to-do: " + toDo.getTitle());

    }

    @Override
    public void showToDoList() {
        System.out.println("*** Print to-do list: ");
        System.out.println("-----------------------------------");
        toDoCollection.find().forEach(toDo -> System.out.println(toDo.toString()));
    }
}
