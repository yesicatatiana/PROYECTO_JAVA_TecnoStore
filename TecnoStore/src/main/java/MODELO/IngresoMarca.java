/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author camper
 */
public class IngresoMarca {

    private String marca;
    private double totalIngreso;

    public IngresoMarca(String marca, double totalIngreso) {
        this.marca = marca;
        this.totalIngreso = totalIngreso;
    }

    public String getMarca() {
        return marca;
    }

    public double getTotalIngreso() {
        return totalIngreso;
    }
}

}
