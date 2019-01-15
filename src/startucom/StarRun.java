package startucom;

import especies.*;
import planeta.*;
import utilidades.*;

import java.io.*;
import java.util.*;

public class StarRun {
    //Arrays de la planeta y seres.
    public static List<Planeta> planetas = new ArrayList<>();
    public static List<Especie> seres = new ArrayList<>();

    public static void main(String[] args) {

        // Para crear o mirar los planetas.
        crearPlanetas();
        //Vamos a hablar con el usuario.
        hablemos();


    }

    /**
     * funcion donde creamos los ficheros o guardamos en las arrayList los planetas.
     */
    public static void crearPlanetas() {
        File Vulcano = new File("vulcano.txt");
        File Nibiru = new File("nibiru.txt");
        File Andoria = new File("andoria.txt");
        File Kronos = new File("kronos.txt");
        FileReader fr = null;

        // creamos planeta o leemos planeta para mirar las lineas del planeta.
        int vul = checkPlaneta(Vulcano, fr);
        int nib = checkPlaneta(Nibiru, fr);
        int and = checkPlaneta(Andoria, fr);
        int kro = checkPlaneta(Kronos, fr);

        //Comprobamos si hay vida en el planeta y la guardamos en array.
        if (vul != 0) {
            comprobarVida(Vulcano);
        }
        if (nib != 0) {
            comprobarVida(Nibiru);
        }
        if (and != 0) {
            comprobarVida(Andoria);
        }
        if (kro != 0) {
            comprobarVida(Kronos);
        }

        // añadimos los planetas txt a las class planetas.
        planetas.add(new PlanetasCalorosos("vulcano"));
        planetas.add(new Planeta("nibiru"));
        planetas.add(new Planeta("andoria"));
        planetas.add(new PlanetasPequeños("kronos"));
    }

