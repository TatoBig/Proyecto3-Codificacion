/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto3codificacion;

import java.util.ArrayList;

/**
 *
 * @author aniba
 */
public class Lista {
    
    private ArrayList<Letras> letras=new ArrayList<>();
    private ArrayList<Letras> MenorMayor=new ArrayList<>();
    private int mayor=0;
    
    public void Insertar(String palabra)
    {
        boolean flag=false;
        int pos=0;
        for(int re=0;re<palabra.length();re++)
        {
            if(letras.size()==0)
            {
                Letras nuevo= new Letras();
                nuevo.setLetra(palabra.charAt(re));
                nuevo.setRepeticiones();
                letras.add(nuevo);
            }
            else
            {
                for(int i=0;i<letras.size();i++)
                {
                        if(palabra.charAt(re)==letras.get(i).getLetra())
                    {
                        flag=true;
                        pos=i;
                    }
                        if(mayor<letras.get(i).getRepeticiones())
                        {
                            mayor=letras.get(i).getRepeticiones();
                        }
                }
                if(flag)
                {
                    letras.get(pos).setRepeticiones();
                    flag=false;
                }
                else
                {
                    Letras nuevo= new Letras();
                nuevo.setLetra(palabra.charAt(re));
                nuevo.setRepeticiones();
                letras.add(nuevo);
                }
            }
        }
    }
    public String MostrarList()
    {
        String most="";
        ordenar();
        for(int i=0;i<MenorMayor.size();i++)
                {
                    most=most+"Letra: "+MenorMayor.get(i).getLetra()+"  Repeticion: "+MenorMayor.get(i).getRepeticiones()+"\n";
                }
        return most;
    }
    public void ordenar()
    {
        for(int j=0;j<=mayor;j++)
        {
            for(int i=0;i<letras.size();i++)
            {
                if(j==letras.get(i).getRepeticiones())
                {
                    Letras nuevo=letras.get(i);
                    MenorMayor.add(nuevo);
                }
            }
        }
    }
    public void Limpieza()
    {
        letras.clear();
        MenorMayor.clear();
    }
    public ArrayList getLista(){
        ordenar();
        return this.MenorMayor;
    }
}
