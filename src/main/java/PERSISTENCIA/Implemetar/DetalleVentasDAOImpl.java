package PERSISTENCIA.Implemetar;

import MODELO.Celulares;
import MODELO.Clientes;
import MODELO.DetalleVentas;
import MODELO.Marcas;
import MODELO.Tipogama;
import MODELO.Ventas;
import PERSISTENCIA.Conexion;
import PERSISTENCIA.DetalleVentasDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public class DetalleVentasDAOImpl implements DetalleVentasDAO {

    Conexion cone = new Conexion();

    @Override
    public void guardar(DetalleVentas dv) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into detalle_ventas(cantidad,subtotal,id_celular,id_venta) VALUES (?,?,?,?)");

            ps.setInt(1, dv.getCantidad());
            ps.setDouble(2, dv.getSubtotal());
            ps.setInt(3, dv.getId_celular().getId());
            ps.setInt(4, dv.getId_venta().getId());
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(DetalleVentas dv, int id) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("update detalle_ventas   cantidad = ?, subtotal = ? id_celular = ?, set id_venta = ?, where id = ?");

            ps.setInt(3, dv.getCantidad());
            ps.setDouble(4, dv.getSubtotal());
            ps.setInt(2, dv.getId_celular().getId());
            ps.setInt(1, dv.getId_venta().getId());

            ps.setInt(5, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
public void eliminar(int id) {
    try (Connection con = cone.conectar()) {

        PreparedStatement ps = con.prepareStatement(
                "delete from detalle_ventas where id_venta=?");

        ps.setInt(1, id);

        int filas = ps.executeUpdate();

        if (filas > 0) {
            System.out.println("DETALLE DE VENTA ELIMINADO CORRECTAMENTE");
        } else {
            System.out.println("No existe detalle con esa venta");
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}


    @Override
    public double calcularSubtotal(int cantidad, double precio) {
        double subtotal = 0;
        subtotal = cantidad * precio;
        return subtotal;
    }

    @Override
    public DetalleVentas buscar(int id) {
        DetalleVentas dv = null;
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement(
                    "select id,  cantidad, subtotal, id_celular, id_venta from detalle_ventas where id=?"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                dv = new DetalleVentas();
                dv.setId(rs.getInt(1));

                dv.setCantidad(rs.getInt(2));
                dv.setSubtotal(rs.getDouble(3));

                Celulares cel = new Celulares();
                cel.setId(rs.getInt(4));
                dv.setId_celular(cel);

                Ventas v = new Ventas();
                v.setId(rs.getInt(5));
                dv.setId_venta(v);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dv;
    }

    @Override
    public ArrayList<DetalleVentas> listar() {
        ArrayList<DetalleVentas> ventas = new ArrayList<>();
        try (Connection con = cone.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT dv.id, cl.nombre, v.id, v.fecha_venta, v.subtotal_Iva, "
                    + "ce.id AS id_celular, ce.modelo, ce.sistema_operativo, ce.gama, ce.precio, ce.stock, "
                    + "m.id AS id_marca, m.nombre, dv.cantidad, dv.subtotal "
                    + "FROM detalle_ventas dv "
                    + "INNER JOIN ventas v ON dv.id_venta = v.id "
                    + "INNER JOIN clientes cl ON v.id_cliente = cl.id "
                    + "INNER JOIN celulares ce ON dv.id_celular = ce.id "
                    + "INNER JOIN marcas m ON ce.id_marca = m.id "
                    + "ORDER BY v.fecha_venta DESC;"
            );

            while (rs.next()) {

                Clientes cl = new Clientes();
                cl.setNombre(rs.getString(2));

                Ventas v = new Ventas();
                v.setId(rs.getInt(3));
                v.setFecha_venta(rs.getString(4));
                v.setSubtotal_Iva(rs.getDouble(5));
                v.setId_cliente(cl);

                Marcas mc = new Marcas();
                mc.setId(rs.getInt(12));
                mc.setNombre(rs.getString(13));

                Celulares cel = new Celulares();
                cel.setId(rs.getInt(6));
                cel.setModelo(rs.getString(7));
                cel.setSistema_operativo(rs.getString(8));

                String ga = rs.getString(9);
                if (ga != null) {
                    cel.setGama(Tipogama.valueOf(ga.trim().toUpperCase()));
                }

                cel.setPrecio(rs.getDouble(10));
                cel.setStock(rs.getInt(11));
                cel.setId_marca(mc);

                DetalleVentas dv = new DetalleVentas();
                dv.setId(rs.getInt(1));
                dv.setCantidad(rs.getInt(14));
                dv.setSubtotal(rs.getDouble(15));
                dv.setId_venta(v);
                dv.setId_celular(cel);

                ventas.add(dv);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return ventas;
    }
}
