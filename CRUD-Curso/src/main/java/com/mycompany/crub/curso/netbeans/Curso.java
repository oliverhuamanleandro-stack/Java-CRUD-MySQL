package com.mycompany.crud.curso.netbeans;

public class Curso {
    private int id;
    private String nombre;
    private int creditos;
    private String docente;

    public Curso() {}

    public Curso(String nombre, int creditos, String docente) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.docente = docente;
    }

    public Curso(int id, String nombre, int creditos, String docente) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.docente = docente;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }

    public String getDocente() { return docente; }
    public void setDocente(String docente) { this.docente = docente; }

    @Override
    public String toString() {
        return "Curso{id=" + id + ", nombre='" + nombre + "', creditos=" + creditos + ", docente='" + docente + "'}";
    }
}
