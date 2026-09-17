
import Modelo.Estudiante;
import Modelo.EventoUniversitario;
import Modelo.Inscripcion;
import Modelo.Sala;
import Modelo.actividades.Actividad;
import Modelo.actividades.Charla;
import Modelo.actividades.Curso;
import Modelo.actividades.Taller;
import Modelo.certificacion.Certificable;
import excepciones.CupoExcedidoException;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws CupoExcedidoException {

        //a.Estudiantes
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("50111", "Franco"));
        estudiantes.add(new Estudiante("50112", "Pedro"));
        estudiantes.add(new Estudiante("50113", "Mauro"));

        //1
        // b. Creacion de Evento
        EventoUniversitario evento1 = new EventoUniversitario("Franco", "Clase OPP", 15000, false);

        //b.1. asignar sala

        evento1.asignarSala(new Sala(1, "Aula 114"));

        // b.2. Crear Actividad (Charla, Taller, Curso)

        evento1.crearActividad(1, "Introduccion a POO", 10, "Taller");
        evento1.crearActividad(2, "Clase de Java", 1, "Curso");
        evento1.crearActividad(3,"Charla IA",10,"Charla");

        // c. Incripcion de Estudiantes

        try {
            Actividad tallerEvento1 = evento1.getActividades().get(0);
            Actividad charlaEvento1 = evento1.getActividades().get(1);
            Actividad cursoEvento1 = evento1.getActividades().get(2);

            charlaEvento1.inscribir(estudiantes.get(0));

            tallerEvento1.inscribir(estudiantes.get(0));
            tallerEvento1.inscribir(estudiantes.get(1));

            cursoEvento1.inscribir(estudiantes.get(2));
        } catch (CupoExcedidoException e){
            System.err.println("Error de cupo: " + e.getMessage());
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
            System.err.println("Error de cupo: " + e.getMessage());
        }


        try {
        Actividad charlaEvento2 = evento2.getActividades().get(0);
        Actividad tallerEvento2 = evento2.getActividades().get(1);
        charlaEvento2.inscribir(estudiantes.get(2));
        tallerEvento2.inscribir(estudiantes.get(0));
        } catch(CupoExcedidoException mensaje){

            System.out.println(mensaje.getMessage());

        }

        //persistir

        evento1.persistirEvento();

        // f. Se muestra el resumen de cada evento, recorriendo sus Modelo.actividades
        //    de forma polimórfica (mostrarIdentificacion())
        System.out.println("Evento 1");
        System.out.println(" ");
        evento1.mostrarDatos();


        System.out.println("Evento 2 ");
        System.out.println(" ");
        evento2.mostrarDatos();


        // Emision y vision de cerificados

        System.out.println("Emision de Certificado");

        for (Actividad act : evento1.getActividades()){
            if (act instanceof Certificable){
                Certificable actividadCertificable = (Certificable) act;
                for (Inscripcion inscripcion : act.getInscripciones()){
                    String certificado = actividadCertificable.generarCertificado(inscripcion.getEstudiante());
                System.out.println(certificado);
                }
            }
        }

        // Se muestra el total de eventos creados
        System.out.println("Total de eventos creados: " + EventoUniversitario.getCantidadEventos());


        //d. Parametrizacion por tipo de clase

        List<Taller> listaTalleres = evento1.filtrarActividadesPorTipo(Taller.class);
        List<Curso> listaCursos = evento1.filtrarActividadesPorTipo(Curso.class);
        List <Charla> listaCharlas = evento1.filtrarActividadesPorTipo(Charla.class);

        /**

        List<Taller> listaTalleres2 = evento2.filtrarActividadesPorTipo(Taller.class);
        List<Curso> listaCursos2 = evento2.filtrarActividadesPorTipo(Curso.class);
        List <Charla> listaCharlas2 = evento2.filtrarActividadesPorTipo(Charla.class);

        **/

        //1

        System.out.println("---------------------------------------------");

        System.out.println("Talleres Encontrados (" + listaTalleres.size() + "):");
            for (Taller t : listaTalleres){
                System.out.println(" - " + t.getTitulo() + "| Requiere Notebook: " + t.isRequiereNotebook());
            }

        System.out.println("Cursos Encontrados (" + listaCursos.size() + "):");
        for (Curso c : listaCursos){
            System.out.println(" - " + c.getTitulo() + "| Horas: " + c.getHoras());
        }

        /**2

        System.out.println("Talleres Encontrados (" + listaTalleres2.size() + "):");
        for (Taller t : listaTalleres2){
            System.out.println(" - " + t.getTitulo() + "| Requiere Notebook: " + t.isRequiereNotebook());
        }

        System.out.println("Cursos Encontrados (" + listaCursos2.size() + "):");
        for (Curso c : listaCursos2){
            System.out.println(" - " + c.getTitulo() + "| Horas: " + c.getHoras());
        }
        **/

        // Cantidad de actividades de cada tipo

        System.out.println("---------------------------------------------");

        System.out.println("Cantidad de Charlas: "+ listaCharlas.size());
        System.out.println("Cantidad de Talleres: " + listaTalleres.size());
        System.out.println("Cantidad de Cursos: "+ listaCursos.size());



        // Calculo de costo de Materiales

        double costoMaterialesTotal1= evento1.calcularCostoMateriales(evento1.getActividades());

        double costoMaterialesTalleres1= evento1.calcularCostoMateriales(listaTalleres);

        double costoMaterialesCursos1= evento1.calcularCostoMateriales(listaCursos);

        /**

        double costoMaterialesTalleres2 = evento2.calcularCostoMateriales(listaTalleres);
        double costoMaterialesCursos2 = evento2.calcularCostoMateriales(listaCursos);
        double costoMaterialesTotal2 = evento2.calcularCostoMateriales(evento2.getActividades());

        double costoMaterialesTalleres = (costoMaterialesTalleres1 + costoMaterialesTalleres2) ;
        double costoMaterialesCursos = (costoMaterialesCursos1 + costoMaterialesCursos2) ;
        double costoMaterialesTotal = (costoMaterialesTotal1 + costoMaterialesTotal2) ;

        **/




        System.out.println("---------------------------------------------");

        System.out.println("Costo de Materiales Talleres: " + costoMaterialesTalleres1);
        System.out.println("Costo de materiales Cursos: " + costoMaterialesCursos1);
        System.out.println("Costo materiales general: " + costoMaterialesTotal1);

        //Lista de cada tipo

        System.out.println("---------------------------------------------");
        for (Charla ch : listaCharlas) {
            System.out.println("Charla: " + ch.getTitulo() + " Disetante: " + ch.getDisertante());
        }
        for (Taller t: listaTalleres){
            System.out.println("Taller: " + t.getTitulo() + " Requiere Notebook: " + t.isRequiereNotebook());
        }
        for(Curso c: listaCursos) {
            System.out.println("Curso: " + c.getTitulo() + " Horas: " + c.getHoras());
        }

    }



}