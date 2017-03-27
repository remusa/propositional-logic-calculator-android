package Ordenamiento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rms on 27/03/2017.
 */

public class Posfijo {

    String aux;
    Pila operadores;
    Pila operandos;
    ArrayList<String> lines;
    ArrayList<String> lexema;
    ArrayList<String> res;
    public Map<String, Integer> prioridad = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ArrayList<String> res1 = new ArrayList();
        String prueba1 = "(P→Q^-R)↔((-P^-S)^R↔(P^-Q))^R→S";
//        String prueba1 = "((P→Q)^(P→R))→(P→R)";    //PQ→PR→^PR→→
        String prb1[] = prueba1.split("");
        res1.addAll(Arrays.asList(prb1));
        Posfijo p1 = new Posfijo(res1);
        res1 = p1.getPosfijo();
        System.out.println("Prueba 1: " + prueba1 + "\nPostfijo 1: " + res1 + "\n");

        ArrayList<String> res2 = new ArrayList();
        String prueba2 = "(P^Q)↔(-Qv-P)";
        String prb2[] = prueba2.split("");
        res2.addAll(Arrays.asList(prb2));
        Posfijo p2 = new Posfijo(res2);
        res2 = p2.getPosfijo();
        System.out.println("Prueba 2: " + prueba2 + "\nPostfijo 2: " + res2 + "\n");

        ArrayList<String> res3 = new ArrayList();
        String prueba3 = "((P→Q)^(Q^P))↔(P↔Q)";
        String prb3[] = prueba3.split("");
        res3.addAll(Arrays.asList(prb3));
        Posfijo p3 = new Posfijo(res3);
        res3 = p3.getPosfijo();
        System.out.println("Prueba 3: " + prueba3 + "\nPostfijo 3: " + res3 + "\n");
    }

    /**
     * @param lexema
     * @throws java.io.IOException
     */
    public Posfijo(ArrayList<String> lexema) throws IOException {
        this.lexema = lexema;
        operadores = new Pila(lexema.size());
        operandos = new Pila(lexema.size());
        this.getPrioridad();
    }

    private void getPrioridad() {
        prioridad.put("-", 2);
        prioridad.put("^", 3);
        prioridad.put("v", 3);
        prioridad.put("→", 3);
        prioridad.put("↔", 3);
    }

    public ArrayList<String> getPosfijo() {
        for (String lex : lexema) {
            switch (lex) {
                case ")":
                    while (!operadores.empty() && !"(".equals(operadores.stacktop())) {//mientras no este vacia y operadores no sea un (
                        aux = operadores.pop();
                        operandos.push(aux);//cambia lo que esta en la pila operadores a la pila operandos
                    }
                    if (operadores.empty()) {
                        System.out.println("Expresión Errónea");
                    } else {
                        operadores.pop();//sacamos el paréntesis que abre
                    }
                    break;
                case "(":
                    operadores.push(lex);
                    break;
                case "^":
                case "v":
                case "→":
                case "↔":
                case "-":
                    if (operadores.empty()) {
                        operadores.push(lex);
                    } else {
                        while (!operadores.empty() && !operadores.stacktop().equals("(") && this.prioridad.get(lex) >= this.prioridad.get(operadores.stacktop())) {
                            aux = operadores.pop();
                            operandos.push(aux);
                        }
                        operadores.push(lex);
                    }
                    break;
                default:
                    operandos.push(lex);
                    break;
            }
        }
        while (!operandos.empty()) {
            operadores.push(operandos.pop());
        }
        return mostrarPila(operadores);
    }

    public ArrayList<String> mostrarPila(Pila p) {
        ArrayList<String> posfijo = new ArrayList();
        while (!p.empty()) {
            posfijo.add(p.pop());
        }
        return posfijo;
    }
}
