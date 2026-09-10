package Modelo;

import Modelo.actividades.Actividad;
import Modelo.actividades.Charla;
import Modelo.actividades.Curso;
import Modelo.actividades.Taller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {

    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;

    private static int cantidadEventos = 0;
    private Sala sala;
    private List<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public void setCostoBase(double costoBase) {
        this.costoBase = costoBase;
    }

    public boolean isGratuito() {
        return gratuito;
    }

    public void setGratuito(boolean gratuito) {
        this.gratuito = gratuito;
    }

    public Sala getSala() {
        return sala;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    // Si el evento es gratuito, el costo es 0.
    // Si no, es (costoBase + costo de materiales de cada actividad) * 1.21
    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0;
        }
        double costoActividades = 0;
        for (Actividad a : actividades) {
            costoActividades += a.calcularCostoMateriales();
        }
        return (costoBase + costoActividades) * 1.21;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    // crea una actividad concreta (Modelo.actividades.Charla o Modelo.actividades.Taller) y la agrega al evento



    public void crearActividad(int id, String titulo, int cupo, String tipo) {
        Actividad actividad;
        switch (tipo) {
            case "Charla":
                actividad = new Charla(id, titulo, cupo, "A confirmar");
                break;

            case "Taller":
                actividad = new Taller(id, titulo, cupo, false);
                break;

            case "Curso":
                actividad = new Curso(id, titulo, cupo, 20);
                break;

            default:
                System.out.println("Tipo de actividad desconocido: " + tipo);
                return;
        }
        this.actividades.add(actividad);
    }

    public void mostrarDatos() {
        System.out.println("ID: " + id + "-" + "Título: " + titulo);
        System.out.println("Costo estimado: " + calcularCostoEstimado());
        System.out.println("Sala: " + (sala != null ? sala.getNombre() : "Sin asignar"));
        System.out.println("Actividades (" + actividades.size() + "):");

        // cada actividad muestra su identificación
        for (Actividad evento : actividades) {
            evento.mostrarIdentificacion();
            evento.mostrarInscripciones();
        }
        System.out.println("____________________________________________");
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public boolean persistirEvento() {
        try (ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(id + "evento.dat"))) {


            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al guardar el evento: " + e.getMessage());
            return false;
        }

        return false;
    }
}