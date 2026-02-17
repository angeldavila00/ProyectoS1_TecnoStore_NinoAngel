
package PERSISTENCIA;

import MODELO.Celulares;
import MODELO.StockCelular;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANGEL
 */
public class GestionarReporte {
    Conexion cone = new Conexion();
    
 public List<Celulares> obtenerStockMasVendidos() {

        List<Celulares> top3 = new ArrayList<>();

        try (Connection cn = cone.conectar()) {
            PreparedStatement ps = cn.prepareStatement("""
        SELECT 
            m.nombre,
            c.modelo,
            c.gama,
            c.precio,
            SUM(dv.cantidad)
        FROM celulares c
        INNER JOIN marca m ON c.id_marca = m.id
        INNER JOIN detalle_ventas dv ON dv.id_celular = c.id
        GROUP BY c.id, m.nombre, c.modelo, c.gama, c.precio
        ORDER BY unidades_vendidas DESC
        LIMIT 3
    """);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                StockCelular celulares = new StockCelular(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5)
                );

                
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return top3;
    }
}
