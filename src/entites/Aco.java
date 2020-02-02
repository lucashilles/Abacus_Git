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
    private float fyk;// MPa
    private float Ecs;// GPa
    private tipoAco typeAco;
    public Aco(tipoAco typeAco, float Ecs){
        this.typeAco = typeAco;
        this.fyk = typeAco.getTipoAco();
        this.Ecs = Ecs;
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
    
}
