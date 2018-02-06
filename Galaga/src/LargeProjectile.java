/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * Projectile.java
 */
import javax.swing.*;
import java.awt.*;

/**
*   The larger version of the projectile
*/
public class LargeProjectile extends Projectile
{
	
	/**
	*   Constructs a large projectile
	*	@param x the x position
	*	@param y the y position
	*	@param ste the amount it moves each step
	*	@param c the color
	*/
   public LargeProjectile(int x, int y, int ste, Color c)
   {
   	  super(x,y,ste,c);
   }
   
   
    /**
      Draws the Projectile in the current position.
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
	  g2.setColor(getColor());
	  Rectangle body = new Rectangle(getX(), getY(), 15, 15);
      g2.fill(body);
   }
}