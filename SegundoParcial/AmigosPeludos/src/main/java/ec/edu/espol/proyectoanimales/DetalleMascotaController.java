/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectoanimales;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetalleMascotaController implements Initializable {
    private String nombreFoto;

    @FXML
    private Label nombre;
    @FXML
    private Label raza;

    @FXML
    private Label dueño;
    @FXML
    private Label fecha;
    @FXML
    private ImageView imagenFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.setRoot("adMascota");
    }
    
    public void setNombre(String nombre) {
        this.nombre.setText(nombre);
    }

    public void setRaza(String raza) {
        this.raza.setText(raza);
    }

    public void setDueño(String dueño) {
        this.dueño.setText(dueño);
    }

    public void setFecha(String fecha) {
        this.fecha.setText(fecha);
    }

    public void setImagenFoto(ImageView imagenFoto) {
        this.imagenFoto = imagenFoto;
    }
    
    public void setNombreFoto(String nombreFoto) {
        this.nombreFoto = nombreFoto;
    }
    
    public void cargarFoto(){
        FileInputStream inputImagen= null;
        try {
            inputImagen= new FileInputStream(App.path+nombreFoto+".png");
            Image imagen = new Image(inputImagen, 200,100,false,false);
            imagenFoto.setImage(imagen);
        } catch (FileNotFoundException ex) {
            try {
                inputImagen = new FileInputStream(App.path+"nofoto.png");
                Image imagen = new Image(inputImagen, 100,100,false,false);
                imagenFoto.setImage(imagen);
            } catch (FileNotFoundException ex1) {
                ex1.printStackTrace();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
