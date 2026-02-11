package PERSISTENCIA.Implemetar;

import MODELO.DetalleVentas;
import PERSISTENCIA.Conexion;
import PERSISTENCIA.DetalleVentasDAO;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public class DetalleVentasDAOImpl implements DetalleVentasDAO {

    Conexion cone = new Conexion();

    @Override
    public void guardar(DetalleVentas dv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizar(DetalleVentas dv, int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double calcularSubtotal(int cantidad, double precio) {
        double subtotal = 0;
        subtotal = cantidad * precio;
        return subtotal;
    }

    @Override
    public DetalleVentas buscar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<DetalleVentas> listar() {
        return null;
    }

}
