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

public class Dueño implements Serializable{
    private int codigo;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private Ciudad ciudad;
    private String email;

    public Dueño(int codigo, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.email = email;
    }



    @Override  //Sobre-escritura de toString
    public String toString() {
        return nombres + " " + apellidos;
    }
    
    
    public static Dueño getDueño(ArrayList<Dueño> dueños, String email){
        for (Dueño dueño : dueños) {
            if (email.equals(dueño.getEmail()))
                return dueño;
        }
        
        return null;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
    public static ArrayList<Dueño> cargar(){
        ArrayList<Dueño> dueños = new ArrayList<>();
        try(ObjectInputStream objlista = new ObjectInputStream(new FileInputStream(App.path+"Dueños.ser"))){
                dueños = (ArrayList<Dueño>) objlista.readObject();
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        return dueños;
    }
    
    public static void guardar(ArrayList<Dueño> dueños){
        try(ObjectOutputStream objlista = new ObjectOutputStream(new FileOutputStream(App.path+"Dueños.ser"))){
                    objlista.writeObject(dueños); 
                    objlista.flush();   

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
        try(BufferedWriter objlista = new BufferedWriter(new FileWriter(App.path+"Dueños.txt"))){
            for(Dueño au: dueños){
                objlista.write(String.valueOf(au.codigo)+";");
                objlista.write(au.nombres+";");
                objlista.write(au.apellidos+";");
                objlista.write(au.email+";");
                objlista.write(au.direccion+";");
                objlista.write(au.telefono+";");
                objlista.write(au.ciudad.toString());
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
        final Dueño other = (Dueño) obj;
        if (!Objects.equals(this.nombres, other.nombres)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        return true;
    }


    
}
