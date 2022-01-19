module ec.edu.espol.proyectoanimales {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.proyectoanimales to javafx.fxml;
    exports ec.edu.espol.proyectoanimales;
    
    opens ec.edu.espol.modelo to javafx.fxml;
    exports ec.edu.espol.modelo;
}
