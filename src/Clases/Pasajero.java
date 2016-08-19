/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Francisco
 */
public class Pasajero {
    private String rut_pas;
    private String nombre_pas;
    private String direccion_pas;
    private int edad_pas;
    private String email_pas;
    private int fono_pas;
    //constructor sin parametros
     public Pasajero() {
    }

    public Pasajero(String rut_pas, String nombre_pas,String direccion_pas, int edad_pas,String email_pas,int fono_pas) {
      this.rut_pas = rut_pas;
      this.nombre_pas = nombre_pas;
      this.direccion_pas = direccion_pas;
      this.edad_pas = edad_pas;
      this.email_pas = email_pas;
      this.fono_pas = fono_pas;
    }

    public int getFono_pas() {
        return fono_pas;
    }

    public void setFono_pas(int fono_pas) {
        this.fono_pas = fono_pas;
    }

    public String getDireccion_pas() {
        return direccion_pas;
    }

    public void setDireccion_pas(String direccion_pas) {
        this.direccion_pas = direccion_pas;
    }

    public int getEdad_pas() {
        return edad_pas;
    }

    public void setEdad_pas(int edad_pas) {
        this.edad_pas = edad_pas;
    }

    public String getEmail_pas() {
        return email_pas;
    }

    public void setEmail_pas(String email_pas) {
        this.email_pas = email_pas;
    }

    public String getNombre_pas() {
        return nombre_pas;
    }

    public void setNombre_pas(String nombre_pas) {
        this.nombre_pas = nombre_pas;
    }

    public String getRut_pas() {
        return rut_pas;
    }

    public void setRut_pas(String rut_pas) {
        this.rut_pas = rut_pas;
    }

    
}
