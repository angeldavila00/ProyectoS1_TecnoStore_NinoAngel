package CONTROLADOR;

import MODELO.clientes;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public class GestionarClientes {

    private ArrayList<clientes> clientesList = new ArrayList<>();
    
    //validar correo
    private boolean validarCorreo(String correo){
        return correo !=null && correo.matches(".+@.+\\..+");
    }

    //Utilizaremos Stream Api
    //Registrar
    public void registrar(clientes cl) {
        boolean existen = clientesList.stream()
                .anyMatch(x -> x.getIdentificacion()
                .equals(cl.getIdentificacion()));
        if (existen) {
            System.out.println("Error ya existe ese ID en un cliente " + cl.getIdentificacion() + cl.getNombre());
            return;
        }
        if(!validarCorreo(cl.getCorreo())){
            System.out.println("el correo es invalido...");
            return;
        }
        clientesList.add(cl);
        System.out.println("Cliente registrado");
    }
    
    //Listar
    
    public void listar(){
        if(clientesList.isEmpty()){
            System.out.println("No existen clientes..");
            return;
        }
        clientesList.forEach(System.out::println);
    }
    
    //buscar por identificacion
    public clientes buscar(String identificacion){
        return clientesList.stream()
                .filter(cl ->cl.getIdentificacion().equals(identificacion))
                .findFirst()
                .orElse(null);
    }
    
    //Eliminar cliente
    public void eliminar(String identificacion){
        boolean eliminado = clientesList.removeIf(cl ->cl.getIdentificacion().equals(identificacion));
        if (eliminado){
            System.out.println("Cliente eliminado con exito");
        }
        else{
            System.out.println("No existe el cliente que quiere eliminar");
        }
    }
    
    
    //Actualizar cliente
    public void actualizar(clientes cl){
        clientes existen = buscar(cl.getIdentificacion());
        
        if(existen == null){
            System.out.println("No existe el cliente que quiere actualizar");
            return;
        }
        if(!validarCorreo(cl.getCorreo())){
            System.out.println("Correo invalido "+cl.getCorreo());
            return;
        }
        existen.setCorreo(cl.getCorreo());
        existen.setNombre(cl.getNombre());
        existen.setIdentificacion(cl.getIdentificacion());
        existen.setTelefono(cl.getTelefono());
    }
}
