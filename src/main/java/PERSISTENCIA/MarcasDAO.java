
package PERSISTENCIA;

import MODELO.Marcas;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public interface MarcasDAO {
    void guardar(Marcas m);

    void actualizar(Marcas m);

    void eliminar(int id);

    ArrayList<Marcas> listar();

    Marcas buscar(int id);
}
