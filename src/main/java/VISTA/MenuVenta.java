package VISTA;

import PERSISTENCIA.CelularesDAO;
import PERSISTENCIA.ClientesDAO;
import PERSISTENCIA.Implemetar.CelularesDAOImpl;
import PERSISTENCIA.Implemetar.ClientesDAOImlp;
import PERSISTENCIA.Implemetar.VentasDAOImpl;
import PERSISTENCIA.VentasDAO;
import java.util.Scanner;

public class MenuVenta {

    VentasDAO gv = new VentasDAOImpl();
    private CelularesDAO celularDAO = new CelularesDAOImpl();
    private VentasDAO  ventaDAO = new VentasDAOImpl();
    private ClientesDAO clientesDAO = new ClientesDAOImlp();
    
    public void menuventa(){
        int op;
        do{
            System.out.println("""
                               =====Menu Venta====
                               -------------------
                               1. Registrar venta
                               2. Listar ventas
                               3. Actualizar venta
                               4. Eliminar venta
                               5. Buscar venta
                               6. Salir
                               -------------------
                               """);
            System.out.print("Seleccione una opción: ");
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op >6){
                System.out.println("Error opcion no valida");
                op = new Scanner(System.in).nextInt();
                
            }
            switch (op) {
                case 1:

                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                
            }
        }while ( op != 6) ;
            
            
        }
    }
    
