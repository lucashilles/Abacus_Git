/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Barras {

    private List<barra> barras = new ArrayList<>();
    private float areaBars = 0f;

    public Barras() {

    }

    public void addBarra(barra b) {
        if (b != null) {
            barras.add(b);
        }
        gerarAreaTotal();
    }

    public void removeBarra(Integer index) {
        if (index != null) {
            barras.remove(index.intValue());
        }
        gerarAreaTotal();
    }

    private void gerarAreaTotal() {
        if (barras.size() > 0) {
            float aux = 0f;
            for (int i = 0; i < barras.size(); i++) {
                aux += barras.get(i).getArea();
            }
            this.areaBars = aux;
            System.out.println("area é : " + areaBars);
        }
    }

    public void editBar(int indice, float diametro, float x, float y) {
        if (barras.size() > 0) {
            barras.get(indice).setX(x);
            barras.get(indice).setY(y);
            barras.get(indice).setDiametro(diametro);
        }
        gerarAreaTotal();
    }

    /**
     * @return the barras
     */
    public List<barra> getBarras() {
        return barras;
    }

    /**
     * @return the areaBars
     */
    public float getAreaBars() {
        return areaBars;
    }

}
