/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ar.angel.aplicaciontecnostore;

import CONTROLADOR.Conexion;
import CONTROLADOR.GestionarCelulares;

/**
 *
 * @author ANGEL
 */
public class AplicacionTecnoStore {

    public static void main(String[] args) {
        Conexion cone = new Conexion();
        cone.conectar();
        GestionarCelulares ges = new GestionarCelulares();
        
        ges.listar();
        
    }
}
