/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entites.Vertice;
import entites.barra;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.objects.Global;

/**
 *
 * @author Administrador
 */
public class areaDesenho extends javax.swing.JPanel {

    Vertice centro = null;
    Vertice centroide = null;
    List<Vertice> vertices = new ArrayList<>();
    List<barra> bars = new ArrayList<>();
    private double zoom = 2;

    public areaDesenho() {

        setBackground(Color.black);
    }
    @Override
    public void setPreferredSize(Dimension dimension){
    this.setSize(new Dimension((int)(getZoom()*this.getSize().getWidth()),(int)(getZoom()*this.getSize().getHeight())));
    revalidate();
    repaint();
    
    }
    public void Zoom(double s) {
        this.zoom += s*0.1 ;
        System.out.println("z: "+ getZoom());
   
        
        revalidate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return (new Dimension((int)getZoom()*600,  (int)getZoom()*400));
    }

    private void drawLines(Graphics g) {
        for (int i = 1; i < vertices.size(); i++) {
            Vertice v1 = vertices.get(i - 1);
            Vertice v2 = vertices.get(i);
            g.drawLine(Math.round((int)getZoom()*v1.getX()), (int)getZoom()*Math.round(v1.getY()) * (-1),(int)getZoom()* Math.round(v2.getX()), (int)getZoom()*Math.round(v2.getY()) * (-1));
        }

        Vertice fim = vertices.get(vertices.size() - 1);
        Vertice inicio = vertices.get(0);

        g.drawLine((int) Math.round((int)getZoom()*fim.getX()),  (int)getZoom()*Math.round(fim.getY() * (-1)),(int)getZoom()*Math.round(inicio.getX()),(int)getZoom()* Math.round(inicio.getY()) * (-1));
    }

    private void drawPoint(Graphics g, float x, float y) {
        g.fillOval((int) getZoom()*Math.round(x)-1, (1+((int) getZoom()* Math.round(y)))*(-1), 2, 2);

    }

    private void updateCentro() {
        float xMin = (float) Global.Infinity;
        float xMax = (float) -Global.Infinity;
        float yMin = (float) Global.Infinity;
        float yMax = (float) -Global.Infinity;

        for (Vertice v : vertices) {
            if (v.getX() < xMin) {
                xMin = v.getX();
            }
            if (v.getX() > xMax) {
                xMax = v.getX();
            }
            if (v.getY() < yMin) {
                yMin = v.getY();
            }
            if (v.getY() > yMax) {
                yMax = v.getY();
            }
        }

        centro = new Vertice(xMin + (xMax - xMin) / 2, yMin + (yMax - yMin) / 2);
    }

    public void updateVerticeList(List<Vertice> v) {
        vertices = v;
        if (v.size() > 1) {
            updateCentro();
        }
        revalidate();
        repaint();
    }
    public void updateBarsList(List<barra> b){
        bars = b;
        repaint();
        revalidate();
        
    }

    public Vertice getCentro() {
        return centro;
    }

    public void setCentroide(Vertice c) {
        this.centroide = c;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
 
      
        Graphics2D g2 = (Graphics2D) g;
        
        if (centro != null) {
            g.translate(((int) centro.getX() - getWidth() / 2) * (-1), getHeight() - ((int) centro.getY() - getHeight() / 2) * (-1));
        } else {
            g.translate(20, getHeight() - 40);
        }
        g2.scale(getZoom(), getZoom());
        drawOrigem(g);
        
        g.setColor(Color.cyan);
        if (vertices.size() > 0) {
            drawLines(g);
            g2.setColor(Color.cyan);
            for (Vertice v : vertices) {
                drawPoint(g, v.getX(), v.getY());

            }
        }

        if (centroide != null) {
            if(vertices.size()> 2){
            g2.setColor(Color.green);
            drawPoint(g, centroide.getX(), centroide.getY());
        }
        }
        if(bars.size() > 0){
            g2.setColor(Color.gray);
            for( barra b : bars){
                drawPoint(g, b.getX(), b.getY());
            }
        }
        
        validate();

    }

    private void drawOrigem(Graphics g) {

        g.setColor(Color.white);
        g.drawRect(0 - 2, 0 - 2, 4, 4);
        g.setColor(Color.gray);
        g.drawLine(0, 0, 0, -5);
        g.drawLine(0, 0, 5, 0);

        g.setFont(new Font("Arial", 1, 6));
        g.drawString("(0;0)", 0, 10);

    }

    /**
     * @return the zoom
     */
    public double getZoom() {
        return zoom;
    }

}
