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
public class LinhaNeutra {

    float X0;
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

    public float comecar(float X0, float alfa) {
        float result;
        this.X0 = X0;
        this.alfa = alfa;
        Translate(this.secaoRecebida);
        Rotate(this.secTransladada, alfa);
        parametros(this.secRotate);
        this.dominios = verifyDomain(X0, this.d, this.h);
        deformacaoBarra(this.dominios, this.secRotate.getBars(), X0, this.d, this.h);
        this.secACC = ACC(this.secRotate, this.yMax, X0);
        unrotate(this.secACC, alfa);
        staticsMomentos(this.secUnRotate);
        this.momentosResistentes = EquilibrioEquacoes(this.acc, this.secRotate, this.secTransladada, this.materiais, this.Sx, this.Sy);
        result = capacidadeResistente(this.secRotate, this.acc, this.momentosResistentes);
        return result;
    }

    // quero testar o valor retornado pela funcao comecar, cajo seja 0 o valor colocado como parametro X0 é o valor correto para
    // profundidade da LN. caso contrário deve gerar um novo intervalo e um novo chute, ate que satisfaça a condiçao
    // valor da funçao encontrada no metodo capacidadeResistente;
    public void bissecant(float a, float b, float angulo) {
        float c;
        if ((comecar(a, angulo)) * (comecar(b, angulo)) <= 0) {
            c = a;
            while (Math.abs((b - a)) >= 0.01) {
                c = (a + b) / 2;
                if (Math.abs(comecar(c, angulo)) <= 0.001) {
                    break;
                } else if (comecar(c, angulo) * comecar(a, angulo) < 0) {
                    b = c;
                } else {
                    a = c;
                }

            }
            System.out.println("LN: " + c);

        }
    }

    // pego o valor do esforço solicitante de calculo "2º botao" e faço a subtração da Normal de calculo resistente, encontrada no metodo
    //EquilibrioEquaçoes 

    private float capacidadeResistente(secaoTransversal secTensao, float Acc, Esforcos momentosR) {

        float fxs;
        // esforço solicitante de calculo - esforco resistente de calculo
        fxs = (this.esforcosRecebidos.getNk() - this.momentosResistentes.getNk());

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
        Mxdr = Mxdr / 100;
        Mydr = Mydr / 100;
        Ers = new Esforcos(Mxdr, Mydr, Ndr);

        System.out.println("Ndr: " + Ers.getNk() + ", Mxdr: " + Ers.getMxk() + ", Mydr: " + Ers.getMyk());

        return Ers;
    }

    private void staticsMomentos(secaoTransversal s) {
        float sx = 0f, sy = 0f;

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
        this.Sx = sx;
        this.Sy = sy;

        System.out.println("Sx: " + Sx);
        System.out.println("Sy: " + Sy);

    }

    public void unrotate(secaoTransversal s, float angulo) {
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
        this.secUnRotate = sec;

    }

    public secaoTransversal ACC(secaoTransversal sec, float ymax, float x0) {
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
                float xLinha = a.getX() + (this.gamac - a.getY()) * (deltaX / deltaY);
                Vertice Inter = new Vertice(xLinha, this.gamac);
                if (a.getY() >= this.gamac) {
                    secT.addVertice(a);
                }
                if (Inter.getY() >= this.gamac) {
                    secT.addVertice(Inter);

                }
                System.out.println("faz intercepto");
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
            bar.getBarras().get(i).setDefbarra(((1) * esi));
            System.out.println(" ");
            System.out.println("deformaçao Esi: " + esi);
            bar.getBarras().get(i).setTensao(this.materiais.getAco().getEcs());
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

    private void parametros(secaoTransversal secRot) {
        float ht, dt, ymax, ymin;
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
        this.h = ht;

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
        this.d = dt;
        this.yMax = ymax;
        this.gamac = this.yMax - this.materiais.getConcrete().getLambda() * this.X0;
        System.out.println("Yc = " + this.gamac + ", h= " + this.h + " , d = " + this.d);
    }

    private void Rotate(secaoTransversal sec, float angulo) {
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
        this.secRotate = sec2;

        for (Vertice v : sec2.getVertices()) {
            System.out.println("VR: " + v.getX() + "; " + v.getY());
        }
        for (barra b : sec2.getBars().getBarras()) {
            System.out.println("BR: " + b.getX() + "; " + b.getY() + " dia: " + b.getDiametro());
        }

    }

    private void Translate(secaoTransversal sec) {
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

        this.secTransladada = sec2;
        System.out.println("Area transladaded: " + secTransladada.getArea());
        System.out.println("Area barras total: " + secTransladada.getBars().getAreaBars());

    }

    /**
     * @return the fx
     */
    public float getFx() {
        return fx;
    }

}
