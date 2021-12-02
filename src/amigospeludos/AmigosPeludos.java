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

    public static final String FECHA_ACTUAL = "03/12/2021";

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatos();
        menuPrincipal();
    }

    private static void menuPrincipal() {
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

    private static void menuConcursos() {
        String opcion;
        String MENU = "\n1. Crear Concurso\n"
                + "2. Inscribir Participante\n"
                + "0. Regresar Al Menú Principal";
        do {
            for (Concurso concurso : concursos) {
                System.out.println(concurso);
            }
            System.out.println(MENU);
            System.out.print("\nOpción: ");
            opcion = sc.nextLine();
            String codigo;
            switch (opcion) {
                case "1":
                    System.out.print("\nNombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("\nFecha del Evento: ");
                    String fechaEvento = sc.nextLine();
                    while (!fechaCorrecta(fechaEvento)) {
                        System.out.print("\nFecha del Evento: ");
                        fechaEvento = sc.nextLine();
                    }
                    System.out.print("\nHora del Evento: ");
                    String horaEvento = sc.nextLine();
                    while (!horaCorrecta(horaEvento)) {
                        System.out.print("\nHora del Evento: ");
                        horaEvento = sc.nextLine();
                    }
                    System.out.print("\nInicio de Inscripción: ");
                    String inicioInscripcion = sc.nextLine();
                    while (!fechaCorrecta(inicioInscripcion)) {
                        System.out.print("\nInicio de Inscripción: ");
                        inicioInscripcion = sc.nextLine();
                    }
                    System.out.print("\nCierre de Inscripción: ");
                    String cierreInscripcion = sc.nextLine();
                    while (!fechaCorrecta(cierreInscripcion)) {
                        System.out.print("\nCierre de Inscripción: ");
                        cierreInscripcion = sc.nextLine();
                    }
                    System.out.println("\nCiudad:");
                    for (Ciudad ciudad : ciudades) {
                        System.out.println("\t- " + ciudad.getCodigo() + ": " + ciudad.getNombre());
                    }
                    System.out.println("\nElija el código de la ciudad: ");
                    String codigoCiudad = sc.nextLine();
                    Ciudad ciudad;
                    while ((ciudad = Ciudad.getCiudad(ciudades, codigoCiudad)) == null) {
                        System.out.println("\nElija el código de la ciudad: ");
                        codigoCiudad = sc.nextLine();
                    }
                    System.out.print("\nLugar: ");
                    String lugar = sc.nextLine();
                    System.out.print("\nCantidad de Premios: ");
                    String n = sc.nextLine();
                    while (!esNumero(n)) {
                        System.out.print("\nCantidad de Premios: ");
                        n = sc.nextLine();
                    }
                    ArrayList<String> premios = new ArrayList<>();
                    for (int i = 0; i < Integer.parseInt(n); i++) {
                        System.out.println("\n" + (i + 1) + "º Lugar:");
                        premios.add(sc.nextLine());
                    }
                    ArrayList<Auspiciante> auspiciantesConcurso = new ArrayList<>();
                    Auspiciante auspiciante;
                    do {
                        System.out.println("\nAgregar Auspiciantes o (SALIR)");
                        for (Auspiciante ausp : auspiciantes) {
                            System.out.println("\t- " + ausp.getCodigo() + ": " + ausp.getNombre());
                        }
                        System.out.println("\nIngrese el codigo");
                        codigo = sc.nextLine().toUpperCase();
                        while (!((auspiciante = Auspiciante.getAuspiciante(auspiciantes, codigo)) != null || codigo.equals("SALIR"))) {
                            System.out.println("\nIngrese el codigo");
                            codigo = sc.nextLine().toUpperCase();
                        }
                        if (!codigo.equals("SALIR") && !auspiciantesConcurso.contains(auspiciante)) {
                            auspiciantesConcurso.add(auspiciante);
                        }
                    } while (!codigo.equals("SALIR"));
                    System.out.print("\nTipo Mascota: ");
                    String tipoMascota = sc.nextLine().toUpperCase();
                    while (!(Mascota.TIPOS.contains(tipoMascota)
                            || tipoMascota.equals("TODOS")
                            || tipoMascota.equals("TODO"))) {
                        System.out.print("\nTipo Mascota: ");
                        tipoMascota = sc.nextLine().toUpperCase();
                    }
                    concursos.add(new Concurso(nombre, fechaEvento, horaEvento,
                            inicioInscripcion, cierreInscripcion, ciudad,
                            lugar, premios, auspiciantesConcurso, tipoMascota,
                            new ArrayList<Mascota>(), new ArrayList<Mascota>()));

                    break;
                case "2":
                    System.out.print("\nCódigo Concurso: ");
                    codigo = sc.nextLine();
                    Concurso concurso;
                    while ((concurso = Concurso.getConcursoDisponible(concursos, codigo)) == null) {
                        System.out.print("\nCódigo Concurso: ");
                        codigo = sc.nextLine();
                    }
                    Mascota mascota;
                    for (Mascota masc : mascotas) {
                        if (masc.getTipo().equals(concurso.getTipoMascota())
                                || concurso.getTipoMascota().equals("TODOS")
                                || concurso.getTipoMascota().equals("TODO")) {
                            System.out.println("\t- " + masc.getCodigo() + ": " + masc.getNombre());
                        }
                    }
                    System.out.println("\nElija el código de la mascota: ");
                    codigo = sc.nextLine();
                    while ((mascota = Mascota.getMascota(mascotas, codigo, concurso.getTipoMascota())) == null) {
                        System.out.println("\nElija el código de la mascota: ");
                        codigoCiudad = sc.nextLine();
                    }
                    if (!concurso.getMascotasInscritas().contains(mascota)) {
                        concurso.getMascotasInscritas().add(mascota);
                    }

                    break;
                case "0":
                    System.out.println("\nRegresando...");
                    break;
                default:
                    System.out.println("\nOpción Incorrecta");
            }

        } while (!opcion.equals("0"));
    }

    private static void menuDueños() {
        String opcion;
        String MENU = "\n1. Crear Dueño\n"
                + "2. Editar Dueño\n"
                + "0. Regresar Al Menú Principal";
        do {
            for (Dueño dueño : dueños) {
                System.out.println(dueño);
            }
            System.out.println(MENU);
            System.out.print("\nOpción: ");
            opcion = sc.nextLine();
            String cedula;
            switch (opcion) {
                case "1":
                    System.out.print("\nCédula: ");
                    cedula = sc.nextLine();
                    System.out.print("\nNombres: ");
                    String nombres = sc.nextLine();
                    System.out.print("\nApellidos: ");
                    String apellidos = sc.nextLine();
                    System.out.print("\nDirección: ");
                    String direccion = sc.nextLine();
                    System.out.print("\nTeléfono: ");
                    String telefono = sc.nextLine();
                    System.out.println("\nCiudad:");
                    for (Ciudad ciudad : ciudades) {
                        System.out.println("\t- " + ciudad.getCodigo() + ": " + ciudad.getNombre());
                    }
                    System.out.println("\nElija el código de la ciudad: ");
                    String codigoCiudad = sc.nextLine();
                    Ciudad ciudad;
                    while ((ciudad = Ciudad.getCiudad(ciudades, codigoCiudad)) == null) {
                        System.out.println("\nElija el código de la ciudad: ");
                        codigoCiudad = sc.nextLine();
                    }
                    System.out.print("\nEmail: ");
                    String email = sc.nextLine();
                    dueños.add(new Dueño(cedula, nombres, apellidos, direccion, telefono, ciudad, email));
                    break;
                case "2":
                    System.out.print("\nCédula: ");
                    cedula = sc.nextLine();
                    Dueño dueño;
                    while ((dueño = Dueño.getDueño(dueños, cedula)) == null) {
                        System.out.print("\nCédula: ");
                        cedula = sc.nextLine();
                    }
                    String dato;
                    do {
                        System.out.println("\n¿Qué dato desea actualizar? (SALIR)");
                        dato = sc.nextLine().toUpperCase();
                        System.out.print(" > ");
                        switch (dato) {
                            case "NOMBRE":
                            case "NOMBRES":
                                dueño.setNombres(sc.nextLine());
                                break;
                            case "APELLIDO":
                            case "APELLIDOS":
                                dueño.setApellidos(sc.nextLine());
                                break;
                            case "DIRECCION":
                                dueño.setDireccion(sc.nextLine());
                                break;
                            case "TELEFONO":
                                dueño.setTelefono(sc.nextLine());
                                break;
                            case "EMAIL":
                                dueño.setEmail(sc.nextLine());
                                break;
                            case "SALIR":
                                System.out.println("\nGuardando...");
                                break;
                            default:
                                System.out.println("\nDato incorrecto o no se puede actualizar");
                        }
                    } while (!dato.equals("SALIR"));

                    break;
                case "0":
                    System.out.println("\nRegresando...");
                    break;
                default:
                    System.out.println("\nOpción Incorrecta");
            }

        } while (!opcion.equals("0"));
    }

    private static void menuMascotas() {
        String opcion;
        String MENU = "\n1. Crear Mascota\n"
                + "2. Eliminar Mascota\n"
                + "0. Regresar Al Menú Principal";
        do {
            for (Mascota mascota : mascotas) {
                System.out.println("\nCodigo: " + mascota.getCodigo()
                        + "\nNombre: " + mascota.getNombre());
            }
            System.out.println(MENU);
            System.out.print("\nOpción: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    System.out.print("\nNombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("\nTipo: ");
                    String tipo = sc.nextLine().toUpperCase();
                    while (!Mascota.TIPOS.contains(tipo)) {
                        System.out.print("\nTipo: ");
                        tipo = sc.nextLine().toUpperCase();
                    }
                    System.out.print("\nRaza: ");
                    String raza = sc.nextLine();
                    System.out.print("\nFecha de Nacimiento: ");
                    String fecha = sc.nextLine();
                    while (!fechaCorrecta(fecha)) {
                        System.out.print("\nFecha de Nacimiento: ");
                        fecha = sc.nextLine();
                    }
                    System.out.println("\nDueño:");
                    for (Dueño dueño : dueños) {
                        System.out.println("\t- " + dueño.getCedula() + ": "
                                + dueño.getNombres() + " " + dueño.getApellidos());
                    }
                    System.out.println("\nCédula del dueño: ");
                    String cedula = sc.nextLine();
                    Dueño dueño;
                    while ((dueño = Dueño.getDueño(dueños, cedula)) == null) {
                        System.out.println("\nCédula del dueño: ");
                        cedula = sc.nextLine();
                    }
                    mascotas.add(new Mascota(nombre, tipo, raza, fecha, "", dueño));
                    break;
                case "2":
                    System.out.print("\nCódigo: ");
                    String codigo = sc.nextLine();
                    Mascota mascota;
                    while ((mascota = Mascota.getMascota(mascotas, codigo)) == null) {
                        System.out.print("\nCódigo: ");
                        codigo = sc.nextLine();
                    }
                    mascotas.remove(mascota);
                    System.out.println("\nSe ha eliminado: " + mascota.getNombre());
                    break;
                case "0":
                    System.out.println("\nRegresando...");
                    break;
                default:
                    System.out.println("\nOpción Incorrecta");
            }

        } while (!opcion.equals("0"));
    }

    private static void cargarDatos() {
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
                new Mascota("Rex", "PERRO", "Labrador",
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

    public static boolean compararFechas(String fecha1, String fecha2) {
        String[] datos1 = fecha1.split("/");
        String[] datos2 = fecha2.split("/");
        int f1 = Integer.parseInt(datos1[0]);
        f1 += Integer.parseInt(datos1[1]) * 30;
        f1 += Integer.parseInt(datos1[2]) * 360;
        int f2 = Integer.parseInt(datos2[0]);
        f2 += Integer.parseInt(datos2[1]) * 30;
        f2 += Integer.parseInt(datos2[2]) * 360;
        return f1 > f2;
    }

    private static boolean esNumero(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean fechaCorrecta(String string) {
        return string.length() == 10 && string.charAt(2) == '/'
                && string.charAt(5) == '/' && esNumero(string.substring(0, 2))
                && esNumero(string.substring(3, 5))
                && esNumero(string.substring(6))
                && Integer.parseInt(string.substring(0, 2)) <= 31
                && Integer.parseInt(string.substring(0, 2)) >= 1
                && Integer.parseInt(string.substring(3, 5)) <= 12
                && Integer.parseInt(string.substring(3, 5)) >= 1
                && Integer.parseInt(string.substring(6)) >= 1700;
    }

    private static boolean horaCorrecta(String string) {
        return string.length() == 5 && string.charAt(2) == ':'
                && esNumero(string.substring(0, 2))
                && esNumero(string.substring(3))
                && Integer.parseInt(string.substring(0, 2)) <= 24
                && Integer.parseInt(string.substring(3)) < 60;
    }

}
