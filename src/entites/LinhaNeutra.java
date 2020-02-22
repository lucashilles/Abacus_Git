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
public class LinhaNeutra {

    private float X0;
    float alfa;
    float Sx, Sy;
    private float fx;

    dominiosDeformacao dominios;
    secaoTransversal secaoRecebida = null;
    Materials materiais = null;
    Esforcos esforcosRecebidos = null;

    secaoTransversal secTransladada;
    secaoTransversal secRotate;
    float h, d, yMax, gamac;
    float acc = 0f;
    Esforcos momentosResistentes;
    secaoTransversal secACC;
    secaoTransversal secUnRotate;

    public LinhaNeutra(secaoTransversal secaoRecebida, Materials materiais, Esforcos esforcosRecebidos) {
        this.secaoRecebida = secaoRecebida;
        this.materiais = materiais;
        this.esforcosRecebidos = esforcosRecebidos;
    }

    // me retorna o valor  da equação 4.6.1 livro verde araujo
    public Esforcos momentos(float X0, float alfa) {
        dominiosDeformacao domi;
        secaoTransversal secTrans, secRotacionada, secUnrotacionada, secAConc;
        float altura, alturaU, sy, sx, Yc, ymax;
        Esforcos esforcosResistentes;
        secTrans = Translate(this.secaoRecebida);
        secRotacionada = Rotate(secTrans, alfa);
        altura = parametros(secRotacionada, X0).get(0);
        alturaU = parametros(secRotacionada, X0).get(1);
        Yc = parametros(secRotacionada, X0).get(3);
        ymax = parametros(secRotacionada, X0).get(2);
        domi = verifyDomain(X0, alturaU, altura);
        deformacaoBarra(domi, secRotacionada.getBars(), X0, alturaU, altura);
        secAConc = ACC(secRotacionada, ymax, X0, Yc);
        secUnrotacionada = unrotate(secAConc, alfa);
        sx = staticsMomentos(secUnrotacionada).getX();
        sy = staticsMomentos(secUnrotacionada).getY();
        esforcosResistentes = EquilibrioEquacoes(secUnrotacionada.getArea(), secRotacionada, secTrans, this.materiais, sx, sy);
        return esforcosResistentes;
    }

    private float setAlfaProx(float alfini, float tetaD, float tetaR) {
        float alfaProx = 0f;
        if (tetaR > tetaD) {
            alfaProx = (alfini + 90) * ((tetaD / tetaR)) - 90;
        } else if (tetaR < tetaD) {

            alfaProx = ((90 - tetaD) / (90 - tetaR)) * alfini;
        }

        return alfaProx;
    }

    public float inclinacaoLN(float x0, float alfa) {
        int cont = 0;
        Esforcos momentosResi;
        float alfaIn = alfa;
        momentosResi = momentos(x0, alfaIn);
        float tetaD = (float) this.esforcosRecebidos.getTetaD();
        float tetaR = (float) momentosResi.getTetaD();
        float alfaPr;
        while (Math.abs((tetaR - tetaD)) > (float) 0.001) {
            alfaPr = setAlfaProx(alfaIn, tetaD, tetaR);
            momentosResi = momentos(x0, alfaPr);
            tetaR = (float) momentosResi.getTetaD();
            alfaIn = alfaPr;
            System.out.println("");
            System.out.println("tentativa: " + cont);
            System.out.println("inclinaçao avaliada: " + alfaPr);
            System.out.println("");
            cont++;
        }
        System.out.println("");
        System.out.println("a inclinação correta é: " + alfaIn);
        System.out.println("Numero de interações: " + cont);
        System.out.println("");
        return alfaIn;
    }

