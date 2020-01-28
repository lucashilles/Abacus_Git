/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.Vertice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import views.verticesCoordenates;

/**
 *
 * @author Administrador
 */
public class verticesCoordenatesController {

    Vertice v = null;
    JDialog dialog;
    JFrame parent;
    verticesCoordenates view;

    public verticesCoordenatesController(JFrame parent) {
        this.parent = parent;
        view = new verticesCoordenates();
        init();
    }

    private void init() {
        view.getBtnOk().addActionListener(e -> Lancar());
        view.getBtnOk().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    Lancar();
                }

            }

        });
        view.getTxtCY().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    Lancar();
                }

            }

        });

        view.getTxtCX().setText("0.0");
        view.getTxtCY().setText("0.0");

        view.getTxtCX().setSelectedTextColor(new Color(0, 51, 51));
        view.getTxtCY().setSelectedTextColor(new Color(0, 51, 51));

        view.getTxtCX().selectAll();
        view.getTxtCY().selectAll();

        dialog = new JDialog(parent, "Vértice");
        dialog.add(view);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private void Lancar() {
        if (view.getTxtCX().getText().isEmpty() == true || view.getTxtCY().getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(parent, "AVISO", "Coordenadas de vértices inválidas!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            float x, y;
            x = Float.parseFloat(view.getTxtCX().getText());
            y = Float.parseFloat(view.getTxtCY().getText());
            v = new Vertice(x, y);
            dialog.setVisible(false);
        }
    }

    public Vertice getVerticeLancado() {
        return this.v;
    }

}
