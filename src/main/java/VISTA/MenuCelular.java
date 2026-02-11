
package VISTA;

import MODELO.Celulares;
import MODELO.Tipogama;
import PERSISTENCIA.CelularesDAO;
import PERSISTENCIA.Implemetar.CelularesDAOImpl;
import java.util.Scanner;

/**
 *
 * @author ANGEL
 */
public class MenuCelular {
    
    private CelularesDAO celularDAO = new CelularesDAOImpl();
    
    private void guardar(){
        Celulares celu = new Celulares();
        System.out.println("\n==== REGISTRAR Celular=====");
        System.out.print("Ingrese el modelo del celular: ");
        String modelo = new Scanner(System.in).nextLine();
        System.out.print("Ingrese el sistema operativo ");
        String sistema_operativo = new Scanner(System.in).nextLine();
        System.out.println("""
                           Ingrese la gama:
                           1. ALTA  2. MEDIA  3. BAJA
                           """);
        int Gama = new Scanner(System.in).nextInt();
        while (Gama < 1 || Gama > 3) {
            System.out.println("Opcion no valida");
            Gama = new Scanner(System.in).nextInt();
        }
        Tipogama gama;
        switch (Gama) {
            case 1:
                gama = Tipogama.ALTA;
                break;
            case 2:
                gama = Tipogama.MEDIA;
                break;
            default:
                gama = Tipogama.BAJA;
                break;
        }
        System.out.println("Ingresa el precio del celular: ");
        double precio = new Scanner(System.in).nextDouble();
        
    }

}
