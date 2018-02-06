/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * Death.java
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;

/**
*  	The death animation for enemies
*/
public class Death
{

   private int xLeft;
   private int yTop;
   private int changer;
   
   /**
      Creates a new Death
      @param x the x coordinate of the top left corner
      @param y the y coordinate of the top left corner
    */
   public Death(int x, int y)
   {
   		xLeft = x;
   		yTop = y;
   		changer = 1;
   }
   
   /**
   	*  	increments the stage of the animation
   	*/
   public void change()
   {
   		changer++;
   }
   
    /**
      Draws the Death in the current position, based on stage of animation
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {   	
   	
   	
   	if(changer == 1)
   	{
   	  Rectangle r1 = new Rectangle(xLeft, yTop, 10, 40);
	  Rectangle r2 = new Rectangle(xLeft, yTop - 40, 10, 40);
	  Rectangle r3 = new Rectangle(xLeft - 40, yTop, 40, 10);
	  Rectangle r4 = new Rectangle(xLeft, yTop, 40, 10);
  
	  
	  
	  g2.setColor(Color.RED);
      g2.fill(r1);
      g2.fill(r2);
      g2.fill(r3);
      g2.fill(r4);

      
      g2.setColor(Color.ORANGE);
	  g2.drawLine(xLeft-30,yTop-30,xLeft+30,yTop+30);
	  g2.drawLine(xLeft-30,yTop+30,xLeft+30,yTop-30);
	  
   	}
      


   	if(changer == 2)
   	{
   	  Rectangle r1 = new Rectangle(xLeft, yTop, 6, 30);
	  Rectangle r2 = new Rectangle(xLeft, yTop - 30, 6, 30);
	  Rectangle r3 = new Rectangle(xLeft - 30, yTop, 30, 6);
	  Rectangle r4 = new Rectangle(xLeft, yTop, 30, 6);
  
	  
	  
	  g2.setColor(Color.RED);
      g2.fill(r1);
      g2.fill(r2);
      g2.fill(r3);
      g2.fill(r4);

      
      g2.setColor(Color.ORANGE);
	  g2.drawLine(xLeft-20,yTop-20,xLeft+20,yTop+20);
	  g2.drawLine(xLeft-20,yTop+20,xLeft+20,yTop-20);
	  
   	}
   	
   	
   	if(changer == 3)
   	{
   	  Rectangle r1 = new Rectangle(xLeft, yTop, 3, 20);
	  Rectangle r2 = new Rectangle(xLeft, yTop - 20, 3, 20);
	  Rectangle r3 = new Rectangle(xLeft - 20, yTop, 20, 3);
	  Rectangle r4 = new Rectangle(xLeft, yTop, 20, 3);
  
	  
	  
	  g2.setColor(Color.RED);
      g2.fill(r1);
      g2.fill(r2);
      g2.fill(r3);
      g2.fill(r4);

      
      g2.setColor(Color.ORANGE);
	  g2.drawLine(xLeft-10,yTop-10,xLeft+10,yTop+10);
	  g2.drawLine(xLeft-10,yTop+10,xLeft+10,yTop-10);
	  
   	}
   	
   	if(changer == 4)
   	{
   	  Rectangle r1 = new Rectangle(xLeft-5, yTop-5, 10, 10);
	  
	  g2.setColor(Color.ORANGE);
      g2.fill(r1);
	  
   	}
   }
   
   
}