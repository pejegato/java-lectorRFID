/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import GUI.ConsultarSaldo;
import GUI.jframeHiatt;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import javax.swing.JFrame;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import org.jdesktop.application.FrameView;

/**
 *
 * @author Francisco
 */
public class ConexionTarjeta extends JFrame implements ActionListener {
     private boolean salir = false;
      TerminalFactory factory;                    //
    java.util.List<CardTerminal> terminals;     //Get the terminals attached
    CardTerminal terminal;                      //receive the object of terminals
    Card card;                                  //
    CardChannel channel;                        //
    String readerName;
    String pasanom = "";
    public boolean tnph = false;
    int indice = 0;
    int solounavez = 0;
    int solounavez1 = 0;
    public static boolean tarjetavalida = false;
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
    public static boolean ti = false;
    private boolean transmisiondatos = false;
    private int texa = 0;
    public static boolean cerrar = false;
    private String cardtype;
    private String UID;
    private String ATR;
    private String ConexionTarjeta;
    private String tipoTarjeta;
    private String seleccionarBlock ="01";
    private String llaveTarjeta ="00";
    private String seleccionarNumero ="01";
    private boolean autentificacion = false;
    public static String[] datos = new String[5];
    private boolean relacionPasTar = false;
    private String traerestado = "";
    private int acum1 = 0 ;
    private int acum = 0;
    private String numhab = ""; 
    private String tipotarj = "";
    private String nombrepas = "";
    private String rutpas = "";
    private String monto = "";
    private String estado = "";
    private String nombre_us = "";
    private int ingreso = 0; 
    private int metodo = 0;
    private boolean consultar = false;
    private String block = "";
    private boolean solounavezTP = true;
    private boolean solounavezTI = true;
    private String rutTranspaso = "";
    
