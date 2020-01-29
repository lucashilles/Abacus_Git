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
public class barra {

    private float diametro = 0f;
    private float x, y;
    private float area = 0f;

    public barra(float diametro, float x, float y) {
        this.diametro = diametro;
        areaBar();
        this.x = x;
        this.y = y;

    }
    private void areaBar(){
        float aux;
        aux = (float) (Math.pow((diametro / 2),2)*Math.PI);
        this.area = aux;
        System.out.println("AREA: "+ this.getArea());
    }

    /**
     * @return the diametro
     */
    public float getDiametro() {
        return diametro;
    }

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @return the area
     */
    public float getArea() {
        return area;
    }

    /**
     * @param diametro the diametro to set
     */
    public void setDiametro(float diametro) {
        this.diametro = diametro;
    }

    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }

}
