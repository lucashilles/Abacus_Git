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
import java.awt.BorderLayout;
import java.awt.CardLayout;

import java.awt.Dialog;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;

import views.abacoView;
import views.grafico;

/**
 *
 * @author Administrador
 */
public class abacoViewController {

    secaoTransversal sec;
    Esforcos esf;
    Materials mat;
    private abacoView view = null;
    private JDialog frame;
    private JFrame parent = null;
    private float taxaArm, NormalReduzida;

    public abacoViewController(JFrame parent, secaoTransversal sec, Esforcos esf, Materials mat) {
        this.sec = sec;
        this.esf = esf;
        this.mat = mat;
        this.parent = parent;
        this.view = new abacoView();
        taxa();
        init();
    }

    private void taxa() {
        if (this.mat != null && this.sec != null) {
            float Ac, As, sigma, fyd, normal;
            Ac = this.sec.getArea(); //cm²
            As = this.sec.getBars().getAreaBars() / 100; //cm²
            sigma = this.mat.getConcrete().getSigmacd() / 10; //kN/cm²
            fyd = this.mat.getAco().getFyd() / 10; //kN/cm²
            normal = this.esf.getNk();
            this.taxaArm = (As / Ac) * (fyd / sigma);
            this.NormalReduzida = ((normal) / (Ac * sigma));
        }
    }

    private void init() {
        view.getBtnGerar().addActionListener(e -> gerar());
        view.getBtnEnvoltoria().addActionListener(e -> envoltoria());
        view.getJPanelEnvoltoria().validate();
        view.getTxtNormal().setText(String.format("%.2f", this.NormalReduzida));
        view.getTxtTaxa().setText(String.format("%.2f", this.taxaArm));
        frame = new JDialog(parent, "Ábaco");
        frame.setResizable(true);
        frame.add(view);
        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(parent);
        frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        frame.setVisible(true);

    }
//  arrumar metodo

    private void envoltoria() {
        grafico graf = new grafico();
        view.getJPanelEnvoltoria().add(graf.grafico(), BorderLayout.CENTER);
        view.getJPanelEnvoltoria().validate();
        CardLayout cl = (CardLayout) view.getJPChart().getLayout();
        cl.show(view.getJPChart(), "env");
        List< Esforcos> es;
        List<Float> N = new ArrayList<>();
        List<Float> Mx = new ArrayList<>();
        List<Float> My = new ArrayList<>();
        NeutralLine LN = new NeutralLine(this.sec, this.esf, this.mat);
        es = LN.envoltoria(0, 360, this.sec.getBars().getAreaBars(), this.esf.getNk());
        for (int i = 0; i < es.size(); i++) {
            Mx.add(es.get(i).getMxk());
            My.add(es.get(i).getMyk());
        }
        Mx.add(Mx.get(0));
        My.add(My.get(0));
        graf.setSeries(Mx, My, this.taxaArm);
        graf.grafico().revalidate();

    }

    private void gerar() {
        grafico graf = new grafico();
        view.getJPanelEnvoltoria().add(graf.grafico(), BorderLayout.CENTER);
        view.getJPanelEnvoltoria().validate();
        CardLayout cl = (CardLayout) view.getJPChart().getLayout();
        cl.show(view.getJPChart(), "env");
        NeutralLine LN = new NeutralLine(this.sec, this.esf, this.mat);
        float deltaw = (float) 0.2;
        float ac = this.sec.getArea();
        float fyd = this.mat.getAco().getFyd() / 10;
        float sigma = this.mat.getConcrete().getSigmacd() / 10;
        float w1 = Float.parseFloat(view.getTxtVarTaxa().getText());
        float w2 = Float.parseFloat(view.getTxtVarTaxa2().getText());
        float V = Float.parseFloat(view.getTxtVarV().getText());
        float N;
        N = V * ac * sigma;
        System.out.println("N : " + N);
        float hx = this.sec.getHx();
        float hy = this.sec.getH();
        for (float k = w1; k <= w2; k = k+deltaw) {
            float As = ((k * ac * sigma) / fyd)*100;
            List< Esforcos> es;
            List<Float> Nr = new ArrayList<>();
            List<Float> Mx = new ArrayList<>();
            List<Float> My = new ArrayList<>();
            es = LN.envoltoria(0, 360, As, N);
            for (int i = 0; i < es.size(); i++) {
                float mx = es.get(i).getMxk();
                float my = es.get(i).getMyk();
                mx = (((mx * 100)) / (ac * hx * sigma));
                my = (((my * 100)) / (ac * hy * sigma));
                float n = es.get(i).getNk();
                Mx.add(mx);
                My.add(my);

            }
            graf.setEspacamento(0.1);
            graf.setSeries(Mx, My, k);
            System.out.println("");
            System.out.println("Next w: " + k);
        }
    }

    /**
     * @return the taxaArm
     */
    public float getTaxaArm() {
        return taxaArm;
    }

    /**
     * @return the NormalReduzida
     */
    public float getNormalReduzida() {
        return NormalReduzida;
    }
}
