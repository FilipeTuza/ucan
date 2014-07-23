/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

import com.codename1.ui.Display;

/**
 *
 * @author moreno angola
 */
public class Defs 
{
    public static final int LARGURA_TELA = Display.getInstance().getDisplayWidth();
    public static final int ALTURA_TELA = Display.getInstance().getDisplayHeight();
    
    public static int QUAD_INTERVALO = 95;
    
    public static int O_QUAD_I_X = Utils.getXCentroEcran() - 120;
    public static int O_QUAD_I_Y = Utils.getYCentroEcran() - 120;     
    
    public static int X_QUAD_I_X = O_QUAD_I_X;
    public static int X_QUAD_I_Y = O_QUAD_I_Y - 15;
    

    
    public static int MAX_QUADRANTES = 9;
    
    public static int LARGURA_PADRAO_CARACTER_O = 65;
    public static int ALTURA_PADRAO_CARACTER_O = 65;
    public static int COR_PADRAO_CARACTER_O = 0x22ff32;

    public static int X_PADRAO_CARACTER_O = O_QUAD_I_X;
    public static int Y_PADRAO_CARACTER_O = O_QUAD_I_Y;    
    public static int ESPESSURA_PADRAO_CARACTER_O = 10;
    
    public static int X_PADRAO_CARACTER_X = X_PADRAO_CARACTER_O;
    public static int Y_PADRAO_CARACTER_X = Defs.Y_PADRAO_CARACTER_O - 15;   
    public static int LARGURA_PADRAO_CARACTER_X = 5;
    public static int ALTURA_PADRAO_CARACTER_X = 70;   
    public static int COR_PADRAO_CARACTER_X = 0x321ff2;    

    public static int JOGADOR1_VS_SISTEMA = 1;   
    public static int JOGADOR1_VS_JOGADOR2 = 2;
    public static int NUMERO_PARTIDAS_PADRAO = 1;
        
    public static String VITORIA_H1 = "123";    
    public static String VITORIA_H2 = "456";    
    public static String VITORIA_H3 = "789";      

    public static String VITORIA_V1 = "147";    
    public static String VITORIA_V2 = "258";    
    public static String VITORIA_V3 = "369"; 
    
    public static String VITORIA_D1 = "159";    
    public static String VITORIA_D2 = "357";  
    
    public static String DECOD_ASCII = "48";  
    
    public static char CHAR_X = 'x';   
    public static char CHAR_O = 'o';    
    
    public static String NOME_PADRAO_1 = "Jogador 1";   
    public static String NOME_PADRAO_2 = "Jogador 2"; 
    public static String NOME_SISTEM_1 = "Android -";  
    
    public static char INITIAL_CHAR = 'x';

    public static int AMARELA_1 = 0, AMARELA_2 = 1;//AMARELA 1 E 2
    public static int AZUL_1 = 2,AZUL_2 = 3;//AZUL 1 E 2
    public static int BRANCO = 4;//BRANCO 1 
    public static int CINZA_1 = 5,CINZA_2 = 6,CINZA_3 = 7;//CINZA 1 , 2 E 3
    public static int LARANJA_1 = 8,LARANJA_2 = 9;//LARANJA 1 E 2
    public static int PRETA = 10;//PRETA 1   
    public static int ROSA_1 = 11,ROSA_2 = 12;//ROSA 1 E 2     
    public static int VIOLETA_1 = 13, VIOLETA_2 = 14;//VIOLETA 1 E 2     
    public static int VERDE_1 = 15,VERDE_2 = 16;//VERDE 1 E 2   
    public static int VERMELHO_1 = 17,VERMELHO_2 = 18;//VERMELHO 1 E 2      

    
    public static int CORES [] = new int []
    {
        0xFFFF66,0xFFFF00,//AMARELA 1 E 2
        0x0033CC,0x339900,//AZUL 1 E 2
        0xFFFFFF,//BRANCO 1 
        0x666666,0x999999,0xCCCCCC,//CINZA 1 , 2 E 3
        0xFF9900,0xFFCC00,//LARANJA 1 E 2
        0x000000,//PRETA 1   
        0xFF0099,0xFF99FF,//ROSA 1 E 2     
        0xCC00CC,0xCC66CC,//VIOLETA 1 E 2     
        0x33CC00,0x339900,//VERDE 1 E 2   
        0xFF0000,0xFF0066,//VERMELHO 1 E 2        
    };  

    
}
