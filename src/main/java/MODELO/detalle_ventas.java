
package MODELO;

/**
 *
 * @author ANGEL
 */
public class detalle_ventas {
    private int id, cantidad;
    private double subtotal;
    private celulares id_celular;
    private ventas id_venta;

    public detalle_ventas(int id, int cantidad, double subtotal, celulares id_celular, ventas id_venta) {
        this.id = id;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.id_celular = id_celular;
        this.id_venta = id_venta;
    }
    public detalle_ventas(){
        
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
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad inválida");
        }
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public celulares getId_celular() {
        return id_celular;
    }

    public void setId_celular(celulares id_celular) {
        this.id_celular = id_celular;
    }

    public ventas getId_venta() {
        return id_venta;
    }

    public void setId_venta(ventas id_venta) {
        this.id_venta = id_venta;
    }
    
    public void calcularSubtotal() {
    if (id_celular == null) throw new IllegalStateException("Celular requerido");
    this.subtotal = id_celular.getPrecio() * this.cantidad;
}

}
