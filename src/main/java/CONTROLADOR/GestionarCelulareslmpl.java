package CONTROLADOR;

import MODELO.celulares;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public class GestionarCelulareslmpl implements GestionarCelulares {

    Conexion cone = new Conexion();

    @Override
    public void guardar(celulares c) {
        try (Connection con = cone.conectar()) {
PreparedStatement ps = con.prepareStatement("insert into celulares(stock, modelo, sistema_operativo,gama,precio,id_marca ) values (?,?,?,?,?,?)");
            ps.setInt(1, c.getStock());
            ps.setString(2,c.getModelo());
            ps.setString(3, c.getSistema_operativo());
            ps.setString(4, c.getGama());
            ps.setDouble(5, c.getPrecio());
            ps.setString(6, String.valueOf(c.getId_marca().getId()));
            ps.executeUpdate();
            System.out.println("REGISTRO DE CELULAR EXITOSO!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            
        }
    }

    @Override
    public void actualizar(celulares c, int id) {
        
    }

    @Override
    public void eliminar(int id) {
        
    }

    @Override
    public ArrayList<celulares> listar() {
        return null;
    }

    @Override
    public celulares buscar(int id) {
        return null;
    }

}
