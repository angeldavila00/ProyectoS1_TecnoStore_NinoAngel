package Servicios;


import MODELO.Clientes;
import PERSISTENCIA.ClientesDAO;
import PERSISTENCIA.Conexion;
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
public class ClientesDAOImlp implements ClientesDAO {

    Conexion cone = new Conexion();

    @Override
    public void guardar(Clientes cl) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into Personas(nombre,identificacion, correo, telefono ) values (?,?,?,?)");
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());

            ps.executeUpdate();
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Clientes cl, int id) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("update Clientes set nombre=?, identificacion=?, correo=?, telefono=? where id=?");
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIdentificacion());
            ps.setString(3, cl.getCorreo());
            ps.setString(4, cl.getTelefono());
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("ACTUALIZACION DE CLIENTE, EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void eliminar(int id) {
        try (Connection con = cone.conectar()) {

            PreparedStatement ps = con.prepareStatement("delete from Clientes where id=?");
            ps.setInt(1, id);
            int op = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el Cliente ?", null, JOptionPane.YES_NO_OPTION);
            if (op == 0) {
                ps.executeUpdate();
                System.out.println("ELIMINACION EXITOSA DEL CLIENTE!");
            } else {
                System.out.println("Operacion cancelada!!!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Clientes> listar() {
        ArrayList<Clientes> clienteList = new ArrayList<>();
        try (Connection con = cone.conectar()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Clientes");
            while (rs.next()) {
                clienteList.add(new Clientes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clienteList;

    }

    @Override
    public Clientes buscar(int id) {
        Clientes c = null;
        try (Connection con = cone.conectar()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Clientes where id=" + id);
            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setIdentificacion(rs.getString(3));
                c.setCorreo(rs.getString(4));
                c.setTelefono(rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

}
