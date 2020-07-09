package modelo;
// Generated 09-jul-2020 12:30:16 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Habitaciones generated by hbm2java
 */
public class Habitaciones  implements java.io.Serializable {


     private HabitacionesId id;
     private Areas areas;
     private String habitacion;
     private Set dispositivoses = new HashSet(0);

    public Habitaciones() {
    }

	
    public Habitaciones(HabitacionesId id, Areas areas, String habitacion) {
        this.id = id;
        this.areas = areas;
        this.habitacion = habitacion;
    }
    public Habitaciones(HabitacionesId id, Areas areas, String habitacion, Set dispositivoses) {
       this.id = id;
       this.areas = areas;
       this.habitacion = habitacion;
       this.dispositivoses = dispositivoses;
    }
   
    public HabitacionesId getId() {
        return this.id;
    }
    
    public void setId(HabitacionesId id) {
        this.id = id;
    }
    public Areas getAreas() {
        return this.areas;
    }
    
    public void setAreas(Areas areas) {
        this.areas = areas;
    }
    public String getHabitacion() {
        return this.habitacion;
    }
    
    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }
    public Set getDispositivoses() {
        return this.dispositivoses;
    }
    
    public void setDispositivoses(Set dispositivoses) {
        this.dispositivoses = dispositivoses;
    }




}

