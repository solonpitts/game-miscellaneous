/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * GameTask.java
 */
 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

/**
*   The central timer for the game
*/	
public class GameTask extends TimerTask
{
	private JComponent j;
	
	/**
	*   Creates a game task
	*	@param the game component
	*/
	public GameTask(JComponent jc)
	{
		j = jc;
	}
	
	/**
	*   Performs the game's main operations
	*/
	public void run()
	{	
		((GameComponent)j).task();
	}
}


