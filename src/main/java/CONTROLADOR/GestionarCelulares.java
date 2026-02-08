package CONTROLADOR;

import MODELO.Tipogama;
import MODELO.celulares;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public class GestionarCelulares {

    private ArrayList<celulares> celularesList = new ArrayList<>();
    
  //Uso de Stream Api
    
    //Registro si cumple y si el id swe repite genera error
    public void registrar(celulares c){
        boolean existen = celularesList.stream().anyMatch(x -> x.getId() == c.getId());
        if(existen){
            System.out.println("El id del dispositivo a registrar ya existe..."+c.getId());
            return;       
        }
        celularesList.add(c);
        System.out.println("Celular Registrado con exito!");
        
    }
    
    //Listar celulares si existen
    public void listar(){
        if(celularesList.isEmpty()){
            System.out.println("No existen celulares registrados");
            return;
        }
        celularesList.forEach(System.out::println);
    }
    
    //Buscar celular por ID se filtra por el id cel celular y si no existe retorna null.
    public celulares buscar(int id){
        return celularesList.stream()
                .filter(c -> c.getId()== id)
                .findFirst()
                .orElse(null);
    }
    
    //Eliminar celular se busca por el ID, si no existe retorna null.
    
    public void eliminar(int id){
        boolean elimina = celularesList.removeIf(c -> c.getId() == id);
        if(elimina){
            System.out.println("Celular Eliminado....");
        }else {
            System.out.println("El celular que busca eliminar no existe..");
        }
    }
    
    //Buscar por ID y actualiza informacion, si no existe cancela el proceso.
    
    public void actualizar(celulares c){
        celulares existencia = buscar(c.getId());
        if (existencia == null){
            System.out.println("No se puede actualizar la informacion, no existen celulares con ese ID..");
            return;
        }
        existencia.setModelo(c.getModelo());
        existencia.setPrecio(c.getPrecio());
        existencia.setSistema_operativo(c.getSistema_operativo());
        existencia.setGama(c.getGama());
        existencia.setStock(c.getStock());
        System.out.println("Celular actualizado..");
           
        }
    
    //Busca el celular por el id si el celular no existe no se descuenta el stock, si no hay stock suficiente se cancela y deja stock igual y si existe stock se descuenta 
    public boolean quitarStock(int idCelular,int cantidad){
        celulares c = buscar(idCelular);
        
        if(c == null){
            return false;
        }
        if(cantidad <=0){
            return false;
        }
        if(c.getStock()< cantidad){
            return false;
        }
        c.setStock(c.getStock()- cantidad);
        return true;
        
    }
    
}
    
    
    

