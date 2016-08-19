/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javasamplesformifarecards;

//*******************************************************************************
/*
 * NativeAboutbox.class
 */
public class NativeAboutbox {
    
    //********************************************************
    //**********Native Function Declaration*******************
    //Function Name:AbotboxmenuInserted,AboutFlagGet,aboutFlagSet
    //Input(Parameter) : ------
    //OutPutParameter:-------
    //Description:For calling VC++ dll for AboutMenu
    //********************************************************
    native static public void AboutboxmenuInserted();
    native static public boolean AboutFlagGet();
    native static public boolean AboutFlagSet();
    static 
    {
        String bits = System.getProperty("sun.arch.data.model", "?");
 if (!bits.equals("64")) {
        //For loading the native library
        System.loadLibrary("NativeAboutbox");
 }
 else
 {
     System.loadLibrary("NativeAboutboxX64");
 }
    }
    
}
