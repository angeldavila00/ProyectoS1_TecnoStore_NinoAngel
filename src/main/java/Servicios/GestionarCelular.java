
package Servicios;

import MODELO.Celulares;
import PERSISTENCIA.CelularesDAO;
import PERSISTENCIA.Implemetar.CelularesDAOImpl;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public class GestionarCelular {
  private CelularesDAO celularDAO= new CelularesDAOImpl();
  
  //Si cumple con la condicion de registro, verifica que el id si exista 
  public void guardar(Celulares celu){
      Celulares existe = celularDAO.buscar(celu.getId());
      if(existe != null){
          System.out.println("ERROR: ya existe un celular con ese ID "+celu.getId());
          return;
      }
      celularDAO.guardar(celu);
      System.out.println("Celular registrado");
  }
  
  //Consultar registros
  public void listar(){
      ArrayList<Celulares> lista = celularDAO.listar();
      if(lista.isEmpty()){
          System.out.println("No existen celulares");
          return;
      }
      lista.forEach(System.out::println);
  }
  
  //Buscar por ID
  
  public Celulares buscar(int id){
      return celularDAO.buscar(id);
  }
  
  //verifica si existe y lo elimina
  
  public void eliminar(int id){
      Celulares existe = celularDAO.buscar(id);
      if(existe == null){
          System.out.println("No existen celulares con ese ID ");
          return;
      }
      celularDAO.eliminar(id);
      System.out.println("Celular eliminado");
  }
  
  //Actualizar celular 
  
  public void actualizar(Celulares celu){
      Celulares existe = celularDAO.buscar(celu.getId());
      if (existe == null) {
            System.out.println("No existe un celular con ese ID");
            return;
        }
       celularDAO.actualizar(celu, celu.getId());
        System.out.println("Celular Actualizado");
    }
      //Se gestiona medicante una actualizacion automatica en la base de datos
  
  public boolean descontarStock(int id, int cantidad){
      if(cantidad <= 0) return false;
      return celularDAO.descontarStock(id, cantidad);
  }
  
}
