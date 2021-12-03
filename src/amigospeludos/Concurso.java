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

    public Concurso(String nombre, String fechaEvento, String horaEvento, String inicioInscripcion, String cierreInscripcion, Ciudad ciudad, String lugar, ArrayList<String> premios, ArrayList<Auspiciante> auspiciantes, String tipoMascota, ArrayList<Mascota> mascotasInscritas, ArrayList<Mascota> ganadores) {
        this.nombre = nombre;
        this.fechaEvento = fechaEvento;
        this.horaEvento = horaEvento;
        this.inicioInscripcion = inicioInscripcion;
        this.cierreInscripcion = cierreInscripcion;
        this.ciudad = ciudad;
        this.lugar = lugar;
        this.premios = premios;
        this.auspiciantes = auspiciantes;
        this.tipoMascota = tipoMascota;
        this.mascotasInscritas = mascotasInscritas;
        this.ganadores = ganadores;
        codigo = ++contador;
    }
    //TOSTRING
    @Override
    public String toString() {
        return "\nCodigo: " + codigo
                + "\nNombre = " + nombre
                + "\nFechaEvento = " + fechaEvento + " HoraEvento = " + horaEvento
                + "\nCierreInscripcion = " + cierreInscripcion
                + "\nMascotas: " + mascotasInscritas;
    }
    //SETTERS Y GETTERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(String horaEvento) {
        this.horaEvento = horaEvento;
    }

    public String getInicioInscripcion() {
        return inicioInscripcion;
    }

    public void setInicioInscripcion(String inicioInscripcion) {
        this.inicioInscripcion = inicioInscripcion;
    }

    public String getCierreInscripcion() {
        return cierreInscripcion;
    }

    public void setCierreInscripcion(String cierreInscripcion) {
        this.cierreInscripcion = cierreInscripcion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public ArrayList<String> getPremios() {
        return premios;
    }

    public void setPremios(ArrayList<String> premios) {
        this.premios = premios;
    }

    public ArrayList<Auspiciante> getAuspiciantes() {
        return auspiciantes;
    }

    public void setAuspiciantes(ArrayList<Auspiciante> auspiciantes) {
        this.auspiciantes = auspiciantes;
    }

    public String getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(String tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public int getCodigo() {
        return codigo;
    }

    public ArrayList<Mascota> getMascotasInscritas() {
        return mascotasInscritas;
    }

    public void setMascotasInscritas(ArrayList<Mascota> mascotasInscritas) {
        this.mascotasInscritas = mascotasInscritas;
    }

    public ArrayList<Mascota> getGanadores() {
        return ganadores;
    }

    public void setGanadores(ArrayList<Mascota> ganadores) {
        this.ganadores = ganadores;
    }

    public static Concurso getConcursoDisponible(ArrayList<Concurso> concursos, String codigo) {
        for (Concurso concurso : concursos) {
            if (codigo.equals(""+concurso.getCodigo()) //el codigo se lo pasa a String para mejor manejo de validacion
                    && AmigosPeludos.compararFechas(concurso.getCierreInscripcion(), AmigosPeludos.FECHA_ACTUAL)) {
                return concurso;
            }
        }

        return null;
    }

}
