/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Objects.Celda;
import Objects.Estado;
import Objects.GameEst;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;


public class Buscaminas implements Serializable{
    private Celda tablero[][];
    private Integer dimx;
    private Integer dimy;
    private Integer minas;
    private GameEst juego;

    
    
    public Buscaminas(Integer x,Integer y,Integer m){
        tablero=new Celda[x][y];
        dimx=x;
        dimy=y;
        minas=m;
        juego=GameEst.Sigue;
        
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                tablero[i][j]=new Celda();
            }
        }
        colocarMinas();
       
        
    }
    private void colocarMinas(){
        Random random = new Random();
        int contador = 0;
        while(contador<minas){
            int xx = random.nextInt(this.dimx);
            int yy = random.nextInt(this.dimy);
            if (!(tablero[xx][yy]).isMina()) {
                tablero[xx][yy].setMina(true);
                contador++;
            }
        }
    }
    
    public Integer getMinas(){
        return minas;
    }
    
    public Integer getDimx() {
        return dimx;
    }

    public void setDimx(Integer dimx) {
        this.dimx = dimx;
    }

    public Integer getDimy() {
        return dimy;
    }

    public void setDimy(Integer dimy) {
        this.dimy = dimy;
    }
    public Celda[][] getTablero(){
        return tablero;
    }
    
    public void abrirCelda(Integer x, Integer y){
        if(this.celdaValida(x, y)){
            if(!tablero[x][y].isMina()){
                if(this.tablero[x][y].getEstado()==Estado.Cerrado){
                    this.tablero[x][y].setNumero(obtenerNumero(x,y));
                    this.tablero[x][y].abrirCelda();
                    if(this.tablero[x][y].getNumero()==0){
                        for(int i=x-1;i<=x+1;i++){
                            for(int j=y-1;j<=y+1;j++){
                                this.abrirCelda(i, j);
                            }
                        }
                    }
                }
                if(Objects.equals(contarCerrados(), this.minas)){
                    this.juego=GameEst.Win;
                    this.abrirTodo();
                }
            }else{
                this.juego=GameEst.Lose;
                this.abrirTodo();
                this.tablero[x][y].setEstado(Estado.Boom);
            }
            
        } 
    }
    
    private boolean celdaValida(Integer x, Integer y){
        return (x>=0 &&x<this.dimx)&&(y>=0&&y<this.dimy);
    }
    
    private void abrirTodo(){
        for(int i=0 ; i<this.getDimy();i++){
                for(int j=0;j<this.getDimx();j++){
                    if(this.tablero[i][j].isMina() && this.tablero[i][j].getEstado()!=Estado.Bandera)
                        this.tablero[i][j].setEstado(Estado.Abierto);
                    if(!this.tablero[i][j].isMina() && this.tablero[i][j].getEstado()==Estado.Bandera)
                        this.tablero[i][j].setEstado(Estado.BanderaMala);
                }
            }
    }
    
    private Integer obtenerNumero(Integer x, Integer y){
        int cont=0;
        for(int i=x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){
                if(this.celdaValida(i, j)){
                    cont=this.tablero[i][j].isMina() ? ++cont : cont;
                }
            }
        }
        return cont;
    }
    private Integer contarCerrados(){
        int cont=0;
        for(int i=0 ; i<this.getDimy();i++){
                for(int j=0;j<this.getDimx();j++){
                    cont=this.tablero[j][i].getEstado()==Estado.Cerrado ? ++cont : cont;
                }
            }
        return cont;
    }
    public void marcarCelda(Integer x, Integer y){
        tablero[x][y].nextEdo();
    }
    public GameEst getEstado(){
        return juego;
    }
    
    
    
}