   // private ActionListener taskperformer;
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void ConexionTarjeta1()
    {
      
        windowload2();
    }
    public void ConexionTarjeta2(boolean consultar)
    {
        this.consultar = consultar;
        windowload2();
    }
    public void TranspasoDatos(String numhab,String tipotarj,String nombrepas,String rutpas, String monto,String estado,String nombre_us){
         this.numhab = numhab; 
         this.tipotarj = tipotarj;
         this.nombrepas = nombrepas;
         this.rutpas = rutpas;
         this.monto = monto;
         this.estado = estado;
         this.nombre_us = nombre_us;
         this.ingreso = 1;
     } 
     public void TranspasoDatos1(int met)
  {
   
     this.ingreso = 0;
     this.metodo = met;
 }        
    
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
           pasares[i] = readerarray[i].toString().trim();
            //jComboBox1.addItem(readerarray[i].toString().trim());
            i++;
        }
      
            pasanom = pasares[indice];//jComboBox1.getItemAt(indice).toString();
            readerName = pasanom;
     
        //OMNIKEY CardMan 5x21 0 OMNIKEY CardMan 5x21-CL 0
        }
        catch(CardException e)
        {
        System.out.println(e.toString());
         ConsultarSaldo.Lector(false);
         //salir = true;
        
          
        }
         
         
        // Tool_Tip();
        // license();
         if(solounavez1 ==0)
         {
         TiempoConexion();
         Conectar();
         //Autentifica();
         }
         solounavez1++;
     }
         
    ActionListener taskperformer = new ActionListener() {
    
       
        public void actionPerformed(ActionEvent e) {
   if(!cerrar)
         if(!salir)
     {
           if(readerName.matches(""))
           {
                //jTextField1.setText("Tarjeta Removida");
               
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
                 
                   ConsultarSaldo.nopertenece = false;
                   ConsultarSaldo.pertenece = false;
                   solounavez = 0;
                   validarau = true;
                  // if(tarjetavalida)
                  // {
                   traerestado = "";
                   acum = 0;//}
                  
                   if(solounavezTP)
                   {  solounavezTP = false;
                      solounavezTI = true;
                      ConsultarSaldo.TarjetaPresente(false);
                      //if(ConsultarSaldo.evento == 1)
                      ConsultarSaldo.unavez = false;
                      ConsultarSaldo.evento = 0;
                     // if(ConsultarSaldo.evento == 2)
                      
                      ConsultarSaldo.targeta_presente = false;
                      ConsultarSaldo.CrearDestruirObjeto();
                   }
                        try {
                            
                            this.finalize();
                        } catch (Throwable ex) {
                            Logger.getLogger(ConexionTarjeta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   
               }
               else
               {
                   
                    if(solounavez == 0)
                    {
                        if(validarau == true)
                       {
                          if(solounavezTI)
                          {
                              solounavezTI = false;
                          ConsultarSaldo.NuevaConexionTarjeta();
                          }
                          TiempoConexion();
                          Conectar();
                          Autentifica();
                          solounavezTP = true;
                       }
                    }
                    solounavez++;
                    
               }
           }
           cardpre = false;
        }
      } 
    };
 
     public void TiempoConexion()
    {
        if(!(readerName.matches("")))
       {
           
            IsAuthenticated = false;
            // get the terminal Selected
            if(readerName.contains("CL"))
            {
                //terminal = terminals.get(jComboBox1.getSelectedIndex());
                  terminal = terminals.get(1); 
            }
            else
            {
               //terminal = terminals.get(jComboBox1.getSelectedIndex());
                terminal = terminals.get(1);
            }
            
            t=new Timer(1000, taskperformer);
            t.start();
            //evt1=evt;
       }
       else
       {
           
       }
        
            
    } 
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
                //establece coneccion  con tarjeta
                card = terminal.connect("T=1");
                System.out.print(card);
       
             channel = card.getBasicChannel();
             //////////ATR////////
             ATR r2= channel.getCard().getATR();
             byte atr[]=r2.getBytes();
           
             
             
              for (n = 0; n < atr.length; n++) 
              {
                  x = (int) (0x000000FF & atr[n]);  //conversion de byte en int
                  s = Integer.toHexString(x).toUpperCase();
                  if (s.length() == 1) s = "0" + s;
                  s1=s1+s+" ";
                 
              } 
              atr_temp=s1;
               ATR ="ATR ="+atr_temp;
              
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
                      x = (int) (0x000000FF & uid[n]);  //conversion de byte en int
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
                   UID ="UID ="+uid_temp;
                   }
                       
                       
                       
               //Card Type
               if(atr_byte==1)
               {
                    card_Type=1;
                   // card_Type_Identification();
                   // jLabel17.setText("Card Type: Mifare 1K");
                    tipoTarjeta = "Card Type: Mifare 1K";
                    
               }
               else if(atr_byte==2)
               {
                    card_Type=2;
                   // card_Type_Identification();
                   // jLabel17.setText("Card Type: Mifare 4K");
                    tipoTarjeta = "Card Type: Mifare 4K";
                    
               }
               else if(uid_temp.matches("") )
               {
                    UID = "UID = n/a";
                    //jLabel17.setText("                            Card Type: No Mifare 1K or 4K Card ");
                    tipoTarjeta = "Card Type: No Mifare 1K or 4K Card ";
               }
               else
               {
                   //jLabel17.setText("                            Card Type: No Mifare 1K or 4K Card ");
                    tipoTarjeta = "Card Type: No Mifare 1K or 4K Card ";
               }
               //Textcolorchange("> SCardConnect \n     Successsful\n\n",Color.black);
               ConexionTarjeta ="> SCardConnect \n     Successsful\n\n";
            }
            catch(CardException e)
            {
               //Textcolorchange("> SCardConnect" + "\n   Failed... \n\n",Color.red);
               ConexionTarjeta = "> SCardConnect" + "\n   Failed... \n\n";
            }
    }  
     public void Autentifica()
    {
      
        String keych;
        byte a1[]=new byte[1];
        byte receive[];
        validarau = false;
        if(!(seleccionarBlock.matches("")))
        {
            int cb=Integer.parseInt(seleccionarBlock);
            currentBlock=intToPseudoUnsignedByte(cb);
            System.out.println(currentBlock);
            keynum =Byte.parseByte(llaveTarjeta);
            a1[0]=keynum;//Key Number
            
                    //////////////Authentication By Key Number using KEY A
            if(keynum == 0)
                {
                    byte data[]=new byte[5];
                    keynum =Byte.parseByte(seleccionarNumero);
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
                              
                              IsAuthenticated=true;
                             
                              autentificacion =true;
                              tnph = false;
                             
                            
                        }
                        else
                        {
                             
                              ConsultarSaldo.TarjetaPertenece();
                              ConsultarSaldo.TarjetaPresente(false);
                              autentificacion = false;
                              tnph = true;
                              //datos[0]= "0";

                        }
                       if(ingreso == 1)
                        {
                            //Textcolorchange("> General Authenticate" + "   Failed(SW1 SW2 ="+bytestohex(receive)+")\n\n", Color.red);
                            

                        }   
                    }
                    catch(CardException e)
                    {
                        System.out.println(e.toString());
                    }
                }
                    
                    //////////////Authentication By Key Number using KEY B
            else if(keynum == 1)
                {
                    byte data[]=new byte[5];
                    keynum =Byte.parseByte(seleccionarNumero);
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
                              
                              autentificacion =true;
                            
                        }
                        else
                        {
                            autentificacion = false;
                             
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
            //jLabel13.setText("Select Block");
        }
        //Autentificar("01","02");
       if(consultar )//&& !tnph) 
       {
           Traer("01");
           metodo = 1;
           String paso4 = ""+acum;
       }
       else //if(tnph )//&& !ti)
       {
          
       }
       if(metodo == 1)
       {  
       
        Traer("02");
        String cadena = traerestado;
      if(!cadena.equals(""))
      {
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
            this.texa = 0;
            relacionPasTar = true;
             datos[0] = "";
             datos[1] = "";
             datos[2] = "";
             datos[3] = "";
             datos[4] = "";
             ConsultarSaldo.TraerDatos(datos);
            
                 
                
               // salir = true; esto era parte del codigo recalcarrr
                
        }
        else
        {
            String ttar = "";
            if(tt.equals("02"))
            {   
                String saldo = "";
                int cont = 0;
                boolean salgo = false;
                while(salgo == false)
                {
                    if(transpaso[cont] == 'D')
                    {
                        salgo = true;
                    }
                    else
                    {
                       if(Character.isDigit(transpaso[cont]))
                       saldo = saldo + transpaso[cont]; 
                    }
                  cont++;
                }
               
                int saldo1 = Integer.parseInt(saldo);
                 ttar = "Prepagada";
                this.texa = 1;
                 relacionPasTar = true;
                 datos[0] = r;
                 datos[1] = ttar;
                 datos[2] = h;
                 datos[3] = ""+acum;
                 datos[4] = ""+saldo1;
             ConsultarSaldo.TraerDatos(datos);
            }     
               
            if(tt.equals("01"))
            {    
                ttar = "Libre";
                this.texa = 0;
                 relacionPasTar = true;
             datos[0] = r;
             datos[1] = ttar;
             datos[2] = h;
             datos[3] = ""+acum;
             datos[4] = "0";
             ConsultarSaldo.TraerDatos(datos);
            } 
            
            
            
             
                
        }    
       
        
       }
       else
       {
           datos[0] = "0";
           ConsultarSaldo.TraerDatos(datos);
       }
      }
       
    }
     private void Traer(String numero)
   {
        String read_str;
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
                        //jLabel22.setText(""+acum);
                        }
                        else
                        {    
                           if(cb == 2)
                           {    
                                
                                 traerestado = read_str.toString();
                               
                                // jLabel22.setText(""+acum);
                           }
                        } 
                    }
                    else
                    {
                        //Textcolorchange("> SCardTransmit" + "   Failed(SW1 SW2 ="+bytestohex(receive).substring(bytestohex(receive).length()-4, bytestohex(receive).length())+")\n\n", Color.red);
                    }
            }
            catch(CardException e)
            {
                System.out.println(e.toString());
            }
        }
       // jLabel14.setText("");
        
        }
    public void Autentificar(String num, String num1)
     {
        String keych;
        byte a1[]=new byte[1];
        byte receive[];
        
       
            int cb=Integer.parseInt(num);
            currentBlock=intToPseudoUnsignedByte(cb);
            System.out.println(currentBlock);
            keynum =Byte.parseByte("00");//El numero de llave seleccionado
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
                             
                              //Textcolorchange("> General Authenticate\n" + "   Successful \n\n", Color.black);
                            autentificacion = true;
                        }
                        else
                        {
                           // Textcolorchange("> General Authenticate" + "   Failed(SW1 SW2 ="+bytestohex(receive)+")\n\n", Color.red);
                             
                            autentificacion = false;
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
        
        
            //jTextField2.setText("");
             
             
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
                //jLabel22.setText(""+paso2);
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
                             //jTextArea1.setText("");
                             //jTextArea1.append("Ingreso: "+s4+"\n"); //log textbox
                            // jTextArea1.setCaretPosition(jTextArea1.getText().length());
                            // Textcolorchange("> UPDATE BINARY   \n    ( Block "+num + ")   Successful \n\n", Color.black);
                             transmisiondatos = true;
                        }
                 else
                        {
                           // Textcolorchange("> SCardTransmit" + "   Failed(SW1 SW2 ="+bytestohex(receive).substring(bytestohex(receive).length()-4, bytestohex(receive).length())+")\n\n", Color.red);
                        }
                }
                catch(CardException e)
                {
                    System.out.println(e.toString());
                }
            }
            else
            {
                 transmisiondatos = false;
                //jLabel14.setText("Error en la transmision de datos");
            }
                 
     }
     public String RetornaTipo()
    {
                
                String cadena = traerestado;
                char transpaso[]= cadena.toCharArray();
                String a =""+ String.valueOf(transpaso[30])+String.valueOf(transpaso[31]);
                
                return a;
                
                
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
     public  byte intToPseudoUnsignedByte(int n) 
   {
     if (n < 128) return (byte)n;
        return (byte) (n - 256);
   }
     public byte[] hexToBytes(String hexString) { 
         HexBinaryAdapter adapter = new HexBinaryAdapter(); 
         byte[] bytes = adapter.unmarshal(hexString); 
         return bytes; 
    } 
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
   public void LimpiarDatos()
   {
       for(int i = 0; i <=4; i++)
       {
           datos[i]="";
           
       }
   }
}
