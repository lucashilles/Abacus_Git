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
public class Esforcos {
    private float Mxk =0f;
    private float Myk =0f;
    private float Nk = 0f;
    private float ex;
    private float ey;
    private float Md =0f;
    private double tetaD = 0; // graus
    
    
public Esforcos(float mx, float my, float nk){
    this.Mxk = mx;
    this.Myk = my;
    this.Nk = nk;
    gerarExcentricidades();
    gerarMD();
    gerarTeta();

}
//em graus
private void gerarTeta(){
    if(this.Md != 0f){
        double teta;
        teta = (float)(Math.acos(((this.Mxk/this.Md)))*(180/Math.PI));
        this.tetaD = teta;
        //teste
        System.out.println("TetaD: "+ this.tetaD);
    }
}
// kN.m
private void gerarMD(){
    float md;
    md = (float) Math.sqrt((double)Math.pow((double)Mxk, 2)+(double)Math.pow((double)Myk, 2));
    this.Md = md;
    // verificação
    System.out.println("MD: "+ this.Md);
}
// in meters
private void gerarExcentricidades(){
    this.ex = (Mxk/Nk);
    this.ey = (Myk/Nk);
}

    /**
     * @return the Mxk
     */
    public float getMxk() {
        return Mxk;
    }

    /**
     * @return the Myk
     */
    public float getMyk() {
        return Myk;
    }

    /**
     * @return the Nk
     */
    public float getNk() {
        return Nk;
    }

    /**
     * @return the ex
     */
    public float getEx() {
        return ex;
    }

    /**
     * @return the ey
     */
    public float getEy() {
        return ey;
    }

    /**
     * @return the Md
     */
    public float getMd() {
        return Md;
    }

    /**
     * @return the tetaD
     */
    public double getTetaD() {
        return tetaD;
    }
    
}
