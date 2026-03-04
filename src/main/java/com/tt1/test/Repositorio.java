package com.tt1.test;

import java.util.List;
import java.util.Set;

public class Repositorio {
    private DBStub dbStub;

    public Repositorio(DBStub dbStub) {
        this.dbStub = dbStub;
    }

    public void storeTask(ToDo task) {
        dbStub.addTask(task);
    }

    public void storeEmail(String email) {
        dbStub.addEmail(email);
    }

    public ToDo getTaskById(int id) {
        return dbStub.findTaskById(id);
    }

    // Marca tarea como completada
    public boolean markTaskCompleted(int index) {
        return dbStub.markTaskAsCompleted(index);
    }
    
    // Obtiene tareas incompletas
    public List<ToDo> getIncompleteTasks() {
        return dbStub.getIncompleteTasks();
    }

    // Obtiene la agenda de emails
    public Set<String> getEmailAgenda() {
        return dbStub.getEmailAgenda();
    }
}