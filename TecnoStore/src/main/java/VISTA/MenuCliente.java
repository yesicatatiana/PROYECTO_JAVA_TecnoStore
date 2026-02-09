/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VISTA;

import CONTROLAOR.GestionarCliente;
import CONTROLAOR.GestionarClienteImpl;
import MODELO.Cliente;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author camper
 */
public class MenuCliente {

    GestionarCliente gc = new GestionarClienteImpl();
   
    private void registrar() {
        Cliente cl = new Cliente();
        System.out.println("Ingrese el nombre:");
        cl.setNombre(new Scanner(System.in).nextLine());
        System.out.println("Ingrese su identificacion:");
        cl.setIdentificacion(new Scanner(System.in).nextLine());
        System.out.println("Ingrese su correo:");
        cl.setCorreo(new Scanner(System.in).nextLine());
        System.out.println("Ingrese su telefono:");
        cl.setTelefono(new Scanner(System.in).nextLine());
        gc.guardar(cl);
    }
    

  private void actualizar() {
        System.out.println("Ingrese el id de la empleado a buscar");
        int id = new Scanner(System.in).nextInt();
        Cliente cl = gc.buscar(id);
        if (cl != null) {
            System.out.println("CLIENTE BUSCADO");
            System.out.println(cl);
            System.out.println("""
                               Ingrese lo quiere modificar
                               1.   Nombre.
                               2.   Identificacion.
                               3.   Correo.
                               4.   Telefono. 
                               """);
            int op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 4) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre");
                    cl.setNombre(new Scanner(System.in).nextLine());
                    break;
                case 2:
                    System.out.println("Ingrese la nueva identificacion");
                    cl.setIdentificacion(new Scanner(System.in).nextLine());
                    break;
                case 3:
                    System.out.println("Ingrese el nuevo correo");
                    cl.setCorreo(new Scanner(System.in).nextLine());
                    break;
                case 4:
                    System.out.println("Ingrese el nuevo telefono");
                    cl.setCorreo(new Scanner(System.in).nextLine());
                    break;
            }
            gc.actualizar(cl, id);
    }
        

    private void buscar() {
        System.out.println("Ingrese el id de el cliente a buscar");
        int id = new Scanner(System.in).nextInt();
        Cliente cl = gc.buscar(id);
        if (cl != null) {
            System.out.println(cl);
        } else {
            System.out.println("No existe dicha area!");
        }
    }


    private void eliminar() {
        System.out.println("Ingrese el id del empleado a eliminar");
        int id = new Scanner(System.in).nextInt();
        gc.eliminar(id);
    }
    
    private void listar() {
        ArrayList<cliente> clientes = gc.listar();
        for (cliente cl : clientes) {
            System.out.println(cl);
        }
    }
    

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                           ******************************
                                        Empleado
                           1.   Registrar
                           2.   Actualizar
                           3.   Eliminar.
                           4.   Listar.
                           5.   Buscar.
                           6.   Regresar.
                           """);
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 6) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    registrar();
                    break;
                case 2:
                    actualizar();
                    break;
                case 3:
                    eliminar();
                    break;
                case 4:
                    listar();
                    break;
                case 5:
                    buscar();
                    break;
            }
        } while (op != 6);
    }
}
