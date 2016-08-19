//****************************************************************************** 
//* @file :  Java_Samples_for_Mifare_CardsView.java
//* @brief <Brief description>
//* This project illustrate Step By Step process of Mifare Read/Write on a CL reader. 
//*
//* <Long description>
//* The source file  Java_Samples_for_Mifare_CardsView.java contains JAVA code for Mifare Authenticate/Read/Write,
//* It also contains the JAVA code for the optional feature such as Load Key(). 
//* For Terms and conditions kindly refer to license.cs.
//* In source code following windows APIs has been exploited:-->
//* 1. TerminalFactory ------ This API has all PC/SC functionality.In this code, we are using following function 
//*    1.1  GetDefault 
//*    1.2  List 
//*    1.3  Get
//*    1.4  Connect
//*    1.5  Disconnect
//*    1.6  GetBasicChannel
//*    1.7  isCardPresent
//* 2. NativeAboutbox.dll  ------- This dll has Windows functionality .In this code, this is only use for System Menu Changes(Add About Dialog) 
//*    2.1  GetSystemMenu
//*    2.2  InsertMenu
//*    
//*
//****************************************************************************** 
//* $Id$   
//*
//* @date January 17 ,2012
//* @version 1.0.0.1
//* 
//* Copyright © 2012 HID Global Corporation.
//* All rights reserved. 
//* Use is subject to license terms
//*******************************************************************************
/*
 * Java_Samples_for_Mifare_CardsView.java
 */
// All packages being imported
package javasamplesformifarecards;
import java.awt.*;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.*;
import javax.smartcardio.*;
import javax.smartcardio.ATR;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame; 
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import Clases.Pax;
import Clases.Utilidades;
import Clases.montos;
import Clases.Conexion;
import Clases.Habitacion;
import GUI.jframeHiatt;
import java.awt.Dialog.ModalityType;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFrame;



/**
 * The application's main frame.
 */
public class Java_Samples_for_Mifare_CardsView extends FrameView implements ActionListener  { 
   int leng1=0;
   int srt=0,end=0;
   static Dimension dim;
   
  
   
