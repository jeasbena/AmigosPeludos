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

public class Concurso implements Serializable{

    private String nombre;
    private String fechaEvento;
    private String horaEvento;
    private String inicioInscripcion;
    private String cierreInscripcion;
    private Ciudad ciudad;
    private String lugar;
    private ArrayList<Premio> premios;
    private ArrayList<Auspiciante> auspiciantes;
    private String tipoMascota;
    private int codigo;
    private ArrayList<Mascota> mascotasInscritas;
    private ArrayList<Mascota> ganadores;

    public Concurso(int codigo, String nombre, String fechaEvento, String horaEvento, String inicioInscripcion, 
            String cierreInscripcion, Ciudad ciudad, String lugar,  ArrayList<Premio> premios, ArrayList<Auspiciante> auspiciantes, 
            String tipoMascota) {
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
        this.codigo = codigo;
        mascotasInscritas = new ArrayList<>();
        ganadores = new ArrayList<>();
    }
    //TOSTRING
    @Override
    public String toString() {
        return nombre;
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

    public ArrayList<Premio> getPremios() {
        return premios;
    }

    public void setPremios(ArrayList<Premio> premios) {
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
    public void agregarPremio(Premio pre){
        this.premios.add(pre);
    }
    public void addMascota(Mascota m){
        if(!mascotasInscritas.contains(m)){
            mascotasInscritas.add(m);
        }
    }
    
    public static ArrayList<Concurso> cargar(){
        ArrayList<Concurso> concursos = new ArrayList<>();
        try(ObjectInputStream objlista = new ObjectInputStream(new FileInputStream(App.path+"Concursos.ser"))){
                concursos = (ArrayList<Concurso>) objlista.readObject();
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        return concursos;
    }
    
    public static void guardar(ArrayList<Concurso> concursos){
        try(ObjectOutputStream objlista = new ObjectOutputStream(new FileOutputStream(App.path+"Concursos.ser"))){
                    objlista.writeObject(concursos); 
                    objlista.flush();   

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
        try(BufferedWriter objlista = new BufferedWriter(new FileWriter(App.path+"Concursos.txt"))){
            for(Concurso au: concursos){
                objlista.write(String.valueOf(au.codigo)+";");
                objlista.write(au.nombre+";");
                objlista.write(au.fechaEvento+";");
                objlista.write(au.horaEvento+";");
                objlista.write(au.inicioInscripcion+";");
                objlista.write(au.cierreInscripcion+";");
                objlista.write(au.lugar+";");
                objlista.write(au.tipoMascota+";");
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
        final Concurso other = (Concurso) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }


    

}
