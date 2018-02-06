/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * Cloud.java
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
*  	The clouds in the background
*/
public class Cloud
{
   	private ImageIcon CloudImage;
   	private int x;
   	private int y;
   	private JComponent j;
   	private int s;
   	
   	/**
   	*   Creates a cloud with the given values
   	*	@param x1 the x value
   	*	@param y1 the y value
   	*	@param jc the game component
   	*	@param speed the movement rate
   	*/	
   	public Cloud(int x1, int y1, JComponent jc, int speed)
   	{
   		CloudImage = new ImageIcon(getClass().getResource("/cloud.png"));
   		x = x1;
   		y = y1;
   		j = jc;
   		s = speed;
   	}
   	
   	/**
   	*  	shifts the y value by a given amount
   	*	@param n the amount to shift the image down
   	*/
   	public void shiftDown(int n)
   	{
   		y+=n;
   		if(y > j.getBounds().getHeight())
   			y = -200;
   	}
   	
   	/**
   	*  	draws the cloud image at the x and y points
   	*	@param g2 the graphics object
   	*/
   	public void draw(Graphics g2)
   	{
	    g2.drawImage(CloudImage.getImage(), x, y, j);
   	}
   	
   	/**
   	*  	increments the x value by the speed value
   	*/
   	public void increment()
   	{
   		x-=s;
   		if(x<-280)
   			x=(int)j.getBounds().getWidth();
   	}
}