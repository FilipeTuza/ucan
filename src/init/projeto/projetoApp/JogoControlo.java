/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

/**
 *
 * @author moreno angola
 */
public class JogoControlo 
{
    private SenarioDoJogo senario;
    private SistemaEstado sistema;
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador cPU;
    private String jogadasEfectuadas;
    private int numeroDePartidas;

    public JogoControlo(SenarioDoJogo senario, SistemaEstado sistema) 
    {
        this.senario = senario;
        this.sistema = sistema;
        jogador1 = sistema.getJogador1();
        jogador2 = sistema.getJogador2(); 
        cPU  = new AndroidCPU(senario);
        cPU.setNome(jogador2.getNome());
        cPU.setCaracter(jogador2.getCaracter());
        numeroDePartidas = sistema.getNumeroDePartidas();
        jogadasEfectuadas = "";
    }
    
    public JogoControlo( SenarioDoJogo senario ) 
    {
        this (senario,senario.getSistema());         
    }

    
    
    public String getJogadasEfectuadas() {
        return jogadasEfectuadas;
    }

    public void setJogadasEfectuadas(String jogadasEfectuadas) {
        this.jogadasEfectuadas = jogadasEfectuadas;
    }

    public int getNumeroDePartidas() {
        return numeroDePartidas;
    }

    public void setNumeroDePartidas(int numeroDePartidas) {
        this.numeroDePartidas = numeroDePartidas;
    }
    
    public SenarioDoJogo getSenario() {
        return senario;
    }

    public void setSenario(SenarioDoJogo senario) {
        this.senario = senario;
    }

    public AndroidCPU getcPU() {
        return (AndroidCPU) cPU;
    }

    public void setcPU(AndroidCPU cPU) {
        this.cPU = cPU;
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

    public SistemaEstado getSistema() {
        return sistema;
    }

    public void setSistema(SistemaEstado sistema) {
        this.sistema = sistema;
    }

    
    
    public boolean jogadorVenceu ( char caracter )
    {
        Jogador aux ;
       if( sistema.getModalidade() == Defs.JOGADOR1_VS_JOGADOR2)
       {
            aux = (jogador1.getCaracter() == caracter )? jogador1 : jogador2;
       }
       else 
       {
           aux = (jogador1.getCaracter() == caracter )? jogador1 : cPU;
       }
        return Utils.temVitoria( aux.getJogadas() );
    }
        
    public Jogador getVencedor ()
    {
        if( jogadorVenceu(jogador1.getCaracter()))
        {
            return jogador1;
        }
        else if( jogadorVenceu(jogador2.getCaracter()))
        {
            return jogador2;
        }
        
        return null;
    }
    
    public boolean fimDoJogo ()
    {
        boolean fim =(sistema.getModalidade() == Defs.JOGADOR1_VS_JOGADOR2)?
         ( senario.getTotalJogadas() == Defs.MAX_QUADRANTES) || 
           jogadorVenceu(jogador1.getCaracter()) || 
           jogadorVenceu(jogador2.getCaracter()) :
                
           ( senario.getTotalJogadas() == Defs.MAX_QUADRANTES) || 
           jogadorVenceu(jogador1.getCaracter()) || 
           jogadorVenceu(cPU.getCaracter()) ;
        
        return fim;
    }
}
