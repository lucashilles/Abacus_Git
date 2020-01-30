/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class secaoTransversal {
    private int numVertice; //Variável desnecessária, este dado pode ser obtido pela lista de vertices
    private List<Vertice> vertices = new ArrayList<>();
    private float area = 0f;
    private Vertice centroide = null;
    private tipoConcreto typeConcrete;
    private int numBars;

    public secaoTransversal() {

    }

    public void addVertice(Vertice v) {
        if (v != null) {
            vertices.add(v);
            gerarArea();
            gerarCentroide();

        }
    }

    public void removeVertice(Integer index) {
        if (index != null) {
            vertices.remove(index.intValue());
        } else {
            System.out.println("Selecione primeiro um vértce a ser adicionado");
        }
        gerarArea();
        gerarCentroide();

    }

    private void gerarCentroide() {
        if (vertices.size() > 2) {
            float sx = 0;
            float sy = 0;
            for (int i = 0; i < vertices.size(); i++) {
                Vertice a, b;
                if (i < vertices.size() - 1) {
                    a = vertices.get(i);
                    b = vertices.get(i + 1);

                } else {
                    a = vertices.get(i);
                    b = vertices.get(0);
                }
                sx += ((a.getX() + b.getX()) * ((a.getX() * b.getY()) - (a.getY() * b.getX())));
                sy += ((a.getY() + b.getY()) * ((a.getX() * b.getY()) - (a.getY() * b.getX())));
            }
            if (this.area != 0) {
                this.centroide = new Vertice((sx) / (6 * area), (sy) / (6 * area));
            }
        }
    }

    private void gerarArea() {
        if (vertices.size() > 2) {
            float auxi = 0f;
            for (int i = 0; i < vertices.size(); i++) {
                Vertice v1, v2;
                if (i < vertices.size() - 1) {
                    v1 = vertices.get(i);
                    v2 = vertices.get(i + 1);
                } else {
                    v1 = vertices.get(i);
                    v2 = vertices.get(0);
                }
                auxi += ((v1.getX() * v2.getY()) - (v1.getY() * v2.getX()));
            }

            this.area = auxi / 2;

        }

    }

    public secaoTransversal(tipoConcreto typeConcrete, int numVertice, int numBars, List<Vertice> vertices) {
        this.numBars = numBars;
        this.numVertice = numVertice;
        this.vertices = vertices;

    }

    /**
     * @return the numVertice
     */
    public int getNumVertice() {
        return vertices.size();
    }

    /**
     * @param numVertice the numVertice to set
     */
//    Desnecessário, a lista de vértices já faz este controle.
//    public void setNumVertice(int numVertice) {
//        this.numVertice = numVertice;
//    }

    /**
     * @return the vertices
     */
    public List<Vertice> getVertices() {
        return vertices;
    }

    /**
     * @return the area
     */
    public float getArea() {
        return area;
    }

    /**
     * @return the centroide
     */
    public Vertice getCentroide() {
        return centroide;
    }

    /**
     * @return the typeConcrete
     */
    public tipoConcreto getTypeConcrete() {
        return typeConcrete;
    }

    /**
     * @param typeConcrete the typeConcrete to set
     */
    public void setTypeConcrete(tipoConcreto typeConcrete) {
        this.typeConcrete = typeConcrete;
    }

    /**
     * @return the numBars
     */
    public int getNumBars() {
        return numBars;
    }

    /**
     * @param numBars the numBars to set
     */
    public void setNumBars(int numBars) {
        this.numBars = numBars;
    }

}
