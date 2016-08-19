/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

/**
 *
 * @author malvarado
 */
public class Pax {
    
    private String id;
    private String nombre;
    private String habitacion;
   // private String consumo;
   // private int monto;
    private String fecha;
    private String num_tarj;
    private String fecha_term;
    private String hora_ingreso;
    private String hora_termino;

    
    //constructor sin parametros
     public Pax() {
    }

    public Pax(String id, String nombre,String habitacion, String fecha,String fecha_term,String hora_ingreso,String hora_termino,String num_tarj) {
        this.id = id;
        this.nombre = nombre;
        this.habitacion = habitacion;        
        //this.consumo = consumo;
        //this.monto = monto;
        this.fecha = fecha;
        this.fecha_term = fecha_term;
        this.hora_ingreso = hora_ingreso;
        this.hora_termino = hora_termino;
        this.num_tarj = num_tarj;
    }

    public String getHora_ingreso() {
        return hora_ingreso;
    }

    public void setHora_ingreso(String hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }

    public String getHora_termino() {
        return hora_termino;
    }

    public void setHora_termino(String hora_termino) {
        this.hora_termino = hora_termino;
    }

    public void setFecha_term(String fecha_term) {
        this.fecha_term = fecha_term;
    }

    public String getNum_tarj() {
        return num_tarj;
    }

    public void setNum_tarj(String num_tarj) {
        this.num_tarj = num_tarj;
    }

    public String getFecha_term() {
        return fecha_term;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
     
     
     
}