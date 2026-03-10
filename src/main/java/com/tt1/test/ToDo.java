package com.tt1.test;

import java.io.Serializable;
import java.util.Date;

/**
 * Representa una tarea en el sistema de gestión de tareas.
 * 
 * Cada instancia de ToDo almacena información sobre una tarea específica,
 * incluyendo su nombre, descripción, fecha límite y si ha sido completada.
 * Esta clase es utilizada por el servicio principal para gestionar las tareas.
 * 
 * @author caortio
 * @version 1.0
 */

public class ToDo implements Serializable {

    /** Nombre de la tarea */
    private String nombre;

    /** Descripción de la tarea */
    private String descripcion;

    /** Fecha límite para completar la tarea */
    private Date fechaLimite;

    /** Indica si la tarea ha sido completada */
    private boolean completado;

    /**
     * Constructor vacío.
     * Crea un objeto ToDo sin inicializar sus campos.
     */
    public ToDo() {}
    
    /**
     * Constructor con parámetros.
     * @param nombre Nombre de la tarea, usado para identificarla.
     * @param descripcion Breve descripción o detalle de la tarea.
     * @param fechaLimite Fecha límite en que la tarea debe completarse.
     * @param completado Estado inicial de la tarea: true si ya está completada, false si no.
     */
    public ToDo (String nombre, String descripcion, Date fechaLimite, boolean completado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.completado = completado;
    }

	/**
     * Devuelve el nombre de la tarea.
     * 
     * @return Nombre de la tarea.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre de la tarea.
     * 
     * @param nombre Nuevo nombre de la tarea.
     */ 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	/** Devuelve la descripción de la tarea 
     * 
     * @return Descripción de la tarea.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /** Establece la descripción de la tarea.
     * 
     * @param descripcion Nueva descripción de la tarea.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /** Devuelve la fecha límite de la tarea 
     * 
     * @return Fecha límite de la tarea.
     */
    public Date getFechaLimite() {
        return fechaLimite;
    }

    /** Establece la fecha límite de la tarea
     * 
     * @param fechaLimite Nueva fecha límite de la tarea.
     */
    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    /**
     * Indica si la tarea ha sido completada.
     * 
     * @return true si la tarea está completada, false si no.
     */
    public boolean isCompletado() {
        return completado;
    }
    /**
     * Cambia el estado de completado de la tarea.
     * 
     * @param completado true para marcar la tarea como completada, false para marcarla como pendiente.
     */
	public void setCompletado(boolean completado) {
		this.completado = b;	
	}

}