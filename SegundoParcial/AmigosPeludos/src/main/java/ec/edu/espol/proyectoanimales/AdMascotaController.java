/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectoanimales;

import ec.edu.espol.modelo.Dueño;
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
public class AdMascotaController implements Initializable {
    private ArrayList<Mascota> mascotas;
    @FXML
    private TableView lasMascotas;
    @FXML
    private TableColumn<Mascota, Integer> colcodigo;
    @FXML
    private TableColumn<Mascota, String> colnombre;
    @FXML
    private TableColumn<Mascota, String> coltipo;
    @FXML
    private TableColumn<Mascota, Dueño> coldueño;
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
        colnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        coltipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        coldueño.setCellValueFactory(new PropertyValueFactory<>("dueño"));
        lasMascotas.getItems().setAll(mascotas);
        
    }    

    @FXML
    private void agregarMascota(ActionEvent event) throws IOException {
         App.setRoot("crearMascota");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    private void cargarDatos(){
        mascotas = Mascota.cargar();
    }

    @FXML
    private void detalle(ActionEvent event) throws IOException {
        Mascota obj = (Mascota) lasMascotas.getSelectionModel().getSelectedItem();
        if(obj==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó la mascota.");
            vacio.showAndWait();
        }else{
            FXMLLoader ventana= new FXMLLoader(App.class.getResource("detalleMascota.fxml"));  
            Parent root =(Parent) ventana.load();
            DetalleMascotaController c2 = ventana.getController();
            c2.setDueño(obj.getDueño().toString());
            c2.setFecha(obj.getFechaNacimiento());
            c2.setNombre(obj.getNombre());
            c2.setRaza(obj.getRaza());
            c2.setNombreFoto(obj.getFoto());
            c2.cargarFoto();
            App.cambiaRoot(root);
            
            
        }
        
    }

    @FXML
    private void eliminar(ActionEvent event) throws IOException {
        Mascota obj = (Mascota) lasMascotas.getSelectionModel().getSelectedItem();
        if(obj==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó la mascota.");
            vacio.showAndWait();
        }else{
            Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Eliminar");             
            alerta.setHeaderText("Eliminar "+obj);
            alerta.setContentText("¿Esta seguro?");
            Optional<ButtonType> resultado = alerta.showAndWait();
            if(resultado.isPresent() && resultado.get()== ButtonType.OK){
                mascotas.remove(obj);
                Mascota.guardar(mascotas);
                App.setRoot("adMascota");                
            }
        }
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
        Mascota obj = (Mascota) lasMascotas.getSelectionModel().getSelectedItem();
        if(obj==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó la mascota.");
            vacio.showAndWait();
        }else{
            FXMLLoader ventana= new FXMLLoader(App.class.getResource("crearMascota.fxml"));  
            Parent root =(Parent) ventana.load();
            CrearMascotaController c2 = ventana.getController();
            c2.editar(obj);
            App.cambiaRoot(root);
            
        }
    }
    
}
