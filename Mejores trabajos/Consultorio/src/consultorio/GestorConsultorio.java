/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package consultorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio Atienza Cano
 */
public class GestorConsultorio {

    private HashMap<Integer, Paciente> pacientes = new HashMap<>();
    private HashMap<Integer, Medico> medicos = new HashMap<>();
    private ArrayList<Cita> citas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    //  metodo leer y guardar un paciente
    public void leerPaciente() {

        System.out.println("Introduzca el Dni del paciente");
        int dniPa = scanner.nextInt();
        System.out.println("Introduce el Nombre ");
        String nombrePa = scanner.nextLine();
        System.out.println("Introduce el primer apellido ");
        String apellidoPa1 = scanner.nextLine();
        System.out.println("Introduce el segundo apellido ");
        String apellidoPa2 = scanner.nextLine();
        System.out.println("Introduce el tlf ");
        int tlf = scanner.nextInt();

        pacientes.put(dniPa, new Paciente(dniPa, nombrePa, apellidoPa1, apellidoPa2, tlf));

    }
    // metodo para leer y guardar un paciente
    public void leerMedico() {

        System.out.println("Introduzca el Dni del Medico");
        int dniMe = scanner.nextInt();
        System.out.println("Introduce el Nombre ");
        String nombreMe = scanner.nextLine();
        System.out.println("Introduce el primer apellido ");
        String apellidoMe1 = scanner.nextLine();
        System.out.println("Introduce el segundo apellido ");
        String apellidoMe2 = scanner.nextLine();
        System.out.println("Introduce Codigo de consulta ");
        int cod_consul = scanner.nextInt();

        medicos.put(dniMe, new Medico(dniMe, nombreMe, apellidoMe1, apellidoMe2, cod_consul));
    }
    // metodo para leer y guardar un paciente
    public void leerCita() {

        System.out.println("Introduzca el Dni Paciente");
        int dniPa = scanner.nextInt();
        System.out.println("Introduce el Dni Medico ");
        int dniMe = scanner.nextInt();
        System.out.println("Introduce introduce hora ");
        int hora = scanner.nextInt();

        System.out.println("Introduce la fecha de la consulta (formato dd/MM/yyyy):");
        String fechaStr = scanner.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = dateFormat.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Introduce la fecha en formato dd/MM/yyyy.");
        }

        citas.add(new Cita(dniPa, dniMe, fecha, hora));
    }

    // metodo para buscar una consulta por fecha y dni de un paciente
    public void buscarConsulta() {

        // pedimos los datos, la fecha y el dni tienen que ser parseados 
        System.out.println("Inserte el dni del paciente");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserte la fecha de la cita (dd/MM/yyyy)");
        String fechaConsulta = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // creamos una bandera para comprobar si encontramos almenos una consulta o no, la iniciamos  a false
        // cremos un booleano en array de uno para saltarnos las restricciones del foreach
        boolean[] flag = {false};

        // comprobamos que el dni insertado y la fecha insertada coincidan con alguno dentro del Array
        citas.forEach((cita)
                -> {
            if (cita.getDniPa() == dni && dateFormat.format(cita.getFecha()).equals(fechaConsulta)) {

                System.out.println("la consulta es: " + medicos.get(cita.getDniMe()).getCod_consul());

                flag[0] = true;
            }

        });

        if (flag[0] == false) {
            System.out.println("No se pudo encontrar ningun consulta con estos datos");
        }

    }

    // metodo para buscar una consulta por fecha y dni de un paciente
    public void elimnarCita() {

        // pedimos los datos, la fecha y el dni tienen que ser parseados 
        System.out.println("Inserte el dni del paciente");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserte la hora");
        int horaCita = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserte la fecha de la cita (dd/MM/yyyy)");
        String fechaConsulta = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // creamos una bandera para comprobar si encontramos almenos una consulta o no, la iniciamos  a false
        // cremos un booleano en array de uno para saltarnos las restricciones del foreach
        boolean[] flag = {false};

        // comprobamos que el dni insertado y la fecha insertada coincidan con alguno dentro del Array
        citas.forEach((cita)
                -> {
            if (cita.getDniPa() == dni && dateFormat.format(cita.getFecha()).equals(fechaConsulta) && cita.getHora() == horaCita) {
                citas.remove(cita);

                System.out.println("La cita se ha eliminado con exito ");

                flag[0] = true;
            }

        });

        if (flag[0] == false) {
            System.out.println("No se pudo encontrar ninguna cita con estos datos");
        }

    }
    
