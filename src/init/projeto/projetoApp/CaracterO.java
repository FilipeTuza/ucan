/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Rectangle;

/**
 *
 * @author moreno angola
 */
public class CaracterO extends Caracter
{
    private int espessura;
    private int x;
    private int y;

    public CaracterO(Form pai,int espessura, int x, int y, int cor) 
    {
        this.pai = pai;
        this.x = x;
        this.y = y;
        this.espessura = espessura;
        this.cor = cor; 
        largura = Defs.LARGURA_PADRAO_CARACTER_O;
        altura = Defs.ALTURA_PADRAO_CARACTER_O;
    }
    
    public CaracterO( Form pai ) 
    {
        this
        (
            pai, Defs.ESPESSURA_PADRAO_CARACTER_O,
            Defs.X_PADRAO_CARACTER_O ,Defs.Y_PADRAO_CARACTER_O,
            Defs.COR_PADRAO_CARACTER_O
        );
    }
    
    @Override
    public char getCaracter() 
    {
        return caracter = 'o';
    } 
    
    public void paint(Graphics g, Rectangle rect) 
    {
        g.setAntiAliased(true);
        g.setColor(cor);
        g.fillArc( x, y, largura, altura, (int) Math.toDegrees(400), (int) Math.toDegrees(400));
//        g.setColor( ((SenarioDoJogo)pai).getCorFundo());
        g.setColor(Defs.AMARELA_2);
        g.fillArc( x + 3, y + 3, largura - espessura, altura - espessura, (int) Math.toDegrees(400), (int) Math.toDegrees(400));
        
    }
    
}
