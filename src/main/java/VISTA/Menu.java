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
                           3.   Salir.
                           """);
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 3) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    MenuVenta menu = new MenuVenta();
                    menu.menu();
                    break;
                case 2:
                    MenuCliente menucliente = new MenuCliente();
                    menucliente.menu();
                    break;
                case 3:
                    System.out.println("Gracias por usar nuestro sistema!");
                    break;
            }
        } while (op != 3);
    }
}
