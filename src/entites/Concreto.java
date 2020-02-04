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

    private float fck = 0f; // MPa
    private float ModuloElasticidade; // Mpa
    private float alfac;
    private float lambda;
    private float fcd = 0f; // MPa
    private float sigmacd;
    private float deformacaoE0; // porMil
    private float deformacaoEu; //por mil

    public Concreto(float fck) {
        this.fck = fck;
        gerarParametros(fck);
        gerarEcs();
    }
    

    public void setSigmaCD() {
        if (this.fcd != 0) {
            this.sigmacd = (float) 0.95 * this.alfac * this.fcd;
        }
    }

    public void setFcd(float gamac) {
        if (gamac >= 1) {
            this.fcd = fck / gamac;
        }
    }

    private void gerarParametros(float fck) {
        if (fck <= 50) {
            this.alfac = (float) 0.85;
            this.lambda = (float) 0.8;
            this.deformacaoE0 = (float) 2.0;
            this.deformacaoEu = (float) 3.5;
        } else {
            this.alfac = (float) (0.85 * (1 - ((fck - 50) / 200)));
            this.lambda = (float) (0.8 - ((fck - 50) / 400));
            this.deformacaoE0 = (float) (2+0.085*(Math.pow((fck - 50),0.53)));
            this.deformacaoEu = (float)(2.6+35*(Math.pow(((90-fck)/100), 4)));
        }
    }

    private void gerarEcs() {
        float alfai;
        float Eci;
        alfai = (float) (0.8 + 0.2 * (fck / 80));
        if (alfai <= 1) {
            if (fck <= 50) {
                Eci = (float) (5600 * Math.sqrt(fck));// considerando alfaE = 1 - para granito e gnaisse

            } else {
                Eci = (float) ((float) 21500 * (Math.pow((fck / 10) + 1.25, 0.333333333)));
            }
            this.setModuloElasticidade(alfai * Eci);
        } else {
            if (fck <= 50) {
                Eci = (float) (5600 * Math.sqrt(fck));// considerando alfaE = 1 - para granito e gnaisse
            } else {
                Eci = (float) ((float) 21500 * (Math.pow((fck / 10) + 1.25, 0.333333333)));
            }
            this.ModuloElasticidade = Eci * 1;
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

    /**
     * @return the alfac
     */
    public float getAlfac() {
        return alfac;
    }

    /**
     * @return the lambda
     */
    public float getLambda() {
        return lambda;
    }

    /**
     * @param lambda the lambda to set
     */
    public void setLambda(float lambda) {
        this.lambda = lambda;
    }

    /**
     * @return the fcd
     */
    public float getFcd() {
        return fcd;
    }

    /**
     * @return the sigmacd
     */
    public float getSigmacd() {
        return sigmacd;
    }

    /**
     * @return the deformacaoE0
     */
    public float getDeformacaoE0() {
        return deformacaoE0;
    }

    /**
     * @param deformacaoE0 the deformacaoE0 to set
     */
    public void setDeformacaoE0(float deformacaoE0) {
        this.deformacaoE0 = deformacaoE0;
    }

    /**
     * @return the deformacaoEu
     */
    public float getDeformacaoEu() {
        return deformacaoEu;
    }

}
