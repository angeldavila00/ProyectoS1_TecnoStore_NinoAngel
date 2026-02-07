
package PATRON;

import MODELO.celulares;
import java.util.List;

/**
 *
 * @author ANGEL
 */
public interface StrategyDescuento {
double aplicar(double subtotal, List<celulares> listaCelulares);
    String mensaje();
}
