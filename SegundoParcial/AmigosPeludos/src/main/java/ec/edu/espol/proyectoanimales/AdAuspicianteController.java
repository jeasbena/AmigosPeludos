
package ec.edu.espol.proyectoanimales;

import ec.edu.espol.modelo.Auspiciante;
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
public class AdAuspicianteController implements Initializable {
    private ArrayList<Auspiciante> auspiciantes;
    @FXML
    private TableColumn<Auspiciante, Integer> colcodigo;
    @FXML
    private TableColumn<Auspiciante, String> colnombre;
    @FXML
    private TableColumn<Auspiciante, String> coltelefono;
    @FXML
    private TableColumn<Auspiciante, Ciudad> colciudad;
    @FXML
    private VBox eventos1;
    @FXML
    private TableView losauspiciantes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        colcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        coltelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colciudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        losauspiciantes.getItems().setAll(auspiciantes);
        
    }    

    @FXML
    private void crearAuspiciante(ActionEvent event) throws IOException {
         App.setRoot("crearAuspiciante");
    }

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    
    private void cargarDatos(){
        auspiciantes = Auspiciante.cargar();
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
         Auspiciante obj = (Auspiciante) losauspiciantes.getSelectionModel().getSelectedItem();
        if(obj==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó el auspiciante.");
            vacio.showAndWait();
        }else{
            FXMLLoader ventana= new FXMLLoader(App.class.getResource("crearAuspiciante.fxml"));  
            Parent root =(Parent) ventana.load();
            CrearAuspicianteController c2 = ventana.getController();
            c2.editar(obj);
            App.cambiaRoot(root);
        }
    }

    @FXML
    private void eliminar(ActionEvent event) throws IOException {
        Auspiciante obj = (Auspiciante) losauspiciantes.getSelectionModel().getSelectedItem();
        if(obj==null){
            Alert vacio= new Alert(Alert.AlertType.INFORMATION);
            vacio.setTitle("Información");             
            vacio.setHeaderText("No se seleccionó el auspiciante.");
            vacio.showAndWait();
        }else{
            Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Eliminar");             
            alerta.setHeaderText("Eliminar "+obj);
            alerta.setContentText("¿Esta seguro?");
            Optional<ButtonType> resultado = alerta.showAndWait();
            if(resultado.isPresent() && resultado.get()== ButtonType.OK){
                auspiciantes.remove(obj);
                Auspiciante.guardar(auspiciantes);
                App.setRoot("adAuspiciante");                
            }
        }
    }
    
}
