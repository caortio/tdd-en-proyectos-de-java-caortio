//Tests unitarios para la clase MailerStub
package com.tt1.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MailerStubTest {

    private MailerStub mailerStub;

    @BeforeEach
    void setUp() throws Exception {
        mailerStub = new MailerStub();
    }
    
    //Test SendEmail para datos correctos
    @Test
    void testSendEmailValidInput() {
        // Arrange
        String email = "test@example.com";
        String message = "Este es un mensaje de prueba.";

        // Act
        boolean result = mailerStub.sendEmail(email, message);

        // Assert
        assertTrue(result, "El correo no fue enviado correctamente.");
    }
    
    //Test SendEmail con mensaje vacío
    @Test
    void testSendEmailEmptyMessage() {
        // Arrange
        String email = "test@example.com";
        String message = "";

        // Act
        boolean result = mailerStub.sendEmail(email, message);

        // Assert
        assertFalse(result, "El correo no debería ser enviado con un mensaje vacío.");
    }
    
    //Test SendEmail con email incorrecto
    @Test
    void testSendEmailInvalidEmail() {
        // Arrange
        String email = "invalid-email";
        String message = "Este es un mensaje de prueba.";

        // Act
        boolean result = mailerStub.sendEmail(email, message);

        // Assert
        assertFalse(result, "El correo no debería ser enviado con un email inválido.");
    }

    //Test SendEmail con email nulo
    @Test
    void testSendEmailNullEmail() {
        // Arrange
    	String email = null;
        String message = "Este es un mensaje de prueba.";

        // Act
        boolean result = mailerStub.sendEmail(email, message);

        // Assert
        assertFalse(result, "El correo no debería ser enviado con un email nulo.");
    }

    //Test SendEmail con mensake nulo
    @Test
    void testSendEmailNullMessage() {
        // Arrange
        String email = "test@example.com";
        String message = null;

        // Act
        boolean result = mailerStub.sendEmail(email, message);

        // Assert
        assertFalse(result, "El correo no debería ser enviado con un mensaje nulo.");
    }
}