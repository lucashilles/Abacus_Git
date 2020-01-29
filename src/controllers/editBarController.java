/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.barra;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import views.editBar;

/**
 *
 * @author Administrador
 */
public class editBarController {
    JFrame parent;
    JDialog dialog;
    editBar view;
    private barra barraE = null;
    
    public editBarController(JFrame parent, barra bar){
        this.parent = parent;
        this.barraE = bar;
        view = new editBar();
      
        init();
        
    }
    private void init(){
        view.getBtnEditar().addActionListener(e -> editar(e));
        view.getTxtCx().setText((Float.toString(getBarraE().getX())));
        view.getTxtCy().setText(Float.toString(getBarraE().getY()));
        view.getComboB().setSelectedItem(String.valueOf(barraE.getDiametro()));
        dialog = new JDialog(parent, "Bar edit");
        dialog.add(view);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
        
    }
 

    /**
     * @return the barraE
     */
    public barra getBarraE() {
        return barraE;
    }
    private void editar(ActionEvent e){
        float diametro;
        float x;
        float y;
        diametro = Float.parseFloat((String)view.getComboB().getSelectedItem());
        x = Float.parseFloat(view.getTxtCx().getText());
        y = Float.parseFloat(view.getTxtCy().getText());
        
        barraE.setDiametro(diametro);
        barraE.setX(x);
        barraE.setY(y);
        dialog.setVisible(false);
        System.out.println("diametro novo: "+ barraE.getDiametro());
        
    }

    
}
