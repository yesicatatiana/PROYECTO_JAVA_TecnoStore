package MODELO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {

    private int id;
    private Cliente cliente;
    private LocalDate fecha;
    private double total;
    private ArrayList<DetalleVenta> detalles;

    public Venta() {
        detalles = new ArrayList<>();
        fecha = LocalDate.now();
    }

    public Venta(int id, Cliente cliente, LocalDate fecha, double total) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
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

    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
    }

    public void calcularTotal() {
        double subtotal = detalles.stream()
                .mapToDouble(d -> d.getSubtotal())
                .sum();

        total = subtotal + (subtotal * 0.19); // IVA 19%
    }

    @Override
    public String toString() {
        return """
               *****************************
               Venta ID:      %s
               Cliente:       %s
               Fecha:         %s
               Total (IVA):   %.2f
               """.formatted(id, cliente.getNombre(), fecha, total);
    }
}
