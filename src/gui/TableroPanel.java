/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Objects.Celda;
import Objects.Estado;
import controller.Buscaminas;
import gui.listener.TableroListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author JAVIER1
 */
public class TableroPanel extends JPanel{
    private TableroListener listener;
    
    public TableroPanel(){
        super();
        super.setBackground(Color.LIGHT_GRAY);
        super.setLayout(null);
    }
    
    
    public void drawTablero(Buscaminas buscaminas){
        for(int i=0; i<buscaminas.getDimx(); i++){
            for(int j=0; j<buscaminas.getDimy(); j++){
                Celda celda = buscaminas.getTablero()[i][j];
                if(celda.getEstado() == Estado.Abierto){
                    if(!celda.isMina()){
                        JLabel abierto = new JLabel ();
                    
                        if(celda.getNumero() != 0)
                            abierto.setText(celda.getNumero().toString());
                    
                        abierto.setBounds(i*31, j*31, 30, 30);
                        abierto.setFont(new Font("Courier new", Font.BOLD, 24));
                        this.colorNumber(abierto, celda);
                        abierto.setHorizontalAlignment(SwingConstants.CENTER);
                        abierto.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                        super.add(abierto);
                    }else{
                        TButton abierto = new TButton(celda, i ,j);
                        abierto.setBounds(i*31, j*31, 30, 30);
                        super.add(abierto);
                    }
                }else{
                    TButton cerrado=new TButton(celda, i, j);
                    cerrado.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        listener.onClickButton(cerrado.getXx(),cerrado.getYy());
                    }
                    });
                    cerrado.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e){
                            if(e.getButton() == MouseEvent.BUTTON3){
                                listener.onRigthClickButton(cerrado.getXx(), cerrado.getYy());
                            }  
                        }
                    });
                    cerrado.setBounds(i*31, j*31, 30, 30);
                    super.add(cerrado);
                }
            }
        }
    }
    
    public void setTableroListener(TableroListener listener){
        this.listener=listener;
    }
    
    private void colorNumber(JLabel lbl, Celda celda){
        switch(celda.getNumero()){
            case 1:
                lbl.setForeground(Color.BLUE);
                break;
            case 2:
                lbl.setForeground(Color.GREEN);
                break;
            case 3:
                lbl.setForeground(Color.RED);
                break;
            case 4:
                lbl.setForeground(Color.CYAN);
                break;
            case 5:
                lbl.setForeground(Color.ORANGE);
                break;
            default:
                break;
                    }
    }
}
