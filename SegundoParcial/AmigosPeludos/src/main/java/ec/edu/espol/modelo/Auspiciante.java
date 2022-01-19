
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

public class Auspiciante implements Serializable{
    
    private String nombre;
    private String direccion;
    private String telefono;
    private Ciudad ciudad;
    private String email;
    private String WebPage;
    private int codigo;

    //Definiendo Auspiciante con sus datos
    public Auspiciante(int codigo, String nombre, String direccion, String telefono, Ciudad ciudad, String email, String WebPage) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.email = email;
        this.WebPage = WebPage;
        this.codigo = codigo;
    }
    
    
    //Definicion de setters y getters
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebPage() {
        return WebPage;
    }

    public void setWebPage(String WebPage) {
        this.WebPage = WebPage;
    }

    public int getCodigo() {
        return codigo;
    }
    
    //Toma los datos del auspiciante previamente registrado
    public static Auspiciante getAuspiciante(ArrayList<Auspiciante> auspiciantes, String codigo){
        for (Auspiciante auspiciante : auspiciantes) {
            if (codigo.equals(""+auspiciante.getCodigo()))//El codigo se lo pasa a String para mejor manejo de validacion
                return auspiciante;
        }        
        return null;
    }

    @Override
    public String toString() {
        return nombre ;
    }
    
    public static ArrayList<Auspiciante> cargar(){
        ArrayList<Auspiciante> auspiciantes = new ArrayList<>();
        try(ObjectInputStream objlista = new ObjectInputStream(new FileInputStream(App.path+"Auspiciantes.ser"))){
                auspiciantes = (ArrayList<Auspiciante>) objlista.readObject();
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        return auspiciantes;
    }
    
    public static void guardar(ArrayList<Auspiciante> auspiciantes){
        try(ObjectOutputStream objlista = new ObjectOutputStream(new FileOutputStream(App.path+"Auspiciantes.ser"))){
                    objlista.writeObject(auspiciantes); 
                    objlista.flush();   

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
        
        try(BufferedWriter objlista = new BufferedWriter(new FileWriter(App.path+"Auspiciantes.txt"))){
            for(Auspiciante au: auspiciantes){
                objlista.write(String.valueOf(au.codigo)+";");
                objlista.write(au.nombre+";");
                objlista.write(au.ciudad.toString()+";");
                objlista.write(au.telefono+";");
                objlista.write(au.email+";");
                objlista.write(au.direccion+";");
                objlista.write(au.WebPage);
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
        final Auspiciante other = (Auspiciante) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
   
    
}
