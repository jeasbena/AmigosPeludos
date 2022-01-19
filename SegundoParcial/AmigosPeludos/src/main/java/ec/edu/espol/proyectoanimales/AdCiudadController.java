/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectoanimales;

import ec.edu.espol.modelo.Ciudad;
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
public class AdCiudadController implements Initializable {
    private ArrayList<Ciudad> ciudades;
    @FXML
    private TableView lasCiudades;
    @FXML
    private TableColumn<Ciudad, Integer> colcodigo;
    @FXML
    private TableColumn<Ciudad, String> colnombre;
    @FXML
    private TableColumn<Ciudad, String> colprovincia;
    @FXML
    private VBox eventos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        colcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colprovincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        lasCiudades.getItems().setAll(ciudades);
    }    

    @FXML
    private void agregarCiudad(ActionEvent event) throws IOException {
         App.setRoot("crearCiudad");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    private void cargarDatos(){
        ciudades = Ciudad.cargar();
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
        Ciudad ciudadF = (Ciudad) lasCiudades.getSelectionModel().getSelectedItem();
        if(ciudadF==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó la ciudad.");
            vacio.showAndWait();
        }else{
            FXMLLoader ventana= new FXMLLoader(App.class.getResource("crearCiudad.fxml"));  
            Parent root =(Parent) ventana.load();
            CrearCiudadController c2 = ventana.getController();
            c2.editar(ciudadF);
            App.cambiaRoot(root);
        }
    }

    @FXML
    private void eliminar(ActionEvent event) throws IOException {
        Ciudad ciudadF = (Ciudad) lasCiudades.getSelectionModel().getSelectedItem();
        if(ciudadF==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó la ciudad.");
            vacio.showAndWait();
        }else{
            Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Eliminar");             
            alerta.setHeaderText("Eliminar "+ciudadF);
            alerta.setContentText("¿Esta seguro?");
            Optional<ButtonType> resultado = alerta.showAndWait();
            if(resultado.isPresent() && resultado.get()== ButtonType.OK){
                ciudades.remove(ciudadF);
                Ciudad.guardar(ciudades);
                App.setRoot("adCiudad");                
            }
        }
    }
    
}
