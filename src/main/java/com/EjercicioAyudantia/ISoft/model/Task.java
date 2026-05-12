package com.EjercicioAyudantia.ISoft.model;


public class Task {
    private static Long contador = 1L;

    private Long id;
    private String titulo;
    private String prioridad;
    private String fechaLimite;
    private boolean completada;

    public Task(String titulo, String prioridad, String fechaLimite) {
        this.id = contador++;
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.fechaLimite = fechaLimite;
        this.completada = false;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getPrioridad() { return prioridad; }
    public String getFechaLimite() { return fechaLimite; }

    public boolean isCompletada() { return completada; }

    public void setCompletada(boolean completada) { this.completada = completada; }
}