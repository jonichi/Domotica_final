/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controiador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import modelo.*;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;    
import org.hibernate.Transaction;
import vista.AgregarArea;
import vista.AgregarDisp;
import vista.AgregarHabitacion;
import vista.AgregarUsuario;
import vista.Login;
import vista.domotica;

/**
 *
 * @author ciber
 */
public class Controlador implements ActionListener {
    private Casa casa;
    private domotica vista;
    private AdminCasa adminCasa;
    private AdminDisp adminDisp;
    private AdminUsuarios adminUsuarios;
    private String areasName[]= new String[0];
    private String habName[]= new String[0];
    private errores error = new errores();
    public Login login = new Login();

     public Controlador() {
     
              
    }
     public void altaArea(Areas area){
      SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session = sesion.openSession();
      Transaction tx = session.beginTransaction();
      session.save(area);
      tx.commit();
      session.close();
         
     }
     public void altaHabitacion(Habitaciones habitacion){
      SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session = sesion.openSession();
      Transaction tx = session.beginTransaction();
      session.save(habitacion);
      tx.commit();
      session.close();
         
     }
     public void altaDisp(Dispositivos dispositivo){
      SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session = sesion.openSession();
      Transaction tx = session.beginTransaction();
      session.save(dispositivo);
      tx.commit();
      session.close();
         
     }
      public void altaUsuarios(Usuarios usuario){
      SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session = sesion.openSession();
      Transaction tx = session.beginTransaction();
      session.save(usuario);
      tx.commit();
      session.close();
         
     }
     public Areas buscararea(int id){
         Areas ar;
         SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session = sesion.openSession();
      Transaction tx = session.beginTransaction();
      ar=(Areas)session.get(Areas.class,id);
      tx.commit();
      session.close();
      
      return ar;
     }
     public Habitaciones buscarHabitacion(int idH,int idA){
         Habitaciones habitacion;
         SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session = sesion.openSession();
      Transaction tx = session.beginTransaction();
     HabitacionesId id = new HabitacionesId(idH,idA); 
     habitacion=(Habitaciones)session.get(Habitaciones.class,id);
      tx.commit();
      session.close();
      
      return habitacion;
     }
     
