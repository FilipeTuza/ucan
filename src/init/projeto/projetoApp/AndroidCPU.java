/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

import java.util.Random;

public class AndroidCPU extends Jogador
{
    private SenarioDoJogo senario;
    private int bloqueio;
    private int jogadaVitoria ;
    
    private int limites[];
    private int vitoriasPossiveis[][] ;
    private int limLivre;
    
    public AndroidCPU ( SenarioDoJogo senario )
    {
        this.senario = senario;
        this.caracter = senario.getSistema().getJogador2().getCaracter();
        bloqueio = 0;
        jogadaVitoria = 0;
        limLivre = 0;
        limites  = new int [ Defs.MAX_QUADRANTES];
        jogadas = "";
        vitoriasPossiveis = new int[][]{ {1,2,3}, {4,5,6}, {7,8,9},{1,4,7}, {2,5,8}, {3,6,9},{1,5,9}, {3,5,7} };
    }

    public SenarioDoJogo getSenario() {
        return senario;
    }

    public void setSenario(SenarioDoJogo senario) {
        this.senario = senario;
    }

    public int getBloqueio() {
        return bloqueio;
    }

    public void setBloqueio(int bloqueio) {
        this.bloqueio = bloqueio;
    }

    public int getJogadaVitoria() {
        return jogadaVitoria;
    }

    public void setJogadaVitoria(int jogadaVitoria) {
        this.jogadaVitoria = jogadaVitoria;
    }
    
    public int defesa ( String jogadasAdversarias )
    {
        int [] aux = Utils.criaArreiDeInteiros(jogadasAdversarias);
        
        int resposta = -1;
        int cont = 0;
        int k = 1;
        
        if ( aux.length > 0 )
        {
            int j = aux[0];
            
            for (int i = 0; i < aux.length - 1; i++) 
            {
                if ( detectarPerigo(aux [ i ], aux [ i + 1 ]) )
                {
                    do
                    {
                        resposta =  Utils.complementoGenerico( aux [ i ], aux [ (i + k++)%aux.length ] );
                        
                    }
                    while ( quadranteOcupado( resposta ) && cont++ < (Defs.MAX_QUADRANTES * 2) + 1 );
                }
            }
        }
        return resposta;
    }
    
    public int ataque ( String jogadas )
    {
        int [] aux = Utils.criaArreiDeInteiros(jogadas);
        int proximo = defesa ( jogadas );

        int cont = 0;
        Random rnd = new Random();
        
        if (( senario.getControlo().getJogadasEfectuadas().length() == 0 ) || aux.length == 0 )
        {
            proximo = rnd.nextInt(9);
        }
        else if ( proximo == -1 )
        {
            
            if ( aux.length == 1 )
            {
               for ( int [] vitoria : vitoriasPossiveis )
               {
                   if ( Utils.estaContido( aux[ 0 ], vitoria) ) 
                   {
                       int k = 0 ;
                       int op;
                       
                       while ( vitoria[ op = k++ ] == aux[ 0 ] && k <= 2 );
                       
                       proximo = Utils.complementoGenerico( aux[ 0 ], vitoria[op] );
                       
                       if ( !quadranteOcupado( proximo ))
                       {
                           break;
                       }

                   }
               }
            }
            else if ( aux.length > 1 )
            {
                for ( int i = 0; i < aux.length - 1; i++ )
                {
                    for ( int j = 0; j < aux.length; j++ )
                    {
                       proximo = Utils.complementoGenerico(aux [i], aux[j]);
                        
                       if ( !quadranteOcupado( proximo ))
                       {
                           break;
                       }
                    }
                }
            }
        }
        
        return proximo;
    }
    
    public int buscaQuadranteDesocupado ()
    {
        int i = 1;
        int cont = 9 ;
        return 0;
    }
    
    public boolean quadranteOcupado ( int q )
    {   
        int [] partidasEfectuadas = Utils.criaArreiDeInteiros( senario.getControlo().getJogadasEfectuadas());
        
        for ( int aux : partidasEfectuadas )
        {
            if ( aux == q )
            {
                return true;
            }
        }
        
        return false;
    }
    
    public boolean detectarPerigo (int i1,int i2)
    {
        for (int i = 0; i < vitoriasPossiveis.length; i++) 
        {
                if ( Utils.estaContido(i1, vitoriasPossiveis [i]))
                        if ( Utils.estaContido(i2, vitoriasPossiveis [i]))
                        {
                                return true;
                        }
        }

        return false;
    }
    
    public void removerJogadasCompletas ( String partidasRealizadas )
    {
        int [][]todasVitorias = { {1,2,3}, {4,5,6}, {7,8,9},{1,4,7}, {2,5,8}, {3,6,9},{1,5,9}, {3,5,7} };
        
        int [] aux = Utils.criaArreiDeInteiros( partidasRealizadas );
        String auxStr = "";
        if ( aux.length > 2 )
        {
            for (int i = 0; i < aux.length - 2;i ++)
            {
                for (int j = i + 1; j < aux.length - 1; j++)
                {
                    for (int k = j + 1; k < aux.length; k++) 
                    {
                        for ( int [] unidade : vitoriasPossiveis )
                        {
                            if ( Utils.contem( unidade[0]+""+unidade[0]+""+unidade[0], aux[i]+"" +aux[i]+"" +aux[i]))
                            {
                                remover (  unidade );
                            }
                        }
                    }

                }

            }
        }
    }
    
    
    public void remover ( int [] unidade )
    {
        for (int i = 0; i < vitoriasPossiveis.length; i++) 
        {
            if ( Utils.iguais(vitoriasPossiveis [i], unidade ))
            {
                for (int j = i; j < vitoriasPossiveis.length - 1; j++) 
                {
                    vitoriasPossiveis [j] = vitoriasPossiveis [j+1];
                }
            }
        }
        
    }    
    
}
