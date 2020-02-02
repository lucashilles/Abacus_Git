/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.Aco;
import entites.Concreto;
import entites.Materials;
import entites.tipoAco;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import views.Materiais;

/**
 *
 * @author Administrador
 */
public class MateriaisController {
    private Materials materiais;
    private Concreto concreto;
    private Aco aco;
    private tipoAco acoType;
    private JDialog frame;
    private JFrame parent;
    private Materiais view;

    public MateriaisController(JFrame parent) {
        this.parent = parent;
        view = new Materiais();
        init();
    }
    private void init(){   
        view.getBtnConfirmar().addActionListener(e ->finalizar());
        view.getBntEAco().addActionListener(e -> setAco());
        view.getJComboBoxAco().addItemListener(e -> setFyk(e));
        view.getBtnEcs().addActionListener(e -> setECs());
        view.getJCkeckEcs().addActionListener(e ->editECS(e));
        view.getBtnFck().addActionListener(e -> setFck());
        view.getBtnConfirmar().setEnabled(false);
        view.getTxtEcs().setEditable(false);
        view.getBtnEcs().setEnabled(false);
        frame = new JDialog(parent,"Materiais");
        frame.add(view);
        frame.pack();
        frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        frame.setLocationRelativeTo(parent);
        frame.setVisible(true);
        
    }
    private void setFck(){
        if(view.getTxtFck().getText() != null){
            float fck = Float.parseFloat(view.getTxtFck().getText());
            concreto = new Concreto(fck);
            view.getTxtEcs().setText(String.format("%.2f", concreto.getModuloElasticidade()));

        }
    }
    private void editECS(ActionEvent e){
      if(view.getJCkeckEcs().isSelected() == true){
          view.getTxtEcs().setEditable(true);
          view.getBtnEcs().setEnabled(true);
      }else{
          view.getTxtEcs().setEditable(false);
          view.getBtnEcs().setEnabled(false);
      }
    }
    private void setECs(){
        if(view.getJCkeckEcs().isSelected() == true){
            concreto.setModuloElasticidade(Float.parseFloat(view.getTxtEcs().getText()));
            System.out.println("ECS novo: "+ concreto.getModuloElasticidade());
            
        }
    }
   
    public void setFyk(ItemEvent e){
        if(e.getStateChange() == ItemEvent.SELECTED){
            acoType = (tipoAco) e.getItem();
            view.getTxtFyk().setText(String.format("%.2f", acoType.getTipoAco()));
        }
    }
    public void setAco(){
        if(view.getTxtEAco().getText().isEmpty() == false && view.getTxtFyk().getText().isEmpty()==false){
            aco = new Aco(acoType, Float.parseFloat(view.getTxtEAco().getText()));
            //vai ser removido depois - apenas por questao de verificação de funcionamento
            System.out.println("Aço: "+ acoType.toString()+" fyk: "+ acoType.getTipoAco());
            System.out.println("E aco: "+ aco.getEcs()+"Fyk a: "+ aco.getFyk()+" tipo: "+ aco.getTypeAco());
            view.getBtnConfirmar().setEnabled(true);
        }
    }
    private void finalizar(){
        if(aco != null && concreto != null){
            materiais = new Materials(concreto, aco);
            //verificação apenas
            System.out.println("Concreto Mpa: "+ materiais.getConcrete().getFck()+ "Aco fyk: "+ materiais.getAco().getFyk()+ "tipoAco: "+ materiais.getAco().getTypeAco());
            frame.setVisible(false);
            parent.setVisible(true);
        }
    }

    /**
     * @return the concreto
     */
    public Concreto getConcreto() {
        return concreto;
    }

    /**
     * @return the aco
     */
    public Aco getAco() {
        return aco;
    }

    /**
     * @return the acoType
     */
    public tipoAco getAcoType() {
        return acoType;
    }

    /**
     * @return the materiais
     */
    public Materials getMateriais() {
        return materiais;
    }
}
