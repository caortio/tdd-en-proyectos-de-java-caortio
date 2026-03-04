package com.tt1.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DBStub {
    private List<ToDo> toDoList;
    private Set<String> emailAgenda;

    public DBStub() {
        this.toDoList = new ArrayList<>();
        this.emailAgenda = new HashSet<>();
    }

    public void addTask(ToDo task) {
        toDoList.add(task);
    }

    public ToDo findTaskById(int id) {
        if (id >= 0 && id < toDoList.size()) {
            return toDoList.get(id);
        }
        return null; // Tarea no encontrada
    }

    public List<ToDo> getIncompleteTasks() {
        List<ToDo> incompleteTasks = new ArrayList<>();
        for (ToDo todo : toDoList) {
            if (!todo.isCompletado()) {
                incompleteTasks.add(todo);
            }
        }
        return incompleteTasks;
    }

    public boolean addEmail(String email) {
        return emailAgenda.add(email);
    }

    public Set<String> getEmailAgenda() {
        return emailAgenda;
    }

    public boolean markTaskAsCompleted(int index) {
        ToDo task = findTaskById(index);
        if (task != null) {
            task.setCompletado(true);
            return true;
        }
        return false;
    }
    
    public List<ToDo> getToDoList() {
        return toDoList;
    }

}