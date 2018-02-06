/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * PlayerDeathTask.java
 */
 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

/**
*   Animates player deaths
*/
public class PlayerDeathTask extends TimerTask
{
	private int counter;
	private JComponent c;
	private JFrame frame;
	private PlayerDeath playerDeath;
	
	/**
	*   Constructs a new playerDeathTask
	*	@param j the game component
	*	@param fram the frame
	*	@param d the PlayerDeath
	*/
	public PlayerDeathTask(JComponent j, JFrame fram, PlayerDeath d)
	{
		counter = 0;
		c = j;
		frame = fram;
		playerDeath = d;
	}
	
	/**
	*   Animates the player death and ends the game when complete
	*/
	public void run()
	{
		counter++;
		if(counter>9)
		{
			cancel();
			((FlightFrame)frame).changeToUpgrade();
			((GameComponent)c).resEnemies();
		}
		playerDeath.change();
		c.repaint();
	}
}
