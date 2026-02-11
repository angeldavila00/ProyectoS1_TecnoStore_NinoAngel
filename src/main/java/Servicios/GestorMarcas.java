
package Servicios;

import MODELO.Marcas;
import PERSISTENCIA.Implemetar.MarcasDAOImpl;
import PERSISTENCIA.MarcasDAO;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public class GestorMarcas {
     private final MarcasDAO marcaDAO = new MarcasDAOImpl();

    
    public void registrar(Marcas m) {
        Marcas existe = marcaDAO.buscar(m.getId());
        if (existe != null && existe.getId() != 0) {
            System.out.println("Error: ya existe una marca con ese ID. " + m.getId());
            return;
        }
        marcaDAO.guardar(m);
        System.out.println("Marca registrada.");
    }

   
    public void listar() {
        ArrayList<Marcas> lista = marcaDAO.listar();
        if (lista.isEmpty()) {
            System.out.println("No existen marcas");
            return;
        }
        lista.forEach(System.out::println);
    }
    

    public Marcas buscarPorId(int id) {
        return marcaDAO.buscar(id);
    }

 
    public void eliminar(int id) {
        Marcas existe = marcaDAO.buscar(id);
        if (existe != null && existe.getId() != 0) {
            marcaDAO.eliminar(id);
            System.out.println("Marca eliminada.");
        } else {
            System.out.println("No existe una marca con ese ID.");
        }
    }

  
    public void actualizar(Marcas m) {
        Marcas existe = buscarPorId(m.getId());
        if (existe == null || existe.getId() == 0) {
            System.out.println("No existe una marca con ese ID.");
            return;
        }
        marcaDAO.actualizar(m);
        System.out.println("Marca Actualizada.");
    }
}


