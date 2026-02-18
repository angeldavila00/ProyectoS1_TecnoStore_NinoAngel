package PERSISTENCIA.Implemetar;

import MODELO.Marcas;
import PERSISTENCIA.Conexion;
import PERSISTENCIA.MarcasDAO;
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
public class MarcasDAOImpl implements MarcasDAO {

    Conexion cone = new Conexion();

    public MarcasDAOImpl() {
    }

    @Override
    public void guardar(Marcas m) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into marcas(nombre ) values (?)");

            ps.setString(1, m.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Marcas m) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("update marcas set  nombre=? where id=?");
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("delete from marcas where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("ELIMINACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Marcas> listar() {
        ArrayList<Marcas> marcasList = new ArrayList<>();
        try (Connection con = cone.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from marcas");
            while (rs.next()) {
                marcasList.add(new Marcas(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return marcasList;
    }

    @Override
    public Marcas buscar(int id) {
        Marcas m = new Marcas(0, "");
        try (Connection con = cone.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from marcas where id=" + id);
            while (rs.next()) {
                m.setId(rs.getInt(1));
                m.setNombre(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return m;
    }

}
