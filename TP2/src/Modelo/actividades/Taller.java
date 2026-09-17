package Modelo.actividades;

import Modelo.Estudiante;
import Modelo.certificacion.Certificable;

public class Taller extends Actividad implements Certificable {

    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }

    public void setRequiereNotebook(boolean requiereNotebook) {
        this.requiereNotebook = requiereNotebook;
    }

    // $5000 si requiere notebook, $2000 si no
    @Override
    public double calcularCostoMateriales() {
        return requiereNotebook ? 5000 : 2000;
    }

    @Override
    public String getTipo() {
        return "Taller";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {

        return "CERTIFICADO DE ASISTENCIA (" + ENTIDAD_EMISORA + ") "
                + " Otorgado a " + estudiante.getNombre() + " - " + "Legajo: " + estudiante.getLegajo()
                + " Por haber asistido al curso de: " + getTitulo() + ") ";
    }
}