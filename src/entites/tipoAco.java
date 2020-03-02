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
public enum tipoAco {

    NULL(0) {
                @Override
                public String toString() {
                    return "-------";
                }
            },
    CA_60(600) {
                @Override
                public String toString() {
                    return "CA-60";
                }
            },// MPa
    CA_50(500) {
                @Override
                public String toString() {
                    return "CA-50";
                }
            }; // MPa
    private final float tipoAco;

    /**
     * @return the tipoAco
     */
    public float getTipoAco() {
        return tipoAco;
    }

    tipoAco(float tipoAco) {
        this.tipoAco = tipoAco;
    }

}
