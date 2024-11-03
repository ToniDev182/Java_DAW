/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aeropuerto_examen;

import static aeropuerto_examen.Aeropuerto_Examen.scanner;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class GestorAeropuerto {

    private HashMap<UUID, Avion> aviones = new HashMap<>();
    private HashMap<UUID, Vuelo> vuelos = new HashMap<>();

    // leer avion
    public static Avion leerAvion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la compañía:");
        String compania = scanner.nextLine();

        System.out.println("Introduce el tipo de avión:");
        String tipo = scanner.nextLine();

        System.out.println("Introduce el número de pasajeros:");
        int nPasajeros = scanner.nextInt();

        return new Avion(compania, tipo, nPasajeros);
    }

    // metodo para leer el vuelo 
    public static Vuelo leerVuelo() {
        Scanner scanner = new Scanner(System.in); 

        System.out.println("Introduce la ciudad de origen:");
        String ciuOrigen = scanner.nextLine();

        System.out.println("Introduce la ciudad de destino:");
        String ciuDestino = scanner.nextLine();

        System.out.println("Introduce la fecha del vuelo (formato dd/MM/yyyy):");
        String fechaStr = scanner.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = dateFormat.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Introduce la fecha en formato dd/MM/yyyy.");
        }

        return new Vuelo(fecha, ciuOrigen, ciuDestino);
    }

    // Añadir avión al HashMap
    public void añadirAvion(Avion avion) {
        aviones.put(avion.getCodAvion(), avion);
    }

    // Añadir vuelo al HashMap
    public void añadirVuelo(Vuelo vuelo) {
        vuelos.put(vuelo.getCodVuelo(), vuelo);
    }

    // Mostrar un avión por ID
    public Avion mostrarAvion(UUID codAvion) {
        return aviones.get(codAvion);
    }

    // Mostrar un vuelo por ID
    public Vuelo mostrarVuelo(UUID codVuelo) {
        return vuelos.get(codVuelo);
    }

    // Modificar un avión
    public void modificarAvion(UUID codAvion) {

        Avion avionExistente = aviones.get(codAvion);
        if (avionExistente != null) {
            int opcionModificacion = Integer.parseInt(scanner.nextLine());
            System.out.println("Elija que desea modificar/n 1 compañia /n 2 Tipo /n 3 Nº pasajeros /n 4 Cancelar");
            Scanner scanner = new Scanner(System.in);
            switch (opcionModificacion) {
                case 1:
                    System.out.println("Compañia: " + avionExistente.getCompañia());
                    String nuevaCompañia = scanner.nextLine();
                    aviones.get(codAvion).setCompañia(nuevaCompañia);
                    break;
                case 2:
                    System.out.println("Tipo" + avionExistente.getTipo());
                    String nuevoTipo = scanner.nextLine();
                    aviones.get(codAvion).setTipo(nuevoTipo);
                    break;
                case 3:
                    System.out.println("Nº pasajeros " + avionExistente.getnPasajeros());
                    int nuevoNumeroPasajeros = scanner.nextInt();
                    aviones.get(codAvion).setnPasajeros(nuevoNumeroPasajeros);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");

            }

        } else {
            System.out.println("Avión no encontrado.");
        }
    }

    // Modificar un vuelo
    public void modificarVuelo(UUID codVuelo) {

        Vuelo vueloExistente = vuelos.get(codVuelo);
        if (vueloExistente != null) {
            int opcionModificacion = Integer.parseInt(scanner.nextLine());
            System.out.println("Elija que desea modificar/n 1 Ciudad Origen /n 2 Ciudad Destino /n 3 Nº Fecha /n 4 Cancelar");
            Scanner scanner = new Scanner(System.in);
            switch (opcionModificacion) {
                case 1:
                    System.out.println("Ciudad Origen: " + vueloExistente.getCiuOrigen());
                    String nuevaCiudadOrigen = scanner.nextLine();
                    vuelos.get(codVuelo).setCiuOrigen(nuevaCiudadOrigen);
                    break;
                case 2:
                    System.out.println("Ciudad Destino " + vueloExistente.getCiuDestino());
                    String nuevaCiudadDestino = scanner.nextLine();
                    vuelos.get(codVuelo).setCiuDestino(nuevaCiudadDestino);
                    break;
                case 3:
                    System.out.println("Fecha (aaaa/mm/dd) " + vueloExistente.getFecha());
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                    String dateInString = scanner.nextLine();
                    Date date;
                    try {
                        date = formatter.parse(dateInString);
                        vuelos.get(codVuelo).setFecha(date);

                    } catch (ParseException ex) {
                        System.out.println("ha fallado la fecha revise el formato en el que la ha escrito");
                        Logger.getLogger(GestorAeropuerto.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;

                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");

            }

        } else {
            System.out.println("Avión no encontrado.");
        }
    }

    // Borrar un avión
    public void borrarAvion(UUID codAvion) {
        aviones.remove(codAvion);
    }

    // Borrar un vuelo
    public void borrarVuelo(UUID codVuelo) {
        vuelos.remove(codVuelo);
    }

    // Ordenar los aviones por su código y por su tipo
    public List<Avion> avionesOrdenados() {
        List<Avion> listaAviones = new ArrayList<>(aviones.values());
        listaAviones.sort(Comparator.comparing(Avion::getCodAvion).thenComparing(Avion::getTipo));
        return listaAviones;
    }

    // Ordenar los vuelos por fecha
    public List<Vuelo> vuelosOrdenados() {
        List<Vuelo> listaVuelos = new ArrayList<>(vuelos.values());
        listaVuelos.sort(Comparator.comparing(Vuelo::getFecha));
        return listaVuelos;
    }

    // Ordenar los vuelos por fecha y filtrar por una fecha específica
    public List<Vuelo> vuelosOrdenadosParaUnaFecha(Date fechaIntroducida) {
        List<Vuelo> listaVuelos = new ArrayList<>(vuelos.values());
        listaVuelos.sort(Comparator.comparing(Vuelo::getFecha));
        
        List<Vuelo> vuelosFiltrados = new ArrayList<>();
        for (Vuelo vuelo : listaVuelos) {
            if (esMismaFecha(vuelo.getFecha(), fechaIntroducida)) {
                vuelosFiltrados.add(vuelo);
            }
        }
        return vuelosFiltrados;
    }
    
    // Comparar dos fechas sin tener en cuenta el tiempo (solo la fecha)
    private boolean esMismaFecha(Date fecha1, Date fecha2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha1).equals(sdf.format(fecha2));
    }

    // Guardar datos de aviones en un archivo
    public void guardarDatosAviones() {
        File archivoAviones = new File("datos_Aviones.txt");
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivoAviones))) {
            salida.writeObject(aviones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar datos de vuelos en un archivo
    public void guardarDatosVuelos() {
        File archivoVuelos = new File("datos_Vuelos.bin");
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivoVuelos))) {
            salida.writeObject(vuelos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar datos de archivos
    public void cargarDatos() {
        File archivoAviones = new File("datos_Aviones.txt");
        if (archivoAviones.exists() && archivoAviones.isFile()) {
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivoAviones))) {
                aviones = (HashMap<UUID, Avion>) entrada.readObject();
                System.out.println("Datos de aviones cargados correctamente");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        File archivoVuelos = new File("datos_Vuelos.bin");
        if (archivoVuelos.exists() && archivoVuelos.isFile()) {
            try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivoVuelos))) {
                vuelos = (HashMap<UUID, Vuelo>) entrada.readObject();
                System.out.println("Datos de vuelos cargados correctamente");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * *
 * CON ARRAYLIST
 *
 *
 *
 *
 * public class GestorAeropuerto { private ArrayList<Avion> aviones = new
 * ArrayList<>(); private ArrayList<Vuelo> vuelos = new ArrayList<>();
 *
 * // Añadir avión al ArrayList public void añadirAvion(Avion avion) {
 * aviones.add(avion); }
 *
 * // Añadir vuelo al ArrayList public void añadirVuelo(Vuelo vuelo) {
 * vuelos.add(vuelo); }
 *
 * // Mostrar un avión por su posición en el ArrayList public Avion
 * mostrarAvion(int index) { if (index >= 0 && index < aviones.size()) {
 * return aviones.get(index);
 * } else {
 * System.out.println("Índice de avión fuera de rango.");
 * return null;
 * }
 * }
 *
 * // Mostrar un vuelo por su posición en el ArrayList
 * public Vuelo mostrarVuelo(int index) {
 * if (index >= 0 && index < vuelos.size()) {
 * return vuelos.get(index);
 * } else {
 * System.out.println("Índice de vuelo fuera de rango.");
 * return null;
 * }
 * }
 *
 * // Modificar un avión por su posición en el ArrayList
 * public void modificarAvion(int index) {
 * if (index >= 0 && index < aviones.size()) {
 * Avion nuevoAvion = Avion.leerAvion();
 * aviones.set(index, nuevoAvion);
 * } else {
 * System.out.println("Índice de avión fuera de rango.");
 * }
 * }
 *
 * // Modificar un vuelo por su posición en el ArrayList
 * public void modificarVuelo(int index) {
 * if (index >= 0 && index < vuelos.size()) {
 * Vuelo nuevoVuelo = Vuelo.leerVuelo();
 * vuelos.set(index, nuevoVuelo);
 * } else {
 * System.out.println("Índice de vuelo fuera de rango.");
 * }
 * }
 *
 * // Borrar un avión por su posición en el ArrayList
 * public void borrarAvion(int index) {
 * if (index >= 0 && index < aviones.size()) {
 * aviones.remove(index);
 * } else {
 * System.out.println("Índice de avión fuera de rango.");
 * }
 * }
 *
 * // Borrar un vuelo por su posición en el ArrayList
 * public void borrarVuelo(int index) {
 * if (index >= 0 && index < vuelos.size()) { vuelos.remove(index); } else {
 * System.out.println("Índice de vuelo fuera de rango."); } }
 *
 * // Ordenar los aviones por su código y por su tipo public List<Avion>
 * avionesOrdenados() { ArrayList<Avion> listaAvionesOrdenados = new
 * ArrayList<>(aviones);
 * listaAvionesOrdenados.sort(Comparator.comparing(Avion::getCodAvion).thenComparing(Avion::getTipo));
 * return listaAvionesOrdenados; }
 *
 * // Ordenar los vuelos por fecha public List<Vuelo> vuelosOrdenados() {
 * ArrayList<Vuelo> listaVuelosOrdenados = new ArrayList<>(vuelos);
 * listaVuelosOrdenados.sort(Comparator.comparing(Vuelo::getFecha)); return
 * listaVuelosOrdenados; }
 *
 *
 * CON LINKEDLIST
 *
 * public class GestorAeropuerto { private LinkedList<Avion> aviones = new
 * LinkedList<>(); private LinkedList<Vuelo> vuelos = new LinkedList<>();
 *
 * // Añadir avión a la lista enlazada public void añadirAvion(Avion avion) {
 * aviones.add(avion); }
 *
 * // Añadir vuelo a la lista enlazada public void añadirVuelo(Vuelo vuelo) {
 * vuelos.add(vuelo); }
 *
 * // Mostrar un avión por su posición en la lista enlazada public Avion
 * mostrarAvion(int index) { if (index >= 0 && index < aviones.size()) {
 * return aviones.get(index);
 * } else {
 * System.out.println("Índice de avión fuera de rango.");
 * return null;
 * }
 * }
 *
 * // Mostrar un vuelo por su posición en la lista enlazada
 * public Vuelo mostrarVuelo(int index) {
 * if (index >= 0 && index < vuelos.size()) {
 * return vuelos.get(index);
 * } else {
 * System.out.println("Índice de vuelo fuera de rango.");
 * return null;
 * }
 * }
 *
 * // Modificar un avión por su posición en la lista enlazada
 * public void modificarAvion(int index) {
 * if (index >= 0 && index < aviones.size()) {
 * Avion nuevoAvion = Avion.leerAvion();
 * aviones.set(index, nuevoAvion);
 * } else {
 * System.out.println("Índice de avión fuera de rango.");
 * }
 * }
 *
 * // Modificar un vuelo por su posición en la lista enlazada
 * public void modificarVuelo(int index) {
 * if (index >= 0 && index < vuelos.size()) {
 * Vuelo nuevoVuelo = Vuelo.leerVuelo();
 * vuelos.set(index, nuevoVuelo);
 * } else {
 * System.out.println("Índice de vuelo fuera de rango.");
 * }
 * }
 *
 * // Borrar un avión por su posición en la lista enlazada
 * public void borrarAvion(int index) {
 * if (index >= 0 && index < aviones.size()) {
 * aviones.remove(index);
 * } else {
 * System.out.println("Índice de avión fuera de rango.");
 * }
 * }
 *
 * // Borrar un vuelo por su posición en la lista enlazada
 * public void borrarVuelo(int index) {
 * if (index >= 0 && index < vuelos.size()) { vuelos.remove(index); } else {
 * System.out.println("Índice de vuelo fuera de rango."); } }
 *
 * // Ordenar los aviones por su código y por su tipo public List<Avion>
 * avionesOrdenados() { LinkedList<Avion> listaAvionesOrdenados = new
 * LinkedList<>(aviones);
 * listaAvionesOrdenados.sort(Comparator.comparing(Avion::getCodAvion).thenComparing(Avion::getTipo));
 * return listaAvionesOrdenados; }
 *
 * // Ordenar los vuelos por fecha public List<Vuelo> vuelosOrdenados() {
 * LinkedList<Vuelo> listaVuelosOrdenados = new LinkedList<>(vuelos);
 * listaVuelosOrdenados.sort(Comparator.comparing(Vuelo::getFecha)); return
 * listaVuelosOrdenados; }
 *
 *
 *
 * CON HASHSET
 *
 * public class GestorAeropuerto { private HashSet<Avion> aviones = new
 * HashSet<>(); private HashSet<Vuelo> vuelos = new HashSet<>();
 *
 * // Añadir avión al HashSet public void añadirAvion(Avion avion) {
 * aviones.add(avion); }
 *
 * // Añadir vuelo al HashSet public void añadirVuelo(Vuelo vuelo) {
 * vuelos.add(vuelo); }
 *
 * // Mostrar un avión por su código public Avion mostrarAvion(int codAvion) {
 * for (Avion avion : aviones) { if (avion.getCodAvion() == codAvion) { return
 * avion; } } System.out.println("Avión no encontrado."); return null; }
 *
 * // Mostrar un vuelo por su código public Vuelo mostrarVuelo(int codVuelo) {
 * for (Vuelo vuelo : vuelos) { if (vuelo.getCodVuelo() == codVuelo) { return
 * vuelo; } } System.out.println("Vuelo no encontrado."); return null; }
 *
 * // Modificar un avión por su código public void modificarAvion(int codAvion)
 * { for (Avion avion : aviones) { if (avion.getCodAvion() == codAvion) { Avion
 * nuevoAvion = Avion.leerAvion(); aviones.remove(avion);
 * aviones.add(nuevoAvion); return; } } System.out.println("Avión no
 * encontrado."); }
 *
 * // Modificar un vuelo por su código public void modificarVuelo(int codVuelo)
 * { for (Vuelo vuelo : vuelos) { if (vuelo.getCodVuelo() == codVuelo) { Vuelo
 * nuevoVuelo = Vuelo.leerVuelo(); vuelos.remove(vuelo); vuelos.add(nuevoVuelo);
 * return; } } System.out.println("Vuelo no encontrado."); }
 *
 * // Borrar un avión por su código public void borrarAvion(int codAvion) { for
 * (Avion avion : aviones) { if (avion.getCodAvion() == codAvion) {
 * aviones.remove(avion); return; } } System.out.println("Avión no
 * encontrado."); }
 *
 * // Borrar un vuelo por su código public void borrarVuelo(int codVuelo) { for
 * (Vuelo vuelo : vuelos) { if (vuelo.getCodVuelo() == codVuelo) {
 * vuelos.remove(vuelo); return; } } System.out.println("Vuelo no encontrado.");
 * }
 *
 * // Ordenar los aviones por su código y por su tipo public List<Avion>
 * avionesOrdenados() { List<Avion> listaAvionesOrdenados = new
 * ArrayList<>(aviones);
 * listaAvionesOrdenados.sort(Comparator.comparing(Avion::getCodAvion).thenComparing(Avion::getTipo));
 * return listaAvionesOrdenados; }
 *
 * // Ordenar los vuelos por fecha public List<Vuelo> vuelosOrdenados() {
 * List<Vuelo> listaVuelosOrdenados = new ArrayList<>(vuelos);
 * listaVuelosOrdenados.sort(Comparator.comparing(Vuelo::getFecha)); return
 * listaVuelosOrdenados; }
 *
 *
 * CON LINKEDHASHSET
 *
 * public class GestorAeropuerto { private LinkedHashSet<Avion> aviones = new
 * LinkedHashSet<>(); private LinkedHashSet<Vuelo> vuelos = new
 * LinkedHashSet<>();
 *
 * // Añadir avión al LinkedHashSet public void añadirAvion(Avion avion) {
 * aviones.add(avion); }
 *
 * // Añadir vuelo al LinkedHashSet public void añadirVuelo(Vuelo vuelo) {
 * vuelos.add(vuelo); }
 *
 * // Mostrar un avión por su código public Avion mostrarAvion(int codAvion) {
 * for (Avion avion : aviones) { if (avion.getCodAvion() == codAvion) { return
 * avion; } } System.out.println("Avión no encontrado."); return null; }
 *
 * // Mostrar un vuelo por su código public Vuelo mostrarVuelo(int codVuelo) {
 * for (Vuelo vuelo : vuelos) { if (vuelo.getCodVuelo() == codVuelo) { return
 * vuelo; } } System.out.println("Vuelo no encontrado."); return null; }
 *
 * // Modificar un avión por su código public void modificarAvion(int codAvion)
 * { for (Avion avion : aviones) { if (avion.getCodAvion() == codAvion) { Avion
 * nuevoAvion = Avion.leerAvion(); aviones.remove(avion);
 * aviones.add(nuevoAvion); return; } } System.out.println("Avión no
 * encontrado."); }
 *
 * // Modificar un vuelo por su código public void modificarVuelo(int codVuelo)
 * { for (Vuelo vuelo : vuelos) { if (vuelo.getCodVuelo() == codVuelo) { Vuelo
 * nuevoVuelo = Vuelo.leerVuelo(); vuelos.remove(vuelo); vuelos.add(nuevoVuelo);
 * return; } } System.out.println("Vuelo no encontrado."); }
 *
 * // Borrar un avión por su código public void borrarAvion(int codAvion) { for
 * (Avion avion : aviones) { if (avion.getCodAvion() == codAvion) {
 * aviones.remove(avion); return; } } System.out.println("Avión no
 * encontrado."); }
 *
 * // Borrar un vuelo por su código public void borrarVuelo(int codVuelo) { for
 * (Vuelo vuelo : vuelos) { if (vuelo.getCodVuelo() == codVuelo) {
 * vuelos.remove(vuelo); return; } } System.out.println("Vuelo no encontrado.");
 * }
 *
 * // Ordenar los aviones por su código y por su tipo public List<Avion>
 * avionesOrdenados() { List<Avion> listaAvionesOrdenados = new
 * ArrayList<>(aviones);
 * listaAvionesOrdenados.sort(Comparator.comparing(Avion::getCodAvion).thenComparing(Avion::getTipo));
 * return listaAvionesOrdenados; }
 *
 * // Ordenar los vuelos por fecha public List<Vuelo> vuelosOrdenados() {
 * List<Vuelo> listaVuelosOrdenados = new ArrayList<>(vuelos);
 * listaVuelosOrdenados.sort(Comparator.comparing(Vuelo::getFecha)); return
 * listaVuelosOrdenados; }
 *
 *
 * CON TREESET
 *
 * public class GestorAeropuerto { private TreeSet<Avion> aviones = new
 * TreeSet<>(); private TreeSet<Vuelo> vuelos = new TreeSet<>();
 *
 * // Añadir avión al TreeSet public void añadirAvion(Avion avion) {
 * aviones.add(avion); }
 *
 * // Añadir vuelo al TreeSet public void añadirVuelo(Vuelo vuelo) {
 * vuelos.add(vuelo); }
 *
 * // Mostrar un avión por su código public Avion mostrarAvion(int codAvion) {
 * for (Avion avion : aviones) { if (avion.getCodAvion() == codAvion) { return
 * avion; } } System.out.println("Avión no encontrado."); return null; }
 *
 * // Mostrar un vuelo por su código public Vuelo mostrarVuelo(int codVuelo) {
 * for (Vuelo vuelo : vuelos) { if (vuelo.getCodVuelo() == codVuelo) { return
 * vuelo; } } System.out.println("Vuelo no encontrado."); return null; }
 *
 * // Modificar un avión por su código public void modificarAvion(int codAvion)
 * { for (Avion avion : aviones) { if (avion.getCodAvion() == codAvion) { Avion
 * nuevoAvion = Avion.leerAvion(); aviones.remove(avion);
 * aviones.add(nuevoAvion); return; } } System.out.println("Avión no
 * encontrado."); }
 *
 * // Modificar un vuelo por su código public void modificarVuelo(int codVuelo)
 * { for (Vuelo vuelo : vuelos) { if (vuelo.getCodVuelo() == codVuelo) { Vuelo
 * nuevoVuelo = Vuelo.leerVuelo(); vuelos.remove(vuelo); vuelos.add(nuevoVuelo);
 * return; } } System.out.println("Vuelo no encontrado."); }
 *
 * // Borrar un avión por su código public void borrarAvion(int codAvion) { for
 * (Avion avion : aviones) { if (avion.getCodAvion() == codAvion) {
 * aviones.remove(avion); return; } } System.out.println("Avión no
 * encontrado."); }
 *
 * // Borrar un vuelo por su código public void borrarVuelo(int codVuelo) { for
 * (Vuelo vuelo : vuelos) { if (vuelo.getCodVuelo() == codVuelo) {
 * vuelos.remove(vuelo); return; } } System.out.println("Vuelo no encontrado.");
 * }
 *
 * // Ordenar los aviones por su código y por su tipo public List<Avion>
 * avionesOrdenados() { List<Avion> listaAvionesOrdenados = new
 * ArrayList<>(aviones);
 * listaAvionesOrdenados.sort(Comparator.comparing(Avion::getCodAvion).thenComparing(Avion::getTipo));
 * return listaAvionesOrdenados; }
 *
 * // Ordenar los vuelos por fecha public List<Vuelo> vuelosOrdenados() {
 * List<Vuelo> listaVuelosOrdenados = new ArrayList<>(vuelos);
 * listaVuelosOrdenados.sort(Comparator.comparing(Vuelo::getFecha)); return
 * listaVuelosOrdenados; }
 *
 *
 *
 * CON LINKEDHASHMAP
 *
 * public class GestorAeropuerto { private LinkedHashMap<Integer, Avion> aviones
 * = new LinkedHashMap<>(); private LinkedHashMap<Integer, Vuelo> vuelos = new
 * LinkedHashMap<>();
 *
 * // Añadir avión al LinkedHashMap public void añadirAvion(Avion avion) {
 * aviones.put(avion.getCodAvion(), avion); }
 *
 * // Añadir vuelo al LinkedHashMap public void añadirVuelo(Vuelo vuelo) {
 * vuelos.put(vuelo.getCodVuelo(), vuelo); }
 *
 * // Mostrar un avión por ID public Avion mostrarAvion(int codAvion) { return
 * aviones.get(codAvion); }
 *
 * // Mostrar un vuelo por ID public Vuelo mostrarVuelo(int codVuelo) { return
 * vuelos.get(codVuelo); }
 *
 * // Modificar un avión public void modificarAvion(int codAvion) { Avion
 * avionExistente = aviones.get(codAvion); if (avionExistente != null) { Avion
 * nuevoAvion = Avion.leerAvion(); aviones.put(codAvion, nuevoAvion); } else {
 * System.out.println("Avión no encontrado."); } }
 *
 * // Modificar un vuelo public void modificarVuelo(int codVuelo) { Vuelo
 * vueloExistente = vuelos.get(codVuelo); if (vueloExistente != null) { Vuelo
 * nuevoVuelo = Vuelo.leerVuelo(); vuelos.put(codVuelo, nuevoVuelo); } else {
 * System.out.println("Vuelo no encontrado."); } }
 *
 * // Borrar un avión public void borrarAvion(int codAvion) {
 * aviones.remove(codAvion); }
 *
 * // Borrar un vuelo public void borrarVuelo(int codVuelo) {
 * vuelos.remove(codVuelo); }
 *
 * // Ordenar los aviones por su código y por su tipo public List<Avion>
 * avionesOrdenados() { List<Avion> listaAviones = new
 * ArrayList<>(aviones.values());
 * listaAviones.sort(Comparator.comparing(Avion::getCodAvion).thenComparing(Avion::getTipo));
 * return listaAviones; }
 *
 * // Ordenar los vuelos por fecha public List<Vuelo> vuelosOrdenados() {
 * List<Vuelo> listaVuelos = new ArrayList<>(vuelos.values());
 * listaVuelos.sort(Comparator.comparing(Vuelo::getFecha)); return listaVuelos;
 * }
 *
 *
 * CON TREE MAP
 *
 * public class GestorAeropuerto { private TreeMap<Integer, Avion> aviones = new
 * TreeMap<>(); private TreeMap<Integer, Vuelo> vuelos = new TreeMap<>();
 *
 * // Añadir avión al TreeMap public void añadirAvion(Avion avion) {
 * aviones.put(avion.getCodAvion(), avion); }
 *
 * // Añadir vuelo al TreeMap public void añadirVuelo(Vuelo vuelo) {
 * vuelos.put(vuelo.getCodVuelo(), vuelo); }
 *
 * // Mostrar un avión por ID public Avion mostrarAvion(int codAvion) { return
 * aviones.get(codAvion); }
 *
 * // Mostrar un vuelo por ID public Vuelo mostrarVuelo(int codVuelo) { return
 * vuelos.get(codVuelo); }
 *
 * // Modificar un avión public void modificarAvion(int codAvion) { Avion
 * avionExistente = aviones.get(codAvion); if (avionExistente != null) { Avion
 * nuevoAvion = Avion.leerAvion(); aviones.put(codAvion, nuevoAvion); } else {
 * System.out.println("Avión no encontrado."); } }
 *
 * // Modificar un vuelo public void modificarVuelo(int codVuelo) { Vuelo
 * vueloExistente = vuelos.get(codVuelo); if (vueloExistente != null) { Vuelo
 * nuevoVuelo = Vuelo.leerVuelo(); vuelos.put(codVuelo, nuevoVuelo); } else {
 * System.out.println("Vuelo no encontrado."); } }
 *
 * // Borrar un avión public void borrarAvion(int codAvion) {
 * aviones.remove(codAvion); }
 *
 * // Borrar un vuelo public void borrarVuelo(int codVuelo) {
 * vuelos.remove(codVuelo); }
 *
 * // Ordenar los aviones por su código y por su tipo public List<Avion>
 * avionesOrdenados() { List<Avion> listaAviones = new
 * ArrayList<>(aviones.values());
 * listaAviones.sort(Comparator.comparing(Avion::getCodAvion).thenComparing(Avion::getTipo));
 * return listaAviones; }
 *
 * // Ordenar los vuelos por fecha public List<Vuelo> vuelosOrdenados() {
 * List<Vuelo> listaVuelos = new ArrayList<>(vuelos.values());
 * listaVuelos.sort(Comparator.comparing(Vuelo::getFecha)); return listaVuelos;
 * }
 *
 *
 */
