/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Objects.ArchivoAbrir;
import Objects.ArchivoGuardar;
import Objects.Celda;
import Objects.GameEst;
import controller.Buscaminas;
import gui.PrincipalFrame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class main {
    
    public static void pichon() throws IOException, FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        Integer a,b,op;
        Buscaminas busca=null;
        ArchivoGuardar nuevo=null;
        String nombreArch;
        
        
        System.out.println("Que quieres hacer?");
        System.out.println("1.Juego nuevo");
        System.out.println("2.Abrir");
        
        Integer opcion=sc.nextInt();
        switch(opcion){
            case 1: 
                busca = new Buscaminas(10,10,10);
                break;
            case 2:
                try {
                    busca= new ArchivoAbrir().getResultado();
                } catch (IOException e) {
                    System.out.println("Error con el archivo");
                } catch (ClassNotFoundException ex){
                    System.out.println("Error con la clase");
                }
                break;
            default: 
                throw new AssertionError();
        }
        
        
        Celda[][] celda = busca.getTablero();
        do{
            System.out.println("*************************");
            System.out.println("1) Abrir Celda\n2) Marcar Celda\n3)Guardar");
            op=sc.nextInt();
            switch(op){
                case 1:
                case 2:
                    System.out.println("Coordenadas");
                    a=sc.nextInt();
                    b=sc.nextInt();
                    if(op==1)
                        busca.abrirCelda(a, b);
                    else
                        busca.marcarCelda(a, b);
                    break;
                case 3:
                    try{
                        nuevo = new ArchivoGuardar(busca);
                        System.out.println("Guardado");
                    }catch (IOException ex){
                        System.out.println("Error al intentar guardar");
                    }
                    break;
            }
            
            for(int i=0 ; i<busca.getDimy();i++){
                for(int j=0;j<busca.getDimx();j++){
                    System.out.print(celda[j][i]);
                }
            System.out.println("");
            }
        }while(busca.getEstado()== GameEst.Sigue);
        System.out.println("*************************");
        if(busca.getEstado()==GameEst.Win)
            System.out.println("Ah Perro, Ganaste!");
        else
            System.out.println("Pinshi Loser!");
    }
    public static void grafico(){
        PrincipalFrame frame = new PrincipalFrame("Buscaminas");
    }
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        grafico();
    }
}
