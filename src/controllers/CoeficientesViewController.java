/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.Coeficientes;
import entites.Materials;
import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JFrame;
import views.CoeficientesView;

/**
 *
 * @author Administrador
 */
public class CoeficientesViewController {
    private Materials materiais = null;
    private Coeficientes coeficientes;
    private CoeficientesView view = null;
    private JDialog frame;
    private JFrame parent = null;

    public CoeficientesViewController(JFrame parent, Materials materiais) {
        this.parent = parent;
        this.materiais = materiais;
        view = new CoeficientesView();
        System.out.println("FCk analisado: "+ materiais.getConcrete().getFck());
        init();
    }

    private void init() {
       frame = new JDialog(parent,"Coeficientes");
       frame.add(view);
       frame.pack();
       frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
       frame.setLocationRelativeTo(parent);
       frame.setResizable(false);
       frame.setVisible(true);
        
    }

    /**
     * @return the coeficientes
     */
    public Coeficientes getCoeficientes() {
        return coeficientes;
    }

    /**
     * @return the materiais
     */
    public Materials getMateriais() {
        return materiais;
    }

}
