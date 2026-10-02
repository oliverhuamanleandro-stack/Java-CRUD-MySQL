package com.mycompany.crud.empleado.netbeans;

public class Empleado {
    private int id;
    private String nombre;
    private String cargo;
    private double salarioBase;
    private int horasExtra;

    public Empleado() {}

    public Empleado(String nombre, String cargo, double salarioBase, int horasExtra) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.horasExtra = horasExtra;
    }

    public Empleado(int id, String nombre, String cargo, double salarioBase, int horasExtra) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.salarioBase = salarioBase;
        this.horasExtra = horasExtra;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) { this.salarioBase = salarioBase; }

    public int getHorasExtra() { return horasExtra; }
    public void setHorasExtra(int horasExtra) { this.horasExtra = horasExtra; }

    // Método para calcular el salario total
    public double getSalarioTotal() {
        return salarioBase + (horasExtra * 10);
    }

    @Override
    public String toString() {
        return "Empleado { " +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salarioBase=" + salarioBase +
                ", horasExtra=" + horasExtra +
                ", salarioTotal=" + getSalarioTotal() +
                " }";
    }
}
