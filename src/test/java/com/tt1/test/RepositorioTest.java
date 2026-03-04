package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class RepositorioTest {

    private DBStub dbStub;
    private Repositorio repositorio;
    private ToDo task1;
    private ToDo task2;

    @BeforeEach
    public void setUp() {
        dbStub = new DBStub();
        repositorio = new Repositorio(dbStub);

        task1 = new ToDo("Tarea 1", "Descripción 1", new Date(), false);
        task2 = new ToDo("Tarea 2", "Descripción 2", new Date(), false);
    }
    	
    //Test StoreTask y GetTaskById
    @Test
    public void testStoreTask_GetTaskById() {
        // Act
        repositorio.storeTask(task1);
        repositorio.storeTask(task2);
        
        ToDo foundTask1 = repositorio.getTaskById(0); 
        ToDo foundTask2 = repositorio.getTaskById(1); 

        // Assert
        assertNotNull(foundTask1);
        assertNotNull(foundTask2);
        assertEquals("Tarea 1", foundTask1.getNombre());
        assertEquals("Tarea 2", foundTask2.getNombre());
        assertSame(task1, foundTask1); 
        assertSame(task2, foundTask2);
    }

    //Test StoreEmail
    @Test
    void testStoreEmail() {
        //Act
        repositorio.storeEmail("juan.perez@ejemplo.com");
        repositorio.storeEmail("ANA.gomez@empresa.com");

        // Assert
        assertTrue(dbStub.getEmailAgenda().contains("juan.perez@ejemplo.com"));
        assertTrue(dbStub.getEmailAgenda().contains("ANA.gomez@empresa.com"));
        assertEquals(2, dbStub.getEmailAgenda().size());
    }

    //Test MarkTaskCompleted
    @Test
    public void testMarkTaskCompleted() {
        // Arrange
        repositorio.storeTask(task1);

        // Act
        boolean exito = repositorio.markTaskCompleted(0);

        // Assert
        assertTrue(exito);
        assertTrue(task1.isCompletado());
    }
    
    //Test GetIncompleteTasks
    @Test
    public void testGetIncompleteTasks() {
        // Arrange
        task1.setCompletado(false);
        task2.setCompletado(true);
        repositorio.storeTask(task1);
        repositorio.storeTask(task2);

        // Act
        List<ToDo> incompleteTasks = repositorio.getIncompleteTasks();

        // Assert
        assertEquals(1, incompleteTasks.size());
        assertEquals(task1, incompleteTasks.get(0));
    }   
}
