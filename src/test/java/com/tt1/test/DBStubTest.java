//Tests unitarios para la clase DBStub
package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DBStubTest {

    private DBStub dbStub;
    private ToDo todo1;
    private ToDo todo2;

    @BeforeEach
    void setUp() throws Exception {
        // Inicializamos el objeto DBStub y los ToDos para cada test
        dbStub = new DBStub();
        todo1 = new ToDo("Tarea 1", "Descripción 1", new Date(), false);
        todo2 = new ToDo("Tarea 2", "Descripción 2", new Date(System.currentTimeMillis() + 10000000), false);
    }
    
    //Test addTask
    @Test
    void testAddTask() {
        // Act
        dbStub.addTask(todo1);
        dbStub.addTask(todo2);

        // Assert
        assertEquals(2, dbStub.getToDoList().size(), "El número de tareas no coincide.");
        assertTrue(dbStub.getToDoList().contains(todo1), "La tarea 1 no fue añadida.");
        assertTrue(dbStub.getToDoList().contains(todo2), "La tarea 2 no fue añadida.");
    }

    //Test FindTaskById
    @Test
    void testFindTaskById() {
        // Arrange
        dbStub.addTask(todo1);
        dbStub.addTask(todo2);
        
        // Act
        ToDo foundTask1 = dbStub.findTaskById(0);
        ToDo foundTask2 = dbStub.findTaskById(1);

        // Assert
        assertNotNull(foundTask1);
        assertEquals(todo1, foundTask1, "La tarea encontrada no es la correcta.");
        assertSame(todo2, foundTask2,  "La tarea encontrada no es la correcta.");
    }

    //Test GetIncompleteTasks
    @Test
    void testGetIncompleteTasks() {
        // Arrange
        dbStub.addTask(todo1);
        dbStub.addTask(todo2);
        todo1.setCompletado(true);
        
        //Act
        List<ToDo> incompleteTasks = dbStub.getIncompleteTasks();
        
        // Assert
        assertEquals(1, incompleteTasks.size(), "El número de tareas incompletas no coincide.");
        assertTrue(incompleteTasks.contains(todo2), "La tarea incompleta no fue encontrada.");
        assertFalse(incompleteTasks.contains(todo1), "La tarea completada debería ser ignorada.");
    }
    
    //Test MarkTaskAsCompleted
    @Test
    void testMarkTaskAsCompleted() {
        // Arrange
        dbStub.addTask(todo1);

        // Act
        boolean result = dbStub.markTaskAsCompleted(0);

        // Assert
        assertTrue(result, "La tarea no fue marcada como completada.");
        assertTrue(todo1.isCompletado(), "La tarea no fue marcada como completada.");
    }

    //Test AddEmail y GetEmailAgenda
    @Test
    void testAddEmail() {
        // Act
        boolean result = dbStub.addEmail("test@example.com");
        
        // Assert
        assertTrue(result, "El correo electrónico no fue agregado correctamente.");
        assertEquals(1, dbStub.getEmailAgenda().size(), "El correo electrónico no fue agregado correctamente a la agenda.");
        assertTrue(dbStub.getEmailAgenda().contains("test@example.com"), "La agenda no contiene el correo electrónico.");
    }

}