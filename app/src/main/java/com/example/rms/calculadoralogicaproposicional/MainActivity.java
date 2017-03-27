package com.example.rms.calculadoralogicaproposicional;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import Modelo.Evaluacion;

import static Modelo.Principal.mostrarTablasVariables;
import static Modelo.Principal.postfijo;

public class MainActivity extends AppCompatActivity {

    private EditText etProposicion;
    private EditText etTipo;
    private EditText etPostfijo;
    private Button btnEvaluar;
    private TableLayout tabla;

    private Adapter adapter;

    private String proposicion = "";
    private String tipoEvaluacion = "";
    private String postfijo = "";
    private int noColumnas = 0;
    private int noFilas = 0;

    private static ArrayList<ArrayList> vectorFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProposicion = (EditText) findViewById(R.id.etProposicion);
        etTipo = (EditText) findViewById(R.id.etTipo);
        etPostfijo = (EditText) findViewById(R.id.etPostfijo);
        btnEvaluar = (Button) findViewById(R.id.btnEvaluar);
        tabla = (TableLayout) findViewById(R.id.tabla);

        btnEvaluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etProposicion.getText().toString().equals("")) {
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
                        vectorFinal = Evaluacion.arrayTabla;

                        noColumnas = vectorFinal.size();
                        noFilas = vectorFinal.get(0).size();

                        System.out.println("NOCOLUMNAS: " + noColumnas);
                        System.out.println("NOFILAS: " + noFilas);
                        System.out.println("VECTOR FINAL: " + vectorFinal.toString());

//                        for (int i = 0; i < noColumnas; i++) {
//
//                        }

//                        for (int i = 0; i < vectorFinal.get(i).size(); i++) {
//                            for (int j = 0; j < vectorFinal.size(); j++) {
////                                TableRow fila = new TableRow(vectorFinal.get(i).get(j));
//                            }
//                        }
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
                } else {
                    Toast.makeText(getApplicationContext(), "Ingresa una proposición", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
