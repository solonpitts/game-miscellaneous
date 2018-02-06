/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * PlayerDeath.java
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;

/**
*  the player death animation
*/
public class PlayerDeath extends Death
{

   private int xLeft;
   private int yTop;
   private int changer;
   
   /**
      Creates a new PlayerDeath
      @param x the x coordinate of the top left corner
      @param y the y coordinate of the top left corner
    */
   public PlayerDeath(int x, int y)
   {
   		super(x,y);
   		xLeft = x;
   		yTop = y;
   		changer = 0;
   }
   
   /**
   	*  	increments the stage of the animation
   	*/
   public void change()
   {
   		changer++;
   }
   
    /**
      Draws the PlayerDeath in the current position, dependent on the state of changer.
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {   	
   	
   	
   	if(changer == 1)
   	{
   	  Rectangle r1 = new Rectangle(xLeft, yTop, 15, 70);
	  Rectangle r2 = new Rectangle(xLeft, yTop - 70, 15, 70);
	  Rectangle r3 = new Rectangle(xLeft - 70, yTop, 70, 15);
	  Rectangle r4 = new Rectangle(xLeft, yTop, 70, 15);
	  
	  g2.setColor(Color.YELLOW);
      g2.fill(r1);
      g2.fill(r2);
      g2.fill(r3);
      g2.fill(r4);

	  g2.setColor(Color.RED);
	  g2.drawLine(xLeft-40,yTop-30,xLeft+40,yTop+30);
	  g2.drawLine(xLeft-40,yTop+30,xLeft+40,yTop-30);
	  g2.drawLine(xLeft-40,yTop-50,xLeft+40,yTop+50);
	  g2.drawLine(xLeft-40,yTop+50,xLeft+40,yTop-50);
	  g2.setColor(Color.ORANGE);
	  g2.drawLine(xLeft-40,yTop-40,xLeft+40,yTop+40);
	  g2.drawLine(xLeft-40,yTop+40,xLeft+40,yTop-40);
   	}
      


   	if(changer == 2)
   	{
   	  Rectangle r1 = new Rectangle(xLeft, yTop, 10, 50);
	  Rectangle r2 = new Rectangle(xLeft, yTop - 50, 10, 50);
	  Rectangle r3 = new Rectangle(xLeft - 50, yTop, 50, 10);
	  Rectangle r4 = new Rectangle(xLeft, yTop, 50, 10);
	  
	  g2.setColor(Color.YELLOW);
      g2.fill(r1);
      g2.fill(r2);
      g2.fill(r3);
      g2.fill(r4);

      
	  g2.setColor(Color.RED);
	  g2.drawLine(xLeft-30,yTop-20,xLeft+30,yTop+20);
	  g2.drawLine(xLeft-30,yTop+20,xLeft+30,yTop-20);
	  g2.drawLine(xLeft-30,yTop-40,xLeft+30,yTop+40);
	  g2.drawLine(xLeft-30,yTop+40,xLeft+30,yTop-40);      
	  g2.setColor(Color.ORANGE);
	  g2.drawLine(xLeft-30,yTop-30,xLeft+30,yTop+30);
	  g2.drawLine(xLeft-30,yTop+30,xLeft+30,yTop-30);
	  
   	}
   	
   	
   	if(changer == 3)
   	{
   	  Rectangle r1 = new Rectangle(xLeft, yTop, 5, 30);
	  Rectangle r2 = new Rectangle(xLeft, yTop - 30, 5, 30);
	  Rectangle r3 = new Rectangle(xLeft - 30, yTop, 30, 5);
	  Rectangle r4 = new Rectangle(xLeft, yTop, 30, 5);
  
	  g2.setColor(Color.YELLOW);
      g2.fill(r1);
      g2.fill(r2);
      g2.fill(r3);
      g2.fill(r4);

	  g2.setColor(Color.RED);
	  g2.drawLine(xLeft-15,yTop-10,xLeft+15,yTop+10);
	  g2.drawLine(xLeft-15,yTop+10,xLeft+15,yTop-10);
	  g2.drawLine(xLeft-15,yTop-20,xLeft+15,yTop+20);
	  g2.drawLine(xLeft-15,yTop+20,xLeft+15,yTop-20);    
	  g2.setColor(Color.ORANGE);
	  g2.drawLine(xLeft-15,yTop-15,xLeft+15,yTop+15);
	  g2.drawLine(xLeft-15,yTop+15,xLeft+15,yTop-15);
	  
   	}
   	
   	if(changer == 4)
   	{
   	  Rectangle r1 = new Rectangle(xLeft, yTop, 3, 15);
	  Rectangle r2 = new Rectangle(xLeft, yTop - 15, 3, 15);
	  Rectangle r3 = new Rectangle(xLeft - 15, yTop, 15, 3);
	  Rectangle r4 = new Rectangle(xLeft, yTop, 15, 3);
  
	  
	  
	  g2.setColor(Color.YELLOW);
      g2.fill(r1);
      g2.fill(r2);
      g2.fill(r3);
      g2.fill(r4);

	  g2.setColor(Color.RED);
	  g2.drawLine(xLeft-5,yTop-2,xLeft+5,yTop+2);
	  g2.drawLine(xLeft-5,yTop+2,xLeft+5,yTop-2);
	  g2.drawLine(xLeft-5,yTop-7,xLeft+5,yTop+7);
	  g2.drawLine(xLeft-5,yTop+7,xLeft+5,yTop-7);
	  g2.setColor(Color.ORANGE);
	  g2.drawLine(xLeft-5,yTop-5,xLeft+5,yTop+5);
	  g2.drawLine(xLeft-5,yTop+5,xLeft+5,yTop-5);
   	}
   	
   	if(changer == 5)
   	{
   	  Rectangle r1 = new Rectangle(xLeft-5, yTop-5, 10, 10);
	  g2.setColor(Color.RED);
      g2.fill(r1);
   	}
   }
   
   
}