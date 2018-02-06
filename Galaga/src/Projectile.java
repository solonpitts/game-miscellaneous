/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * Projectile.java
 */
import javax.swing.*;
import java.awt.*;

/**
*   The projectile used in the game
*/
public class Projectile
{
   private int xLeft;
   private int yTop;
   private int step;
   private boolean done;
   private Color co;
   
   /**
      Creates a new projectile
      @param x the x coordinate of the top left corner
      @param y the y coordinate of the top left corner
      @param ste Step in pixels the Projectile will move each time
      @param c the color
    */
   public Projectile(int x, int y, int ste, Color c)
   {
      step = ste;
      xLeft = x;
      yTop = y;
      done = false;
      co = c;
   }
   
   /**
    * Adjusts the Projectile's x and y locations based on the direction it is going.  Moves it a fixed
    * number of steps.
    */
   public boolean animate()
   {
         yTop+=step;
         if(yTop < 0 || yTop > 1200)
         	return true;
         return false;
   }
   
    /**
      Draws the Projectile in the current position.
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	  g2.setColor(co);
	  Rectangle body = new Rectangle(xLeft, yTop, 5, 15);
      g2.fill(body);
   }
   
   /**
   *  Returns the XLeft value.
   *@return the x value of the projectile
   */
   public int getX()
   {
   	return xLeft;
   }
   
   /**
   *  Return the yTop value.
   *@return the y value of the projectile
   */
   public int getY()
   {
   	return yTop;
   }
   
   /**
	*   Gets the color
	*	@return the color
	*/
   public Color getColor()
   {
   	 return co;
   }
}