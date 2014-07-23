/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package init.projeto.projetoApp;

/**
 *
 * @author moreno angola
 */
public class Quadrante 
{
    private int codigo;
    private Caracter carater;
    private boolean ocupado;

    public Quadrante() 
    {
        this (0,null,false);
    }
    public Quadrante( int codigo ) 
    {
        this (codigo,null,false);
    }
    
    public Quadrante(int codigo, Caracter carater, boolean ocupado) 
    {
        this.codigo = codigo;
        this.carater = carater;
        this.ocupado = ocupado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Caracter getCarater() {
        return carater;
    }

    public void setCarater(Caracter carater) {
        this.carater = carater;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    
    @Override
    public String toString ()
    {
        return "\n Quadrante : "+codigo +
               "\n Caracter :"+ carater +
               "\n Ocupado : " + ocupado; 
    }
}
