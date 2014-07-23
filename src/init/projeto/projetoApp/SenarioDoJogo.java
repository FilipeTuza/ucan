/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Painter;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.painter.PainterChain;


public class SenarioDoJogo extends Form implements Painter,Runnable
{
    private FormPrincipal principal;
    
    private boolean cpUPodeJogar;
    private JogoControlo controlo;     
    private SistemaEstado sistema;
    private PainelEstadoJogo painelEstado;
    private Grade grade;
    private char caracterAtual;

    private int  totalJogadas;
    private Quadrante [] quadrantes ;
    private Rectangle fundo;
    
    private int corFundo;
    
    private Thread atualizador ;
    private Thread atualizadorCPU ;
    
    private Button voltarBT;
    
    public SenarioDoJogo() 
    {
    }
    
    public SenarioDoJogo ( FormPrincipal principal )
    {
        this.principal = principal;
        voltarBT = new Button ();
        PainterChain.installGlassPane(this, this);
        atualizador = new Thread(this);
        atualizador.start();
        this.sistema = principal.getSistema();
        totalJogadas = 0;
        caracterAtual = Defs.INITIAL_CHAR;
        corFundo = principal.getCorFundoJogo();
        grade = new Grade(this);
        grade.setCoresAtes(principal.getCorGrade(),principal.getCorGrade(),principal.getCorGrade(),principal.getCorGrade());
        controlo = new JogoControlo(this);
        painelEstado = new PainelEstadoJogo(controlo);
        painelEstado.setCorContorno(principal.getCorBordaPlacard());
        painelEstado.setCorFundo(principal.getCorFundoPlacard());
        painelEstado.setTextColor(principal.getCorTextoPlacard());
        
        inicializarQuadrantes ();
        trataEventos ();
                
        setLayout(new BorderLayout());
        
        addComponent ( BorderLayout.SOUTH, voltarBT );
    }

    public char getCaracterAtual() {
        return caracterAtual;
    }

    public void setCaracterAtual(char caracterAtual) {
        this.caracterAtual = caracterAtual;
    }

    public int getTotalJogadas() {
        return totalJogadas;
    }

    public void setTotalJogadas(int totalJogadas) {
        this.totalJogadas = totalJogadas;
    }

    public Quadrante[] getQuadrantes() {
        return quadrantes;
    }

    public void setQuadrantes(Quadrante[] quadrantes) {
        this.quadrantes = quadrantes;
    }

    
    
    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public JogoControlo getControlo() {
        return controlo;
    }

    public void setControlo(JogoControlo controlo) {
        this.controlo = controlo;
    }

    public SistemaEstado getSistema() {
        return sistema;
    }

    public void setSistema(SistemaEstado sistema) {
        this.sistema = sistema;
    }

    public PainelEstadoJogo getPainelEstado() {
        return painelEstado;
    }

    public void setPainelEstado(PainelEstadoJogo painelEstado) {
        this.painelEstado = painelEstado;
    }

    
    
    public Rectangle getFundo() {
        return fundo;
    }

    public void setFundo(Rectangle fundo) {
        this.fundo = fundo;
    }

    public int getCorFundo() {
        return corFundo;
    }

    public void setCorFundo(int corFundo) {
        this.corFundo = corFundo;
    }

    public Thread getAtualizador() {
        return atualizador;
    }

    public void setAtualizador(Thread atualizador) {
        this.atualizador = atualizador;
    }
    
