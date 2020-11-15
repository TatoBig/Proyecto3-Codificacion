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
public class Nodo {
    private Integer dato;
    private char letra;
    private Nodo izq;
    private Nodo der;
    private String codigo;


    public Integer getDato() {
        return dato;
    }

    public void setDato(Integer dato) {
        this.dato = dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public Nodo(char l, Integer d) {
        letra = l;
        dato = d;
        izq = null;
        der = null;
    }
    public Nodo(){
        
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    @Override
    public String toString() {
        return letra + ":" + dato;
    }
}