 public void TranspasoDatos(String numhab,String tipotarj,String nombrepas,String rutpas, String monto,String estado,String nombre_us){
         this.numhab = numhab; 
         this.tipotarj = tipotarj;
         this.nombrepas = nombrepas;
         this.rutpas = rutpas;
         this.monto = monto;
         this.estado = estado;
         this.nombre_us = nombre_us;
         this.jTextField3.setText(numhab);
         this.TextoNombre.setText(nombrepas);
         this.TextoRut.setText(rutpas);
         this.TextoTarjeta.setText(tipotarj);
         this.jFormattedTextField4.setText(monto);
         this.jTextField2.setVisible(false);
         this.jLabel23.setVisible(false);
         this.ingreso = 1;
     } 
 public void TranspasoDatos1(int met)
  {
    // this.jTextField3.setVisible(false); 
     this.TextoNombre.setVisible(false); 
     //this.TextoTarjeta.setVisible(false);
     this.LabelNombre.setVisible(false);
     this.jButton11.setVisible(false);
     this.jTextField3.setEnabled(false);
     this.TextoRut.setEnabled(false);
     this.TextoTarjeta.setEnabled(false);
     this.ingreso = 0;
     this.metodo = met;
 }       
 public void actionPerformed(ActionEvent e) {  
    if("Cut      Ctr+X"==e.getActionCommand())   
    {
     jFormattedTextField4.cut();  
    }
    else if("Copy   Ctr+C"==e.getActionCommand())   
    {
     jFormattedTextField4.copy();  
    }
    else if("Paste   Ctr+V"==e.getActionCommand())   
    {
     jFormattedTextField4.paste(); 
    }
   
    
    }

/////////////////////////////
    public Java_Samples_for_Mifare_CardsView(SingleFrameApplication app) {
        super(app);
       int aerrs = this.getFrame().getDefaultCloseOperation();
       
       
      app.getMainFrame().setDefaultCloseOperation(2);
      
        this.getFrame().setDefaultCloseOperation(2);
        
       
        initComponents();
        
        Image im=Toolkit.getDefaultToolkit().getImage("HIDLOGO.gif");
        dim= super.getFrame().getToolkit().getScreenSize();
         int largo = dim.height;
         int ancho = dim.width;
        windowload2();
      
        this.getFrame().setSize(300,400);
        if(!nombre_us.equals(""))
        this.getFrame().setTitle("Cargar datos Tarjeta   Usuario: "+ nombre_us);
        else
         this.getFrame().setTitle("Cargar datos Tarjeta ");   
        
        //setExtendedState(Inicio.MAXIMIZED_BOTH);
        this.jLabel22.setVisible(false);
        this.jLabel15.setVisible(false);
        this.jLabel16.setVisible(false);
        this.getFrame().setIconImage(im);
        this.jComboBox1.setVisible(false);
        this.jButton1.setVisible(false);
        this.jButton2.setVisible(false);
        this.jButton3.setVisible(false);
        this.jButton4.setVisible(false);
        this.jButton5.setVisible(false);
          
        this.jPanel3.setVisible(false);
        this.jRadioButton1.setVisible(false);
        this.jRadioButton2.setVisible(false);
        this.jLabel21.setVisible(false);
        this.jLabel9.setVisible(false);
        this.jComboBox3.setVisible(false);
        this.jComboBox4.setVisible(false);
        this.getFrame().setResizable(true);
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
               
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        
    }
   
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Java_Samples_for_Mifare_CardsApp.getApplication().getMainFrame();
            aboutBox = new Java_Samples_for_Mifare_CardsAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Java_Samples_for_Mifare_CardsApp.getApplication().show(aboutBox);
    }

    @SuppressWarnings("unchecked")
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jComboBox3 = new javax.swing.JComboBox();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBox4 = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        TextoNombre = new javax.swing.JTextField();
        LabelNombre = new javax.swing.JLabel();
        TextoRut = new javax.swing.JTextField();
        LabelRut = new javax.swing.JLabel();
        TextoTarjeta = new javax.swing.JTextField();
        LabelTarjeta = new javax.swing.JLabel();
        BotonSalir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        mensaje = new javax.swing.JLabel();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(Java_Samples_for_Mifare_CardsView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mainPanel.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        mainPanel.setMaximumSize(new java.awt.Dimension(700, 650));
        mainPanel.setMinimumSize(new java.awt.Dimension(700, 650));
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setPreferredSize(new java.awt.Dimension(700, 650));
        mainPanel.setRequestFocusEnabled(false);

        jPanel1.setBackground(resourceMap.getColor("jPanel1.background")); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel1.border.titleFont"), resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(510, 51));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jComboBox1.setMinimumSize(new java.awt.Dimension(21, 18));
        jComboBox1.setName("selectreaderjComboBox"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox1FocusGained(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(resourceMap.getColor("jPanel2.background")); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(510, 109));

        jButton4.setText(resourceMap.getString("disconnectjButton.text")); // NOI18N
        jButton4.setEnabled(false);
        jButton4.setName("disconnectjButton"); // NOI18N
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton3.setText(resourceMap.getString("connectjButton.text")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.setName("connectjButton"); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextField1.setBackground(resourceMap.getColor("cardstatusjTextField.background")); // NOI18N
        jTextField1.setEditable(false);
        jTextField1.setFont(resourceMap.getFont("cardstatusjTextField.font")); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText(resourceMap.getString("cardstatusjTextField.text")); // NOI18N
        jTextField1.setName("cardstatusjTextField"); // NOI18N

        jButton1.setText(resourceMap.getString("sCardEstablishContextjButton.text")); // NOI18N
        jButton1.setName("sCardEstablishContextjButton"); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText(resourceMap.getString("sCardReleaseContextjButton.text")); // NOI18N
        jButton2.setEnabled(false);
        jButton2.setName("sCardReleaseContextjButton"); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)))))
                .addGap(157, 157, 157))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)))
        );

        jPanel3.setBackground(resourceMap.getColor("jPanel3.background")); // NOI18N
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel3.border.title"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(510, 91));

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jComboBox2.setMaximumRowCount(5);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBox2.setEnabled(false);
        jComboBox2.setName("keynumberloadjComboBox"); // NOI18N
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jButton9.setText(resourceMap.getString("loadkeyjButton.text")); // NOI18N
        jButton9.setEnabled(false);
        jButton9.setName("loadkeyjButton"); // NOI18N
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });

        jLabel19.setForeground(resourceMap.getColor("leyLoadjLabel.foreground")); // NOI18N
        jLabel19.setText(resourceMap.getString("leyLoadjLabel.text")); // NOI18N
        jLabel19.setName("leyLoadjLabel"); // NOI18N

        jFormattedTextField2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jFormattedTextField2.setEnabled(false);
        jFormattedTextField2.setName("keyloadjFormattedTextField2"); // NOI18N
        jFormattedTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFormattedTextField2MouseClicked(evt);
            }
        });
        jFormattedTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextField2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(27, 27, 27)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel4.setBackground(resourceMap.getColor("jPanel4.background")); // NOI18N
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel4.border.title"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(510, 243));

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jRadioButton1.setBackground(resourceMap.getColor("keynumberradiojRadioButton.background")); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText(resourceMap.getString("keynumberradiojRadioButton.text")); // NOI18N
        jRadioButton1.setEnabled(false);
        jRadioButton1.setName("keynumberradiojRadioButton"); // NOI18N
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jComboBox3.setMaximumRowCount(5);
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01" }));
        jComboBox3.setEnabled(false);
        jComboBox3.setName("keynumberjComboBox"); // NOI18N
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });

        jRadioButton2.setBackground(resourceMap.getColor("keyjRadioButton.background")); // NOI18N
        jRadioButton2.setText(resourceMap.getString("keyjRadioButton.text")); // NOI18N
        jRadioButton2.setEnabled(false);
        jRadioButton2.setName("keyjRadioButton"); // NOI18N
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jComboBox4.setMaximumRowCount(5);
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05" }));
        jComboBox4.setEnabled(false);
        jComboBox4.setName("blockjComboBox"); // NOI18N
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jButton5.setText(resourceMap.getString("authenticatejButton.text")); // NOI18N
        jButton5.setEnabled(false);
        jButton5.setName("authenticatejButton"); // NOI18N
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jButton6.setText(resourceMap.getString("readjButton6.text")); // NOI18N
        jButton6.setEnabled(false);
        jButton6.setName("readjButton6"); // NOI18N
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jButton7.setText(resourceMap.getString("writejButton7.text")); // NOI18N
        jButton7.setEnabled(false);
        jButton7.setName("writejButton7"); // NOI18N
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(resourceMap.getFont("logsjTextArea1.font")); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setName("logsjTextArea1"); // NOI18N
        jTextArea1.setPreferredSize(new java.awt.Dimension(194, 79));
        jScrollPane1.setViewportView(jTextArea1);

        jLabel13.setForeground(resourceMap.getColor("jLabel13.foreground")); // NOI18N
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        jLabel14.setForeground(resourceMap.getColor("writejLabel.foreground")); // NOI18N
        jLabel14.setText(resourceMap.getString("writejLabel.text")); // NOI18N
        jLabel14.setName("writejLabel"); // NOI18N

        jLabel20.setForeground(resourceMap.getColor("keyjLabel.foreground")); // NOI18N
        jLabel20.setText(resourceMap.getString("keyjLabel.text")); // NOI18N
        jLabel20.setName("keyjLabel"); // NOI18N

        jFormattedTextField4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jFormattedTextField4.setEnabled(false);
        jFormattedTextField4.setMinimumSize(new java.awt.Dimension(5, 20));
        jFormattedTextField4.setName("datajFormattedTextField4"); // NOI18N
        jFormattedTextField4.setPreferredSize(new java.awt.Dimension(5, 20));
        jFormattedTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFormattedTextField4MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jFormattedTextField4MouseReleased(evt);
            }
        });
        jFormattedTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextField4KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextField4KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField4KeyReleased(evt);
            }
        });

        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setEnabled(false);
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setEnabled(false);
        jTextField2.setName("jTextField2"); // NOI18N

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setEnabled(false);
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });

        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setEnabled(false);
        jTextField3.setName("jTextField3"); // NOI18N

        jLabel24.setText(resourceMap.getString("jLabel24.text")); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N

        TextoNombre.setText(resourceMap.getString("TextoNombre.text")); // NOI18N
        TextoNombre.setEnabled(false);
        TextoNombre.setName("TextoNombre"); // NOI18N

        LabelNombre.setText(resourceMap.getString("LabelNombre.text")); // NOI18N
        LabelNombre.setName("LabelNombre"); // NOI18N

        TextoRut.setText(resourceMap.getString("TextoRut.text")); // NOI18N
        TextoRut.setEnabled(false);
        TextoRut.setName("TextoRut"); // NOI18N

        LabelRut.setText(resourceMap.getString("LabelRut.text")); // NOI18N
        LabelRut.setName("LabelRut"); // NOI18N

        TextoTarjeta.setText(resourceMap.getString("TextoTarjeta.text")); // NOI18N
        TextoTarjeta.setEnabled(false);
        TextoTarjeta.setName("TextoTarjeta"); // NOI18N

        LabelTarjeta.setText(resourceMap.getString("LabelTarjeta.text")); // NOI18N
        LabelTarjeta.setName("LabelTarjeta"); // NOI18N

        BotonSalir.setText(resourceMap.getString("BotonSalir.text")); // NOI18N
        BotonSalir.setName("BotonSalir"); // NOI18N
        BotonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonSalirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(226, 226, 226))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(LabelNombre)
                            .addComponent(LabelRut)
                            .addComponent(LabelTarjeta))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TextoRut, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                            .addComponent(TextoNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton7))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addComponent(jLabel21)
                                                .addGap(18, 18, 18)
                                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(40, 40, 40)
                                                .addComponent(jButton5, 0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jRadioButton1)
                                                .addGap(18, 18, 18)
                                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jButton10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 78, Short.MAX_VALUE)
                                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(24, 24, 24))
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel14)
                                                        .addComponent(jButton11)
                                                        .addComponent(BotonSalir)))))))
                                .addContainerGap())
                            .addComponent(TextoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jRadioButton1)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jButton6)
                    .addComponent(jButton11)
                    .addComponent(jButton7)
                    .addComponent(jButton10))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelTarjeta)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextoRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelRut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNombre)
                    .addComponent(BotonSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(resourceMap.getColor("jPanel6.background")); // NOI18N
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel6.border.title"))); // NOI18N
        jPanel6.setInheritsPopupMenu(true);
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(157, 480));

        jButton8.setText(resourceMap.getString("clearjButton8.text")); // NOI18N
        jButton8.setName("clearjButton8"); // NOI18N
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jTextPane2.setEditable(false);
        jTextPane2.setName("rtb"); // NOI18N
        jScrollPane4.setViewportView(jTextPane2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8))
        );

        jLabel15.setForeground(resourceMap.getColor("uidjLabel.foreground")); // NOI18N
        jLabel15.setText(resourceMap.getString("uidjLabel.text")); // NOI18N
        jLabel15.setName("uidjLabel"); // NOI18N

        jLabel16.setForeground(resourceMap.getColor("atrjLabel.foreground")); // NOI18N
        jLabel16.setText(resourceMap.getString("atrjLabel.text")); // NOI18N
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel16.setName("atrjLabel"); // NOI18N

        jLabel17.setForeground(resourceMap.getColor("mifarecardtypejLabel.foreground")); // NOI18N
        jLabel17.setText(resourceMap.getString("mifarecardtypejLabel.text")); // NOI18N
        jLabel17.setName("mifarecardtypejLabel"); // NOI18N

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(resourceMap.getIcon("jLabel18.icon")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel18.setName("jLabel18"); // NOI18N

        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        mensaje.setText(resourceMap.getString("mensaje.text")); // NOI18N
        mensaje.setName("mensaje"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(132, 132, 132)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(306, 306, 306)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(209, 209, 209))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addGap(107, 107, 107)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160))
        );

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 535, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 585, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents
    //******************************************************************
    //Global Variables
    //******************************************************************
    TerminalFactory factory;                    //
    java.util.List<CardTerminal> terminals;     //Get the terminals attached
    CardTerminal terminal;                      //receive the object of terminals
    Card card;                                  //
    CardChannel channel;                        //
    String readerName;
    String pasanom = "";
    int indice = 0;
    int solounavez = 0;
    int solounavez1 = 0;
    boolean validarau = true;
    boolean IsAuthenticated;
    int card_Type;
    byte currentBlock;
    byte keynum;
    private Timer t;
    int h=1;
    java.awt.event.MouseEvent evt1;
    boolean cardpre;
    String license;
    int chat1=0,chat=0,chat4=0,chat14=0;
    char ch1;
    //*******************************************************************
    
    //********************************************************
    //Function Name:windowload2
    //Input(Parameter) : ------
    //OutPutParameter:-------
    //Description:First time when the window is loaded
    //********************************************************
    @SuppressWarnings("MalformedRegexp")
    private void windowload2() 
    {
        String reader;
        String readertemp;
        int count=0;
        int index1=0;
        int startindex1=0;
        String readerarray[];
        t = new Timer(1000,taskperformer);
        try
        {
        factory = TerminalFactory.getDefault();
        terminals = factory.terminals().list();
        reader=terminals.toString();
        
        while(index1!=-1)
                {
                   index1= reader.indexOf(",",startindex1+1);
                   count++;
                   startindex1=index1;
                }
        
        readertemp=reader;
        reader=readertemp.replaceAll("]", " ");
        readertemp=reader.replaceAll("PC/SC terminal", "");
        reader=readertemp.replace('[',' ');
        readerarray = reader.split(",", count);
        int i =0;
        String pasares[];
        pasares = new String[2];
        indice = count - 1;
        while (i<count)
        {
           // pasares[i] = readerarray[i].toString().trim();
            jComboBox1.addItem(readerarray[i].toString().trim());
            i++;
        }
      
            pasanom = jComboBox1.getItemAt(indice).toString();
            readerName = pasanom;
     
        //OMNIKEY CardMan 5x21 0 OMNIKEY CardMan 5x21-CL 0
        }
        catch(CardException e)
        {
        System.out.println(e.toString());
         this.getFrame().setAlwaysOnTop(false);
         JOptionPane.showMessageDialog(null, "El lector esta desconectado");
         
         salir = true;
        this.getFrame().dispose();
         
          
        }
         
         jLabel13.setText("");
         jLabel14.setText("");
         jLabel15.setText("");
         jLabel16.setText("");
         jLabel17.setText("");
         jLabel19.setText("                     ");
         jLabel20.setText("                     ");
         //readerName="";
         Tool_Tip();
         license();
         if(solounavez1 ==0)
         {
         TiempoConexion();
         Conectar();
         
         }
         solounavez1++;
     }
    
    
    // Establish Context
    //********************************************************
    //Function Name: jButton1MouseClicked
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Establishing the context to the reader
    //********************************************************
    public void TiempoConexion()
    {
        if(!(readerName.matches("")))
       {
            jButton1.setEnabled(false);
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
            IsAuthenticated = false;
            // obtiene la selección del terminaal
            if(readerName.contains("CL"))
            {
                
                  terminal = terminals.get(1); 
            }
            else
            {
               //terminal = terminals.get(jComboBox1.getSelectedIndex());
                terminal = terminals.get(1);
            }
            Textcolorchange(">SCardEstablishContext\n  Successsful\n\n",Color.black);
            t=new Timer(1000, taskperformer);
            t.start();
            //evt1=evt;
       }
       else
       {
           Textcolorchange("\n> SCardEstablishContext\n" + "   Failed..." +"\n\n",Color.red);
       }
        
            
    }    //********************************************************
    //Function Name: taskperformer
    //Input Parameter:-------
    //OutPutParameter:-------
    //Description:Perform action after certain time interval passed 
    //********************************************************
   ActionListener taskperformer = new ActionListener() {
    
        public void actionPerformed(ActionEvent e) {
         if(!salir)
     {
           if(readerName.matches(""))
           {
                jTextField1.setText("Tarjeta Removida");
                jTextField1.setBackground(Color.red);
                jTextField1.setForeground(Color.white);
           }
           else
           {  
               try
                {
                    cardpre=terminal.isCardPresent();
                }
                catch(CardException e1)
                {
                    System.out.println(e1.toString());
                }
             
               if(!cardpre)
               {
                   jTextField1.setText("Tarjeta Removida");
                   jTextField1.setBackground(Color.red);
                   jTextField1.setForeground(Color.white);
                   jLabel17.setText("");
                   jLabel16.setText("");
                   jLabel15.setText("");
                   jButton3.setEnabled(true); //Connect enable
                   Disabled_Enabled_Controls();
                   jButton4.setEnabled(false);
                   solounavez = 0;
                   validarau = true;
                   
               }
               else
               {
                    jTextField1.setText("Tarjeta Insertada");
                    jTextField1.setBackground(new Color(173,255, 47));
                    jTextField1.setForeground(Color.black);
                    if(solounavez == 0)
                    {
                        if(validarau == true)
                       {
                          TiempoConexion();
                          Conectar();
                          Autentifica();
                       }
                    }
                    solounavez++;
                    
               }
           }
           cardpre = false;
        }
      } 
    };
    
    //releaseContext
    //********************************************************
    //Function Name:jButton2MouseClicked
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Release the context
    //********************************************************    
    //********************************************************
    //Function Name: jComboBox1ItemStateChanged
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Selecting The Reader
    //********************************************************    
    //Connect
    //********************************************************
    //Function Name: jButton3MouseClicked
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Connect to SmartCard
    //********************************************************
    public void Conectar()
    {
         String s="";
        String s1="";
        String atr_temp="";
        String uid_temp="";
        int atr_byte =0;
        int n,x;
    
        try
        {
                //establcer conexion con la tarjeta
                card = terminal.connect("T=1");
                System.out.print(card);
       
             channel = card.getBasicChannel();
             //////////ATR////////
             ATR r2= channel.getCard().getATR();
             byte atr[]=r2.getBytes();
           
             
             
              for (n = 0; n < atr.length; n++) 
              {
                  x = (int) (0x000000FF & atr[n]);  // conversion de byte a int
                  s = Integer.toHexString(x).toUpperCase();
                  if (s.length() == 1) s = "0" + s;
                  s1=s1+s+" ";
                 
              } 
              atr_temp=s1;
               jLabel16.setText("ATR ="+atr_temp);
              
               try
               {
                  atr_byte=atr[14];
                  
              }
              catch(ArrayIndexOutOfBoundsException e)
              {
                   System.out.println(e.toString());
              }
               
               
                  //////////UID///////////   
                  s1=""; 
                  CommandAPDU c2= new CommandAPDU(0xff,0xCA,0x00,0x00,null,0x00,0x00,0x1);
                  ResponseAPDU r1 = channel.transmit(c2);  
                  byte uid[]=r1.getBytes();
               

                  for (n = 0; n < uid.length-2; n++) 
                  {
                      x = (int) (0x000000FF & uid[n]);  // byte to int conversion
                      s = Integer.toHexString(x).toUpperCase();
                      if (s.length() == 1) s = "0" + s;
                      s1=s1+s+" ";
                  }
                   uid_temp=s1;
                   if(uid_temp.matches(""))
                   {
                   
                   }
                   else
                   {
                   jLabel15.setText("UID ="+uid_temp);
                   }
                       
                       
                       
               //Card Type
               if(atr_byte==1)
               {
                    card_Type=1;
                    card_Type_Identification();
                    jLabel17.setText("Card Type: Mifare 1K");
                    jButton3.setEnabled(false);
                    jButton4.setEnabled(true);
                    jComboBox2.setEnabled(true);
                    jFormattedTextField2.setEnabled(true);
                    jButton9.setEnabled(true);
                    jRadioButton1.setEnabled(true);
                    jRadioButton2.setEnabled(true);
                    jComboBox4.setEnabled(true);
                    jButton5.setEnabled(true);
                    jComboBox2.setSelectedIndex(0);
                    jComboBox3.setSelectedIndex(0);
                    jComboBox4.setSelectedIndex(0);
                    jTextField1.setText("Tarjeta Insertada");
                    jTextField1.setBackground(new Color(173,255, 47));
                    jTextField1.setForeground(Color.black);
                    if(jRadioButton2.isSelected()==true)
                    {
                        jComboBox3.setEnabled(true);
                    }
                    else if(jRadioButton1.isEnabled()==true)
                    {
                        jComboBox3.setEnabled(true);
                        jComboBox3.setSelectedIndex(0);
                    }
               
               }
               else if(atr_byte==2)
               {
                    card_Type=2;
                    card_Type_Identification();
                    jLabel17.setText("Card Type: Mifare 4K");
                    jButton3.setEnabled(false);
                    jButton4.setEnabled(true);
                    jComboBox2.setEnabled(true);
                    jFormattedTextField2.setEnabled(true);
                    jButton9.setEnabled(true);
                    jRadioButton1.setEnabled(true);
                    jRadioButton2.setEnabled(true);
                    
                    jComboBox4.setEnabled(true);
                    jButton5.setEnabled(true);

                     jComboBox2.setSelectedIndex(0);
                    jComboBox3.setSelectedIndex(0);
                    jComboBox4.setSelectedIndex(0);
                    jTextField1.setText("Tarjeta Insertada");
                    jTextField1.setBackground(new Color(173,255, 47));
                    jTextField1.setForeground(Color.black);
                      if(jRadioButton2.isSelected()==true)
                    {
                        jComboBox3.setEnabled(true);
                    }
                    else if(jRadioButton1.isEnabled()==true)
                    {
                        jComboBox3.setEnabled(true);
                        jComboBox3.setSelectedIndex(0);
                    }
                    
               }
               else if(uid_temp.matches("") )
               {
                    jLabel15.setText("UID = n/a");
                    jLabel17.setText("                            Card Type: No Mifare 1K or 4K Card ");
                    jButton3.setEnabled(false);
                    jButton4.setEnabled(true);
               }
               else
               {
                   jLabel17.setText("                            Card Type: No Mifare 1K or 4K Card ");
                    jButton3.setEnabled(false);
                    jButton4.setEnabled(true);
               }
               Textcolorchange("> SCardConnect \n     Successsful\n\n",Color.black);
            
            }
            catch(CardException e)
            {
               Textcolorchange("> SCardConnect" + "\n   Failed... \n\n",Color.red);
            }
    }    
    //Disconnect
    //********************************************************
    //Function Name: DisconnectButtonClick
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Disconnect the Smartcard
    //********************************************************    
    //Load Key
    //********************************************************
    //Function Name:jButton9MouseClicked
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on Loadkey Button Click
    //APDU Description: ClassByte bcla = 0xFF, Instruction Byte bins=0x82 ,Parameter P1=Key Structure , Parameter P2=Key Number
    //                  Lc = key length and Data Bytes = key
    //********************************************************    
    //********************************************************
    //Function Name:jComboBox2ItemStateChanged
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on key number ComboBox selection changed
    //********************************************************    
    //********************************************************
    //Function Name:jComboBox3ItemStateChanged
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on key number ComboBox selection changed
    //********************************************************
    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        jLabel13.setText("");
        jButton6.setEnabled(false);
        jButton11.setEnabled(false);
        jButton7.setEnabled(false);
        jButton10.setEnabled(false);
        jFormattedTextField4.setEnabled(false);
        jTextField2.setEnabled(false);
        jLabel14.setText("");
  
    }//GEN-LAST:event_jComboBox3ItemStateChanged
    
    //********************************************************
    //Function Name:jComboBox4ItemStateChanged
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on Block Selection
    //********************************************************
    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
        jLabel13.setText("");
        jButton6.setEnabled(false);
        jButton11.setEnabled(false);
        jButton7.setEnabled(false);
        jFormattedTextField4.setEnabled(false);
        jLabel14.setText("");
       // jFormattedTextField3.setBackground(Color.white);
        jLabel20.setText("                     ");
        
    }//GEN-LAST:event_jComboBox4ItemStateChanged
    
    //Authenticate
    //********************************************************
    //Function Name: jButton5MouseClicked
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Authenticate the card using key 
    //APDU Description: ClassByte bcla = 0xFF, Instruction Byte bins=0x88 / 0x86 ,Parameter P1=Address MSB / 0x00 , Parameter P2=Address LSB / 0x00
    //                  P3 = key type / 0x05 and Data Bytes = keynumber / (Version,Address MSB,Address LSB,Key Type,Key Number)
    //********************************************************
    public void Autentifica()
    {
        String keych;
        byte a1[]=new byte[1];
        byte receive[];
        validarau = false;
        if(!(jComboBox4.getSelectedItem().toString().matches("")))
        {
            int cb=Integer.parseInt(jComboBox4.getSelectedItem().toString());
            currentBlock=intToPseudoUnsignedByte(cb);
            System.out.println(currentBlock);
            keynum =Byte.parseByte(jComboBox2.getSelectedItem().toString());
            a1[0]=keynum;//Key Number
            
                    //////////////Authentication By Key Number using KEY A
            if(jRadioButton1.isSelected()==true && jRadioButton1.isEnabled()==true && !(jComboBox3.getSelectedItem().toString().matches("")))
                {
                    byte data[]=new byte[5];
                    keynum =Byte.parseByte(jComboBox3.getSelectedItem().toString());
                    try
                    {
                      data[0]=(byte)0x1;
                      data[1]=(byte)0x0;
                      data[2]=currentBlock;
                      data[3]=(byte)0x60;
                      data[4]=keynum;
                      CommandAPDU c2= new CommandAPDU(0xff,0x86,0x00,0x00,data);
                      ResponseAPDU r2 = channel.transmit(c2);
                      System.out.println(r2);
                      receive=r2.getBytes();
                       if(bytestohex(receive).matches("9000"))
                        {
                              jComboBox4.setEnabled(true);
                              jButton6.setEnabled(true);
                              jButton11.setEnabled(true);
                              jButton7.setEnabled(true);
                              jButton10.setEnabled(true);
                              jFormattedTextField4.setEnabled(true);
                              jTextField2.setEnabled(true);
                              IsAuthenticated=true;
                              jLabel20.setText("                     ");
                              Textcolorchange("> General Authenticate\n" + "   Successful \n\n", Color.black);
                            
                        }
                        else
                        {
                            Textcolorchange("> General Authenticate" + "   Failed(SW1 SW2 ="+bytestohex(receive)+")\n\n", Color.red);
                             jButton6.setEnabled(false);
                             jButton11.setEnabled(false);
                             jButton7.setEnabled(false);
                             jFormattedTextField4.setEnabled(false);

                        }
                       if(ingreso == 1)
                        {
                            //Textcolorchange("> General Authenticate" + "   Failed(SW1 SW2 ="+bytestohex(receive)+")\n\n", Color.red);
                             jButton6.setEnabled(false);
                             jButton11.setEnabled(false);
                             jButton10.setEnabled(false);
                             jButton7.setEnabled(true);
                             jFormattedTextField4.setEnabled(false);

                        }   
                    }
                    catch(CardException e)
                    {
                        System.out.println(e.toString());
                    }
                }
                    
                    //////////////Authentication By Key Number using KEY B
            else if(jRadioButton2.isSelected()==true && jRadioButton2.isEnabled()==true && !(jComboBox3.getSelectedItem().toString().matches("")))
                {
                    byte data[]=new byte[5];
                    keynum =Byte.parseByte(jComboBox3.getSelectedItem().toString());
                    try
                    {
                      data[0]=(byte)0x1;
                      data[1]=(byte)0x0;
                      data[2]=currentBlock;
                      data[3]=(byte)0x61;
                      data[4]=keynum;
                      CommandAPDU c2= new CommandAPDU(0xff,0x86,0x00,0x00,data);
                      ResponseAPDU r2 = channel.transmit(c2);
                      System.out.println(r2);
                      receive=r2.getBytes();
                       if(bytestohex(receive).matches("9000"))
                        {
                              jComboBox4.setEnabled(true);
                              jButton6.setEnabled(true);
                              jButton11.setEnabled(true);
                              jButton7.setEnabled(true);
                              jButton10.setEnabled(true);
                              jFormattedTextField4.setEnabled(true);
                              jTextField2.setEnabled(false);
                              IsAuthenticated=true;
                              jLabel20.setText("                     ");
                              Textcolorchange("> General Authenticate\n" + "   Successful \n\n", Color.black);
                            
                        }
                        else
                        {
                            Textcolorchange("> General Authenticate" + "   Failed(SW1 SW2 ="+bytestohex(receive)+")\n\n", Color.red);
                             jButton6.setEnabled(false);
                             jButton7.setEnabled(false);
                             jButton10.setEnabled(false);
                             jFormattedTextField4.setEnabled(false);
                             jTextField2.setEnabled(false);
                        }
                    }
                    catch(CardException e)
                    {
                        System.out.println(e.toString());
                    }
                }
            else
                keych="";
            
        }
        else
        {
            jLabel13.setText("Select Block");
        }
       
       if(metodo == 1)
       {    
        Traer("02");
        String cadena = traerestado;
        char transpaso[]= cadena.toCharArray();
        String tt =""+ String.valueOf(transpaso[30])+String.valueOf(transpaso[31]);
        String h =""+ String.valueOf(transpaso[23])+String.valueOf(transpaso[24]+String.valueOf(transpaso[25]));
        String r = "";
        for(int i = 13;i<=21;i++ )
        {
            r= r + String.valueOf(transpaso[i]);
        }
        if(tt.equals("00"))
        {
            this.getFrame().setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null,"Tarjeta no relacionada a pasajero, Deve ingresar al pasajero, para mas informaciòn, dirijase a recepciòn", "Ventana", JOptionPane.ERROR_MESSAGE);
                 
                 
                
                 salir = true;
                 this.getFrame().setVisible(false);
                 this.getFrame().dispose();
        }
        else
        {
            String ttar = "";
            if(tt.equals("02"))
            {    
                 ttar = "Prepagada";
                this.texa = 1;
            }     
               
            if(tt.equals("01"))
            {    
                ttar = "Libre";
                this.texa = 0;
            } 
            this.getFrame().setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null,",Autentificaciòn de tarjeta correcta");
            this.getFrame().setAlwaysOnTop( true );
                 
             this.TextoRut.setText(r);
             this.TextoTarjeta.setText(ttar);
             this.jTextField3.setText(h);
             this.jTextField2.requestFocus();
                
        }    
        
        
       }  
    }
    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        // TODO add your handling code here:
        String keych;
        byte a1[]=new byte[1];
        byte receive[];
        
        if(!(jComboBox4.getSelectedItem().toString().matches("")))
        {
            int cb=Integer.parseInt(jComboBox4.getSelectedItem().toString());
            currentBlock=intToPseudoUnsignedByte(cb);
            System.out.println(currentBlock);
            keynum =Byte.parseByte(jComboBox2.getSelectedItem().toString());
            a1[0]=keynum;//Key Number
            
                    //////////////Authentication By Key Number using KEY A
            if(jRadioButton1.isSelected()==true && jRadioButton1.isEnabled()==true && !(jComboBox3.getSelectedItem().toString().matches("")))
                {
                    byte data[]=new byte[5];
                    keynum =Byte.parseByte(jComboBox3.getSelectedItem().toString());
                    try
                    {
                      data[0]=(byte)0x1;
                      data[1]=(byte)0x0;
                      data[2]=currentBlock;
                      data[3]=(byte)0x60;
                      data[4]=keynum;
                      CommandAPDU c2= new CommandAPDU(0xff,0x86,0x00,0x00,data);
                      ResponseAPDU r2 = channel.transmit(c2);
                      System.out.println(r2);
                      receive=r2.getBytes();
                       if(bytestohex(receive).matches("9000"))
                        {
                              jComboBox4.setEnabled(true);
                              jButton6.setEnabled(true);
                              jButton11.setEnabled(true);
                              jButton7.setEnabled(true);
                              jButton10.setEnabled(true);
                              jFormattedTextField4.setEnabled(true);
                              jTextField2.setEnabled(true);
                              IsAuthenticated=true;
                              jLabel20.setText("                     ");
                              Textcolorchange("> General Authenticate\n" + "   Successful \n\n", Color.black);
                            
                        }
                        else
                        {
                            Textcolorchange("> General Authenticate" + "   Failed(SW1 SW2 ="+bytestohex(receive)+")\n\n", Color.red);
                             jButton6.setEnabled(false);
                             jButton11.setEnabled(false);
                             jButton7.setEnabled(false);
                             jFormattedTextField4.setEnabled(false);

                        }
                       if(ingreso == 1)
                        {
                            //Textcolorchange("> General Authenticate" + "   Failed(SW1 SW2 ="+bytestohex(receive)+")\n\n", Color.red);
                             jButton6.setEnabled(false);
                             jButton11.setEnabled(false);
                             jButton10.setEnabled(false);
                             jButton7.setEnabled(true);
                             jFormattedTextField4.setEnabled(false);

                        }   
                    }
                    catch(CardException e)
                    {
                        System.out.println(e.toString());
                    }
                }
                    
                    //////////////Authentication By Key Number using KEY B
            else if(jRadioButton2.isSelected()==true && jRadioButton2.isEnabled()==true && !(jComboBox3.getSelectedItem().toString().matches("")))
                {
                    byte data[]=new byte[5];
                    keynum =Byte.parseByte(jComboBox3.getSelectedItem().toString());
                    try
                    {
                      data[0]=(byte)0x1;
                      data[1]=(byte)0x0;
                      data[2]=currentBlock;
                      data[3]=(byte)0x61;
                      data[4]=keynum;
                      CommandAPDU c2= new CommandAPDU(0xff,0x86,0x00,0x00,data);
                      ResponseAPDU r2 = channel.transmit(c2);
                      System.out.println(r2);
                      receive=r2.getBytes();
                       if(bytestohex(receive).matches("9000"))
                        {
                              jComboBox4.setEnabled(true);
                              jButton6.setEnabled(true);
                              jButton11.setEnabled(true);
                              jButton7.setEnabled(true);
                              jButton10.setEnabled(true);
                              jFormattedTextField4.setEnabled(true);
                              jTextField2.setEnabled(false);
                              IsAuthenticated=true;
                              jLabel20.setText("                     ");
                              Textcolorchange("> General Authenticate\n" + "   Successful \n\n", Color.black);
                            
                        }
                        else
                        {
                            Textcolorchange("> General Authenticate" + "   Failed(SW1 SW2 ="+bytestohex(receive)+")\n\n", Color.red);
                             jButton6.setEnabled(false);
                             jButton7.setEnabled(false);
                             jButton10.setEnabled(false);
                             jFormattedTextField4.setEnabled(false);
                             jTextField2.setEnabled(false);
                        }
                    }
                    catch(CardException e)
                    {
                        System.out.println(e.toString());
                    }
                }
            else
                keych="";
            
        }
        else
        {
            jLabel13.setText("Select Block");
        }
        
       if(metodo == 1)
       {    
        Traer("02");
        String cadena = traerestado;
        char transpaso[]= cadena.toCharArray();
        String tt =""+ String.valueOf(transpaso[30])+String.valueOf(transpaso[31]);
        String h =""+ String.valueOf(transpaso[23])+String.valueOf(transpaso[24]+String.valueOf(transpaso[25]));
        String r = "";
        for(int i = 13;i<=21;i++ )
        {
            r= r + String.valueOf(transpaso[i]);
        }
        if(tt.equals("00"))
        {
            this.getFrame().setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null,"Tarjeta no relacionada a pasajero, Deve ingresar al pasajero, para mas informaciòn, dirijase a recepciòn", "Ventana", JOptionPane.ERROR_MESSAGE);
                 salir = true;
                 this.getFrame().setVisible(false);
                 this.getFrame().dispose();
        }
        else
        {
            String ttar = "";
            if(tt.equals("02"))
            {    
                 ttar = "Prepagada";
                this.texa = 1;
            }     
               
            if(tt.equals("01"))
            {    
                ttar = "Libre";
                this.texa = 0;
            }  
            this.getFrame().setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null,",Autentificaciòn de tarjeta correcta");
              this.getFrame().setAlwaysOnTop(true);   
             this.TextoRut.setText(r);
             this.TextoTarjeta.setText(ttar);
             this.jTextField3.setText(h);
             this.jTextField2.requestFocus();
                
        }    
        //JOptionPane.showMessageDialog(null,"Me ziviooooo rut: " +r+" habitacion: "+h,"Ventana",JOptionPane.INFORMATION_MESSAGE);
        
       }  
    }//GEN-LAST:event_jButton5MouseClicked
    
    //**************************************************************************
    //Method for Hexidecimal to Byte conversion
    //**************************************************************************
    public byte[] hexToBytes(String hexString) { 
         HexBinaryAdapter adapter = new HexBinaryAdapter(); 
         byte[] bytes = adapter.unmarshal(hexString); 
         return bytes; 
    } 
    
    //**************************************************************************
    //Method for Byte to Hexidecimal conversion
    //**************************************************************************
    public String bytestohex(byte hexbyte[])
    {
         String s="";
         String s1="";
         int n,x;
         for (n = 0; n < hexbyte.length; n++) 
                  {
                      x = (int) (0x000000FF & hexbyte[n]);  // byte to int conversion
                      s = Integer.toHexString(x).toUpperCase();
                      if (s.length() == 1) s = "0" + s;
                      s1=s1+s;
                  } 
         return s1;
    }
    
    //**************************************************************************
    //Method unsigned to signed Byte
    //**************************************************************************
   public  byte intToPseudoUnsignedByte(int n) 
   {
     if (n < 128) return (byte)n;
        return (byte) (n - 256);
   }
   
   //**************************************************************************
   //class for Hexidecimal to Byte 
   //**************************************************************************
   public byte[] fromHexString(String s) { 
    String[] v = s.split(" "); 
    byte[] arr = new byte[v.length]; 
    int i = 0; 
    for(String val: v) { 
        arr[i++] =  Integer.decode("0x" + val).byteValue(); 
 
    } 
    return arr; 
    } 

    //Read
    //********************************************************
    //Function Name: jButton6MouseClicked
    //Input(Parameter) : evt
    //OutPutParameter: -------
    //Description: Read the data from the card
    //APDU Description: ClassByte bcla = 0xFF, Instruction Byte bins=0xB0 ,Parameter P1=Address MSB , Parameter P2=Address LSB
    //                  Le=Number of Bytes to be Read 
    //********************************************************
    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
       String read_str;
        jLabel14.setText("");
        byte receive[];
        int cb=Integer.parseInt(jComboBox4.getSelectedItem().toString());
            currentBlock=intToPseudoUnsignedByte(cb);
            System.out.println(currentBlock);
        if(IsAuthenticated==true && !(jComboBox4.getSelectedItem().toString().matches("")))
        {
            try
            {
                    CommandAPDU c1= new CommandAPDU(0xff,0xb0,0x00,currentBlock,null,0x00,0x00,0x12);
                    ResponseAPDU r1 = channel.transmit(c1);
                    System.out.println(r1);
                    receive=r1.getBytes();
                    if(bytestohex(receive).substring(bytestohex(receive).length()-4, bytestohex(receive).length()).matches("6282"))
                    {
                        System.out.println(bytestohex(receive));
                        read_str=bytestohex(receive).substring(0, bytestohex(receive).length()-4);
                        jFormattedTextField4.setText(read_str);
                        int jfpas = validacion(read_str);
                        int jf4=0;
                        if(jfpas == 0)
                         {
                             jf4 = Integer.parseInt(jFormattedTextField4.getText().toString());
                         }
                        else
                         {   
                            jf4 = 0;
                         }   
                        jFormattedTextField4.setText(""+jf4);
                        acum = Integer.parseInt(jFormattedTextField4.getText().toString());
                        acum1 = 1;
                        jLabel22.setText("Total: "+acum);
                        jTextArea1.setText("");
                        if(texa == 0)
                        {    
                        jTextArea1.append("Total Consumo: "+acum+"\n"); //log textbox
                        jTextArea1.setCaretPosition(jTextArea1.getText().length());
                        } 
                        if(texa == 1)
                        {    
                        jTextArea1.append("Saldo Tarjeta : "+acum+"\n"); //log textbox
                        jTextArea1.setCaretPosition(jTextArea1.getText().length());
                        } 
                        Textcolorchange("> READ BINARY     \n    ( Block "+jComboBox4.getSelectedItem().toString() + ")   Successful \n\n", Color.black);
                      
                    }
                    else
                    {
                        Textcolorchange("> SCardTransmit" + "   Failed(SW1 SW2 ="+bytestohex(receive).substring(bytestohex(receive).length()-4, bytestohex(receive).length())+")\n\n", Color.red);
                    }
            }
            catch(CardException e)
            {
                System.out.println(e.toString());
            }
        }
        jLabel14.setText("");
        jFormattedTextField4.setText("");
        jTextField2.requestFocus();
    }//GEN-LAST:event_jButton6MouseClicked
    private void Traer(String numero)
   {
        String read_str;
        jLabel14.setText("");
        byte receive[];
        int cb=Integer.parseInt(numero);
            currentBlock=intToPseudoUnsignedByte(cb);
            System.out.println(currentBlock);
        if(IsAuthenticated==true )
        {
            try
            {
                    CommandAPDU c1= new CommandAPDU(0xff,0xb0,0x00,currentBlock,null,0x00,0x00,0x12);
                    ResponseAPDU r1 = channel.transmit(c1);
                    System.out.println(r1);
                    receive=r1.getBytes();
                    if(bytestohex(receive).substring(bytestohex(receive).length()-4, bytestohex(receive).length()).matches("6282"))
                    {
                        System.out.println(bytestohex(receive));
                        read_str=bytestohex(receive).substring(0, bytestohex(receive).length()-4);
                        if(cb == 1)
                        {    
                        int jf4 = Integer.parseInt(read_str.toString());
                       
                        acum = jf4;
                        jLabel22.setText(""+acum);
                        }
                        else
                        {    
                           if(cb == 2)
                           {    
                                
                                 traerestado = read_str.toString();
                                // JOptionPane.showMessageDialog(null,traerestado, "Ventana", JOptionPane.INFORMATION_MESSAGE);
                                 jLabel22.setText(""+acum);
                           }
                        } 
                    }
                    else
                    {
                        Textcolorchange("> SCardTransmit" + "   Failed(SW1 SW2 ="+bytestohex(receive).substring(bytestohex(receive).length()-4, bytestohex(receive).length())+")\n\n", Color.red);
                    }
            }
            catch(CardException e)
            {
                System.out.println(e.toString());
            }
        }
        jLabel14.setText("");
        
        }
    private int validacion(String numero)
    {
            int num = 0;
            char transpaso[]= numero.toCharArray();
            for(int i = 0; i< numero.length(); i++)
             {
               if(transpaso[i] != '0'&& transpaso[i] != '1'&& transpaso[i] != '2'&&transpaso[i] != '3'
                       && transpaso[i] != '4'&& transpaso[i] != '5'&& transpaso[i] != '6'
                       && transpaso[i] != '7'&& transpaso[i] != '8'&& transpaso[i] != '9' )
               {
                 num = 1;
               }  
            }   
            return num;
    }
    public void Autentificar(String num, String num1)
     {
        String keych;
        byte a1[]=new byte[1];
        byte receive[];
        
       
            int cb=Integer.parseInt(num);
            currentBlock=intToPseudoUnsignedByte(cb);
            System.out.println(currentBlock);
            keynum =Byte.parseByte(jComboBox2.getSelectedItem().toString());
            a1[0]=keynum;//Key Number
            
                    //////////////Authentication By Key Number using KEY A
            
                    byte data[]=new byte[5];
                    keynum =Byte.parseByte(num1);
                    try
                    {
                      data[0]=(byte)0x1;
                      data[1]=(byte)0x0;
                      data[2]=currentBlock;
                      data[3]=(byte)0x60;
                      data[4]=keynum;
                      CommandAPDU c2= new CommandAPDU(0xff,0x86,0x00,0x00,data);
                      ResponseAPDU r2 = channel.transmit(c2);
                      System.out.println(r2);
                      receive=r2.getBytes();
                       if(bytestohex(receive).matches("9000"))
                        {
                             
                              Textcolorchange("> General Authenticate\n" + "   Successful \n\n", Color.black);
                            
                        }
                        else
                        {
                            Textcolorchange("> General Authenticate" + "   Failed(SW1 SW2 ="+bytestohex(receive)+")\n\n", Color.red);
                             

                        }
                    }
                    catch(CardException e)
                    {
                        System.out.println(e.toString());
                    }
                
                    
                    //////////////Authentication By Key Number using KEY B
            
            
        
            
     } 
    public void Agregar(String num,String ingreso)
     {
        String s4;
        byte receive[];
        
             
             
            if(IsAuthenticated==true  )
            {
               
                int cb=Integer.parseInt(num);
                currentBlock=intToPseudoUnsignedByte(cb);
                System.out.println(currentBlock);
                s4= ingreso;
                String paso1 = s4;
                int largo = 32-paso1.length();
                String paso2 = "";
                for(int i =1; i<=largo; i++)
                {
                    paso2 ="0"+paso2;
                }
                paso2 = paso2 + paso1;
                jLabel22.setText(""+paso2);
                s4 = paso2;
                byte str3[]=hexToBytes(paso2);
                try
                {
                 CommandAPDU c1= new CommandAPDU(0xff,0xd6,0x00,currentBlock,str3);//0x00,0x00,0x12);
                 ResponseAPDU r1 = channel.transmit(c1);
                 System.out.println(r1);
                 receive=r1.getBytes();
                 System.out.println(bytestohex(receive));
                 if(bytestohex(receive).matches("9000"))
                        {
                             jTextArea1.setText("");
                             jTextArea1.append("Ingreso: "+s4+"\n"); //log textbox
                             jTextArea1.setCaretPosition(jTextArea1.getText().length());
                             Textcolorchange("> UPDATE BINARY   \n    ( Block "+num + ")   Successful \n\n", Color.black);

                        }
                 else
                        {
                            Textcolorchange("> SCardTransmit" + "   Failed(SW1 SW2 ="+bytestohex(receive).substring(bytestohex(receive).length()-4, bytestohex(receive).length())+")\n\n", Color.red);
                        }
                }
                catch(CardException e)
                {
                    System.out.println(e.toString());
                }
            }
            else
            {
               
                jLabel14.setText("Error en la transmision de datos");
            }
                 
     }
    public String RetornaTipo()
    {
                
                String cadena = traerestado;
                char transpaso[]= cadena.toCharArray();
                String a =""+ String.valueOf(transpaso[30])+String.valueOf(transpaso[31]);
                return a;
                
                
     }        
            
    //Write
    //********************************************************
    //Function Name:jButton7MouseClicked
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Write the data into the memory
    //APDU Description: ClassByte bcla = 0xFF, Instruction Byte bins=0xD6 ,Parameter P1=Address MSB , Parameter P2=Address LSB
    //                  Lc=Number of Bytes to be Updated and Data Input = Data  
    //********************************************************
    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:
       String s4;
        byte receive[];
        if(jFormattedTextField4.getText().toString().equals(""))//|| jTextField2.getText().equals("")) //datatextbox
        {
                   // jLabel14.setText("Deve ingresar datos");
                    this.getFrame().setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null,"Deve ingresar monto", "Ventana", JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField4.requestFocus();
                    this.getFrame().setAlwaysOnTop(true);
        }
        else
        {
         if(pre == 1)// recargar la tarjeta prepagaa
        {
                   
                    s4= jFormattedTextField4.getText().toString();
                    int paso = 0;
                    int jfpas = validacion(s4);
                    int jf4=0;
                    if(jfpas == 0)
                     {
                         paso = Integer.parseInt(s4);
                     }
                     else
                     {   
                        paso = 0;
                     }
                    Traer("01");
                    acum = acum + paso;
                   Traer("02");
                   String cadena = traerestado;
                    char transpaso[]= cadena.toCharArray();
                    String tt =""+ String.valueOf(transpaso[30])+String.valueOf(transpaso[31]);
                    String et =""+ String.valueOf(transpaso[27])+String.valueOf(transpaso[28]);
                    String nh =""+ String.valueOf(transpaso[23])+String.valueOf(transpaso[24])+String.valueOf(transpaso[25]);
                    String rp ="";
                    for (int i = 13; i<=21 ; i++)
                    {
                        rp = rp + String.valueOf(transpaso[i]);
                    }    
                    String ip = "";
                    boolean sal = true;
                    boolean acumulando = false;
                    int contador = 0;
                    while(sal)
                    {
                       if(transpaso[contador] == 'D')
                       {
                           acumulando = true;
                           contador++;
                       }
                       
                       if(acumulando)
                       {
                           if(transpaso[contador] == 'B')
                           {
                               sal = false;
                           }
                           else
                           {
                             ip = ip+transpaso[contador];
                           }
                       }
                       contador ++;
                       
                    }
                    
                     String saldo = "";
                     int cont = 0;
                     boolean salgo = false;
                     while(salgo == false)
                     {
                          if(transpaso[cont] == 'D')
                          {
                             salgo = true;
                             cont = cont-2;
                          }
                          else
                          {
                             if(Character.isDigit(transpaso[cont]))
                             saldo = saldo + transpaso[cont]; 
                          }
                          cont++;
                     }
                     int saldo1 = 0;
                     int jifpas = validacion(saldo);
                     if(jifpas == 0)
                     {
                      saldo1 = Integer.parseInt(saldo);
                      saldo1 = saldo1+paso;
                      
                     }
                     else
                     {
                         saldo1 = 0;
                     }
                    
                  
                     String p =saldo1+"d"+ip+"b"+ rp+"a"+nh+"e"+et+"c"+tt;
                    String paso4 = ""+acum;
                    Autentificar("01","01");
                    Agregar("01",paso4);
                    Autentificar("01","02");
                    Agregar("02",p);
                    this.getFrame().setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null,"Su nuevo saldo es: "+paso4);
                    this.getFrame().setAlwaysOnTop(true);
                    jButton5.setEnabled(true);
                      jButton6.setEnabled(true);
                      jButton10.setEnabled(true);
                      jButton11.setEnabled(true);
                      jTextField2.setEnabled(true);
                      pre = 0;
           
                      jFormattedTextField4.setText("");
                      jTextField2.setText("");
                      jTextField2.requestFocus();
              
         }
        else
        {    
          s4 = jFormattedTextField4.getText().toString();  
          Traer("02");
          int ve = validacion(traerestado);
          if(ve!= 0 && ingreso ==  1)//ver si tarjeta ya esta en uso
          {
              this.getFrame().setAlwaysOnTop(false);
              JOptionPane.showMessageDialog(null,"La tarjeta ya esta en uso, para volver a usarla borre sus datos", "Ventana", JOptionPane.ERROR_MESSAGE);
                
              this.getFrame().setAlwaysOnTop(true);
              jButton6.setEnabled(false);
              jButton7.setEnabled(false);
              jButton10.setEnabled(false);
              jButton11.setEnabled(true);
              jFormattedTextField4.setEnabled(false);
              jTextField2.setEnabled(false);
              jButton5.setEnabled(false);
                 
           }
          else
          {
              if(ve == 0 && ingreso == 1)//ingreso de pasajero
              {
              
                
                             Utilidades ut = new Utilidades();
                            Calendar c1 = Calendar.getInstance();
                            int dia = c1.get(Calendar.DATE);
                            int mes = c1.get(Calendar.MONTH);
                            int annio = c1.get(Calendar.YEAR);
                            int hora = c1.get(Calendar.HOUR);
                            int minuto = c1.get(Calendar.MINUTE);
                            int segundo = c1.get(Calendar.SECOND);
                            String dia1 = ut.validadia(dia);
                            String mes1 = ut.validames(mes) ;
                            String hora1 = ut.validadia(hora); 
                            String minuto1 = ut.validadia(minuto) ;
                            String segundo1 = ut.validadia(segundo);
                            String horas = hora1+":"+minuto1+":"+segundo1;
                            String fecha = annio+"/"+mes1+"/"+dia1;
                            Pax pasajero = new Pax(this.rutpas,this.nombrepas,this.numhab,fecha,fecha,horas,horas,this.jLabel15.getText().toString());
                           
                            int ingres = ut.insertarPax(pasajero);
                            int idpas = ut.BuscarId(numhab,rutpas,this.jLabel15.getText().toString());
                            
                           Habitacion hab = new Habitacion(numhab,1);
                           int respuestahab = ut.actualizarHab(hab);
                          if(ingres != 1 || respuestahab != 1)
                          { 
                              this.getFrame().setAlwaysOnTop(false);
                            JOptionPane.showMessageDialog(null,"Los datos no han podido guardarse en la base de datos","Ventana",JOptionPane.ERROR_MESSAGE);  
                             this.getFrame().setAlwaysOnTop(true);
                          }
                          else
                          {
                            if(this.tipotarj.equals("Prepagada"))
                            {
                            //String pasid =""+idpas;
                            
                            Autentificar("01","01");
                            Agregar("01",s4);          
                            String p =s4+"d"+idpas+"b"+ this.rutpas+"a"+this.numhab+"e"+"01"+"c"+"02";
                           // s4+"d"+
                            Autentificar("01","02");
                            Agregar("02",p);
                            this.ingreso = 0;
                            this.getFrame().setAlwaysOnTop(false);
                            JOptionPane.showMessageDialog(null,"Tarejeta prepagada habilitada, ingreso correcto");
                             
                            salir = true;
                            this.getFrame().setVisible(false);
                            this.getFrame().dispose();
                                            
                          }           
                         
                          else
                          {
                            if(this.tipotarj.equals("Libre"))
                            {
                                 Autentificar("01","01");
                                 Agregar("01",s4); 
                                 String p =idpas + "b"+ this.rutpas+"a"+this.numhab+"e"+"01"+"c"+"01";
                                 Autentificar("01","02");
                                 this.ingreso = 0 ;
                                 Agregar("02",p);
                                 this.getFrame().setAlwaysOnTop(false);
                                 JOptionPane.showMessageDialog(null,"Tarejeta libre habilitada, ingreso correcto"); 
                                 
                                 salir = true;
                                 this.getFrame().setVisible(false);
                                 this.getFrame().dispose();
                             } 
                          }   
                 
                 } 
              }
              else
              {
                if(ve == 0 && ingreso == 0)//ingreso de pasajero
                {
                 this.getFrame().setAlwaysOnTop(false);
                 JOptionPane.showMessageDialog(null,"Tarjeta no relacionada a pasajero, Deve ingresar al pasajero, para mas informaciòn, dirijase a recepciòn", "Ventana", JOptionPane.ERROR_MESSAGE);
                
               salir = true;
                this.getFrame().setVisible(false);
                this.getFrame().dispose();
                 
                }
                
                else
                {
                 if(ve == 1 && ingreso == 0)//ingreso de pasajero
                 {
                    
                   String prod= "";
                   int produc = 0;
                     if(jTextField2.getText().toString().equals(""))
                           produc = 1;
             
                 if(produc == 0 && IsAuthenticated==true && !(jComboBox4.getSelectedItem().toString().matches("")) && jFormattedTextField4.getText().toString().length()>0)
                  {
                    String cadena = traerestado;
                    char transpaso[]= cadena.toCharArray();
                    String tt =""+ String.valueOf(transpaso[30])+String.valueOf(transpaso[31]);
                    String idp = "";
                    int idp1 = 0;
                    if(tt.equals("02"))
                {
                    String saldo = "";
                    int cont = 0;
                    boolean salgo = false;
                    for(int i = 0; i <12; i++)
                    {
                            if(transpaso[i] == 'D')
                            {
                               salgo = true;
                               
                            }
                            else if(salgo)
                            {
                               if(Character.isDigit(transpaso[i]))
                               idp = idp + transpaso[i]; 
                             }
                        
                     }
               
                   
                 }
                if(tt.equals("01"))
                {
                   for(int i = 0; i <12; i++)
                   {
                      idp =idp + String.valueOf(transpaso[i]);
                   }
                 
                }
                    idp1 = Integer.parseInt(idp);
                    int cb=Integer.parseInt(jComboBox4.getSelectedItem().toString());
                    currentBlock=intToPseudoUnsignedByte(cb);
                    System.out.println(currentBlock);
                    s4= jFormattedTextField4.getText().toString();
                    int paso = 0;
                    int jfpas = validacion(s4);
                    int jf4=0;
                    if(jfpas == 0)
                     {
                         paso = Integer.parseInt(s4);
                     }
                     else
                     {   
                        paso = 0;
                     }   
                     Traer("01");
                    if(acum < paso && tt.equals("02") )//ingreso de pasajero
                    {
                      this.getFrame().setAlwaysOnTop(false);
                      JOptionPane.showMessageDialog(null,"Tarjeta prepagada exede el monto, su saldo es: "+acum+" , por favor cargue dinero", "Ventana", JOptionPane.ERROR_MESSAGE);
                      this.getFrame().setAlwaysOnTop(true);
                      this.pre = 1;
                      //6,10,11,5, jt2
                      jButton5.setEnabled(false);
                      jButton6.setEnabled(false);
                      jButton10.setEnabled(false);
                      jButton11.setEnabled(false);
                      jTextField2.setEnabled(false);
                      this.jFormattedTextField4.setText("");
                      this.jFormattedTextField4.requestFocus();
                    }
                   else
                   { 
                    if(tt.equals("01"))
                    acum = acum + paso;
                    if(tt.equals("02"))
                    acum = acum - paso;
                    Utilidades ut = new Utilidades();
                    String recibir = ut.buscarPax(TextoRut.getText().toString());
                    int idppp = idp1;
                    int resp1 = 0;
                    
                      Date fecha = new Date();
                      Calendar c3 = Calendar.getInstance();
                      Calendar c2 = new GregorianCalendar();
                      int dia = c3.get(Calendar.DATE);
                      int mes = c3.get(Calendar.MONTH);
                      int annio = c3.get(Calendar.YEAR);
                      int hora = c3.get(Calendar.HOUR);
                      int minuto = c3.get(Calendar.MINUTE);
                      int segundo = c3.get(Calendar.SECOND);
                      String dia1 = ut.validadia(dia);
                      String mes1 = ut.validames(mes) ;
                      String hora1 = ut.validadia(hora); 
                      String minuto1 = ut.validadia(minuto) ;
                      String segundo1 = ut.validadia(segundo);
                      String horas = hora1+":"+minuto1+":"+segundo1;
                      String fecha1 = annio+"/"+mes1+"/"+dia1;
                      String rut_us_in = jframeHiatt.rut_us_in;
                      montos mon = new montos(idp1,jLabel15.getText().toString(),jTextField2.getText().toString(),paso,fecha1,horas,1,rut_us_in);
                      
                      resp1 = ut.insertarMontos(mon);
                   
                      
                    if(resp1!=1)
                    {
                        this.getFrame().setAlwaysOnTop(false);
                        JOptionPane.showMessageDialog(null,"Los datos no han podido guardarse en la base de datos", "Ventana", JOptionPane.ERROR_MESSAGE);
                        this.getFrame().setAlwaysOnTop(true);
                    }
                    else
                    {
                    jLabel22.setText(""+acum);
                    String paso1 = jLabel22.getText();
                    int largo = 32-paso1.length();
                    String paso2 = "";
                    for(int i =1; i<=largo; i++)
                    {
                        paso2 ="0"+paso2;
                    }
                    paso2 = paso2 + paso1;
                    jLabel22.setText(""+paso2);
                    s4 = paso2;
                    byte str3[]=hexToBytes(paso2);
                    try
                    {
                     CommandAPDU c1= new CommandAPDU(0xff,0xd6,0x00,currentBlock,str3);//0x00,0x00,0x12);
                     ResponseAPDU r1 = channel.transmit(c1);
                     System.out.println(r1);
                     receive=r1.getBytes();
                     System.out.println(bytestohex(receive));
                     if(bytestohex(receive).matches("9000"))
                        {
                            if(texa == 0)
                            {    
                             jTextArea1.setText("");
                             jTextArea1.append("Consumo registrado: "+paso+"\n"); //log textbox
                             jTextArea1.setCaretPosition(jTextArea1.getText().length());
                             Textcolorchange("> UPDATE BINARY   \n    ( Block "+jComboBox4.getSelectedItem().toString() + ")   Successful \n\n", Color.black);
                             this.jFormattedTextField4.setText("");
                             this.jTextField2.requestFocus();
                            } 
                            if(texa == 1)
                            {    
                             jTextArea1.setText("");
                             jTextArea1.append("Monto descontado: "+paso+"\n"); //log textbox
                             jTextArea1.setCaretPosition(jTextArea1.getText().length());
                             Textcolorchange("> UPDATE BINARY   \n    ( Block "+jComboBox4.getSelectedItem().toString() + ")   Successful \n\n", Color.black);
                             this.jFormattedTextField4.setText("");
                             this.jTextField2.setText("");
                             this.jTextField2.requestFocus();
                            } 
                        }
                        else
                        {
                            Textcolorchange("> SCardTransmit" + "   Failed(SW1 SW2 ="+bytestohex(receive).substring(bytestohex(receive).length()-4, bytestohex(receive).length())+")\n\n", Color.red);
                        }
                }
                catch(CardException e)
                {
                    System.out.println(e.toString());
                }
               }
              }     
            }
            else
            {
               if(jFormattedTextField4.getText().toString().length()<1)

                    jLabel14.setText("El monto debe ser numèrico");
                if(jFormattedTextField4.getText().toString().length()>32)
                jLabel14.setText("El numero extiende el rango");
                if(produc == 1)
                {          this.getFrame().setAlwaysOnTop(false);
                           JOptionPane.showMessageDialog(null,"Deve ingresar el nombre del producto", "Ventana", JOptionPane.ERROR_MESSAGE);
                           this.getFrame().setAlwaysOnTop(true);
                           produc = 0;
                           jTextField2.requestFocus();
                }
                if(jFormattedTextField4.getText().toString().equals(""))
                {
                    this.getFrame().setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null,"Deve ingresar monto", "Ventana", JOptionPane.ERROR_MESSAGE);
                    jFormattedTextField4.requestFocus();
                    this.getFrame().setAlwaysOnTop(true);
                }
             }
            }
           }
                
          }
        }
       }   
      }
    }//GEN-LAST:event_jButton7MouseClicked
    
    //********************************************************
    //Function Name:jButton8MouseClicked
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Clear the return values shown in Text pane
    //********************************************************
    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked
             
        StyledDocument doc = jTextPane2.getStyledDocument();
        try{ 
        doc.remove(0,doc.getLength() );
        }
        catch(BadLocationException e)
        {
            System.out.println(e.toString());
        }
    }//GEN-LAST:event_jButton8MouseClicked
    
    //********************************************************
    //Function Name:jRadioButton1ActionPerformed
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on jRadioButton ActionPerformed
    //********************************************************
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        jRadioButton1.setSelected(true);
        jRadioButton2.setSelected(false);
        jComboBox3.setEnabled(true);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jButton10.setEnabled(false);
        jFormattedTextField4.setEnabled(false);
        jTextField2.setEnabled(false);
        jLabel14.setText("");
        jLabel20.setText("                     ");
        jComboBox3.setSelectedIndex(0);
    }//GEN-LAST:event_jRadioButton1ActionPerformed
    
    //********************************************************
    //Function Name:jRadioButton2ActionPerformed
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on jRadioButton ActionPerformed
    //********************************************************
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        jRadioButton2.setSelected(true);
        jRadioButton1.setSelected(false);
       
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jButton10.setEnabled(false);
        jFormattedTextField4.setEnabled(false);
        jTextField2.setEnabled(false);
        jComboBox3.setSelectedIndex(0);
        jComboBox3.setEnabled(true);
        jLabel14.setText("");
    }//GEN-LAST:event_jRadioButton2ActionPerformed
   
    //********************************************************
    //Function Name:jFormattedTextField2KeyPressed
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on jFormatted TextField Key Pressed
    //********************************************************    
    //********************************************************
    //Function Name:jFormattedTextField3KeyPressed
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on jFormatted TextField Key Pressed
    //********************************************************    
    //********************************************************
    //Function Name:jFormattedTextField4KeyPressed
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on jFormatted TextField Key Pressed
    //********************************************************    
    //********************************************************
    //Function Name:jFormattedTextField2MouseClicked
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on jFormatted TextField Mouse Clicked
    //********************************************************    
    //********************************************************
    //Function Name:jFormattedTextField3MouseClicked
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on jFormatted TextField Mouse Clicked
    //********************************************************    
    //********************************************************
    //Function Name:jFormattedTextField4MouseClicked
    //Input(Parameter) : evt
    //OutPutParameter:-------
    //Description:Event on jFormatted TextField Mouse Clicked
    //********************************************************
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jFormattedTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==37)
        {
        chat4++;
        }
        else if(evt.getKeyCode()==39)
        {
        chat14++;
        }
        int x=evt.getKeyCode();
       if((x>47 && x<58 || x>64 && x<71 || x>96 && x<103 || (x>36 && x<41) || (x==8))  && (jFormattedTextField4.getText().length()<=32))
        {
            jLabel14.setText("");
            jFormattedTextField4.setBackground(Color.white);
        }
        else 
        {
            jLabel14.setText("Enter HEX Value Only");
        }
        if(!(jFormattedTextField4.getText().toString().trim().length()<=33 || evt.getKeyChar()==8))
        {
            evt.setKeyCode(8);
            jLabel14.setText("Data cannot exceed 16 Bytes");
        }
        
      
}//GEN-LAST:event_jFormattedTextField4KeyPressed

    
    
    private void jFormattedTextField4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFormattedTextField4MouseReleased
        
        if(evt.isPopupTrigger() && jFormattedTextField4.isEnabled() ) {
            JPopupMenu Pmenu = new JPopupMenu();
            JMenuItem menuItem = new JMenuItem("Cut      Ctr+X");
            Pmenu.add(menuItem);
            menuItem.addActionListener(this);
            menuItem = new JMenuItem("Copy   Ctr+C");
            Pmenu.add(menuItem);
            menuItem.addActionListener(this);
            menuItem = new JMenuItem("Paste   Ctr+V");
            Pmenu.add(menuItem);
            menuItem.addActionListener(this);
            Pmenu.show(evt.getComponent(), evt.getX(), evt.getY());
            
        }
}//GEN-LAST:event_jFormattedTextField4MouseReleased

    private void jFormattedTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFormattedTextField4MouseClicked
        // TODO add your handling code here:
        // jFormattedTextField4.setCaretPosition(0);
        jFormattedTextField4.setText("");
}//GEN-LAST:event_jFormattedTextField4MouseClicked

    private void jFormattedTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField4KeyReleased
        // TODO add your handling code here:
     
        int x=evt.getKeyCode();
       System.out.print(x);
        String st=jFormattedTextField4.getText();
        int len=st.length();
        String st1="";
        for(int i=0;i<len;i++)
        {
            char ch=st.charAt(i);
            if(((ch>47 && ch<58) || (ch>64 && ch<71) || (ch>96 && ch<103) || (ch==8)&&(len<=32) ) )
            {
            st1=st1+ch;  
            }
        }

   if((len==32) && (!(x>47 && x<58 || x>64 && x<71 || x>96 && x<103 || (x>36 && x<41) || (x==8))))
        {
        st1+=ch1;
        }
        jFormattedTextField4.setText(st1);
   if(jFormattedTextField4.getText().length()==32)
        {
        ch1=jFormattedTextField4.getText().charAt(31);
        }
       try{
            if((jFormattedTextField4.getCaretPosition()>0 || jFormattedTextField4.getCaretPosition()<len ) &&((len-chat4+chat14)>=0 && (len-chat4+chat14)<len))
             {
                jFormattedTextField4.setCaretPosition(len-chat4+chat14);
             }
            else 
            {
                chat4=0;chat14=0;
            }
         }catch(Exception ex){}  
       if(jFormattedTextField4.getText().toString().trim().length()>32)
        {
            String st2=jFormattedTextField4.getText();
            st2=st2.substring(0, 32);
            jFormattedTextField4.setText(st2);
             jLabel14.setText("Data cannot exceed 16 Bytes");
        }
        
        
        
    }//GEN-LAST:event_jFormattedTextField4KeyReleased

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        
        // TODO add your handling code here:
        String s4;
        int paso33= 0;
        byte receive[];
         if(IsAuthenticated==true && !(jComboBox4.getSelectedItem().toString().matches("")) )
            {   
                Traer("02");
                String cadena = traerestado;
                String a = "";
                char transpaso[]= cadena.toCharArray();
                String tt =""+ String.valueOf(transpaso[30])+String.valueOf(transpaso[31]);
                 String ip = "";
                    boolean sal = true;
                    boolean acumulando = false;
                    int contador = 0;
                    int ipn = 0;
                    if(tt.equals("02"))
                    {
                    while(sal)
                    {
                       if(transpaso[contador] == 'D')
                       {
                           acumulando = true;
                           contador++;
                       }
                       
                       if(acumulando)
                       {
                           if(transpaso[contador] == 'B')
                           {
                               sal = false;
                           }
                           else
                           {
                             ip = ip+transpaso[contador];
                           }
                       }
                       contador ++;
                       
                      }
                       Utilidades utilid = new Utilidades();
                       boolean esnum = utilid.isNumero(ip);
                       if(esnum)
                       ipn = Integer.parseInt(ip);
                       
                    }
                    if(tt.equals("01"))
                    {
                     while(sal)
                     {
                         if(transpaso[contador] == 'B')
                             sal = false;
                         else
                             ip = ip+transpaso[contador];
                         contador++;
                     }
                     Utilidades utilid = new Utilidades();
                     boolean pasarip = utilid.isNumero(ip);
                     
                     if(pasarip)
                     {
                      ipn = Integer.parseInt(ip);
                      ip = Integer.toString(ipn);
                     }
                     else
                     {
                         ip = "0";
                     }
                    }
                
                   
               
                if(tt.equals("02"))
                {
                    String saldo = "";
                    int cont = 0;
                    boolean salgo = false;
                    for(int i = 0; i <12; i++)
                    {
                            if(transpaso[i] == 'D')
                            {
                               salgo = true;
                               
                            }
                            else if(salgo)
                            {
                               if(Character.isDigit(transpaso[i]))
                               saldo = saldo + transpaso[i]; 
                             }
                        
                     }
               
                    paso33 = Integer.parseInt(saldo);
                 }
                else if(tt.equals("01"))
               {
                   for(int i = 0; i <12; i++)
                   {
                      a =a + String.valueOf(transpaso[i]);
                   }
                  paso33 = Integer.parseInt(a);
                }
                if(tt.equals("01")|| tt.equals("02"))
                {
                    
                Utilidades u = new Utilidades();
                Calendar c1 = Calendar.getInstance();
                int dia = c1.get(Calendar.DATE);
                int mes = c1.get(Calendar.MONTH);
                int annio = c1.get(Calendar.YEAR);
                int hora = c1.get(Calendar.HOUR);
                int minuto = c1.get(Calendar.MINUTE);
                int segundo = c1.get(Calendar.SECOND);
                String dia1 = u.validadia(dia);
                String mes1 = u.validames(mes) ;
                String hora1 = u.validadia(hora); 
                String minuto1 = u.validadia(minuto) ;
                String segundo1 = u.validadia(segundo);
                String horas = hora1+":"+minuto1+":"+segundo1;
                String fecha = annio+"/"+mes1+"/"+dia1;
                int paseo = u.actualizarPax(paso33,fecha,horas);
                String actuHab = u.buscarHab(paso33);
                int actuahab = 0;
                int actuamon = 0;
                if(!"".equals(actuHab))
                {
                   Habitacion hab = new Habitacion(actuHab,0);
                   actuahab = u.actualizarHab(hab);
                }
                if(actuahab == 1)
                {
                    actuamon = u.actualizarMontos(ipn, 0);
                }
                
                    
                if(paseo != 1 || actuahab != 1)
                { 
                    this.getFrame().setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null,"No se ha podido inhabilitar pasajero");
                    this.getFrame().setAlwaysOnTop(true);
                }
                else if(actuamon == 0)
                {
                    this.getFrame().setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null,"No se ha podido modificar el monto");
                    this.getFrame().setAlwaysOnTop(true);
                }
                 
                }
                Autentificar("01","01");
                Agregar("01","00");
                Autentificar("01","02");
                Agregar("02","00");
                this.getFrame().setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null,"Datos Eliminados la tarjeta esta lista para usarse nuevamente");
                
                salir = true;
                this.getFrame().setVisible(false);
                t.stop();  
                this.getFrame().dispose();
               
                   JOptionPane.showMessageDialog(null,"Se ha inhabilitado pasajero"); 
             
              
            }
    }//GEN-LAST:event_jButton11MouseClicked

    private void BotonSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonSalirMouseClicked
        // TODO add your handling code here:
        this.getFrame().setAlwaysOnTop( false );
        JOptionPane.showMessageDialog(null,"Operaciòn finalizada");
              
        this.taskperformer = null;
        salir = true;
        this.getFrame().dispose();
      
        
    }//GEN-LAST:event_BotonSalirMouseClicked

    private void jFormattedTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField2KeyReleased
        
        
        int x=evt.getKeyCode();
        System.out.print(x);
        String st=jFormattedTextField2.getText();
        int len=st.length();
        String st1="";
        for(int i=0;i<len;i++) {
            char ch=st.charAt(i);
            if(((ch>47 && ch<58) || (ch>64 && ch<71) || (ch>96 && ch<103) || (ch==8)&&(len<=12) ) ) {
                st1=st1+ch;
            }
        }
        
        if((len==12) && (!(x>47 && x<58 || x>64 && x<71 || x>96 && x<103 || (x>36 && x<41) || (x==8)))) {
            st1+=ch1;
        }
        jFormattedTextField2.setText(st1);
        if(jFormattedTextField2.getText().length()==12) {
            ch1=jFormattedTextField2.getText().charAt(11);
        }
        try{
            if((jFormattedTextField2.getCaretPosition()>0 || jFormattedTextField2.getCaretPosition()<len ) &&((len-chat+chat1)>=0 && (len-chat+chat1)<len)) {
                jFormattedTextField2.setCaretPosition(len-chat+chat1);
            } else {
                chat=0;chat1=0;
            }
        }catch(Exception ex){}
        if(jFormattedTextField2.getText().toString().trim().length()>12) {
            String st2=jFormattedTextField2.getText();
            st2=st2.substring(0, 12);
            jFormattedTextField2.setText(st2);
            jLabel19.setText("Data cannot exceed 6 Bytes");
        }
        
    }//GEN-LAST:event_jFormattedTextField2KeyReleased

    private void jFormattedTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField2KeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==37) {
            chat++;
        } else if(evt.getKeyCode()==39) {
            chat1++;
        }
        int x=evt.getKeyCode();
        if((x>47 && x<58 || x>64 && x<71 || x>96 && x<103 || (x>36 && x<41) || (x==8))  && (jFormattedTextField4.getText().length()<=12)) {
            jLabel19.setText("");
            jFormattedTextField2.setBackground(Color.white);
        } else {
            jLabel19.setText("Enter HEX Value Only");
        }
        if(!(jFormattedTextField2.getText().toString().trim().length()<=13 || evt.getKeyChar()==8)) {
            evt.setKeyCode(8);
            jLabel19.setText("Data cannot exceed 6 Bytes");
        }
        
    }//GEN-LAST:event_jFormattedTextField2KeyPressed

    private void jFormattedTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFormattedTextField2MouseClicked
        // TODO add your handling code here:
        //jFormattedTextField2.setCaretPosition(0);
}//GEN-LAST:event_jFormattedTextField2MouseClicked

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        jFormattedTextField2.setBackground(Color.white);
        jLabel19.setText("                     ");
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:
        String  keych1 ;
        byte receive[];
        
        try {
            if( !(jFormattedTextField2.getText().toString().trim().equals("")) && !(jComboBox2.getSelectedItem().toString().equals("")) && (jFormattedTextField2.getText().toString().length()==12)) {
                keych1=jFormattedTextField2.getText().toString();
                jFormattedTextField2.setText("");
                keynum =Byte.parseByte(jComboBox3.getSelectedItem().toString());
                byte str3[]=hexToBytes(keych1);
                CommandAPDU c1= new CommandAPDU(0xff,0x82,0x20,keynum,str3);
                ResponseAPDU r1 = channel.transmit(c1);
                receive=r1.getBytes();
                if(bytestohex(receive).matches("9000")) {
                    System.out.println("success");
                    Textcolorchange("> LOAD KEY ( No. " + jComboBox2.getSelectedItem().toString() + " )\n   Successful \n\n", Color.black);
                    jLabel19.setText("               ");
                    jComboBox3.setEnabled(true);
                } else {
                    Textcolorchange("> Load Key" + "   Failed\n(SW1 SW2 =" +bytestohex(receive)+")\n\n", Color.red);
                    jComboBox3.setEnabled(false);
                }
            } else {
                if(jFormattedTextField2.getText().toString().trim().equals(" ")) {
                    jFormattedTextField2.setBackground(Color.red);
                    jLabel19.setText("Enter Key of 6 bytes");
                } else {
                    jFormattedTextField2.setBackground(Color.red);
                    jLabel19.setText("Enter Key of 6 bytes");
                }
            }
        } catch(CardException e) {
            System.out.println(e.toString());
        }
        
    }//GEN-LAST:event_jButton9MouseClicked

    private void jComboBox1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1FocusGained
        // TODO add your handling code here:
        try{
            readerName=jComboBox1.getSelectedItem().toString();
            
            pasanom = jComboBox1.getItemAt(indice).toString();
            readerName = pasanom;
            mensaje.setText(pasanom.toString());
        } catch(Exception e){
            System.out.println(e.toString());
        }
}//GEN-LAST:event_jComboBox1FocusGained

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        jTextField1.setText("");
        jComboBox3.setSelectedIndex(0);
        jTextArea1.setText("");
        Disabled_Enabled_Controls();
        jTextField1.setBackground(Color.white);
        jTextField1.setForeground(Color.black);
        t.stop();
        readerName = jComboBox1.getSelectedItem().toString();
        readerName = pasanom;
}//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        
        try{
            // disconnect
            card.disconnect(false);
            if(card.toString().contains("DISCONNECT")) {
                //System.out.println(card);
                jButton3.setEnabled(true); //Connect enable
                Disabled_Enabled_Controls();
                jButton4.setEnabled(false);
                
                if(jRadioButton1.isEnabled()==true)
                    jComboBox3.setSelectedIndex(0);
                Textcolorchange("> SCardDisconnect\n" + "     Successful \n\n", Color.black);
            }
        } catch(CardException e) {
            System.out.println(e.toString());
        }
}//GEN-LAST:event_jButton4MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        
        String s="";
        String s1="";
        String atr_temp="";
        String uid_temp="";
        int atr_byte =0;
        int n,x;
        
        try {
            //establece conexion con la tarjeta
            card = terminal.connect("T=1");
            System.out.print(card);
            
            channel = card.getBasicChannel();
            //////////ATR////////
            ATR r2= channel.getCard().getATR();
            byte atr[]=r2.getBytes();
            
            
            
            for (n = 0; n < atr.length; n++) {
                x = (int) (0x000000FF & atr[n]);  // conversion de byte a int
                s = Integer.toHexString(x).toUpperCase();
                if (s.length() == 1) s = "0" + s;
                s1=s1+s+" ";
                
            }
            atr_temp=s1;
            jLabel16.setText("ATR ="+atr_temp);
            
            try {
                atr_byte=atr[14];
                
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println(e.toString());
            }
            
            
            //////////UID///////////
            s1="";
            CommandAPDU c2= new CommandAPDU(0xff,0xCA,0x00,0x00,null,0x00,0x00,0x1);
            ResponseAPDU r1 = channel.transmit(c2);
            byte uid[]=r1.getBytes();
            
            
            for (n = 0; n < uid.length-2; n++) {
                x = (int) (0x000000FF & uid[n]);  // conversion de byte a int
                s = Integer.toHexString(x).toUpperCase();
                if (s.length() == 1) s = "0" + s;
                s1=s1+s+" ";
            }
            uid_temp=s1;
            if(uid_temp.matches("")) {
                
            } else {
                jLabel15.setText("UID ="+uid_temp);
            }
            
            
            
            //Tipo de tarjeta
            if(atr_byte==1) {
                card_Type=1;
                card_Type_Identification();
                jLabel17.setText("Card Type: Mifare 1K");
                jButton3.setEnabled(false);
                jButton4.setEnabled(true);
                jComboBox2.setEnabled(true);
                jFormattedTextField2.setEnabled(true);
                jButton9.setEnabled(true);
                jRadioButton1.setEnabled(true);
                jRadioButton2.setEnabled(true);
                jComboBox4.setEnabled(true);
                jButton5.setEnabled(true);
                jComboBox2.setSelectedIndex(0);
                jComboBox3.setSelectedIndex(0);
                jComboBox4.setSelectedIndex(0);
                jTextField1.setText("Tarjeta Insertada");
                jTextField1.setBackground(new Color(173,255, 47));
                jTextField1.setForeground(Color.black);
                if(jRadioButton2.isSelected()==true) {
                    jComboBox3.setEnabled(true);
                } else if(jRadioButton1.isEnabled()==true) {
                    jComboBox3.setEnabled(true);
                    jComboBox3.setSelectedIndex(0);
                }
                
            } else if(atr_byte==2) {
                card_Type=2;
                card_Type_Identification();
                jLabel17.setText("Card Type: Mifare 4K");
                jButton3.setEnabled(false);
                jButton4.setEnabled(true);
                jComboBox2.setEnabled(true);
                jFormattedTextField2.setEnabled(true);
                jButton9.setEnabled(true);
                jRadioButton1.setEnabled(true);
                jRadioButton2.setEnabled(true);
                // jComboBox3.setEnabled(true);
                jComboBox4.setEnabled(true);
                jButton5.setEnabled(true);
                jComboBox2.setSelectedIndex(0);
                jComboBox3.setSelectedIndex(0);
                jComboBox4.setSelectedIndex(0);
                jTextField1.setText("Targeta Insertada");
                jTextField1.setBackground(new Color(173,255, 47));
                jTextField1.setForeground(Color.black);
                if(jRadioButton2.isSelected()==true) {
                    jComboBox3.setEnabled(true);
                } else if(jRadioButton1.isEnabled()==true) {
                    jComboBox3.setEnabled(true);
                    jComboBox3.setSelectedIndex(0);
                }
                
            } else if(uid_temp.matches("") ) {
                jLabel15.setText("UID = n/a");
                jLabel17.setText("                            Card Type: No Mifare 1K or 4K Card ");
                jButton3.setEnabled(false);
                jButton4.setEnabled(true);
            } else {
                jLabel17.setText("                            Card Type: No Mifare 1K or 4K Card ");
                jButton3.setEnabled(false);
                jButton4.setEnabled(true);
            }
            Textcolorchange("> SCardConnect \n     Successsful\n\n",Color.black);
            
        } catch(CardException e) {
            Textcolorchange("> SCardConnect" + "\n   Failed... \n\n",Color.red);
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        jTextField1.setText("");
        jComboBox3.setSelectedIndex(0);
        jTextArea1.setText("");
        jButton3.setEnabled(false);
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jTextField1.setBackground(Color.white);
        jTextField1.setForeground(Color.black);
        Disabled_Enabled_Controls();
        Textcolorchange("> SCardReleaseContext \n   Successsful\n\n",Color.black);
        t.stop();
}//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if(!(readerName.matches(""))) {
            jButton1.setEnabled(false);
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
            IsAuthenticated = false;
            // obtiene el terminal seleccionado
            if(readerName.contains("CL")) {
                
                terminal = terminals.get(1);
            } else {
                
                terminal = terminals.get(1);
            }
            Textcolorchange(">SCardEstablishContext\n  Successsful\n\n",Color.black);
            t=new Timer(1000, taskperformer);
            t.start();
            evt1=evt;
        } else {
            Textcolorchange("\n> SCardEstablishContext\n" + "   Failed..." +"\n\n",Color.red);
        }
        
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jFormattedTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField4KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        int limite = 8;
        String texto = this.jFormattedTextField4.getText();
        if(texto.length()==limite || !Character.isDigit(c))
        {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_jFormattedTextField4KeyTyped

    //********************************************************
        //Function Name:Disabled_Enabled_Controls
        //Description:Disabled and Enabled Controls and Label Content value null on runtime
    //********************************************************  
    private void Disabled_Enabled_Controls()
    {
        jComboBox2.setSelectedIndex(0);
        jLabel19.setText("                     ");
        jLabel20.setText("                     ");
        jTextField2.setText("");
        jLabel14.setText("");
        jLabel13.setText("");
        jLabel17.setText("");
        jLabel15.setText("");
        jLabel16.setText("");
        jFormattedTextField2.setText("");
        jComboBox4.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
        jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
        jComboBox3.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jButton10.setEnabled(false);
        jFormattedTextField4.setEnabled(false);
        jTextField2.setEnabled(false);
        jComboBox4.setEnabled(false);
        IsAuthenticated = false;
        jFormattedTextField2.setBackground(Color.white);
        //jFormattedTextField3.setBackground(Color.white);
        jFormattedTextField2.setEnabled(false);
        //jFormattedTextField3.setEnabled(false);
        jComboBox2.setEnabled(false);
        jButton9.setEnabled(false);
     }
    //********************************************************
        //Function Name:card_Type_Identification
        //Description:Function to add blocks in block combo box based on card type
    //********************************************************
    private void card_Type_Identification()
    {
          jComboBox4.removeAllItems();
          if(card_Type==1)
          {
                //jComboBox4.addItem("00");
                jComboBox4.addItem("01");
                /*jComboBox4.addItem("02");
                jComboBox4.addItem("03");
                jComboBox4.addItem("04");
                jComboBox4.addItem("05");
                jComboBox4.addItem("06");
                jComboBox4.addItem("07");
                jComboBox4.addItem("08");
                jComboBox4.addItem("09");
                jComboBox4.addItem("10");
                jComboBox4.addItem("11");
                jComboBox4.addItem("12");
                jComboBox4.addItem("13");
                jComboBox4.addItem("14");
                jComboBox4.addItem("15");
                jComboBox4.addItem("16");
                jComboBox4.addItem("17");
                jComboBox4.addItem("18");
                jComboBox4.addItem("19");
                jComboBox4.addItem("20");
                jComboBox4.addItem("21");
                jComboBox4.addItem("22");
                jComboBox4.addItem("23");
                jComboBox4.addItem("24");
                jComboBox4.addItem("25");
                jComboBox4.addItem("26");
                jComboBox4.addItem("27");
                jComboBox4.addItem("28");
                jComboBox4.addItem("29");
                jComboBox4.addItem("30");
                jComboBox4.addItem("31");
                jComboBox4.addItem("32");
                jComboBox4.addItem("33");
                jComboBox4.addItem("34");
                jComboBox4.addItem("35");
                jComboBox4.addItem("36");
                jComboBox4.addItem("37");
                jComboBox4.addItem("38");
                jComboBox4.addItem("39");
                jComboBox4.addItem("40");
                jComboBox4.addItem("41");
                jComboBox4.addItem("42");
                jComboBox4.addItem("43");
                jComboBox4.addItem("44");
                jComboBox4.addItem("45");
                jComboBox4.addItem("46");
                jComboBox4.addItem("47");
                jComboBox4.addItem("48");
                jComboBox4.addItem("49");
                jComboBox4.addItem("50");
                jComboBox4.addItem("51");
                jComboBox4.addItem("52");
                jComboBox4.addItem("53");
                jComboBox4.addItem("54");
                jComboBox4.addItem("55");
                jComboBox4.addItem("56");
                jComboBox4.addItem("57");
                jComboBox4.addItem("58");
                jComboBox4.addItem("59");
                jComboBox4.addItem("60");
                jComboBox4.addItem("61");
                jComboBox4.addItem("62");
                jComboBox4.addItem("63");*/

          }
          else if(card_Type==2)
          {
                jComboBox4.addItem("00");
                jComboBox4.addItem("01");
                jComboBox4.addItem("02");
                jComboBox4.addItem("03");
                jComboBox4.addItem("04");
                jComboBox4.addItem("05");
                jComboBox4.addItem("06");
                jComboBox4.addItem("07");
                jComboBox4.addItem("08");
                jComboBox4.addItem("09");
                jComboBox4.addItem("10");
                jComboBox4.addItem("11");
                jComboBox4.addItem("12");
                jComboBox4.addItem("13");
                jComboBox4.addItem("14");
                jComboBox4.addItem("15");
                jComboBox4.addItem("16");
                jComboBox4.addItem("17");
                jComboBox4.addItem("18");
                jComboBox4.addItem("19");
                jComboBox4.addItem("20");
                jComboBox4.addItem("21");
                jComboBox4.addItem("22");
                jComboBox4.addItem("23");
                jComboBox4.addItem("24");
                jComboBox4.addItem("25");
                jComboBox4.addItem("26");
                jComboBox4.addItem("27");
                jComboBox4.addItem("28");
                jComboBox4.addItem("29");
                jComboBox4.addItem("30");
                jComboBox4.addItem("31");
                jComboBox4.addItem("32");
                jComboBox4.addItem("33");
                jComboBox4.addItem("34");
                jComboBox4.addItem("35");
                jComboBox4.addItem("36");
                jComboBox4.addItem("37");
                jComboBox4.addItem("38");
                jComboBox4.addItem("39");
                jComboBox4.addItem("40");
                jComboBox4.addItem("41");
                jComboBox4.addItem("42");
                jComboBox4.addItem("43");
                jComboBox4.addItem("44");
                jComboBox4.addItem("45");
                jComboBox4.addItem("46");
                jComboBox4.addItem("47");
                jComboBox4.addItem("48");
                jComboBox4.addItem("49");
                jComboBox4.addItem("50");
                jComboBox4.addItem("51");
                jComboBox4.addItem("52");
                jComboBox4.addItem("53");
                jComboBox4.addItem("54");
                jComboBox4.addItem("55");
                jComboBox4.addItem("56");
                jComboBox4.addItem("57");
                jComboBox4.addItem("58");
                jComboBox4.addItem("59");
                jComboBox4.addItem("60");
                jComboBox4.addItem("61");
                jComboBox4.addItem("62");
                jComboBox4.addItem("63");
                jComboBox4.addItem("64");
                jComboBox4.addItem("65");
                jComboBox4.addItem("66");
                jComboBox4.addItem("67");
                jComboBox4.addItem("68");
                jComboBox4.addItem("69");
                jComboBox4.addItem("70");
                jComboBox4.addItem("71");
                jComboBox4.addItem("72");
                jComboBox4.addItem("73");
                jComboBox4.addItem("74");
                jComboBox4.addItem("75");
                jComboBox4.addItem("76");
                jComboBox4.addItem("77");
                jComboBox4.addItem("78");
                jComboBox4.addItem("79");
                jComboBox4.addItem("80");
                jComboBox4.addItem("81");
                jComboBox4.addItem("82");
                jComboBox4.addItem("83");
                jComboBox4.addItem("84");
                jComboBox4.addItem("85");
                jComboBox4.addItem("86");
                jComboBox4.addItem("87");
                jComboBox4.addItem("88");
                jComboBox4.addItem("89");
                jComboBox4.addItem("90");
                jComboBox4.addItem("91");
                jComboBox4.addItem("92");
                jComboBox4.addItem("93");
                jComboBox4.addItem("94");
                jComboBox4.addItem("95");
                jComboBox4.addItem("96");
                jComboBox4.addItem("97");
                jComboBox4.addItem("98");
                jComboBox4.addItem("99");
                jComboBox4.addItem("100");
                jComboBox4.addItem("101");
                jComboBox4.addItem("102");
                jComboBox4.addItem("103");
                jComboBox4.addItem("104");
                jComboBox4.addItem("105");
                jComboBox4.addItem("106");
                jComboBox4.addItem("107");
                jComboBox4.addItem("108");
                jComboBox4.addItem("109");
                jComboBox4.addItem("110");
                jComboBox4.addItem("111");
                jComboBox4.addItem("112");
                jComboBox4.addItem("113");
                jComboBox4.addItem("114");
                jComboBox4.addItem("115");
                jComboBox4.addItem("116");
                jComboBox4.addItem("117");
                jComboBox4.addItem("118");
                jComboBox4.addItem("119");
                jComboBox4.addItem("120");
                jComboBox4.addItem("121");
                jComboBox4.addItem("122");
                jComboBox4.addItem("123");
                jComboBox4.addItem("124");
                jComboBox4.addItem("125");
                jComboBox4.addItem("126");
                jComboBox4.addItem("127");
                jComboBox4.addItem("128");
                jComboBox4.addItem("129");
                jComboBox4.addItem("130");
                jComboBox4.addItem("131");
                jComboBox4.addItem("132");
                jComboBox4.addItem("133");
                jComboBox4.addItem("134");
                jComboBox4.addItem("135");
                jComboBox4.addItem("136");
                jComboBox4.addItem("137");
                jComboBox4.addItem("138");
                jComboBox4.addItem("139");
                jComboBox4.addItem("140");
                jComboBox4.addItem("141");
                jComboBox4.addItem("142");
                jComboBox4.addItem("143");
                jComboBox4.addItem("144");
                jComboBox4.addItem("145");
                jComboBox4.addItem("146");
                jComboBox4.addItem("147");
                jComboBox4.addItem("148");
                jComboBox4.addItem("149");
                jComboBox4.addItem("150");
                jComboBox4.addItem("151");
                jComboBox4.addItem("152");
                jComboBox4.addItem("153");
                jComboBox4.addItem("154");
                jComboBox4.addItem("155");
                jComboBox4.addItem("156");
                jComboBox4.addItem("157");
                jComboBox4.addItem("158");
                jComboBox4.addItem("159");
                jComboBox4.addItem("160");
                jComboBox4.addItem("161");
                jComboBox4.addItem("162");
                jComboBox4.addItem("163");
                jComboBox4.addItem("164");
                jComboBox4.addItem("165");
                jComboBox4.addItem("166");
                jComboBox4.addItem("167");
                jComboBox4.addItem("168");
                jComboBox4.addItem("169");
                jComboBox4.addItem("170");
                jComboBox4.addItem("171");
                jComboBox4.addItem("172");
                jComboBox4.addItem("173");
                jComboBox4.addItem("174");
                jComboBox4.addItem("175");
                jComboBox4.addItem("176");
                jComboBox4.addItem("177");
                jComboBox4.addItem("178");
                jComboBox4.addItem("179");
                jComboBox4.addItem("180");
                jComboBox4.addItem("181");
                jComboBox4.addItem("182");
                jComboBox4.addItem("183");
                jComboBox4.addItem("184");
                jComboBox4.addItem("185");
                jComboBox4.addItem("186");
                jComboBox4.addItem("187");
                jComboBox4.addItem("188");
                jComboBox4.addItem("189");
                jComboBox4.addItem("190");
                jComboBox4.addItem("191");
                jComboBox4.addItem("192");
                jComboBox4.addItem("193");
                jComboBox4.addItem("194");
                jComboBox4.addItem("195");
                jComboBox4.addItem("196");
                jComboBox4.addItem("197");
                jComboBox4.addItem("198");
                jComboBox4.addItem("199");
                jComboBox4.addItem("200");
                jComboBox4.addItem("201");
                jComboBox4.addItem("202");
                jComboBox4.addItem("203");
                jComboBox4.addItem("204");
                jComboBox4.addItem("205");
                jComboBox4.addItem("206");
                jComboBox4.addItem("207");
                jComboBox4.addItem("208");
                jComboBox4.addItem("209");
                jComboBox4.addItem("210");
                jComboBox4.addItem("211");
                jComboBox4.addItem("212");
                jComboBox4.addItem("213");
                jComboBox4.addItem("214");
                jComboBox4.addItem("215");
                jComboBox4.addItem("216");
                jComboBox4.addItem("217");
                jComboBox4.addItem("218");
                jComboBox4.addItem("219");
                jComboBox4.addItem("220");
                jComboBox4.addItem("221");
                jComboBox4.addItem("222");
                jComboBox4.addItem("223");
                jComboBox4.addItem("224");
                jComboBox4.addItem("225");
                jComboBox4.addItem("226");
                jComboBox4.addItem("227");
                jComboBox4.addItem("228");
                jComboBox4.addItem("229");
                jComboBox4.addItem("230");
                jComboBox4.addItem("231");
                jComboBox4.addItem("232");
                jComboBox4.addItem("233");
                jComboBox4.addItem("234");
                jComboBox4.addItem("235");
                jComboBox4.addItem("236");
                jComboBox4.addItem("237");
                jComboBox4.addItem("238");
                jComboBox4.addItem("239");
                jComboBox4.addItem("240");
                jComboBox4.addItem("241");
                jComboBox4.addItem("242");
                jComboBox4.addItem("243");
                jComboBox4.addItem("244");
                jComboBox4.addItem("245");
                jComboBox4.addItem("246");
                jComboBox4.addItem("247");
                jComboBox4.addItem("248");
                jComboBox4.addItem("249");
                jComboBox4.addItem("250");
                jComboBox4.addItem("251");
                jComboBox4.addItem("252");
                jComboBox4.addItem("253");
                jComboBox4.addItem("254");
                jComboBox4.addItem("255");

          }
          
      }
    
    //********************************************************
        //Function Name:ToolTip
        //Description:Add Tool Tip on Every Controls
    //********************************************************
    private void Tool_Tip()
    {
         jTextField1.setToolTipText("Current card state: Inserted \\ Removed");
         jComboBox1.setToolTipText("Please select a contactless slot" + "\n" + "of an available reader");
         jButton1.setToolTipText("The SCardEstablishContext function establishes the resource manager context (the scope)" + "\n" + "within functions can be used later.");
         jButton2.setToolTipText("The SCardReleaseContext function closes an established resource manager context," + "\n" + "freeing any resources allocated under that context.");
         jButton3.setToolTipText("This function establishes a connection, using a specific resource manager context," + "\n" + "between the calling application and a smart card contained by a specific reader.");
         jButton4.setToolTipText("This function terminates a connection previously opened between the calling application" + "\n" + "and a smart card in the target reader.");
         jComboBox2.setToolTipText("Select a key number from 0-31");
         jFormattedTextField2.setToolTipText("The key must have a length of 6 bytes");
         jButton9.setToolTipText("Click to load key into the reader" + "\n" + "(LOAD KEY command from the PC/SC part 3 specifcation)");
         jComboBox3.setToolTipText("Select a key number from 0-31");
         jComboBox4.setToolTipText("Select block number to read or write");
         jButton5.setToolTipText("Click to authenticate a block with a Mifare Key" + "\n" + "(GENERAL AUTHENTICATE command from the PC/SC part 3 specifcation)");
         jButton6.setToolTipText("Click to read data from a block" + "\n" + "(READ BINARY command from the PC/SC part 3 specifcation)");
         jButton7.setToolTipText("Click to write data to a block" + "\n" + "(UPDATE BINARY command from the PC/SC part 3 specifcation)");
          jButton10.setToolTipText("Click to clear Log to a block" );
         jFormattedTextField4.setToolTipText("16 bytes of block data");
         jTextArea1.setToolTipText("Log information about read/update operations");
         jTextPane2.setToolTipText("Return Values");
         jButton8.setToolTipText("Click to Clear Return Values");
         
     }
    
    //**************************************************************************
    //license
    //**************************************************************************

    private void license()
    {
          license="© 2012 HID Global Corporation 15370 Barranca Parkway, Irvine, California 92618, U.S.A. All rights reserved."+
         "Use is subject to license terms." +
         "" +
         "HID Global Corporation has intellectual property rights relating to technology embodied/described in this product."+
         "In particular, and without limitation, these intellectual property rights may include one or more of the U.S." +
         "patents or pending patent applications in the U.S. and other countries." +
         "" +
         "Third-party software is copyrighted and licensed from HID’s suppliers."+ 
         "" +
         "HID Global, HID, and the HID logo, are trademarks or registered trademarks of HID Global Corporation in the U.S." +
         "and other countries. "+ 
         "" +
         "Federal Acquisitions: Commercial Software - Government Users Subject to Standard License Terms and Conditions. " +
         " " +
         "SOFTWARE AND DOCUMENTATION IS PROVIDED \"AS IS\" AND ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND" +
         "WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR" +
         "NON-INFRINGEMENT, ARE DISCLAIMED, EXCEPT TO THE EXTENT THAT SUCH DISCLAIMERS ARE HELD TO BE LEGALLY INVALID.";
      }
    
    //********************************************************
        //Function Name:Textcolorchange
        //Input(Parameter) : txt, c
        //OutPutParameter:-------
        //Description:Change the text color of return values if error occured 
    //********************************************************
    private void Textcolorchange(String txt,Color c)
    {
        StyledDocument doc = jTextPane2.getStyledDocument(); 
        Style style = jTextPane2.addStyle("I'm a Style", null); 
        StyleConstants.setForeground(style, c); 

        try 
        {
            doc.insertString(doc.getLength(), txt,style);
           
        } 
        catch (BadLocationException e)
        {
            System.out.println(e.toString());
        } 
        
  
    }
     
      //User32
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonSalir;
    private javax.swing.JLabel LabelNombre;
    private javax.swing.JLabel LabelRut;
    private javax.swing.JLabel LabelTarjeta;
    private javax.swing.JTextField TextoNombre;
    private javax.swing.JTextField TextoRut;
    private javax.swing.JTextField TextoTarjeta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mensaje;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
    private int acum = 0;
    private int acum1 = 0 ;
    private String traerestado="";
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private String numhab = "";
    private String tipotarj = "";
    private String nombrepas ="";
    private String rutpas ="";
    private String monto = "";
    private String estado = "";
    private String nombre_us = "";
    private int metodo = 0;
    private int pre = 0;
    private int ingreso = 0;
    private int texa = 0;
    private JDialog aboutBox;
    private boolean salir = false;
    
   
}

 
