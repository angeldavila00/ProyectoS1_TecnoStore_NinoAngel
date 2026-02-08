
package MODELO;

/**
 *
 * @author ANGEL
 */
public class clientes  extends Personas{

    public clientes(int id, String nombre, String identificacion, String correo, String telefono) {
        super(id, nombre, identificacion, correo, telefono);
    }
    
    @Override
    public String toString() {
        return """
               *****************************
               Id:          %s
               Nombre:      %s
               Identificacion: %s
               Correo:         %s
               Telefono:       %s
               """.formatted(getId(),getNombre(),getIdentificacion(),getCorreo(),getTelefono());
    }
}
