/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.listener.EncabezadoListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;


/**
 *
 * @author JAVIER1
 */
public class ControlPanel extends JPanel{
    private TButton btnJuego;
    private JPanel pnlBoton;
    private JTextField txtMina;
    private JTextField txtTiempo;
    private JLabel mensaje;
    
    private EncabezadoListener listener;
    
    public ControlPanel (){
        super();
        super.setLayout(new BorderLayout());
        super.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        
        btnJuego=new TButton("/images/004-smiling.png");
        
        
        txtMina=new JTextField("000");
        txtMina.setPreferredSize(new Dimension(60,30));
        txtMina.setFont(new Font("Courier new", Font.BOLD, 24));
        txtMina.setHorizontalAlignment(SwingConstants.CENTER);
        txtMina.setForeground(Color.RED);
        txtMina.setBackground(Color.BLACK);
        txtMina.setFocusable(false);
        
        txtTiempo=new JTextField("000");
        txtTiempo.setPreferredSize(new Dimension(60,30));
        txtTiempo.setFont(new Font("Courier new", Font.BOLD, 24));
        txtTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        txtTiempo.setForeground(Color.RED);
        txtTiempo.setBackground(Color.BLACK);
        txtTiempo.setFocusable(false);
        
        pnlBoton=new JPanel();
        pnlBoton.add(btnJuego);
        
        btnJuego.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
            listener.btnJuegoOnClick();
            }
        });
        
        super.add(pnlBoton,BorderLayout.CENTER);
        super.add(txtMina,BorderLayout.WEST);
        super.add(txtTiempo,BorderLayout.EAST);
    }
    
    public void setButtonJuego(Boolean check){
        if(check)
            btnJuego=new TButton("/images/002-happy.png");
        else
            btnJuego=new TButton("/images/003-sad.png");
        
        pnlBoton = new JPanel();
        pnlBoton.add(btnJuego);
        super.add(pnlBoton, BorderLayout.CENTER);
        super.repaint();
    }
    public void setListener(EncabezadoListener listener){
        this.listener=listener;
    }
}
