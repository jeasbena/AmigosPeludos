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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CrearMascotaController implements Initializable {
    private Mascota obj =null;
    private ArrayList<Dueño> dueños;
    private ArrayList<Mascota> mascotas;
    @FXML
    private TextField nombre;
    @FXML
    private TextField raza;
    @FXML
    private ComboBox<Dueño> opDueño;
    @FXML
    private CheckBox esPerro;
    @FXML
    private CheckBox esGato;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextField fotoRuta;
    @FXML
    private Label titulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatos();
        opDueño.getItems().clear();
        for (Dueño cd : dueños){
            opDueño.getItems().add(cd);
        }
    }    
    private int guardarEditar(){
        int i = mascotas.indexOf(obj);
        return mascotas.get(i).getCodigo();
        
    }
    
    
    public void editar(Mascota ma){
        nombre.setText(ma.getNombre());
        titulo.setText("Editar Mascota");
        raza.setText(ma.getRaza());
        if(ma.getTipo().equals("Perro")){
            esPerro.setSelected(true);
        }else{
            esGato.setSelected(true);
        }
        opDueño.setValue(ma.getDueño());
        fotoRuta.setText(ma.getFoto());
        LocalDate date = LocalDate.parse(ma.getFechaNacimiento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        fecha.setValue(date);
        obj = ma;
    }
    

    @FXML
    private void guardar(ActionEvent event) throws IOException {
        String nombreF = nombre.getText();
        String razaF = raza.getText();
        LocalDate fecha1 = fecha.getValue();
        String fechaF = fecha1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String fotoF = fotoRuta.getText();
        boolean perroF = esPerro.isSelected();
        boolean gatoF = esGato.isSelected();
        Dueño dueñoF = (Dueño) opDueño.getValue();
        
        if(nombreF.isEmpty() ||razaF.isEmpty() || fotoF.isEmpty() || (!perroF && !gatoF) || dueñoF==null || fecha1==null){
            App.alertaVacio();
        }else{
            if((perroF &&  gatoF)){
                Alert vacio= new Alert(Alert.AlertType.INFORMATION);
                vacio.setTitle("Informacion de campo");             
                vacio.setHeaderText("Solo seleccione un tipo de animal: Perro o Gato");
                vacio.showAndWait();
            }else{
                String tipo = "";
                if(perroF){
                    tipo = "Perro";
                }else{
                    tipo = "Gato";
                }
                
                if(obj!=null){
                    int codigo = guardarEditar();
                    mascotas.remove(obj);
                    mascotas.add(new Mascota(codigo,  nombreF, tipo, razaF, fechaF, fotoF, dueñoF));
                    Mascota.guardar(mascotas);
                }else{

                    if(!mascotas.contains(new Mascota(1,  nombreF, tipo, razaF, fechaF, fotoF, dueñoF))){
                        if(mascotas.isEmpty()){mascotas.add(new Mascota(1,  nombreF, tipo, razaF, fechaF, fotoF, dueñoF));}
                        else{mascotas.add(new Mascota(mascotas.get(mascotas.size()-1).getCodigo()+1,  nombreF, tipo, razaF, fechaF, fotoF, dueñoF));}
                        Mascota.guardar(mascotas);
                    }
                }
           
                Alert alerta= new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Guardado exitoso");  
                alerta.setHeaderText("Guardado exitoso");
                alerta.showAndWait();

                App.setRoot("adMascota");
            }
            
        }
        
        
        
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("adMascota");
    }
    
    
    
    
    
    private void cargarDatos(){
        mascotas = Mascota.cargar();
        
        dueños = Dueño.cargar();
    }
    

    
}
