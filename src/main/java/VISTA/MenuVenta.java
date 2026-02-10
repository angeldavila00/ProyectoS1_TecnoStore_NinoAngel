
package VISTA;

  

import MODELO.Clientes;
import MODELO.Celulares;
import MODELO.Ventas;
import PERSISTENCIA.CelularesDAO;
import PERSISTENCIA.ClientesDAO;
import PERSISTENCIA.Implemetar.CelularesDAOImpl;
import PERSISTENCIA.Implemetar.ClientesDAOImlp;
import PERSISTENCIA.Implemetar.VentasDAOImpl;
import PERSISTENCIA.VentasDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuVenta {

    private final ClientesDAO clientesDAO = new ClientesDAOImlp();
    private final CelularesDAO celularesDAO = new CelularesDAOImpl();
    private final VentasDAO ventasDAO = new VentasDAOImpl();

    private final Scanner sc = new Scanner(System.in);

    private static final double IVA = 0.19;

    // ==========================
    // 1) REGISTRAR (NUEVA VENTA)
    // ==========================
    private void registrar() {
        System.out.println("\n--- REGISTRAR VENTA ---");

        // Seleccionar cliente
        System.out.println("\nCLIENTES DISPONIBLES:");
        listarClientesSimple();

        System.out.print("Ingrese el ID del cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Clientes cliente = clientesDAO.buscar(idCliente);
        if (cliente == null || cliente.getId() == 0) {
            System.out.println("No existe un cliente con ese ID");
            return;
        }

        // Seleccionar celular
        System.out.println("\nCELULARES DISPONIBLES (con stock):");
        listarCelularesStockSimple();

        System.out.print("Ingrese el ID del celular: ");
        int idCelular = sc.nextInt();
        sc.nextLine();

        Celulares celular = celularesDAO.buscar(idCelular);
        if (celular == null || celular.getId() == 0) {
            System.out.println("No existe un celular con ese ID");
            return;
        }

        if (celular.getStock() <= 0) {
            System.out.println("El celular no tiene stock disponible");
            return;
        }

        // Cantidad
        System.out.print("Ingrese la cantidad: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0");
            return;
        }

        if (cantidad > celular.getStock()) {
            System.out.println("Stock insuficiente. Stock disponible: " + celular.getStock());
            return;
        }

        // Totales
        double subtotalSinIva = celular.getPrecio() * cantidad;
        double valorIva = subtotalSinIva * IVA;
        double totalConIva = subtotalSinIva + valorIva;

        // Resumen
        System.out.println("\nRESUMEN DE VENTA:");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Celular: " + celular.getModelo());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Subtotal: $" + String.format("%.2f", subtotalSinIva));
        System.out.println("IVA (19%): $" + String.format("%.2f", valorIva));
        System.out.println("TOTAL: $" + String.format("%.2f", totalConIva));

        System.out.print("\n¿Confirmar venta? (S/N): ");
        String conf = sc.nextLine();

        if (!conf.equalsIgnoreCase("S") && !conf.equalsIgnoreCase("SI")) {
            System.out.println("Venta cancelada.");
            return;
        }

        // Guardar venta (SIN detalle_ventas)
        String fechaVenta = LocalDate.now().toString();

        Ventas v = new Ventas();
        v.setId_cliente(cliente);
        v.setFecha_venta(fechaVenta);

        // En tu proyecto tú estabas usando:
        // subtotal_sin_Iva y subtotal_Iva (donde subtotal_Iva lo usas como TOTAL con IVA)
        v.setSubtotal_sin_Iva(subtotalSinIva);
        v.setSubtotal_Iva(totalConIva);

        ventasDAO.guardar(v);

        // Descontar stock
        boolean ok = celularesDAO.descontarStock(idCelular, cantidad);
        if (ok) {
            System.out.println("\nVenta registrada y stock actualizado correctamente!");
        } else {
            System.out.println("\nVenta registrada, pero NO se pudo actualizar el stock.");
        }
    }

    // ==========================
    // 2) LISTAR VENTAS
    // ==========================
    private void listar() {
        System.out.println("\n--- LISTADO DE VENTAS ---");

        ArrayList<Ventas> ventas = ventasDAO.listar();
        if (ventas == null || ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        for (Ventas v : ventas) {
            System.out.println("\nVENTA #" + v.getId());
            System.out.println("Fecha: " + v.getFecha_venta());
            System.out.println("Cliente: " + (v.getId_cliente() != null ? v.getId_cliente().getNombre() : "N/A"));
            System.out.println("Subtotal: $" + String.format("%.2f", v.getSubtotal_sin_Iva()));
            System.out.println("Total (con IVA): $" + String.format("%.2f", v.getSubtotal_Iva()));
        }

        System.out.println("\n⚠️ Nota: aquí NO se muestran productos porque no estás usando detalle_ventas.");
    }

    // ==========================
    // 3) BUSCAR POR ID
    // ==========================
    private void buscar() {
        System.out.print("\nIngrese el ID de la venta a buscar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Ventas v = ventasDAO.buscar(id);
        if (v != null && v.getId() != 0) {
            System.out.println("\nVENTA ENCONTRADA:");
            System.out.println("ID: " + v.getId());
            System.out.println("Fecha: " + v.getFecha_venta());
            System.out.println("Cliente: " + (v.getId_cliente() != null ? v.getId_cliente().getNombre() : "N/A"));
            System.out.println("Subtotal: $" + String.format("%.2f", v.getSubtotal_sin_Iva()));
            System.out.println("Total (con IVA): $" + String.format("%.2f", v.getSubtotal_Iva()));
            System.out.println("\n⚠️ Nota: no hay detalle de productos sin detalle_ventas.");
        } else {
            System.out.println("No existe una venta con ese ID");
        }
    }

    // ==========================
    // 4) VENTAS POR CLIENTE
    // ==========================
    private void ventasPorCliente() {
        System.out.println("\n--- VENTAS POR CLIENTE ---");

        System.out.println("\nCLIENTES DISPONIBLES:");
        listarClientesSimple();

        System.out.print("Ingrese el ID del cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Clientes c = clientesDAO.buscar(idCliente);
        if (c == null || c.getId() == 0) {
            System.out.println("No existe un cliente con ese ID");
            return;
        }

        ArrayList<Ventas> ventas = ventasDAO.listar();
        if (ventas == null || ventas.isEmpty()) {
            System.out.println("El cliente no tiene ventas registradas.");
            return;
        }

        System.out.println("\nVentas de: " + c.getNombre());
        for (Ventas v : ventas) {
            System.out.println(" - Venta #" + v.getId() + " | Fecha: " + v.getFecha_venta()
                    + " | Total: $" + String.format("%.2f", v.getSubtotal_Iva()));
        }
    }

    // ==========================
    // 5) REPORTE DEL DÍA
    // ==========================
    private void ventasDelDia() {
        System.out.println("\n--- REPORTE DE VENTAS DEL DÍA ---");

        String hoy = LocalDate.now().toString();
        ArrayList<Ventas> ventas = ventasDAO.listar();

        if (ventas == null || ventas.isEmpty()) {
            System.out.println("No hay ventas registradas el día de hoy.");
            return;
        }

        double totalDia = 0;
        double totalIva = 0;

        for (Ventas v : ventas) {
            double total = v.getSubtotal_Iva();
            double subtotal = v.getSubtotal_sin_Iva();
            totalDia += total;
            totalIva += (total - subtotal);

            System.out.println(" - Venta #" + v.getId()
                    + " | Cliente: " + (v.getId_cliente() != null ? v.getId_cliente().getNombre() : "N/A")
                    + " | Total: $" + String.format("%.2f", total));
        }

        System.out.println("\nTOTAL DEL DÍA: $" + String.format("%.2f", totalDia));
        System.out.println("IVA RECAUDADO: $" + String.format("%.2f", totalIva));
    }

    // ==========================
    // 6) ELIMINAR VENTA
    // ==========================
    private void eliminar() {
        System.out.print("\nIngrese el ID de la venta a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        Ventas v = ventasDAO.buscar(id);
        if (v == null || v.getId() == 0) {
            System.out.println("No existe una venta con ese ID");
            return;
        }

        System.out.println("\nVENTA A ELIMINAR:");
        System.out.println("ID: " + v.getId());
        System.out.println("Fecha: " + v.getFecha_venta());
        System.out.println("Total: $" + String.format("%.2f", v.getSubtotal_Iva()));

        System.out.print("\n¿Confirmar eliminación? (S/N): ");
        String conf = sc.nextLine();

        if (!conf.equalsIgnoreCase("S") && !conf.equalsIgnoreCase("SI")) {
            System.out.println("Eliminación cancelada.");
            return;
        }

        ventasDAO.eliminar(id);
        System.out.println("Venta eliminada correctamente.");
    }

    // ==========================
    // MENÚ PRINCIPAL
    // ==========================
    public void menu() {
        int op;
        do {
            System.out.println("""
            ==========================================
                    GESTION DE VENTAS (TECNOSTORE)
            ==========================================
            1. Registrar Venta
            2. Listar Ventas
            3. Buscar Venta por ID
            4. Ventas por Cliente
            5. Reporte Ventas del Día
            6. Eliminar Venta
            7. Regresar
            ==========================================
            """);

            System.out.print("Seleccione una opcion: ");
            op = sc.nextInt();
            sc.nextLine();

            while (op < 1 || op > 7) {
                System.out.println("Error, opcion no valida");
                System.out.print("Seleccione una opcion: ");
                op = sc.nextInt();
                sc.nextLine();
            }

            switch (op) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> buscar();
                case 4 -> ventasPorCliente();
                case 5 -> ventasDelDia();
                case 6 -> eliminar();
            }

        } while (op != 7);
    }

    // ==========================
    // HELPERS DE LISTADO SIMPLE
    // ==========================
    private void listarClientesSimple() {
        ArrayList<Clientes> clientes = clientesDAO.listar();
        if (clientes == null || clientes.isEmpty()) {
            System.out.println("(No hay clientes)");
            return;
        }
        for (Clientes c : clientes) {
            System.out.println(c.getId() + " - " + c.getNombre() + " | " + c.getIdentificacion());
        }
    }

    private void listarCelularesStockSimple() {
        ArrayList<Celulares> celulares = celularesDAO.listar();
        if (celulares == null || celulares.isEmpty()) {
            System.out.println("(No hay celulares)");
            return;
        }
        for (Celulares c : celulares) {
            if (c.getStock() > 0) {
                System.out.println(c.getId() + " - " + c.getModelo()
                        + " | $" + String.format("%.2f", c.getPrecio())
                        + " | Stock: " + c.getStock());
            }
        }
    }
}


    

    