    public float comecar(float X0, float alfa) {
        dominiosDeformacao domi;
        secaoTransversal secTrans, secRotacionada, secUnrotacionada, secAConc;
        float result, altura, alturaU, sy, sx, accL, Yc, ymax;
        Esforcos esforcosResistentes;
        secTrans = Translate(this.secaoRecebida);
        secRotacionada = Rotate(secTrans, alfa);
        altura = parametros(secRotacionada, X0).get(0);
        alturaU = parametros(secRotacionada, X0).get(1);
        Yc = parametros(secRotacionada, X0).get(3);
        ymax = parametros(secRotacionada, X0).get(2);
        domi = verifyDomain(X0, alturaU, altura);
        deformacaoBarra(domi, secRotacionada.getBars(), X0, alturaU, altura);
        secAConc = ACC(secRotacionada, ymax, X0, Yc);
        secUnrotacionada = unrotate(secAConc, alfa);
        sx = staticsMomentos(secUnrotacionada).getX();
        sy = staticsMomentos(secUnrotacionada).getY();
        esforcosResistentes = EquilibrioEquacoes(secUnrotacionada.getArea(), secRotacionada, secTrans, this.materiais, sx, sy);

        result = capacidadeResistente(secRotacionada, secUnrotacionada.getArea(), esforcosResistentes);
        return result;
    }

    // quero testar o valor retornado pela funcao comecar, cajo seja 0 o valor colocado como parametro X0 é o valor correto para
    // profundidade da LN. caso contrário deve gerar um novo intervalo e um novo chute, ate que satisfaça a condiçao
    // valor da funçao encontrada no metodo capacidadeResistente;
    public void bissecant(float a, float b, float angulo) {
        int contador = 0;
        float a1 = a;
        float b1 = b;
        float fa = comecar(a1, angulo);
        float fb = comecar(b1, angulo);
        if (fb * fa <= 0) {
            System.out.println("solucao esta entre " + a1 + " e " + b1);

        } else {
            while ((fb * fa > 0)) {
                a1 = b1;
                b1 = b1 * 10;

                if (fb * fa <= 0) {
                    break;

                }

            }
            System.out.println("solucao esta entre " + a1 + " e " + b1);
        }

        float c, fc;
        c = ((a1 * fb) - (b1 * fa)) / (fb - fa);
        System.out.println("X Ln analisado: " + c);
        fc = comecar(c, angulo);
        while (Math.abs(fc) > (float) 0.001) {
            float produto = fa * fc;
            if (produto > 0) {
                a1 = c;
                fa = fc;

            } else if (produto < 0) {
                b1 = c;
                fb = fc;
            }
            c = ((a1 * fb) - (b1 * fa)) / (fb - fa);
            fc = comecar(c, angulo);
            System.out.println("X Ln analisado: " + c);
            contador++;
        }
        this.X0 = c;
        System.out.println("a LN é : " + this.getX0());
        System.out.println("");
        System.out.println("Numero de interações: " + contador);
    }

    public List<Esforcos> paresMomentos(float LN, float alfa1, float alfaFim) {
        List<Esforcos> mom = new ArrayList<>();
        float acrescimo = (float) 1;
        while (alfa1 <= alfaFim) {
            Esforcos m;
            m = momentos(LN, alfa1);
            mom.add(m);
            alfa1 = alfa1 + acrescimo;
            System.out.println("");
            System.out.println("proximo angulo: " + alfa1);

        }
        for (int i = 0; i < mom.size(); i++) {
            System.out.println("");
            System.out.println("Mxr: " + mom.get(i).getMxk());
            System.out.println("Myr: " + mom.get(i).getMyk());

        }

        return mom;
    }

    // pego o valor do esforço solicitante de calculo "2º botao" e faço a subtração da Normal de calculo resistente, encontrada no metodo
    //EquilibrioEquaçoes 
    private float capacidadeResistente(secaoTransversal secTensao, float Acc, Esforcos momentosR) {

        float fxs;
        // esforço solicitante de calculo - esforco resistente de calculo
        fxs = (-this.esforcosRecebidos.getNk() - momentosR.getNk());

        System.out.println("Fx: " + fxs);
        return fxs;
    }

