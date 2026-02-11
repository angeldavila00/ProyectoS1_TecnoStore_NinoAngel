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
    public void guardar(Ventas v) {
        try (Connection con = cone.conectar()) {
            //Insercion a la base de datos 
            PreparedStatement ps = con.prepareStatement("insert into ventas (subtotal_iva, subtotal_sin_iva, id_cliente,fecha_venta,) VALUES (?,?,?,?)");

            

            ps.setDouble(1, v.getSubtotal_Iva());

            ps.setDouble(2, v.getSubtotal_sin_Iva());

            ps.setInt(3, v.getId_cliente().getId());
            
            ps.setString(4, v.getFecha_venta());

            ps.executeUpdate();

            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

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
        ClientesDAO cDao = new ClientesDAOImlp();
        Ventas v = null;
        try (Connection con = cone.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ventas where id=" + id);
            while (rs.next()) {
                v.setId(rs.getInt(1));
                v.setSubtotal_Iva(rs.getDouble(2));
                v.setSubtotal_sin_Iva(rs.getDouble(3));
                Clientes cl = cDao.buscar(4);
                v.setFecha_venta(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return v;
    }

    @Override
    public double calculartotal(double subtotal) {
        double total=0;
        total= subtotal*0.19 + subtotal;
        return total;
    }

}
