/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author ciber
 */
public class AdminUsuarios {
 public int total_Usuarios;
 public HashMap<Integer,Usuario> usuarios = new HashMap<>();
 public Usuarios usuarioActivo;
 
 public void añadirUsuario(String tipo,String nombre,String contra){
     Usuario usuario;
     if (tipo.equals("administrador")){
         usuario=new Administrador(nombre,contra);
         
     }else if (tipo.equals("residente")){
         usuario=new Residente(nombre,contra);     
     }else{
         usuario=new Invitado(nombre,contra);
     }
     usuarios.put(usuario.getId(),usuario);
     total_Usuarios++;
 }

    public Usuario getUsuario(int usuarioId) {
        return usuarios.get(usuarioId);
    }
    public void setUsuarioActivo(Usuarios usuario){
        usuarioActivo = usuario;
    }
    public int getId(String usuario){
        Set<Integer> keys = usuarios.keySet();
         for(Integer key:keys){
             if(usuario.equals(getUsuario(key).getNombre())){
                 return getUsuario(key).getId();
             }
        
    }
     return 0;
    
    } 
    public String tipoSesion(){
        return usuarioActivo.getTipo();
    }
        
}
