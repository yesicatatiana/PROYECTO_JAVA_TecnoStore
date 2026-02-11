package CONTROLAOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public Connection conectar() {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql:localhost:3306/TecnoStore", "campus2023", "campus2023");
            System.out.println("CONEXION ESTABLECIDA CORRECTA");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

}
