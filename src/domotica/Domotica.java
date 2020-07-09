/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domotica;

import controiador.Controlador;
import modelo.AC;
import modelo.AdminCasa;
import modelo.AdminDisp;
import modelo.AdminUsuarios;
import modelo.Area;
import modelo.Casa;
import modelo.Foco;
import modelo.Habitacion;
import modelo.TV;
import modelo.Usuario;
import vista.domotica;

/**
 *
 * @author ciber
 */
public class Domotica {
    
    public static void main(String[] args) {
    Casa casa = new Casa("prueba");
    AdminCasa adminCasa = new AdminCasa();
    domotica vista = new domotica();
    AdminUsuarios adminUsuario = new AdminUsuarios();
    AdminDisp adminDisp = new AdminDisp();
    Controlador controlador = new Controlador();
    controlador.setCasa(casa);
    controlador.setAdminDisp(adminDisp);
    controlador.setAdminUsuarios(adminUsuario);
    controlador.setAdminCasa(adminCasa);
    controlador.setVista(vista);
    while(controlador.login.isShowing()){
        
    }
    vista.setVisible(true);
    }
    
    }
    

