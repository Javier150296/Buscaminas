/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Objects.Celda;
import java.awt.Color;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 *
 * @author JAVIER1
 */
public class TButton extends JButton{
    private Celda celda;
    private Integer xx;
    private Integer yy;
    
    public TButton(String path){
        super();
        this.cargarIcono(path);
    }
    
    public TButton (Celda celda, Integer xx, Integer yy){
        this.celda=celda;
        this.xx=xx;
        this.yy=yy;
        switch(celda.getEstado()){
            case Interrogacion:
                this.cargarIcono("/images/001-question.png");
                break;
            case Bandera:
                this.cargarIcono("/images/003-symbol.png");
                break;
            case BanderaMala:
                this.cargarIcono("/images/002-symbol-1.png");
                break;
            case Abierto: 
                if(celda.isMina())
                    this.cargarIcono("/images/002-mine-1.png");
                break;
            case Boom:
                this.cargarIcono("/images/001-mine.png");
                this.setBackground(Color.red);
                break;
            default:
                break;
        }
        
    }

    public Celda getCelda() {
        return celda;
    }

    public void setCelda(Celda celda) {
        this.celda = celda;
    }

    public Integer getXx() {
        return xx;
    }

    public void setXx(Integer xx) {
        this.xx = xx;
    }

    public Integer getYy() {
        return yy;
    }

    public void setYy(Integer yy) {
        this.yy = yy;
    }
    private void cargarIcono(String path){
        URL url = System.class.getResource(path);
        ImageIcon im =new ImageIcon(url);
        this.setIcon(im);
    }
}
