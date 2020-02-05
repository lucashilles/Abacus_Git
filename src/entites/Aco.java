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
public class Aco{
    private float fyk = 0f;// MPa
    private float Ecs = 0f;// GPa
    private tipoAco typeAco = null;
    private float DefEuAco = 0f;
    private float fyd = 0f ;// MPa
    public Aco(tipoAco typeAco, float Ecs){
        this.typeAco = typeAco;
        this.fyk = typeAco.getTipoAco();
        this.Ecs = Ecs;
    }
    public void setDefAco(float defEu){
    this.DefEuAco = defEu;
    }

    /**
     * @return the fyk
     */
    public float getFyk() {
        return fyk;
    }

    /**
     * @return the Ecs
     */
    public float getEcs() {
        return Ecs;
    }

    /**
     * @return the typeAco
     */
    public tipoAco getTypeAco() {
        return typeAco;
    }

    /**
     * @return the DefEuAco
     */
    public float getDefEuAco() {
        return DefEuAco;
    }

    /**
     * @return the fyd
     */
    public float getFyd() {
        return fyd;
    }

  
    public void setFyd(float gamaS) {
        this.fyd = this.fyk/gamaS;
    }
    
}
