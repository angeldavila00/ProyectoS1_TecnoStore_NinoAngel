package PERSISTENCIA.Implemetar;

import MODELO.Celulares;
import MODELO.Marcas;
import MODELO.Tipogama;
import PERSISTENCIA.CelularesDAO;
import PERSISTENCIA.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class CelularesDAOImpl implements CelularesDAO {

    Conexion cone = new Conexion();

    @Override
    public void guardar(Celulares celu) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into celulares(modelo, sistema_operativo, gama, precio, id_marca,stock ) values (?,?,?,?,?,?)");

            ps.setString(1, celu.getModelo());
            ps.setString(2, celu.getSistema_operativo());
            ps.setString(3, celu.getGama().name());
            ps.setDouble(4, celu.getPrecio());
            ps.setInt(5, celu.getId_marca().getId());
            ps.setString(6, String.valueOf(celu.getStock()));

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Celulares celu, int id) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("update celulares set  modelo=? sistema_operativo=?, gama=?, precio=?, id_marca=?, Stock=? where id=?");
            ps.setInt(1, celu.getStock());
            ps.setString(2, celu.getModelo());
            ps.setString(3, celu.getSistema_operativo());
            ps.setString(4, celu.getGama().name());
            ps.setDouble(5, celu.getPrecio());
            ps.setInt(6, celu.getId_marca().getId());
            ps.setInt(7, id);
            ps.executeUpdate();
            System.out.println("Actualizacion celular exitosa...");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void eliminar(int id) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("delete from celulares where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("ELIMINACION EXITOSA!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ArrayList<Celulares> listar() {

        ArrayList<Celulares> celularesList = new ArrayList<>();
        try (Connection con = cone.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select celu.id, celu.stock, celu.modelo, celu.sistema_operativo, celu.gama, celu.precio, celu.id_marca, m.nombre from celulares celu inner join marcas m on celu.id_marca = m.id");

            while (rs.next()) {
                Marcas marca = new Marcas(
                        rs.getInt("id_marca"),
                        rs.getString("nombre")
                );

                Tipogama gama = Tipogama.valueOf(
                        rs.getString("gama").toUpperCase()
                );
                celularesList.add(new Celulares(
                        rs.getInt("id"),
                        rs.getInt("stock"),
                        rs.getString("modelo"),
                        rs.getString("sistema_operativo"),
                        gama,
                        rs.getDouble("precio"),
                        marca
                ));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return celularesList;

    }

    @Override
    public Celulares buscar(int id) {
        Celulares celu = new Celulares(0, 0, "", "", Tipogama.BAJA, 0, new Marcas(0, ""));

        try (Connection con = cone.conectar()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select celu.id, celu.stock, celu.modelo, celu.sistema_operativo, celu.gama, celu.precio, celu.id_marca, m.nombre from celulares celu inner join marcas m on celu.id_marca = m.id where celu.id=" + id);
            while (rs.next()) {
                Marcas marca = new Marcas(rs.getInt(7), rs.getString(8));
                celu.setId(rs.getInt(1));
                celu.setStock(rs.getInt(2));
                celu.setModelo(rs.getString(3));
                celu.setSistema_operativo(rs.getString(4));
                celu.setGama(Tipogama.valueOf(rs.getString(5)));
                celu.setPrecio(rs.getDouble(6));
                celu.setId_marca(marca);
            }

        } catch (SQLException ex) {
            System.getLogger(CelularesDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        return celu;

    }

    @Override
    public boolean descontarStock(int id, int cantidad) {

        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("update celulares set stock = stock - ? where id = ? and stock >= ?");
            ps.setInt(1, cantidad);
            ps.setInt(2, id);
            ps.setInt(3, cantidad);
            int conteo = ps.executeUpdate();
            if (conteo > 0) {
                System.out.println("Stock actualizado");
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
