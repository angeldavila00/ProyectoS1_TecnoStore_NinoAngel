
package PATRON;

import MODELO.Celulares;
import java.util.List;

/**
 *
 * @author ANGEL
 */
public interface StrategyDescuento {
double aplicar(double subtotal, List<Celulares> listaCelulares);
    String mensaje();
}
