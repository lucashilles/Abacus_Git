/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Dimension;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Administrador
 */
public class secaoDraw extends javax.swing.JPanel {

    /**
     * Creates new form secaoDraw
     */
    public secaoDraw() {
        initComponents();
        jLVertices.setModel(new DefaultListModel());
        DefaultListCellRenderer render1 = (DefaultListCellRenderer) jLVertices.getCellRenderer();
        render1.setHorizontalAlignment(JLabel.CENTER);
        jLBars.setModel(new DefaultListModel());
        DefaultListCellRenderer render2 = (DefaultListCellRenderer) jLBars.getCellRenderer();
        render1.setHorizontalAlignment(JLabel.CENTER);
    }

    public JButton getBtnAddV() {
        return btnAddV;
    }

    public JPanel getJPLists() {
        return jPLists;
    }

    public JTextField getTxtArea() {
        return txtArea;
    }

    public JPanel getJPanelAreaDraw() {
        return JPanelDrawArea;
    }
    public JScrollPane getScrollPaneDraw(){
    return jScrollPaneDraw;
    }
    public JButton getBtnZoomMais(){
    return btnZoomMais;
    }
    public JButton getBtnZoomOut(){
    return btnZoomOut;}

    public JTextField getTxtCentroide() {
        return txtCentroide;
    }
    public JButton getBtnRemoveVertice(){
    return btnRemoveVertice;}

    public JList getJLVertices() {
        return jLVertices;
    }

    public JList getJLBars() {
        return jLBars;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPLists = new javax.swing.JPanel();
        jPanelEntrada = new javax.swing.JPanel();
        jPanelListas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLVertices = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jLBars = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jPMenu = new javax.swing.JPanel();
        btnAddV = new javax.swing.JButton();
        btnRemoveVertice = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnZoomMais = new javax.swing.JButton();
        btnZoomOut = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCentroide = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPaneDraw = new javax.swing.JScrollPane();
        JPanelDrawArea = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(800, 500));
        setLayout(new java.awt.BorderLayout());

        jPLists.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPLists.setPreferredSize(new java.awt.Dimension(200, 440));
        jPLists.setLayout(new java.awt.CardLayout());

        jPanelEntrada.setBackground(new java.awt.Color(0, 51, 51));

        javax.swing.GroupLayout jPanelEntradaLayout = new javax.swing.GroupLayout(jPanelEntrada);
        jPanelEntrada.setLayout(jPanelEntradaLayout);
        jPanelEntradaLayout.setHorizontalGroup(
            jPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
        );
        jPanelEntradaLayout.setVerticalGroup(
            jPanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        jPLists.add(jPanelEntrada, "entrada");

        jPanelListas.setLayout(new java.awt.GridLayout(2, 1));

        jLVertices.setBorder(javax.swing.BorderFactory.createTitledBorder("Vértices"));
        jScrollPane3.setViewportView(jLVertices);

        jPanelListas.add(jScrollPane3);

        jLBars.setBorder(javax.swing.BorderFactory.createTitledBorder("Barras"));
        jScrollPane4.setViewportView(jLBars);

        jPanelListas.add(jScrollPane4);

        jPLists.add(jPanelListas, "listas");

        add(jPLists, java.awt.BorderLayout.LINE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPMenu.setBackground(new java.awt.Color(0, 51, 51));
        jPMenu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPMenu.setPreferredSize(new java.awt.Dimension(600, 70));
        jPMenu.setLayout(new java.awt.GridBagLayout());

        btnAddV.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnAddV.setText("Adicionar vértice");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPMenu.add(btnAddV, gridBagConstraints);

        btnRemoveVertice.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnRemoveVertice.setText("Remover vértice");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPMenu.add(btnRemoveVertice, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setText("Adicionar barra");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPMenu.add(jButton3, gridBagConstraints);

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton4.setText("Remover barra");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPMenu.add(jButton4, gridBagConstraints);

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton5.setText("Editar barra");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPMenu.add(jButton5, gridBagConstraints);

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton6.setText("Descartar barras");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPMenu.add(jButton6, gridBagConstraints);

        btnZoomMais.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnZoomMais.setText("Mais Zoom");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPMenu.add(btnZoomMais, gridBagConstraints);

        btnZoomOut.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnZoomOut.setText("Menos Zoom");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPMenu.add(btnZoomOut, gridBagConstraints);

        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton9.setText("Refazer tudo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPMenu.add(jButton9, gridBagConstraints);

        jButton10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton10.setText("Criar seção");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPMenu.add(jButton10, gridBagConstraints);

        jPanel2.add(jPMenu, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 30));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("AREA:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(jLabel1, gridBagConstraints);

        txtArea.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtArea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtArea.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(txtArea, gridBagConstraints);
        txtArea.getAccessibleContext().setAccessibleName("");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("CENTROIDE:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        txtCentroide.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtCentroide.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCentroide.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel1.add(txtCentroide, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("cm²");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("cm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jPanel2.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout JPanelDrawAreaLayout = new javax.swing.GroupLayout(JPanelDrawArea);
        JPanelDrawArea.setLayout(JPanelDrawAreaLayout);
        JPanelDrawAreaLayout.setHorizontalGroup(
            JPanelDrawAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        JPanelDrawAreaLayout.setVerticalGroup(
            JPanelDrawAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        jScrollPaneDraw.setViewportView(JPanelDrawArea);

        jPanel2.add(jScrollPaneDraw, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelDrawArea;
    private javax.swing.JButton btnAddV;
    private javax.swing.JButton btnRemoveVertice;
    private javax.swing.JButton btnZoomMais;
    private javax.swing.JButton btnZoomOut;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JList jLBars;
    private javax.swing.JList jLVertices;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPLists;
    private javax.swing.JPanel jPMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelEntrada;
    private javax.swing.JPanel jPanelListas;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPaneDraw;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtCentroide;
    // End of variables declaration//GEN-END:variables
}