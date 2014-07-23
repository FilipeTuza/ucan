/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Painter;
import com.codename1.ui.geom.Rectangle;

/**
 *
 * @author moreno angola
 */
public class Grade implements Painter
{
    Form pai;
    
    private int afastamento;
    public static final int ASTE_VERTICAL_1 = 0,ASTE_VERTICAL_2 = 1;
    public static final int ASTE_HORIZONTAL_1 = 2,ASTE_HORIZONTAL_2 = 3;
    
    private Rectangle [] astes; 
    private int [] coresAtes ;
    private Ponto [] pontosOrigem; 
    
    private int largura;
    private int altura;
    private int dx;
    private int dy;
    
    public Grade(Form pai)
    {
        this.pai = pai;
        largura = 10;
        altura = 250;
        inicializarHastes ();
    }

    public Form getPai() {
        return pai;
    }

    public void setPai(Form pai) {
        this.pai = pai;
    }

    public Rectangle[] getAstes() {
        return astes;
    }

    public void setAstes(Rectangle[] astes) {
        this.astes = astes;
    }

    public int[] getCoresAstes() {
        return coresAtes;
    }

    public void setCoresAtes(int ... coresAtes) {
        this.coresAtes = coresAtes;
    }

    public Ponto[] getPontosOrigem() {
        return pontosOrigem;
    }

    public void setPontosOrigem(Ponto[] pontosOrigem) {
        this.pontosOrigem = pontosOrigem;
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
    
    public void inicializarHastes ()
    {

        coresAtes = new int []
        {
            Defs.CINZA_1,Defs.CINZA_3,Defs.CINZA_1,Defs.CINZA_3
        } ; 

        Ponto centro = getCentro();
        
        afastamento = (int) Utils.perecent(20, altura );

        pontosOrigem = new Ponto []
        {
            new Ponto ( centro.getX() - afastamento,centro.getY() - ( altura/2) ),
            new Ponto ( centro.getX() + afastamento,centro.getY() - ( altura/2) ),
            
            new Ponto ( centro.getX() - ( altura/2),centro.getY() - afastamento ),
            new Ponto ( centro.getX() - ( altura/2),centro.getY() + afastamento )
        };
                
        astes = new Rectangle[ pontosOrigem.length ];
        
        for (int i = 0; i < astes.length; i++ )
        {
            atualizarAste ( i );
        }
    }
    
    public void atualizarAste (int i )
    {
        if ( i <= ASTE_VERTICAL_2)
        {
            astes [ i ] = new Rectangle(pontosOrigem [ i ].getX(), pontosOrigem [ i ].getY(), largura, altura);
        }
        else 
        {
            astes [ i ] = new Rectangle(pontosOrigem [ i ].getX(), pontosOrigem [ i ].getY(), altura, largura);
        }

    }
    
    public Ponto getCentro ()
    {
        return new Ponto ( pai.getWidth()/2 ,pai.getHeight()/2);
    }
    
    public int getCoresAstes (int aste)
    {
        int color = 0;
        switch ( aste )
        {
            case ASTE_HORIZONTAL_2 :
                color = ( coresAtes.length == 4 )? coresAtes [ ASTE_HORIZONTAL_2 ] : 0 ;
                
                break;
                
            case ASTE_HORIZONTAL_1 :
                color = ( coresAtes.length >= 3 )? coresAtes [ ASTE_HORIZONTAL_1 ] : 0 ;
                
                break;
                
            case ASTE_VERTICAL_2:
                                color = ( coresAtes.length >= 2 )? coresAtes [ ASTE_VERTICAL_2 ] : 0 ;
                break;
            case ASTE_VERTICAL_1 :
                color = ( coresAtes.length >= 1 )? coresAtes [ ASTE_VERTICAL_1 ] : 0 ;
                break;
            
        }
        
        return color;
    }
    
    public void pintarHastes ( Graphics g )
    {
        for (int i = 0; i < astes.length; i++ )
        {
            atualizarAste ( i );
            g.setColor( getCoresAstes( i ) );
            g.fillShape( astes [ i ] );             
        }
    }
    
    public void setOrigem ( int x,int y )
    {
        Ponto centro = getCentro();
        
        int xCentro = centro.getX() - x;
        int yCentro = centro.getY() - y;
        
        pontosOrigem = new Ponto []
        {
            new Ponto ( xCentro - afastamento,yCentro - ( altura/2) ),
            new Ponto ( xCentro + afastamento,yCentro - ( altura/2) ),
            
            new Ponto ( xCentro - ( altura/2),yCentro - afastamento ),
            new Ponto ( xCentro - ( altura/2),yCentro + afastamento )
        };
    }
    
    public void mover ()
    {
        
        setOrigem ( dx,dy);
    }
    
    public Runnable moverParaDireita ()
    {
        return new Runnable ()
        {
            public void run ()
            {
                dx = ( dx < Utils.getXCentroEcran())? dx + 1 : dx;
            }
        };
    }
    public void paint(Graphics g, Rectangle rect) 
    {
        pintarHastes ( g );
    }
    
}
