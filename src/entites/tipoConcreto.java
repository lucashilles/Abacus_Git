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
public enum tipoConcreto {

    C20(20),
    C25(25),
    C30(30),
    C35(35),
    C40(40),
    C50(50);
    private float tipoConcreto;

    tipoConcreto(float tipoConcreto) {
        this.tipoConcreto = tipoConcreto;
    }

    public float getTipoConcreto() {
        return this.tipoConcreto;
    }

}
