package MODELO;

import java.util.List;

/**
 *
 * @author ANGEL
 */
public class Ventas {

    private int id;
    private double subtotal_Iva;
    private double subtotal_sin_Iva;
    private Clientes id_cliente;
    private String fecha_venta;

    public Ventas(int id, double subtotal_Iva, double subtotal_sin_Iva, Clientes id_cliente, String fecha_venta) {
        this.id = id;
        this.subtotal_Iva = subtotal_Iva;
        this.subtotal_sin_Iva = subtotal_sin_Iva;
        this.id_cliente = id_cliente;
        this.fecha_venta = fecha_venta;
    }

    public Ventas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSubtotal_Iva() {
        return subtotal_Iva;
    }

    public void setSubtotal_Iva(double subtotal_Iva) {
        this.subtotal_Iva = subtotal_Iva;
    }

    public double getSubtotal_sin_Iva() {
        return subtotal_sin_Iva;
    }

    public void setSubtotal_sin_Iva(double subtotal_sin_Iva) {
        this.subtotal_sin_Iva = subtotal_sin_Iva;
    }

    public Clientes getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Clientes id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

}
