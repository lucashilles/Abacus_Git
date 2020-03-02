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
import javax.swing.JOptionPane;
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
    private float gamaEsforcos, gamaC,gamaF;
    private float EuAco;

    public CoeficientesViewController(JFrame parent, Materials materiais) {
        this.parent = parent;
        this.materiais = materiais;
        view = new CoeficientesView();
        System.out.println("FCk analisado: "+ materiais.getConcrete().getFck());
        init();
    }

    private void init() {
        view.getBtnOK().addActionListener(e -> criarCoefs());
        view.getBtnConfirmarGamas().addActionListener(e -> ConfirmaCoefs());
        view.getbtnConfirmargama().addActionListener(e -> confirmarGama());
        view.getJTPane().setEnabledAt(1, false);
        view.getJTPane().setEnabledAt(0, false);
        view.getJTPane().setSelectedIndex(2);
       frame = new JDialog(parent,"Coeficientes");
       frame.add(view);
       frame.pack();
       frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
       frame.setLocationRelativeTo(parent);
       frame.setResizable(false);
       frame.setVisible(true);
        
    }
    private void confirmarGama(){
        if(view.getTxtgamaEsforcos().getText().isEmpty() != true){
            float gamaEsf = Float.parseFloat(view.getTxtgamaEsforcos().getText());
            this.gamaEsforcos = gamaEsf;
            view.getJTPane().setEnabledAt(0, true);
            view.getJTPane().setSelectedIndex(0);
        }else{
            JOptionPane.showMessageDialog(frame, "Coeficiente inválido","ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void ConfirmaCoefs(){
        if(view.getTxtGamac().getText().isEmpty() == false && view.getTxtGamaF().getText().isEmpty() == false){
            float gmaf; float gamac;
            gamac = Float.parseFloat(view.getTxtGamac().getText());
            gmaf = Float.parseFloat(view.getTxtGamaF().getText());
            this.gamaC = gamac;
            this.gamaF = gmaf;
            //this.coeficientes = new Coeficientes(materiais,(double)this.gamaC,(double) this.gamaF,(double) this.gamaEsforcos);
            this.materiais.getConcrete().setFcd((float) this.gamaC);
            this.materiais.getConcrete().setSigmaCD();
            view.getTxtLambda().setText(String.format("%.2f", materiais.getConcrete().getLambda()));
            view.getTxtalfa().setText(String.format("%.2f", materiais.getConcrete().getAlfac()));
            view.getTxtFcd().setText(String.format("%.2f", materiais.getConcrete().getFcd()));
            view.getTxtSigmaCd().setText(String.format("%.2f", materiais.getConcrete().getSigmacd()));
            view.getTxtE0().setText(String.format("%.2f", materiais.getConcrete().getDeformacaoE0()));
            view.getTxtEu().setText(String.format("%.2f", materiais.getConcrete().getDeformacaoEu()));
            view.getJTPane().setEnabledAt(1, true);
            view.getJTPane().setSelectedIndex(1);
        }
    }
    private void criarCoefs(){
        if(gamaC > 0&& gamaF > 0 && gamaEsforcos > 0){
            coeficientes = new Coeficientes(materiais,gamaC, gamaF, gamaEsforcos);
            this.EuAco = Float.parseFloat(view.getTxtEuAco().getText());
            frame.setVisible(false);
            
        }
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

    /**
     * @return the gamaEsforcos
     */
    public float getGamaEsforcos() {
        return gamaEsforcos;
    }

    /**
     * @return the EuAco
     */
    public float getEuAco() {
        return EuAco;
    }

}
