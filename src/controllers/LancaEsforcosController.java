/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.Esforcos;
import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import views.LancaEsforcos;

/**
 *
 * @author Administrador
 */
public class LancaEsforcosController {
    private Esforcos esforcos;
    private LancaEsforcos view;
    private JDialog frame;
    private JFrame parent;

    public LancaEsforcosController(JFrame parent) {
        this.parent = parent;
        view = new LancaEsforcos();
        init();
    }

    private void init() {
        view.getBtnLancar().addActionListener(e -> lancar());
        view.getTxtMx().setText("0.0");
        view.getTxtMy().setText("0.0");
        view.getTxtNk().setText("0.0");
        view.getTxtEx().setEditable(false);
        view.getTxtEy().setEditable(false);
        frame = new JDialog(parent, "Esforços");
        frame.add(view);
        frame.pack();
        frame.setIconImage(parent.getIconImage());
        frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        frame.setLocationRelativeTo(parent);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if (JOptionPane.showConfirmDialog(frame, "Tem certeza que deseja sair ?") == JOptionPane.OK_OPTION) {
                    frame.setVisible(false);
                    parent.setVisible(true);
                }
            }
        });
        parent.setVisible(false);
        frame.setVisible(true);
    }
 private void lancar(){
     float ex, ey, mx, my, nk;
      mx = Float.parseFloat(view.getTxtMx().getText());
      my = Float.parseFloat(view.getTxtMy().getText());
      nk = Float.parseFloat(view.getTxtNk().getText());
      ex= (mx/nk)*100;
      ey = (my/nk)*100;
      view.getTxtEx().setText(String.format("%.2f", ex));
      view.getTxtEy().setText(String.format("%.2f", ey));
     if(JOptionPane.showConfirmDialog(frame, view.getJPanelE()) == JOptionPane.OK_OPTION);
     esforcos = new Esforcos(mx, my,nk);
     frame.setVisible(false);
     parent.setVisible(true);
 }

    /**
     * @return the esforcos
     */
    public Esforcos getEsforcos() {
        return esforcos;
    }
}
