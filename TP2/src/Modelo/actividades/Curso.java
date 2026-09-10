package Modelo.actividades;

import Modelo.certificacion.Certificable;
import Modelo.Estudiante;

public class Curso extends Actividad implements Certificable {

    private int horas;

    public Curso (int id, String titulo, int cupoMaximo,int horas) {
        super(id,titulo,cupoMaximo);
        this.horas = horas;

    }

    public int getHoras() {
        return horas;
    }


    @Override
    public double calcularCostoMateriales() {
        return 0;
    }

    @Override
    public String getTipo() {
        return null;
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {

        return "CERTIFICADO DE APROBACION (" + ENTIDAD_EMISORA + ")"
                + "Otorgado a: " + estudiante.getNombre() + "Legajo: " + estudiante.getLegajo()
                + "Por completar el Curso: " + getTitulo() + "(horas: " + horas + ")";
    }
}
