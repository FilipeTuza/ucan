/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.GenericSpinner;
import com.codename1.ui.table.TableLayout;



public class FormPrincipal extends Form 
{
    
    private TableLayout tLayout;
    private TableLayout.Constraint constraints;
    
    private SistemaEstado sistema;
    private SenarioDoJogo jogo;
    private Jogador jogador1;
    private Jogador jogador2;
    private ComponentGroup grupoComponent;

    private Label modalidadeLB;
    private Label jogador1LB;
    private Label jogador2LB;    
    
    private Label caractecrJogador1LB;
    private Label numeroDePartidasLB;    
    
    private RadioButton modalidade1RB;
    private RadioButton modalidade2RB;
    
    private TextField jogador1TF;
    private TextField jogador2TF;
    
    private ComboBox < String > caractecrJogadorCMB; 
    private ComboBox < String > numeroDePartidasCMB; 
    
    private GenericSpinner gSpiner ;

    private Container containerJogo;
    private Container contentorJogoPrincipal;
    
    private AcercaDoJogo acercaDoJogoBGP;
    private Container acercaDoJogoCont;
    private AcercaDoProgramador acercaDoProGramador;
    private Container containerDifinicao;
    private Container contentorDifinicaoPrincipal;
    
    private Label [] labelDefinicaoCoresLB;
    private ComboBox<String> [] comboDefinicaoCores;
    
    private String [] textoLabelsDefinicoesCores;
    
    private ComponentGroup grupoBotoesDefinicao;

    private OnOffSwitch padraoCoresBT;
      
    private ComponentGroup grupoBotoes;

    private Button padraoBT;
    private Button continuarBT;  
    private Button sairBT;
    
    
    private Tabs tabs;
    private Container [] contentores;   
    private String [] textTabs;    
    private int ACERCA_DE = 0;

    private String [] textButtons;
    private Label padraoLB;
    private Label cartaoPostal;
    private Image imagemCartaoPostal;
    private Image loader;
    private Container paginaInicila;
    
    private Label programadorLB;
    private Button programadorBT;
    
    private Label jogoLB;
    private Button jogoBT;    
    
    public FormPrincipal ()
    {      
        this ("");
    }
    
    public FormPrincipal ( String title )
    {
        super ( title );
        
        imagemCartaoPostal = Utils.buscaImagem("/fundo-madeira.jpg");
        cartaoPostal = new Label(imagemCartaoPostal);
        cartaoPostal.getStyle().setPadding(LEFT, 0);
 
        paginaInicila = new Container(new BorderLayout());
        paginaInicila.addComponent(BorderLayout.CENTER, cartaoPostal);
        
        acercaDoJogoBGP = new AcercaDoJogo( this );
        acercaDoProGramador = new AcercaDoProgramador(this);
        jogador1 = new Jogador();
        jogador2 = new Jogador();
        sistema = new SistemaEstado( );
        
        criarGUI ();
        atualizarEstado ();
        jogo = new SenarioDoJogo(this);
       
    }

    
    public void setJogo ( SenarioDoJogo jogo )
    {
        this.jogo = jogo;
    }
    
    public SenarioDoJogo getJogo ()
    {
        return jogo;
    }

    public SistemaEstado getSistema() {
        return sistema;
    }

