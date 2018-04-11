/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Objects.GameEst;
import controller.Buscaminas;
import gui.listener.EncabezadoListener;
import gui.listener.TableroListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author JAVIER1
 */
public class PrincipalFrame extends JFrame{
    private ControlPanel pnlControl;
    private TableroPanel pnlTablero;
    private Buscaminas buscaminas;
    
    
    public PrincipalFrame(String title){
        super(title);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(315,410);
        super.setResizable(false);
        super.setLayout(new BorderLayout());
        
        buscaminas = new Buscaminas(10,10,10);
        
        super.setJMenuBar(createMenu());
        
        pnlControl=new ControlPanel();
        pnlTablero=new TableroPanel();
        pnlTablero.drawTablero(buscaminas);
        
        pnlTablero.setTableroListener(new TableroListener(){
          @Override
          public void onClickButton(Integer x, Integer y){
              buscaminas.abrirCelda(x, y);
              if(buscaminas.getEstado()== GameEst.Sigue){
                  
                  pnlTablero.removeAll();
                  pnlTablero.drawTablero(buscaminas);
                  PrincipalFrame.this.repaint();
              }else{
                  
                  pnlTablero.removeAll();
                  pnlTablero.drawTablero(buscaminas);
                  PrincipalFrame.this.repaint();
                  
              }
                  
          }
          @Override
          public void onRigthClickButton(Integer x, Integer y){
                buscaminas.marcarCelda(x, y);
                pnlTablero.removeAll();
                pnlTablero.drawTablero(buscaminas);
                PrincipalFrame.this.repaint();
          }
        });
        
        pnlControl.setListener(new EncabezadoListener(){
            @Override
            public void btnJuegoOnClick(){
                int a,b,c;
                a= buscaminas.getDimx();
                b= buscaminas.getDimy();
                c= buscaminas.getMinas();
                pnlTablero.removeAll();
                buscaminas= new Buscaminas(a,b,c);
                pnlTablero.drawTablero(buscaminas);
                PrincipalFrame.this.repaint();
            }
        });
        super.add(pnlControl,BorderLayout.NORTH);
        super.add(pnlTablero,BorderLayout.CENTER);
        
        super.setVisible(true);
    }
    
    private JMenuBar createMenu(){
        JMenuBar menu=new JMenuBar();
        
        JMenu mmArchivo=new JMenu("Archivo");
        JMenuItem nnNuevo=new JMenuItem("Juego Nuevo");
        nnNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
        nnNuevo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Nuevo");
            }
        });
        
        JMenuItem nnAbrir=new JMenuItem("Abrir...");
        nnAbrir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser fc = new JFileChooser();
                if(fc.showOpenDialog(PrincipalFrame.this) == JFileChooser.APPROVE_OPTION){
                    //cargar el archivo
                    System.out.println(fc.getSelectedFile());
                }
            }
        });
        nnAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
        nnAbrir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Abrir Juego");
            }
        });
        
        JMenuItem nnGuardar=new JMenuItem("Guardar...");
        nnGuardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser fc = new JFileChooser();
                if(fc.showSaveDialog(PrincipalFrame.this) == JFileChooser.APPROVE_OPTION){
                    System.out.println(fc.getSelectedFile());
                    File f = new File(fc.getSelectedFile().toString());
                    if(f.exists()){
                        JOptionPane.showMessageDialog(PrincipalFrame.this, "No","Titulo", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        
        nnGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,0));
        nnGuardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("Guardar");
            }
        });
        
        JMenuItem nnSalir=new JMenuItem("Salir");
        nnSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
        nnSalir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        
        mmArchivo.add(nnNuevo);
        mmArchivo.addSeparator();
        mmArchivo.add(nnAbrir);
        mmArchivo.add(nnGuardar);
        mmArchivo.addSeparator();
        mmArchivo.add(nnSalir);
        
        JMenu mmNiveles=new JMenu("Niveles");
         
        JRadioButtonMenuItem nnPrincipiante=new JRadioButtonMenuItem("Pichón");
        nnPrincipiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int msj=JOptionPane.showConfirmDialog(null, "Estás seguro de iniciar un nuevo Juego?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if(msj == JOptionPane.YES_OPTION){
                PrincipalFrame.this.pnlTablero.removeAll();
                PrincipalFrame.this.buscaminas = new Buscaminas(10,10,10);
                PrincipalFrame.this.pnlTablero.drawTablero(PrincipalFrame.this.buscaminas);
                PrincipalFrame.this.setSize(315, 410);
                PrincipalFrame.this.add(pnlTablero,BorderLayout.CENTER);
                PrincipalFrame.this.repaint();
                }
            }
        });
        
        JRadioButtonMenuItem nnIntermedio=new JRadioButtonMenuItem("Cuervo");
        nnIntermedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int msj=JOptionPane.showConfirmDialog(null, "Estás seguro de iniciar un nuevo Juego?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if(msj == JOptionPane.YES_OPTION){
                PrincipalFrame.this.pnlTablero.removeAll();
                PrincipalFrame.this.buscaminas = new Buscaminas(20,20,40);
                PrincipalFrame.this.pnlTablero.drawTablero(PrincipalFrame.this.buscaminas);
                PrincipalFrame.this.setSize(625, 720);
                PrincipalFrame.this.add(pnlTablero,BorderLayout.CENTER);
                PrincipalFrame.this.repaint();
                }
            }
        });
        
        JRadioButtonMenuItem nnExperto=new JRadioButtonMenuItem("Águila Real");
        nnExperto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int msj=JOptionPane.showConfirmDialog(null, "Estás seguro de iniciar un nuevo Juego?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if(msj == JOptionPane.YES_OPTION){
                PrincipalFrame.this.pnlTablero.removeAll();
                PrincipalFrame.this.buscaminas = new Buscaminas(40,20,80);
                PrincipalFrame.this.pnlTablero.drawTablero(PrincipalFrame.this.buscaminas);
                PrincipalFrame.this.setSize(1245, 720);
                PrincipalFrame.this.add(pnlTablero,BorderLayout.CENTER);
                PrincipalFrame.this.repaint();
                }
            } 
        });
        
        JRadioButtonMenuItem nnPersonalizado=new JRadioButtonMenuItem("Personalizado...");
        nnPersonalizado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        
        ButtonGroup bgpOpciones =new ButtonGroup();
        bgpOpciones.add(nnPrincipiante);
        bgpOpciones.add(nnIntermedio);
        bgpOpciones.add(nnExperto);
        bgpOpciones.add(nnPersonalizado);
        
        mmNiveles.add(nnPrincipiante);
        mmNiveles.add(nnIntermedio);
        mmNiveles.add(nnExperto);
        mmNiveles.addSeparator();
        mmNiveles.add(nnPersonalizado);
        
        JMenu mmAiuda = new JMenu("Ayuda");
        JMenuItem nnAcercaDe=new JMenuItem("Acerca de");
        
        menu.add(mmArchivo);
        menu.add(mmNiveles);
        menu.add(mmAiuda);
       
        
        return menu;
    }
    
}
