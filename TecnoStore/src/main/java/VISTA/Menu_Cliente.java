
package VISTA;

import CONTROLADOR.GestionarCliente;
import CONTROLADOR.GestionarClienteImpl;
import MODELO.Cliente;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu_Cliente {

    GestionarCliente gc = new GestionarClienteImpl();
    Scanner sc = new Scanner(System.in);

    private void registrar() {

        Cliente cl = new Cliente();

        System.out.println("Ingrese nombre:");
        cl.setNombre(sc.nextLine());

        System.out.println("Ingrese identificacion:");
        cl.setIdentificacion(sc.nextLine());

        System.out.println("Ingrese correo:");
        cl.setCorreo(sc.nextLine());

        System.out.println("Ingrese telefono:");
        cl.setTelefono(sc.nextLine());

        gc.guardar(cl);
    }

    private void actualizar() {

        System.out.println("Ingrese el id del cliente:");
        int id = sc.nextInt();
        sc.nextLine();

        Cliente cl = gc.buscar(id);

        if (cl != null) {

            System.out.println("CLIENTE ENCONTRADO");
            System.out.println(cl);

            System.out.println("Ingrese nuevo nombre:");
            cl.setNombre(sc.nextLine());

            System.out.println("Ingrese nueva identificacion:");
            cl.setIdentificacion(sc.nextLine());

            System.out.println("Ingrese nuevo correo:");
            cl.setCorreo(sc.nextLine());

            System.out.println("Ingrese nuevo telefono:");
            cl.setTelefono(sc.nextLine());

            gc.actualizar(cl, id);

        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese id del cliente:");
        int id = sc.nextInt();
        gc.eliminar(id);
    }

    private void listar() {

        ArrayList<Cliente> clientes = gc.listar();

        for (Cliente cl : clientes) {
            System.out.println(cl);
        }
    }

    private void buscar() {

        System.out.println("Ingrese id del cliente:");
        int id = sc.nextInt();

        Cliente cl = gc.buscar(id);

        if (cl != null) {
            System.out.println(cl);
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    public void menu() {

        int op;

        do {
            System.out.println("""
                    ******************************
                                CLIENTE
                    1. Registrar
                    2. Actualizar
                    3. Eliminar
                    4. Listar
                    5. Buscar
                    6. Salir
                    """);

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> registrar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 5 -> buscar();
            }

        } while (op != 6);
    }
}