    public void setSistema(SistemaEstado sistema) {
        this.sistema = sistema;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public void setJogador1(Jogador jogador1) {
        this.jogador1 = jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public void setJogador2(Jogador jogador2) {
        this.jogador2 = jogador2;
    }

    public int getModalidadeRB() 
    {
        return( modalidade1RB.isSelected() )? Defs.JOGADOR1_VS_JOGADOR2 : Defs.JOGADOR1_VS_SISTEMA ;
    }

    public void setModalidadeRB() 
    {
        modalidade1RB.setSelected(sistema.getModalidade() == Defs.JOGADOR1_VS_JOGADOR2);
        modalidade2RB.setSelected(sistema.getModalidade() == Defs.JOGADOR1_VS_SISTEMA);
    }

    public String  getJogador1Nome()
    {
        return jogador1TF.getText();
    }

    public void setJogador1Nome() 
    {
        jogador1TF.setText(sistema.getJogador1().getNome());
    }

    public String  getJogador2Nome()
    {
        return jogador2TF.getText();
    }
    public void setJogador2Nome() 
    {
        jogador2TF.setText(sistema.getJogador2().getNome());
    }

    public char getCaractecrJogadorCMB() 
    {
        return caractecrJogadorCMB.getSelectedItem().charAt(0);
    }

    public void setCaractecrJogadorCMB() 
    {
        int i = (sistema.getJogador1().getCaracter() == 'x') ? 0 : 1;

        caractecrJogadorCMB.setSelectedIndex( i );
    }

    public int getNumeroDePartidasCMB() 
    {
        return Integer.parseInt(numeroDePartidasCMB.getSelectedItem());
    }

    public void setNumeroDePartidasCMB( ) 
    {
        int numPart = sistema.getNumeroDePartidas();
        
        switch ( numPart )
        {
            case 1:
                numeroDePartidasCMB.setSelectedIndex( 0 );
                break;
            case 2:
                numeroDePartidasCMB.setSelectedIndex( 1 );
                break;                
            case 3:
                numeroDePartidasCMB.setSelectedIndex( 2 );                
                break;                
            case 4:
                numeroDePartidasCMB.setSelectedIndex( 3 );                
                break;                
            default:
                numeroDePartidasCMB.setSelectedIndex( 4 ); 
        }

    }
    
   public int getCorFundoJogo ()
   {
       return Defs.CORES [ comboDefinicaoCores [ 0 ].getSelectedIndex() ];
   }
   
   public int getCorCaracterX ()
   {
       return Defs.CORES [ comboDefinicaoCores [ 1 ].getSelectedIndex() ];
   }
   
   public int getCorCaracterO ()
   {
       return Defs.CORES [ comboDefinicaoCores [ 2 ].getSelectedIndex() ];
   }
   
   public int getCorGrade ()
   {
       return Defs.CORES [ comboDefinicaoCores [ 3 ].getSelectedIndex() ];
   }   
   
   
   public int getCorFundoPlacard ()
   {
       return Defs.CORES [ comboDefinicaoCores [ 4 ].getSelectedIndex() ];
   }
   public int getCorBordaPlacard ()
   {
       return Defs.CORES [ comboDefinicaoCores [ 5 ].getSelectedIndex() ];
   }
   public int getCorTextoPlacard ()
   {
       return Defs.CORES [ comboDefinicaoCores [ 6 ].getSelectedIndex() ];
   }   

    public void criarGUI ()
    {
        tLayout = new TableLayout(6, 3);
        
        constraints = new TableLayout.Constraint();
        
        inicializarTabs ();
        setLayout(new BorderLayout());
        contruirContentorAcercaDe ();
        inicilaizarGrupoBotoes ();
        inicilaizarGrupoBotoesDefinicao ();

        construirContainerJogo ();  
        construirContainerDefinicao ();
        inicializarContainerJogoPrincipal ();
        inicializarContainerDefinicaoPrincipal ();
        
        trataEventosBotoes ();
        
        addComponent( BorderLayout.CENTER, tabs );
    }
    
    public FormPrincipal getInstance ()
    {
        return this;
    }
    
    public void trataEventosBotoes ()
    {
        // JOGO = 0,ACERCA_DE = 1,DEFINICOES = 2,JOGO = 3;
        
        modalidade2RB.addActionListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent evt)
                {
                    jogador2TF.setText( Defs.NOME_SISTEM_1 ); 
                    jogador2TF.setEditable(modalidade2RB.isSelected()? false : true );
                }
            }
        );
        
