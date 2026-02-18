package PERSISTENCIA;

import MODELO.Celulares;
import MODELO.Marcas;
import MODELO.Tipogama;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionarReporte {

    private final Conexion cone = new Conexion();

    public List<Celulares> obtenerStockBajo() {

        List<Celulares> lista = new ArrayList<>();

        try (
            Connection cn = cone.conectar();
            PreparedStatement ps = cn.prepareStatement("""
                SELECT 
                    c.id,
                    c.stock,
                    c.modelo,
                    c.sistema_operativo,
                    c.gama,
                    c.precio,
                    m.id AS id_marca,
                    m.nombre AS nombre_marca
                FROM celulares c
                INNER JOIN marcas m ON c.id_marca = m.id
                WHERE c.stock <= 5
                ORDER BY c.stock ASC
            """);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {

                Marcas marca = new Marcas();
                marca.setId(rs.getInt("id_marca"));
                marca.setNombre(rs.getString("nombre_marca"));

                Celulares celular = new Celulares(
                    rs.getInt("id"),
                    rs.getInt("stock"),
                    rs.getString("modelo"),
                    rs.getString("sistema_operativo"),
                    Tipogama.valueOf(rs.getString("gama").toUpperCase()),
                    rs.getDouble("precio"),
                    marca
                );

                lista.add(celular);
            }

        } catch (SQLException e) {
            System.err.println("Error en obtenerStockBajo: " + e.getMessage());
        }

        return lista;
    }
}
