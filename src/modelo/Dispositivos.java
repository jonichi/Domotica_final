package modelo;
// Generated 09-jul-2020 12:30:16 by Hibernate Tools 4.3.1



/**
 * Dispositivos generated by hbm2java
 */
public class Dispositivos  implements java.io.Serializable {


     private DispositivosId id;
     private Habitaciones habitaciones;
     private String marca;
     private String modelo;
     private String tipo;

    public Dispositivos() {
    }

    public Dispositivos(DispositivosId id, Habitaciones habitaciones, String marca, String modelo, String tipo) {
       this.id = id;
       this.habitaciones = habitaciones;
       this.marca = marca;
       this.modelo = modelo;
       this.tipo = tipo;
    }
   
    public DispositivosId getId() {
        return this.id;
    }
    
    public void setId(DispositivosId id) {
        this.id = id;
    }
    public Habitaciones getHabitaciones() {
        return this.habitaciones;
    }
    
    public void setHabitaciones(Habitaciones habitaciones) {
        this.habitaciones = habitaciones;
    }
    public String getMarca() {
        return this.marca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }




}


