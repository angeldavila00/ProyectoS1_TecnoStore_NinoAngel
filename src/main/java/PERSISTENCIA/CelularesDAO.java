package PERSISTENCIA;


import MODELO.Celulares;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public interface CelularesDAO {

    void guardar(Celulares celu);

    void actualizar(Celulares celu, int id);

    void eliminar(int id);

    ArrayList<Celulares> listar();

    Celulares buscar(int id);
    
    boolean descontarStock(int id, int cantidad);

}
    

    
    
    

