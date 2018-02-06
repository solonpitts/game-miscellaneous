/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * Start.java
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

/**
   This component manages the main menu of the game
*/
public class StartMenu extends JComponent implements ActionListener
{  
    private ImageIcon SkyImage;
	private JButton start;
	private JButton instructions;
	private JButton quit;
	private JFrame frame;
	private ArrayList<Cloud> clouds;
	private Timer timer;
	
	/**
	 * Adds the buttons and the background imagery to the component
	 */
   public StartMenu(JFrame j) 
   {
   		setSize(1280,987);
   		clouds = new ArrayList<Cloud>();
   		clouds.add(new Cloud(40,40,this,6));
   		clouds.add(new Cloud(300,200,this,8));
   		clouds.add(new Cloud(700,400,this,2));
   		clouds.add(new Cloud(200,520,this,3));
   		clouds.add(new Cloud(40,800,this,7));
   		clouds.add(new Cloud(700,250,this,4));
   		
   		SkyImage = new ImageIcon(getClass().getResource("/SkyBlue.gif"));
   		frame = j;
   		JPanel panel = new JPanel();
   		start = new JButton("Start");
		start.addActionListener(this);
		quit = new JButton("Quit");
		quit.addActionListener(this);
		instructions = new JButton("Instructions");
		instructions.addActionListener(this);
		panel.setLayout(new GridLayout(3,1));
		panel.add(start);
		panel.add(instructions);
		panel.add(quit);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = c.BOTH;
		c.gridx = 300;
		c.gridy = 200;
		c.ipadx = 100;
		c.ipady = 200;
		add(panel,c);
		
  		
   }
   
   /**
    * Draws the clouds and the sky
    * @param g the Graphics object
    */
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;
      g2.drawImage(SkyImage.getImage(),0,0,this);
	  for(Cloud c: clouds)
	  	c.draw(g2);
   }
   
   /**
   	*  	performs an action based on the action event
   	*	@param e the ActionEvent
   	*/
   public void actionPerformed(ActionEvent e)
   {
   	  String s = ((JButton)e.getSource()).getText();
   	  if(s.equals("Start"))
   	  {
   	  	((FlightFrame)frame).changeToUpgrade();
   	  	timer.cancel();
   	  }
   	  else if(s.equals("Instructions"))
   	  {
   	  	String text = "Play the game and don't do drugs.";
   	  	JOptionPane pane = new JOptionPane();
   	  	pane.showMessageDialog(null,text);
   	  	
   	  }
   	  else if(s.equals("Quit"))
   	  {
   	  	frame.dispose();
   	  	timer.cancel();
   	  }
   }
   
   /**
   	*  	restarts the timer
   	*/
   public void resumeTimer()
   {
   		timer = new Timer();
		timer.schedule(new UpTask(clouds,this),50,50);
   }
   
   
}