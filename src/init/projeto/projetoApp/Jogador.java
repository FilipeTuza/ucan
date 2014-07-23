/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

/**
 *
 * @author moreno angola
 */
public class Jogador 
{
    protected int vitorias;
    protected String nome;
    protected char caracter;
    protected String jogadas;

    public Jogador()
    {
        this( 0 , "", 'c' ,"" ) ;
    }
    
    public Jogador(int vitorias, String nome, char caracter, String jogadas) 
    {
        this.vitorias = vitorias;
        this.nome = nome;
        this.caracter = caracter;
        this.jogadas = jogadas;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public String getJogadas() {
        return jogadas;
    }

    public void setJogadas(String jogadas) {
        this.jogadas = jogadas;
    }

    public void addJogada ( int jogada )
    {
        jogadas += jogada;
    }
    
    @Override
     public String toString ()
     {
         return       
         "\n\tNome " + nome +
         "\n\tCaracter " + caracter +                 
         "\n\tVitorias "+ vitorias +
         "\n\tJogadas " + jogadas;
     }
    
    
}
