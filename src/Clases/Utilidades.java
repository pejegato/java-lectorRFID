/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import Clases.Habitacion;
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author malvarado
 */
public class Utilidades {
    
    Object imprime;
    
    public int insertarPax(Pax u){
        int respuesta = 0;
        try
        {
            Conexion conn = new Conexion();
            String sql = "INSERT INTO USUARIO VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement insertar = conn.crearSentencia(sql);
            
         
            insertar.setString(1, u.getId());
            insertar.setString(2, u.getNombre());
            insertar.setString(3, u.getHabitacion());
            insertar.setString(4, u.getFecha());
            insertar.setString(5, u.getFecha_term());
            insertar.setString(6, u.getHora_ingreso());
            insertar.setString(7, u.getHora_termino());
            insertar.setInt(8, 1);
            insertar.setInt(9, 0);
            insertar.setString(10, u.getNum_tarj());
            
            
            respuesta = insertar.executeUpdate();
            return respuesta;            
        }
        catch(SQLException ex)
        {
            //return ex.getErrorCode();
            JOptionPane.showMessageDialog(null, ex.toString());
            return respuesta;
        }
    }
    
    
   
        
    // *************************   ACTUALIZA USUARIO ****************************************
    public int actualizarPax(int id,String fecha,String horario){
        int respuesta = 0;
        try
        {
            Conexion conn = new Conexion();
            
            String sql = "UPDATE usuario SET estado = 0,fecha_term = ?,hora_termino = ? WHERE id_interno = ? ";
            PreparedStatement actualizar = conn.crearSentencia(sql);
            actualizar.setString(1, fecha);
            actualizar.setString(2, horario);
            actualizar.setInt(3, id);
            respuesta = actualizar.executeUpdate();
            return respuesta;
        }
        catch(SQLException ex)
        {
            return respuesta;
        }
        
    }
    
  
    // *************************   CAMBIA ESTADO USUARIO ****************************************
    
     public int eliminarPax(int id){
        try
        {
            Conexion conn = new Conexion();
            String sql = "UPDATE usuario set estado = 2  WHERE idusuario = ?";
            PreparedStatement eliminar = conn.crearSentencia(sql);
            eliminar.setInt(1, id);
            int respuesta = eliminar.executeUpdate();
            return respuesta;
        }
        catch(SQLException ex)
        {
            return ex.getErrorCode();
        }
    }
     public List TraerHabitacion()
     {
         ResultSet res;
         List lista = new ArrayList();
         try{
             
             Conexion conn = new Conexion();
             String sql = "Select * from Habitacion";
             PreparedStatement consultar = conn.crearSentencia(sql);
             res = consultar.executeQuery();
             while(res.next())
             {
                 Habitacion hab = new Habitacion();
                 hab.setNum_hab(res.getString(1));
                 hab.setEstado_hab(res.getInt(2));
                 
                // lista = Arrays.asList(hab);
                 lista.add(hab);
             }
        
         }
         catch(Exception e)
         {
           
         }
        // return res;
         return lista;
         
     }
      public int actualizarHab(Habitacion hab){
        int respuesta = 0;
        try
        {
            Conexion conn = new Conexion();
            String sql = "UPDATE habitacion SET estado_hab = ? WHERE num_hab = ? ";
            PreparedStatement actualizar = conn.crearSentencia(sql);
            actualizar.setInt(1, hab.getEstado_hab());
            actualizar.setString(2, hab.getNum_hab());
            respuesta = actualizar.executeUpdate();
            return respuesta;
        }
        catch(SQLException ex)
        {
            return respuesta;
        }
        
    }
    
 public String buscarHab(int id){
        try
        {
            Pax u = new Pax();
            Conexion conn = new Conexion();
            String sql = "SELECT habitacion FROM usuario WHERE id_interno = ?";
            PreparedStatement consultar = conn.crearSentencia(sql);
            consultar.setInt(1, id);
            ResultSet registro = consultar.executeQuery();
            if(registro.next())
            {
               /* u.setId(registro.getString(1));
                u.setNombre(registro.getString(2));
                u.setHabitacion(registro.getString(3));*/
                String pasar = registro.getString(1);
               
                
                return pasar;
            }
            else
            {
                return "";
                //JOptionPane.showMessageDialog( null, "no se obtuvo idusuario");
            }
        }
        catch(SQLException ex)
        {
            return "";
        }
    }    
      // ************************* BUSCAR  USUARIO ****************************************
 public List TraerPasajeros()
     {
         ResultSet res;
         List lista = new ArrayList();
         try{
             
             Conexion conn = new Conexion();
             String sql = "Select nombre from usuario where estado = 1";
             PreparedStatement consultar = conn.crearSentencia(sql);
             res = consultar.executeQuery();
             while(res.next())
             {
                 Pax p = new Pax();
                 p.setNombre(res.getString(1));
                 lista.add(p);
             }
        
         }
         catch(Exception e)
         {
           lista.add("nada");
         }
        // return res;
         return lista;
         
     }
     
