/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto3codificacion;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tato
 */
class Codificador {
    Arbol huffman = new Arbol();
    Lista listaLetras = new Lista();
    ArrayList<Letras> arrayListLetras = new ArrayList<>();
    public String codificarTexto(String url) throws FileNotFoundException, IOException{
        return "";
    }

    public String obtenerTexto(String url) throws FileNotFoundException, IOException{
        File file = new File(url);
        byte[] fileData = new byte[(int)file.length()];
        char textoChar[] = new char[(int)file.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        dis.readFully(fileData);       
        for (int i = 0; i < (int)file.length(); i++) {
            textoChar[i] = (char)fileData[i];
        }  
        String textoOriginal = new String(textoChar);
        return textoOriginal;
    }
    /*
    11000010 01100101 10101110 01011000 01101110 00111110 01
    194 - 101 - 174 - 88 - 110 - 62
    e   |  00
    x   |  0100
    o   |  0101
    r   |  0110
    d   |  0111
    p   |  1000
    a   |  1001
        |  101
    t   |  110
    u   |  1110
    b   |  1111
    */
    public String obtenerCadenaBits(String texto){
        String cadenaDeBits="";
        this.listaLetras.Insertar(texto);
        System.out.println(arrayListLetras.size());
        arrayListLetras = listaLetras.getLista();        
        huffman.insertar(arrayListLetras);
        ArrayList<Nodo> codigos = huffman.getCodigoBits();
        for (int i = 0; i < texto.length(); i++) {
            for (int j = 0; j < codigos.size(); j++) {
                if(texto.charAt(i)==codigos.get(j).getLetra()){
                    cadenaDeBits = cadenaDeBits +codigos.get(j).getCodigo();
                    break;
                }
            }
        }
        
        return cadenaDeBits;
    }

    public String obtenerCadenaBytes(String textoBits) {
        String cadenaBytes ="";
        int tamañoBytes = textoBits.length()/8,bytesRestantes = textoBits.length()%8,contadorInterno=0,valorBytes,binario,pow;
        for (int i = 0; i < tamañoBytes; i++) {
            valorBytes=0;
            pow=0;
            for (int j = contadorInterno; j < contadorInterno+8; j++) {
                binario=Character.getNumericValue(textoBits.charAt(j));
                valorBytes = binario*(int)Math.pow(2, 7-pow)+valorBytes;
                pow++;
            }
            contadorInterno +=8;
            cadenaBytes= cadenaBytes+(char)valorBytes;
        }
        int separador = 255;
        cadenaBytes = cadenaBytes + (char)separador;
        for (int i = 0; i < bytesRestantes; i++) {
            cadenaBytes = cadenaBytes + textoBits.charAt((tamañoBytes*8)+i);
        }
        System.out.println(cadenaBytes.length());
        return cadenaBytes;
    }
}