    private Esforcos EquilibrioEquacoes(float Acc, secaoTransversal secTensao, secaoTransversal secTransladada, Materials conc, float sX, float sY) {
        Esforcos Ers;
        float Ndr = 0f;
        float Mxdr = 0f;
        float Mydr = 0f;
        for (int i = 0; i < secTensao.getBars().getBarras().size(); i++) {
            // cm²
            float areabarra = (float) ((secTensao.getBars().getBarras().get(i).getArea()) / 100);
            //kN/cm²
            float tensaoBarra = secTensao.getBars().getBarras().get(i).getTensaoBarra();
            //centimeters
            float xsi = (secTransladada.getBars().getBarras().get(i).getX());
            //centimeters
            float ysi = (secTransladada.getBars().getBarras().get(i).getY());
            //kN
            Ndr += (Acc) * (conc.getConcrete().getSigmacd() / 10) + areabarra * tensaoBarra;
            //kN.cm
            Mxdr += (sX) * (conc.getConcrete().getSigmacd() / 10) + areabarra * tensaoBarra * xsi;
            //kN.cm
            Mydr += (sY) * (conc.getConcrete().getSigmacd() / 10) + areabarra * tensaoBarra * ysi;

        }
        //kN/m
        Mxdr = Mxdr / 100;
        Mydr = Mydr / 100;
        Ers = new Esforcos(Mxdr, Mydr, Ndr);

        System.out.println("Ndr: " + Ers.getNk() + ", Mxdr: " + Ers.getMxk() + ", Mydr: " + Ers.getMyk());

        return Ers;
    }

    private Vertice staticsMomentos(secaoTransversal s) {
        float sx = 0f, sy = 0f;
        Vertice momentosstaticos;

        for (int i = 0; i < s.getVertices().size(); i++) {
            Vertice a, b;
            float deltax, deltay;
            if (i < s.getVertices().size() - 1) {
                a = s.getVertices().get(i);
                b = s.getVertices().get(i + 1);
            } else {
                a = s.getVertices().get(i);
                b = s.getVertices().get(0);
            }
            deltax = b.getX() - a.getX();
            deltay = b.getY() - a.getY();
            System.out.println("Dx: " + deltax + "& Dy: " + deltay);
            sx += deltay * (3 * a.getX() * b.getX() + ((float) Math.pow(deltax, 2)));
            sy += deltax * (3 * a.getY() * b.getY() + ((float) Math.pow(deltay, 2)));
            System.out.println("sx parcial: " + sx);
        }
        sx = sx / 6;
        sy = sy / -6;
        momentosstaticos = new Vertice(sx, sy);
        //this.Sx = sx;
        //this.Sy = sy;

        System.out.println("Sx: " + momentosstaticos.getX());
        System.out.println("Sy: " + momentosstaticos.getY());
        return momentosstaticos;

    }

    public secaoTransversal unrotate(secaoTransversal s, float angulo) {
        secaoTransversal sec = new secaoTransversal();

        for (Vertice v : s.getVertices()) {

            float x, y;
            x = v.getX() * (float) Math.cos(angulo * (Math.PI / 180)) - v.getY() * (float) Math.sin(angulo * (Math.PI / 180));
            y = v.getX() * (float) Math.sin(angulo * (Math.PI / 180)) + v.getY() * (float) Math.cos(angulo * (Math.PI / 180));
            Vertice vs = new Vertice(x, y);
            sec.addVertice(vs);
            System.out.println("nrmal v: " + vs.getX() + "; " + vs.getY());

        }

        System.out.println("Area ver: " + sec.getArea());
        return sec;

    }

