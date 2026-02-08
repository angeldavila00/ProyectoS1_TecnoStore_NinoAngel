
package CONTROLADOR;

import MODELO.ventas;
import PERSISTENCIA.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public class GestionarVentasImpl implements GestionarVentas{
        Conexion cone = new Conexion();
        
    @Override
    public void guardar(ventas v) {
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
    public void actualizar(ventas v, int id) {
        
    }

    @Override
    public void eliminar(int id) {
        
    }

    @Override
    public ArrayList<ventas> listar() {
        
        return null;
        
    }

    @Override
    public ventas buscar(int id) {
        
        return null;
        
    }

}
