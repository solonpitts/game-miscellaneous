/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * UpTask.java
 */
 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
 
   /**
   	*  	Runs the background animation
   	*/
   public class UpTask extends TimerTask
   {
   		private ArrayList<Cloud> clouds;
   		private JComponent jc;
   		
   		/**
   		*  	creates an UpTask
   		*	@param c the cloud list
   		*	@param j the component of the game
   		*/
   		public UpTask(ArrayList<Cloud> c, JComponent j)
   		{
   			clouds = c;
   			jc = j;
   		}
   		
   		/**
   		*  	increments the clouds and repaints
   		*/
   		public void run()
   		{
   			for(Cloud c: clouds)
   				c.increment();
   			jc.repaint();
   		}   	
   }