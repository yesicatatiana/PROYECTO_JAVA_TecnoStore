package CONTROLAOR;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public connection conectar() {
        connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql:localhost:3306/TecnoStore", "root", "123");
            System.out.println("CONEXION ESTABLECIDA CORRECTA");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

}
