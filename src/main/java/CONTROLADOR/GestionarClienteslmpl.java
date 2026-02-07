package CONTROLADOR;

import MODELO.clientes;
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
public class GestionarClienteslmpl implements GestionarClientes {

    Conexion cone = new Conexion();

    @Override
    public void guardar(clientes c) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into clientes(nombre,identificacion, correo, telefono ) values (?,?,?,?)");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getIdentificacion());
            ps.setString(3, c.getCorreo());
            ps.setString(4, c.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(clientes c, int id) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("update clientes set nombre=?, identificacion=?, correo=?, telefono=? where id=?");
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getIdentificacion());
            ps.setString(3, c.getCorreo());
            ps.setString(4, c.getTelefono());
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

            PreparedStatement ps = con.prepareStatement("delete from clientes where id=?");
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
    public ArrayList<clientes> listar() {
        ArrayList<clientes> clienteList = new ArrayList<>();
        try (Connection con = cone.conectar()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from clientes");
            while (rs.next()) {
                clienteList.add(new clientes(
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
    public clientes buscar(int id) {
        clientes c = new clientes();
        try (Connection con = cone.conectar()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from clientes where id=" + id);
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


