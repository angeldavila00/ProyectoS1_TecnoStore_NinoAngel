
package MODELO;

/**
 *
 * @author ANGEL
 */
public class DetalleVentas {
    private int id, cantidad;
    private double subtotal;
    private Celulares id_celular;
    private Ventas id_venta;

    public DetalleVentas(int id, int cantidad, double subtotal, Celulares id_celular, Ventas id_venta) {
        this.id = id;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.id_celular = id_celular;
        this.id_venta = id_venta;
    }
    
    public DetalleVentas(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Celulares getId_celular() {
        return id_celular;
    }

    public void setId_celular(Celulares id_celular) {
        this.id_celular = id_celular;
    }

    public Ventas getId_venta() {
        return id_venta;
    }

    public void setId_venta(Ventas id_venta) {
        this.id_venta = id_venta;
    }

    
    
    
}


