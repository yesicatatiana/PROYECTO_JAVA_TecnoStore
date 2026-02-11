
package VISTA;

import CONTROLADOR.GestionarCelular;
import CONTROLADOR.GestionarCelularImpl;
import MODELO.Celular;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu_Celular {

    GestionarCelular gc = new GestionarCelularImpl();
    Scanner sc = new Scanner(System.in);

    private void registrar() {

        try {

            Celular cel = new Celular();

            System.out.println("Ingrese marca:");
            cel.setMarca(sc.nextLine());

            System.out.println("Ingrese modelo:");
            cel.setModelo(sc.nextLine());

            System.out.println("Ingrese sistema operativo:");
            cel.setSistemaOperativo(sc.nextLine());

            System.out.println("Ingrese gama (Baja, Media, Alta):");
            cel.setGama(sc.nextLine());

            System.out.println("Ingrese precio:");
            cel.setPrecio(sc.nextInt());

            System.out.println("Ingrese stock:");
            cel.setStock(sc.nextInt());
            sc.nextLine();

            gc.guardar(cel);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            sc.nextLine();
        }
    }

    private void actualizar() {

        System.out.println("Ingrese id del celular:");
        int id = sc.nextInt();
        sc.nextLine();

        Celular cel = gc.buscar(id);

        if (cel != null) {

            System.out.println(cel);

            try {

                System.out.println("Nueva marca:");
                cel.setMarca(sc.nextLine());

                System.out.println("Nuevo modelo:");
                cel.setModelo(sc.nextLine());

                System.out.println("Nuevo sistema operativo:");
                cel.setSistemaOperativo(sc.nextLine());

                System.out.println("Nueva gama:");
                cel.setGama(sc.nextLine());

                System.out.println("Nuevo precio:");
                cel.setPrecio(sc.nextInt());

                System.out.println("Nuevo stock:");
                cel.setStock(sc.nextInt());
                sc.nextLine();

                gc.actualizar(cel, id);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }

        } else {
            System.out.println("Celular no encontrado");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese id del celular:");
        int id = sc.nextInt();
        gc.eliminar(id);
    }

    private void listar() {

        ArrayList<Celular> lista = gc.listar();

        for (Celular cel : lista) {
            System.out.println(cel);
        }
    }

    private void buscar() {

        System.out.println("Ingrese id del celular:");
        int id = sc.nextInt();

        Celular cel = gc.buscar(id);

        if (cel != null) {
            System.out.println(cel);
        } else {
            System.out.println("Celular no encontrado");
        }
    }

    public void menu() {

        int op;

        do {

            System.out.println("""
                    ******************************
                                CELULAR
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
