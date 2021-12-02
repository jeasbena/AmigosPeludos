
package amigospeludos;

import entidades.Auspiciante;
import entidades.Dueño;
import entidades.Mascota;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import lugares.Ciudad;

public class AmigosPeludos {
    
    public static final ArrayList<Concurso> concursos = new ArrayList<>();
    public static final ArrayList<Mascota> mascotas = new ArrayList<>();
    public static final ArrayList<Ciudad> ciudades = new ArrayList<>();
    public static final ArrayList<Auspiciante> auspiciantes = new ArrayList<>();
    public static final ArrayList<Dueño> dueños = new ArrayList<>();
    
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatos();
        menuPrincipal();
    }
    
    private static void menuPrincipal(){
        String opcion;
        String MENU = "\n1. Administrar Concursos\n"
                + "2. Administrar Dueños\n"
                + "3. Administrar Mascotas\n"
                + "0. Salir";
        do {
            System.out.println(MENU);
            System.out.print("\nOpción: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    menuConcursos();
                    break;
                case "2":
                    menuDueños();
                    break;
                case "3":
                    menuMascotas();
                    break;
                case "0":
                    System.out.println("\nSaliendo...");
                    break;
                default:
                    System.out.println("\nOpción Incorrecta");
            }
            
        } while (!opcion.equals("0"));
        
    }
    
    private static void menuConcursos(){
        String opcion;
        String MENU = "\n1. Crear Concurso\n"
                + "2. Inscribir Participante\n"
                + "0. Regresar Al Menú Principal";
        do {
            System.out.println(MENU);
            System.out.print("\nOpción: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    
                    break;
                case "2":
                    
                    break;
                case "0":
                    System.out.println("\nRegresando...");
                    break;
                default:
                    System.out.println("\nOpción Incorrecta");
            }
            
        } while (!opcion.equals("0"));
    }
    
    private static void menuDueños(){
        String opcion;
        String MENU = "\n1. Crear Dueño\n"
                + "2. Editar Dueño\n"
                + "0. Regresar Al Menú Principal";
        do {
            System.out.println(MENU);
            System.out.print("\nOpción: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    
                    break;
                case "2":
                    
                    break;
                case "0":
                    System.out.println("\nRegresando...");
                    break;
                default:
                    System.out.println("\nOpción Incorrecta");
            }
            
        } while (!opcion.equals("0"));        
    }
    
    private static void menuMascotas(){
        String opcion;
        String MENU = "\n1. Crear Mascota\n"
                + "2. Eliminar Mascota\n"
                + "0. Regresar Al Menú Principal";
        do {
            System.out.println(MENU);
            System.out.print("\nOpción: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    
                    break;
                case "2":
                    
                    break;
                case "0":
                    System.out.println("\nRegresando...");
                    break;
                default:
                    System.out.println("\nOpción Incorrecta");
            }
            
        } while (!opcion.equals("0"));        
    }
    
    private static void cargarDatos(){
        ciudades.addAll(Arrays.asList(
                new Ciudad("Guayaquil", "Guayas"),
                new Ciudad("Quito", "Pichincha"),
                new Ciudad("Cuenca", "Azuay")
        ));
        dueños.addAll(Arrays.asList(
                new Dueño("0900000001", "Julio", "Fernandez",
                        "Villaclub",
                        "2465398", ciudades.get(0), "jfernandez@gmail.com"),
                new Dueño("0900000002", "Alberto", "Rodriguez",
                        "La Joya",
                        "2467986", ciudades.get(1), "arodriguez@hotmail.com"),
                new Dueño("0900000003", "Rodrigo", "Benitez",
                        "Ciudad Santiago",
                        "3465987", ciudades.get(0), "rbenitez@yahoo.com"),
                new Dueño("0900000004", "Aurelio", "Perez",
                        "Villa Bonita",
                        "3659898", ciudades.get(2), "aperez@gmail.com"),
                new Dueño("0900000005", "Raul", "Ramirez",
                        "Villas del Rey",
                        "2341115", ciudades.get(0), "rramirez@gmail.com"),
                new Dueño("0900000006", "Eduardo", "Ortiz",
                        "La Perla",
                        "2115589", ciudades.get(2), "eortiz@gmail.com"),
                new Dueño("0900000007", "Maria", "Gonzalez",
                        "La Fuente",
                        "3265689", ciudades.get(1), "mgonzalez@hotmail.com"),
                new Dueño("0900000008", "Federico", "Suarez",
                        "Villa España",
                        "2332659", ciudades.get(1), "fsuarez@hotmail.com"),
                new Dueño("0900000009", "Jorge", "Yanez",
                        "Metropolis",
                        "3699695", ciudades.get(1), "fsuarez@hotmail.com"),
                new Dueño("0900000010", "Jose", "Guaman",
                        "La Rioja",
                        "3216549", ciudades.get(0), "jguaman@yahoo.com")
        ));
        mascotas.addAll(Arrays.asList(
                new Mascota("Fluffy", "PERRO", "Labrador",
                        "05/01/2018", "",
                        dueños.get(0)),
                new Mascota("Fluffy", "PERRO", "Labrador",
                        "06/02/2018", "",
                        dueños.get(1)),
                new Mascota("Firulais", "PERRO", "Golden",
                        "07/03/2020", "",
                        dueños.get(2)),
                new Mascota("Brandom", "PERRO", "Pug",
                        "08/04/2020", "",
                        dueños.get(3)),
                new Mascota("Tito", "GATO", "Siames",
                        "09/05/2017", "",
                        dueños.get(4)),
                new Mascota("Lucy", "GATO", "Persa",
                        "10/06/2019", "",
                        dueños.get(5)),
                new Mascota("Pelusa", "GATO", "Azul Ruso",
                        "11/07/2019", "",
                        dueños.get(6)),
                new Mascota("Michi", "GATO", "Fold Escoces",
                        "12/08/2020", "",
                        dueños.get(7)),
                new Mascota("Beethoven", "PERRO", "San Bernardo",
                        "13/09/2017", "",
                        dueños.get(8)),
                new Mascota("Max", "PERRO", "Pastor Aleman",
                        "14/10/2019", "",
                        dueños.get(9))
        ));
        auspiciantes.addAll(Arrays.asList(
                new Auspiciante("Dog Show",
                        "Urdesa", "3333659",
                        ciudades.get(0), "info@dogshow.com",
                        "dogshow.com"),
                new Auspiciante("Cani",
                        "Sanmiro", "2349659",
                        ciudades.get(1), "info@cani.com",
                        "cani.com"),
                new Auspiciante("Whiskas",
                        "Lomas", "3333659",
                        ciudades.get(2), "contacto@whiskas.com",
                        "whiskas.com")
        ));
        concursos.addAll(Arrays.asList(
                new Concurso("Peluditos", "10/11/2021", "16:00",
                        "10/10/2021", "08/11/2021", ciudades.get(0),
                        "Parque Samanes", 
                        new ArrayList<>(Arrays.asList(
                                "$200 y Productos Cani",
                                "$150",
                                "Productos Cani"
                        )),
                        new ArrayList<>(Arrays.asList(
                                auspiciantes.get(1)
                        )),
                        "TODOS",
                        new ArrayList<>(Arrays.asList(
                                mascotas.get(0),
                                mascotas.get(1),
                                mascotas.get(2),
                                mascotas.get(3),
                                mascotas.get(4)
                        )),
                        new ArrayList<>(Arrays.asList(
                                mascotas.get(0),
                                mascotas.get(3),
                                mascotas.get(4)
                        ))
                ),
                new Concurso("Mejores Amigos", "10/01/2022", "16:00",
                        "01/12/2021", "09/01/2022", ciudades.get(2),
                        "Plaza", 
                        new ArrayList<>(Arrays.asList(
                                "$300 y Productos Dog Show",
                                "$200",
                                "Productos Dog Show"
                        )),
                        new ArrayList<>(Arrays.asList(
                                auspiciantes.get(0)
                        )),
                        "PERRO",
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        ));
       
    }
    
}
