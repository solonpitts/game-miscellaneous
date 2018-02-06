/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * UpgradeScreen.java
 */
 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

/**
   This component manages the configure ship screen
*/
public class UpgradeScreen extends JComponent implements ActionListener, ItemListener
{  

 //  private ImageIcon image;
	private JButton fly;
	private JButton quit;
	private String environment;
	
	private JLabel wingLabel;
	private JLabel noseLabel;
	private JLabel tailLabel;
	private JLabel bodyLabel;
	private JLabel engineLabel;
	private JLabel gunLabel;
	private JLabel eLabel;
	
	private JComboBox<WingPart> wingMenu;
	private JComboBox<NosePart> noseMenu;
	private JComboBox<TailPart> tailMenu;
	private JComboBox<BodyPart> bodyMenu;
	private JComboBox<EnginePart> engineMenu;
	private JComboBox<GunPart> gunMenu;
	
	private Plane plane;
	private JFrame frame;
	private ImageIcon background;
	
	private JComboBox<String> eMenu;
	
	private ArrayList<Cloud> clouds;
	
	private Timer timer;
	
	private JLabel xSpeedModifier;
	private JLabel ySpeedModifier;
	private JLabel life;
	private JLabel fireRate;
	private JPanel panel;
	
	/**
	 * Adds the Plane parts and environment options to the component
	 */
   public UpgradeScreen(Plane p, JFrame fr) 
   {
   		clouds = new ArrayList<Cloud>();
   		clouds.add(new Cloud(50,40,this,5));
   		clouds.add(new Cloud(320,220,this,5));
   		clouds.add(new Cloud(720,400,this,1));
   		clouds.add(new Cloud(130,520,this,2));
   		clouds.add(new Cloud(40,800,this,4));
   		clouds.add(new Cloud(720,250,this,3));
   		
   		   		
   		environment = "Sky Mode";
   		setSize(1280,987);
   		background = new ImageIcon(getClass().getResource("/SkyBlue.gif"));
   		
   		plane = p;
   		frame = fr;
   		
   		wingMenu = new JComboBox<WingPart>();
   		wingMenu.setName("wingMenu");   		
   		wingMenu.addItem(new WingPart("FlyingWing", 1.1, .9));
   		wingMenu.addItem(new WingPart("TriangleWing", .9, 1.1));

   		wingMenu.addItemListener(this);
   		
   		noseMenu = new JComboBox<NosePart>();
   		noseMenu.setName("noseMenu");
   		noseMenu.addItem(new NosePart("ConicNose", .9, 1.1));
   		noseMenu.addItem(new NosePart("BiconicNose", 1.1, .9));
   		noseMenu.addItemListener(this);
   		
   		tailMenu = new JComboBox<TailPart>();
   		tailMenu.setName("tailMenu");
   		tailMenu.addItem(new TailPart("T-Tail", 1.1, .9));
   		tailMenu.addItem(new TailPart("CruciformTail", .9, 1.1));
   		tailMenu.addItemListener(this);
   	
   		bodyMenu = new JComboBox<BodyPart>();
   		bodyMenu.setName("bodyMenu");   		
   		bodyMenu.addItem(new BodyPart("BlendedFuselage", 2, .9, .9));
   		bodyMenu.addItem(new BodyPart("TubeFuselage",1 , 1 ,1));

   		bodyMenu.addItemListener(this);
   		
   		engineMenu = new JComboBox<EnginePart>();
   		engineMenu.setName("engineMenu");
   		engineMenu.addItem(new EnginePart("LargeJetEngine", .9, 1.1));
   		engineMenu.addItem(new EnginePart("DoubleEngine", 1.1, .9));
   		engineMenu.addItemListener(this);
   		
   		gunMenu = new JComboBox<GunPart>();
   		gunMenu.setName("gunMenu");
   		gunMenu.addItem(new GunPart("SingleGun",.9,.9,"slow", 700));
   		gunMenu.addItem(new GunPart("MachineGun",1,1,"fast", 400));
   		gunMenu.addItemListener(this);
   		
   		eMenu = new JComboBox<String>();
   		eMenu.setName("eMenu");
   		eMenu.addItem("Sky Mode");
   		eMenu.addItem("Galaga Mode");
   		eMenu.addItemListener(this);
   		   		   		
   		fly = new JButton("Fly");
		fly.addActionListener(this);
		quit = new JButton("Quit To Menu");
		quit.addActionListener(this);
		JPanel botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(1,2));
		botPanel.add(quit);
		botPanel.add(fly);
		setLayout(new BorderLayout());
		add(botPanel, BorderLayout.SOUTH);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new GridLayout(4,1));
		
		
		JPanel win = new JPanel();
		win.setLayout(new FlowLayout());
		wingLabel = new JLabel("Wing: ");
		win.add(wingLabel);
		win.add(wingMenu);
		sidePanel.add(win);
		
		JPanel nos = new JPanel();
		nos.setLayout(new FlowLayout());
		noseLabel = new JLabel("Nose: ");
		nos.add(noseLabel);
		nos.add(noseMenu);
		sidePanel.add(nos);
		
		
		JPanel tal = new JPanel();
		tal.setLayout(new FlowLayout());
		tailLabel = new JLabel("Tail: ");
		tal.add(tailLabel);
		tal.add(tailMenu);
		sidePanel.add(tal);
		
		
		JPanel bod = new JPanel();
		bod.setLayout(new FlowLayout());
		bodyLabel = new JLabel("Body: ");
		bod.add(bodyLabel);
		bod.add(bodyMenu);
		sidePanel.add(bod);
		
		
		JPanel eng = new JPanel();
		eng.setLayout(new FlowLayout());
		engineLabel = new JLabel("Engine: ");
		eng.add(engineLabel);
		eng.add(engineMenu);
		sidePanel.add(eng);
		
		
		JPanel gu = new JPanel();
		gu.setLayout(new FlowLayout());
		gunLabel = new JLabel("Gun: ");
		gu.add(gunLabel);
		gu.add(gunMenu);
		sidePanel.add(gu);
		
		JPanel envir = new JPanel();
		envir.setLayout(new FlowLayout());
		eLabel = new JLabel("Environment: ");
		envir.add(eLabel);
		envir.add(eMenu);
		sidePanel.add(envir);
		
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		
		xSpeedModifier = new JLabel("Total Horizontal Speed Modifier: ");
		ySpeedModifier = new JLabel("Total Vertical Speed Modifier: ");
		life = new JLabel("Life Remaining: ");
		fireRate = new JLabel("Fire Rate: ");
		
		panel.add(xSpeedModifier);
		panel.add(ySpeedModifier);
		panel.add(fireRate);
		panel.add(life);
		
		sidePanel.add(panel);
		
  		add(sidePanel, BorderLayout.WEST);
   }
   
   /*
    * Draws background and the parts of the plane
    * @param g the Graphics object
    */
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;
      g2.drawImage(background.getImage(),0,0,this);
	  for(Cloud c: clouds)
	  {
	  	c.draw(g2);
	  }
      
	  g2.drawImage(plane.getEngine().getImage().getImage(),890,420,this);
	  g2.drawImage(plane.getBody().getImage().getImage(),890,420,this);
	  g2.drawImage(plane.getNose().getImage().getImage(),890,420,this);
	  g2.drawImage(plane.getTail().getImage().getImage(),890,420,this);
	  g2.drawImage(plane.getGun().getImage().getImage(),890,420,this);
	  g2.drawImage(plane.getWing().getImage().getImage(),890,420,this);
	  
	  wingLabel.setIcon(plane.getWing().getImage());   
	  bodyLabel.setIcon(plane.getBody().getImage()); 
	  engineLabel.setIcon(plane.getEngine().getImage()); 
	  noseLabel.setIcon(plane.getNose().getImage()); 
	  tailLabel.setIcon(plane.getTail().getImage());
	  gunLabel.setIcon(plane.getGun().getImage());
	  
	  life.setText("Life Remaining: " + plane.getBody().getLives());
	  
      xSpeedModifier.setText("Total Horizontal Speed Modifier: " + (int)(plane.getXSpeedMod() * 14));
      ySpeedModifier.setText("Total Vertical Speed Modifier: " + (int)(plane.getYSpeedMod() * 14));
    	
      fireRate.setText("Fire Rate: " + plane.getGun().getAttackSpeed());  
	  
	  }
   
   /**
   	*  	performs an action dependent on the action event, either changing to the game component
   	*	or the main menu
   	*	@param e the action event
   	*/
   public void actionPerformed(ActionEvent e)
   {
   	  String s = ((JButton)e.getSource()).getText();
   	  if(s.equals("Fly"))
   	  {
   	  	timer.cancel();
   	  	((FlightFrame)frame).changeToFly(plane, environment);
   	  }
   	  else if(s.equals("Quit To Menu"))
   	  {
   	  	timer.cancel();
   	  	((FlightFrame)frame).changeToStartMenu();
   	  }
   }
   
   	/**
       Performs an action when a new item is clicked on the ComboBox, selectng a new plane part
       or environment option
       @param n ItemEvent of the ComboBox
	*/
  	public void itemStateChanged(ItemEvent n)
  	{
  		String name = ((JComboBox)n.getSource()).getName();
  		Object o = ((JComboBox)n.getSource()).getSelectedItem();
  		if(name.equals("wingMenu"))
  		{
  			plane.setWing((WingPart)o);
  			repaint();
  		}
  		else if(name.equals("noseMenu"))
  		{
  			plane.setNose((NosePart)o);
  			repaint();
  		}
  		else if(name.equals("tailMenu"))
  		{
  			plane.setTail((TailPart)o);
  			repaint();
  		}
  		else if(name.equals("bodyMenu"))
  		{
  			plane.setBody((BodyPart)o);
  			repaint();
  		}
  		else if(name.equals("engineMenu"))
  		{
  			plane.setEngine((EnginePart)o);
  			repaint();
  		}
  		else if(name.equals("gunMenu"))
  		{
  			plane.setGun((GunPart)o);
  			repaint();
  		}
  		else if(name.equals("eMenu"))
  		{
  			environment = (String)o;
  		}
  		//image.setIcon(new ImageIcon(type + ".gif"));
  	}
  
  /**
   	*  	restarts the timer for the animation
   	*/	
  public void resumeTimer()
   {
   		timer = new Timer();
		timer.schedule(new UpTask(clouds,this),50,50);
   }
   
}