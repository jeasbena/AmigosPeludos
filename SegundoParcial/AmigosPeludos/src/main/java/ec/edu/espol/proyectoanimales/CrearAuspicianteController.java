/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectoanimales;

import ec.edu.espol.modelo.Auspiciante;
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
public class CrearAuspicianteController implements Initializable {
    private Auspiciante obj = null;
    private ArrayList<Auspiciante> auspiciantes;
    private ArrayList<Ciudad> ciudades;
    @FXML
    private TextField nombre1;
    @FXML
    private TextField telefono;
    @FXML
    private TextField direccion;
    @FXML
    private ComboBox<Ciudad> opCiudad;
    @FXML
    private TextField email;
    @FXML
    private TextField webPage;
    @FXML
    private Label titulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatos();
        opCiudad.getItems().clear();
        for (Ciudad cd : ciudades){
            opCiudad.getItems().add(cd);
        }
        
    }    
    public void editar(Auspiciante aus){
        titulo.setText("Editar Auspiciante");
        nombre1.setText(aus.getNombre());
        direccion.setText(aus.getDireccion());
        webPage.setText(aus.getWebPage());
        email.setText(aus.getEmail());
        telefono.setText(aus.getTelefono());
        opCiudad.setValue(aus.getCiudad());
        obj = aus;
    }
    
    @FXML
    private void guardar(ActionEvent event) throws IOException {
        String nombreF = nombre1.getText();
        String direccionF = direccion.getText();
        String webPageF = webPage.getText();
        String emailF = email.getText();
        String telefonoF = telefono.getText();
        Ciudad ciudadF = (Ciudad) opCiudad.getValue();
        if(nombreF.isEmpty() ||direccionF.isEmpty() || webPageF.isEmpty() || emailF.isEmpty() ||  telefonoF.isEmpty() ||ciudadF==null){
            App.alertaVacio();
        }else{
            if(obj!=null){
                int codigo = guardarEditar();
                auspiciantes.remove(obj);
                auspiciantes.add(new Auspiciante(codigo, nombreF, direccionF, telefonoF, ciudadF, emailF, webPageF));
                Auspiciante.guardar(auspiciantes);
            }else{
                if(!auspiciantes.contains(new Auspiciante(1, nombreF, direccionF, telefonoF, ciudadF, emailF, webPageF))){
                    if(auspiciantes.isEmpty()){auspiciantes.add(new Auspiciante(1, nombreF, direccionF, telefonoF, ciudadF, emailF, webPageF));}
                    else{auspiciantes.add(new Auspiciante(auspiciantes.get(auspiciantes.size()-1).getCodigo()+1, nombreF, direccionF, telefonoF, ciudadF, emailF, webPageF));}
                    Auspiciante.guardar(auspiciantes);
                }
            }
            Alert alerta= new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Guardado exitoso");  
            alerta.setHeaderText("Guardado exitoso");
            alerta.showAndWait();
            App.setRoot("adAuspiciante");
        }
        
    }
    
    private int guardarEditar(){
        int i = auspiciantes.indexOf(obj);
        return auspiciantes.get(i).getCodigo();
        
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("adAuspiciante");
    }
    
    private void cargarDatos(){
        auspiciantes = Auspiciante.cargar();
        
        ciudades = Ciudad.cargar();
    }
}
