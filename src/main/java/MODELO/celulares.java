
package MODELO;

import java.io.Serializable;

/**
 *
 * @author ANGEL
 */
public class celulares implements Serializable {
    private int id , stock;
    private String modelo, sistema_operativo;
    private Tipogama gama;
    private double precio;
    private marcas id_marca;

    public celulares(int id, int stock, String modelo, String sistema_operativo, Tipogama gama, double precio, marcas id_marca) {
        this.id = id;
        this.stock = stock;
        this.modelo = modelo;
        this.sistema_operativo = sistema_operativo;
        this.gama = gama;
        this.precio = precio;
        this.id_marca = id_marca;
    }

    
    
    public celulares(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSistema_operativo() {
        return sistema_operativo;
    }

    public void setSistema_operativo(String sistema_operativo) {
        this.sistema_operativo = sistema_operativo;
    }

    public Tipogama getGama() {
        return gama;
    }

    public void setGama(Tipogama gama) {
        this.gama = gama;
    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public marcas getId_marca() {
        return id_marca;
    }

    public void setId_marca(marcas id_marca) {
        this.id_marca = id_marca;
    }
    @Override
    public String toString() {
        return """
               *****************************
               Id:          %s
               Nombre:      %s
               Sistema Operativo:         %s
               Gama:       %s
               """.formatted(id,id_marca.getNombre(),sistema_operativo,gama);
    }

    
    
}
