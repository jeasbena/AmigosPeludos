
package entidades;

import lugares.Ciudad;

public class Auspiciante {
    
    private String nombre;
    private String direccion;
    private String telefono;
    private Ciudad ciudad;
    private String email;
    private String WebPage;
    private static int contador = 0;
    private int codigo;

    public Auspiciante(String nombre, String direccion, String telefono, Ciudad ciudad, String email, String WebPage) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.email = email;
        this.WebPage = WebPage;
        codigo = ++contador;
    }
    
    

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
    
    
}
