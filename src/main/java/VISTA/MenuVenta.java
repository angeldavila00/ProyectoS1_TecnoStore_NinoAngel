package VISTA;

import PERSISTENCIA.CelularesDAO;
import PERSISTENCIA.ClientesDAO;
import PERSISTENCIA.Implemetar.CelularesDAOImpl;
import PERSISTENCIA.Implemetar.ClientesDAOImlp;
import PERSISTENCIA.Implemetar.MarcasDAOImpl;
import PERSISTENCIA.Implemetar.VentasDAOImpl;
import PERSISTENCIA.MarcasDAO;
import PERSISTENCIA.VentasDAO;
import Servicios.GestionarVentas;

public class MenuVenta {

    VentasDAO gv = new VentasDAOImpl();
    private CelularesDAO celularDAO = new CelularesDAOImpl();
    private MarcasDAO marcaDAO = new MarcasDAOImpl();
    private ClientesDAO clientesDAO = new ClientesDAOImlp();

}
