package PERSISTENCIA.Implemetar;

import MODELO.Clientes;
import MODELO.Ventas;
import PERSISTENCIA.ClientesDAO;
import PERSISTENCIA.Conexion;
import PERSISTENCIA.VentasDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ANGEL
 */
public class VentasDAOImpl implements VentasDAO {

    Conexion cone = new Conexion();

    @Override
    public int guardar(Ventas v) {
        int idVenta = 0;

    try (Connection con = cone.conectar();
         PreparedStatement ps = con.prepareStatement(
                 "INSERT INTO ventas (subtotal_Iva, subtotal_sin_Iva, id_cliente, fecha_venta) VALUES (?,?,?,?)",
                 Statement.RETURN_GENERATED_KEYS)) {

        ps.setDouble(1, v.getSubtotal_Iva());
        ps.setDouble(2, v.getSubtotal_sin_Iva());
        ps.setInt(3, v.getId_cliente().getId());
        ps.setString(4, v.getFecha_venta());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            idVenta = rs.getInt(1);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return idVenta;

    }

    @Override
    public void actualizar(Ventas v, int id) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("update ventas set  ,subtotal_iva=? , subtotal_sin_iva=?, id_cliente=? , fecha_venta= ? where id=?");
            
            ps.setDouble(1, v.getSubtotal_Iva());
            ps.setDouble(2, v.getSubtotal_sin_Iva());
            ps.setInt(3, v.getId_cliente().getId());
            ps.setString(4, v.getFecha_venta());
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("ACTUALIZACION DE VENTA, EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        try (Connection con = cone.conectar()) {

            PreparedStatement ps = con.prepareStatement("delete from ventas where id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la venta ?", null, JOptionPane.YES_NO_OPTION);
            if (op == 0) {
                ps.executeUpdate();
                System.out.println("ELIMINACION EXITOSA");
            } else {
                System.out.println("Operacion cancelada!!!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Ventas> listar() {
        ClientesDAO cDao = new ClientesDAOImlp();
        ArrayList<Ventas> ventaList = new ArrayList<>();
        try (Connection con = cone.conectar()) {
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ventas");
            while (rs.next()) {
                Clientes cl = cDao.buscar(4);
                ventaList.add(new Ventas(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), cl, rs.getString(5)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ventaList;

    }

    @Override
public Ventas buscar(int id) {

    try (Connection con = cone.conectar();
         PreparedStatement ps = con.prepareStatement("SELECT * FROM ventas WHERE id = ?")) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Ventas v = new Ventas();
            v.setId(rs.getInt("id"));
            v.setSubtotal_Iva(rs.getDouble("subtotal_Iva"));
            v.setSubtotal_sin_Iva(rs.getDouble("subtotal_sin_Iva"));
            v.setFecha_venta(rs.getString("fecha_venta"));

            Clientes cl = new Clientes();
            cl.setId(rs.getInt("id_cliente"));
            v.setId_cliente(cl);

            return v;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return null;
}


    @Override
    public double calculartotal(double subtotal) {
        double total=0;
        total= subtotal*0.19 + subtotal;
        return total;
    }

}
