/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Francisco
 */
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
public class ImagenFondo implements Border{
    public BufferedImage back;
   
    public ImagenFondo(){
        try
        {
           URL imagePath = new URL(getClass().getResource("../Imagenes/Grand-Hyatt.jpg").toString());
           back = ImageIO.read(imagePath);
        }
        catch(Exception e)
        {
            
        }
    }
    public void paintBorder(Component d,Graphics g,int x,int y,int width,int height){
       g.drawImage(back,(x + (width - back.getWidth())/2), (y + (height - back.getHeight()/2)), null);         
      //.drawImage(back, x, y, d);
    }
    
    public Insets getBorderInsets(Component c)
    {
        return new Insets(0,0,0,0);
    }
    
    public boolean isBorderOpaque()
    {
        return false;
    }
}
