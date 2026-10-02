package com.crudmysql.crud.maven.netbeans;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private int edad;

    public Usuario() {}

    public Usuario(String nombre, String correo, int edad) {
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

    public Usuario(int id, String nombre, String correo, int edad) {
        this(nombre, correo, edad);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nombre='" + nombre + '\'' +
               ", correo='" + correo + '\'' + ", edad=" + edad + '}';
    }
}
