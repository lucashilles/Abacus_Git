/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author Administrador
 */
public class barADD extends javax.swing.JPanel {

    /**
     * Creates new form barADD
     */
    public barADD() {
        initComponents();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(240, 150);
    }

    public JButton getBtnLancarB() {
        return btnLancarB;
    }

    public JComboBox getJComboDiametro() {
        return JComboDiametro;
    }

    public JTextField getTxtCoordX() {
        return txtCoordX;
    }

    public JTextField getTxtCoordY() {
        return txtCoordY;
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

        jLabel4 = new javax.swing.JLabel();
        JComboDiametro = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtCoordX = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCoordY = new javax.swing.JTextField();
        btnLancarB = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 51, 51));
        setPreferredSize(new java.awt.Dimension(240, 150));
        setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Diâmetro (mm): ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        add(jLabel4, gridBagConstraints);

        JComboDiametro.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        JComboDiametro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "5.0", "6.3", "8.0", "10.0", "12.5", "16.0", "20.0", "22.5", "25.0" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        add(JComboDiametro, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Coordenada X (cm): ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        add(jLabel1, gridBagConstraints);

        txtCoordX.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtCoordX.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCoordX.setNextFocusableComponent(txtCoordY);
        txtCoordX.setPreferredSize(new java.awt.Dimension(60, 20));
        txtCoordX.setSelectionColor(new java.awt.Color(51, 51, 51));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        add(txtCoordX, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Coordenada Y (cm): ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        add(jLabel2, gridBagConstraints);

        txtCoordY.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtCoordY.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCoordY.setNextFocusableComponent(btnLancarB);
        txtCoordY.setPreferredSize(new java.awt.Dimension(60, 20));
        txtCoordY.setSelectionColor(new java.awt.Color(51, 51, 51));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        add(txtCoordY, gridBagConstraints);

        btnLancarB.setBackground(new java.awt.Color(255, 255, 255));
        btnLancarB.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnLancarB.setText("Lançar barra");
        btnLancarB.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 51, 51), new java.awt.Color(51, 51, 0), null, null));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        add(btnLancarB, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JComboDiametro;
    private javax.swing.JButton btnLancarB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtCoordX;
    private javax.swing.JTextField txtCoordY;
    // End of variables declaration//GEN-END:variables
}
