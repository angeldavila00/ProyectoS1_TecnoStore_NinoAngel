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
    
    public void listar(){
        ArrayList<Ventas> lista = ventaDAO.listar();
    }

}
