
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

/**
 *
 * @author moreno angola
 */
public class SistemaEstado 
{
    private int modalidade;
    private int numeroDePartidas; 
    
    private Jogador jogador1;
    private Jogador jogador2;
    
    private int corFundoSenario ;
    private int corCaracterX;
    private int corCaracterO;    

    
    public SistemaEstado ()
    {
        this
        ( 
            Defs.JOGADOR1_VS_JOGADOR2,Defs.NUMERO_PARTIDAS_PADRAO ,Defs.CHAR_X,Defs.CHAR_O,
            new Jogador(0, Defs.NOME_PADRAO_1, Defs.CHAR_X, ""),new Jogador(0, Defs.NOME_PADRAO_2, Defs.CHAR_O, "")
        );
    }

    public SistemaEstado ( FormPrincipal principal )
    {
        this(
                 principal.getModalidadeRB(),
                 
                 principal.getNumeroDePartidasCMB(),
                 
                 principal.getJogador1().getCaracter(),
                 
                 principal.getJogador2().getCaracter(),
                 
                 principal.getJogador1(), 
                 
                 principal.getJogador2()

            );        
    }
    
    public SistemaEstado(int modalidade,int numeroDePartidas, char caracterJogador1, char caracterJogador2, Jogador jogador1, Jogador jogador2) 
    {
        this.modalidade = modalidade;
        this.numeroDePartidas = numeroDePartidas;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.jogador1.setCaracter ( caracterJogador1 );
        this.jogador2.setCaracter ( caracterJogador2 );
    }

    public int getCorFundoSenario() {
        return corFundoSenario;
    }

    public void setCorFundoSenario(int corFundoSenario) {
        this.corFundoSenario = corFundoSenario;
    }

    public int getCorCaracterX() {
        return corCaracterX;
    }

    public void setCorCaracterX(int corCaracterX) {
        this.corCaracterX = corCaracterX;
    }

    public int getCorCaracterO() {
        return corCaracterO;
    }

    public void setCorCaracterO(int corCaracterO) {
        this.corCaracterO = corCaracterO;
    }
    
    public int getNumeroDePartidas() {
        return numeroDePartidas;
    }

    public void setNumeroDePartidas(int numeroDePartidas) {
        this.numeroDePartidas = numeroDePartidas;
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

    public void setJogador2(Jogador jogador2) 
    {
        this.jogador2 = jogador2;
    }
    
    public int getModalidade() 
    {
        return modalidade;
    }

    public void setModalidade(int modalidade) 
    {
        this.modalidade = modalidade;
    }

    
    
}
