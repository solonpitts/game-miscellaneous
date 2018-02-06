/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * DeathTask.java
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

/**
*   Animates enemy deaths
*/
public class DeathTask extends TimerTask
{
	private Death d;
	private int counter;
	private ArrayList<Death> deaths;
	
	/**
	*   Constructs a new deathTask and a new enemy Death animation
	*	@param x the x value of the enemy
	*	@param y the y value of the enemy
	*	@param g the list of deaths
	*/
	public DeathTask(int x, int y, ArrayList<Death> g)
	{
		d = new Death(x,y);
		deaths = g;
		deaths.add(d);
		counter = 0;
		
	}
	
	/**
	*   increments the animation
	*/
	public void run()
	{
		counter++;
		if(counter>4)
		cancel();
		d.change();
	}
}
