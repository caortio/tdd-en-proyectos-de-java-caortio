package com.tt1.test;

import java.io.Serializable;
import java.util.Date;

public class ToDo implements Serializable {
    private String nombre;
    private String descripcion;
    private Date fechaLimite;
    private boolean completado;

    //Constructor vacío
    public ToDo() {}
    
    // Constructor
    public ToDo (String nombre, String descripcion, Date fechaLimite, boolean completado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.completado = completado;
    }

	// Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public boolean isCompletado() {
        return completado;
    }

	public void setCompletado(boolean b) {
		this.completado = b;	
	}

}