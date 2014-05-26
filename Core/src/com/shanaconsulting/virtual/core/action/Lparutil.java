/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shanaconsulting.virtual.core.action;

import com.shanaconsulting.virtual.core.ui.LparUtilImport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "PowerVM",
        id = "com.shanaconsulting.virtual.core.Lparutil"
)
@ActionRegistration(
        displayName = "#CTL_Lparutil"
)
@ActionReferences(
        {@ActionReference(path = "Menu/File/Import", position = 1300),
        @ActionReference(path="Toolbars/Import",name = "Import data from lslparutil")}
)
@Messages("CTL_Lparutil=Lpartuil")
public final class Lparutil implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        LparUtilImport import1 = new LparUtilImport();
        import1.show();
    }
}
