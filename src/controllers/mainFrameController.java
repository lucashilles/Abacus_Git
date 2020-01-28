/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javafx.scene.paint.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import views.mainFrame;

/**
 *
 * @author Administrador
 */
public class mainFrameController {
    private mainFrame mf;

    public mainFrameController() {
        mf = new mainFrame();
        mf.setPreferredSize(new Dimension(400,300));
        init();
    }
    private void init(){
        mf.getBntNew().addActionListener(e -> Start(e));
        mf.setTitle("ABACUS");
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.pack();
        mf.setVisible(true);
    
    }
    private void Start(ActionEvent e){
     new telaInicialController(mf);
     mf.setVisible(false);
    
    }
}
