/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Administrador
 */
public class progressDialog extends JDialog {

    private JButton btn_cancel;
    private JProgressBar progressBar;

    public progressDialog(Window parent) {
        super(parent, "Processando...", ModalityType.APPLICATION_MODAL);
        

        btn_cancel = new JButton("Cancelar");
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        Dimension size = btn_cancel.getPreferredSize();
        size.width = 400;
        progressBar.setPreferredSize(size);
        setLayout(new FlowLayout());
        add(progressBar);
        add(btn_cancel);
        pack();
        setLocationRelativeTo(parent);
        
    }

    public void setMaximum(int value) {
        this.progressBar.setMaximum(value);
    }

    public void setValue(int value) {
        this.progressBar.setValue(value);
    }

}
