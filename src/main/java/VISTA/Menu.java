package VISTA;

import ViSTA.MenuCliente;
import java.util.Scanner;

/**
 *
 * @author ANGEL
 */
public class Menu {

    public void Menu_Principal() {
        int op = 0;
        do {
            System.out.println("""
                           ******************************
                           1.   Gestionar Ventas.
                           2.   Gestionar Clientes.
                           3.   Gestionar Marcas.
                           4.   Gestionar Celulares
                           5.   Gestionar Venta
                           6.   Gestionar Detalle de Venta
                           7.   Salir.
                           """);
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 7) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    MenuVenta menu = new MenuVenta();

                    break;
                case 2:
                    MenuCliente menucliente = new MenuCliente();
                    menucliente.menu();
                    break;
                case 3:
                    MenuMarca menumarca = new MenuMarca();
                    menumarca.menuMarcas();
                    break;
                case 4:
                    MenuCelular menucelular = new MenuCelular();
                    menucelular.menu();
                    break;
                case 5:
                    MenuVenta menuventa = new MenuVenta();
                    menuventa.menuventa();
                    break;
                case 6:
                    
                    break;
            }
        } while (op != 7);
    }
}
