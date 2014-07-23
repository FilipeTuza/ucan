/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

/**
 *
 * @author moreno angola
 */
public class Ponto 
{
    int x,y;

    public Ponto() 
    {
        this ( 0, 0);
    }
    
    public Ponto(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public String toString ()
    {
        return " [ " + x +" ; "+y +" ]\n";
    }
}
