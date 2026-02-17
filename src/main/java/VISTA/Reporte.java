package VISTA;

import MODELO.Celulares;
import PERSISTENCIA.GestionarReporte;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ANGEL
 */
public class Reporte {

    GestionarReporte gr = new GestionarReporte();

    private void stockBajo() {
        List<Celulares> lista = gr.obtenerStockMasVendidos();

        System.out.println("""
                    =====================================
                      CELULARES CON STOCK BAJO (<= 5)
                    =====================================
                    """);
        if (lista.isEmpty()) {
            System.out.println("No hay celulares con stock bajo.");
        } else {
            for (Celulares celu : lista) {
                System.out.println(celu);
            }
        }
        menu();
    }

    public void menu() {
        int op = 0;
        do {
            System.out.println("""
                               --------------------------------------------
                               Reportes.
                               --------------------------------------------
                               1.   reporte de stock
                               2.   Regresar al Menu
                               --------------------------------------------
                               """);
            while (op < 1 || op > 5) {
                System.out.println("Error opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    stockBajo();
                    break;

            }
        }while (op != 2);
    }
}
