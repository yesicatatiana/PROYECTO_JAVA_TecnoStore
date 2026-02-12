
package CONTROLADOR;

import MODELO.Cliente;
import java.sql.*;
import java.util.ArrayList;

public class GestionarClienteImpl implements GestionarCliente {

    private final Conexion c = new Conexion();

    @Override
    public void guardar(Cliente cl) {

        String sql = "INSERT INTO cliente(nombre, identificacion, correo, telefono) VALUES (?,?,?,?)";

        try (Connection con = c.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());

            ps.executeUpdate();
            System.out.println("CLIENTE REGISTRADO EXITOSAMENTE!");

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Cliente cl, int id) {

        String sql = "UPDATE cliente SET nombre=?, identificacion=?, correo=?, telefono=? WHERE id=?";

        try (Connection con = c.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());
            ps.setInt(5, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("ACTUALIZACION EXITOSA!");
            } else {
                System.out.println("No se encontro el cliente");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {

        String sql = "DELETE FROM cliente WHERE id=?";

        try (Connection con = c.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("ELIMINACION EXITOSA!");
            } else {
                System.out.println("Cliente no encontrado");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Cliente> listar() {

        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection con = c.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return clientes;
    }

    @Override
    public Cliente buscar(int id) {

        Cliente cl = null;
        String sql = "SELECT * FROM cliente WHERE id=?";

        try (Connection con = c.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cl = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }

        return cl;
    }
}
