//Test de integración para la clase Servicio
package com.tt1.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServicioIntegrationTest {

    private Servicio servicio;
    private DBStub dbStub;
    private MailerStub mailerStub;
    private Repositorio repo;

    @BeforeEach
    void setUp() {
        dbStub = new DBStub();
        repo = new Repositorio(dbStub);
        mailerStub = new MailerStub();
        servicio = new Servicio(repo, mailerStub);
    }
    
    //Test para el flujo de añadir tarea, marcarlar como completada y desaparecer de incompletas
    @Test
    void testFlujo() {
        // Arrange
        servicio.addTask("Preparar presentación", "2026-03-15");

        // Assert
        List<ToDo> incompletas = servicio.getIncompleteTasks();
        assertEquals(1, incompletas.size());
        assertEquals("Preparar presentación", incompletas.get(0).getNombre());
        assertFalse(incompletas.get(0).isCompletado());

        // Act
        boolean marcado = servicio.markTaskAsCompleted(0);
        assertTrue(marcado);

        // Assert
        incompletas = servicio.getIncompleteTasks();
        assertTrue(incompletas.isEmpty());
    }    
}
