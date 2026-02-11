
package CONTROLADOR;

import MODELO.Celular;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarCelularImpl implements GestionarCelular {

    Conexion c = new Conexion();

    @Override
    public void guardar(Celular cel) {

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO celular(marca, modelo, sistema_operativo, gama, precio, stock) VALUES (?,?,?,?,?,?)");

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

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE celular SET marca=?, modelo=?, sistema_operativo=?, gama=?, precio=?, stock=? WHERE id=?");

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

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement("DELETE FROM celular WHERE id=?");
            ps.setInt(1, id);

            int op = JOptionPane.showConfirmDialog(null,
                    "¿Desea eliminar el celular?", null, JOptionPane.YES_NO_OPTION);

            if (op == 0) {
                ps.executeUpdate();
                System.out.println("ELIMINACION EXITOSA!");
            } else {
                System.out.println("Operacion cancelada");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Celular> listar() {

        ArrayList<Celular> celulares = new ArrayList<>();

        try (Connection con = c.conectar()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM celular");

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

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM celular WHERE id=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

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

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }

        return cel;
    }
}
