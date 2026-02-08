
package CONTROLADOR;

import MODELO.personas;
import PERSISTENCIA.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ANGEL
 */
public class GestionarPersonas {
    
    Conexion cone = new Conexion();
    
    public void agregarPersona(personas p){
        try(Connection con = cone.conectar()){
            PreparedStatement ps = con.prepareStatement("insert into personas(nombre,identificacion,correo,telefono) values (?,?,?,?)");
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getIdentificacion());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
