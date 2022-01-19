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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CrearCiudadController implements Initializable {
    private Ciudad obj = null;
    ArrayList<Ciudad> ciudades;
    @FXML
    private TextField nombre;
    @FXML
    private ComboBox<String> colProvincia;
    @FXML
    private Label titulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        colProvincia.getItems().setAll("Guayas","Santa Elena", "Los Ríos", "El Oro", "Manabí", "Azuay","Carchi", "Loja","Pichincha", "Napo", "Sucumbios");
    }    

    @FXML
    private void guardar(ActionEvent event) throws IOException {
        String nombreF = nombre.getText();
        String provinciaF = (String) colProvincia.getValue();
        if(nombreF.isEmpty() || provinciaF==null){
            App.alertaVacio();
        }else{
            if(obj!=null){
                int codigo = guardarEditar();
                ciudades.remove(obj);
                ciudades.add(new Ciudad(codigo,nombreF,provinciaF));
                Ciudad.guardar(ciudades);
            }else{
            
                if(!ciudades.contains(new Ciudad(1,nombreF,provinciaF))){
                    if(ciudades.isEmpty()){ciudades.add(new Ciudad(1,nombreF,provinciaF));}
                    else{ciudades.add(new Ciudad(ciudades.get(ciudades.size()-1).getCodigo()+1,nombreF,provinciaF));}
                    Ciudad.guardar(ciudades);
                }
            }
            Alert alerta= new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Guardado exitoso");  
            alerta.setHeaderText("Guardado exitoso");
            alerta.showAndWait();
            
            App.setRoot("adCiudad");
        }
    }
    private int guardarEditar(){
        int i = ciudades.indexOf(obj);
        return ciudades.get(i).getCodigo();
        
    }
    
    
    public void editar(Ciudad ciu){
        nombre.setText(ciu.getNombre());
        titulo.setText("Editar Ciudad");
        colProvincia.setValue(ciu.getProvincia());
        obj = ciu;
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("adCiudad");
    }
    
    private void cargarDatos(){
        ciudades = Ciudad.cargar();
    }
    

}
