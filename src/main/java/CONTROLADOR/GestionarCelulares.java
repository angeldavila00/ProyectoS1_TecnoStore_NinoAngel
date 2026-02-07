package CONTROLADOR;

import MODELO.celulares;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public interface GestionarCelulares {

    void guardar(celulares c);

    void actualizar(celulares c, int id);

    void eliminar(int id);

    ArrayList<celulares> listar();

    celulares buscar(int id);

}