     public void iniAreas(){
      SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session = sesion.openSession();
      Transaction tx = session.beginTransaction();
      Query q = session.createQuery("from Areas");
      List <Areas>lista = q.list();
      Iterator<Areas>iter=lista.iterator();
      tx.commit();
      session.close();
      while(iter.hasNext()){
          Areas area = (Areas)iter.next();
          int i = areasName.length+1;
      String[] newArray = new String[i];  
       System.arraycopy(areasName, 0, newArray, 0, areasName.length);
          newArray[i-1]=area.getArea();
          areasName = newArray;
          vista.cargarAreas(areasName);
          adminCasa.agregarArea(casa,area.getArea());
      }
     }
      public void iniHabitaciones(){
      SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session = sesion.openSession();
      Transaction tx = session.beginTransaction();
      Query q = session.createQuery("from Habitaciones");
      List <Habitaciones>lista = q.list();
      Iterator<Habitaciones>iter=lista.iterator();
      tx.commit();
      session.close();
      while(iter.hasNext()){
          Habitaciones habitacion = (Habitaciones)iter.next();
          
          adminCasa.agregarHabitacion(casa,adminCasa.getArea(casa,habitacion.getId().getIdA()),habitacion.getHabitacion());
      }
     }
       public void iniDisp(){
      SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session = sesion.openSession();
      Transaction tx = session.beginTransaction();
      Query q = session.createQuery("from Dispositivos");
      List <Dispositivos>lista = q.list();
      Iterator<Dispositivos>iter=lista.iterator();
      tx.commit();
      session.close();
      String areaName;
      String habitacionName;
      while(iter.hasNext()){
          Dispositivos dispositivo = (Dispositivos)iter.next();
          areaName=adminCasa.getArea(casa, dispositivo.getId().getIdA());
          habitacionName=adminCasa.getHabitacionName(casa, areaName,dispositivo.getId().getIdH());
          Dispositivo disp = null;
          if(dispositivo.getTipo().equals("AC")){
         disp = new AC(dispositivo.getMarca(),dispositivo.getModelo());
     }else if(dispositivo.getTipo().equals("TV")){
        disp = new TV(dispositivo.getMarca(),dispositivo.getModelo());
     }else if(dispositivo.getTipo().equals("Bocina")){
          disp = new Bocina (dispositivo.getMarca(),dispositivo.getModelo());
     }else if(dispositivo.getTipo().equals("Foco")){
         disp = new Foco(dispositivo.getMarca(),dispositivo.getModelo());
     }
      adminDisp.agregarDisposp(casa,areaName,habitacionName,disp);
      }
     }
       public void iniUsuarios(){
      SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session = sesion.openSession();
      Transaction tx = session.beginTransaction();
      Query q = session.createQuery("from Usuarios");
      List <Usuarios>lista = q.list();
      Iterator<Usuarios>iter=lista.iterator();
      tx.commit();
      session.close();
      while(iter.hasNext()){
          Usuarios usuario = (Usuarios)iter.next();
          adminUsuarios.añadirUsuario(usuario.getTipo(), usuario.getNombre(),usuario.getContra());
          
      }
     }
    public void setVista(domotica vista) {
        this.vista = vista;
        this.vista.offArea.addActionListener(this);
        this.vista.boxArea.addActionListener(this);
        this.vista.boxDispositivo.addActionListener(this);
        this.vista.boxHabitacion.addActionListener(this);
        this.vista.menuArea.addActionListener(this);
        this.vista.menuDispositivo.addActionListener(this);
        this.vista.menuHabitacion.addActionListener(this);
        this.vista.menuUsuario.addActionListener(this);
        this.vista.offCasa.addActionListener(this);
        this.vista.offDispositivo.addActionListener(this);
        this.vista.onArea.addActionListener(this);
        this.vista.offHabitacion.addActionListener(this);
        this.vista.onCasa.addActionListener(this);
        this.vista.onDispositivo.addActionListener(this);
        this.vista.onHabitacion.addActionListener(this);
         iniAreas();
         iniHabitaciones();
         iniDisp();
         iniUsuarios();
         login.setVisible(true);
         this.login.salir.addActionListener(this);
         this.login.iniciar.addActionListener(this);
                 
    }

    public void setAdminCasa(AdminCasa adminCasa) {
        this.adminCasa = adminCasa;
    }

    public void setAdminDisp(AdminDisp adminDisp) {
        this.adminDisp = adminDisp;
    }

