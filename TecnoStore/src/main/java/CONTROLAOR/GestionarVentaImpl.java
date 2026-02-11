/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLAOR;


import modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class GestionarVentaImpl implements GestionarVenta {

    private List<Venta> ventas = new ArrayList<>();

    @Override
    public void guardar(Venta venta) {
        ventas.add(venta);
    }

    public List<Venta> listar() {
        return ventas;
    }
}

}
