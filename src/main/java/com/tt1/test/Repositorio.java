package com.tt1.test;

import java.util.List;
import java.util.Set;

/**
 * Repositorio que gestiona el almacenamiento de tareas (ToDo) y la agenda de correos.
 * 
 * Esta clase proporciona métodos para almacenar tareas, almacenar emails, 
 * consultar tareas por ID, obtener tareas incompletas y obtener la agenda de correos.
 * Internamente, utiliza un {@link DBStub} como almacenamiento simulado.
 * 
 * Solo se documenta esta clase como parte del dominio principal, no el DBStub en sí.
 * 
 * @author caortio
 * @version 1.0
 */
public class Repositorio {
        
    /** Stub de base de datos utilizado para simular almacenamiento en memoria */
    private DBStub dbStub;

    /**
     * Constructor del repositorio.
     * 
     * @param dbStub Instancia de DBStub que simula la base de datos.
     */
    public Repositorio(DBStub dbStub) {
        this.dbStub = dbStub;
    }

    /**
     * Almacena una nueva tarea en el repositorio.
     * 
     * @param task Tarea (ToDo) que se desea almacenar.
     */
    public void storeTask(ToDo task) {
        dbStub.addTask(task);
    }

    /**
     * Almacena un email en la agenda de correos.
     * 
     * @param email Dirección de correo a agregar.
     */
    public void storeEmail(String email) {
        dbStub.addEmail(email);
    }

    /**
     * Obtiene una tarea por su índice en la lista del repositorio.
     * 
     * @param id índice de la tarea a recuperar.
     * @return Tarea correspondiente si existe, null si no se encuentra.
     */
    public ToDo getTaskById(int id) {
        return dbStub.findTaskById(id);
    }

    /**
     * Marca una tarea como completada.
     * 
     * @param index índice de la tarea a marcar como completada.
     * @return true si la tarea fue marcada, false si no se encontró.
     */
    public boolean markTaskCompleted(int index) {
        return dbStub.markTaskAsCompleted(index);
    }
    
    /**
     * Obtiene la lista de tareas que no han sido completadas.
     * 
     * @return Lista de tareas incompletas.
     */
    public List<ToDo> getIncompleteTasks() {
        return dbStub.getIncompleteTasks();
    }

    /**
     * Obtiene la agenda de emails almacenados.
     * 
     * @return Conjunto de direcciones de correo en la agenda.
     */
    public Set<String> getEmailAgenda() {
        return dbStub.getEmailAgenda();
    }
}