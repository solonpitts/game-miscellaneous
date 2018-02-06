/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * Enemy.java
 */
 
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;

/**
*  	The enemy in the game
*/
public class Enemy
{

   private int xLeft;
   private int yTop;
   private int step;
   private JComponent component;
   private int counter;
   public static boolean left;
   public static int fireSpeed = 30;
   
   /**
      Creates a new Enemy
      @param x the x coordinate of the top left corner
      @param y the y coordinate of the top left corner
      @param ste Step in pixels the Enemy will move each time
      @param componen The component
    */
   public Enemy(int x, int y, int ste, JComponent componen)
   {
      step = ste;
      component = componen;
      xLeft = x;
      yTop = y;
      counter = 0;
      left = true;
   }
   
   /**
   	*  	checks if the given object coordinates collide with the enemy
   	*	@param x the x coord
   	*	@param y the y coord
   	*	@param width the width of the object
   	*/
   public boolean checkCollide(int x, int y, int width)
   {
   		return (x + width > xLeft && x < xLeft + 60 && y > yTop && y < yTop + 40);
   }
   
   /**
   	*  	gets the x of the enemy
   	*	@return the x value
   	*/
   public int getX()
   {
   		return xLeft;
   }
   
   /**
   	*  	gets the y of the enemy
   	*	@return the y value
   	*/
   public int getY()
   {
   		return yTop;
   }
   
   /**
   	*  	sets a new fire rate
   	*	@param n the fire Rate
   	*/
   public void setFireSpeed(int n)
   {
   		fireSpeed = n;
   }
   
   /**
   	*  	gets the fire speed of the enemy
   	*	@return the fire rate
   	*/
   public int getFireSpeed()
   {
   		return fireSpeed;
   }
   
   /**
    * Adjusts the Enemy's x and y locations based on the direction it is going.
    */
   public void animate()
   {
   		 counter++;
   		 
         if(left)
            xLeft-=step;
         else 
         	xLeft+=step;
         if(xLeft - step < 0)
         {
         	left = false;
         	xLeft+=(step*2);
         }
         	
         if(xLeft + 60 + step > component.getWidth())
         {
         	left = true;
         }	
			
		 if(counter == fireSpeed)
		 {
		 	counter = 0;
		 	((GameComponent)component).addProjectile(new Projectile(xLeft+25, yTop + 55, 12, Color.ORANGE));
		 }
		//switch directions uniformly for uniform enemy movement
   }
   
    /**
      Draws the Enemy in the current position.
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	  Rectangle body = new Rectangle(xLeft, yTop + 10, 60, 10);
	  Rectangle leftArm = new Rectangle(xLeft+5, yTop - 40, 5, 30);
	  Rectangle rightArm = new Rectangle(xLeft+50, yTop - 40, 5, 30);
	  Rectangle leftArm2 = new Rectangle(xLeft, yTop - 50, 5, 30);
	  Rectangle rightArm2 = new Rectangle(xLeft+55, yTop - 50, 5, 30);
	  
	  Rectangle frontArm1 = new Rectangle(xLeft+11, yTop + 20, 5, 15);
	  Rectangle frontArm2 = new Rectangle(xLeft+45, yTop + 20, 5, 15);      
	        
      Rectangle left = new Rectangle(xLeft-10, yTop + 10, 20, 20);
      Rectangle right = new Rectangle(xLeft+50, yTop + 10, 20, 20);
	  Rectangle top = new Rectangle(xLeft+10, yTop - 30, 40, 40);   

      
   	  g2.setColor(Color.RED);
      g2.fill(body);
      g2.fill(leftArm);
      g2.fill(rightArm);
      g2.fill(leftArm2);
      g2.fill(rightArm2);
      g2.fill(frontArm1);
      g2.fill(frontArm2);
      
      g2.setColor(Color.ORANGE);
      g2.draw(left);
      g2.draw(right);
      g2.fill(top);
   }
   
   
}