    public secaoTransversal ACC(secaoTransversal sec, float ymax, float x0, float yc) {
        secaoTransversal secT = new secaoTransversal();
        float deltaX, deltaY;

        for (int i = 0; i < sec.getVertices().size(); i++) {
            Vertice a, b;
            if (i < sec.getVertices().size() - 1) {
                a = sec.getVertices().get(i);
                b = sec.getVertices().get(i + 1);
            } else {
                a = sec.getVertices().get(i);
                b = sec.getVertices().get(0);
            }
            deltaX = b.getX() - a.getX();
            deltaY = b.getY() - a.getY();
            if (deltaY != 0) {
                float xLinha = a.getX() + (yc - a.getY()) * (deltaX / deltaY);
                Vertice Inter = new Vertice(xLinha, yc);

                if (Inter.getY() >= yc) {
                    secT.addVertice(Inter);

                }
                if (b.getY() >= yc) {
                    secT.addVertice(b);
                }
                System.out.println("lado " + (i + 1) + " faz intercepto");
            } else {
                System.out.println("lado" + (i + 1) + " Nao faz intercepto");
                if(b.getY() >= yc){
                    secT.addVertice(b);
                }
            }

        }
        for (int m = 0; m < secT.getVertices().size(); m++) {
            System.out.println("Vcc: " + secT.getVertices().get(m).getX() + "; " + secT.getVertices().get(m).getY());
        }
        System.out.println("tamanho: " + secT.getVertices().size());
        System.out.println("Area CC: " + secT.getArea());
        this.acc = secT.getArea();
        return secT;
    }

    private void deformacaoBarra(dominiosDeformacao dom, Barras bar, float x0, float dUtil, float H) {
        float esi;
        for (int i = 0; i < bar.getBarras().size(); i++) {
            if (dom.getDominiosDeformacao() == 0) {
                esi = (10 * ((x0 - bar.getBarras().get(i).getDi()) / (dUtil - x0))) / 1000;
            } else {
                if (dom.getDominiosDeformacao() == 1) {
                    esi = (this.materiais.getConcrete().getDeformacaoEu() * ((x0 - bar.getBarras().get(i).getDi()) / x0)) / 1000;
                } else {
                    esi = (this.materiais.getConcrete().getDeformacaoE0() * ((x0 - bar.getBarras().get(i).getDi()) / (x0 - this.materiais.getConcrete().getK() * H))) / 1000;
                }
            }
            esi = esi * (1);
            bar.getBarras().get(i).setDefbarra((esi));
            bar.getBarras().get(i).setTensao(this.materiais.getAco().getEcs());
            System.out.println(" ");
            System.out.println("deformaçao Esi: " + esi);
            System.out.println("tensao: " + bar.getBarras().get(i).getTensaoBarra());

        }
    }

    public dominiosDeformacao verifyDomain(float x0, float d, float h) {
        dominiosDeformacao dom;
        float ds = (this.materiais.getConcrete().getDeformacaoEu() / (this.materiais.getConcrete().getDeformacaoEu() + 10)) * d;
        if (x0 >= 0 && x0 <= ds) {

            dom = dominiosDeformacao.DOMINIO_2;

        } else {
            if (x0 >= ds && x0 <= h) {
                dom = dominiosDeformacao.DOMINIO_3_4_4A;
            } else {
                dom = dominiosDeformacao.DOMINIO_5;
            }
        }
        System.out.println("dominios: " + dom.toString());

        return dom;
    }

