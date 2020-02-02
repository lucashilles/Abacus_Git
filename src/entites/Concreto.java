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
public class Concreto {

    private float fck; // MPa
    private float ModuloElasticidade; // Mpa

    public Concreto(float fck) {
        this.fck = fck;
        gerarEcs();
    }

    private void gerarEcs() {
        float alfai;
        float Eci;
        alfai = (float) (0.8 + 0.2 * (fck / 80));
        if (alfai <= 1) {
            if (fck <= 50) {
                Eci = (float) (5600 * Math.sqrt(fck));// considerando alfaE = 1 - para granito e gnaisse

            } else {
                Eci = (float) ((float) 21500*(Math.pow((fck/10)+1.25, 0.333333333)));
            }
            this.setModuloElasticidade(alfai * Eci);
        } else {
            if (fck <= 50) {
                Eci = (float) (5600 * Math.sqrt(fck));// considerando alfaE = 1 - para granito e gnaisse
            } else {
                Eci = (float) ((float) 21500*(Math.pow((fck/10)+1.25, 0.333333333)));
            }
            this.ModuloElasticidade = Eci*1;
        }

    }

    /**
     * @return the fck
     */
    public float getFck() {
        return fck;
    }

    /**
     * @return the ModuloElasticidade
     */
    public float getModuloElasticidade() {
        return ModuloElasticidade;
    }

    /**
     * @param ModuloElasticidade the ModuloElasticidade to set
     */
    public void setModuloElasticidade(float ModuloElasticidade) {
        this.ModuloElasticidade = ModuloElasticidade;
    }

}
