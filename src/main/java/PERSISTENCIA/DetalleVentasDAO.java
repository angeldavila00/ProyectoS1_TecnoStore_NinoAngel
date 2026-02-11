package PERSISTENCIA;

import MODELO.DetalleVentas;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public interface DetalleVentasDAO {

    void guardar(DetalleVentas dv);

    void actualizar(DetalleVentas dv, int id);

    void eliminar(int id);

    double calcularSubtotal(int cantidad, double precio);

    DetalleVentas buscar(int id);

    ArrayList<DetalleVentas> listar();
}
