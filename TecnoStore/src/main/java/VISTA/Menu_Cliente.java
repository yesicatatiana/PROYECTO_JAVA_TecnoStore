package VISTA;

import CONTROLADOR.GestionarCliente;
import CONTROLADOR.GestionarClienteImpl;
import MODELO.Cliente;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu_Cliente {

    private final GestionarCliente gc = new GestionarClienteImpl();
    private final Scanner sc = new Scanner(System.in);

    private void registrar() {

        try {
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

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void actualizar() {

        System.out.println("Ingrese el id del cliente:");
        int id = sc.nextInt();
        sc.nextLine();

        Cliente cl = gc.buscar(id);

        if (cl != null) {

            System.out.println("CLIENTE ENCONTRADO:");
            System.out.println(cl);

            try {
                System.out.println("Ingrese nuevo nombre:");
                cl.setNombre(sc.nextLine());

                System.out.println("Ingrese nueva identificacion:");
                cl.setIdentificacion(sc.nextLine());

                System.out.println("Ingrese nuevo correo:");
                cl.setCorreo(sc.nextLine());

                System.out.println("Ingrese nuevo telefono:");
                cl.setTelefono(sc.nextLine());

                gc.actualizar(cl, id);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese id del cliente:");
        int id = sc.nextInt();
        sc.nextLine();
        gc.eliminar(id);
    }

    private void listar() {

        ArrayList<Cliente> clientes = gc.listar();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    private void buscar() {

        System.out.println("Ingrese id del cliente:");
        int id = sc.nextInt();
        sc.nextLine();

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

            while (op < 1 || op > 6) {
                System.out.println("Opcion invalida");
                op = sc.nextInt();
                sc.nextLine();
            }

            switch (op) {
                case 1 -> registrar();
                case 2 -> actualizar();
                case 3 -> eliminar();
                case 4 -> listar();
                case 5 -> buscar();
                case 6 -> System.out.println("Saliendo...");
            }

        } while (op != 6);
    }
}