     public String buscarPax(String id){
        try
        {
            Pax u = new Pax();
            Conexion conn = new Conexion();
            String sql = "SELECT idusuario,nombre FROM usuario WHERE idusuario = ? AND ESTADO = 1";
            PreparedStatement consultar = conn.crearSentencia(sql);
            consultar.setString(1, id);
            ResultSet registro = consultar.executeQuery();
            if(registro.next())
            {
               /* u.setId(registro.getString(1));
                u.setNombre(registro.getString(2));
                u.setHabitacion(registro.getString(3));*/
                String pasar = registro.getString(2);
               
                
                return pasar;
            }
            else
            {
                return "";
                //JOptionPane.showMessageDialog( null, "no se obtuvo idusuario");
            }
        }
        catch(SQLException ex)
        {
             return "";
        }
    }
      public String buscarPax1(String id,String hab){
        try
        {
            Pax u = new Pax();
            Conexion conn = new Conexion();
            String sql = "SELECT idusuario,nombre FROM usuario WHERE idusuario = ? AND ESTADO = 1 and habitacion = ?";
            PreparedStatement consultar = conn.crearSentencia(sql);
            consultar.setString(1, id);
            consultar.setString(2, hab);
            ResultSet registro = consultar.executeQuery();
            if(registro.next())
            {
               /* u.setId(registro.getString(1));
                u.setNombre(registro.getString(2));
                u.setHabitacion(registro.getString(3));*/
                String pasar = registro.getString(2);
               
                
                return pasar;
            }
            else
            {
                return "";
                //JOptionPane.showMessageDialog( null, "no se obtuvo idusuario");
            }
        }
        catch(SQLException ex)
        {
             return "";
        }
    }
     public ArrayList buscarPaxRut(String rut){
         Pax u = new Pax();
         int contador =0;
         ArrayList<Pax> listas = new ArrayList<Pax>();
        try
        {
            
            Conexion conn = new Conexion();
            String sql = "SELECT nombre FROM usuario WHERE idusuario = ?;";
            PreparedStatement consultar = conn.crearSentencia(sql);
            consultar.setString(1, rut);
            ResultSet registro = consultar.executeQuery();
            
            while(registro.next() && contador == 0)
            {
             
              
                
                u.setNombre(registro.getString(1).toString());
                listas.add(u);
                contador++;
                
            }
           
        }
        catch(SQLException ex)
        {
           
        }
        return listas;
    }
     public ArrayList buscarPasajeroRut(String rut){
         Pasajero u = new Pasajero();
         int contador =0;
         ArrayList<Pasajero> listas = new ArrayList<Pasajero>();
        try
        {
            
            Conexion conn = new Conexion();
            String sql = "SELECT * FROM pasajero WHERE rut_pas = ?;";
            PreparedStatement consultar = conn.crearSentencia(sql);
            consultar.setString(1, rut);
            ResultSet registro = consultar.executeQuery();
            
            while(registro.next() && contador == 0)
            {
             
              
                u.setRut_pas(registro.getString(1).toString());
                u.setNombre_pas(registro.getString(2).toString());
                u.setDireccion_pas(registro.getString(3).toString());
                u.setEdad_pas(registro.getInt(4));
                u.setEmail_pas(registro.getString(5).toString());
                u.setFono_pas(registro.getInt(6));
                listas.add(u);
                contador++;
                
            }
           
        }
        catch(SQLException ex)
        {
           
        }
        return listas;
    }
     public int actualizarPasajero(Pasajero p){
        int respuesta = 0;
        try
        {
            Conexion conn = new Conexion();
            
            String sql = "UPDATE pasajero SET nombre_pas = ?,direccion_pas = ?,edad_pas = ?,email_pas = ?,fono_pas = ? WHERE rut_pas = ? ";
            PreparedStatement actualizar = conn.crearSentencia(sql);
            actualizar.setString(1, p.getNombre_pas());
            actualizar.setString(2, p.getDireccion_pas());
            actualizar.setInt(3, p.getEdad_pas());
            actualizar.setString(4, p.getEmail_pas());
            actualizar.setInt(5,p.getFono_pas());
            actualizar.setString(6, p.getRut_pas());
            respuesta = actualizar.executeUpdate();
            return respuesta;
        }
        catch(SQLException ex)
        {
            return respuesta;
        }
        
    }
     public int BuscarId(String numhab,String rut,String numtar)
     {       
         int num = 0;
         try
        {
            Pax u = new Pax();
            Conexion conn = new Conexion();
            String sql = "SELECT * FROM usuario WHERE ESTADO = 1 and habitacion = ? and idusuario = ? and num_tarj = ?";
            PreparedStatement consultar = conn.crearSentencia(sql);
            consultar.setString(1, numhab);
            consultar.setString(2, rut);
            consultar.setString(3,numtar);
            ResultSet registro = consultar.executeQuery();
            while(registro.next())
            {
                
               num = registro.getInt(9);
                
                
            }
            
        }
        catch(SQLException ex)
        {
            return num;
        }
         return num;
     }        
    public int insertarMontos(montos u){
        int respuesta = 0;
        try
        {
            Conexion conn = new Conexion();
            String sql = "INSERT INTO MONTOS VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement insertar = conn.crearSentencia(sql);
            
         
            insertar.setInt(1, u.getId_usuario());
            insertar.setString(2, u.getId_tarjeta());
            insertar.setString(3, u.getNombre_prod());
            insertar.setInt(4, u.getPrecio());
            insertar.setString(5, u.getFecha_ingreso());
            insertar.setString(6, u.getHora_ven());
            insertar.setInt(7, u.getEstado_mon());
            insertar.setString(8, u.getId_vendedor());
            
            
            respuesta = insertar.executeUpdate();
            return respuesta;            
        }
        catch(SQLException ex)
        {
            //return ex.getErrorCode();
            JOptionPane.showMessageDialog(null, ex.toString());
            return respuesta;
            
        }
    }
     public int actualizarMontos(int id_usuario_monto, int estado_monto){
        int respuesta = 0;
        try
        {
            Conexion conn = new Conexion();
            String sql = "UPDATE montos SET estado_monto = ? WHERE id_usuario_monto = ? ";
            PreparedStatement actualizar = conn.crearSentencia(sql);
            actualizar.setInt(1, estado_monto);
            actualizar.setInt(2, id_usuario_monto);
            respuesta = actualizar.executeUpdate();
            return respuesta;
        }
        catch(SQLException ex)
        {
            return respuesta;
        }
        
    }
    public int insertarPasajero(Pasajero u){
        int respuesta = 0;
        try
        {
            Conexion conn = new Conexion();
            String sql = "INSERT INTO Pasajero VALUES(?,?,?,?,?,?)";
            PreparedStatement insertar = conn.crearSentencia(sql);
            
         
            insertar.setString(1, u.getRut_pas());
            insertar.setString(2, u.getNombre_pas());
            insertar.setString(3, u.getDireccion_pas());
            insertar.setInt(4, u.getEdad_pas());
            insertar.setString(5, u.getEmail_pas());
            insertar.setInt(6,u.getFono_pas());
            
            
            
            respuesta = insertar.executeUpdate();
            return respuesta;            
        }
        catch(SQLException ex)
        {
            //return ex.getErrorCode();
            JOptionPane.showMessageDialog(null, ex.toString());
            return respuesta;
        }
    }
    public String validadia(int dia)
    {
        String dia1="";
        if(dia < 10)
            dia1 = "0"+dia;
        else
            dia1 = Integer.toString(dia);
        return dia1;
    }
    public String validames(int mes)
    {
        mes = mes+1;
        String mes1="";
        if(mes < 10)
            mes1 = "0"+mes;
        else
            mes1 = Integer.toString(mes);
            
        return mes1;
    }
   
public boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;        
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            System.out.println("[" + mat.group() + "]");
            return true;
        }else{
            return false;
        }        
    } 
