/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.barra;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import views.barADD;

/**
 *
 * @author Administrador
 */
public class barADDController {

    barra bar = null;
    barADD view;
    JFrame frame;
    JDialog dialog;

    public barADDController(JFrame parent) {
        this.frame = parent;
        view = new barADD();
        init();
    }

    private void init() {
        view.getBtnLancarB().addActionListener(e -> addBar(e));
        view.getTxtCoordX().setText("0.0");
        view.getTxtCoordY().setText("0.0");

        view.getTxtCoordX().setSelectedTextColor(new Color(0, 51, 0));
        view.getTxtCoordY().setSelectedTextColor(new Color(0, 51, 0));

        view.getTxtCoordX().selectAll();
        view.getTxtCoordY().selectAll();

        dialog = new JDialog(frame, "Barras");
        dialog.add(view);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private void addBar(ActionEvent e) {
        if (view.getJComboDiametro().getSelectedIndex() != 0 || view.getTxtCoordX().getText().isEmpty() == false || view.getTxtCoordY().getText().isEmpty() == false) {

            if (view.getJComboDiametro().getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(dialog, "Por favor, selecione um diâmetro válido", "Aviso", JOptionPane.ERROR_MESSAGE);
            } else {
                float diametro, x, y;
                diametro = Float.parseFloat((String)view.getJComboDiametro().getSelectedItem());
                x = Float.parseFloat(view.getTxtCoordX().getText());
                y = Float.parseFloat(view.getTxtCoordY().getText());

                bar = new barra(diametro, x, y);
                dialog.setVisible(false);
            }
        }
    }

    public barra getBarra() {
        return this.bar;
    }
}
