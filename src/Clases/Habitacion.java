/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Francisco
 */
public class Habitacion {
    String num_hab;
    int estado_hab;

   
    
    public Habitacion(){
   }
    public Habitacion(String num_hab, int estado_hab){
        this.num_hab = num_hab;
        this.estado_hab = estado_hab;
        
    }
     public int getEstado_hab() {
        return estado_hab;
    }

    public void setEstado_hab(int estado_hab) {
        this.estado_hab = estado_hab;
    }

    public String getNum_hab() {
        return num_hab;
    }

    public void setNum_hab(String num_hab) {
        this.num_hab = num_hab;
    }

}
