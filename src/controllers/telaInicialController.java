/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.Esforcos;
import entites.Materials;
import entites.NeutralLine;
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
    
    private Materials materiais;
    private JFrame parent = null, frame;
    private telaInicial tela = null;
    private secaoTransversal secaoTransversal;
    private Esforcos esforcos;
    private Esforcos esforcosCalculo;
    // private Barras barras;

    public telaInicialController(JFrame parent) {
        this.parent = parent;
        tela = new telaInicial();
        init();
        
    }
    
    private void init() {
        
        tela.getBtnAbaco().addActionListener(e -> metodoIterativo());
        tela.getBtnConfig().addActionListener(e -> lancarCoeficientes());
        tela.getBtnProp().addActionListener(e -> lancarMateriais());
        tela.getBtnEsforcos().addActionListener(e -> lancarEsforcos());
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
        frame.setVisible(true);
        
    }
    
    private void abrirSecao(ActionEvent e) {
        secaoDrawController sdc = new secaoDrawController(frame);

        if (sdc.getSecEnviar() == null) {
            tela.getBtnEsforcos().setEnabled(false);
            
        } else {
            tela.getBtnEsforcos().setEnabled(true);
            secaoTransversal = sdc.getSecEnviar();

            // Apenas para verificar se esta tudo certo! apos o termino do programa, será removido
            System.out.println("Qtd de vertices: " + secaoTransversal.getNumVertice());
            System.out.println("QTS de barras: " + secaoTransversal.getNumBars());
            System.out.println("centroide: " + secaoTransversal.getCentroide().getX() + ", " + secaoTransversal.getCentroide().getY());
            System.out.println("AREA: " + secaoTransversal.getArea());
            System.out.println("BArs AREA: " + secaoTransversal.getBars().getAreaBars());

            
        }
        
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }
    
    private void lancarEsforcos() {
        LancaEsforcosController lec = new LancaEsforcosController(frame);
        if (lec.getEsforcos() != null) {
            esforcos = lec.getEsforcos();
            System.out.println("Esforcos: " + esforcos.getMxk());
        }
    }
    
    private void lancarMateriais() {
        MateriaisController mc = new MateriaisController(frame);
        if (mc.getMateriais() != null) {
            materiais = mc.getMateriais();
            // apenas verificaçoes de funcionamento do code
            System.out.println("Def: " + materiais.getConcrete().getDeformacaoE0() + " eu: " + materiais.getConcrete().getDeformacaoEu());

            System.out.println("fcd: " + materiais.getConcrete().getFcd());
            
            System.out.println("SigmaCD: " + materiais.getConcrete().getSigmacd());
            System.out.println("Concreto fck: " + materiais.getConcrete().getFck() + ", " + "Aço: " + materiais.getAco().getTypeAco() + "Ecs: " + materiais.getConcrete().getModuloElasticidade());
            tela.getBtnConfig().setEnabled(true);
        }
        
    }

    // terminar o code implemetation
    private void lancarCoeficientes() {
        if(this.materiais != null){
        CoeficientesViewController CVC = new CoeficientesViewController(frame, materiais);
        this.materiais.setCoeficiente(CVC.getCoeficientes());
        this.materiais.getAco().setFyd((float) materiais.getCoef().getGamaS());
        this.materiais.getConcrete().setFcd((float) this.materiais.getCoef().getGamaC());
        this.materiais.getConcrete().setSigmaCD();
        
        this.materiais.getAco().setDefAco(CVC.getEuAco());
        this.esforcosCalculo = new Esforcos((float) (esforcos.getMxk() * materiais.getCoef().getGamaEsforcos()), (float) (esforcos.getMyk() * materiais.getCoef().getGamaEsforcos()), (float) (esforcos.getNk() * materiais.getCoef().getGamaEsforcos()));
        
        System.out.println("Fyd: " + materiais.getAco().getFyd());
        // testando code
        System.out.println("SigmaCD : " + materiais.getConcrete().getSigmacd());
    } else{
        
        }
    }
    
    private void metodoIterativo() {
        abacoViewController avc = new abacoViewController(parent,this.secaoTransversal,this.esforcosCalculo,this.materiais);
    }

    /**
     * @return the esforcosCalculo
     */
    public Esforcos getEsforcosCalculo() {
        return esforcosCalculo;
    }
}
