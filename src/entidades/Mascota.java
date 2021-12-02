
package entidades;

import java.util.ArrayList;

public class Mascota {
    
    private String nombre;
    private String tipo;
    private String raza;
    private String fechaNacimiento;
    private String foto;
    private Dueño dueño;
    private int contador = 0;
    private int codigo;

    public Mascota(String nombre, String tipo, String raza, String fechaNacimiento, String foto, Dueño dueño) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.foto = foto;
        this.dueño = dueño;
        codigo = ++contador;
    }

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
    
    
    
}
