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
public class CrearDueñoController implements Initializable {
    private Dueño obj;
    private ArrayList<Dueño> dueños;
    private ArrayList<Ciudad> ciudades;
    @FXML
    private TextField nombre1;
    @FXML
    private TextField apellido;
    @FXML
    private TextField direccion;
    @FXML
    private ComboBox<Ciudad> opCiudad;
    @FXML
    private TextField telefono;
    @FXML
    private TextField email;
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

    @FXML
    private void guardar(ActionEvent event) throws IOException {
        String nombreF = nombre1.getText();
        String direccionF = direccion.getText();
        String apellidoF = apellido.getText();
        String emailF = email.getText();
        String telefonoF = telefono.getText();
        Ciudad ciudadF = (Ciudad) opCiudad.getValue();
        if(nombreF.isEmpty() ||direccionF.isEmpty() || apellidoF.isEmpty() || emailF.isEmpty() ||  telefonoF.isEmpty() ||ciudadF==null){
            App.alertaVacio();
            
        }else{
            if(obj!=null){
                int codigo = guardarEditar();
                dueños.remove(obj);
                dueños.add(new Dueño(codigo, nombreF, apellidoF, direccionF, telefonoF, ciudadF, emailF));
                Dueño.guardar(dueños);
                
            }else{
                if(!dueños.contains(new Dueño(1, nombreF, apellidoF, direccionF, telefonoF, ciudadF, emailF))){
                    if(dueños.isEmpty()){dueños.add(new Dueño(1, nombreF, apellidoF, direccionF, telefonoF, ciudadF, emailF));}
                    else{dueños.add(new Dueño(dueños.get(dueños.size()-1).getCodigo()+1, nombreF, apellidoF, direccionF, telefonoF, ciudadF, emailF));}
                    Dueño.guardar(dueños);
                }
            }   
            Alert alerta= new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Guardado exitoso");  
            alerta.setHeaderText("Guardado exitoso");
            alerta.showAndWait();
            
            App.setRoot("adDueño");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("adDueño");
    }
    
    
    private void cargarDatos(){
        ciudades = Ciudad.cargar();
        
        dueños = Dueño.cargar();
    }
     private int guardarEditar(){
        int i = dueños.indexOf(obj);
        return dueños.get(i).getCodigo();
        
    }
    
    
    public void editar(Dueño du){
        nombre1.setText(du.getNombres());
        apellido.setText(du.getApellidos());
        direccion.setText(du.getDireccion());
        telefono.setText(du.getTelefono());
        email.setText(du.getEmail());
        titulo.setText("Editar Dueño");
        opCiudad.setValue(du.getCiudad());
        obj = du;
    }

}
