package PERSISTENCIA.Implemetar;

import MODELO.Celulares;
import PERSISTENCIA.CelularesDAO;
import PERSISTENCIA.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;

public class CelularesDAOImpl implements CelularesDAO {

    Conexion cone = new Conexion();

    @Override
    public void guardar(Celulares celu) {
        try (Connection con = cone.conectar()) {
            PreparedStatement ps = con.prepareStatement("insert into celulares(stock,modelo, sistema_operativo, gama, precio ) values (?,?,?,?,?)");
            ps.setString(1, String.valueOf(celu.getStock()));
            ps.setString(2, celu.getModelo());
            ps.setString(3, celu.getSistema_operativo());
            ps.setString(4, celu.getGama().name());
            ps.setDouble(5, celu.getPrecio());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actualizar(Celulares celu, int id) {
        
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public ArrayList<Celulares> listar() {

        return null;

    }

    @Override
    public Celulares buscar(int id) {

        return null;

    }

    @Override
    public boolean descontarStock(int id, int cantidad) {

        return false;

    }

}
