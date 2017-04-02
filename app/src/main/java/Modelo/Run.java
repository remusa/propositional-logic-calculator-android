package Modelo;

import java.io.IOException;

import static Modelo.Principal.mostrarTablasVariables;

/**
 * Created by rms on 01/04/2017.
 */

public class Run {

    public static void main(String[] args) throws IOException {
        String proposicion = "(PvQ)^R";
        //Tablas
        mostrarTablasVariables(proposicion);
        //Evaluación
        Evaluacion.evaluacionIniciar(proposicion);
        //Postfijo
        String postfijo = Principal.postfijo(proposicion);
        System.out.println("Postfijo: " + postfijo);
        //Tipo de evaluación
        String tipoEvaluacion = Evaluacion.evaluacionTipo();
        System.out.println("Tipo de evaluación: " + tipoEvaluacion);
    }

}
