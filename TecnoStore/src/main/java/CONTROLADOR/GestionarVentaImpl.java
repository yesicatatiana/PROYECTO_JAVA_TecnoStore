package CONTROLADOR;

import MODELO.*;
import java.sql.*;
import java.util.ArrayList;

public class GestionarVentaImpl implements GestionarVenta {

    Conexion c = new Conexion();
    GestionarCelular gc = new GestionarCelularImpl();

    @Override
    public void guardar(Venta v) {

        try (Connection con = c.conectar()) {

            con.setAutoCommit(false);

            // 1️⃣ Insertar venta
            PreparedStatement psVenta = con.prepareStatement(
                    "INSERT INTO venta(id_cliente, fecha, total) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            psVenta.setInt(1, v.getCliente().getId());
            psVenta.setDate(2, Date.valueOf(v.getFecha()));
            psVenta.setDouble(3, v.getTotal());
            psVenta.executeUpdate();

            ResultSet rs = psVenta.getGeneratedKeys();
            rs.next();
            int idVenta = rs.getInt(1);

            // 2️⃣ Insertar detalles
            for (DetalleVenta d : v.getDetalles()) {

                PreparedStatement psDetalle = con.prepareStatement(
                        "INSERT INTO detalle_venta(id_venta, id_celular, cantidad, subtotal) VALUES (?,?,?,?)");

                psDetalle.setInt(1, idVenta);
                psDetalle.setInt(2, d.getCelular().getId());
                psDetalle.setDouble(3, d.getCantidad());
                psDetalle.setDouble(4, d.getSubtotal());
                psDetalle.executeUpdate();

                // 3️⃣ Actualizar stock
                Celular cel = d.getCelular();
                int nuevoStock = cel.getStock() - (int) d.getCantidad();

                PreparedStatement psStock = con.prepareStatement(
                        "UPDATE celular SET stock=? WHERE id=?");

                psStock.setInt(1, nuevoStock);
                psStock.setInt(2, cel.getId());
                psStock.executeUpdate();
            }

            con.commit();
            System.out.println("VENTA REGISTRADA EXITOSAMENTE!");

        } catch (SQLException e) {
            System.out.println("Error en la venta: " + e.getMessage());
        }
    }
}