// Metodo para imprimir 
    public void imprimirCita() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // obtenemos la cita.. para cada paciente
        for (Integer dni : pacientes.keySet()) {
            citas.forEach((cita)
                    -> {
                //mediante la cita obtenemos el medigo
                if (cita.getDniPa() == dni) {
                    // mediante el medico obtenemos la consulta
                    //  medicos.get(cita.getDniMe()).getCod_consul();

                    BufferedWriter writer;
                    try {
                        writer = new BufferedWriter(new FileWriter("ficheros//" + dni + "_" + dateFormat.format(cita.getFecha()) + ".txt", true)); // append hace que si ya se creo ese archivo no sea sobre escrito
                        writer.write("Citas para el paciente con DNI: " + dni + ", para el día: " + dateFormat.format(cita.getFecha()) + "hora: " + cita.getHora() + "consulta: " + medicos.get(cita.getDniMe()).getCod_consul());
                        // esto es clave ya que cada vez que escribimos una cita metemos un salto de linea esto nos ayudará mas tarde a contar el numero de lineas, es decir el numero de citas del paciente
                        writer.newLine();

                        writer.close();

                    } catch (IOException ex) {
                        Logger.getLogger(GestorConsultorio.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
            );

        }
    }

    public void lecturaNumeroCitas() {

        System.out.println("Inserte el dni del paciente");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserte la fecha de la cita (dd/MM/yyyy)");
        String fechaConsulta = scanner.nextLine();

        try {
            BufferedReader lectura = new BufferedReader(new FileReader("ficheros//" + dni + "_" + fechaConsulta + ".txt"));

            // imprimir todas las lineas en pantalla  
            String linea;
            System.out.println("el paciente tiene " + lectura.lines().count() + " citas registradas");
            while ((linea = lectura.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorConsultorio.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("El dni o la fecha no son correctos");
        } catch (IOException ex) {
            Logger.getLogger(GestorConsultorio.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error leyendo el contenido");
        }
    }
}
/*    public void ordenarCitasPorFecha() {
        Collections.sort(citas);
        System.out.println("Citas ordenadas por fecha:");
        citas.forEach(cita -> {
            System.out.println("DNI Paciente: " + cita.getDniPa() + ", DNI Medico: " + cita.getDniMe() +
                    ", Fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(cita.getFecha()) +
                    ", Hora: " + cita.getHora());
        });
    }    

  // metodo para listar todas las personas por nombre y apellido
    public List<Cita> citaOrdenadaPorFecha() {
        // crea una lista a partir de de los valores del hashMap
        List<Cita> listaCitas = new ArrayList<>(citas);
        // ordena la lista usando comparadores
        listaCitas.sort(Comparator.comparing(Cita::getFecha));
        return listaCitas;
    }
/*

/*

public class GestorConsultorio {

    private HashMap<Integer, Paciente> pacientes = new HashMap<>();
    private HashMap<Integer, Medico> medicos = new HashMap<>();
    private HashMap<Integer, Cita> citas = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private int citaIdCounter = 1; // Counter for unique cita IDs
    
    // Method to read and save a patient
    public void leerPaciente() {
        System.out.println("Introduzca el Dni del paciente");
        int dniPa = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.println("Introduce el Nombre ");
        String nombrePa = scanner.nextLine();
        System.out.println("Introduce el primer apellido ");
        String apellidoPa1 = scanner.nextLine();
        System.out.println("Introduce el segundo apellido ");
        String apellidoPa2 = scanner.nextLine();
        System.out.println("Introduce el tlf ");
        int tlf = scanner.nextInt();

        pacientes.put(dniPa, new Paciente(dniPa, nombrePa, apellidoPa1, apellidoPa2, tlf));
    }

    // Method to read and save a doctor
    public void leerMedico() {
        System.out.println("Introduzca el Dni del Medico");
        int dniMe = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.println("Introduce el Nombre ");
        String nombreMe = scanner.nextLine();
        System.out.println("Introduce el primer apellido ");
        String apellidoMe1 = scanner.nextLine();
        System.out.println("Introduce el segundo apellido ");
        String apellidoMe2 = scanner.nextLine();
        System.out.println("Introduce Codigo de consulta ");
        int cod_consul = scanner.nextInt();

        medicos.put(dniMe, new Medico(dniMe, nombreMe, apellidoMe1, apellidoMe2, cod_consul));
    }

    // Method to read and save an appointment
    public void leerCita() {
        System.out.println("Introduzca el Dni Paciente");
        int dniPa = scanner.nextInt();
        System.out.println("Introduce el Dni Medico ");
        int dniMe = scanner.nextInt();
        System.out.println("Introduce la hora ");
        int hora = scanner.nextInt();

        System.out.println("Introduce la fecha de la consulta (formato dd/MM/yyyy):");
        String fechaStr = scanner.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = null;
        try {
            fecha = dateFormat.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido. Introduce la fecha en formato dd/MM/yyyy.");
            return;
        }

        citas.put(citaIdCounter++, new Cita(dniPa, dniMe, fecha, hora));
    }

    // Method to search for a consultation by date and patient DNI
    public void buscarConsulta() {
        System.out.println("Inserte el dni del paciente");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserte la fecha de la cita (dd/MM/yyyy)");
        String fechaConsulta = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        boolean found = false;

        for (Cita cita : citas.values()) {
            if (cita.getDniPa() == dni && dateFormat.format(cita.getFecha()).equals(fechaConsulta)) {
                System.out.println("La consulta es: " + medicos.get(cita.getDniMe()).getCod_consul());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No se pudo encontrar ninguna consulta con estos datos");
        }
    }

    // Method to delete an appointment
    public void elimnarCita() {
        System.out.println("Inserte el dni del paciente");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserte la hora");
        int horaCita = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserte la fecha de la cita (dd/MM/yyyy)");
        String fechaConsulta = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        boolean found = false;

        for (Integer key : new ArrayList<>(citas.keySet())) {
            Cita cita = citas.get(key);
            if (cita.getDniPa() == dni && dateFormat.format(cita.getFecha()).equals(fechaConsulta) && cita.getHora() == horaCita) {
                citas.remove(key);
                System.out.println("La cita se ha eliminado con éxito ");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No se pudo encontrar ninguna cita con estos datos");
        }
    }

    // Method to print an appointment
    public void imprimirCita() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Integer dni : pacientes.keySet()) {
            for (Cita cita : citas.values()) {
                if (cita.getDniPa() == dni) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("ficheros//" + dni + "_" + dateFormat.format(cita.getFecha()) + ".txt", true))) {
                        writer.write("Citas para el paciente con DNI: " + dni + ", para el día: " + dateFormat.format(cita.getFecha()) + " hora: " + cita.getHora() + " consulta: " + medicos.get(cita.getDniMe()).getCod_consul());
                        writer.newLine();
                    } catch (IOException ex) {
                        Logger.getLogger(GestorConsultorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    // Method to read the number of appointments
    public void lecturaNumeroCitas() {
        System.out.println("Inserte el dni del paciente");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.println("Inserte la fecha de la cita (dd/MM/yyyy)");
        String fechaConsulta = scanner.nextLine();

        try (BufferedReader lectura = new BufferedReader(new FileReader("ficheros//" + dni + "_" + fechaConsulta + ".txt"))) {
            long count = lectura.lines().count();
            System.out.println("El paciente tiene " + count + " citas registradas");
            lectura.lines().forEach(System.out::println);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorConsultorio.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("El dni o la fecha no son correctos");
        } catch (IOException ex) {
            Logger.getLogger(GestorConsultorio.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error leyendo el contenido");
        }
    }
} 

*/