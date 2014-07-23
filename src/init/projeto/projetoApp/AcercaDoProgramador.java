/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

import com.codename1.ui.Button;
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
public class AcercaDoProgramador extends Form implements Painter,ActionListener
{
    private Image jogIMG;
    private Image imageUcanIMG;
    
    private FormPrincipal parent;
    private Button voltar;
    private int xTexto;
    private int yTexto;
    private int xImagem;
    private int yImagem;
    private int textColor;
    private int espacoHorizontal;
    private String [] texto;

    private int corFundo1;
    private int corFundo2;
    
    public AcercaDoProgramador(FormPrincipal parent) 
    {
        PainterChain.installGlassPane(this, this);
        jogIMG = Utils.buscaImagem("/filipe112.png");
        imageUcanIMG = Utils.buscaImagem("/ucan1.png");
        
        xTexto =  Utils.getXCentroEcran() - 200;
        yTexto = 22;
        xImagem = 10;
        yImagem = Utils.getYCentroEcran() - 100;
        textColor = Defs.CORES[ Defs.VERDE_1 ];
        espacoHorizontal = Utils.getYCentroEcran() + 140;
        this.parent = parent;

        
        corFundo1 = Defs.CORES[ Defs.PRETA ];
        corFundo2 = Defs.CORES[ Defs.CINZA_3 ];
        
        texto = new String []
        {
            "Nome: Filipe Tuza",
            "Universidade : U.C.A.N",
            "Faculdade: Engenharia",
            "Curso : Informatica",
            "Ano: 3º Ano",
            "Turma: EIA3",
        };
        voltar = new Button("Voltar");
        voltar.addActionListener(this );
        
        setLayout(new BorderLayout());
        addComponent(BorderLayout.SOUTH, voltar);
    }

    public void escreverTextoAcerceDoProgramador ( Graphics g )
    {
        g.setFont( Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC|Font.STYLE_BOLD, Font.SIZE_LARGE));
        g.setColor(textColor);
        for ( int i = 0; i< texto.length; i++)
        {
            g.drawString(texto[ i ], xTexto, yTexto * (i+1) + espacoHorizontal, Style.TEXT_DECORATION_3D);
        }
    }
        
    public AcercaDoProgramador ()
    {
        this ( null);
    }
    
    public void desenhaBotaoVoltar ( Graphics g )
    {
        g.setColor(Defs.CORES [ Defs.LARANJA_2]);
        g.drawRect(xTexto + 150, getHeight() - 40  , 110, 27 );
        g.drawString(" Volatar ",xTexto + 150, getHeight()-46 );
    }      
    
    public void desenharFundo ( Graphics g )
    {
        g.drawImage(Utils.buscaImagem("/fundo-madeira.jpg"), 0, 0 ,getWidth(),getHeight());
    }
    
    public void desenharProgramador ( Graphics g )
    {
        g.drawImage(Utils.buscaImagem("/Philipe1.1.1.jpg"), Utils.getXCentroEcran() - 200, Utils.getYCentroEcran() - 200 );
    }
    
    public void paint(Graphics g, Rectangle rect) 
    {
        g.setAntiAliased(true);
        g.setAntiAliasedText(true);
        desenharFundo ( g );
        desenharProgramador( g );
              
        g.drawImage(imageUcanIMG, xImagem + 200, yImagem);
        escreverTextoAcerceDoProgramador(g);
        desenhaBotaoVoltar (  g );
    }

    public void actionPerformed(ActionEvent evt) 
    {
        parent.showBack();
    }
}
