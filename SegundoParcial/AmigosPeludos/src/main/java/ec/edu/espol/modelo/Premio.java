/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.modelo;

import ec.edu.espol.modelo.Auspiciante;
import java.io.Serializable;

/**
 *
 * @author USER
 */
public class Premio implements Serializable{
    private int lugar;
    private String descripcion;
    private Auspiciante auspiciante;

    public Premio(int lugar, String descripcion, Auspiciante auspiciante) {
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.auspiciante = auspiciante;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Auspiciante getAuspiciante() {
        return auspiciante;
    }

    public void setAuspiciante(Auspiciante auspiciante) {
        this.auspiciante = auspiciante;
    }
    
}
