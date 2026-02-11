package MODELO;

public class VentaItem {

    private int id;
    private Celular celular;
    private double cantidad;
    private double subtotal;

    public VentaItem(Celular celular, double cantidad) {
        this.celular = celular;
        this.cantidad = cantidad;
        this.subtotal = celular.getPrecio() * cantidad;
    }

    public Celular getCelular() {
        return celular;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }
}
