
package amigospeludos;

import entidades.Auspiciante;
import entidades.Mascota;
import java.util.ArrayList;
import lugares.Ciudad;

public class Concurso {
    private String nombre;
    private String fechaEvento;
    private String horaEvento;
    private String inicioInscripcion;
    private String cierreInscripcion;
    private Ciudad ciudad;
    private String lugar;
    private ArrayList<String> premios;
    private ArrayList<Auspiciante> auspiciantes;
    private String tipoMascota;
    private static int contador = 0;
    private int codigo;
    private ArrayList<Mascota> mascotasInscritas;
    private ArrayList<Mascota> ganadores;
    
    
}
