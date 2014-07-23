/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Painter;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.painter.PainterChain;
import com.codename1.ui.plaf.Style;



/**
 *
 * @author moreno angola
 */
public class AcercaDoJogo extends Form implements Painter,ActionListener
{
    private Image jogIMG;
    private FormPrincipal parent;
    private Button voltar;
    private int xTexto;
    private int yTexto;
    private int xImagem;
    private int yImagem;
    private int textColor;
    private int espacoHorizontal;
    private String [] texto;
    private Rectangle fundo;
    private int corFundo1;
    private int corFundo2;
    public AcercaDoJogo(FormPrincipal parent) 
    {
        PainterChain.installGlassPane(this, this);
        jogIMG = Utils.buscaImagem("/X 0 3.png");
        
        xTexto = 10;
        yTexto = 20;
        xImagem = Utils.getXCentroEcran() - 150;
        yImagem = Utils.getYCentroEcran() - 350;
        textColor = Defs.CORES[ Defs.VERDE_1 ];
        espacoHorizontal = Utils.getXCentroEcran() ;
        this.parent = parent;

        corFundo1 = Defs.CORES[ Defs.VERMELHO_2 ];
        corFundo2 = Defs.CORES[ Defs.LARANJA_2 ];
        
        texto = new String []
        {
            "   X / O - É um jogo super divertido, não",
            "apenas por ser fácil de jogar mas acima ",
            "de tudo por ser impolgante, simples e ",
            "complexo ao mesmo tempo o cara usuario ",
            "tirará bom proveito ao joga com seus,",
            "colegas de escola ( ao intervalo é claro )",
            ", de trabalho se for o caso e não só como ",
            "também entre os mem - bros da familia."
        };
        voltar = new Button("Voltar");
        voltar.addActionListener(this );
        
        setLayout(new BorderLayout());
        addComponent(BorderLayout.SOUTH, voltar);
    }

    public void escreverTextoAcerceDoJogo ( Graphics g )
    {
        g.setFont( Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC|Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        g.setColor(textColor);
        for ( int i = 0; i< texto.length; i++)
        {
            g.drawString(texto[ i ], xTexto, yTexto * (i+1) + espacoHorizontal, Style.TEXT_DECORATION_3D);
        }
    }
        
    public AcercaDoJogo ()
    {
        this ( null);
    }
    
    public void desenhaBotaoVoltar ( Graphics g )
    {
        g.setFont( Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC|Font.STYLE_BOLD, Font.SIZE_LARGE));
        g.setColor(Defs.CORES [ Defs.LARANJA_2]);
        g.drawRect(xTexto + 150, getHeight() - 40  , 110, 27 );
        g.drawString(" Volatar ",xTexto + 150, getHeight()-46 );
    }  
    
    public void desenharFundo ( Graphics g )
    {
        g.drawImage(Utils.buscaImagem("/fundo-madeira.jpg"), 0, 0 ,getWidth(),getHeight());
    }    
    
    public void paint(Graphics g, Rectangle rect) 
    {
        desenharFundo ( g );
        g.drawImage(jogIMG, xImagem, yImagem);
        escreverTextoAcerceDoJogo(g);
        desenhaBotaoVoltar ( g );
    }

    public void actionPerformed(ActionEvent evt) 
    {
        parent.showBack();
    }
}
