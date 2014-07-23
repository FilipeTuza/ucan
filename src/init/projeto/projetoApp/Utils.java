/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

import com.codename1.ui.Image;
import java.io.IOException;

/**
 *
 * @author moreno angola
 */
public class Utils 
{
    
    public static int getXCentroEcran ()
    {
        return Defs.LARGURA_TELA/2;
    }
    
    public static int getYCentroEcran ()
    {
        return Defs.ALTURA_TELA/2;
    }
    
    public static double perecent ( double percent, double qtd )
    {
        return qtd * ( percent / 100 ) ;
    }    
    
    public static Ponto getCentro ( int largura, int altura )
    {
        return new Ponto ( largura /2 ,altura /2);
    }
    
    public static int getXCentro ( int largura, int altura )
    {
        return getCentro ( largura, altura ).getX();
    }
    
    public static int getYCentro ( int largura, int altura )
    {
        return getCentro ( largura, altura ).getY();
    }    
    
  
    public static boolean contem ( String contem , String estaContida )
    {        
        for ( int i = 0; i < estaContida.toCharArray().length; i++)
        {
            if ( contem.indexOf( estaContida.charAt( i ) ) == -1 )
            {
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean temVitoria ( String jogadas )
    {
        if ( contem ( jogadas, Defs.VITORIA_H1 ))
        {
            return true;
        }
        else if ( contem ( jogadas, Defs.VITORIA_H2 ))
        {
            return true;
        }  
        else if ( contem ( jogadas, Defs.VITORIA_H3 ))
        {
            return true;
        }   
        
        
        else if ( contem ( jogadas, Defs.VITORIA_V1 ))
        {
            return true;
        }  
        else if ( contem ( jogadas, Defs.VITORIA_V2 ))
        {
            return true;
        }      
        else if ( contem ( jogadas, Defs.VITORIA_V3 ))
        {
            return true;
        }   
        
        else if ( contem ( jogadas, Defs.VITORIA_D1 ))
        {
            return true;
        }      
        return contem ( jogadas, Defs.VITORIA_D2 );
    }
    
    public static int [] criaArreiDeInteiros ( String string )
    {
        int prox = 0;
        int [] inteiros = new int [ string.length() ] ;
        
        for ( char c : string.replace(';', ' ').trim().toCharArray() )
        {
            if (Character.isDigit(c))
            {
                inteiros [ prox ] = c - 48;
                
                prox ++;
            }
        }
        
        return inteiros;
    }
    
    public static String [] getTodasVitorias()
    {
        return new String[] 
        {
            Defs.VITORIA_H1, Defs.VITORIA_H2 ,Defs.VITORIA_H3 ,Defs.VITORIA_V1,
            Defs.VITORIA_V2 ,Defs.VITORIA_V3 , Defs.VITORIA_D1 ,Defs.VITORIA_D2        
        };
    }  
       
    public static boolean estaContido ( int aux,int [] itens )
    {
            for ( int j = 0; j < itens.length; j ++ )
            {
                    if ( itens[ j ] == aux )
                    {
                            return true;
                    }
            }

            return false;
    }

    public static int complemento( int [] aux,int i1,int i2 )
    {
            int emFalta = -1;

                    if ( estaContido(i1, aux ))
                            if ( estaContido(i2, aux ))
                            {
                                    for (int i = 0; i < aux.length; i++) 
                                    {
                                            if ( aux [ i ] != i1 && aux [ i ] != i2 )
                                            {
                                                    return aux [ i ];
                                            }
                                    }
                            }

            return emFalta;
    }  
    
    public static int complementoGenerico ( int i1,int i2)
    {
            int alt [][] = { {1,2,3}, {4,5,6}, {7,8,9},{1,4,7}, {2,5,8}, {3,6,9},{1,5,9}, {3,5,7} };

            int j = -1;

            for (int[] aux : alt) 
            {
                    if ( ( j = complemento(  aux, i1,i2 ) ) != -1 )
                    {
                            return j;
                    }
            }

            return j;
    }
        
    public static Image buscaImagem ( String path)
    {
        try 
        {
            return Image.createImage(path);
        } catch (IOException ex) 
        {
            
        }
        return null;
    }
    
    public static boolean iguais ( int [] array1,int [] array2)
    {
        if ( array1.length == array2.length )
        {
            for (int i = 0; i < array2.length; i++) 
            {
                if (array1[i] != array2[i] )
                {
                    return false;
                }
                
            }
        }
        
        return true;
    }
    
}
