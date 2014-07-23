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
public class CaracterX extends Caracter
{    
    private Rectangle [] astes;
    private Ponto [] origemAstes;

    public CaracterX( Form pai )
    {
        this(pai,Defs.X_PADRAO_CARACTER_X,Defs.Y_PADRAO_CARACTER_X,Defs.COR_PADRAO_CARACTER_X);
    }

    public CaracterX( Form pai, int x,int y ,int cor )
    {
        this.pai =  pai;
        
        this.cor = cor;
        largura = Defs.LARGURA_PADRAO_CARACTER_X;
        altura = Defs.ALTURA_PADRAO_CARACTER_X;
        
        origem = new Ponto(x,y);
        
        inicializarAstes ();
    }

    public Rectangle[] getAstes() {
        return astes;
    }

    public void setAstes(Rectangle[] astes) {
        this.astes = astes;
    }
    
    @Override
    public char getCaracter() 
    {
        return caracter = 'x';
    } 
    
    public void inicializarAstes ()
    {     
        origemAstes = new Ponto[]
        {
            new Ponto (origem.getX() + (altura/2), origem.getY() ),
            new Ponto (origem.getX() , origem.getY() + (altura/2) )
        };
        
        astes = new Rectangle [ origemAstes.length ] ;
        
        for ( int i = 0; i < astes.length; i ++ )
        {
            atualizarAste ( i );
        }
  
    }
    
    public void atualizarAste ( int i )
    {
        if ( i == 0)
        {
            astes [ i ] = new Rectangle( origemAstes [ i ].getX() , origemAstes [ i ].getY(), largura, altura );
        }
        else 
        {
            astes [ i ] = new Rectangle( origemAstes [ i ].getX() , origemAstes [ i ].getY(), altura,largura );
        }
    }
    
    public void pintarAstes ( Graphics g )
    {
        for ( int i = 0; i < astes.length; i ++ )
        {
            atualizarAste ( i );
            g.setColor(cor);
            g.fillShape( astes[ i ] );
        }        
    }
    
    
    
    public void paint(Graphics g, Rectangle rect) 
    {
        g.rotate( (float)Math.toRadians(45), origem.getX() + (largura/2)+20, origem.getY() + (altura/2));
        pintarAstes ( g );
        g.resetAffine();
    }

    
}
