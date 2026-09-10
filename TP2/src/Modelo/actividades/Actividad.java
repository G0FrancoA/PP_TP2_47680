package Modelo.actividades;

import Modelo.Estudiante;
import Modelo.Inscripcion;
import excepciones.CupoExcedidoException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {

    private int id;
    private String titulo;
    private int cupoMaximo;
    public static final int CUPO_MINIMO = 5;

    private List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    // Inscribe un estudiante en la actividad, si hay cupo disponible
    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {

            if (inscripciones.size() >= cupoMaximo) {

                throw new CupoExcedidoException("No se pudo inscribir al alumno: " + estudiante.getNombre() + " Cupo Maximo Alcanzado");
        }

        Inscripcion inscripcion = new Inscripcion(LocalDate.now(), "Confirmada",estudiante);
        inscripciones.add(inscripcion);

        System.out.println(estudiante.getNombre() + " se inscribió en " + titulo);
        return inscripcion;
    }

    // Muestra las inscripciones registradas en esta actividad
    public void mostrarInscripciones() {
        System.out.println(" Inscripciones en " + titulo + " (Cupo max: " + cupoMaximo + ")");
        if (inscripciones.isEmpty()) {
            System.out.println("    Sin inscripciones todavía.");
        }
        for (Inscripcion i : inscripciones) {
            System.out.println("    Estudiante: "+ i.getEstudiante().getNombre() +"    Fecha: " + i.getFecha() + " - Estado: " + i.getEstado());
        }
    }

    public final void mostrarIdentificacion() {

        System.out.println("/  Actividad ID: "+ id + "  /");
        System.out.println("/  Tipo:" + getTipo() + "  /");
        System.out.println("/  Titulo: " + titulo + "  /") ;

    }

    // cada subclase define cómo calcula el costo de sus materiales
    public abstract double calcularCostoMateriales();

    public abstract String getTipo();
}