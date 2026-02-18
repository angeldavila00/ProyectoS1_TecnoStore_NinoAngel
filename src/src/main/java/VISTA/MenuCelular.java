package VISTA;

import MODELO.Celulares;
import MODELO.Marcas;
import MODELO.Tipogama;
import PERSISTENCIA.CelularesDAO;
import PERSISTENCIA.Implemetar.CelularesDAOImpl;
import PERSISTENCIA.Implemetar.MarcasDAOImpl;
import PERSISTENCIA.MarcasDAO;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ANGEL
 */
public class MenuCelular {

    private CelularesDAO celularDAO = new CelularesDAOImpl();
    private MarcasDAO marcaDAO = new MarcasDAOImpl();

    private void guardar() {
        
        System.out.println("\n==== REGISTRAR Celular=====");
        System.out.print("Ingrese el modelo del celular: ");
        String modelo = new Scanner(System.in).nextLine();
        System.out.println("Ingresa el sistema operativo");
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
        System.out.println("Ingrese la marca del celular");
        System.out.println(marcaDAO.listar());
        Marcas marca = marcaDAO.buscar(new Scanner(System.in).nextInt());
        System.out.println(marcaDAO.listar());
        System.out.println("Ingresa el stock del Celular");
        int stock = new Scanner(System.in).nextInt();
        Celulares celu = new Celulares(Gama, stock, modelo, sistema_operativo, gama, precio, marca);
        celularDAO.guardar(celu);

    }

    public void actualizar() {
        System.out.println("Ingrese el id del celular a actualizar");
        int id = new Scanner(System.in).nextInt();
        Celulares celu = celularDAO.buscar(id);
        if (celu != null & celu.getId() != 0) {
            System.out.println("Celular encontrado");
            System.out.println(celu);
            System.out.println("""
                               Ingrese el identificador de lo que desea actualizar
                               1.Modelo
                               2.Sistema operativo
                               3.Gama
                               4.Precio
                               5.Marca
                               6.Stock
                               """);

            int op = new Scanner(System.in).nextInt();
            while (op < 1 || op > 6) {
                System.out.println("Ingrea una opcion valida");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nuevo modelo");
                    celu.setModelo(new Scanner(System.in).nextLine());
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo sistema operativo");
                    celu.setSistema_operativo(new Scanner(System.in).nextLine());
                    break;
                case 3:
                    System.out.println("""
                                    1. ALTA  2. MEDIA  3. BAJA
                                    """);
                    int opGama = new Scanner(System.in).nextInt();
                    while (opGama < 1 || opGama > 3) {
                        System.out.println("Opcion no valida");
                        opGama = new Scanner(System.in).nextInt();
                    }
                    switch (opGama) {
                        case 1:
                            celu.setGama(Tipogama.ALTA);
                            break;
                        case 2:
                            celu.setGama(Tipogama.MEDIA);
                            break;
                        case 3:
                            celu.setGama(Tipogama.BAJA);
                            break;
                    }

                    break;
                case 4:
                    System.out.println("Ingrese el nuevo precio");
                    celu.setPrecio(new Scanner(System.in).nextDouble());
                    break;
                case 5:
                    System.out.println("********* MARCAS *********");
                    celularDAO.listar();
                    System.out.println("Ingrese el id de la nueva marca");
                    Marcas marca = marcaDAO.buscar(new Scanner(System.in).nextInt());
                    if (marca != null && marca.getId() != 0) {
                        celu.setId_marca(marca);
                    } else {
                        System.out.println("MARCA NO ENCONTRADA, NO SE ACTUALIZO");
                    }
                    break;
                case 6:
                    System.out.println("Ingrese el nuevo stock");
                    celu.setStock(new Scanner(System.in).nextInt());
                    break;
            }
            celularDAO.actualizar(celu, id);
        } 
        
    }

    public void eliminar() {
        listar();
        System.out.println("Ingresa el id del celular que quieres eliminar?");      
        int id = new Scanner(System.in).nextInt();
        celularDAO.eliminar(id);
    }

    private void listar() {
        System.out.println("\n===LISTADO DE CELULARES ===");
        ArrayList<Celulares> celularList = celularDAO.listar();
        if (celularList.isEmpty()) {
            System.out.println("No hay Celulares registrados.");
            return;
        }
        for (Celulares cl : celularList) {
            System.out.println(cl);
        }
    }

    public Celulares buscar() {
        System.out.print("\nIngrese el ID del Celular a buscar: ");
        int id = new Scanner(System.in).nextInt();

        Celulares cl = celularDAO.buscar(id);
        if (cl != null && cl.getId() != 0) {
            System.out.println("===CELULAR ENCONTRADO:===");
            System.out.println("ID: " + cl.getId());
            System.out.println("Modelo: " + cl.getModelo());
            System.out.println("SistemaOperativo: " + cl.getSistema_operativo());
            System.out.println("Gama: " + cl.getGama());
            System.out.println("Marca: " + cl.getId_marca());
        } else {
            System.out.println("No existe un cliente con ese ID");
        }
        return cl;
    }
    public void menu() {
        int op;
        do {
            System.out.println("""
            ==========================================
                    GESTION DE CELULARES 
            ==========================================
            1. Registrar Celular
            2. Listar Celular
            3. Buscar por ID
            4. Actualizar Celular
            5. Eliminar Celular
            6. Regresar
            ==========================================
            """);

            System.out.print("Seleccione una opción: ");
            op = new Scanner(System.in).nextInt();

            while (op < 1 || op > 6) {
                System.out.println("Error, opción no válida");
                System.out.print("Seleccione una opción: ");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    guardar();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    actualizar();
                    break;
                case 5:
                    eliminar();
                    break;

            }

        } while (op != 6);
    }
    
}
