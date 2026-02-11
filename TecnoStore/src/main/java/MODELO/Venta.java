/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.util.List;

/**
 *
 * @author camper
 */
public class Venta {

    private Cliente cliente;
    private List<Celular> celulares;
    private double total;

    public Venta(Cliente cliente, List<Celular> celulares) {
        this.cliente = cliente;
        this.celulares = celulares;
        calcularTotal();
    }

    private void calcularTotal() {
        total = 0;
        for (Celular c : celulares) {
            total += c.getPrecio();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Celular> getCelulares() {
        return celulares;
    }

    public double getTotal() {
        return total;
    }
}

}