    /**
     * function para comprobar si hay vida en dichos planetas, si hay vida guardamos en arrayList de seres.
     *
     * @param f nos pasamos los ficheros creados para mirar dentro de ellos.
     */
    private static void comprobarVida(File f) {
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String cadena = "";
            while ((cadena = br.readLine()) != null) {
                String[] linea = cadena.split("-");
                switch (linea[0].toLowerCase()) {
                    case "human":
                        // shh funciona no preguntes....
                        seres.add(new Human(Integer.parseInt(linea[2]), linea[1],
                                f.getName().substring(0, f.getName().length() - 4)));
                        break;
                    case "vulcan":
                        seres.add(new Vulcan(Integer.parseInt(linea[2]), linea[1],
                                f.getName().substring(0, f.getName().length() - 4)));
                        break;
                    case "klingon":
                        seres.add(new Klingon(Integer.parseInt(linea[2]), linea[1],
                                f.getName().substring(0, f.getName().length() - 4)));
                        break;
                    case "andorian":
                        seres.add(new Andorian(linea[2], linea[1],
                                f.getName().substring(0, f.getName().length() - 4)));
                        break;
                    case "nibirian":
                        seres.add(new Nibirian(linea[2], linea[1],
                                f.getName().substring(0, f.getName().length() - 4)));
                        break;
                }
            }

        } catch (IOException ex) {
            System.out.println("Error al comprobar Planeta " + ex.getMessage());
        } catch (MiExcepcion ex) {
            System.out.println("Error al insertar ser " + ex.getMessage());
        }
    }

    /**
     * miramos las lineas de los planetas para comprobar la existencia del fichero y lo que hay dentro.
     *
     * @param f  fichero
     * @param fr filereader
     * @return int de lineas
     */
    public static int checkPlaneta(File f, FileReader fr) {
        if (f.exists()) {
            return lineasPlaneta(f, fr);
        } else {
            try {
                f.createNewFile();
                return 0;
            } catch (IOException ex) {
                System.out.println("Error al Crear Planeta: " + ex.getMessage());
            }
        }
        return 0;
    }

    /**
     * la funcion que usamo en chek planeta para devolver las lineas.
     *
     * @param f  fichero
     * @param fr filereader
     * @return int lineas
     */
    public static int lineasPlaneta(File f, FileReader fr) {
        int cont = 0;
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            while (br.readLine() != null) {
                cont++;
            }
            br.close();
            return cont;
        } catch (IOException ex) {
            System.out.println("Error al Leer Planeta " + ex.getMessage());
        }
        return 0;
    }

    /**
     * La funcion principal del programa la usaremos todo el rato para mirar las entradas del usuario
     * he iremos llamando funciones para cada opcion.
     */
    public static void hablemos() {
        String cadena = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean salir = false;
        try {
            do {
                try {
                    cadena = br.readLine();
                    if (cadena.startsWith("x")) {
                        return;
                    }
                    String[] letras = cadena.split(" ");
                    switch (letras[0].toLowerCase()) {
                        case "c":
                            if (letras.length != 5) {
                                throw new MiExcepcion("< ERROR 001: N.º de argumentos inválido >");
                            }
                            crearSer(letras[1], letras[2], letras[3], letras[4]);
                            break;
                        case "b":
                            if (letras.length != 2) {
                                throw new MiExcepcion("< ERROR 001: N.º de argumentos inválido >");
                            }
                            for (Especie ser : seres) {
                                if (ser.getNombre().equalsIgnoreCase(letras[1])) {
                                    borrarSer(letras[1]);
                                } else {
                                    throw new MiExcepcion("< ERROR 007: No existe ningún ser con ese nombre >");
                                }
                            }
                            break;
                        case "l":
                            if (letras.length != 1) {
                                throw new MiExcepcion("< ERROR 001: N.º de argumentos inválido >");
                            }
                            System.out.println("< POPULATION BY PLANET >");
                            Collections.sort(seres);

                            System.out.println("< Vulcano >");
                            mostrarSerL("vulcano");

                            System.out.println("< Andoria >");
                            mostrarSerL("andoria");

                            System.out.println("< Nibiru >");
                            mostrarSerL("nibiru");

                            System.out.println("< Kronos >");
                            mostrarSerL("kronos");

                            break;
                        case "p":
                            if (letras.length != 2) {
                                throw new MiExcepcion("< ERROR 001: N.º de argumentos inválido >");
                            }
                            System.out.println("< POPULATION BY RACE >");
                            mostrarSeres(letras[1]);
                            break;
                        case "m":
                            if (letras.length != 3) {
                                throw new MiExcepcion("< ERROR 001: N.º de argumentos inválido >");
                            }
                            for (Especie ser : seres) {
                                if (ser.getNombre().equalsIgnoreCase(letras[1])) {
                                    modificarSer(letras[1], letras[2]);
                                } else {
                                    throw new MiExcepcion("< ERROR 007: No existe ningún ser con ese nombre >");
                                }
                            }
                            break;
                        case "x":
                            System.exit(0);
                            salir = true;
                            break;
                        default:
                            throw new MiExcepcion("< ERROR 009: Operación incorrecta >");
                    }
                } catch (MiExcepcion ex) {
                    System.out.println(ex.getMessage());
                }
            } while (salir);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * funcion de L para mostrar los seres por planeta y ordenados por especie o nombre.
     *
     * @param planeta String del nombre del planeta
     */
    private static void mostrarSerL(String planeta) {
        for (Especie ser : seres) {
            if (ser.getPlaneta().equalsIgnoreCase(planeta)) {
                if (ser.getClass().getSimpleName().equalsIgnoreCase("andorian")) {
                    System.out.println(ser.getClass().getSimpleName()
                            + "-" + ser.getNombre() + "-" + (((Andorian) ser).isAenar() ? "aenar" : "noaenar"));
                } else if (ser.getClass().getSimpleName().equalsIgnoreCase("human")) {
                    System.out.println(ser.getClass().getSimpleName()
                            + "-" + ser.getNombre() + "-" + (((Human) ser).getEdad()));
                } else if (ser.getClass().getSimpleName().equalsIgnoreCase("klingon")) {
                    System.out.println(ser.getClass().getSimpleName()
                            + "-" + ser.getNombre() + "-" + (((Klingon) ser).getFuerza()));
                } else if (ser.getClass().getSimpleName().equalsIgnoreCase("nibirian")) {
                    System.out.println(ser.getClass().getSimpleName()
                            + "-" + ser.getNombre() + "-" + (((Nibirian) ser).getAlimentacion()));
                } else if (ser.getClass().getSimpleName().equalsIgnoreCase("vulcan")) {
                    System.out.println(ser.getClass().getSimpleName()
                            + "-" + ser.getNombre() + "-" + (((Vulcan) ser).getMeditacion()));
                }
            }
        }
    }

    /**
     * Funcion para modificar los seres de Humanos, Vulcanos y Klingons.
     *
     * @param nombreSer  el nombre del ser.
     * @param valorNuevo valor que queremos poner nuevo.
     * @throws MiExcepcion en el caso de que el ser no exista.
     */
    private static void modificarSer(String nombreSer, String valorNuevo) throws MiExcepcion {
        int valorCambiar = Integer.parseInt(valorNuevo);

        if (!mirarName(nombreSer)) {
            for (int i = 0; i < seres.size(); i++) {
                if (seres.get(i).getNombre().equalsIgnoreCase(nombreSer)) {
                    switch (seres.get(i).getClass().getSimpleName().toLowerCase()) {
                        case "human":
                            Human serH = (Human) seres.get(i);
                            serH.setEdad(valorCambiar);
                            modificarFicheroSer(seres.get(i), valorCambiar);
                            break;
                        case "vulcan":
                            Vulcan serV = (Vulcan) seres.get(i);
                            serV.setMeditacion(valorCambiar);
                            modificarFicheroSer(seres.get(i), valorCambiar);
                            break;
                        case "klingon":
                            Klingon serK = (Klingon) seres.get(i);
                            serK.setFuerza(valorCambiar);
                            modificarFicheroSer(seres.get(i), valorCambiar);

                            break;
                    }
                    System.out.println("< OK: Ser modificado correctamente >");
                }
            }
        } else {
            throw new MiExcepcion("< ERROR 007: No existe ningún ser con ese nombre >");
        }
    }

    /**
     * funcion para modificar el fichero del planeta donde esta guardado el ser modificado.
     *
     * @param ser          el ser a modificar.
     * @param valorCambiar el valor nuevo.
     */
    private static void modificarFicheroSer(Especie ser, int valorCambiar) {
        String planeta = ser.getPlaneta() + ".txt";
        String cadena = "";
        List<String> lineas = new ArrayList<>();
        try {
            File f = new File(planeta);

            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while ((cadena = br.readLine()) != null) {
                String[] linea = cadena.split("-");
                System.out.println(cadena);
                if (!linea[1].equalsIgnoreCase(ser.getNombre())) {
                    lineas.add(cadena);
                } else {
                    String[] letras = cadena.split("-");
                    String cadenaNew = letras[0] + "-" + letras[1] + "-" + valorCambiar;
                    lineas.add(cadenaNew);
                }
            }

            br.close();
            fr.close();
            f.delete();
            reCrearMundo(planeta, lineas);


        } catch (IOException ex) {
            System.out.println("Error borrar ser del planeta " + ex.getMessage());
        }
    }

    /**
     * al modificar el ser toca modificar el fichero
     * en mi caso guardamos todo en un arrayList y borramos el fichero luego modificamos y recreamos el fichero.
     *
     * @param planeta el nombre del fichero a cambiar.
     * @param lineas  la array list de los seres del fichero.
     */
    private static void reCrearMundo(String planeta, List<String> lineas) {
        try {
            File newF = new File(planeta);
            FileWriter fw = new FileWriter(newF);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error borrar ser del planeta " + ex.getMessage());
        }
    }

    /**
     * funcion de p para mostrar los seres de la especie que eligamos
     *
     * @param especie el nombre de la especie a mostrar.
     * @throws MiExcepcion por si pasamos una especie que no exista.
     */
    private static void mostrarSeres(String especie) throws MiExcepcion {
        especie = especie.toLowerCase();

        for (Especie ser : seres) {
            if (ser.getClass().getSimpleName().equalsIgnoreCase(especie)) {
                switch (especie) {
                    case "human":
                        System.out.println(especie + "-" + ser.getNombre() + "-"
                                + ((Human) ser).getEdad() + "-" + ser.getPlaneta());
                        break;
                    case "vulcan":
                        System.out.println(especie + "-" + ser.getNombre() + "-"
                                + ((Vulcan) ser).getMeditacion() + "-" + ser.getPlaneta());
                        break;
                    case "klingon":
                        System.out.println(especie + "-" + ser.getNombre() + "-"
                                + ((Klingon) ser).getFuerza() + "-" + ser.getPlaneta());
                        break;
                    case "nibirian":
                        System.out.println(especie + "-" + ser.getNombre() + "-"
                                + ((Nibirian) ser).getAlimentacion() + "-" + ser.getPlaneta());
                        break;
                    case "andorian":
                        System.out.println(especie + "-" + ser.getNombre() + "-"
                                + (((Andorian) ser).isAenar() ? "aenar" : "noaenar") + "-" + ser.getPlaneta());
                        break;
                    default:
                        throw new MiExcepcion("< ERROR 002: Especie incorrecta >");
                }

            }
        }
    }

    /**
     * para borrar un ser del planeta.
     *
     * @param nombrePersona tenemos que pasarle el nombre del ser a borrar.
     * @throws MiExcepcion en el caso de que pasemos mal el nombre del ser.
     */
    public static void borrarSer(String nombrePersona) throws MiExcepcion {
        if (!mirarName(nombrePersona)) {
            for (int i = 0; i < seres.size(); i++) {
                if (seres.get(i).getNombre().equalsIgnoreCase(nombrePersona)) {
                    borrarSerFichero(seres.get(i));
                    seres.remove(i);
                    System.out.println("<OK : Ser borrado correctamente >");
                }
            }
        } else {
            throw new MiExcepcion("< ERROR 007 : No existe ningún ser con ese nombre >");
        }
    }

    /**
     * para borrar el ser del fichero
     *
     * @param ser le pasamos el Objeto del ser a borrar.
     */
    private static void borrarSerFichero(Especie ser) {
        String planeta = ser.getPlaneta() + ".txt";
        String cadena = "";
        List<String> lineas = new ArrayList<>();
        try {
            File f = new File(planeta);

            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while ((cadena = br.readLine()) != null) {
                String[] linea = cadena.split("-");
                if (!linea[1].equalsIgnoreCase(ser.getNombre())) {
                    lineas.add(cadena);
                }
            }
            br.close();
            fr.close();
            f.delete();
            reCrearMundo(planeta, lineas);

        } catch (IOException ex) {
            System.out.println("Error borrar ser del planeta " + ex.getMessage());
        }
    }

    /**
     * para crear los seres.
     *
     * @param especie       el nombre de la especie a crear.
     * @param planeta       el nombre del planeta a crear.
     * @param nombrePersona el nombre de la persona a crear
     * @param especial      el dato especial de la especie.
     */
    public static void crearSer(String especie, String planeta, String nombrePersona, String especial) {
        planeta = planeta.toLowerCase();
        try {
            if (mirarPlaneta(planeta) != -1) {
                switch (especie.toLowerCase()) {
                    case "human":
                        if (mirarName(nombrePersona)) {
                            int especials = Integer.parseInt(especial);
                            seres.add(new Human(especials, nombrePersona, planeta));
                            // vamos a guardara en el fichero el ser
                            añadirSer(seres.get(seres.size() - 1), planeta, especie);
                            System.out.println("< OK: Ser censado correctamente en el planeta >");
                        } else {
                            throw new MiExcepcion("< ERROR 006: Ya existe un ser censado con ese nombre >");
                        }
                        break;
                    case "andorian":
                        if (mirarName(nombrePersona)) {
                            if (planetas.get(mirarPlaneta(planeta)).rivalidad(especie)) {
                                seres.add(new Andorian(especial, nombrePersona, planeta));
                                añadirSer(seres.get(seres.size() - 1), planeta, especie);
                                System.out.println("< OK: Ser censado correctamente en el planeta >");
                            } else {
                                throw new MiExcepcion("< ERROR 005: No se puede registrar ese ser en ese planeta >");
                            }
                        } else {
                            throw new MiExcepcion("< ERROR 006: Ya existe un ser censado con ese nombre >");
                        }
                        break;
                    case "klingon":
                        if (mirarName(nombrePersona)) {
                            int especials = Integer.parseInt(especial);
                            seres.add(new Klingon(especials, nombrePersona, planeta));
                            añadirSer(seres.get(seres.size() - 1), planeta, especie);
                            System.out.println("< OK: Ser censado correctamente en el planeta >");
                        } else {
                            throw new MiExcepcion("< ERROR 006: Ya existe un ser censado con ese nombre >");
                        }
                        break;
                    case "nibirian":
                        if (mirarName(nombrePersona)) {
                            seres.add(new Nibirian(especial, nombrePersona, planeta));
                            añadirSer(seres.get(seres.size() - 1), planeta, especie);
                            System.out.println("< OK: Ser censado correctamente en el planeta >");
                        } else {
                            throw new MiExcepcion("< ERROR 006: Ya existe un ser censado con ese nombre >");
                        }
                        break;
                    case "vulcan":
                        if (mirarName(nombrePersona)) {
                            if (planetas.get(mirarPlaneta(planeta)).rivalidad(especie)) {
                                int especials = Integer.parseInt(especial);
                                seres.add(new Vulcan(especials, nombrePersona, planeta));
                                añadirSer(seres.get(seres.size() - 1), planeta, especie);
                                System.out.println("< OK: Ser censado correctamente en el planeta >");
                            } else {
                                throw new MiExcepcion("< ERROR 005: No se puede registrar ese ser en ese planeta >");
                            }
                        } else {
                            throw new MiExcepcion("< ERROR 006: Ya existe un ser censado con ese nombre >");
                        }
                        break;
                    default:
                        throw new MiExcepcion("< ERROR 002: Especie incorrecta >");
                }
            } else {
                throw new MiExcepcion("< ERROR 003: Planeta incorrecto >");
            }
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * para añadir el ser a los fichero.
     *
     * @param ser     el objeto ser creado anteriormente.
     * @param planeta el nombre del planeta donde guardamos el ser.
     * @param especie la especie de nuestro ser creado.
     * @throws MiExcepcion para los casos de pasar mal los datos tenemos todo vigilado.
     */
    private static void añadirSer(Especie ser, String planeta, String especie) throws MiExcepcion {
        try {
            File f = new File(planeta + ".txt");
            FileWriter fw = new FileWriter(f, true);

            BufferedWriter bw = new BufferedWriter(fw);
            switch (especie) {
                case "human":
                    bw.write("human-" + ser.getNombre() + "-" + ((Human) ser).getEdad());
                    break;
                case "vulcan":
                    bw.write("vulcan-" + ser.getNombre() + "-" + ((Vulcan) ser).getMeditacion());
                    break;
                case "klingon":
                    bw.write("klingon-" + ser.getNombre() + "-" + ((Klingon) ser).getFuerza());
                    break;
                case "andorian":
                    bw.write("andorian-" + ser.getNombre() + "-" +
                            (((Andorian) ser).isAenar() ? "aenar" : "noaenar"));
                    break;
                case "nibirian":
                    bw.write("nibirian-" + ser.getNombre() + "-" + ((Nibirian) ser).getAlimentacion());
            }
            bw.newLine();
            bw.close();

        } catch (IOException ex) {
            throw new MiExcepcion("Error fichero");
        }

    }

    /**
     * la comprobacion del nombre del planeta
     *
     * @param planeta nombre planeta
     * @return un int de si es erroneo el nombre devuelve -1
     */
    public static int mirarPlaneta(String planeta) {
        switch (planeta) {
            case "vulcano":
                return 0;
            case "nibiru":
                return 1;
            case "andoria":
                return 2;
            case "kronos":
                return 3;
            default:
                return -1;
        }
    }

    /**
     * comprobar el nombre del ser si existe de arayList seres.
     *
     * @param nombrePersona nombre del ser.
     * @return false si existe o true.
     */
    public static boolean mirarName(String nombrePersona) {
        for (Especie ser : seres) {
            if (nombrePersona.equalsIgnoreCase(ser.getNombre())) {
                return false;
            }
        }
        return true;
    }

}
