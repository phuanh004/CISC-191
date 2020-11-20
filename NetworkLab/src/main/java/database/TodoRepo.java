package database;

import models.ToDo;

public interface TodoRepo {
    public void startConnector();
    public void stopConnector();
    public void addToDo(ToDo toDo);
    public void showToDoList();
}
