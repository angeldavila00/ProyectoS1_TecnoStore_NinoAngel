package VISTA;

import MODELO.Celulares;
import MODELO.Clientes;
import MODELO.DetalleVentas;
import MODELO.Ventas;
import PATRON.DescuentoPorMonto;
import PATRON.StrategyDescuento;
import PERSISTENCIA.CelularesDAO;
import PERSISTENCIA.ClientesDAO;
import PERSISTENCIA.DetalleVentasDAO;
import PERSISTENCIA.Implemetar.CelularesDAOImpl;
import PERSISTENCIA.Implemetar.ClientesDAOImlp;
import PERSISTENCIA.Implemetar.DetalleVentasDAOImpl;
import PERSISTENCIA.Implemetar.VentasDAOImpl;
import PERSISTENCIA.VentasDAO;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuVenta {

    private VentasDAO ventaDAO = new VentasDAOImpl();
    private CelularesDAO celularDAO = new CelularesDAOImpl();
    private DetalleVentasDAO detalleDAO = new DetalleVentasDAOImpl();
    private ClientesDAO clientesDAO = new ClientesDAOImlp();

    private void guardar() {
        Ventas v = new Ventas();
        for (Clientes cl : clientesDAO.listar()) {
            System.out.println(cl);
        }

        System.out.println("Ingrese el numero del cliente: ");
        int idCliente = new Scanner(System.in).nextInt();

        System.out.println("Ingresa la fecha de compra: ");
        String fecha_venta = new Scanner(System.in).nextLine();

        Clientes cl = new Clientes();
        cl.setId(idCliente);
        v.setId_cliente(cl);

        for (Celulares celu : celularDAO.listar()) {
            System.out.println(celu);
        }

        System.out.println("ingrese el numero del celular");
        int idcel = new Scanner(System.in).nextInt();

        Celulares celu = celularDAO.buscar(idcel);

        System.out.println("Ingrese cuantos celulares compro: ");
        int cantidad = new Scanner(System.in).nextInt();

        if (celu.getStock() < cantidad) {
            System.out.println("No tenemos stock de el dispositivo");
            return;
        }

        double precio = celu.getPrecio();
        double subtotal = detalleDAO.calcularSubtotal(cantidad, precio);
        
        ArrayList<Celulares> lista = new ArrayList<>();
        lista.add(celu);
        
        //creamos el desceunto de la Strategy
        StrategyDescuento estrategia = new DescuentoPorMonto();
        //Aplicamos el descuento
        double subtotalconDescuento = estrategia.aplicar(subtotal, lista);
        
        System.out.println(estrategia.mensaje());
        
        double total = ventaDAO.calculartotal(subtotalconDescuento);

        v.setSubtotal_Iva(subtotalconDescuento);
        
        v.setSubtotal_Iva(total);
        v.setFecha_venta(fecha_venta);

        int idVenta = ventaDAO.guardar(v);
        v.setId(idVenta);

        DetalleVentas dv = new DetalleVentas();
        dv.setId_venta(v);
        dv.setId_celular(celu);
        dv.setCantidad(cantidad);
        
        
        dv.setSubtotal(subtotalconDescuento);

        celularDAO.descontarStock(celu.getId(), cantidad);
        detalleDAO.guardar(dv);
    }

    private void listar() {
        ArrayList<DetalleVentas> dvList = detalleDAO.listar();
        if (dvList.isEmpty()) {
            System.out.println("No hay ventas registradas...");
            return;
        }
        dvList.forEach(n -> System.out.println(n));
    }

    public void actualizar() {
        ArrayList<DetalleVentas> dvList = detalleDAO.listar();
        if (dvList.isEmpty()) {
            return;
        }

        dvList.forEach(x -> System.out.println(x));

        DetalleVentas dv = null;
        Ventas v = null;

        do {
            System.out.println("Ingrese el ID de la venta a actualizar:");
            int idVenta = new Scanner(System.in).nextInt();
            dv = detalleDAO.buscar(idVenta);

            if (dv == null) {
                System.out.println("No existe la venta que busca");
            } else {
                v = ventaDAO.buscar(idVenta);
            }
        } while (dv == null);

        Celulares celu = dv.getId_celular();
        int cantidad = dv.getCantidad();

        int op;
        do {
            System.out.println("""
                               ===============================
                               SELECIONA LA OPCION A ACTUALIZAR
                               ===============================
                               1.   Actualizar el cliente
                               2.   Actualizar celular
                               3.   Actualizar cantidad
                               4.   Regresar al menu 
                               ===============================
                               """);
            op = new Scanner(System.in).nextInt();

            while (op < 1 || op > 4) {
                System.out.println("opcion invalida...");
                op = new Scanner(System.in).nextInt();
            }

            switch (op) {

                case 1:
                    for (Clientes cli : clientesDAO.listar()) {
                        System.out.println(cli);
                    }

                    System.out.println("Ingrese el ID del cliente NUEVO: ");
                    int idCliente = new Scanner(System.in).nextInt();

                    Clientes nuevoCliente = new Clientes();
                    nuevoCliente.setId(idCliente);
                    v.setId_cliente(nuevoCliente);

                    ventaDAO.actualizar(v, v.getId());

                    System.out.println("Cliente Actualizado");
                    break;

                case 2:
                    for (Celulares c : celularDAO.listar()) {
                        System.out.println(c);
                    }

                    System.out.println("Ingrese el nuevo ID del celular:");
                    int idCelularNuevo = new Scanner(System.in).nextInt();

                    Celulares celNuevo = celularDAO.buscar(idCelularNuevo);

                    if (celNuevo == null || celNuevo.getId() == 0) {
                        System.out.println("El celular ingresado no existe");
                        break;
                    }

                    celularDAO.descontarStock(celu.getId(), cantidad);

                    if (celNuevo.getStock() < cantidad) {
                        System.out.println("No hay stock suficiente del nuevo celular");
                        celularDAO.descontarStock(celu.getId(), -cantidad);
                        break;
                    }

                    double subtotalNuevo2 = detalleDAO.calcularSubtotal(cantidad, celNuevo.getPrecio());
                    double totalNuevo2 = ventaDAO.calculartotal(subtotalNuevo2);

                    dv.setId_celular(celNuevo);
                    dv.setSubtotal(subtotalNuevo2);

                    v.setSubtotal_Iva(totalNuevo2);
                    v.setSubtotal_sin_Iva(subtotalNuevo2);

                    detalleDAO.actualizar(dv, dv.getId());
                    ventaDAO.actualizar(v, v.getId());

                    celularDAO.descontarStock(celNuevo.getId(), cantidad);

                    celu = celNuevo;

                    System.out.println("Celular actualizado correctamente.");
                    break;

                case 3:
                    System.out.println("Ingrese la nueva cantidad:");
                    int nuevaCantidad = new Scanner(System.in).nextInt();

                    Celulares celBD = celularDAO.buscar(celu.getId());

                    int stockDisponible = celBD.getStock() + cantidad;

                    if (stockDisponible < nuevaCantidad) {
                        System.out.println("No hay stock suficiente");
                        break;
                    }

                    int diferencia = nuevaCantidad - cantidad;

                    if (diferencia > 0) {
                        celularDAO.descontarStock(celu.getId(), diferencia);
                    } else if (diferencia < 0) {
                        celularDAO.descontarStock(celu.getId(), -diferencia);
                    }

                    double subtotalNuevo3 = detalleDAO.calcularSubtotal(nuevaCantidad, celBD.getPrecio());
                    double totalNuevo3 = ventaDAO.calculartotal(subtotalNuevo3);

                    dv.setCantidad(nuevaCantidad);
                    dv.setSubtotal(subtotalNuevo3);

                    v.setSubtotal_Iva(totalNuevo3);
                    v.setSubtotal_sin_Iva(subtotalNuevo3);

                    cantidad = nuevaCantidad;
                    celu = celBD;

                    detalleDAO.actualizar(dv, dv.getId());
                    ventaDAO.actualizar(v, v.getId());

                    System.out.println("stock actualizado");
                    break;

                case 4:
                    System.out.println("Volver");
                    break;
            }

        } while (op != 4);
    }

    private void eliminar() {
        ArrayList<DetalleVentas> ventas = detalleDAO.listar();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas...");
            return;
        }

        ventas.forEach(x -> System.out.println(x));

        System.out.println("Ingrese el numero de la venta a eliminar:");
        int id = new Scanner(System.in).nextInt();

        DetalleVentas dv = detalleDAO.buscar(id);
        Ventas v = ventaDAO.buscar(id);

        detalleDAO.eliminar(id);
        ventaDAO.eliminar(id);

        System.out.println("VENTA ELIMINADA CORRECTAMENTE!");
    }

    public void menuventa() {
        int op;
        do {
            System.out.println("""
                               =====Menu Venta====
                               -------------------
                               1. Registrar venta
                               2. Listar ventas
                               3. Actualizar venta
                               4. Eliminar venta
                               5. Salir
                               -------------------
                               """);
            System.out.print("Seleccione una opción: ");
            op = new Scanner(System.in).nextInt();

            while (op < 1 || op > 5) {
                System.out.println("Error opcion no valida");
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
                    actualizar();
                    break;
                case 4:
                    eliminar();
                    break;

            }

        } while (op != 5);
    }
}
