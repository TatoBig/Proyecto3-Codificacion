/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto3codificacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author aniba
 */
public class Arbol {

    private Nodo raiz;
    private ArrayList<Nodo> codigoBits;

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public ArrayList<Nodo> getCodigoBits() {
        return codigoBits;
    }

    public void setCodigoBits(ArrayList<Nodo> codigoBits) {
        this.codigoBits = codigoBits;
    }

    public Arbol() {
        raiz = null;
    }

    public Arbol(Nodo raizArbol) {
        raiz = raizArbol;
    }

    //Mira pue tato, aca ya esta el arbol el char es la letra que se tiene y el integer es la cantidad de veces que aparece dicha letra.
    public void insertar(ArrayList<Letras> letrasFreq) {
        int n = letrasFreq.size();
        PriorityQueue<Nodo> arbolHuffman = new PriorityQueue<Nodo>(n, new Comparador());

        for (int i = 0; i < n; i++) {
            Nodo nodo = new Nodo();
            nodo.setLetra(letrasFreq.get(i).getLetra());
            nodo.setDato(letrasFreq.get(i).getRepeticiones());
            nodo.setIzq(null);
            nodo.setDer(null);
            arbolHuffman.add(nodo);
        }
        Nodo raiz = null;
        while (arbolHuffman.size() > 1) {
            Nodo nodoIzq = arbolHuffman.peek();
            arbolHuffman.poll();
            Nodo nodoDer = arbolHuffman.peek();
            arbolHuffman.poll();
            Nodo nuevo = new Nodo();
            nuevo.setDato(nodoIzq.getDato() + nodoDer.getDato());
            nuevo.setLetra('*');
            nuevo.setIzq(nodoIzq);
            nuevo.setDer(nodoDer);
            raiz = nuevo;
            arbolHuffman.add(nuevo);
        }
        this.codigoBits = new ArrayList<>();
        obtenerCodigoBits(raiz, "");
    }

    public String toString() {
        return raiz.toString();
    }

    public void enOrder(Nodo nodoRaiz) {
        if (nodoRaiz != null) {
            enOrder(nodoRaiz.getIzq());
            if (nodoRaiz.getDer() == null && nodoRaiz.getIzq() == null) {
                System.out.println(nodoRaiz.toString());
            }
            enOrder(nodoRaiz.getDer());
        }
    }

    public void obtenerCodigoBits(Nodo raiz, String cadena) {
        if (raiz.getIzq() == null && raiz.getDer() == null) {
            System.out.println(raiz.getLetra() + "   |  " + cadena);
            raiz.setCodigo(cadena);
            this.codigoBits.add(raiz);
            return;
        }
        obtenerCodigoBits(raiz.getIzq(), cadena + "0");
        obtenerCodigoBits(raiz.getDer(), cadena + "1");
    }
}

class Comparador implements Comparator<Nodo> {

    public int compare(Nodo nodoIzq, Nodo nodoDer) {
        return nodoIzq.getDato() - nodoDer.getDato();
    }
}
