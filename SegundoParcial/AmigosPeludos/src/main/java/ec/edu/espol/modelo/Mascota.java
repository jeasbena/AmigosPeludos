
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
import java.util.Arrays;
import java.util.Objects;

public class Mascota implements Serializable{
    
    private String nombre;
    private String tipo;
    private String raza;
    private String fechaNacimiento;
    private String foto;
    private Dueño dueño;
    private int codigo;

    public Mascota(int codigo, String nombre, String tipo, String raza, String fechaNacimiento, String foto, Dueño dueño) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
        this.dueño = dueño;
        this.codigo = codigo;
    }

    @Override //Sobre-escritura de toString
    public String toString() {
        return nombre;
    }
    
    //Se define getters y setters
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Dueño getDueño() {
        return dueño;
    }

    public void setDueño(Dueño dueño) {
        this.dueño = dueño;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public static Mascota getMascota(ArrayList<Mascota> mascotas, String codigo){
        for (Mascota mascota : mascotas) {
            if (codigo.equals(""+mascota.getCodigo()))
                return mascota;
        }
        
        return null;
    }
    
    public static Mascota getMascota(ArrayList<Mascota> mascotas, String codigo, String tipo){
        for (Mascota mascota : mascotas) {
            if ((codigo.equals(""+mascota.getCodigo())) && (mascota.getTipo().equals(tipo) //El codigo se lo pasa a String para mejor manejo de validacion
                    || tipo.equals("TODOS") || tipo.equals("TODO")))
                return mascota;
        }
        
        return null;
    }
    
    public static ArrayList<Mascota> cargar(){
        ArrayList<Mascota> mascotas = new ArrayList<>();
        try(ObjectInputStream objlista = new ObjectInputStream(new FileInputStream(App.path+"Mascotas.ser"))){
                mascotas = (ArrayList<Mascota>) objlista.readObject();
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        return mascotas;
    }
    
    public static void guardar(ArrayList<Mascota> mascotas){
        try(ObjectOutputStream objlista = new ObjectOutputStream(new FileOutputStream(App.path+"Mascotas.ser"))){
                    objlista.writeObject(mascotas); 
                    objlista.flush();   

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
        try(BufferedWriter objlista = new BufferedWriter(new FileWriter(App.path+"Mascotas.txt"))){
            for(Mascota au: mascotas){
                objlista.write(String.valueOf(au.codigo)+";");
                objlista.write(au.nombre+";");
                objlista.write(au.fechaNacimiento+";");
                objlista.write(au.raza+";");
                objlista.write(au.tipo+";");
                objlista.write(au.dueño.toString()+";");
                objlista.write(au.foto);
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
        final Mascota other = (Mascota) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.dueño, other.dueño)) {
            return false;
        }
        return true;
    }

    
}
