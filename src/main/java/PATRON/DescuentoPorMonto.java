
package PATRON;

import MODELO.Celulares;
import java.util.List;

/**
 *
 * @author ANGEL
 */
public class DescuentoPorMonto implements StrategyDescuento{

    @Override
    public double aplicar(double subtotal, List<Celulares> listaCelulares) {
        if(subtotal >= 3000000){
            return subtotal *0.9;
        }
        return subtotal;
    }

    @Override
    public String mensaje() {
        return "10% por compra mayor a 3.000.000";
    }
    

}
