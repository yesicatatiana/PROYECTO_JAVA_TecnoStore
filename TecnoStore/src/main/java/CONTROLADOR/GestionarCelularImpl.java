

package CONTROLADOR;

import MODELO.Celular;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionarCelularImpl implements GestionarCelular {

    Conexion c = new Conexion();

    @Override
    public void guardar(Celular cel) {

        String sql = "INSERT INTO celular(marca, modelo, sistema_operativo, gama, precio, stock) VALUES (?,?,?,?,?,?)";

        try (Connection con = c.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cel.getMarca());
            ps.setString(2, cel.getModelo());
            ps.setString(3, cel.getSistemaOperativo());
            ps.setString(4, cel.getGama());
            ps.setInt(5, cel.getPrecio());
            ps.setInt(6, cel.getStock());

            ps.executeUpdate();
            System.out.println("CELULAR REGISTRADO EXITOSAMENTE!");

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Celular cel, int id) {

        String sql = "UPDATE celular SET marca=?, modelo=?, sistema_operativo=?, gama=?, precio=?, stock=? WHERE id=?";

        try (Connection con = c.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cel.getMarca());
            ps.setString(2, cel.getModelo());
            ps.setString(3, cel.getSistemaOperativo());
            ps.setString(4, cel.getGama());
            ps.setInt(5, cel.getPrecio());
            ps.setInt(6, cel.getStock());
            ps.setInt(7, id);

            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {

        String sql = "DELETE FROM celular WHERE id=?";

        try (Connection con = c.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("ELIMINACION EXITOSA!");

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Celular> listar() {

        ArrayList<Celular> celulares = new ArrayList<>();

        String sql = "SELECT * FROM celular";

        try (Connection con = c.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                celulares.add(new Celular(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("sistema_operativo"),
                        rs.getString("gama"),
                        rs.getInt("precio"),
                        rs.getInt("stock")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return celulares;
    }

    @Override
    public Celular buscar(int id) {

        Celular cel = null;
        String sql = "SELECT * FROM celular WHERE id=?";

        try (Connection con = c.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    cel = new Celular(
                            rs.getInt("id"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getString("sistema_operativo"),
                            rs.getString("gama"),
                            rs.getInt("precio"),
                            rs.getInt("stock")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }

        return cel;
    }

    @Override
    public void mostrarStockBajo() {

        System.out.println("CELULARES CON STOCK BAJO (<5)");

        listar().stream()
                .filter(cel -> cel.getStock() < 5)
                .forEach(System.out::println);
    }
}
