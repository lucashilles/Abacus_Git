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
public class Materials {

    private Concreto concrete;
    private Aco aco;

    public Materials(Concreto conc, Aco aco) {
        this.concrete = conc;
        this.aco = aco;

    }

    /**
     * @return the concrete
     */
    public Concreto getConcrete() {
        return concrete;
    }

    /**
     * @return the aco
     */
    public Aco getAco() {
        return aco;
    }

}
