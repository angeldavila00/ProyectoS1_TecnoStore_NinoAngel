package CONTROLADOR;

import MODELO.clientes;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public interface GestionarClientes {

    void guardar(clientes c);

    void actualizar(clientes c, int id);

    void eliminar(int id);

    ArrayList<clientes> listar();

    clientes buscar(int id);
}
