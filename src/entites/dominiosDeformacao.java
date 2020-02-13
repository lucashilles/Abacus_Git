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
public enum dominiosDeformacao {

    DOMINIO_2(0) {
                @Override
                public String toString() {
                    return "Domínio 2";
                }
            },
    DOMINIO_3_4_4A(1){
            @Override
            public String toString(){
                return "Domínio 3, 4 e 4a";
            }
    },
    DOMINIO_5(2){
                @Override
                public String toString(){
                return "Domínio 5";
            }
    };
    private final int dominiosDeformacao;

    /**
     * @return the dominiosDeformacao
     */
    public int getDominiosDeformacao() {
        return dominiosDeformacao;
    }
    dominiosDeformacao(int value){
        this.dominiosDeformacao = value;
    }


    
}
