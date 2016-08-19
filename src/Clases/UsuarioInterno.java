/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Francisco
 */
public class UsuarioInterno {
    private String rut_us;
    private String nombre_us;
    private String password_us;
    private String tipo_us;
  public UsuarioInterno()
  {
      
  }
  public UsuarioInterno(String rut_us, String nombre_us,String password_us,String tipo_us)
  {
     this.rut_us = rut_us;
     this.nombre_us = nombre_us;
     this.password_us = password_us;
     this.tipo_us = tipo_us;
  }

    public String getNombre_us() {
        return nombre_us;
    }

    public void setNombre_us(String nombre_us) {
        this.nombre_us = nombre_us;
    }

    public String getPassword_us() {
        return password_us;
    }

    public void setPassword_us(String password_us) {
        this.password_us = password_us;
    }

    public String getRut_us() {
        return rut_us;
    }

    public void setRut_us(String rut_us) {
        this.rut_us = rut_us;
    }

    public String getTipo_us() {
        return tipo_us;
    }

    public void setTipo_us(String tipo_us) {
        this.tipo_us = tipo_us;
    }
}
