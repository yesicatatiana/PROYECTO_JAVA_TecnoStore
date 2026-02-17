
package CONTROLADOR;

import MODELO.Celular;
import MODELO.Venta;
import MODELO.VentaItem;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class GestionarVentaImpl implements GestionarVenta {

    Conexion c = new Conexion();

    @Override
    public void guardar(Venta v) {

        Connection con = null;

        try {
            con = c.conectar();
            con.setAutoCommit(false);

            String sqlVenta = "INSERT INTO venta(id_cliente, fecha, total) VALUES (?,?,?)";

            try (PreparedStatement psVenta = con.prepareStatement(
                    sqlVenta, Statement.RETURN_GENERATED_KEYS)) {

                psVenta.setInt(1, v.getCliente().getId());
                psVenta.setDate(2, Date.valueOf(v.getFecha()));
                psVenta.setDouble(3, v.getTotal());
                psVenta.executeUpdate();

                ResultSet rs = psVenta.getGeneratedKeys();

                if (!rs.next()) {
                    throw new SQLException("No se pudo obtener ID venta");
                }

                int idVenta = rs.getInt(1);

                
                for (VentaItem item : v.getDetalles()) {

                    Celular cel = item.getCelular();

                    if (cel.getStock() < item.getCantidad()) {
                        throw new SQLException("Stock insuficiente ID: " + cel.getId());
                    }

                    String sqlDetalle = "INSERT INTO detalle_venta(id_venta,id_celular,cantidad,subtotal) VALUES (?,?,?,?)";

                    try (PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {

                        psDetalle.setInt(1, idVenta);
                        psDetalle.setInt(2, cel.getId());
                        psDetalle.setInt(3, item.getCantidad());
                        psDetalle.setDouble(4, item.getSubtotal());
                        psDetalle.executeUpdate();
                    }

                    // Actualizar stock
                    String sqlStock = "UPDATE celular SET stock = stock - ? WHERE id=?";

                    try (PreparedStatement psStock = con.prepareStatement(sqlStock)) {
                        psStock.setInt(1, item.getCantidad());
                        psStock.setInt(2, cel.getId());
                        psStock.executeUpdate();
                    }
                }
            }

            con.commit();
            System.out.println("VENTA REGISTRADA CORRECTAMENTE");

        } catch (SQLException e) {

            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error rollback: " + ex.getMessage());
            }

            System.out.println("Error en venta: " + e.getMessage());

        } finally {

            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error cerrando conexión");
            }
        }
    }

    @Override
    public void ventasPorMes() {

        String sql = """
            SELECT DATE_FORMAT(fecha,'%Y-%m') AS mes,
                   SUM(total) AS total_mensual
            FROM venta
            GROUP BY mes
            ORDER BY mes
        """;

        try (Connection con = c.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("VENTAS POR MES");
            System.out.println("----------------");

            while (rs.next()) {
                System.out.println(
                        "Mes: " + rs.getString("mes") +
                        " | Total: $" + rs.getDouble("total_mensual")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error ventas por mes: " + e.getMessage());
        }
    }
}


    @Override
    public List<IngresoMarca> obtenerIngresosPorMarcaMesActual() throws SQLException {

        String sql = """
            SELECT c.marca, SUM(v.precio_venta) AS total
            FROM venta v
            JOIN celular c ON v.id_celular = c.id
            WHERE MONTH(v.fecha_venta) = MONTH(CURRENT_DATE())
              AND YEAR(v.fecha_venta) = YEAR(CURRENT_DATE())
            GROUP BY c.marca
        """;

        List<IngresoMarca> lista = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new IngresoMarca(
                        rs.getString("marca"),
                        rs.getDouble("total")
                ));
            }
        }

        if (lista.isEmpty()) {
            throw new IllegalStateException("No existen ventas registradas este mes.");
        }

        return lista;
    }
