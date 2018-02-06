/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * Block.java
 */

import java.awt.*;
import javax.swing.*;

/**
*   The barriers in the game
*/
public class Block
{
	private Image pic;
	private int y;
	private int x;
	private JComponent j;
	private int original;
	
	/**
	*   Creates a Block with the given values
	*	@param x1 the x value
	*	@param y1 the y value
	*	@param jc the game component
	*/
	public Block(int x1, int y1, JComponent jc)
	{
		pic = new ImageIcon(getClass().getResource("/Block.gif")).getImage();
		x = x1;
		y = y1;
		j = jc;
		original = y1;
	}
	
	/**
      Draws the block in the current position.
      @param g2 the graphics context
   	*/
	public void draw(Graphics2D g)
	{
		g.drawImage(pic,x,y,j);
	}
	
	/**
    * Adjusts the block's y by ten pixels
    */
	public void increment()
	{
		y+=10;
		if(y > 1100)
	//		y = (int)(Math.random()*-2000);
			y = original;
	}
	/**
   	*  Returns the x value.
   	*@return the x value
   	*/
	public int getX()
	{
		return x;
	}
	
	/**
   	*  Returns the y value.
   	*@return the y value
   	*/
	public int getY()
	{
		return y;
	}
	
}