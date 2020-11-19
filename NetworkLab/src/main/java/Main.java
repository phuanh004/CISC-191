import com.github.javafaker.Faker;
import database.DBConnector;
import models.ToDo;

import java.util.Locale;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        DBConnector connector = new DBConnector();

        // Connect to the database
        connector.startConnector();

        // Generate new To-do
        ToDo toDo = generateNewTodo();

        // Add new To-do to BD
        connector.addToDo(toDo);

        // Print data
        connector.showToDoList();

        // Disconnect to the database
        connector.stopConnector();

//        while (isContinue) {
//            printMenu();
//        }
//
//        Document document = new Document("title", "MongoDB")
//                .append("description", "database")
//                .append("likes", 100)
//                .append("url", "http://www.tutorialspoint.com/mongodb/")
//                .append("by", "tutorials point");
//
//        collection.insertOne(document);
//        System.out.println("Document inserted successfully");
//

    }

    private static ToDo generateNewTodo() {
        // Random from 0 - 3
        int rand = (int) (Math.random() * 3);
        // Random time from 11/08/2020 - 12/24/2020
        long randomEpoch = 1604820349 + Math.abs(new Random().nextLong()) % (1608794749-1604820349);

        // Declare variables
        Faker faker = new Faker(new Locale("en-US"));
        ToDo toDo = new ToDo();

        toDo.setDueDate(String.valueOf(randomEpoch));

        switch (rand) {
            case 0: // READ A BOOK + RANDOM BOOK NAME
                toDo.setTitle("Read " + faker.book().title());
                toDo.setDescription("By - " + faker.book().author());
                break;

            case 1:  // CALL BACK TO SOMEONE
                toDo.setTitle("Call " + faker.name().fullName() + " back");
                toDo.setDescription("Phone number - " + faker.phoneNumber().cellPhone());
                break;

            case 2: // EAT SOMETHING
                toDo.setTitle("Eat " + faker.food().spice());
                toDo.setDescription(null);
                break;

            default: // GO TO SOMEWHERE
                toDo.setTitle("Go to " + faker.address().state());
                toDo.setDescription("Address - " + faker.address().streetAddress());
                break;
        }


        return toDo;
    }
}
