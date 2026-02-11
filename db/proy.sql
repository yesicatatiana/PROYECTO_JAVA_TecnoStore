
CREATE DATABASE TecnoStore; 
use TecnoStore;


CREATE TABLE celular (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(50),
    marca VARCHAR(50),
    sistema_operativo VARCHAR(50),
    gama ENUM('Baja', 'Media', 'Alta') NOT NULL,
    precio INT NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    identificacion VARCHAR(50) UNIQUE,
    correo VARCHAR(50) UNIQUE,
    telefono VARCHAR(50) NOT NULL
);


CREATE TABLE venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha DATE NOT NULL,
    total double NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);


CREATE TABLE detalle_venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_celular INT NOT NULL,
    cantidad double NOT NULL,
    subtotal double NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES venta(id),
    FOREIGN KEY (id_celular) REFERENCES celular(id)
);









modelo
package MODELO;

public class Empleado {

    private int id;
    private String nombre, apellido, telefono;
    private Area area_id;

    public Empleado(int id, String nombre, String apellido, String telefono, Area area_id) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.area_id = area_id;
    }

    public Empleado() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Area getArea_id() {
        return area_id;
    }

    public void setArea_id(Area area_id) {
        this.area_id = area_id;
    }

    @Override
    public String toString() {
        return """
               *****************************
               Id:          %s
               Nombre:      %s
               Apellidos:   %s
               Telefono:    %s
               %s
               """.formatted(id,nombre, apellido, telefono, area_id);
    }    
}


vista
package VISTA;

import CONTROLADOR.GestionarArea;
import CONTROLADOR.GestionarAreaImpl;
import CONTROLADOR.GestionarEmpleado;
import CONTROLADOR.GestionarEmpleadoImpl;
import MODELO.Area;
import MODELO.Empleado;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu_Empleado {

    GestionarEmpleado ga = new GestionarEmpleadoImpl();
    GestionarArea aa = new GestionarAreaImpl();

    private void registrar() {
        Empleado em = new Empleado();
        System.out.println("Ingrese el nombre:");
        em.setNombre(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el apellido:");
        em.setApellido(new Scanner(System.in).nextLine());
        System.out.println("Ingrese el telefono:");
        em.setTelefono(new Scanner(System.in).nextLine());
        System.out.println("*******AREAS***********");
        ArrayList<Area> areas = aa.listar();
        for (Area a : areas) {
            System.out.println(a);
        }
        System.out.println("Ingrese el id del area");
        Area area = aa.buscar(new Scanner(System.in).nextInt());
        em.setArea_id(area);
        ga.guardar(em);
    }

    private void actualizar() {
        System.out.println("Ingrese el id de la empleado a buscar");
        int id = new Scanner(System.in).nextInt();
        Empleado em = ga.buscar(id);
        if (em != null) {
            System.out.println("EMPLEADO BUSCADO");
            System.out.println(em);
            System.out.println("""
                               Ingrese lo quiere modificar
                               1.   Nombre
                               2.   Apellido
                               3.   Telefono.
                               4.   Area. 
                               """);
            int op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 4) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre");
                    em.setNombre(new Scanner(System.in).nextLine());
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo apellido");
                    em.setApellido(new Scanner(System.in).nextLine());
                    break;
                case 3:
                    System.out.println("Ingrese el nuevo telefono");
                    em.setTelefono(new Scanner(System.in).nextLine());
                    break;
                case 4:
                    ArrayList<Area> areas = aa.listar();
                    for (Area a : areas) {
                        System.out.println(a);
                    }
                    System.out.println("Ingrese el id de la nueva area");
                    int area = new Scanner(System.in).nextInt();
                    Area a = aa.buscar(area);
                    if (a != null) {
                        em.setArea_id(a);
                    } else {
                        System.out.println("AREA NO ENCONTRADA, NO SE PUEDE ACTUALIZAR");
                    }
                    break;
            }
            ga.actualizar(em, id);
        } else {
            System.out.println("No existe dicha area");
        }
    }

    private void buscar() {
        System.out.println("Ingrese el id de el empleado a buscar");
        int id = new Scanner(System.in).nextInt();
        Empleado em = ga.buscar(id);
        if (em != null) {
            System.out.println(em);
        } else {
            System.out.println("No existe dicha area!");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese el id del empleado a eliminar");
        int id = new Scanner(System.in).nextInt();
        ga.eliminar(id);
    }

    private void listar() {
        ArrayList<Empleado> empleados = ga.listar();
        for (Empleado em : empleados) {
            System.out.println(em);
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




controlador