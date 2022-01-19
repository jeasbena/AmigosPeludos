package ec.edu.espol.proyectoanimales;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {


    @FXML
    private void dueños(ActionEvent event) throws IOException {
        App.setRoot("adDueño");
    }

    @FXML
    private void ciudades(ActionEvent event) throws IOException {
         App.setRoot("adCiudad");
    }

    @FXML
    private void concursos(ActionEvent event) throws IOException {
         App.setRoot("adConcurso");
    }

    @FXML
    private void auspiciantes(ActionEvent event) throws IOException {
         App.setRoot("adAuspiciante");
    }

    @FXML
    private void mascotas(ActionEvent event) throws IOException {
         App.setRoot("adMascota");
    }

    @FXML
    private void inscribir(ActionEvent event) throws IOException {
        App.setRoot("inscribir");
    }
}
