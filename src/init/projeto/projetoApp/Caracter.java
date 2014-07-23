/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

import com.codename1.ui.Form;
import com.codename1.ui.Painter;

/**
 *
 * @author moreno angola
 */
public abstract class  Caracter implements Painter
{
    protected char caracter = '-';
    protected Ponto origem;
    protected Form pai;
    protected int cor;
    protected int largura;
    protected int altura;    

    public abstract char getCaracter ();
            
    public Ponto getOrigem() {
        return origem;
    }

    public void setOrigem(Ponto origem) {
        this.origem = origem;
    }

    public Form getPai() {
        return pai;
    }

    public void setPai(Form pai) {
        this.pai = pai;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    @Override
    public String toString ()
    {
        return "\nCaracter : "+caracter +
               "\nOrigem: " +  origem +
               "\nCor: "+cor +
               "\nLargura : " +largura +
               "\nAltura"+ altura; 
    }
}
