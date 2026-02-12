/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VISTA;

import CONTROLADOR.*;
import MODELO.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu_Venta {

    GestionarVenta gv = new GestionarVentaImpl();
    GestionarCliente gc = new GestionarClienteImpl();
    GestionarCelular gcel = new GestionarCelularImpl();
    Scanner sc = new Scanner(System.in);

    public void registrarVenta() {

        Venta venta = new Venta();

        System.out.println("Ingrese ID del cliente:");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Cliente cliente = gc.buscar(idCliente);

        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }

        venta.setCliente(cliente);

        int opcion = 0;

        do {

            System.out.println("Lista de celulares:");
            ArrayList<Celular> lista = gcel.listar();
            lista.forEach(System.out::println);

            System.out.println("Ingrese ID del celular:");
            int idCel = sc.nextInt();

            Celular cel = gcel.buscar(idCel);

            if (cel == null) {
                System.out.println("Celular no encontrado");
                continue;
            }

            System.out.println("Ingrese cantidad:");
            int cantidad = sc.nextInt();

            if (cantidad > cel.getStock()) {
                System.out.println("Stock insuficiente");
                continue;
            }

            venta.agregarDetalle(new Venta(cel, cantidad));

            System.out.println("¿Agregar otro producto? 1=SI / 2=NO");
            opcion = sc.nextInt();

        } while (opcion == 1);

        venta.calcularTotal();

        System.out.println("TOTAL CON IVA: " + venta.getTotal());

        gv.guardar(venta);
    }

    void menu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
