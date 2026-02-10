package Servicios;

import MODELO.Ventas;
import PERSISTENCIA.Implemetar.VentasDAOImpl;
import PERSISTENCIA.VentasDAO;
import java.util.ArrayList;

/**
 *
 * @author ANGEL
 */
public class GestionarVentas {

    private VentasDAO ventaDAO = new VentasDAOImpl();

//Si cumple para guardar 
    public void guardar(Ventas v) {
        Ventas existe = ventaDAO.buscar(v.getId());
        if (existe != null) {
            System.out.println("Error ya existen ventas con ese id " + v.getId());
            return;
        }
        ventaDAO.guardar(v);
        System.out.println("Venta registrada");

    }

    public void listar() {
        ArrayList<Ventas> lista = ventaDAO.listar();
        if (lista.isEmpty()) {
            System.out.println("NO HAY VENTAS");
            return;
        }

        lista.forEach(System.out::println);
    }
    
    public Ventas buscar(int id){
        return ventaDAO.buscar(id);
    }
    
    public void eliminar(int id){
        Ventas existe = ventaDAO.buscar(id);
        if(existe == null){
            System.out.println("No existe una venta con ese ID.");
        return;
        }
        
        ventaDAO.eliminar(id);
        System.out.println("Venta eliminada con exito");
    }
    
    //Actualizar validacion 
    
    public void actualizar(Ventas v){
        Ventas existe = buscar(v.getId());
        
        if(existe == null){
            System.out.println("No existen ventas con ese id");
            return;
        }
        ventaDAO.actualizar(v, v.getId());
        System.out.println("Celular actualizado");
    }

}
