/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import views.telaInicial;

/**
 *
 * @author Administrador
 */
public class telaInicialController {
    private JFrame parent, frame;
    private telaInicial tela;
    
    public telaInicialController(JFrame parent){
        this.parent = parent;
        tela = new telaInicial();
        init();
    
    }
    private void init(){
        tela.getBtnSecao().addActionListener(e -> abrirSecao(e));
        frame = new JFrame(parent.getTitle());
        frame.add(tela);
        frame.pack();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(frame.getClass().getResource("/icons/Icone.png")));
        frame.setLocationRelativeTo(parent);
        frame.setVisible(true);
    }
    private void abrirSecao(ActionEvent e){
        secaoDrawController sdc = new secaoDrawController(frame);
    }
    
    
}
