/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.Painter;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.plaf.Style;

public class PainelEstadoJogo implements Painter
{
    JogoControlo controlo;
    
    private int x;
    private int y;
    
    private int tX;
    private int tY;
    
    private int largura;
    private int altura;

    private int corFundo;
    private int corContorno;    
    private int curva;
    private int spaco;
    private int textColor;
    
    public PainelEstadoJogo( JogoControlo controlo )
    {
        this.controlo = controlo;
        spaco = 10;
        x = 10;
        y = 10;
        
        tX = x + spaco;
        tY = y + (spaco/2);
        
        curva = 10;
        largura = 300;
        altura = 100;
        
        corFundo = 0xf1f1f2;
        corContorno = 0x235643;  
        textColor = 0x00AA43;

    }

    public JogoControlo getControlo() {
        return controlo;
    }

    public void setControlo(JogoControlo controlo) {
        this.controlo = controlo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int gettX() {
        return tX;
    }

    public void settX(int tX) {
        this.tX = tX;
    }

    public int gettY() {
        return tY;
    }

    public void settY(int tY) {
        this.tY = tY;
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

    public int getCorFundo() {
        return corFundo;
    }

    public void setCorFundo(int corFundo) {
        this.corFundo = corFundo;
    }

    public int getCorContorno() {
        return corContorno;
    }

    public void setCorContorno(int corContorno) {
        this.corContorno = corContorno;
    }

    public int getCurva() {
        return curva;
    }

    public void setCurva(int curva) {
        this.curva = curva;
    }

    public int getSpaco() {
        return spaco;
    }

    public void setSpaco(int spaco) {
        this.spaco = spaco;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
    
    public String textoDuelo ()
    {
        return (( controlo.getNumeroDePartidas() - 1 ) == 0 )? "* * * Duelo Final * * *" : "Duelos Restantes ";
    }
    
    public void paint(Graphics g, Rectangle rect) 
    {
        g.setColor(corFundo);
        
        g.fillRoundRect(x, y,largura, altura,curva,curva);
        
        g.setColor(corContorno);
        
        g.fillRoundRect(x + (spaco/2), y  + (spaco/2),largura - spaco, altura - spaco,curva,curva); 
        
        g.setFont(Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, 16));
        
        g.setColor( textColor );
        g.drawString(controlo.getJogador1().getNome()+" ("+controlo.getJogador1().getCaracter()+")",
                                                                                tX, tY + 3,Style.TEXT_DECORATION_3D);
        
        g.drawString("Pts("+controlo.getJogador1().getVitorias()+")", tX + 170, tY + 3,Style.TEXT_DECORATION_3D);
        
        g.drawString(controlo.getJogador2().getNome()+" ("+controlo.getJogador2().getCaracter()+")",
                                                                                 tX, tY + 28,Style.TEXT_DECORATION_3D); 
        g.drawString("Pts("+controlo.getJogador2().getVitorias()+")", tX + 170, y +(spaco/2) + 28,Style.TEXT_DECORATION_3D);
        
        g.drawString(textoDuelo (), tX , tY + 50,Style.TEXT_DECORATION_3D); 
        if (( controlo.getNumeroDePartidas() - 1 ) > 0)
        g.drawString("" + ( controlo.getNumeroDePartidas() - 1 ), tX + 230, tY + 50,Style.TEXT_DECORATION_3D);
    }
    
}
