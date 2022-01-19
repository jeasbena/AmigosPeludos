/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectoanimales;

import ec.edu.espol.modelo.Concurso;
import ec.edu.espol.modelo.Mascota;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class InscribirController implements Initializable {
    private ArrayList<Concurso> concursos;
    private ArrayList<Mascota> mascotas;
    @FXML
    private ComboBox<Concurso> opConcurso;
    @FXML
    private ComboBox<Mascota> opMascota;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatos();
        
       
    }    

    @FXML
    private void inscribir(ActionEvent event) throws IOException {
        Concurso concursoF = (Concurso) opConcurso.getValue();
        Mascota mascotaF = (Mascota) opMascota.getValue();
        if(concursoF==null || mascotaF== null){
           App.alertaVacio();
        }else{
            concursos = Concurso.cargar();
            int i = concursos.indexOf(concursoF);
            if(!concursos.get(i).getMascotasInscritas().contains(mascotaF)){
                concursos.get(i).addMascota(mascotaF);
                Concurso.guardar(concursos);
            }
            Alert alerta= new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Guardado exitoso");  
            alerta.setHeaderText("Guardado exitoso");
            alerta.showAndWait();
            App.setRoot("inscribir");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    
    private void cargarDatos(){
        concursos = Concurso.cargar();
        ArrayList<Concurso> con = new ArrayList<>();
        for (Concurso c: concursos){
            String[] cierre = c.getCierreInscripcion().split("/");
            String[] fechaActual = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).split("/");
            int diasActual = Integer.valueOf(fechaActual[2])*365 + Integer.valueOf(fechaActual[1])*30 + Integer.valueOf(fechaActual[0]);
            int diasCierre = Integer.valueOf(cierre[2])*365 + Integer.valueOf(cierre[1])*30 + Integer.valueOf(cierre[0]);
            if(diasCierre<diasActual){
                con.add(c);
            }
        }
        for(Concurso c: con){
            concursos.remove(c);
        }
        mascotas = Mascota.cargar();
        for(Concurso c: concursos){
            opConcurso.getItems().add(c);
        }
        
    }

    @FXML
    private void filtro(ActionEvent event) {
        opMascota.getItems().clear();
        Concurso concursoF = (Concurso) opConcurso.getValue();
        if(concursoF!=null){
            boolean isPerros = concursoF.getTipoMascota().equals("Perros");
             for(Mascota c: mascotas){
                if(isPerros && c.getTipo().equals("Perro")){
                    opMascota.getItems().add(c);
                }
                else if(!isPerros && c.getTipo().equals("Gato")){
                    opMascota.getItems().add(c);
                }
            }
        }
    }
    
    
}
