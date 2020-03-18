/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import views.progressDialog;

/**
 *
 * @author Administrador
 */
public class NeutralLine {

    JFrame parent;
    progressDialog PD;
    private final secaoTransversal secRecebida;
    private secaoTransversal secT = null;
    private final Esforcos esfRecebidos;
    private final Materials matRecebido;
    //cm
    private float dLim = 0;
    private float defLim = 0;
    private float lambda = 0;
    private static int prog = 0;

    public NeutralLine(JFrame parent, secaoTransversal secEntrada, Esforcos esfEntrada, Materials matEntrada) {
        this.parent = parent;
        this.secRecebida = secEntrada;
        this.esfRecebidos = esfEntrada;
        this.matRecebido = matEntrada;
        this.secT = Translate(this.secRecebida);
        this.dLim = (this.matRecebido.getConcrete().getDeformacaoEu() / (this.matRecebido.getConcrete().getDeformacaoEu() + 10));
        this.defLim = ((this.matRecebido.getAco().getFyd() / 10) / (this.matRecebido.getAco().getEcs() / 10));
        this.lambda = this.matRecebido.getConcrete().getLambda();

    }

    public List<Esforcos> FC_N_ENV(float Nd, float atb, float alfa1) {
        float xLN;
        float alfa = alfa1;
        List<Esforcos> moR = new ArrayList<>();
        xLN = bissecant(0, 1000, alfa, atb, Nd);
        for (float i = 0; i < 360; i++) {
            moR.add(moments(xLN, alfa, atb));
        }
        return moR;
    }

    //Metodo que retorna a envoltória e momentos resistentes de acordo com uma determinada area de aço e Esforco normal
    public List<Esforcos> envoltoria(float a1, float a2, float atb, float Nd) {
        float a;
        List<Esforcos> moR = new ArrayList<>();
        a = a1;
        float b;
        b = a2;
        float ln;
        PD = new progressDialog(parent);
        PD.setMaximum((int) b);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                PD.setVisible(true);
                }
        });
        for (float i = a; i < b; i++) {

            ln = bissecant(0, 1000, i, atb, Nd);
            moR.add(moments(ln, i, atb));
            prog = (int) i;
            PD.setValue(prog);
            PD.setVisible(true);

        }

        return moR;
    }

    private Esforcos moments(float x0, float alfa, float atb) {
        secaoTransversal secRotate;
        dominiosDeformacao domi;
        Esforcos Resistentes;
        float Acc;
        secaoTransversal secVCC;
        secaoTransversal secUnrot;
        Vertice staticsMoments;
        secRotate = rotate(this.secT, alfa);
        //mudei aqui
        domi = verifyDomain(x0, secRotate.getD(), secRotate.getH());
        deformacao(domi, secRotate, x0);
        secVCC = ACC(secRotate, x0);
        Acc = secVCC.getArea();//cm²
        secUnrot = Unrotate(secVCC, alfa);
        staticsMoments = staticsMomentos(secUnrot);
        Resistentes = equacoesEquilibrio(Acc, this.matRecebido, staticsMoments, atb, secRotate, this.secT);
        return Resistentes;
    }
// metodo da bissecant pr encontar a profundidade da lina neutra de acordo com um angulo alfa

    private float bissecant(float a, float b, float angulo, float atb, float Nd) {
        float xLn;
        float e0 = a;
        float eu = b;
        float f_0;
        f_0 = comecar(e0, angulo, atb, Nd);
        float f_u;
        f_u = comecar(eu, angulo, atb, Nd);
        while ((f_0 * f_u) > 0) {
            e0 = eu;
            eu = eu * 10;
            f_0 = comecar(e0, angulo, atb, Nd);
            f_u = comecar(eu, angulo, atb, Nd);
            System.out.println("intervalo avaliado " + e0 + " e " + eu);
        }
        System.out.println("");
        System.out.println("a Raiz encontar-se no intervalo " + e0 + " e " + eu);
        float e1;
        e1 = ((e0 * f_u) - (eu * f_0)) / (f_u - f_0);
        float f_e1;
        float p;
        f_e1 = comecar(e1, angulo, atb, Nd);
        while (Math.abs(f_e1) > (float) 0.001) {
            p = (f_0 * f_e1);
            if (p > 0) {
                e0 = e1;
                f_0 = comecar(e0, angulo, atb, Nd);
            } else {
                eu = e1;
                f_u = comecar(eu, angulo, atb, Nd);
            }
            e1 = ((e0 * f_u) - (eu * f_0)) / (f_u - f_0);
            f_e1 = comecar(e1, angulo, atb, Nd);
        }
        System.out.println("");
        System.out.println(" a profundidade da linha neutra é : " + e1);
        xLn = e1;
        return xLn;
    }
