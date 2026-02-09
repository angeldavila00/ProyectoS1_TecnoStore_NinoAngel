
package PERSISTENCIA.Implemetar;


import MODELO.Ventas;
import PERSISTENCIA.Conexion;
import PERSISTENCIA.VentasDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public class VentasDAOImpl implements VentasDAO{
        Conexion cone = new Conexion();
        
    @Override
    public void guardar(Ventas v) {
        try(Connection con = cone.conectar()){
            //Insercion a la base de datos 
            PreparedStatement ps = con.prepareStatement("insert into ventas(fecha_venta, total, id_cliente) values (?,?,?)");
            ps.setString(1, v.getFecha_venta());
            ps.setDouble(2, v.getTotal());
            ps.setString(3, String.valueOf(v.getId_cliente().getId()));
            ps.executeUpdate();
            System.out.println("REGISTRO EXITOSO!");
        }   catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        
    }

    @Override
    public void actualizar(Ventas v, int id) {
        
    }

    @Override
    public void eliminar(int id) {
        
    }

    @Override
    public ArrayList<Ventas> listar() {
        
        return null;
        
    }

    @Override
    public Ventas buscar(int id) {
        
        return null;
        
    }

}
