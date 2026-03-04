//Tests unitarios para la clase Servicio
package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicioTest {

    private DBStub dbStub;
    private MailerStub mailerStub;
    private Repositorio repositorio;
    private Servicio servicio;

    @BeforeEach
    public void setUp() {
        dbStub = new DBStub();
        mailerStub = new MailerStub();
        repositorio = new Repositorio(dbStub);
        servicio = new Servicio(repositorio, mailerStub);
    }

    //Test AddTask
    @Test
    public void testAddTask() {
        // Arrange
    	String nombre = "Tarea 1";
    	String fecha = "2026-02-25";
    	
    	//Act
        servicio.addTask(nombre, fecha);

        // Assert
        List<ToDo> incompletas = servicio.getIncompleteTasks();
        assertEquals(1, incompletas.size());

        ToDo tarea = incompletas.get(0);
        assertEquals(nombre, tarea.getNombre());
        assertFalse(tarea.isCompletado());
    }

    //Test AddEmail
    @Test
    public void testAddEmail() {
        // Act
        servicio.addEmail("test@example.com");
        servicio.addEmail("test2@empresa.com");

        // Assert
        assertTrue(dbStub.getEmailAgenda().contains("test@example.com"));
        assertTrue(dbStub.getEmailAgenda().contains("test2@empresa.com"));
        assertEquals(2, dbStub.getEmailAgenda().size());
    }

    //Test MarkTaskAsCompleted
    @Test
    public void testMarkTaskAsCompleted() {
        // Arrange
    	servicio.addTask("Tarea 1", "2026-03-10");
        servicio.addTask("Tarea 2", "2026-03-15");
        assertEquals(2, servicio.getIncompleteTasks().size());

        // Act
        boolean exito = servicio.markTaskAsCompleted(0);

        // Assert
        assertTrue(exito);
        assertEquals(1, servicio.getIncompleteTasks().size());
    }
    
    //Test CheckForOverdueTasks
    @Test
    public void testCheckForOverdueTasks() {
        // Arrange:
    	servicio.addTask("Informe mensual", "2026-02-28");
        servicio.addTask("Reunión pendiente", "2026-03-10"); 

        servicio.addEmail("jefe@empresa.com");
        servicio.addEmail("equipo@proyecto.com");

        // Act
        servicio.checkForOverdueTasks();

        // Assert
        assertTrue(mailerStub.sendEmail("test@example.com", "Alerta: La tarea 'Tarea 2' ha vencido."),
                "No se enviaron correos de alerta para las tareas vencidas.");
    }
}