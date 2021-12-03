
package entidades;

import java.util.ArrayList;
import lugares.Ciudad;

public class Auspiciante {
    
    private String nombre;
    private String direccion;
    private String telefono;
    private Ciudad ciudad;
    private String email;
    private String WebPage;
    private static int contador = 0;//Primer codigo de Aupiciante
    private int codigo;

    //Definiendo Auspiciante con sus datos
    public Auspiciante(String nombre, String direccion, String telefono, Ciudad ciudad, String email, String WebPage) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.email = email;
        this.WebPage = WebPage;
        codigo = ++contador;//Genera un codigo de identificacion para cada Auspiciante
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
    
    
}
