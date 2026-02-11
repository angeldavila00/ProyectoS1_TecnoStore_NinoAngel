package VISTA;

import MODELO.Marcas;
import PERSISTENCIA.Implemetar.MarcasDAOImpl;
import PERSISTENCIA.MarcasDAO;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuMarca {

    private MarcasDAO marcaDAO = new MarcasDAOImpl();

    private void guardar() {
        Marcas m = new Marcas(0, "");
        System.out.println("Ingera el nombre de la marca a registrar: ");
        m.setNombre(new Scanner(System.in).nextLine());
        marcaDAO.guardar(m);
    }

    private void actualizar() {
        System.out.println("Ingera el id de la marca a Actualizar: ");
        int id = new Scanner(System.in).nextInt();
        Marcas m = marcaDAO.buscar(id);
        if (m != null && m.getId() != 0) {
            System.out.println("Marca encontrada: " + m.getNombre());
            System.out.println("\nIngrese el nuevo nombre DE LA MARCA:");
            m.setNombre(new Scanner(System.in).nextLine());
            marcaDAO.actualizar(m);
        } else {
            System.out.println("No existen marcas con ese ID");
        }
    }

    private void eliminar() {
        System.out.println("Ingrese el id de la marca a eliminar");
        int id = new Scanner(System.in).nextInt();
        marcaDAO.eliminar(id);
    }

    private void listar() {
    System.out.println("\n=== LISTADO DE MARCAS ===");

    ArrayList<Marcas> marcas = marcaDAO.listar();
    if (marcas.isEmpty()) {
        System.out.println("No hay marcas registradas.");
        return;
    }
    for (Marcas m : marcas) {
        System.out.println(m);
    }
}

    private void buscar() {
        System.out.println("Ingrese el id de la marca a buscar");
        int id = new Scanner(System.in).nextInt();
        Marcas m = marcaDAO.buscar(id);
        if (m != null && m.getId() != 0) {
            System.out.println("ID: " + m.getId() + " | Nombre: " + m.getNombre());
        } else {
            System.out.println("No existen marcas con ese ID");
        }
    }

    public void menuMarcas() {
        int op = 0;
        do {
            System.out.println("""
                               ******************************
                                        Marcas
                               1.   Registrar
                               2.   Actualizar
                               3.   Eliminar
                               4.   Listar
                               5.   Buscar
                               6.   Regresar
                               """);
            op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 6) {
                System.out.println("Error, opcion no valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    guardar();
                    break;
                case 2:
                    actualizar();
                    break;
                case 3:
                    eliminar();
                    break;
                case 4:
                    listar();
                    break;
                case 5:
                    buscar();
                    break;
            }
        } while (op != 6);

    }
}
