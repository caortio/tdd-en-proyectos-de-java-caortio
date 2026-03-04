//Tests unitarios para la clase ToDo
package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestToDo {

    private ToDo todo;

    @BeforeEach
    void setUp() throws Exception {
        todo = new ToDo();
    }

    //Test constructor vacío
    @Test
    void constructorVacio() {
        // Arrange (ya hecho en @BeforeEach)

        // Act

        // Assert
        assertNull(todo.getNombre());
        assertNull(todo.getDescripcion());
        assertNull(todo.getFechaLimite());
        assertFalse(todo.isCompletado());
    }
    
    //Test constructor con parámetros
    @Test
    void testConstructor() {
    	// Arrange
        String nombreEsperado = "Revisar pull request";
        String descEsperada = "Aprobar cambios en capa de dominio";
        Date mañana = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);

        // Act
        todo = new ToDo(nombreEsperado, descEsperada, mañana, false);

        // Assert: Verificamos que los valores pasados en el constructor sean correctos
        assertEquals(nombreEsperado, todo.getNombre(), "El nombre no coincide.");
        assertEquals(descEsperada, todo.getDescripcion(), "La descripción no coincide.");
        assertSame(mañana, todo.getFechaLimite(), "La fecha límite no coincide.");
        assertFalse(todo.isCompletado(), "El estado de completado no debe ser verdadero.");
    }

    //Test getNombre y setNombre
    @Test
    void testNombre() {
        // Act
        todo.setNombre("Nuevo nombre");

        // Assert
        assertEquals("Nuevo nombre", todo.getNombre(), "El nombre no fue actualizado correctamente.");
    }
    
    //Test getDescripcion y setDescripcion
    @Test
    void testDescripcion() {
        // Act
        todo.setDescripcion("Nueva descripción");

        // Assert
        assertEquals("Nueva descripción", todo.getDescripcion(), "La descripción no fue actualizada correctamente.");
    }

    //Test getFechaLimite y setFechaLimite
    @Test
    void testFechaLimite() {
        // Act
        Date nuevaFecha = new Date(System.currentTimeMillis() + 2000000000);
        todo.setFechaLimite(nuevaFecha);

        // Assert
        assertEquals(nuevaFecha, todo.getFechaLimite(), "La fecha límite no fue actualizada correctamente.");
    }

    //Test getCompletado y setCompletado
    @Test
    void testCompletado() {
        // Act
        todo.setCompletado(true);

        // Assert
        assertTrue(todo.isCompletado(), "La tarea no fue marcada como completada correctamente.");
    }
}