    public void setAdminUsuarios(AdminUsuarios adminUsuarios) {
        this.adminUsuarios = adminUsuarios;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource()==vista.menuArea ){
        if(adminUsuarios.tipoSesion().equals("Administrador")){
        AgregarArea dialogo = new AgregarArea(vista,true);
        dialogo.setVisible(true);
        if(error.comprobar(dialogo.area)){
        int i = areasName.length+1;
        String[] newArray = new String[i];  
        System.arraycopy(areasName, 0, newArray, 0, areasName.length);  
        newArray[i-1]=dialogo.area;
        areasName = newArray;
        vista.cargarAreas(areasName);
        adminCasa.agregarArea(casa,dialogo.area);
        Areas area= new Areas(adminCasa.getAreaid(casa,dialogo.area),dialogo.area);
        altaArea(area);
        }else{
            JOptionPane.showMessageDialog(null,"area no agregada ingrese los datos completos");
        }
        }else{
            JOptionPane.showMessageDialog(null,"el usuario no cuenta con los permisos para acceder a esta funcionalidad");
        
        }
    }
    if(e.getSource()==vista.menuHabitacion){
        if(adminUsuarios.tipoSesion().equals("Administrador")){
        AgregarHabitacion dialogoH = new AgregarHabitacion(vista,true);
        dialogoH.cargarAreas(areasName);
        dialogoH.setVisible(true);
        if(error.comprobar(dialogoH.text.getText())){
        String areaN = dialogoH.boxAreas.getSelectedItem().toString();
        String habitacionN = dialogoH.text.getText();
        System.out.println(areaN);
        adminCasa.agregarHabitacion(casa,areaN, habitacionN);
        HabitacionesId id = new HabitacionesId(adminCasa.getHabitacionId(casa, areaN, habitacionN),adminCasa.getAreaid(casa, areaN));
   
        Habitaciones habitacion = new Habitaciones(id,buscararea(adminCasa.getAreaid(casa, areaN)),habitacionN);
        altaHabitacion(habitacion);
        }else{
        JOptionPane.showMessageDialog(null,"habitacion no agregada ingrese los datos completos");

        }
        }else{
            JOptionPane.showMessageDialog(null,"el usuario no cuenta con los permisos para acceder a esta funcionalidad");
        }
    }
    if(e.getSource()==vista.boxArea){
      vista.cargarHabitaciones(casa.getArea(vista.boxArea.getSelectedItem().toString()).getHNames());
    }
    if(e.getSource()== vista.menuDispositivo){
    if(adminUsuarios.tipoSesion().equals("Invitado")){
    JOptionPane.showMessageDialog(null,"el usuario no cuenta con los permisos para acceder a esta funcionalidad");
    }else{
        AgregarDisp dialogoD = new AgregarDisp(vista,true);
     dialogoD.cargarAreas(areasName);
     controladorD cont = new controladorD(dialogoD,casa);
     dialogoD.setVisible(true);
     String model, marca;
     model=dialogoD.modelo.getText();
     marca=dialogoD.marca.getText();
     if(error.comprobar(marca)&&error.comprobar(model)&&error.comprobar(dialogoD.boxh.getItemCount())){
     Dispositivo disp = null;
     if(dialogoD.boxd.getSelectedItem().toString().equals("AC")){
         disp = new AC(marca,model);
     }else if(dialogoD.boxd.getSelectedItem().toString().equals("TV")){
        disp = new TV(marca,model);
     }else if(dialogoD.boxd.getSelectedItem().toString().equals("Bocina")){
          disp = new Bocina (marca,model);
     }else if(dialogoD.boxd.getSelectedItem().toString().equals("Foco")){
         disp = new Foco(marca,model);
     }
    String AreaName= dialogoD.boxA.getSelectedItem().toString();
    String HabitacionName= dialogoD.boxh.getSelectedItem().toString();
    adminDisp.agregarDisposp(casa,AreaName,HabitacionName,disp);
    DispositivosId dispositivoid= new DispositivosId(adminCasa.getDispositivoId(casa,AreaName,HabitacionName, model),adminCasa.getHabitacionId(casa,AreaName, HabitacionName),adminCasa.getAreaid(casa, AreaName));
    Dispositivos dispositivoServidor = new Dispositivos(dispositivoid,buscarHabitacion(adminCasa.getHabitacionId(casa,AreaName, HabitacionName),adminCasa.getAreaid(casa, AreaName)),marca,model,adminCasa.getDispositivo(casa, AreaName, HabitacionName, model).getClass().getSimpleName());
    altaDisp(dispositivoServidor);
     }else{
       JOptionPane.showMessageDialog(null,"Dispositivo no agregado seleccione los datos completos");

     }
        
    }
    }
    if(e.getSource()==vista.boxHabitacion){
     vista.cargarDispositivos(casa.getArea(vista.boxArea.getSelectedItem().toString()).getHabitacion(vista.boxHabitacion.getSelectedItem().toString()).getNames());
    }
    if(e.getSource()==vista.offCasa){
        casa.apagar();
        JOptionPane.showMessageDialog(null,"Dispositivos de casa apagados");
    }
    if(e.getSource()==vista.onCasa){
        casa.encender();
        JOptionPane.showMessageDialog(null,"Dispositivos de casa encendidos");
    }
    if(e.getSource()==vista.offArea){
        if(error.comprobar(vista.boxArea.getItemCount())){
        casa.getArea(vista.boxArea.getSelectedItem().toString()).apagar();
        JOptionPane.showMessageDialog(null,"Dispositivos del area apagados");
        }else{
            JOptionPane.showMessageDialog(null,"selecione o agregue una area antes");
        }
    }
    if(e.getSource()==vista.onArea){
         if(error.comprobar(vista.boxArea.getItemCount())){
        casa.getArea(vista.boxArea.getSelectedItem().toString()).encender();
        JOptionPane.showMessageDialog(null,"Dispositivos del area encendidos");
        }else{
            JOptionPane.showMessageDialog(null,"selecione o agregue una area antes");
        }
    }
    if(e.getSource()==vista.offHabitacion){
        if(error.comprobar(vista.boxHabitacion.getItemCount())){
           casa.getArea(vista.boxArea.getSelectedItem().toString()).getHabitacion(vista.boxHabitacion.getSelectedItem().toString()).apagar();
           JOptionPane.showMessageDialog(null,"Dispositivos de la habiatacion apagados");
        }else{
            JOptionPane.showMessageDialog(null,"selecione o agregue una area y una habitacion antes");
        }
    }
    if(e.getSource()==vista.onHabitacion){
        
        if(error.comprobar(vista.boxHabitacion.getItemCount())){
           casa.getArea(vista.boxArea.getSelectedItem().toString()).getHabitacion(vista.boxHabitacion.getSelectedItem().toString()).encender();
           JOptionPane.showMessageDialog(null,"Dispositivos de la habitacion encendidos");
        }else{
            JOptionPane.showMessageDialog(null,"selecione o agregue una area y una habitacion antes");
        }
    }
    if(e.getSource()==vista.offDispositivo){
        if(error.comprobar(vista.boxDispositivo.getItemCount())){
          casa.getArea(vista.boxArea.getSelectedItem().toString()).getHabitacion(vista.boxHabitacion.getSelectedItem().toString()).getDispositivo(vista.boxDispositivo.getSelectedItem().toString()).turnOff();
          JOptionPane.showMessageDialog(null,"dispositivo apagado");
        }else{
            JOptionPane.showMessageDialog(null,"selecione o agregue un area , una habitacion y un dispositivo antes");
        }
    }
    if(e.getSource()==vista.onDispositivo){
         if(error.comprobar(vista.boxDispositivo.getItemCount())){
          casa.getArea(vista.boxArea.getSelectedItem().toString()).getHabitacion(vista.boxHabitacion.getSelectedItem().toString()).getDispositivo(vista.boxDispositivo.getSelectedItem().toString()).turnOn();
                    JOptionPane.showMessageDialog(null,"dispositivo apagado");

        }else{
            JOptionPane.showMessageDialog(null,"selecione o agregue un area , una habitacion y un dispositivo antes");
        }
    }
    if(e.getSource()==vista.menuUsuario){
        if(adminUsuarios.tipoSesion().equals("Administrador")){
       AgregarUsuario dialogoU = new AgregarUsuario(vista,true);
       dialogoU.setVisible(true);
       if(error.comprobar(dialogoU.nombre.getText())){
           adminUsuarios.añadirUsuario(dialogoU.tipo.getSelectedItem().toString(),dialogoU.nombre.getText(),dialogoU.contraTxt.getText());
           Usuarios usuario= new Usuarios(adminUsuarios.getId(dialogoU.nombre.getText()),dialogoU.nombre.getText(),dialogoU.contraTxt.getText(),dialogoU.tipo.getSelectedItem().toString());
           altaUsuarios(usuario);
       }else{
           JOptionPane.showMessageDialog(null,"Usuario no agregado ingrese un nombre ");
       }
        }else {
            JOptionPane.showMessageDialog(null,"el usuario no cuenta con los permisos para acceder a esta funcionalidad");
        }
       
       
    }
     if(e.getSource()==login.iniciar ){
      if(comprobacionUser(login.usuario.getText(),login.contra.getText())==true){
          login.setVisible(false);
          vista.setVisible(true);
      }else{
          JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectas ");
      }
     }
     if(e.getSource()==login.salir ){
         System.exit(0);
     }
    }
    public boolean comprobacionUser(String nombre,String contra){
        boolean validacion=false;
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
      Session session;
      session = sesion.openSession();
      Transaction tx = session.beginTransaction();
      Query q = session.createQuery("from Usuarios");
      List <Usuarios>lista = q.list();
      Iterator<Usuarios>iter=lista.iterator();
      tx.commit();
      session.close();
      while(iter.hasNext()){
          Usuarios usuarioSer = (Usuarios)iter.next();
          if(usuarioSer.getNombre().equals(nombre)&& usuarioSer.getContra().equals(contra)){
              adminUsuarios.setUsuarioActivo(usuarioSer);
              return validacion=true;
          
      }
     }
      return validacion;
    }
    
    }
    
    

