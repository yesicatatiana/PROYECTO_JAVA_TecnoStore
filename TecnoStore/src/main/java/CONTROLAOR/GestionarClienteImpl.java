

package CONTROLAOR;


import MODELO.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;





public class GestionarClienteImpl implements GestionarCliente {

    Conexion c = new Conexion();

    @Override
    public void guardar(Cliente cl) {
        try (Connection con = c.conectar()) {
            //La usamos cuando queremos hacer una inserción o modificacion a la base de datos.
            PreparedStatement ps = con.prepareStatement("insert into clientes(nombre, identificacion, correo, celular) values (?,?,?,?)");
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getCelular());
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Cliente cl, int id) {
        try (Connection con = c.conectar()) {
            //La usamos cuando queremos hacer una inserción o modificacion a la base de datos.
            PreparedStatement ps = con.prepareStatement("update empleado set nombre=?, identificacion=?, correo=?, celular=? where id=?");
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getCelular());
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("ACTUALIZACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    
    
    package CONTROLADOR;

import MODELO.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionarClienteImpl implements GestionarCliente {

    @Override
    public void guardar(Cliente cliente) {

        String sql = "INSERT INTO cliente (identificacion, nombre, correo, celular) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getIdentificacion());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getCelular());

            ps.executeUpdate();
            System.out.println("Cliente guardado en la BD");

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> listar() {

        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdentificacion(rs.getString("identificacion"));
                c.setNombre(rs.getString("nombre"));
                c.setCorreo(rs.getString("correo"));
                c.setCelular(rs.getString("celular"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public void actualizar(Cliente cliente) {

        String sql = "UPDATE cliente SET nombre=?, correo=?, celular=? WHERE identificacion=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCorreo());
            ps.setString(3, cliente.getCelular());
            ps.setString(4, cliente.getIdentificacion());

            ps.executeUpdate();
            System.out.println("Cliente actualizado en la BD");

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String identificacion) {

        String sql = "DELETE FROM cliente WHERE identificacion=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, identificacion);
            ps.executeUpdate();
            System.out.println("Cliente eliminado de la BD");

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void eliminar(int id) {
        try (Connection con = c.conectar()) {
            //La usamos cuando queremos hacer una inserción o modificacion a la base de datos.
            PreparedStatement ps = con.prepareStatement("delete from cliente where id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el cliente?", null, JOptionPane.YES_NO_OPTION);
            if (op == 0) {
                ps.executeUpdate();
                System.out.println("ELIMINACION EXITOSA!");
            } else {
                System.out.println("Operacion cancelada");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public Cliente buscar(int id) {
        Cliente cl = new Cliente();
        try (Connection con = c.conectar()) {
            //creo el statement para que quede listo cuando quiera escribir en sql
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente where id=" + id);
            while (rs.next()) {
                cl.setId(rs.getInt(1));
                cl.setNombre(rs.getString(2));
                cl.setIdentificacion(rs.getString(3));
                cl.setCorreo(rs.getString(4));
                cl.setCelular(rs.getString(5));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cl;
    }

    @Override
    public ArrayList<Cliente> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