public boolean isNumero(String ip)
{
   char[] transpaso = ip.toCharArray();
   int largo = ip.length();
   int contador = 0;
   for(int i = 0; i < largo; i++)
   {
       char paso = transpaso[i];
       if(Character.isDigit(paso))
           contador++;
   }
   if(contador == largo)
    return true;
   else
       return false;
}
 public boolean BuscarUsuarioInterno(UsuarioInterno usu)
     {       
         boolean num = false;
         try
        {
           
            Conexion conn = new Conexion();
            String sql = "SELECT * FROM usuario_interno WHERE rut_us = ? and nombre_us = ? and password_us = ? and tipo_us = ?;";
            PreparedStatement consultar = conn.crearSentencia(sql);
            consultar.setString(1, usu.getRut_us());
            consultar.setString(2, usu.getNombre_us());
            consultar.setString(3, usu.getPassword_us());
            consultar.setString(4, usu.getTipo_us());
            ResultSet registro = consultar.executeQuery();
            if(registro.next())
            {
                
               num = true;
                
                
            }
            
        }
        catch(SQLException ex)
        {
            
        }
         return num;
     }   
  public String[] BuscarUsuarioInterno1(UsuarioInterno usu)
     {       
         String[] usuario = new String[2];
         usuario[0] = "";
         usuario[1] = "";
         try
        {
           
            Conexion conn = new Conexion();
            String sql = "SELECT * FROM usuario_interno WHERE rut_us = ? and password_us = ? ;";
            PreparedStatement consultar = conn.crearSentencia(sql);
            consultar.setString(1, usu.getRut_us());
            consultar.setString(2, usu.getPassword_us());
            
            ResultSet registro = consultar.executeQuery();
            if(registro.next())
            {
                
               usuario[0] = registro.getString(2);
               usuario[1] = registro.getString(4);
                
                
            }
            
        }
        catch(SQLException ex)
        {
            
        }
         return usuario;
     }    
    
}
