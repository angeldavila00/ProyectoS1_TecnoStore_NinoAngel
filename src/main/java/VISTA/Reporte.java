package VISTA;

import MODELO.Celulares;
import PERSISTENCIA.GestionarReporte;
import java.util.List;
import java.util.Scanner;

public class Reporte {

     GestionarReporte gr = new GestionarReporte();
    Scanner sc = new Scanner(System.in);

    private void stockBajo() {
        
        List<Celulares> lista = gr.obtenerStockBajo();

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
    }

    public void menu() {
        int op;

        do {
            System.out.println("""
                   --------------------------------------------
                   Reportes.
                   --------------------------------------------
                   1.   reporte de stock
                   2.   Regresar al Menu
                   --------------------------------------------
                   """);
            System.out.print("Digite una opcion: ");
            op = sc.nextInt();

            switch (op) {
                case 1 ->
                    stockBajo();
                case 2 ->
                    System.out.println("Regresando...");
                default ->
                    System.out.println("Opcion no valida.");
            }

        } while (op != 2);
    }
}


