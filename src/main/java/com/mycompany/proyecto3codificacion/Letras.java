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
public class Letras {
    private char letra;
    private Integer repeticiones;
    Letras()
    {
        this.repeticiones=0;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public Integer getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones() {
        this.repeticiones++;
    }
}
