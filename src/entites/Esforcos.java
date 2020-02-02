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
    private float Mxk;
    private float Myk;
    private float Nk;
    private float ex;
    private float ey;
    
    
public Esforcos(float mx, float my, float nk){
    this.Mxk = mx;
    this.Myk = my;
    this.Nk = nk;
    gerarExcentricidades();

}
// em meters
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
    
}
