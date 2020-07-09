/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.Set;

public class Area{
    private int numHabitaciones;
    private int habitacionFinal;
    private String area;
    private static int areaId;
    private int idA;
    public HashMap<Integer,Habitacion> habitaciones = new HashMap<>();

    public Area(String areaName){
        areaId++;
        idA=areaId;
    	this.area=areaName;
    }

    public void setAreaName(String areaName){
    	this.area=areaName;
    }

    public String getAreaName(){
    	return area;
    }

    public int getId(){
    	return idA;
    }
    public Habitacion getHabitacion(int habitacionId){
        return habitaciones.get(habitacionId);
    }
     public Habitacion getHabitacion(String habitacionName){
        Set<Integer> keys = habitaciones.keySet();
         for(Integer key:keys){
             if(habitacionName.equals(getHabitacion(key).getNombre())){
                 return getHabitacion(key);
             }
            
        }
     return null;
    }

    public void addHabitacion(Habitacion newHabitacion){
    	habitaciones.put(newHabitacion.getId(), newHabitacion);
        numHabitaciones++;
        habitacionFinal=newHabitacion.getId();
    }

    public boolean removeHabitacion(int habitacionId){
    	if(habitaciones.get(habitacionId) != null){
    		habitaciones.remove(habitacionId);
                return true;
    	}else{
            return false;
        }
    }
    
    public void apagar(){
        Set<Integer> keys = habitaciones.keySet();
         for(Integer key:keys){
            getHabitacion(key).apagar();
        }
         System.out.println(getAreaName()+" habitaciones apagadas");
        }
    
    
    public void encender(){
         Set<Integer> keys = habitaciones.keySet();
         for(Integer key:keys){
            getHabitacion(key).encender();
        }
         System.out.println(getAreaName()+" habitaciones apagadas");
    
    }
    public String[] getHNames(){
        Set<Integer> keys = habitaciones.keySet();
        int i= 0;
        String[] name= new String[keys.size()];
         for(Integer key:keys){
                name[i]= getHabitacion(key).getNombre();
                i++;
             }
       return name;     
    }
}