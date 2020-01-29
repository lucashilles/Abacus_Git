/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entites.Barras;
import entites.Vertice;
import entites.barra;
import entites.secaoTransversal;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import views.areaDesenho;

import views.secaoDraw;

/**
 *
 * @author Administrador
 */
public class secaoDrawController {

    private Barras bars = new Barras();
    private areaDesenho draw;
    private secaoTransversal sec = new secaoTransversal();
    private secaoDraw secView;
    private JFrame frame, parent;

    public secaoDrawController(JFrame parent) {
        this.parent = parent;
        secView = new secaoDraw();
        draw = new areaDesenho();

        init();

    }

    private void init() {
        secView.getBtnEditBar().addActionListener(e -> editBar(e));
        secView.getBtnRemoveBar().addActionListener(e -> removeBar(e));
        secView.getBtnAddBar().addActionListener(e -> addBars(e));
        secView.getBtnRemoveVertice().addActionListener(e -> removeVertice(e));
        secView.getBtnZoomOut().addActionListener(e -> ZoomOut(e));
        secView.getBtnZoomMais().addActionListener(e -> ZoomIN(e));
        secView.getBtnAddV().addActionListener(e -> add(e));
        secView.getTxtArea().setEnabled(false);
        secView.getTxtCentroide().setEnabled(false);
        frame = new JFrame("Desenho da seção transversal");
        secView.getJPanelAreaDraw().setLayout(new BorderLayout());
        secView.getJPanelAreaDraw().add(draw, BorderLayout.CENTER);
        secView.getScrollPaneDraw().getViewport().setViewPosition(new Point((int) draw.getPreferredSize().getWidth() * (-1), (int) draw.getPreferredSize().getHeight()));
        frame.add(secView);
        frame.pack();
        frame.setIconImage(parent.getIconImage());
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

        frame.setVisible(true);

    }

    // metodo que chama um dialogo para se adicionar um vértice
    private void add(ActionEvent e) {
        verticesCoordenatesController vcc = new verticesCoordenatesController(frame);
        Vertice v = vcc.getVerticeLancado();

        if (v != null) {
            sec.addVertice(v);
            if (sec.getCentroide() != null) {
                draw.setCentroide(sec.getCentroide());
                secView.getTxtCentroide().setText("[" + String.format("%.2f", sec.getCentroide().getX()) + ";" + String.format("%.2f", sec.getCentroide().getY()) + "]");
            }
            if (sec.getArea() >= 0) {

                secView.getTxtArea().setText(String.format("%.2f", sec.getArea()));
            }
            draw.updateVerticeList(sec.getVertices());
            if (draw.getCentro() != null) {
                secView.getScrollPaneDraw().getViewport().setViewPosition(new Point((int) (draw.getSize().getWidth() / 4), (int) (draw.getSize().getHeight() / 4)));
            }
            DefaultListModel model1 = (DefaultListModel) secView.getJLVertices().getModel();
            model1.addElement("V[x;y]: [" + v.getX() + ";" + v.getY() + "]");
            CardLayout cl = (CardLayout) secView.getJPLists().getLayout();
            cl.show(secView.getJPLists(), "listas");
        }
    }

