package com.example.rms.calculadoralogicaproposicional;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import Modelo.Evaluacion;

import static Modelo.Principal.mostrarTablasVariables;
import static Modelo.Principal.postfijo;

public class MainActivity extends AppCompatActivity {

    private EditText etProposicion;
    private EditText etTipo;
    private EditText etPostfijo;
    private Button btnEvaluar;

    private String proposicion = "";
    private String tipoEvaluacion = "";
    private String postfijo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProposicion = (EditText) findViewById(R.id.etProposicion);
        etTipo = (EditText) findViewById(R.id.etTipo);
        etPostfijo = (EditText) findViewById(R.id.etPostfijo);
        btnEvaluar = (Button) findViewById(R.id.btnEvaluar);

        btnEvaluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proposicion = etProposicion.getText().toString();

                //Tablas
                try {
                    mostrarTablasVariables(proposicion);
                } catch (Exception e) {
                    System.out.println("ERROR TABLA: " + e.getMessage());
                }
                //Evaluación
                try {
                    Evaluacion.evaluacionIniciar(proposicion);
                } catch (IOException e) {
                    System.out.println("ERROR EVALUACIÓN: " + e.getMessage());
                }
                //Tipo de evaluación
                try {
                    tipoEvaluacion = Evaluacion.evaluacionTipo();
                    etTipo.setText(tipoEvaluacion);
                    System.out.println("TIPO: " + tipoEvaluacion);
                } catch (Exception e) {
                    System.out.println("ERROR TIPO: " + e.getMessage());
                }
                //Postfijo
                try {
                    postfijo = postfijo(proposicion);
                    etPostfijo.setText(postfijo);
                    System.out.println("POSTFIJO: " + postfijo);
                } catch (IOException e) {
                    System.out.println("ERROR POSTFIJO: " + e.getMessage());
                }

            }
        });

    }
}