        modalidade1RB.addActionListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent evt)
                {
                    jogador1TF.setText( Defs.NOME_PADRAO_1 ); 
                    jogador2TF.setText( Defs.NOME_PADRAO_2 ); 
                    jogador2TF.setEditable(modalidade1RB.isSelected());
                }
            }
        );
        
        continuarBT.addActionListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent evt) 
                {
                    jogador1 = new Jogador(0, jogador1TF.getText(),caractecrJogadorCMB.getSelectedItem().charAt(0), "");
                    char c = caractecrJogadorCMB.getSelectedItem().charAt(0) == 'x' ? 'o' : 'x'; 
                    jogador2 = new Jogador(0, jogador2TF.getText(),c, ""); 

                    sistema = new SistemaEstado( getInstance() );
                    
                    jogo = new SenarioDoJogo( getInstance() );
                    

                    jogo.show();
                    

                }
            }
        );
        
       padraoBT.addActionListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent evt) 
                {
                   sistema = new SistemaEstado();
                   atualizarEstado();
                }
            }
        );
                
        sairBT.addActionListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent evt) 
                {
                   boolean op = Dialog.show("Sair do programa", "Deseja mesmo sair do programa ?", "Sim", "Não");
                   
                   if ( op ) 
                   {
                        System.exit ( 0 );
                   }
                }
            }
        );
        
        padraoCoresBT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) 
            {
                
                if (padraoCoresBT.isValue())
                {
                    padronizarCores();
                }
                habilitarCombos ( !padraoCoresBT.isValue());
            }
        });
        
        
        jogoBT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) 
            {
                acercaDoJogoBGP.show();
            }
        });
        
        programadorBT.addActionListener
        (
            new ActionListener() 
            {
                public void actionPerformed(ActionEvent evt) 
                {
                    acercaDoProGramador.show();
                }
            }
        );
    }
    
    public void habilitarCombos ( boolean estado )
    {
        for ( ComboBox c : comboDefinicaoCores )
        {
            c.setEnabled(estado);
        }
    }
    
    public void contruirContentorAcercaDe ()
    {
        acercaDoJogoCont = new Container ( new BoxLayout(BoxLayout.Y_AXIS));

        programadorBT = new Button("Programador", Utils.buscaImagem("/Administrador.png"));
        jogoBT = new Button("Jogo", Utils.buscaImagem("/X 0 31_1.png"));
        jogoBT.setGap(20);
        acercaDoJogoCont.addComponent(programadorBT);
        acercaDoJogoCont.addComponent(jogoBT);

        tabs.addTab("Acercade", acercaDoJogoCont );
    }
    
    public void atualizarEstado ()
    {
        setJogador1Nome();
        setJogador2Nome();
        setNumeroDePartidasCMB();
        setCaractecrJogadorCMB();
        setModalidadeRB();
        padronizarCores();
    }   
    

    public void inicializarContainerJogoPrincipal ()
    {
        contentorJogoPrincipal = new Container(new FlowLayout(CENTER));        
        containerJogo.getStyle().setMargin(5, 50, 5, 5);
        
        contentorJogoPrincipal.addComponent(containerJogo);
        
        contentorJogoPrincipal.addComponent( grupoBotoes );
        
        tabs.addTab("JOGO", contentorJogoPrincipal );
    }
    
    public void inicilaizarGrupoBotoes ()
    {
        grupoBotoes = new ComponentGroup();

        padraoBT = new Button ("Padrão");
        continuarBT = new Button ("Continuar");
        sairBT = new Button ("Sair");
        
        grupoBotoes.addComponent(sairBT);
        grupoBotoes.addComponent(padraoBT);
        grupoBotoes.addComponent(continuarBT);

        grupoBotoes.setHorizontal(true);
    }
    
    public void inicilaizarGrupoBotoesDefinicao ()
    {
        grupoBotoesDefinicao = new ComponentGroup();
        
        padraoCoresBT = new OnOffSwitch();
        padraoLB = new Label("Padrão");

  
        grupoBotoesDefinicao.setHorizontal(true);
    }
    
    public void padronizarCores ()
    {
        comboDefinicaoCores [ 0 ].setSelectedIndex( Defs.BRANCO );
        comboDefinicaoCores [ 1 ].setSelectedIndex( Defs.VIOLETA_1 );
        comboDefinicaoCores [ 2 ].setSelectedIndex( Defs.VERDE_2 );
        comboDefinicaoCores [ 3 ].setSelectedIndex( Defs.PRETA );        
        comboDefinicaoCores [ 4 ].setSelectedIndex( Defs.AZUL_1 );
        comboDefinicaoCores [ 5 ].setSelectedIndex( Defs.PRETA );
        comboDefinicaoCores [ 6 ].setSelectedIndex( Defs.BRANCO );       
    }
    
    public void construirContainerDefinicao ()
    {
        textoLabelsDefinicoesCores = new String [] {
            "Fundo da arena","Caracter X","Caracter O","Grade",
            "Fundo placard","Borda placard","Texto placard"
        };       
        
        
        String [] cores = new String [] 
        { 
           "AMARELA_1", "AMARELA_2" ,//AMARELA 1 E 2
           "AZUL_1","AZUL_2",//AZUL 1 E 2
           "BRANCO",//BRANCO 1 
           "CINZA_1","CINZA_2","CINZA_3",//CINZA 1 , 2 E 3
           "LARANJA_1","LARANJA_2",//LARANJA 1 E 2
           "PRETA",//PRETA 1   
           "ROSA_1","ROSA_2",//ROSA 1 E 2     
           "VIOLETA_1","VIOLETA_2",//VIOLETA 1 E 2     
           "VERDE_1","VERDE_2 ",//VERDE 1 E 2   
           "VERMELHO_1","VERMELHO_2"//VERMELHO 1 E 2  
        };
                
        tLayout = new TableLayout(textoLabelsDefinicoesCores.length, 2);
        containerDifinicao = new Container(tLayout);        
        
        labelDefinicaoCoresLB = new Label [ textoLabelsDefinicoesCores.length ];
        comboDefinicaoCores = new ComboBox [ labelDefinicaoCoresLB.length ] ;
    
        for (int i = 0; i < textoLabelsDefinicoesCores.length ;i++)
        {
            labelDefinicaoCoresLB [ i ] = new Label ( textoLabelsDefinicoesCores [ i ] );
            comboDefinicaoCores [ i ] = new ComboBox<String>( cores );
        }
        
        int coluna = 0;
        
        for  (int linha = 0; linha < labelDefinicaoCoresLB.length ;linha++)
        {
            constraints = tLayout.createConstraint(linha, coluna );
            tLayout.setGrowHorizontally(true);
            containerDifinicao.addComponent(constraints,labelDefinicaoCoresLB [ linha ]);

            constraints = tLayout.createConstraint(linha, coluna + 1 );
            tLayout.setGrowHorizontally(true);
            containerDifinicao.addComponent(constraints,comboDefinicaoCores [ linha ]);

        }
        
    }
    
    public void inicializarContainerDefinicaoPrincipal ()
    {
        contentorDifinicaoPrincipal = new Container(new FlowLayout(CENTER));

        containerDifinicao.getStyle().setMargin(5, 0, 5, 5);
        containerDifinicao.setAlwaysTensile(true);
        contentorDifinicaoPrincipal.addComponent(containerDifinicao);
        
        contentorDifinicaoPrincipal.addComponent(padraoLB);
        contentorDifinicaoPrincipal.addComponent(padraoCoresBT);
     

        
        tabs.addTab("Definições", contentorDifinicaoPrincipal );
    }    
    public void inicializarTabs ()
    {
        tabs = new Tabs () ;
        tabs.setChangeTabOnFocus(true);
        tabs.applyRTL(true);

        tabs.addTab("Pagina inicial",paginaInicila );
        
    }
    
    public void construirContainerJogo ()
    {
        containerJogo = new Container(tLayout);
        
        tLayout = new TableLayout(6, 3);
        
        modalidadeLB = new Label("Modo");
        jogador1LB = new Label("Jogador1");
        jogador2LB = new Label("jogador2");    

        caractecrJogador1LB = new Label("Caracte1");
        numeroDePartidasLB = new Label("Nº/partidas");   

        modalidade1RB = new RadioButton("P1 VS P2");
        modalidade2RB = new RadioButton("P1 VS CPU");
        
        ComponentGroup modalidadesGroupe = new ComponentGroup();
        modalidadesGroupe.addComponent(modalidade1RB);
        modalidadesGroupe.addComponent(modalidade2RB);
        modalidadesGroupe.setHorizontal(true);
        
        ButtonGroup b = new ButtonGroup(); 
        b.add(modalidade1RB);
        b.add(modalidade2RB);
        
        jogador1TF = new TextField(" Jogador 1");
        jogador2TF = new TextField(" Jogador 1");

        caractecrJogadorCMB = new ComboBox < String > (new Object[]{"x","o"});


        Object [] partidas = new Object[]
        {
            "1","2","3","4"," 8 "
        };    
        numeroDePartidasCMB = new ComboBox < String > ( partidas );  

        tLayout.setGrowHorizontally(true);
        constraints = tLayout.createConstraint(0, 0);        
        constraints.setHorizontalSpan(1);
        containerJogo.addComponent(constraints, modalidadeLB);
        tLayout.setGrowHorizontally(false);
        
  
        constraints = tLayout.createConstraint(0, 1);
        constraints.setHorizontalSpan(2);        
        containerJogo.addComponent(constraints,modalidadesGroupe);

        constraints.setHorizontalSpan(1);  
        constraints = tLayout.createConstraint(2, 0);
        containerJogo.addComponent(constraints,jogador1LB);
        
        constraints = tLayout.createConstraint(2, 1); 
        tLayout.setGrowHorizontally(true);
        constraints.setHorizontalSpan(2); 
        containerJogo.addComponent(constraints,jogador1TF);

        constraints.setHorizontalSpan(1);  
        constraints = tLayout.createConstraint(3, 0);
        containerJogo.addComponent(constraints,jogador2LB);
        
        constraints = tLayout.createConstraint(3, 1); 
        tLayout.setGrowHorizontally(true);
        constraints.setHorizontalSpan(2); 
        containerJogo.addComponent(constraints,jogador2TF);   
        
        
        constraints.setHorizontalSpan(1);  
        constraints = tLayout.createConstraint(4, 0); 
        tLayout.setGrowHorizontally(true);
        constraints.setHorizontalSpan(1); 
        containerJogo.addComponent(constraints,caractecrJogador1LB);

        constraints.setHorizontalSpan(2); 
        constraints = tLayout.createConstraint(4, 1);
        containerJogo.addComponent(constraints,caractecrJogadorCMB);
        

        constraints = tLayout.createConstraint(5, 0);
        tLayout.setGrowHorizontally(false);
        constraints.setHorizontalSpan(1); 
        containerJogo.addComponent(constraints,numeroDePartidasLB); 
        
        tLayout.setGrowHorizontally(true);
        constraints = tLayout.createConstraint(5, 1);        
        constraints.setHorizontalSpan(2); 
        containerJogo.addComponent(constraints,numeroDePartidasCMB);
    }
    
}
