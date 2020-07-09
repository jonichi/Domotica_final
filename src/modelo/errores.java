/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.JTextField;

/**
 *
 * @author ciber
 */
public class errores {
    
    public errores(){
        
    }
    public boolean comprobar(int opciones){
        return opciones !=0;
    }
    
    public boolean comprobar(String cadena){
        if(cadena==null ) return false;
        return !cadena.equals("");    
    }

    public boolean comprobar(JTextField marca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