    private void ZoomIN(ActionEvent e) {
        if (draw.getZoom() < 3.5) {
            draw.Zoom(1);
            secView.getScrollPaneDraw().getViewport().setViewSize(draw.getSize());
            if (draw.getZoom() < 3.0) {
                secView.getScrollPaneDraw().getViewport().setViewPosition(new Point((int) (draw.getSize().getWidth() / 4), (int) (draw.getSize().getHeight() / 4)));
            } else {
                secView.getScrollPaneDraw().getViewport().setViewPosition(new Point((int) (draw.getSize().getWidth() / 3), (int) (draw.getSize().getHeight() / 3)));
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Zoom máximo: " + (String.format("%.2f", (draw.getZoom() - 2) * 100)) + "%", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void ZoomOut(ActionEvent e) {
        if (draw.getZoom() > 1.1) {
            draw.Zoom(-1);

            secView.getScrollPaneDraw().getViewport().setViewSize(draw.getSize());
            secView.getScrollPaneDraw().getViewport().setViewPosition(new Point((int) draw.getSize().getWidth() / 4, (int) draw.getSize().getHeight() / 4));

        } else {
            JOptionPane.showMessageDialog(frame, "Zoom minimo", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void removeVertice(ActionEvent e) {
        if (secView.getJLVertices().isSelectionEmpty() == false) {
            int indiceX;
            indiceX = secView.getJLVertices().getSelectedIndex();
            sec.removeVertice(indiceX);
            draw.updateVerticeList(sec.getVertices());
            DefaultListModel md = (DefaultListModel) secView.getJLVertices().getModel();
            md.remove(indiceX);
            if (md.isEmpty() == true) {
                CardLayout cl = (CardLayout) secView.getJPLists().getLayout();
                cl.show(secView.getJPLists(), "entrada");
            }
            if (sec.getArea() != 0) {
                if (sec.getVertices().size() > 2) {
                    secView.getTxtArea().setText(String.format("%.2f", sec.getArea()));
                } else {
                    secView.getTxtArea().setText("0.0");
                }

            }
            if (sec.getCentroide() != null) {
                if (sec.getVertices().size() > 2) {
                    draw.setCentroide(sec.getCentroide());
                    secView.getTxtCentroide().setText("[" + String.format("%.2f", sec.getCentroide().getX()) + ";" + String.format("%.2f", sec.getCentroide().getY()) + "]");
                } else {
                    secView.getTxtCentroide().setText("[0;0]");

                }
            }

        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um vértice", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }
// parei aqui 28/01 P.S: adiconar no desenho as barras lançadas

    private void addBars(ActionEvent e) {
        if (sec.getVertices().isEmpty() == true) {
            JOptionPane.showMessageDialog(frame, "Antes de lançar uma barra, lance primeiramente \n os vértices da seção!", "Importante", JOptionPane.INFORMATION_MESSAGE);

        } else {
            barADDController bac = new barADDController(frame);
            barra b = bac.getBarra();
            if (b != null) {
                bars.addBarra(b);
                draw.updateBarsList(bars.getBarras());
                DefaultListModel md = (DefaultListModel) secView.getJLBars().getModel();
                md.addElement("B[x;y]: [" + b.getX() + ";" + b.getY() + "]  Ø: " + b.getDiametro());
                CardLayout cl = (CardLayout) secView.getJPLists().getLayout();
                cl.show(secView.getJPLists(), "listas");
            }
        }
    }

    private void removeBar(ActionEvent e) {
        if (secView.getJLBars().isSelectionEmpty() == false) {
            int indice;
            indice = secView.getJLBars().getSelectedIndex();
            bars.removeBarra(indice);
            draw.updateBarsList(bars.getBarras());
            DefaultListModel md = (DefaultListModel) secView.getJLBars().getModel();
            md.remove(indice);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione uma barra da lista", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void editBar(ActionEvent e) {
        if (secView.getJLBars().isSelectionEmpty() == false) {
            editBarController edc = new editBarController(frame, bars.getBarras().get(secView.getJLBars().getSelectedIndex()));
            bars.editBar(secView.getJLBars().getSelectedIndex(), edc.getBarraE().getDiametro(), edc.getBarraE().getX(), edc.getBarraE().getY());
            draw.updateBarsList(bars.getBarras());
            DefaultListModel md = (DefaultListModel) secView.getJLBars().getModel();
            md.setElementAt("B[x;y]: [" + bars.getBarras().get(secView.getJLBars().getSelectedIndex()).getX() + ";" + bars.getBarras().get(secView.getJLBars().getSelectedIndex()).getY() + "]  Ø: " + bars.getBarras().get(secView.getJLBars().getSelectedIndex()).getDiametro(), secView.getJLBars().getSelectedIndex());
            System.out.println("tamanho list: " + bars.getBarras().size());
        }else{
            JOptionPane.showMessageDialog(frame, "Selecione uma barra da lista", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}
