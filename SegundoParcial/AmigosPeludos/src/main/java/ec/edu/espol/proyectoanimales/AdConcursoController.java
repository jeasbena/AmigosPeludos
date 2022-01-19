/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectoanimales;

import ec.edu.espol.modelo.Ciudad;
import ec.edu.espol.modelo.Concurso;
import ec.edu.espol.modelo.Mascota;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AdConcursoController implements Initializable {
    private ArrayList<Concurso> concursos;
    @FXML
    private TableView losConcursos;
    @FXML
    private TableColumn<Concurso, Integer> colcodigo;
    @FXML
    private TableColumn<Concurso, Concurso> colnombre;
    @FXML
    private TableColumn<Concurso, String> colfecha;
    @FXML
    private TableColumn<Concurso, Ciudad> colciudad;
    @FXML
    private VBox eventos1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cargarDatos();
        colcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colfecha.setCellValueFactory(new PropertyValueFactory<>("fechaEvento"));
        colciudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        
        losConcursos.getItems().setAll(concursos);
    }    

    @FXML
    private void crearConcurso(ActionEvent event) throws IOException {
         App.setRoot("crearConcurso");
    }

    @FXML
    private void enviarInvitaciones(ActionEvent event) {
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    
    private void cargarDatos(){
        concursos = Concurso.cargar();
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
        Concurso obj = (Concurso) losConcursos.getSelectionModel().getSelectedItem();
        if(obj==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó el concurso.");
            vacio.showAndWait();
        }else{
            FXMLLoader ventana= new FXMLLoader(App.class.getResource("crearConcurso.fxml"));  
            Parent root =(Parent) ventana.load();
            CrearConcursoController c2 = ventana.getController();
            c2.editar(obj);
            App.cambiaRoot(root);
        }
    }

    @FXML
    private void eliminar(ActionEvent event) throws IOException {
        Concurso obj = (Concurso) losConcursos.getSelectionModel().getSelectedItem();
        if(obj==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó el concurso.");
            vacio.showAndWait();
        }else{
            Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Eliminar");             
            alerta.setHeaderText("Eliminar "+obj);
            alerta.setContentText("¿Esta seguro?");
            Optional<ButtonType> resultado = alerta.showAndWait();
            if(resultado.isPresent() && resultado.get()== ButtonType.OK){
                concursos.remove(obj);
                Concurso.guardar(concursos);
                App.setRoot("adConcurso");                
            }
        }
    }

    @FXML
    private void consultar(ActionEvent event) {
        Concurso obj = (Concurso) losConcursos.getSelectionModel().getSelectedItem();
        if(obj==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó el concurso.");
            vacio.showAndWait();
        }else{
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("Consulta de inscritos");
            vacio.setContentText(obj.getMascotasInscritas().toString());
            vacio.showAndWait();
        }
    }

    @FXML
    private void ganadores(ActionEvent event) {
    }
    
}
