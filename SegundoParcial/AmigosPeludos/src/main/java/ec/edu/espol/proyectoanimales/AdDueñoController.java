/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectoanimales;

import ec.edu.espol.modelo.Ciudad;
import ec.edu.espol.modelo.Dueño;
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
public class AdDueñoController implements Initializable {
    private ArrayList<Dueño> dueños;
    
    @FXML
    private TableView losDueños;
    @FXML
    private TableColumn<Dueño, Integer> colcodigo;
    @FXML
    private TableColumn<Dueño, String> colnombre;
    @FXML
    private TableColumn<Dueño, String> colapellido;
    @FXML
    private TableColumn<Dueño, String> coltelefono;
    @FXML
    private TableColumn<Dueño, Ciudad> colciudad;
    @FXML
    private VBox eventos1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatos();
        colcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colnombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colapellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        coltelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colciudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        losDueños.getItems().setAll(dueños);
        
    }    

    @FXML
    private void agregarDueño(ActionEvent event) throws IOException {
         App.setRoot("crearDueño");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    
    private void cargarDatos(){
        dueños = Dueño.cargar();
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
        Dueño obj = (Dueño) losDueños.getSelectionModel().getSelectedItem();
        if(obj==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó el dueño.");
            vacio.showAndWait();
        }else{
            FXMLLoader ventana= new FXMLLoader(App.class.getResource("crearDueño.fxml"));  
            Parent root =(Parent) ventana.load();
            CrearDueñoController c2 = ventana.getController();
            c2.editar(obj);
            App.cambiaRoot(root);
        }
    }

    @FXML
    private void eliminar(ActionEvent event) throws IOException {
        Dueño obj = (Dueño) losDueños.getSelectionModel().getSelectedItem();
        if(obj==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó el dueño.");
            vacio.showAndWait();
        }else{
            Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Eliminar");             
            alerta.setHeaderText("Eliminar "+obj);
            alerta.setContentText("¿Esta seguro?");
            Optional<ButtonType> resultado = alerta.showAndWait();
            if(resultado.isPresent() && resultado.get()== ButtonType.OK){
                dueños.remove(obj);
                Dueño.guardar(dueños);
                App.setRoot("adDueño");                
            }
        }
    }
    
}
