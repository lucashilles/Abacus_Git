/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.secaoTransversal;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import views.telaInicial;

/**
 *
 * @author Administrador
 */
public class telaInicialController {

    private JFrame parent, frame;
    private telaInicial tela;
    private secaoTransversal secaoTransversal;

    public telaInicialController(JFrame parent) {
        this.parent = parent;
        tela = new telaInicial();
        init();

    }

    private void init() {
        tela.getBtnSecao().addActionListener(e -> abrirSecao(e));
        frame = new JFrame(parent.getTitle());
        frame.add(tela);
        frame.pack();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(frame.getClass().getResource("/icons/Icone.png")));
        frame.setLocationRelativeTo(parent);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(getFrame(), "Tem certeza que deseja sair?") == JOptionPane.OK_OPTION) {
                    frame.dispose();
                    parent.setVisible(true);
                }
            }
        });
        getFrame().setVisible(true);
    }

    private void abrirSecao(ActionEvent e) {
        secaoDrawController sdc = new secaoDrawController(frame);
//      Devido a alteração da sdc para modal, a execução desta função só
//      irá continuar após ao fechamento do sdc, o que possibilita obter a
//      seção gerada lá.
        secaoTransversal = sdc.getSec();
        System.out.println("Qtd de vertices: " + secaoTransversal.getNumVertice());
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

}
