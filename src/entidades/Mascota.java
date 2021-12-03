
package entidades;

import java.util.ArrayList;
import java.util.Arrays;

public class Mascota {
    
    private String nombre;
    private String tipo;
    private String raza;
    private String fechaNacimiento;
    private String foto;
    private Dueño dueño;
    private static int contador = 0;
    private int codigo;
    public static ArrayList<String> TIPOS = new ArrayList<>(Arrays.asList("PERRO", "GATO"));

    public Mascota(String nombre, String tipo, String raza, String fechaNacimiento, String foto, Dueño dueño) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
        this.dueño = dueño;
        codigo = ++contador; //Genera un codigo de identificacion para cada Mascota
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
    
    
}
