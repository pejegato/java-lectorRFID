/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConsultarSaldo.java
 *
 * Created on 15-05-2016, 12:30:09 AM
 */
package GUI;

import Clases.ConexionTarjeta;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Francisco
 */
public class ConsultarSaldo extends javax.swing.JDialog {
 public static boolean targeta_presente = false;
 public static int acumti = 0;
 public static boolean terminar = false;
 public static boolean ti = false;
 public static int evento = 0;
 public static String saldo ="";
 public static boolean pertenece = false;
 public static boolean nopertenece = false;
 static ConexionTarjeta ct;
 public static boolean unavez = false;
 public static String rutpasajero = "";
    /** Creates new form ConsultarSaldo */
    public ConsultarSaldo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ct = new ConexionTarjeta();
        ct.ConexionTarjeta2(true);
        /*CrearDestruirObjeto();
        if(!saldo.equals(""))
        this.Saldo.setText(saldo);*/
        MensajeSI.setVisible(false);
        SaldoInicial.setVisible(false);
        MensajeSaldoT.setVisible(false);
        SaldoT.setVisible(false);
    }
  public void TranspasoRut(String rut)
  {
      rutpasajero = rut;
  }
    public static void CrearDestruirObjeto()
{
  if(!targeta_presente)
  {
   ct = null;
  }
  else
  {
     ct = new ConexionTarjeta();
     ct.ConexionTarjeta2(true);
  }
}
public static int TipoEvento()
{
    return evento;
}
public static void TarjetaPertenece()
{
    if(!pertenece && evento!=1)
    {
        acumti = 1;
   
    
    }
        
    TarjetaPresente(false);
         pertenece = true;
         evento = 2;
}
    
public static void TarjetaPresente(boolean tp)
{
    if(!tp)
    {
      
       
        MensajeIntro.setText("Aproxime su tarjeta al lector (3 segundos)");
        MensajeIntro.setBackground(Color.BLACK);
        MensajeIntro.setForeground(Color.BLACK);
        Saldo.setText("0");
        TipoTarjeta.setText("");
        SaldoT.setText("");
        Habitacion.setText("");
        SaldoInicial.setText("");
         MensajeSI.setVisible(false);
        SaldoInicial.setVisible(false);
        MensajeSaldoT.setVisible(false);
        SaldoT.setVisible(false);
       acumti = 0;
       ConexionTarjeta.tarjetavalida = false;
        
    }
   
}
public static void NuevaConexionTarjeta()
{
   // JOptionPane.showMessageDialog(null, "tarjeta insertada");  
}
public static void TraerDatos(String[] datos)
{
   
   if(!datos[0].equals("0"))
   {    
   
   if(!nopertenece)
   {
       String dato0 = datos[0];
      if(dato0.equals(""))
      {
           MensajeIntro.setText("Tarjeta Inactiva");
            MensajeIntro.setBackground(Color.red);
            MensajeIntro.setForeground(Color.red);
      }
     
      else if(datos[0].equals(rutpasajero))
    {
      saldo = datos[3];
      Saldo.setText(saldo);
      int saldo1 = Integer.parseInt(saldo);
      String rut = datos[0];
      MensajeIntro.setText("Autentificación correcta");
      TipoTarjeta.setText(datos[1]);
      if(TipoTarjeta.getText().equals("Libre"))
      {
          MensajeSaldo.setText("Total Consumos");
          SaldoInicial.setVisible(false);
          MensajeSI.setVisible(false);
          SaldoT.setVisible(false);
          MensajeSaldoT.setVisible(false);
      }
      if(TipoTarjeta.getText().equals("Prepagada"))
      {
           int saldoini = Integer.parseInt(datos[4]);
           Saldo.setText(""+saldoini);
           int totalcon = (saldoini - (saldoini - saldo1));
           saldoini = saldoini-saldo1;
          MensajeSaldo.setText("Saldo Inicial");
         MensajeSI.setVisible(true);
         SaldoInicial.setText(""+saldoini);
         SaldoInicial.setVisible(true);
         SaldoT.setText(""+totalcon);
         MensajeSaldoT.setVisible(true);
         SaldoT.setVisible(true);
      }
      Habitacion.setText(datos[2]);
      for(int i = 0; i<=4; i++)
      {
          ConexionTarjeta.datos[i] = "";
      }
    }
    else
    {
        Saldo.setText("0");
        MensajeIntro.setText("La tarjeta no corresponde al pasajero");
        MensajeIntro.setBackground(Color.red);
        MensajeIntro.setForeground(Color.red);
    }
   }
   else
   {
       if(datos[0].equals("0"))
       {
       Saldo.setText("0");
       JOptionPane.showMessageDialog(null, "Entro");
       }
   }
 
    datos[0]= "";
    datos[1] = "";
    datos[2]= "";
    datos[3]= "";
    datos[4]= "";

   }
   else if(datos[0].equals("0") && acumti == 0 )
   {
       acumti = 0;
        MensajeIntro.setText("La tarjeta no pertenece al hotel");
        MensajeIntro.setBackground(Color.red);
        MensajeIntro.setForeground(Color.red);
       
   }
}

