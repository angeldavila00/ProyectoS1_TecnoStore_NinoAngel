
package MODELO;
/**
 *
 * @author ANGEL
 */
public class ventas {
    
    private int id;
    private String fecha_venta;
    private double total;
    private clientes id_cliente;

    public ventas(int id, String fecha_venta, double total, clientes id_cliente) {
        this.id = id;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.id_cliente = id_cliente;
    }

    public ventas(){
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public clientes getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(clientes id_cliente) {
        this.id_cliente = id_cliente;
    }

}
