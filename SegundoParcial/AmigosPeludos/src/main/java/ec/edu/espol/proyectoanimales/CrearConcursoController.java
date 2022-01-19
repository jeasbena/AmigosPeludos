/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyectoanimales;

import ec.edu.espol.modelo.Auspiciante;
import ec.edu.espol.modelo.Ciudad;
import ec.edu.espol.modelo.Concurso;
import ec.edu.espol.modelo.Premio;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CrearConcursoController implements Initializable {
    private Concurso obj = null;
    private ArrayList<Concurso> concursos;
    private ArrayList<Premio> premios2 = new ArrayList<>();
    private ArrayList<Auspiciante> auspiciantesF = new ArrayList<>();
    private ArrayList<Ciudad> ciudades;
    @FXML
    private ComboBox<String> opciones;
    @FXML
    private TextField nombre;
    @FXML
    private DatePicker fecha;
    @FXML
    private TextField hora;
    @FXML
    private DatePicker inicioIns;
    @FXML
    private DatePicker cierreIns;
    @FXML
    private ComboBox<Ciudad> opcionesCiudad;
    @FXML
    private TextField lugar;
    @FXML
    private TableView premios;
    @FXML
    private TableColumn<Premio, Integer> collugar;
    @FXML
    private TableColumn<Premio, String> coldescripcion;
    @FXML
    private TableColumn<Premio, Auspiciante> colauspiciante;
    @FXML
    private TableView losauspiciantes;
    @FXML
    private TableColumn<Auspiciante, String> colnombre;
    @FXML
    private Label titulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarDatos();
        
        collugar.setCellValueFactory(new PropertyValueFactory<>("lugar"));
        coldescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colauspiciante.setCellValueFactory(new PropertyValueFactory<>("auspiciante"));
        
        colnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        opciones.getItems().setAll("Perros","Gatos");
        opcionesCiudad.getItems().clear();
        for (Ciudad cd : ciudades){
            opcionesCiudad.getItems().add(cd);
        }
        
    }
    
    private int guardarEditar(){
        int i = concursos.indexOf(obj);
        return concursos.get(i).getCodigo();
        
    }
    
    
    public void editar(Concurso ma){
        nombre.setText(ma.getNombre());
        hora.setText(ma.getHoraEvento());
        lugar.setText(ma.getLugar());
        titulo.setText("Editar Concurso");
        fecha.setValue(LocalDate.parse(ma.getFechaEvento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        inicioIns.setValue(LocalDate.parse(ma.getInicioInscripcion(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        cierreIns.setValue(LocalDate.parse(ma.getCierreInscripcion(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        opcionesCiudad.setValue(ma.getCiudad());
        opciones.setValue(ma.getTipoMascota());
        premios2 = ma.getPremios();
        
        this.premios.getItems().setAll(premios2);
        auspiciantesF = new ArrayList<>();
        ArrayList<String> lista = new ArrayList<>();
        for(Premio pr : premios2){
            if(!lista.contains(pr.getAuspiciante().getNombre())){
                lista.add(pr.getAuspiciante().getNombre());
                auspiciantesF.add(pr.getAuspiciante());
            }
        }
        losauspiciantes.getItems().setAll(auspiciantesF);
        
        obj = ma;
    }
    
    public void opcionesEsp(ArrayList<LocalDate> infodate, ArrayList<String> infotext,ArrayList<Premio> premios, Ciudad ciudad, String dirigido){
        nombre.setText(infotext.get(0));
        hora.setText(infotext.get(1));
        lugar.setText(infotext.get(2));
        
        fecha.setValue(infodate.get(0));
        inicioIns.setValue(infodate.get(1));
        cierreIns.setValue(infodate.get(2));
        
        opcionesCiudad.setValue(ciudad);
        opciones.setValue(dirigido);
        
        premios2 = premios;
        
        this.premios.getItems().setAll(premios2);
        auspiciantesF = new ArrayList<>();
        ArrayList<String> lista = new ArrayList<>();
        for(Premio pr : premios2){
            if(!lista.contains(pr.getAuspiciante().getNombre())){
                lista.add(pr.getAuspiciante().getNombre());
                auspiciantesF.add(pr.getAuspiciante());
            }
        } 
        
        
        losauspiciantes.getItems().setAll(auspiciantesF);
    }

    @FXML
    private void addPremio(ActionEvent event) throws IOException{
        String nombreF = nombre.getText();
        String horaF = hora.getText();
        String lugarF = lugar.getText();
        LocalDate fechaF = fecha.getValue();
        LocalDate inicioF = inicioIns.getValue();
        LocalDate cierreF = cierreIns.getValue();
        Ciudad ciudadF = (Ciudad) opcionesCiudad.getValue();
        String dirigidoF = opciones.getValue();
        if(nombreF.isEmpty() || horaF.isEmpty() || lugarF.isEmpty() || dirigidoF.isEmpty() || ciudadF==null || fechaF==null || inicioF==null  || cierreF==null  ){
            App.alertaVacio();
        }else{
        FXMLLoader ventana= new FXMLLoader(App.class.getResource("addPremio.fxml"));  
        Parent root =(Parent) ventana.load();
        AddPremioController c2 = ventana.getController();
        ArrayList<String> infotext= new ArrayList<>();
        infotext.add(nombreF);
        infotext.add(horaF);
        infotext.add(lugarF);
        ArrayList<LocalDate> infodate = new ArrayList<>();
        infodate.add(fechaF);
        infodate.add(inicioF);
        infodate.add(cierreF);
        c2.setInfoTEXT(infotext);
        c2.setInfoDATE(infodate);
        c2.setCiudad(ciudadF);
        c2.setDirigido(dirigidoF);
        if(premios2.isEmpty()){
            c2.setPremios(new ArrayList<>());
        }else{
            c2.setPremios(premios2);
        }
        App.cambiaRoot(root);
        }
        
        
        

    }

    @FXML
    private void guardar(ActionEvent event) throws IOException {
        String nombreF = nombre.getText();
        String horaF = hora.getText();
        String lugarF = lugar.getText();
        LocalDate fechaF = fecha.getValue();
        String fechaF2 = fechaF.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate inicioF = inicioIns.getValue();
        String inicioF2 = inicioF.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate cierreF = cierreIns.getValue();
        String cierreF2 = cierreF.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Ciudad ciudadF = (Ciudad) opcionesCiudad.getValue();
        String dirigidoF = opciones.getValue();
        if(nombreF.isEmpty() || horaF.isEmpty() || lugarF.isEmpty() || dirigidoF.isEmpty() || ciudadF==null || fechaF==null ||
                inicioF==null  || cierreF==null  || premios2.isEmpty() || auspiciantesF.isEmpty()){
            App.alertaVacio();
            
        }else{
            if(obj!=null){
                int codigo = guardarEditar();
                concursos.remove(obj);
                concursos.add(new Concurso(codigo, nombreF,fechaF2,horaF,inicioF2,cierreF2,ciudadF,lugarF,premios2,auspiciantesF,dirigidoF));
                Concurso.guardar(concursos);
            }else{
                if(!concursos.contains(new Concurso(1, nombreF,fechaF2,horaF,inicioF2,cierreF2,ciudadF,lugarF,premios2,auspiciantesF,dirigidoF))){
                    if(concursos.isEmpty()){concursos.add(new Concurso(1, nombreF,fechaF2,horaF,inicioF2,cierreF2,ciudadF,lugarF,premios2,auspiciantesF,dirigidoF));}
                    else{concursos.add(new Concurso(concursos.get(concursos.size()-1).getCodigo()+1, nombreF,fechaF2,horaF,inicioF2,cierreF2,ciudadF,lugarF,premios2,auspiciantesF,dirigidoF));}
                    Concurso.guardar(concursos);
                }
            }
            Alert alerta= new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Guardado exitoso");  
            alerta.setHeaderText("Guardado exitoso");
            alerta.showAndWait();
            
            App.setRoot("adConcurso");
        }
        
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("adConcurso");
    }
    
    private void cargarDatos(){
        concursos = Concurso.cargar();
        
        ciudades = Ciudad.cargar();

    }
    
    
    
}
