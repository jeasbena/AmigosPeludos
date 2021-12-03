package entidades;

import java.util.ArrayList;
import lugares.Ciudad;

public class Dueño {

    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private Ciudad ciudad;
    private String email;

    public Dueño(String cedula, String nombres, String apellidos, String direccion, String telefono, Ciudad ciudad, String email) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override  //Sobre-escritura de toString
    public String toString() {
        return "\nCedula = " + cedula + "\nNombre = " + nombres + " " + apellidos + "\nEmail = " + email;
    }
    
    
    public static Dueño getDueño(ArrayList<Dueño> dueños, String cedula){
        for (Dueño dueño : dueños) {
            if (cedula.equals(dueño.getCedula()))
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

}
