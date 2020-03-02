/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Administrador
 */
public class Coeficientes {
    private double  gamaC=0;
    private double gamaS=0;
    private double gamaEsforcos=0;
    private Materials materiais= null;
    public Coeficientes(Materials materiais,double gamaC, double gamaS,double gamaEsforcos){
        this.materiais = materiais;
        this.gamaC = gamaC;
        this.gamaEsforcos = gamaEsforcos;
        this.gamaS = gamaS;
        
    }

    /**
     * @return the gamaC
     */
    public double getGamaC() {
        return gamaC;
    }

    /**
     * @return the gamaS
     */
    public double getGamaS() {
        return gamaS;
    }

    /**
     * @return the gamaEsforcos
     */
    public double getGamaEsforcos() {
        return gamaEsforcos;
    }

    /**
     * @return the materiais
     */
    public Materials getMateriais() {
        return materiais;
    }
    
}
