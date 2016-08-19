/*
 * Java_Samples_for_Mifare_CardsApp.java
 */

package javasamplesformifarecards; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.io.IOException;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
/////////////////////////////////////////

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
//import java.io.*;
//import java.util.*;
//import javax.swing.*;
// javax.swing.text.*;
//import javax.swing.table.*;

////////////////////////////////////////

/**
 * The main class of the application.
 */
public class Java_Samples_for_Mifare_CardsApp extends SingleFrameApplication {
    ActionListener task;
    static Timer timer;
    public int pas=0;
    private String numhab;
    private String tipotarj;
    private String nombrepas;
    private String rutpas;
    private String monto;
    private String estado;
    private String nombre_us;
    private int met = 1;

    /**
     * At startup create and show the main frame of the application.
     */
    public void TranspasoDatos(String numhab,String tipotarj,String nombrepas,String rutpas,String monto,String estado,String nombre_us){
         this.numhab = numhab; 
         this.tipotarj = tipotarj;
         this.nombrepas = nombrepas;
         this.rutpas = rutpas;
         this.monto = monto;
         this.estado = estado;
         this.nombre_us = nombre_us;
         
     }
    public void RecibeNum(int pas)
     {
         this.pas = pas;
     }       
    @Override public void startup() {
           Java_Samples_for_Mifare_CardsView n = new Java_Samples_for_Mifare_CardsView(this);
           if(numhab != null)
           n.TranspasoDatos(numhab, tipotarj, nombrepas, rutpas,monto,estado,nombre_us);
       
           else   
            n.TranspasoDatos1(met);
           //aqui le cambio la propidad al frame aver si resulta???
          n.getFrame().setDefaultCloseOperation(n.getFrame().DO_NOTHING_ON_CLOSE);
           show( n);
        //This Method is used for Adding About itme into the Mai|n Window titlebar
       NativeAboutbox.AboutboxmenuInserted();
       //Timer class is used for event on About item
          timer=new Timer(100,systemmenu_handler); 
          timer.start();
          
          
          
       
    }
      
        

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of Java_Samples_for_Mifare_CardsApp
     */
    public static Java_Samples_for_Mifare_CardsApp getApplication() {
        
        return Application.getInstance(Java_Samples_for_Mifare_CardsApp.class);
        
    }
    
//    For event handling on titleMenu AboutBox
     ActionListener systemmenu_handler = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
         boolean b=NativeAboutbox.AboutFlagGet();
           if(b)
           {
               //for Showing The Aboutbox When click on About_Item
               JFrame mainFrame = Java_Samples_for_Mifare_CardsApp.getApplication().getMainFrame();
                                  JDialog aboutBox=new Java_Samples_for_Mifare_CardsAboutBox(mainFrame);
                                  Java_Samples_for_Mifare_CardsApp.getApplication().show(aboutBox);
                                  //This method is used for setting the value of AboutFlag
                                  b=NativeAboutbox.AboutFlagSet();                
           }
           
   
        }
        
    };
    

    public static void main(String[] args) //throws UnsatisfiedLinkError
    {
     
        launch(Java_Samples_for_Mifare_CardsApp.class, args);
        //Toolkit.getDefaultToolkit().getSystemEventQueue().push( new TCPopupEventQueue());
        
        
    }
  
   

}
 