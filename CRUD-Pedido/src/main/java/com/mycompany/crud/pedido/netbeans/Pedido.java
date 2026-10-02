package com.mycompany.crud.pedido.netbeans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Date fecha;
    private int clienteId;
    private List<DetallePedido> detalles = new ArrayList<>();

    public Pedido() {}

    public Pedido(Date fecha, int clienteId) {
        this.fecha = fecha;
        this.clienteId = clienteId;
    }

    public Pedido(int id, Date fecha, int clienteId) {
        this.id = id;
        this.fecha = fecha;
        this.clienteId = clienteId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public List<DetallePedido> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedido> detalles) { this.detalles = detalles; }

    @Override
    public String toString() {
        return "Pedido{id=" + id + ", fecha=" + fecha + ", clienteId=" + clienteId + ", detalles=" + detalles + "}";
    }
}
