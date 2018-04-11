/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import controller.Buscaminas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author JAVIER1
 */
public class ArchivoAbrir {
    private JFileChooser chooser;
    private File file;
    private FileInputStream input;
    private ObjectInputStream reader;
    private Buscaminas resultado;
    
    
    public ArchivoAbrir() throws FileNotFoundException, IOException, ClassNotFoundException{
        this.chooser = new JFileChooser();
        this.file=new File("");
        this.chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            this.file = chooser.getSelectedFile();
        
        this.input=new FileInputStream(file);
        this.reader=new ObjectInputStream(input);
        
        this.resultado=(Buscaminas)reader.readObject();
        reader.close();
        input.close();
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileInputStream getInput() {
        return input;
    }

    public void setInput(FileInputStream input) {
        this.input = input;
    }

    public ObjectInputStream getReader() {
        return reader;
    }

    public void setReader(ObjectInputStream reader) {
        this.reader = reader;
    }

    public Buscaminas getResultado() {
        return resultado;
    }

    public void setResultado(Buscaminas resultado) {
        this.resultado = resultado;
    }
    
    
}