public void traerd(String[] datos)
{
    Saldo.setText(datos[3]);
}
public static void Lector(boolean respuesta)
{
    if(!respuesta)
    JOptionPane.showMessageDialog(null,"El lector esta desconectado", "Ventana", JOptionPane.ERROR_MESSAGE);    
}
public static void Desaparecer()
{
    MensajeSaldo.setVisible(false);
    Saldo.setVisible(false);
}
public static void LimpiarDatos()
{
    Saldo.setText("0");
    Habitacion.setText("");
    TipoTarjeta.setText("");
    SaldoT.setText("");
    SaldoInicial.setText("");
}


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        MensajeSI = new javax.swing.JLabel();
        TipoTarjeta = new javax.swing.JLabel();
        MensajeIntro = new javax.swing.JLabel();
        MensajeSaldoT = new javax.swing.JLabel();
        SaldoT = new javax.swing.JLabel();
        Saldo = new javax.swing.JLabel();
        Mensaje3 = new javax.swing.JLabel();
        MensajeSaldo = new javax.swing.JLabel();
        MensajeTT = new javax.swing.JLabel();
        SaldoInicial = new javax.swing.JLabel();
        MensajeHabitacion = new javax.swing.JLabel();
        Habitacion = new javax.swing.JLabel();
        BotonTerminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));
        jDesktopPane1.setForeground(new java.awt.Color(0, 51, 153));
        jDesktopPane1.setName("jDesktopPane1"); // NOI18N

        MensajeSI.setFont(new java.awt.Font("Tunga", 0, 24));
        MensajeSI.setForeground(new java.awt.Color(51, 51, 51));
        MensajeSI.setText("Total Consumos");
        MensajeSI.setName("MensajeSI"); // NOI18N
        MensajeSI.setBounds(50, 210, 190, 20);
        jDesktopPane1.add(MensajeSI, javax.swing.JLayeredPane.DEFAULT_LAYER);

        TipoTarjeta.setFont(new java.awt.Font("Tunga", 0, 24));
        TipoTarjeta.setForeground(new java.awt.Color(51, 51, 51));
        TipoTarjeta.setName("TipoTarjeta"); // NOI18N
        TipoTarjeta.setBounds(290, 150, 160, 20);
        jDesktopPane1.add(TipoTarjeta, javax.swing.JLayeredPane.DEFAULT_LAYER);

        MensajeIntro.setFont(new java.awt.Font("Tunga", 0, 24)); // NOI18N
        MensajeIntro.setForeground(new java.awt.Color(51, 51, 51));
        MensajeIntro.setText("Aproxime su tarjeta al lector (3 segundos)");
        MensajeIntro.setName("MensajeIntro"); // NOI18N
        MensajeIntro.setBounds(100, 20, 390, 20);
        jDesktopPane1.add(MensajeIntro, javax.swing.JLayeredPane.DEFAULT_LAYER);

        MensajeSaldoT.setFont(new java.awt.Font("Tunga", 0, 24));
        MensajeSaldoT.setForeground(new java.awt.Color(51, 51, 51));
        MensajeSaldoT.setText("Saldo Actual");
        MensajeSaldoT.setName("MensajeSaldoT"); // NOI18N
        MensajeSaldoT.setBounds(50, 180, 180, 20);
        jDesktopPane1.add(MensajeSaldoT, javax.swing.JLayeredPane.DEFAULT_LAYER);

        SaldoT.setFont(new java.awt.Font("Tunga", 0, 24));
        SaldoT.setForeground(new java.awt.Color(51, 51, 51));
        SaldoT.setName("SaldoT"); // NOI18N
        SaldoT.setBounds(290, 180, 160, 20);
        jDesktopPane1.add(SaldoT, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Saldo.setFont(new java.awt.Font("Tunga", 0, 24));
        Saldo.setForeground(new java.awt.Color(51, 51, 51));
        Saldo.setText("0");
        Saldo.setName("Saldo"); // NOI18N
        Saldo.setBounds(290, 90, 160, 20);
        jDesktopPane1.add(Saldo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Mensaje3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Clases/HIDLOGO.gif"))); // NOI18N
        Mensaje3.setName("Mensaje3"); // NOI18N
        Mensaje3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                Mensaje3PropertyChange(evt);
            }
        });
        Mensaje3.setBounds(0, 0, 88, 26);
        jDesktopPane1.add(Mensaje3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        MensajeSaldo.setFont(new java.awt.Font("Tunga", 0, 24));
        MensajeSaldo.setForeground(new java.awt.Color(51, 51, 51));
        MensajeSaldo.setText("Saldo ");
        MensajeSaldo.setName("MensajeSaldo"); // NOI18N
        MensajeSaldo.setBounds(50, 90, 180, 20);
        jDesktopPane1.add(MensajeSaldo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        MensajeTT.setFont(new java.awt.Font("Tunga", 0, 24));
        MensajeTT.setForeground(new java.awt.Color(51, 51, 51));
        MensajeTT.setText("Tipo de Tarjeta");
        MensajeTT.setName("MensajeTT"); // NOI18N
        MensajeTT.setBounds(50, 150, 190, 20);
        jDesktopPane1.add(MensajeTT, javax.swing.JLayeredPane.DEFAULT_LAYER);

        SaldoInicial.setFont(new java.awt.Font("Tunga", 0, 24));
        SaldoInicial.setForeground(new java.awt.Color(51, 51, 51));
        SaldoInicial.setName("SaldoInicial"); // NOI18N
        SaldoInicial.setBounds(290, 210, 160, 20);
        jDesktopPane1.add(SaldoInicial, javax.swing.JLayeredPane.DEFAULT_LAYER);

        MensajeHabitacion.setFont(new java.awt.Font("Tunga", 0, 24));
        MensajeHabitacion.setForeground(new java.awt.Color(51, 51, 51));
        MensajeHabitacion.setText("Habitacion");
        MensajeHabitacion.setName("MensajeHabitacion"); // NOI18N
        MensajeHabitacion.setBounds(50, 120, 180, 20);
        jDesktopPane1.add(MensajeHabitacion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Habitacion.setFont(new java.awt.Font("Tunga", 0, 24));
        Habitacion.setForeground(new java.awt.Color(51, 51, 51));
        Habitacion.setName("Habitacion"); // NOI18N
        Habitacion.setBounds(290, 120, 160, 20);
        jDesktopPane1.add(Habitacion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        BotonTerminar.setText("Terminar ");
        BotonTerminar.setName("BotonTerminar"); // NOI18N
        BotonTerminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonTerminarMouseClicked(evt);
            }
        });
        BotonTerminar.setBounds(50, 250, 100, 23);
        jDesktopPane1.add(BotonTerminar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Mensaje3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Mensaje3PropertyChange
        // TODO add your handling code here:
}//GEN-LAST:event_Mensaje3PropertyChange

    private void BotonTerminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonTerminarMouseClicked
        // TODO add your handling code here:
        terminar = true;
        ct = null;
        this.dispose();
    }//GEN-LAST:event_BotonTerminarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ConsultarSaldo dialog = new ConsultarSaldo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonTerminar;
    public static javax.swing.JLabel Habitacion;
    private javax.swing.JLabel Mensaje3;
    private javax.swing.JLabel MensajeHabitacion;
    public static javax.swing.JLabel MensajeIntro;
    public static javax.swing.JLabel MensajeSI;
    public static javax.swing.JLabel MensajeSaldo;
    public static javax.swing.JLabel MensajeSaldoT;
    private javax.swing.JLabel MensajeTT;
    public static javax.swing.JLabel Saldo;
    public static javax.swing.JLabel SaldoInicial;
    public static javax.swing.JLabel SaldoT;
    public static javax.swing.JLabel TipoTarjeta;
    private javax.swing.JDesktopPane jDesktopPane1;
    // End of variables declaration//GEN-END:variables
}
