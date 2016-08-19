/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author silvia
 */
public class montos {
    
private int id_usuario;
private String id_tarjeta;
private String nombre_prod;
private int precio;
private String fecha_ingreso;
private int estado_mon;
private int cod_ven;
private String hora_ven;
private String id_vendedor;
public montos()
{
    
}
public montos(int id_usuario,String id_tarjeta,String nombre_prod,int precio_prod,String fecha_ingreso,String hora_ven,int estado_mon,String id_vendedor)
{
    this.id_usuario = id_usuario;
    this.id_tarjeta = id_tarjeta;
    this.nombre_prod = nombre_prod;
    this.fecha_ingreso = fecha_ingreso;
    this.estado_mon = estado_mon;
    this.precio = precio_prod;
    this.hora_ven= hora_ven;
    this.id_vendedor = id_vendedor;
}

    public int getCod_ven() {
        return cod_ven;
    }

    public void setCod_ven(int cod_ven) {
        this.cod_ven = cod_ven;
    }

    public String getHora_ven() {
        return hora_ven;
    }

    public void setHora_ven(String hora_ven) {
        this.hora_ven = hora_ven;
    }

    public String getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(String id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public int getEstado_mon() {
        return estado_mon;
    }

    public void setEstado_mon(int estado_mon) {
        this.estado_mon = estado_mon;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(String id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_prod() {
        return nombre_prod;
    }

    public void setNombre_prod(String nombre_prod) {
        this.nombre_prod = nombre_prod;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}