package CONTROLADOR;


import MODELO.Celulares;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public interface GestionarCelulares {

    void guardar(Celulares c);

    void actualizar(Celulares c, int id);

    void eliminar(int id);

    ArrayList<Celulares> listar();

    Celulares buscar(int id);

}
    

    
    
    

