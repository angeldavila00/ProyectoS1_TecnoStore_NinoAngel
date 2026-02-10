package ViSTA;

import MODELO.Clientes;
import PERSISTENCIA.ClientesDAO;
import PERSISTENCIA.Implemetar.ClientesDAOImlp;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ANGEL
 */

public class MenuCliente {

    private ClientesDAO clientesDAO = new ClientesDAOImlp();
    

    private void guardar() {
        Clientes cl = new Clientes();
        System.out.println("\n==== REGISTRAR CLIENTE=====");
        System.out.print("Ingrese el nombre: ");
        String nombre = new Scanner(System.in).nextLine();
        System.out.print("Ingrese la identificación: ");
        String identificacion = new Scanner(System.in).nextLine();
        System.out.print("Ingrese el correo: ");
        String correo = new Scanner(System.in).nextLine();
        System.out.print("Ingrese el teléfono: ");
        String telefono = new Scanner(System.in).nextLine();
        clientesDAO.guardar(cl);

    }

    //Actualizar
    private void actualizar() {
        System.out.print("Ingrese el ID del cliente a actualizar: ");
        int id = new Scanner(System.in).nextInt();
        Clientes cl = clientesDAO.buscar(id);
        System.out.println("""
        ¿Qué desea modificar?
        1. Nombre
        2. Identificación
        3. Correo
        4. Teléfono
        """);
        System.out.print("Opción: ");
        int op = new Scanner(System.in).nextInt();
        
        while (op < 1 || op > 4) {
            System.out.println("Error, opcion no valida");
            op = new Scanner(System.in).nextInt();
        }
        switch (op) {
            case 1 -> {
                System.out.print("Ingrese el nuevo nombre: ");
                cl.setNombre(new Scanner(System.in).nextLine());
            }
            case 2 -> {
                System.out.print("Ingrese la nueva identificación: ");
                cl.setIdentificacion(new Scanner(System.in).nextLine());
            }
            case 3 -> {
                System.out.print("Ingrese el nuevo correo: ");
                String correo = new Scanner(System.in).nextLine();
                cl.setCorreo(correo);
            }
            case 4 -> {
                System.out.print("Ingrese el nuevo teléfono: ");
                cl.setTelefono(new Scanner(System.in).nextLine());
            }
            default -> {
                System.out.println("Opción inválida.");
                return;
            }
        }

        clientesDAO.actualizar(cl, id);
        System.out.println("Cliente actualizado correctamente.");
    }

    //listar
    private void listar() {
        System.out.println("\n===LISTADO DE CLIENTES ===");
        ArrayList<Clientes> clientes = clientesDAO.listar();
        for (Clientes cl : clientes) {
            System.out.println(cl);
        }
    }

    //Buscar
    private void buscar() {
        System.out.print("\nIngrese el ID del cliente a buscar: ");
        int id = new Scanner(System.in).nextInt();

        Clientes cl = clientesDAO.buscar(id);
        if (cl != null && cl.getId() != 0) {
            System.out.println("\nCLIENTE ENCONTRADO:");
            System.out.println("ID: " + cl.getId());
            System.out.println("Nombre: " + cl.getNombre());
            System.out.println("Identificación: " + cl.getIdentificacion());
            System.out.println("Correo: " + cl.getCorreo());
            System.out.println("Teléfono: " + cl.getTelefono());
        } else {
            System.out.println("No existe un cliente con ese ID");
        }
    }

    //Eliminar
    private void eliminar() {
        System.out.print("\nIngrese el ID del cliente a eliminar: ");
        int id = new Scanner(System.in).nextInt();
        clientesDAO.eliminar(id);
    }
    

    //Menu clientes
    public void menu() {
        int op;
        do {
            System.out.println("""
            ==========================================
                    GESTION DE CLIENTES 
            ==========================================
            1. Registrar Cliente
            2. Listar Clientes
            3. Buscar por ID
            4. Actualizar Cliente
            5. Eliminar Cliente
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
                case 1 ->
                    guardar();
                case 2 ->
                    listar();
                case 3 ->
                    buscar();
                case 4 ->
                    actualizar();
                case 5 ->
                    eliminar();
            }

        } while (op != 6);
    }

}

