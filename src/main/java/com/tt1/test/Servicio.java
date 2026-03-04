package com.tt1.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Servicio {

    private Repositorio repositorio;
    private MailerStub mailerStub;

    public Servicio(Repositorio repositorio, MailerStub mailerStub) {
        this.repositorio = repositorio;
        this.mailerStub = mailerStub;
    }

    // Crear un ToDo
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

    // Agregar un email a la agenda
    public void addEmail(String email) {
        repositorio.storeEmail(email);
    }

    // Marcar una tarea como completada
    public boolean markTaskAsCompleted(int index) {
        return repositorio.markTaskCompleted(index);
    }

    // Consultar todas las tareas incompletas
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

    // Revisar tareas vencidas y enviar alertas por correo
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