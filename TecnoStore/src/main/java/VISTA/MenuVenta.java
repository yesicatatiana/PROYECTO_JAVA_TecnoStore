/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VISTA;

import CONTROLAOR.GestionarCelular;
import CONTROLAOR.GestionarCelularImpl;
import CONTROLAOR.GestionarCliente;
import CONTROLAOR.GestionarClienteImpl;
import CONTROLAOR.GestionarVenta;
import CONTROLAOR.GestionarVentaImpl;
import MODELO.Celular;
import MODELO.Cliente;
import MODELO.Venta;
import java.util.ArrayList;
import java.util.List;


public class MenuVenta {

    private GestionarCliente gc = new GestionarClienteImpl();
    private GestionarCelular gcel = new GestionarCelularImpl();
    private GestionarVenta gv = new GestionarVentaImpl();

    private Scanner sc = new Scanner(System.in);

    public void registrarVenta() {

        // 🔹 1. Mostrar clientes
        List<Cliente> clientes = gc.listar();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
            return;
        }

        System.out.println("Seleccione un cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).getNombre());
        }

        int opcionCliente = sc.nextInt();
        sc.nextLine();

        Cliente clienteSeleccionado = clientes.get(opcionCliente - 1);

        // 🔹 2. Mostrar celulares
        List<Celular> celularesDisponibles = gcel.listar();
        List<Celular> seleccionados = new ArrayList<>();

        while (true) {

            System.out.println("\nCelulares disponibles:");
            for (int i = 0; i < celularesDisponibles.size(); i++) {
                Celular c = celularesDisponibles.get(i);
                System.out.println((i + 1) + ". " + c.getMarca() + " - $" + c.getPrecio());
            }

            System.out.println("Seleccione celular (0 para terminar):");
            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 0) break;

            seleccionados.add(celularesDisponibles.get(opcion - 1));
        }

        if (seleccionados.isEmpty()) {
            System.out.println("No seleccionó celulares.");
            return;
        }

        // 🔹 3. Crear venta
        Venta venta = new Venta(clienteSeleccionado, seleccionados);
        gv.guardar(venta);

        System.out.println("Venta registrada correctamente");
        System.out.println("Total: $" + venta.getTotal());
    }
}
