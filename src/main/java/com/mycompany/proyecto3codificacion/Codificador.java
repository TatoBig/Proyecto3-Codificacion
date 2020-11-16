/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto3codificacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *
 * @author Tato
 */
class Codificador {
    Arbol huffman = new Arbol();
    Lista listaLetras = new Lista();
    ArrayList<Nodo> codigos = new ArrayList<>();
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
        dis.close();
        return textoOriginal;
    }
    /*
    11000010 01100101 10101110 01011000 01101110 00111110 01
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

    public void generarCodigo(String texto) {
        this.codigos = new ArrayList<>();
        this.listaLetras = new Lista();
        this.listaLetras.Insertar(texto);
        arrayListLetras = listaLetras.getLista();
        huffman.insertar(arrayListLetras);
        this.codigos = huffman.getCodigoBits();
    }
    public ArrayList getCodigos(){
        return codigos;
    }

    public void guardarCodigos() throws FileNotFoundException, IOException {
        String nombreArchivo = "Codigo Huffman.data";
        FileOutputStream archivo = new FileOutputStream(nombreArchivo);
        DataOutputStream escritor = new DataOutputStream(archivo);
        escritor.write("HUF".getBytes(StandardCharsets.US_ASCII));
        for (int i = 0; i < this.codigos.size(); i++) {
            escritor.writeByte((int)codigos.get(i).getLetra());
            escritor.write(codigos.get(i).getCodigo().getBytes(StandardCharsets.US_ASCII));
        }
    }
    public void guardarCadena(String textoCodificadoBytes) throws FileNotFoundException, IOException {
        String nombreArchivo = "Cadena en bytes.txt";
        FileOutputStream archivo = new FileOutputStream(nombreArchivo);
        DataOutputStream escritor = new DataOutputStream(archivo);
        escritor.write(textoCodificadoBytes.getBytes());
    }

    public boolean importarCodigos(String url) throws FileNotFoundException, IOException {
        boolean flag;
        this.codigos = new ArrayList<>();
        File file = new File(url);
        byte[] fileData = new byte[(int)file.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        dis.readFully(fileData);
        int contadorBytes = 3;
        char[] marcaChar = new char[3];
        for (int i = 0; i < 3; i++) {
            marcaChar[i] = (char) fileData[i];
        }
        String marca = new String(marcaChar);
        if("HUF".equals(marca)){
            flag = true;
            while(contadorBytes<file.length()){
                Nodo nuevo = new Nodo();
                String codigo ="";
                System.out.println((char)fileData[contadorBytes]);
                nuevo.setLetra((char)fileData[contadorBytes]);
                contadorBytes++;
                for (int i = contadorBytes; i < file.length(); i++) {
                    System.out.println("LETRA EN ASCII: "+(int)fileData[i]);
                    System.out.println(contadorBytes+"+"+file.length());
                    if((int)fileData[i]==48||(int)fileData[i]==49){
                        codigo=codigo+(char)fileData[i];
                        contadorBytes++;
                    }else{
                        break;
                    }
                }
                System.out.println("Ingresando: "+nuevo.getLetra());
                nuevo.setCodigo(codigo);
                this.codigos.add(nuevo);
            }
            System.out.println("Archivo válido");
        }else{
            flag = false;
        }
        dis.close();
        return flag;
    }

    public String descomprimirABits(String url) throws FileNotFoundException, IOException {        
        File file = new File(url);
        byte[] fileData = new byte[(int)file.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        dis.readFully(fileData);
        int contadorBytes=0,valorBytes,binario,pow=7;
        boolean flag=true;
        String cadenaBits ="";
        while(contadorBytes<file.length()){
            if((int)fileData[contadorBytes]+256!=255&&flag==true){
                valorBytes = (int)fileData[contadorBytes];
                if(valorBytes<0){ valorBytes +=256; }
                for (int i = 0; i < 8; i++) {
                    binario = valorBytes/(int)Math.pow(2, pow-i);
                    valorBytes = valorBytes-(binario*(int)Math.pow(2, pow-i));
                    cadenaBits = cadenaBits+binario;
                }
                contadorBytes++;
            }else{
                if(flag==true){
                    contadorBytes++;
                }
                flag=false;
                System.out.println((char)fileData[contadorBytes]);
                cadenaBits =cadenaBits+(char)fileData[contadorBytes];
                contadorBytes++;
            }
        }
        dis.close();
        return cadenaBits;
    }

    public String descomprimirABytes(String textoBits) {
        String cadenaOriginal="",cadenaCodigo="";
        int contadorCadena=0;
        for (int i = 0; i < textoBits.length(); i++) {
            System.out.println(cadenaCodigo);
            cadenaCodigo=cadenaCodigo+textoBits.charAt(i);
            for (int j = 0; j < codigos.size(); j++) {
                if(cadenaCodigo == null ? codigos.get(j).getCodigo() == null : cadenaCodigo.equals(codigos.get(j).getCodigo())){
                    cadenaOriginal=cadenaOriginal+codigos.get(j).getLetra();
                    cadenaCodigo="";
                }
            }
        }
        System.out.println(cadenaOriginal);
        return cadenaOriginal;
    }
}
