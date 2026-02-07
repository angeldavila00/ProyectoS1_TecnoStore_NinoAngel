
package MODELO;

/**
 *
 * @author ANGEL
 */
public class celulares {
    private int id , stock;
    private String modelo, sistema_operativo, gama;
    private double precio;
    private marcas id_marca;

    public celulares(int id, int stock, String modelo, String sistema_operativo, String gama, double precio, marcas id_marca) {
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

    public String getGama() {
        return gama;
    }

    public void setGama(String gama) {
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
    
    
}
