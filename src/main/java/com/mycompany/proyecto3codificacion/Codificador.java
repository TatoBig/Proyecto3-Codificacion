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

/**
 *
 * @author Tato
 */
class Codificador {
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
}
