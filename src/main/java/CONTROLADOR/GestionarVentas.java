package CONTROLADOR;

import MODELO.ventas;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public interface GestionarVentas {

    void guardar(ventas v);

    void actualizar(ventas v, int id);

    void eliminar(int id);

    ArrayList<ventas> listar();

    ventas buscar(int id);
}
