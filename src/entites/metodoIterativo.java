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
public class metodoIterativo {
    secaoTransversal secRecebida;
    Esforcos esforcosRecebidos;
    secaoTransversal secaoTransladada;
    Barras barrasTransladadas;
    secaoTransversal secUsar;

    public metodoIterativo(secaoTransversal sec, Esforcos esforcos) {
        this.secRecebida = sec;
        this.esforcosRecebidos = esforcos;
        this.barrasTransladadas = new Barras();
        this.secaoTransladada = new secaoTransversal();
        translade();
       
    }
    private void translade(){
        for(int i = 0 ;  i < secRecebida.getVertices().size(); i++){
            Vertice Vaux;
            Vaux = new Vertice(secRecebida.getVertices().get(i).getX() - secRecebida.getCentroide().getX(),secRecebida.getVertices().get(i).getY()- secRecebida.getCentroide().getY());
            secaoTransladada.addVertice(Vaux);
            //verificação
            System.out.println("vertice transladado "+(i+1)+": "+ secaoTransladada.getVertices().get(i).getX()+", "+secaoTransladada.getVertices().get(i).getY());
            
        }
        for(int j =0; j< secRecebida.getBars().getBarras().size(); j++){
            barra Baux;
            Baux = new barra(secRecebida.getBars().getBarras().get(j).getDiametro(),secRecebida.getBars().getBarras().get(j).getX()-secRecebida.getCentroide().getX(),secRecebida.getBars().getBarras().get(j).getY()-secRecebida.getCentroide().getY());
            this.barrasTransladadas.addBarra(Baux);
            // verificação
            System.out.println("barra transladda "+ (j+1)+": "+ this.barrasTransladadas.getBarras().get(j).getX()+"; "+this.barrasTransladadas.getBarras().get(j).getY());
        }
        //verificação
        this.secUsar = new secaoTransversal(this.secaoTransladada, this.barrasTransladadas);
        System.out.println("Centroide: "+ secUsar.getCentroide().getX()+";"+secUsar.getCentroide().getY() );
    }

}
