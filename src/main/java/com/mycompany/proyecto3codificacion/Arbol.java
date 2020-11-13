/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto3codificacion;

/**
 *
 * @author aniba
 */
public class Arbol {
    private Nodo raiz;

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public Arbol() {
        raiz = null;
    }

    public Arbol(Nodo raizArbol) {
        raiz = raizArbol;
    }

    public void insertar(char letra, Integer d) {
        Nodo nuevo = new Nodo(letra, d);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            Nodo aux = raiz;
            Nodo ant = null;
            while (aux != null) {
                ant = aux;
                if (d <= aux.getDato()) {
                    aux = aux.getIzq();
                } else {
                    aux.getDer();
                }
            }
            if (d <= ant.getDato()) {
                ant.setIzq(nuevo);
            } else {
                ant.setDer(nuevo);
            }
        }
    }

    public String toString() {
        return raiz.toString();
    }

    public void enOrder(Nodo r) {
        if (r != null) {
            enOrder(r.getIzq());
            System.out.println(r.toString());
            enOrder(r.getDer());
        }
    }
}
