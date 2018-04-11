/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import controller.Buscaminas;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author JAVIER1
 */
public class ArchivoGuardar {
    private JFileChooser chooser;
    private File file;
    private FileOutputStream output;
    private ObjectOutputStream  writer;
    
    public ArchivoGuardar(Buscaminas b) throws FileNotFoundException, IOException{
        this.chooser = new JFileChooser();
        this.chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
            this.chooser.setSelectedFile(this.file);
        
        this.output=new FileOutputStream(file);
        this.writer=new ObjectOutputStream(output);
        
        writer.writeObject(b);
        writer.close();
        output.close();
        
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileOutputStream getOutput() {
        return output;
    }

    public void setOutput(FileOutputStream output) {
        this.output = output;
    }

    public ObjectOutputStream getWriter() {
        return writer;
    }

    public void setWriter(ObjectOutputStream writer) {
        this.writer = writer;
    }
    
    
    
}
