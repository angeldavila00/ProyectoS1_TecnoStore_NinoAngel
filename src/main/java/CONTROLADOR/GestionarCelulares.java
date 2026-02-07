package CONTROLADOR;

import MODELO.celulares;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author ANGEL
 */
public class GestionarCelulares {

    private ArrayList<celulares> celularesList = new ArrayList<>();
    private final String ARCHIVO = "celulares.dat";
    
    //Cargar
    //Permanencia de datos
    public void cargar(){
        File f = new File(ARCHIVO);
        if(!f.exists()){
            return;
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))){
            celularesList =(ArrayList<celulares>) ois.readObject();
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    //Guardar
    public void guardar(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))){
            oos.writeObject(celularesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //constructor 
    public GestionarCelulares(){
        cargar();
    }
    
    //Administrar Lista
    public void registrar(celulares c){
        celularesList.add(c);
    }
    
    //Listar 
    public void listar(){
        if(celularesList.isEmpty()){
            System.out.println("No hay celulares...");
            return;
        }
        for(celulares c: celularesList){
            System.out.println(c);
            
        }
    }
    
    //Buscar
    
    public void buscar(String nombre){
        for(celulares c: celularesList){
            if(c.getId_marca().getNombre().equalsIgnoreCase(nombre)){
                System.out.println(c);
                
            }
            
        }
        
    }
    
    public void eliminar(int id){
        boolean eliminado = celularesList.removeIf(c -> c.getId() == id);
        
        if(eliminado){
            guardar();
            System.out.println("Celular eliminado...");
        }else{
            System.out.println("NO EXISTE EL CELULAR CON ESE "+id);
        }  
    }
    
    //Backup
    public void exportarBackup(){
        JFileChooser j = new JFileChooser();
        j.setDialogTitle("Escoja la ruta a guardar");
        int op =j.showSaveDialog(j); //Guardar dialogo
        if(op==JFileChooser.APPROVE_OPTION){
            File destino = j.getSelectedFile();
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(destino))){
                oos.writeObject(celularesList);
                System.out.println("Base de datos exportada correctamente");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
    
    
    
    
    
    

}
