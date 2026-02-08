package CONTROLADOR;

import MODELO.Clientes;
import MODELO.Personas;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public interface GestionarClientes {

    void guardar(Personas p);

    void actualizar(Clientes c, int id);

    void eliminar(int id);

    ArrayList<Clientes> listar();

    Clientes buscar(int id);
}
