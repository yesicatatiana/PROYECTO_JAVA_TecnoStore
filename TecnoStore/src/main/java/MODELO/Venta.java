/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {

    private int id;
    private Cliente cliente;
    private LocalDate fecha;
    private double total;
    private ArrayList<VentaItem> detalles;

    public Venta() {
        this.detalles = new ArrayList<>();
        this.fecha = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<VentaItem> getDetalles() {
        return detalles;
    }

    public void agregarDetalle(VentaItem detalle) {
        detalles.add(detalle);
    }

    public void calcularTotal() {
        double subtotal = detalles.stream()
                .mapToDouble(VentaItem::getSubtotal)
                .sum();

        total = subtotal * 1.19; // IVA 19%
    }

    @Override
    public String toString() {
        return """
               *****************************
               Cliente:       %s
               Fecha:         %s
               Total (IVA):   %.2f
               """.formatted(cliente.getNombre(), fecha, total);
    }
}
