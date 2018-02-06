/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * FlightFrame.java
 */
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
*  	The main frame of the whole game
*/
public class FlightFrame extends JFrame
{
	private GameComponent game;
	private UpgradeScreen up;
	private StartMenu starter;
	private JPanel panel;
	private Plane plane;
	
	/**
   	*  	Constructs the frame and its components
   	*/
	public FlightFrame()
	{
		plane = new Plane();

		setTitle("Flight Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280,987);
		game = new GameComponent(this, plane);
		starter = new StartMenu(this);
		starter.resumeTimer();	
		up = new UpgradeScreen(plane, this);
		add(starter);
		setVisible(true);
	}
	
	/**
   	*  	changes the active component to the upgrade screen
   	*/
	public void changeToUpgrade()
	{
		remove(starter);
		remove(game);
		add(up);
		up.resumeTimer();
		revalidate();repaint();
	}
	
	/**
   	*  	changes the active component to the game screen
   	*/
	public void changeToFly(Plane p, String s)
	{
		plane = p;
		remove(up);
		add(game);
		game.reset(plane);
		game.resumeTimer(s);
		revalidate();repaint();
	}
	
	/**
   	*  	changes the active component to the menu screen
   	*/
	public void changeToStartMenu()
	{	
		remove(up);
		add(starter);
		starter.resumeTimer();
		
		revalidate();repaint();	
	}
}

