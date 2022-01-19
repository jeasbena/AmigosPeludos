package ec.edu.espol.proyectoanimales;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Alert;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String path="src/main/resources/ec/edu/espol/data/";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 600);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    static void cambiaRoot (Parent rootnode){             // RECIBE LOS DATOS PARA LA VENTANA JUEGO
        scene.setRoot(rootnode);
    }
    public static void alertaVacio(){
        Alert vacio= new Alert(Alert.AlertType.INFORMATION);
        vacio.setTitle("Información de campo no ingresado");             
        vacio.setHeaderText("Campo sin llenar");
        vacio.setContentText("Llene todos los campos de registro");
        vacio.showAndWait();
        
    }
}