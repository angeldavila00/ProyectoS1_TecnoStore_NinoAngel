
package ar.angel.aplicaciontecnostore;

import PERSISTENCIA.Conexion;
import VISTA.Menu;



public class AplicacionTecnoStore {
    public static void main(String[] args){
    Menu m = new Menu();
    m.Menu_Principal();
    
    Conexion cn =  new Conexion();
    cn.conectar();
    }
    
    
    
}
