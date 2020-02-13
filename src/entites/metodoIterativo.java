/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class metodoIterativo {
    float h, d;
    dominiosDeformacao dominios;
    secaoTransversal secRecebida;
    Materials matRecebido;
    Esforcos esforcosRecebido;
    Coeficientes coefRecebido;
    secaoTransversal secTransladada = null;
    secaoTransversal secRotate = null;

    public metodoIterativo(secaoTransversal sec, Materials material, Esforcos esforcos, Coeficientes coef) {
        this.secRecebida = sec;
        this.matRecebido = material;
        this.esforcosRecebido = esforcos;
        this.coefRecebido = coef;
        translate(this.secRecebida);
        rotate(this.secTransladada, getAlfaINICIAL(esforcos));
        metodo();
        verifyDomain(3);
    }

    private dominiosDeformacao verifyDomain(float x0) {
        float ds = (this.matRecebido.getConcrete().getDeformacaoEu()/(this.matRecebido.getConcrete().getDeformacaoEu()+10))*this.d;
        if(x0>= 0 && x0 <= ds){
            
            this.dominios = dominiosDeformacao.DOMINIO_2;
            
        }else{
            if(x0 >= ds && x0 <= this.h){
                this.dominios = dominiosDeformacao.DOMINIO_3_4_4A;
            }else{
                this.dominios = dominiosDeformacao.DOMINIO_5;
            }
        }
        System.out.println("dominios: "+ this.dominios.toString());
        
        return dominios;
    }

    private void metodo() {
        float h, d, gamaC, ymax, ymin, ysi;
        ymax = 0;
        ymin = 0;

        for (Vertice v : this.secRotate.getVertices()) {
            if (ymax < v.getY()) {
                ymax = v.getY();
            }
            if (ymin > v.getY()) {
                ymin = v.getY();
            }
        }
        h = ymax - ymin;
        this.h = h;
        System.out.println("altura relativa a este sistema: " + h);
        for (int j = 0; j < this.secRotate.getBars().getBarras().size(); j++) {
            float ys = this.secRotate.getBars().getBarras().get(j).getY();
            this.secRotate.getBars().getBarras().get(j).setDi(ymax - ys);
        }
        float da = 0;
        for (barra b : this.secRotate.getBars().getBarras()) {

            if (da < b.getDi()) {
                da = b.getDi();
            }

        }
        d = da;
        this.d =d;
        //testando
        System.out.println("altura util: " + d);
    }

    private void translate(secaoTransversal sec) {
        secaoTransversal section = new secaoTransversal();
        Barras bars = new Barras();
        for (int i = 0; i < sec.getVertices().size(); i++) {
            Vertice v = new Vertice(sec.getVertices().get(i).getX() - sec.getCentroide().getX(), sec.getVertices().get(i).getY() - sec.getCentroide().getY());
            section.addVertice(v);

        }
        for (int j = 0; j < sec.getBars().getBarras().size(); j++) {
            barra bT = new barra(sec.getBars().getBarras().get(j).getDiametro(), sec.getBars().getBarras().get(j).getX() - sec.getCentroide().getX(),
                    sec.getBars().getBarras().get(j).getY() - sec.getCentroide().getY());
            bars.addBarra(bT);
        }
        secTransladada = new secaoTransversal(section, bars);
        System.out.println("Area transladaded: " + secTransladada.getArea());
        System.out.println("Area barras total: " + secTransladada.getBars().getAreaBars());
    }

    private void rotate(secaoTransversal sec, float angulo) {
        secaoTransversal section = new secaoTransversal();
        Barras bars = new Barras();
        for (int i = 0; i < sec.getVertices().size(); i++) {
            Vertice v = new Vertice(sec.getVertices().get(i).getX() * ((float) Math.cos(angulo * (Math.PI / 180))) + sec.getVertices().get(i).getY() * ((float) Math.sin(angulo * (Math.PI / 180))),
                    (-1) * sec.getVertices().get(i).getX() * (float) Math.sin(angulo * (Math.PI / 180)) + sec.getVertices().get(i).getY() * (float) Math.cos(angulo * (Math.PI / 180)));

            section.addVertice(v);
        }
        for (int j = 0; j < sec.getBars().getBarras().size(); j++) {
            barra bar = new barra(sec.getBars().getBarras().get(j).getDiametro(), sec.getBars().getBarras().get(j).getX() * ((float) Math.cos(angulo * (Math.PI / 180)))
                    + sec.getBars().getBarras().get(j).getY() * ((float) Math.sin(angulo * (Math.PI / 180))),
                    (-1) * sec.getBars().getBarras().get(j).getX() * ((float) Math.sin(angulo * (Math.PI / 180))) + sec.getBars().getBarras().get(j).getY() * ((float) Math.cos(angulo * (Math.PI / 180))));
            bars.addBarra(bar);
            //verificação

        }
        this.secRotate = new secaoTransversal(section, bars);
        for (Vertice v : secRotate.getVertices()) {
            System.out.println("VR: " + v.getX() + "; " + v.getY());
        }
        for (barra b : secRotate.getBars().getBarras()) {
            System.out.println("BR: " + b.getX() + "; " + b.getY() + " dia: " + b.getDiametro());
        }
    }

    private float getAlfaINICIAL(Esforcos esf) {
        float alfa = (float) (esf.getTetaD() - 90);
        return alfa;
    }
}