    public void trataEventos ()
    {
  
        addPointerDraggedListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent evt) 
                {
                    
                }
            }
        );
        
        addPointerPressedListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent evt) 
                {
                    if (sistema.getModalidade() == Defs.JOGADOR1_VS_JOGADOR2 )
                    {
                        adicionarCaracter ( evt );
                    }
                    else
                    {
                        adicionarCaracterJogador1 ( evt );
                    }
                    
                }
            }
        );
        
        addPointerReleasedListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent evt) 
                {        
                    
                }
            }
        );
        
        addShowListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent evt) 
                {

                }
            }
        );
        
        voltarBT.addActionListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent evt) 
                {
                    boolean comfirmar = Dialog.show("? Questionário ?", "Pretende mesmo Cancelar este duelo ?", "Sim", "Nâo");
                    
                    if ( comfirmar ) 
                    {
                        principal.showBack();
                    } 
                }
            }
        );
    }
    
    public void pintarFundo ( Graphics g)
    {
        g.setColor(corFundo);
        fundo = new Rectangle( 0,0, getWidth(), getHeight());
        g.fillShape( fundo );
    }
    
    public void decretarFiDoDuelo ()
    {
        if ( controlo.getNumeroDePartidas() == 0  )
        {
            Jogador j2 = ( sistema.getModalidade() == Defs.JOGADOR1_VS_JOGADOR2 )? controlo.getJogador2(): controlo.getcPU();
            
            if ( controlo.getJogador1().getVitorias() >  j2.getVitorias() ) 
            {
                 Dialog.show("Fim do duelo", "Fim\n Vencedor " + controlo.getJogador1() , "OK", "Cancelar");
            }
            else if ( controlo.getJogador1().getVitorias() <  j2.getVitorias()) 
            {
                 Dialog.show("Fim do duelo", "Fim\n Vencedor " + j2.getVitorias() , "OK", "Cancelar");
            }
            else
            {
                Dialog.show("Fim do duelo ", "Duelo Empatado", "OK", "Cancelar");
            }
            
            principal.showBack();
        }
    }
    
    
    
    private void adicionarCaracter ( ActionEvent evt )
    { 
        int quadrante = acharQuadrante( evt );
                
        if ( quadrante != 0  )
        {
            if ( !quadranteOcupado( quadrante ))
            {
                Ponto p = getCoodenadas ( quadrante , caracterAtual );

                quadrantes [ quadrante - 1 ].setCarater( getCaracteInstance (  p ) );
                quadrantes [ quadrante - 1 ].setOcupado(true);

                if ( controlo.getJogador1().getCaracter() == caracterAtual )
                {
                    controlo.getJogador1().addJogada(quadrante);
                }
                else
                {
                    controlo.getJogador2().addJogada(quadrante);
                }

                caracterAtual = ( caracterAtual == 'x' )? 'o' : 'x';
                controlo.setJogadasEfectuadas( controlo.getJogadasEfectuadas() + quadrante );
                totalJogadas ++;
                repaint ();

            }
            else 
            {                
                Dialog.show("Mensagem de Erro! ", "Selecione um Quadrante Livre", "OK", "Cancelar");
            }  
            
            if ( controlo.fimDoJogo() )
            {
                controlo.setNumeroDePartidas( controlo.getNumeroDePartidas() - 1 );
                Jogador aux = controlo.getVencedor();

                if ( aux != null ) 
                {
                    aux.setVitorias( aux.getVitorias() + 1 );
                    Dialog.show("Informação! ", "Fim\n Vencedor " + aux , "OK", "Cancelar");
                }
                else 
                {
                    Dialog.show("Informação! ", "Fim\n Partida enpatada ", "OK", "Cancelar");
                }

                limparGrade ();
                decretarFiDoDuelo ();
            } 
        }       
    }
    
    private void adicionarCaracterJogador1 ( ActionEvent evt)
    { 
        int quadrante;
        quadrante = acharQuadrante( evt );
        
        if ( quadrante != 0  )
        {
            if ( !quadranteOcupado( quadrante ))
            {
                Ponto p = getCoodenadas ( quadrante , caracterAtual );

                quadrantes [ quadrante - 1 ].setCarater( getCaracteInstance (  p ) );
                quadrantes [ quadrante - 1 ].setOcupado(true);

                controlo.getJogador1().addJogada(quadrante);                
                controlo.setJogadasEfectuadas( controlo.getJogadasEfectuadas() + quadrante );
                totalJogadas ++;
                cpUPodeJogar = true;                
                repaint ();
                
                decretarFiDoDuelo ();

            }
            else 
            {                
                Dialog.show("Mensagem de Erro! ", "Selecione um Quadrante Livre", "OK", "Cancelar");
            }
           
        }       
    }
    
    private void adicionarCaracterCPU ( )
    { 
        int quadrante;
        
        quadrante = controlo.getcPU().ataque( controlo.getJogador1().getJogadas());
        
        if ( quadrante > 0 && !controlo.fimDoJogo())
        {
            cpUPodeJogar = !controlo.fimDoJogo();
            controlo.getcPU().removerJogadasCompletas ( controlo.getJogadasEfectuadas() );
            
            if ( !quadranteOcupado( quadrante ) && cpUPodeJogar)
            {
                System.out.print(" quadrante " + quadrante);
                quadrantes [ quadrante - 1 ].setCarater( getCaracteInstanceCPU ( quadrante )  );
                quadrantes [ quadrante - 1 ].setOcupado(true);

                controlo.getcPU().addJogada(quadrante);
                caracterAtual = controlo.getJogador1().getCaracter();
                controlo.setJogadasEfectuadas( controlo.getJogadasEfectuadas() + quadrante );
                totalJogadas ++;
                cpUPodeJogar = false;

            }
            else 
            {
                while( quadranteOcupado( quadrante) )
                {
                    quadrante= (quadrante +1 )%Defs.MAX_QUADRANTES + 1 ;
                    System.out.println("Quadrante alternativo " + quadrante);
                }; 
                
                quadrantes [ quadrante - 1 ].setCarater( getCaracteInstanceCPU ( quadrante )  );
                quadrantes [ quadrante - 1 ].setOcupado(true);

                controlo.getcPU().addJogada(quadrante);
                
                caracterAtual = controlo.getJogador1().getCaracter();
                controlo.setJogadasEfectuadas( controlo.getJogadasEfectuadas() + quadrante );
                totalJogadas ++;
                cpUPodeJogar = false;
            }
            
            if ( controlo.fimDoJogo() )
            {
                controlo.setNumeroDePartidas( controlo.getNumeroDePartidas() - 1 );
                Jogador aux = controlo.getVencedor();

                if ( aux != null ) 
                {
                    aux.setVitorias( aux.getVitorias() + 1 );
                    Dialog.show("Informação! ", "Fim\n Vencedor " + aux , "OK", "Cancelar");
                }
                else 
                {
                    Dialog.show("Informação! ", "Fim\n Partida enpatada ", "OK", "Cancelar");
                }

                limparGrade ();
                decretarFiDoDuelo ();
            }                
        } 
        else
        {
            controlo.setNumeroDePartidas( controlo.getNumeroDePartidas() - 1 );
            Jogador aux = controlo.getVencedor();

            if ( aux != null ) 
            {
                aux.setVitorias( aux.getVitorias() + 1 );
                Dialog.show("Informação! ", "Fim\n Vencedor " + aux , "OK", "Cancelar");
            }
            else 
            {
                Dialog.show("Informação! ", "Fim\n Partida enpatada ", "OK", "Cancelar");
            }

            limparGrade ();
            decretarFiDoDuelo ();
        }  
    }
    
    public void mostrarTodos ()
    {
        for ( Quadrante g : quadrantes )
        {
            System.out.println(g + "\n");
        }
    }
    
    public Caracter getCaracteInstance ( Ponto p )
    {
        return ( caracterAtual == 'x')? new CaracterX(this, p.getX(), p.getY(), principal.getCorCaracterX()): 
                new CaracterO(this, Defs.ESPESSURA_PADRAO_CARACTER_O,p.getX(), p.getY(), principal.getCorCaracterO());
    }
    
    public Caracter getCaracteInstanceCPU ( int q )
    {
        Ponto p = ( controlo.getcPU().getCaracter() == 'x')?  getCoodenadas ( q , 'x' ) : getCoodenadas ( q , 'o' );
        Caracter c;
        
        if ( controlo.getcPU().getCaracter() == 'x' )
        {
            p =  getCoodenadas ( q , 'x' ) ;
            c = new CaracterX(this,  p.getX(), p.getY(), principal.getCorCaracterX());
        }
        else 
        {
            p =  getCoodenadas ( q , 'o' ) ;
            c = new CaracterO(this, Defs.ESPESSURA_PADRAO_CARACTER_O,p.getX(), p.getY(), principal.getCorCaracterO());            
        }

        return c;       
    }
    
    public boolean quadranteOcupado ( int codigo )
    {
        for (int i = 0; i < quadrantes.length; i ++ )
        {
            if ( quadrantes [ i ].getCodigo() == codigo )
            {
                return quadrantes [ i ].isOcupado();
            }
        }
        
        return false;
    }
    
    public Ponto getCoodenadas ( int quadrante , char caracter )
    {
        Ponto p = null;
        switch ( quadrante )
        {
            case 1:
                 p = ( caracter == 'x')? 
                     new Ponto (Defs.X_QUAD_I_X,Defs.X_QUAD_I_Y) : new Ponto (Defs.O_QUAD_I_X,Defs.O_QUAD_I_Y) ;
                break;
                
            case 2:
                p = ( caracter == 'x')?    
                    new Ponto (Defs.X_QUAD_I_X + Defs.QUAD_INTERVALO,Defs.X_QUAD_I_Y ) :
                    new Ponto (Defs.O_QUAD_I_X + Defs.QUAD_INTERVALO,Defs.O_QUAD_I_Y) ;
                break;
                
            case 3:
                p = ( caracter == 'x')?    
                    new Ponto (Defs.X_QUAD_I_X + ( 2 * Defs.QUAD_INTERVALO ),Defs.X_QUAD_I_Y ) :
                    new Ponto (Defs.O_QUAD_I_X + ( 2 * Defs.QUAD_INTERVALO ),Defs.O_QUAD_I_Y) ;
                break;
                            
            case 4:
                p = ( caracter == 'x')?    
                    new Ponto (Defs.X_QUAD_I_X ,Defs.X_QUAD_I_Y + Defs.QUAD_INTERVALO ) :
                    new Ponto (Defs.O_QUAD_I_X ,Defs.O_QUAD_I_Y + Defs.QUAD_INTERVALO ) ;
                break;
                
            case 5:
                p = ( caracter == 'x')?    
                    new Ponto (Defs.X_QUAD_I_X + Defs.QUAD_INTERVALO ,Defs.X_QUAD_I_Y + Defs.QUAD_INTERVALO) :
                    new Ponto (Defs.O_QUAD_I_X + Defs.QUAD_INTERVALO,Defs.O_QUAD_I_Y + Defs.QUAD_INTERVALO) ;
                break;
                
            case 6:
                p = ( caracter == 'x')?    
                    new Ponto (Defs.X_QUAD_I_X + ( 2 * Defs.QUAD_INTERVALO ),Defs.X_QUAD_I_Y + Defs.QUAD_INTERVALO ) :
                    new Ponto (Defs.O_QUAD_I_X + ( 2 * Defs.QUAD_INTERVALO ),Defs.O_QUAD_I_Y + Defs.QUAD_INTERVALO) ;
                break;
                
            case 7:
                p = ( caracter == 'x')?    
                    new Ponto (Defs.X_QUAD_I_X ,Defs.X_QUAD_I_Y + ( 2 * Defs.QUAD_INTERVALO ) ) :
                    new Ponto (Defs.O_QUAD_I_X ,Defs.O_QUAD_I_Y + ( 2 * Defs.QUAD_INTERVALO ) ) ;
                break;
                
            case 8:
                p = ( caracter == 'x')?    
                    new Ponto (Defs.X_QUAD_I_X + Defs.QUAD_INTERVALO ,Defs.X_QUAD_I_Y + ( 2 * Defs.QUAD_INTERVALO ) ) :
                    new Ponto (Defs.O_QUAD_I_X + Defs.QUAD_INTERVALO ,Defs.O_QUAD_I_Y + ( 2 * Defs.QUAD_INTERVALO ) ) ;
                break;
                
            case 9:                
                p = ( caracter == 'x')?    
                    new Ponto (Defs.X_QUAD_I_X + ( 2 * Defs.QUAD_INTERVALO ),Defs.X_QUAD_I_Y + ( 2 * Defs.QUAD_INTERVALO ) ) :
                    new Ponto (Defs.O_QUAD_I_X + ( 2 * Defs.QUAD_INTERVALO ),Defs.O_QUAD_I_Y + ( 2 * Defs.QUAD_INTERVALO ) ) ;
                break;
                
        }
        
        return p;
    }
    
    public void pintarCaracteres ( Graphics g )
    {
        for ( Quadrante quadrante : quadrantes  )
        {
            if ( quadrante.isOcupado() )
            {
                if (quadrante.getCarater() != null ) 
                {
                    quadrante.getCarater().paint( g, fundo );
                }
            }
        }
    }

    public void inicializarQuadrantes ()
    {
        quadrantes = new Quadrante[Defs.MAX_QUADRANTES];

        for ( int i = 0; i < Defs.MAX_QUADRANTES; i++ )
        {
            quadrantes [ i ] = new Quadrante( i + 1 );
        }
    }
    
    public void limparGrade ()
    {
        inicializarQuadrantes ();
        
        totalJogadas = 0;
        controlo.getJogador1().setJogadas("");
        controlo.getJogador2().setJogadas("");
        controlo.getcPU().setJogadas("");
        
        cpUPodeJogar = false;
    }
    
    
    public int acharQuadrante ( ActionEvent evt )
    {
        int quadrante = 0;
        
        Ponto p = new Ponto ( Utils.getXCentroEcran() - 125,Utils.getYCentroEcran()  - 125);

        int pX = p.getX() + 95;
        int pY = p.getY() + 95;
        //LINHA 1 - QUADRANTES I, II & III
        if ( ( evt.getX() > p.getX() && evt.getX() < pX ) && ( evt.getY() > p.getY() && evt.getY() < pY ) )
        {
            quadrante = 1;
        }
        
        else if ( ( evt.getX() > pX + 10 && evt.getX() < pX + 95 ) && ( evt.getY() > p.getY() && evt.getY() < pY ) )
        {
            quadrante = 2;
        }
        
        else if ( ( evt.getX() > pX + 105 && evt.getX() < pX + 200 ) && ( evt.getY() > p.getY() && evt.getY() < pY ) )
        {
            quadrante = 3;
        }
        //FIM - LINHA 1 - QUADRANTES I, II & III
                
        //LINHA 2 - QUADRANTES I, II & III
        else if ( ( evt.getX() > p.getX() && evt.getX() < p.getX() + 95 ) && ( evt.getY() > pY + 10 && evt.getY() < pY + 95 ) )
        {
            quadrante = 4;
        }
        
        else if ( ( evt.getX() > pX + 10 && evt.getX() < pX + 95 ) && ( evt.getY() > pY + 10 && evt.getY() < pY + 95 ) )
        {
            quadrante = 5;
        }
        
        else if ( ( evt.getX() > pX + 105 && evt.getX() < pX + 200 ) && ( evt.getY() > pY + 10 && evt.getY() < pY + 95 ) )
        {
            quadrante = 6;
        }
        //FIM - LINHA 2 - QUADRANTES I, II & III
        
        //LINHA 3 - QUADRANTES I, II & III
        else if ( ( evt.getX() > p.getX() && evt.getX() < p.getX() + 95 ) && ( evt.getY() > pY + 105 && evt.getY() < pY + 200 ) )
        {
            quadrante = 7;
        }
        
        else if ( ( evt.getX() > pX + 10 && evt.getX() < pX + 95 ) && ( evt.getY() > pY + 105 && evt.getY() < pY + 200 ) )
        {
            quadrante = 8;
        }
        
        else if ( ( evt.getX() > pX + 105 && evt.getX() < pX + 200 ) && ( evt.getY() > pY + 105 && evt.getY() < pY + 200 ) )
        {
            quadrante = 9;
        }        
        //FIM - LINHA 3 - QUADRANTES I, II & III
        
        return quadrante;
    }
    
    public void paint( Graphics g, Rectangle rect ) 
    {
        pintarFundo(g);
        grade.paint(g, rect);
        painelEstado.paint(g, rect);
        pintarCaracteres ( g );
        desenhaBotaoVoltar ( g );
    } 
    
    public void run() 
    {
        while (true)
        {
        if  ( sistema.getModalidade() == Defs.JOGADOR1_VS_SISTEMA )
        {
            if ( cpUPodeJogar )
            {
                adicionarCaracterCPU ( );
                repaint ();
                pause (1);
            }
        }
        
        repaint ();
        }
    }    
    
    public void desenhaBotaoVoltar ( Graphics g )
    {
        g.setFont( Font.createSystemFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC|Font.STYLE_BOLD, Font.SIZE_LARGE));
        g.setColor(Defs.CORES [ Defs.LARANJA_2]);
        g.drawRect(40 + 150, getHeight() - 40  , 110, 27 );
        g.drawString(" Volatar ",40 + 150, getHeight()-46 );
    }  
    
    public void pause ( int tempoEmMilisegundo )
    {
         try 
         {
             Thread.sleep((1000/24) * tempoEmMilisegundo );
         } catch (InterruptedException ex) { }
    }
}
