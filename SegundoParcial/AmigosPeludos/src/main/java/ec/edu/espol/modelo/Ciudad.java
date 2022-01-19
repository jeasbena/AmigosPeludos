
package ec.edu.espol.modelo;

import ec.edu.espol.proyectoanimales.App;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Ciudad implements Serializable{
    
    private String nombre;
    private String provincia;
    private int codigo;

    public Ciudad(int codigo, String nombre, String provincia) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public static Ciudad getCiudad(ArrayList<Ciudad> ciudades, String codigo){
        for (Ciudad ciudad : ciudades) {
            if (codigo.equals(""+ciudad.getCodigo()))
                return ciudad;
        }
        
        return null;
    }

    @Override
    public String toString() {
        return  nombre ;
    }
    public static ArrayList<Ciudad> cargar(){
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        try(ObjectInputStream objlista = new ObjectInputStream(new FileInputStream(App.path+"Ciudades.ser"))){
                ciudades = (ArrayList<Ciudad>) objlista.readObject();
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        return ciudades;
    }
    
    public static void guardar(ArrayList<Ciudad> ciudades){
        try(ObjectOutputStream objlista = new ObjectOutputStream(new FileOutputStream(App.path+"Ciudades.ser"))){
                    objlista.writeObject(ciudades); 
                    objlista.flush();   

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
        try(BufferedWriter objlista = new BufferedWriter(new FileWriter(App.path+"Ciudades.txt"))){
            for(Ciudad au: ciudades){
                objlista.write(String.valueOf(au.codigo)+";");
                objlista.write(au.nombre+";");
                objlista.write(au.provincia);
                objlista.newLine();
            }
            objlista.flush();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ciudad other = (Ciudad) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    
    
    
}
