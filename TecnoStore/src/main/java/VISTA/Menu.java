/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VISTA;

import java.util.Scanner;

public class Menu {

    public void Menu_Principal() {
        int op = 0;
        do {
            System.out.println("""
                           ******************************
                           1.   Gestionar Cliente.
                           2.   Gestionar Celular.
                           3.   Gestionar venta.
                           4.   salir.
                           """);
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 4) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    Menu_Cliente a = new Menu_Cliente();
                    a.menu();
                    break;
                case 2:
                    Menu_Celular e=new Menu_Celular();
                    e.menu();
                    break;
                case 3:
                    Menu_Venta i = new Menu_Venta();
                    i.menu();
                    break;
                case 4:
                    System.out.println("Gracias por usar nuestro sistema!");
                    break;
            }
        } while (op != 4);
    }
}