// metodo utilizado para fazer todos os passos necessarios para o calculo da capacidade resistente

    private float comecar(float x0, float alfa, float areaTotalBars, float Nd) {
        float f_x;
        secaoTransversal secRotate;
        dominiosDeformacao domi;
        Esforcos Resistentes;
        float Acc;
        secaoTransversal secVCC;
        secaoTransversal secUnrot;
        Vertice staticsMoments;

        secRotate = rotate(this.secT, alfa);
        //mudei aqui em baixo
        domi = verifyDomain(x0, secRotate.getD(), secRotate.getH());
        deformacao(domi, secRotate, x0);
        secVCC = ACC(secRotate, x0);
        Acc = secVCC.getArea();//cm²
        secUnrot = Unrotate(secVCC, alfa);
        staticsMoments = staticsMomentos(secUnrot);
        Resistentes = equacoesEquilibrio(Acc, this.matRecebido, staticsMoments, areaTotalBars, secRotate, this.secT);
        f_x = capacidadeResistente(Resistentes, Nd);
        System.out.println("F(" + x0 + ")= " + f_x);
        return f_x;
    }

    // metodo que retorna o valor da Normal resistente  - Normal solicitante
    private float capacidadeResistente(Esforcos Resistentes, float Nd) {
        float fx;
        float Nr = Resistentes.getNk();
        float Ns = Nd;
        fx = Ns - Nr;
        return fx;
    }

    // Metodo que retorna os esforços de equilibrio da  peça
    private Esforcos equacoesEquilibrio(float acc, Materials concreto, Vertice staticsM, float atotalBarra, secaoTransversal secRo, secaoTransversal secTransl) {
        Esforcos esf;
        //cm²
        float barsArea = atotalBarra / 100;
        float sx = staticsM.getX();
        float sy = staticsM.getY();
        //kN/cm²
        float sigma = concreto.getConcrete().getSigmacd() / 10;
        //cm²
        float ac = acc;
        float Ndr;
        float Mxdr;
        float Mydr;
        float somaTensaob = 0f;
        float MxSoma = 0f, MySoma = 0f;
        for (int i = 0; i < secRo.getBars().getBarras().size(); i++) {
            //kN/cm²
            float tensao = secRo.getBars().getBarras().get(i).getTensaoBarra();
            //cm
            float xsi = secTransl.getBars().getBarras().get(i).getX();
            float ysi = secTransl.getBars().getBarras().get(i).getY();

            somaTensaob += tensao;
            MxSoma += tensao * xsi;
            MySoma += tensao * ysi;
        }
        //kN
        Ndr = (ac * sigma) + (barsArea / secRo.getBars().getBarras().size()) * somaTensaob;
        //kN.m
        Mxdr = ((sx * sigma) + (barsArea / secRo.getBars().getBarras().size()) * (MxSoma)) / 100;
        Mydr = ((sy * sigma) + (barsArea / secRo.getBars().getBarras().size()) * (MySoma)) / 100;
        esf = new Esforcos(Mxdr, Mydr, Ndr);
        System.out.println("");
        System.out.println("Nd = " + esf.getNk() + " , Mxdr: " + esf.getMxk() + " , Mydr: " + esf.getMyk());
        return esf;
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
            System.out.println("Dx: " + deltax + " &  Dy: " + deltay);
            sx += deltay * (3 * a.getX() * b.getX() + ((float) Math.pow(deltax, 2)));
            sy += deltax * (3 * a.getY() * b.getY() + ((float) Math.pow(deltay, 2)));
            System.out.println("sx parcial: " + sx);
        }
        sx = sx / 6;
        sy = ((-1) * (sy / 6));
        momentosstaticos = new Vertice(sx, sy);

        System.out.println("Sx: " + momentosstaticos.getX());
        System.out.println("Sy: " + momentosstaticos.getY());
        return momentosstaticos;

    }

    private secaoTransversal Unrotate(secaoTransversal secACC, float angulo) {
        secaoTransversal sec = secACC;
        secaoTransversal secunRot;
        secaoTransversal s = new secaoTransversal();
        for (int i = 0; i < sec.getVertices().size(); i++) {
            float x = sec.getVertices().get(i).getX();
            float y = sec.getVertices().get(i).getY();
            float xL = x * (float) (Math.cos(angulo * (Math.PI / 180))) - y * (float) (Math.sin(angulo * (Math.PI / 180)));
            float yL = x * (float) (Math.sin(angulo * (Math.PI / 180))) + y * (float) (Math.cos(angulo * (Math.PI / 180)));
            Vertice vL = new Vertice(xL, yL);
            System.out.println("");
            System.out.println("VR: " + xL + "; " + yL);
            s.addVertice(vL);
        }

        secunRot = s;
        return secunRot;
    }

    private secaoTransversal ACC(secaoTransversal sec, float x0) {
        secaoTransversal secTc = new secaoTransversal();
        float yc = sec.getyMax() - x0 * this.lambda;
        System.out.println(" ");
        System.out.println("yc: " + yc + " lambda: " + this.getLambda() + " yMax: " + sec.getyMax());
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
                    secTc.addVertice(Inter);

                }
                if (b.getY() >= yc) {
                    secTc.addVertice(b);
                }
                System.out.println("lado " + (i + 1) + " faz intercepto");
            } else {
                System.out.println("lado" + (i + 1) + " Nao faz intercepto");
                if (b.getY() >= yc) {
                    secTc.addVertice(b);
                }
            }

        }
        for (int m = 0; m < secTc.getVertices().size(); m++) {
            System.out.println("Vcc: " + secTc.getVertices().get(m).getX() + "; " + secTc.getVertices().get(m).getY());
        }
        System.out.println("tamanho: " + secTc.getVertices().size());
        System.out.println("Area CC: " + secTc.getArea());

        return secTc;
    }

    private void tensao(barra bar, float fyd, float ECS) {
        float dfLim = this.getDefLim();
        float def = bar.getDefbarra();
        if (Math.abs(bar.getDefbarra()) > dfLim) {
            if (bar.getDefbarra() < 0) {
                bar.setTensao((-1 * fyd) / 10);
            } else {
                bar.setTensao(fyd / 10);
            }
        } else {
            bar.setTensao(def * (ECS / 10));
        }
        System.out.println("");
        System.out.println("tensao kN/cm²: " + bar.getTensaoBarra());

    }

    private void deformacao(dominiosDeformacao dominio, secaoTransversal secRot, float x0) {
        float xLn = x0;
        float d = secRot.getD();
        float h = secRot.getH();
        float eu = this.matRecebido.getConcrete().getDeformacaoEu();
        float e0 = this.matRecebido.getConcrete().getDeformacaoE0();
        float k = this.matRecebido.getConcrete().getK();
        float esi;
        for (int i = 0; i < secRot.getBars().getBarras().size(); i++) {
            float di = secRot.getBars().getBarras().get(i).getDi();
            System.out.println("");
            System.out.println("di : " + secRot.getBars().getBarras().get(i).getDi());
            if (dominio.getDominiosDeformacao() == 0) {
                esi = (10 * ((x0 - di) / (d - x0))) / 1000;
                secRot.getBars().getBarras().get(i).setDefbarra(esi);
                System.out.println("deformacao: " + secRot.getBars().getBarras().get(i).getDefbarra());
            } else if (dominio.getDominiosDeformacao() == 1) {
                esi = (eu * ((x0 - di) / x0)) / 1000;
                secRot.getBars().getBarras().get(i).setDefbarra(esi);
                System.out.println("deformacao: " + secRot.getBars().getBarras().get(i).getDefbarra());
            } else {
                esi = (e0 * ((x0 - di) / (x0 - (k * h)))) / 1000;
                secRot.getBars().getBarras().get(i).setDefbarra(esi);
                System.out.println("deformacao: " + secRot.getBars().getBarras().get(i).getDefbarra());
            }
            tensao(secRot.getBars().getBarras().get(i), this.matRecebido.getAco().getFyd(), this.matRecebido.getAco().getEcs());
        }

    }

    private dominiosDeformacao verifyDomain(float x0, float d, float h) {
        dominiosDeformacao dom;
        if (x0 >= 0 && x0 <= (this.dLim * d)) {
            dom = dominiosDeformacao.DOMINIO_2;
        } else if (x0 >= this.dLim * d && x0 <= h) {
            dom = dominiosDeformacao.DOMINIO_3_4_4A;
        } else {
            dom = dominiosDeformacao.DOMINIO_5;
        }
        System.out.println("");
        System.out.println("dominio: " + dom.toString());
        System.out.println("limite d: " + this.dLim);

        return dom;
    }

    private secaoTransversal rotate(secaoTransversal secTrans, float angulo) {
        secaoTransversal sec = secTrans;
        secaoTransversal secRot;
        secaoTransversal s = new secaoTransversal();
        Barras b = new Barras();
        for (int i = 0; i < sec.getVertices().size(); i++) {
            float x = sec.getVertices().get(i).getX();
            float y = sec.getVertices().get(i).getY();
            float xL = x * (float) (Math.cos(angulo * (Math.PI / 180))) + y * (float) (Math.sin(angulo * (Math.PI / 180)));
            float yL = (-1) * x * (float) (Math.sin(angulo * (Math.PI / 180))) + y * (float) (Math.cos(angulo * (Math.PI / 180)));
            Vertice vL = new Vertice(xL, yL);
            System.out.println("");
            System.out.println("VR: " + xL + "; " + yL);
            s.addVertice(vL);
        }
        for (int i = 0; i < sec.getBars().getBarras().size(); i++) {
            float x = sec.getBars().getBarras().get(i).getX();
            float y = sec.getBars().getBarras().get(i).getY();
            float dia = sec.getBars().getBarras().get(i).getDiametro();
            float xL = x * (float) (Math.cos(angulo * (Math.PI / 180))) + y * (float) (Math.sin(angulo * (Math.PI / 180)));
            float yL = (-1) * x * (float) (Math.sin(angulo * (Math.PI / 180))) + y * (float) (Math.cos(angulo * (Math.PI / 180)));
            barra bR = new barra(dia, xL, yL);
            b.addBarra(bR);
            System.out.println("");
            System.out.println("BR: " + xL + "; " + yL);

        }
        secRot = new secaoTransversal(s, b);
        return secRot;
    }

    private secaoTransversal Translate(secaoTransversal sec) {
        secaoTransversal section = new secaoTransversal();
        secaoTransversal sec2;
        Barras bars = new Barras();
        for (int i = 0; i < sec.getVertices().size(); i++) {
            Vertice v = new Vertice(sec.getVertices().get(i).getX() - sec.getCentroide().getX(), sec.getVertices().get(i).getY() - sec.getCentroide().getY());
            System.out.println("");
            System.out.println("VT: " + v.getX() + " , " + v.getY());
            section.addVertice(v);

        }
        for (int j = 0; j < sec.getBars().getBarras().size(); j++) {
            barra bT = new barra(sec.getBars().getBarras().get(j).getDiametro(), sec.getBars().getBarras().get(j).getX() - sec.getCentroide().getX(),
                    sec.getBars().getBarras().get(j).getY() - sec.getCentroide().getY());
            bars.addBarra(bT);
            System.out.println("BT: " + bT.getX() + ", " + bT.getY() + " di : " + bT.getDiametro());
        }
        sec2 = new secaoTransversal(section, bars);

        System.out.println("Area transladaded: " + sec2.getArea());
        System.out.println("Area barras total: " + sec2.getBars().getAreaBars());
        return sec2;
    }

    /**
     * @return the dLim
     */
    public float getdLim() {
        return dLim;
    }

    /**
     * @return the defLim
     */
    public float getDefLim() {
        return defLim;
    }

    /**
     * @return the lambda
     */
    public float getLambda() {
        return lambda;
    }

    /**
     * @return the prog
     */
    public int getProg() {
        return prog;
    }
}
