/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shanaconsulting.virtual.core.ui;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author pierre jacques
 */
public class LparUtilImport extends  JPanel{
    
    final JFileChooser  chooser = new JFileChooser();
    final  FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Text File", "txt", "csv","lparutil");
    public LparUtilImport(){
        super();
        init();
    }
    private void init(){
        chooser.setFileFilter(filter);
    }
    
    public void show(){
        chooser.showOpenDialog(this);
    }
    
    public final File getSelectedFile(){
        return chooser.getSelectedFile();
    }
    
    public final File [] getSelectedFiles(){
        return chooser.getSelectedFiles();
    }
}
