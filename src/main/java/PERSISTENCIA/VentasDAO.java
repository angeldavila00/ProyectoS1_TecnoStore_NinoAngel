package PERSISTENCIA;

import MODELO.Ventas;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public interface VentasDAO {
    
        int guardar(Ventas v);
        void actualizar(Ventas v,int id);
        void eliminar(int id);
        ArrayList<Ventas>listar();
        Ventas buscar(int id);
        double calculartotal(double subtotal);
    
}
