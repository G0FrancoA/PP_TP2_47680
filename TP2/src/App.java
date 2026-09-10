
import Modelo.Estudiante;
import Modelo.EventoUniversitario;
import Modelo.Sala;
import Modelo.actividades.Actividad;
import Modelo.actividades.Charla;
import Modelo.actividades.Taller;
import excepciones.CupoExcedidoException;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws CupoExcedidoException {


        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("50111", "Franco"));
        estudiantes.add(new Estudiante("50112", "Pedro"));
        estudiantes.add(new Estudiante("50113", "Mauro"));

        //1

        EventoUniversitario evento1 = new EventoUniversitario("Franco", "Clase OPP", 15000, false);
        evento1.asignarSala(new Sala(1, "Aula 114"));

        evento1.crearActividad(1, "Introduccion a POO", 10, "Taller");
        evento1.crearActividad(2, "Clase de Java", 1, "Curso");
        evento1.crearActividad(3,"Charla IA",10,"Charla");

        try {
            Actividad tallerEvento1 = evento1.getActividades().get(0);
            Actividad charlaEvento1 = evento1.getActividades().get(1);
            Actividad cursoEvento1 = evento1.getActividades().get(2);

            charlaEvento1.inscribir(estudiantes.get(0));

            tallerEvento1.inscribir(estudiantes.get(0));
            tallerEvento1.inscribir(estudiantes.get(1));

            cursoEvento1.inscribir(estudiantes.get(2));
        } catch (CupoExcedidoException e){
            System.err.println("Eror de cupo: " + e.getMessage());
        }


        //2


        EventoUniversitario evento2 = new EventoUniversitario("Franco", "Sistemas0", 0, true);
        evento2.asignarSala(new Sala(2, "Aula 118"));


        evento2.crearActividad(4, "Charla de Voluntariado", 10, "Charla");
        evento2.crearActividad(5, "Taller de Manualidades", 5, "Taller");
        evento2.crearActividad(6,"Curso de Electricidad",8,"Curso");


        try {
            Actividad tallerEvento2 = evento2.getActividades().get(0);
            Actividad charlaEvento2 = evento2.getActividades().get(1);
            Actividad cursoEvento2 = evento2.getActividades().get(2);

            charlaEvento2.inscribir(estudiantes.get(0));

            tallerEvento2.inscribir(estudiantes.get(0));
            tallerEvento2.inscribir(estudiantes.get(1));

            cursoEvento2.inscribir(estudiantes.get(2));
        } catch (CupoExcedidoException e){
            System.err.println("Eror de cupo: " + e.getMessage());
        }

        // Ajustamos datos propios de cada subtipo con los setters
        ((Charla) evento1.getActividades().get(0)).setDisertante("Prof. Monetti");
        ((Taller) evento1.getActividades().get(1)).setRequiereNotebook(true);


        // Se inscriben estudiantes en cada actividad



        try {
        Actividad charlaEvento2 = evento2.getActividades().get(0);
        Actividad tallerEvento2 = evento2.getActividades().get(1);
        charlaEvento2.inscribir(estudiantes.get(2));
        tallerEvento2.inscribir(estudiantes.get(0));
        } catch(CupoExcedidoException mensaje){

            System.out.println(mensaje.getMessage());

        }
        evento1.persistirEvento();

        // f. Se muestra el resumen de cada evento, recorriendo sus Modelo.actividades
        //    de forma polimórfica (mostrarIdentificacion())
        System.out.println("Evento 1");
        System.out.println(" ");
        evento1.mostrarDatos();


        System.out.println("Evento 2 ");
        System.out.println(" ");
        evento2.mostrarDatos();

        // g. Se muestra el total de eventos creados
        System.out.println("Total de eventos creados: " + EventoUniversitario.getCantidadEventos());


        System.out.println();
    }
}