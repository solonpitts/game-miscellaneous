/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * PlanePart.java
 */

import javax.swing.*;

/**
*   The base Part of the plane
*/
public class PlanePart
{
	private double HSpeedModifier;
	private double VSpeedModifier;
	private ImageIcon image;
	private String n;
	
	/**
	*   Constructs a new PlanePart with a given name, horizontal speed, vertical speed.
	*	@param name the name
	*	@param x the horizontal speed
	*	@param y the vertical speed
	*/
	public PlanePart(String name, double x, double y)
	{
		n = name;
		image = new ImageIcon(getClass().getResource("/" + name + ".png"));
		HSpeedModifier = x;
		VSpeedModifier = y;
	}
	
	/**
	*   Gets the image of the plane
	*	@return the image of the part
	*/
	public ImageIcon getImage()
	{
		return image;
	}
	
	/**
	*   Gets the string version of the part
	*	@return the name of the part
	*/
	public String toString()
	{
		return n;
	}	
	
	/**
	*   Gets the horizontal speed modifier
	*	@return the speed modifier
	*/
	public double getHorizontalSpeedModifier()
	{
		return HSpeedModifier;
	}
	
	/**
	*   Gets the vertical speed modifier
	*	@return the speed modifier
	*/
	public double getVerticalSpeedModifier()
	{
		return VSpeedModifier;
	}
}