    private ArrayList<Float> parametros(secaoTransversal secRot, float x0) {
        ArrayList<Float> parametros = new ArrayList<>();
        float ht, dt, ymax, ymin, gamaC;
        ymax = 0;
        ymin = 0;
        for (Vertice v : secRot.getVertices()) {
            if (ymax < v.getY()) {
                ymax = v.getY();
            }
            if (ymin > v.getY()) {
                ymin = v.getY();
            }
        }
        ht = ymax - ymin;
        // altura peça em relaçao ao angulo alfa
        parametros.add(0, ht);
        //this.h = ht;

        for (int j = 0; j < secRot.getBars().getBarras().size(); j++) {
            float ys = secRot.getBars().getBarras().get(j).getY();
            secRot.getBars().getBarras().get(j).setDi(ymax - ys);
        }
        float da = 0;
        for (barra b : secRot.getBars().getBarras()) {
            if (da < b.getDi()) {
                da = b.getDi();
            }
        }
        dt = da;
        //altura util
        parametros.add(1, dt);
        //this.d = dt;
        //Ymax
        parametros.add(2, ymax);
        //this.yMax = ymax;
        //YC
        gamaC = ymax - this.materiais.getConcrete().getLambda() * x0;
        parametros.add(3, gamaC);
        //x0
        parametros.add(4, x0);
        System.out.println("Yc = " + parametros.get(3) + ", h= " + parametros.get(0) + " , d = " + parametros.get(1));
        return parametros;
    }

    private secaoTransversal Rotate(secaoTransversal sec, float angulo) {
        secaoTransversal section = new secaoTransversal();
        secaoTransversal sec2;
        Barras bars = new Barras();
        for (int i = 0; i < sec.getVertices().size(); i++) {
            Vertice v = new Vertice(sec.getVertices().get(i).getX() * ((float) Math.cos(angulo * (Math.PI / 180))) + sec.getVertices().get(i).getY() * ((float) Math.sin(angulo * (Math.PI / 180))),
                    (-1) * sec.getVertices().get(i).getX() * (float) Math.sin(angulo * (Math.PI / 180)) + sec.getVertices().get(i).getY() * (float) Math.cos(angulo * (Math.PI / 180)));

            section.addVertice(v);
        }
        for (int j = 0; j < sec.getBars().getBarras().size(); j++) {
            barra bar = new barra(sec.getBars().getBarras().get(j).getDiametro(), sec.getBars().getBarras().get(j).getX() * ((float) Math.cos(angulo * (Math.PI / 180)))
                    + sec.getBars().getBarras().get(j).getY() * ((float) Math.sin(angulo * (Math.PI / 180))),
                    (-1) * sec.getBars().getBarras().get(j).getX() * ((float) Math.sin(angulo * (Math.PI / 180))) + sec.getBars().getBarras().get(j).getY() * ((float) Math.cos(angulo * (Math.PI / 180))));
            bars.addBarra(bar);
            //verificação

        }
        sec2 = new secaoTransversal(section, bars);
        //this.secRotate = sec2;

        for (Vertice v : sec2.getVertices()) {
            System.out.println("VR: " + v.getX() + "; " + v.getY());
        }
        for (barra b : sec2.getBars().getBarras()) {
            System.out.println("BR: " + b.getX() + "; " + b.getY() + " dia: " + b.getDiametro());
        }
        return sec2;
    }

    private secaoTransversal Translate(secaoTransversal sec) {
        secaoTransversal section = new secaoTransversal();
        secaoTransversal sec2;
        Barras bars = new Barras();
        for (int i = 0; i < sec.getVertices().size(); i++) {
            Vertice v = new Vertice(sec.getVertices().get(i).getX() - sec.getCentroide().getX(), sec.getVertices().get(i).getY() - sec.getCentroide().getY());
            section.addVertice(v);

        }
        for (int j = 0; j < sec.getBars().getBarras().size(); j++) {
            barra bT = new barra(sec.getBars().getBarras().get(j).getDiametro(), sec.getBars().getBarras().get(j).getX() - sec.getCentroide().getX(),
                    sec.getBars().getBarras().get(j).getY() - sec.getCentroide().getY());
            bars.addBarra(bT);
        }
        sec2 = new secaoTransversal(section, bars);

        //this.secTransladada = sec2;
        System.out.println("Area transladaded: " + sec2.getArea());
        System.out.println("Area barras total: " + sec2.getBars().getAreaBars());
        return sec2;
    }

    /**
     * @return the fx
     */
    public float getFx() {
        return fx;
    }

    /**
     * @return the X0
     */
    public float getX0() {
        return X0;
    }

}
