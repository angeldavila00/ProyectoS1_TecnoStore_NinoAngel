
package Servicios;

import MODELO.Clientes;
import PERSISTENCIA.ClientesDAO;
import PERSISTENCIA.Implemetar.ClientesDAOImlp;
import java.util.ArrayList;



public class GestionarClientes {
private ClientesDAO clientesDAO= new ClientesDAOImlp();

//Validacion de correo

private boolean correoValidacion(String correo){
    return correo != null && correo.matches(".+@.+\\\\..+");
}

//Verificar base de datos

public void guardar(Clientes cl){
    Clientes existe = clientesDAO.buscar(cl.getId());
    if(existe != null){
        System.out.println("ERROR YA EXISTE ESTE CLIENTE CON ESE ID "+cl.getId());
        return;
    }
    if(correoValidacion(cl.getCorreo())){
        System.out.println("ERROR CORREO INVALIDO "+cl.getCorreo());
        return;
    }
    clientesDAO.guardar(cl);
    System.out.println("Cliente registrado");
    
}

//listar clientes valida que no este vacio
public void listar(){
    ArrayList<Clientes> lista = clientesDAO.listar();
    if(lista.isEmpty()){
        System.out.println("No hay clientes");
        return;
        
    }
    lista.forEach(System.out::println);
}

//Buscar 

public Clientes buscar(int id){
    return clientesDAO.buscar(id);
}


//eliminar validar
public void eliminar(int id){
    Clientes existe = clientesDAO.buscar(id);
    if(existe!= null ){
        clientesDAO.eliminar(id);
        System.out.println("Cliente eliminado");
    }else {
        System.out.println("El cliente con ese id no existe");
    }
}

//Actualizar validacion
public void actualizar(Clientes cl){
    Clientes existe = clientesDAO.buscar(cl.getId());
    if(existe == null){
        System.out.println("No existe el cliente para actualizar");
    }
    if(!correoValidacion(cl.getCorreo())){
        System.out.println("error correo no valido "+cl.getCorreo());
        return;
    }
    clientesDAO.actualizar(cl, cl.getId());
    System.out.println("Cliente actualizado");
    
}
}
