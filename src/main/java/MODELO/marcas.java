
package MODELO;

/**
 *
 * @author ANGEL
 */
public class marcas {
    private int id;
    private String nombre;

    public marcas(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public marcas(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
