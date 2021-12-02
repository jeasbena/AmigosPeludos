
package lugares;

public class Ciudad {
    
    private String nombre;
    private String provincia;
    private static int contador = 0;
    private int codigo;

    public Ciudad(String nombre, String provincia) {
        this.nombre = nombre;
        this.provincia = provincia;
        codigo = ++contador;
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
    
    
    
}
