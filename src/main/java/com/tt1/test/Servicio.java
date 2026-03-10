package com.tt1.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Servicio principal para la gestión de tareas (ToDo) en el sistema.
 * 
 * Esta clase permite:
 * <ul>
 *   <li>Crear nuevas tareas.</li>
 *   <li>Agregar emails a la agenda de alertas.</li>
 *   <li>Marcar tareas como completadas.</li>
 *   <li>Consultar tareas incompletas.</li>
 *   <li>Revisar tareas vencidas y enviar alertas por correo.</li>
 * </ul>
 * 
 * Depende de un {@link Repositorio} para almacenar tareas y emails, y de un {@link MailerStub} 
 * para enviar correos electrónicos de prueba.
 * 
 * @author caortio
 * @version 1.0
 */
public class Servicio {

    /** Repositorio para almacenar tareas y emails */
    private Repositorio repositorio;

    /** Stub para enviar correos electrónicos de prueba */
    private MailerStub mailerStub;
    
    /**
     * Constructor del servicio.
     * 
     * @param repositorio Objeto Repositorio donde se almacenan las tareas y emails.
     * @param mailerStub Objeto MailerStub utilizado para enviar notificaciones de correo.
     */
    public Servicio(Repositorio repositorio, MailerStub mailerStub) {
        this.repositorio = repositorio;
        this.mailerStub = mailerStub;
    }

    /**
     * Crea una nueva tarea y la almacena en el repositorio.
     * 
     * @param nombre Nombre de la tarea a crear.
     * @param fechaLimite Fecha límite de la tarea en formato "yyyy-MM-dd".
     */
    public void addTask(String nombre, String fechaLimite) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = sdf.parse(fechaLimite);  // Convertimos el string a Date
            ToDo task = new ToDo(nombre, "Descripción de la tarea", fecha, false);
            repositorio.storeTask(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Agrega un email a la agenda para recibir alertas de tareas vencidas.
     * 
     * @param email Dirección de correo a agregar.
     */
    public void addEmail(String email) {
        repositorio.storeEmail(email);
    }

    /**
     * Marca una tarea como completada en el repositorio.
     * 
     * @param index índice de la tarea en la lista del repositorio.
     * @return true si la tarea fue marcada como completada, false si no se encontró la tarea.
     */
    public boolean markTaskAsCompleted(int index) {
        return repositorio.markTaskCompleted(index);
    }

    /**
     * Devuelve todas las tareas que aún no han sido completadas.
     * 
     * @return Lista de tareas incompletas. Puede estar vacía si todas las tareas están completas.
     */
    public List<ToDo> getIncompleteTasks() {
        List<ToDo> incompleteTasks = repositorio.getIncompleteTasks();
        if (incompleteTasks.isEmpty()) {
            System.out.println("No hay tareas incompletas.");
        } else {
            for (ToDo task : incompleteTasks) {
                System.out.println("Tarea incompleta: " + task.getNombre());
            }
        }
        return incompleteTasks;
    }

    /**
     * Revisa las tareas incompletas y envía alertas por correo a los emails de la agenda 
     * si alguna tarea ha vencido su fecha límite.
     */
    public void checkForOverdueTasks() {
        List<ToDo> tasks = repositorio.getIncompleteTasks();
        Date currentDate = new Date();
        for (ToDo task : tasks) {
            if (task.getFechaLimite().before(currentDate)) {
                // Enviar correo de alerta a todas las direcciones de la agenda
                for (String email : repositorio.getEmailAgenda()) {
                    mailerStub.sendEmail(email, "Alerta: La tarea '" + task.getNombre() + "' ha vencido.");
                }
            }
        }
    }
}