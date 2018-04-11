/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.io.Serializable;


public class Celda implements Serializable{
    private Estado estado;
    private Boolean mina;
    private Integer numero;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Estado getEstado() {
        return estado;
    }

    public Boolean isMina() {
        return mina;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setMina(Boolean mina) {
        this.mina = mina;
    }
    public Celda(){
        estado = Estado.Cerrado;
        mina=false;
    }
    
    public void marcarCelda(){
        switch(estado){
            case Cerrado:
                this.setEstado(Estado.Bandera);
            case Bandera:
                this.setEstado(Estado.Interrogacion);
            default:
                throw new AssertionError();
        }
    }
    public void nextEdo(){
        switch (estado) {
            case Cerrado:
                estado = Estado.Bandera;
                break;
            case Bandera:
                estado = Estado.Interrogacion;
                break;
            case Interrogacion:
                estado = Estado.Cerrado;
                break;                
        }
    }
    public void abrirCelda(){
        this.estado= Estado.Abierto;
    }
    
    @Override
    public String toString(){
        switch(estado){
            case Cerrado:
                return String.format("[-]");
            case Bandera:
                return String.format("[^]");
            case Interrogacion:
                return String.format("[?]");
            case Abierto:
                if(this.isMina())
                    return String.format("[#]");
                if(this.getNumero()==0)
                    return String.format("[ ]");
                else
                    return String.format("[%d]", this.getNumero());
            case BanderaMala:
                return String.format("[x]");
            case Boom:
                return String.format("[*]");
            default:
                throw new AssertionError();
        }
    }
   
    
}
