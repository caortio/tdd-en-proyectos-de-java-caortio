//Test de integración para la clase Repositorio
package com.tt1.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RepositorioIntegrationTest {

    private Repositorio repositorio;
    private DBStub dbStub;

    @BeforeEach
    void setUp() {
        dbStub = new DBStub();
        repositorio = new Repositorio(dbStub);
    }
    
    //Test que almacena una tarea y permite obtenerla a través de su id
    @Test
    void testUnaTarea() {
        // Arrange
        ToDo tareaOriginal = new ToDo("Preparar presentación", "Desc", new Date(2026,3,18), false);

        // Act
        repositorio.storeTask(tareaOriginal);
        ToDo recuperada = repositorio.getTaskById(0);

        // Assert
        assertNotNull(recuperada, "Debe poder recuperar la tarea recién añadida");
        assertEquals("Preparar presentación", recuperada.getNombre());
        assertFalse(recuperada.isCompletado());
        assertEquals(tareaOriginal, recuperada);
    }
    
    //Test que almacena varias tareas incompletas y luego completa algunas
    @Test
    void testVariasTareas() {
        // Arrange
        repositorio.storeTask(new ToDo("Informe Q1", "Desc1", new Date(2026,3,10), false));
        repositorio.storeTask(new ToDo("Reunión cliente", "Desc2", new Date(2026,3,12), false));
        repositorio.storeTask(new ToDo("Revisar pull request", "Desc3", new Date(2026,3,20), false));
        repositorio.storeTask(new ToDo("Backup base de datos", "Desc4", new Date(2026,3,25), false));
        assertEquals(4, dbStub.getIncompleteTasks().size(), "DBStub debe tener 4 tareas");

        // Act
        boolean marcado1 = repositorio.markTaskCompleted(0); 
        boolean marcado2 = repositorio.markTaskCompleted(2); 

        // Assert
        assertTrue(marcado1);
        assertTrue(marcado2);
        assertFalse(repositorio.markTaskCompleted(7));
        List<ToDo> incompletas = repositorio.getIncompleteTasks();
        assertEquals(2, incompletas.size(), "Solo deben quedar 2 tareas incompletas");
    }

    //Test que añade una tarea con fecha pasada para ver si se guarda como pendiente
    @Test
    void storeTask_fechaPasada_sigueSiendoPendiente() {
        // Arrange + Act
        ToDo tareaAtrasada = new ToDo("Tarea muy atrasada","Desc", new Date(2025,3,25), false);
        repositorio.storeTask(tareaAtrasada);

        // Assert
        List<ToDo> pendientes = repositorio.getIncompleteTasks();
        assertEquals(1, pendientes.size());
        assertEquals("Tarea muy atrasada", pendientes.get(0).getNombre());
        assertFalse(pendientes.get(0).isCompletado());
    }
}