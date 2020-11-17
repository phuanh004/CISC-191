import database.DBConnector;
import models.ToDo;

public class Main {

    public static void main(String[] args){
        DBConnector connector = new DBConnector();

        // Connect to the database
        connector.startConnector();

        // Add new To-do
        ToDo toDo = new
                ToDo("To do 1", "Description of to do 1", "1605945600", null);

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
}
