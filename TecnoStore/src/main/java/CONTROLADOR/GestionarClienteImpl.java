

package CONTROLADOR;

import MODELO.Cliente;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GestionarClienteImpl implements GestionarCliente {

    Conexion c = new Conexion();

    @Override
    public void guardar(Cliente cl) {
        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO cliente(nombre, identificacion, correo, telefono) VALUES (?,?,?,?)");

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
        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE cliente SET nombre=?, identificacion=?, correo=?, telefono=? WHERE id=?");

            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());
            ps.setInt(5, id);

            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement("DELETE FROM cliente WHERE id=?");
            ps.setInt(1, id);

            int op = JOptionPane.showConfirmDialog(null,
                    "¿Desea eliminar el cliente?", null, JOptionPane.YES_NO_OPTION);

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
    public ArrayList<Cliente> listar() {

        ArrayList<Cliente> clientes = new ArrayList<>();

        try (Connection con = c.conectar()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cliente");

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

        try (Connection con = c.conectar()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM cliente WHERE id=?");

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
