
package MODELO;

/**
 *
 * @author ANGEL
 */
public class StockCelular {
    
    private String marca;
    private String modelo;
    private String gama;
    private double precio;
    private int cantidad;

    public StockCelular(String marca, String modelo, String gama, double precio, int cantidad) {
        this.marca = marca;
        this.modelo = modelo;
        this.gama = gama;
        this.precio = precio;
        this.cantidad = cantidad;
    }
@Override
    public String toString() {
        return """
               Marca:               %s
               Modelo:              %s
               Gama:                %s
               Precio:              %s  
               Cantidad Vendida:    %s
               """.formatted(marca,modelo,gama,precio,cantidad);
    }
}
