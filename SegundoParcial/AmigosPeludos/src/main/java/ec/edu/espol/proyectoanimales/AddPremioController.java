/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectoanimales;

import ec.edu.espol.modelo.Auspiciante;
import ec.edu.espol.modelo.Ciudad;
import ec.edu.espol.modelo.Premio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AddPremioController implements Initializable {
    private ArrayList<String> infoTEXT;
    private ArrayList<LocalDate> infoDATE;
    private ArrayList<Auspiciante> auspiciantes;
    private String dirigido;
    private Ciudad ciudad;
    private ArrayList<Premio> premios;

    @FXML
    private TextField lugar;
    @FXML
    private TextField descripcion;
    @FXML
    private ComboBox<Auspiciante> opAuspiciante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatos();
        opAuspiciante.getItems().clear();
        for (Auspiciante cd : auspiciantes){
            opAuspiciante.getItems().add(cd);
        }
        
    }    

    @FXML
    private void guardar(ActionEvent event) throws IOException {
        String lugarF = lugar.getText();
        String descripcionF = descripcion.getText();
        Auspiciante auspicianteF = (Auspiciante) opAuspiciante.getValue();
        if(lugarF.isEmpty() ||descripcionF.isEmpty() || auspicianteF==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Informacion de campo no ingresado");             
            vacio.setHeaderText("Campo sin llenar");
            vacio.setContentText("Llene todos los campos de registro");
            vacio.showAndWait();
            
        }else{
            boolean pase = true;
            for(Premio pre : premios){
                if(pre.getLugar()== Integer.valueOf(lugarF)){
                    pase = false;
                    Alert vacio= new Alert(Alert.AlertType.INFORMATION);
                    vacio.setTitle("Informacion de campo");             
                    vacio.setHeaderText("Ya existe un premio para ese lugar");
                    vacio.showAndWait();
                }
            }
            if(pase){
                premios.add(new Premio(Integer.valueOf(lugarF),descripcionF,auspicianteF));
                FXMLLoader ventana= new FXMLLoader(App.class.getResource("crearConcurso.fxml"));      
                Parent root =(Parent) ventana.load();
                CrearConcursoController c2 = ventana.getController();

                c2.opcionesEsp( infoDATE, infoTEXT, premios,ciudad, dirigido);
                
                Alert alerta= new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Guardado exitoso");  
                alerta.setHeaderText("Guardado exitoso");
                alerta.showAndWait();
                App.cambiaRoot(root);
            }
        }
        
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        FXMLLoader ventana= new FXMLLoader(App.class.getResource("crearConcurso.fxml"));      
        Parent root =(Parent) ventana.load();
        CrearConcursoController c2 = ventana.getController();

        c2.opcionesEsp( infoDATE, infoTEXT, premios,ciudad, dirigido);
        App.cambiaRoot(root);
    }
    

    public void setInfoTEXT(ArrayList<String> infoTEXT) {
        this.infoTEXT = infoTEXT;
    }


    public void setInfoDATE(ArrayList<LocalDate> infoDATE) {
        this.infoDATE = infoDATE;
    }

    public void setDirigido(String dirigido) {
        this.dirigido = dirigido;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    public void setPremios(ArrayList<Premio> premios) {
        this.premios = premios;
    }
    
    private void cargarDatos(){
        auspiciantes = Auspiciante.cargar();
    }
}
