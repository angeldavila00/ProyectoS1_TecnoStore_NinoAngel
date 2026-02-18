package PERSISTENCIA;

import MODELO.Clientes;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public interface ClientesDAO {

    void guardar(Clientes cl);

    void actualizar(Clientes cl, int id);

    void eliminar(int id);

    ArrayList<Clientes> listar();

    Clientes buscar(int id);
}
