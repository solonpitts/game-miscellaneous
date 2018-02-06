/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * GameComponent.java
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;


/**
*   This component manages the main flight screen, and draws the plane, the enemies, the projectiles, and the barriers.
*/
public class GameComponent extends JComponent implements KeyListener
{  
private String environment;
private JPanel panel;
private Plane plane;
private JFrame frame;
private Timer timer;
private int x;
private int y;
private Image skyImage;
private Image starImage;
private int yImage;
private Block[] blocks;
private Long time;
private Long timeSinceHit;
private boolean fired;
private PlayerDeath playerDeath;
private boolean died;
private int enemyDeathCounter;
private int enemyNum;
private int level;
private int enemyFireSpeed;
private int lives;

private int xSpeed;
private int ySpeed;

private ArrayList<Projectile> projectiles;
private ArrayList<Projectile> eProjectiles;
private ArrayList<Enemy> enemies;
private ArrayList<Death> deaths;
private ArrayList<Cloud> clouds;


private JLabel xSpeedModifier;
private JLabel ySpeedModifier;
private JLabel life;
private JLabel fireRate;

/**
*   Constructs the GameComponent with a given Frame, Plane, and default values for the instance fields.
*	@param f the given Frame
*	@param p the given Plane
*/
	public GameComponent(JFrame f, Plane p)
	{
		xSpeed = 10;
		ySpeed = 10;
		
		lives = 1;
		environment = "Sky Mode";
		enemyNum = 5;
		enemyFireSpeed = 30;
		enemyDeathCounter = 0;
		level = 1;
		died = false;
		
		playerDeath = new PlayerDeath(0,0);
		fired = false;
		time = System.currentTimeMillis();
		timeSinceHit = System.currentTimeMillis();
		
		deaths = new ArrayList<Death>();
		eProjectiles = new ArrayList<Projectile>();
		projectiles = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		enemies.add(new Enemy(200,100,10,this));
		enemies.add(new Enemy(400,100,10,this));
		enemies.add(new Enemy(600,100,10,this));
		enemies.add(new Enemy(800,100,10,this));
		enemies.add(new Enemy(1000,100,10,this));
		
		setSize(1280,987);
		skyImage = new ImageIcon(getClass().getResource("/SkyBlue.gif")).getImage();
		starImage = new ImageIcon(getClass().getResource("/starfield.gif")).getImage();
	//	tiled = ImageIO.read(new File("sky.gif"));
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,4));
		
		xSpeedModifier = new JLabel("Total Horizontal Speed Modifier: ");
		ySpeedModifier = new JLabel("Total Vertical Speed Modifier: ");
		life = new JLabel("Life Remaining: ");
		fireRate = new JLabel("Fire Rate: ");
		panel.add(xSpeedModifier);
		panel.add(ySpeedModifier);
		panel.add(fireRate);
		panel.add(life);
		
		setLayout(new BorderLayout());
		add(panel, BorderLayout.SOUTH);
		/////
		
		frame = f;
		plane = p;
		addKeyListener(this);
		setFocusable(true);
		x = 0;
		y = 0;
		
		yImage = 0;
		
		blocks = new Block[4];
		blocks[0] = new Block(0,-100,this);
		blocks[1] = new Block(350,-800,this);
		blocks[2] = new Block(600,-1500,this);
		blocks[3] = new Block(830,-2000, this);
		
		clouds = new ArrayList<Cloud>();
		clouds.add(new Cloud(40,40,this,6));
		clouds.add(new Cloud(300,200,this,8));
		clouds.add(new Cloud(700,400,this,2));
		clouds.add(new Cloud(200,520,this,3));
		
	}
	
/**
*   Draws the component and the plane, the enemies, the projectiles, and the barriers.
*/
	public void paintComponent(Graphics g)
	{	
	
		int height = yImage;
		Graphics2D g2 = (Graphics2D) g;
		
		if(environment.equals("Galaga Mode"))
			for(int i = 0; i < 4; i++)
			{
				g2.drawImage(starImage,0,height-400,this);
				height+=400;
			}
		else
		{
			g2.drawImage(skyImage,0,0,this);
			for(Cloud c: clouds)	
			{
				c.draw(g2);
				c.increment();
				c.shiftDown(4);
			}
		}
			
        	
		for(Death d: deaths)
		{
			d.draw(g2);
		}
		
		

        for(int m = 0; m < 4; m++)
        {
        	blocks[m].draw(g2);
        	if(!died)
        		blocks[m].increment();
        }
       	
       	int pX = plane.getX();
       	int pY = plane.getY();
       	
		for(Enemy e: enemies)
		{
			e.draw(g2);
		}
		
       	if(!died)
       	{
			
	  		g2.drawImage(plane.getEngine().getImage().getImage(),pX,pY,this);
	  		g2.drawImage(plane.getBody().getImage().getImage(),pX,pY,this);
	  		g2.drawImage(plane.getNose().getImage().getImage(),pX,pY,this);
	  		g2.drawImage(plane.getTail().getImage().getImage(),pX,pY,this);
	  		g2.drawImage(plane.getGun().getImage().getImage(),pX,pY,this);
			g2.drawImage(plane.getWing().getImage().getImage(),pX,pY,this);
			
			for(int p = 0; p < projectiles.size(); p++)
			{		
				boolean killed = false;
				for(int e = 0; e < enemies.size(); e++)
				{	
					int width = (plane.getGun().getType().equals("fast")) ? 5 : 15;
					if(enemies.get(e).checkCollide(projectiles.get(p).getX(),projectiles.get(p).getY(),width))
					{
						enemyDeathCounter++;
						deathAnimation(enemies.get(e).getX(),enemies.get(e).getY());
						enemies.remove(e);
						killed = true;
						e--;
					}
				}
				if(killed || checkBulletCollideWithBarrier(projectiles.get(p).getX(),projectiles.get(p).getY()))
					projectiles.remove(p);
				else
					projectiles.get(p).draw(g2);	
				
			}
			
			for(int n = 0; n < eProjectiles.size(); n++)
			{
				int xLeft = eProjectiles.get(n).getX();
				int yTop = eProjectiles.get(n).getY();
				if(xLeft > pX + 49 && xLeft < pX + 85 && yTop > pY + 40 && yTop < pY + 85 && System.currentTimeMillis() - timeSinceHit > 100)
				{
					timeSinceHit = System.currentTimeMillis();
					lives--;
					life.setText("Life Remaining: " + lives);
					if(lives == 0)
						death();	
				}
				else
					eProjectiles.get(n).draw(g2);
			}
			
			if(checkCollideWithBarrier(pX, pY) && System.currentTimeMillis() - timeSinceHit > 100)
			{
				timeSinceHit = System.currentTimeMillis();
				lives--;
				life.setText("Life Remaining: " + lives);
					if(lives == 0)
				death();
			}
					
		}
		else
			playerDeath.draw(g2);
		if(enemyDeathCounter==enemyNum)
		{
			level++;
			timer.cancel();
			
			enemyDeathCounter = 0;
			enemies = new ArrayList<Enemy>();			
			enemies.add(new Enemy(200,100,10,this));
			enemies.add(new Enemy(400,100,10,this));
			enemies.add(new Enemy(600,100,10,this));
			enemies.add(new Enemy(800,100,10,this));
			enemies.add(new Enemy(1000,100,10,this));
			enemyNum = 5;
			
			((FlightFrame)frame).changeToUpgrade();
			switch(level)
			{
				case 5 : JOptionPane.showMessageDialog(this,"Congratulations, Victory has been achieved! If you would like to play again, the game will start from level one, but with the increased enemy attack speed."); level = 1;enemies.get(0).setFireSpeed(enemyFireSpeed*2/3); enemyFireSpeed = enemyFireSpeed*2/3; 
					break;
				case 4 :enemies.get(0).setFireSpeed(enemyFireSpeed*2/3); enemyFireSpeed = enemyFireSpeed*2/3;
				case 3 :enemyNum+=3;enemies.add(new Enemy(600,200,13,this));enemies.add(new Enemy(400,200,13,this)); enemies.add(new Enemy(800,200,13,this));	
				case 2 :enemyNum+=2;enemies.add(new Enemy(1000,200,13,this));enemies.add(new Enemy(200,200,13,this));JOptionPane.showMessageDialog(this,"Round complete! Click fly when ready to start the next level."); revalidate();
				
			}
		}
			  
		
		
	}
	
/**
*   Checks whether the given values of the plane coordinates are within the bounds of the blocks.
*	@param x the x value of the coordinate
*	@param y the y value of the coordinate
*	@return whether the plane collides or not
*/	
	public boolean checkCollideWithBarrier(int x, int y)
	{
		for(int i = 0; i < 4; i++)
		{
			int blockX = blocks[i].getX();
			int blockY = blocks[i].getY();
			if((x + 85 > blockX && x + 49 < blockX + 250) && (y + 136> blockY && y + 43 < blockY + 50))
			return true;
			
		}	
		return false;
	}
	
/**
*   Checks whether the given values of the projectile coordinates are within the bounds of the blocks.
*	@param x the x value of the coordinate
*	@param y the y value of the coordinate
*	@return whether the bullet collides or not
*/	
	public boolean checkBulletCollideWithBarrier(int x, int y)
	{
		for(int i = 0; i < 4; i++)
		{
			int blockX = blocks[i].getX();
			int blockY = blocks[i].getY();
			if((x + 5 > blockX && x < blockX + 250) && (y + 15> blockY && y < blockY + 50))
			return true;
			
		}	
				
		return false;
	}
/**
*   Stops the game and begins the player death animation.
*/	
	public void death()
	{
		level = 1;
		died = true;
		timer.cancel();
		Timer tim = new Timer();
		playerDeath = new PlayerDeath(plane.getX()+65, plane.getY()+65);
		tim.schedule(new PlayerDeathTask(this, frame, playerDeath), 50, 120);

	}
	
/**
*   Begins a enemy death animation at the given coordinates.
*	@param x the x value of the enemy
*	@param y the y value of the enemy
*/		
	public void deathAnimation(int x, int y)
	{
		Timer deathTimer = new Timer();
		deathTimer.schedule(new DeathTask(x,y,deaths), 50, 200);
	}
		
/**
*   Resumes the timer for the game, and gets the most recent version of the plane and the environment String.
*	@param p the given Plane
*	@param s the string for the environment mode
*/			
	public void resumeTimer(String s)
	{
		environment = s;
		timer = new Timer();
		timer.schedule(new GameTask(this),30,30);
		requestFocusInWindow();
		repaint();
	}
 
/**
*   Sets the x and y value to increment the plane, as well as whether or not the plane is currently firing.
*	@param e the KeyEvent performed
*/	   
    public void keyPressed(KeyEvent e)
    {

    	if(e.getKeyCode() == KeyEvent.VK_UP)
    	{
    		y = -ySpeed; //up
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_DOWN)
    	{
    		y = ySpeed;//down
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
    	{
    		x = xSpeed; // right
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_LEFT)
    	{
    		x = -xSpeed; //left
    	}  
    	else if(e.getKeyCode() == KeyEvent.VK_W)
    	{
   	    	y = -ySpeed;//up	
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_S)
    	{
    		y = ySpeed;//down
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_D)
    	{
    		x = xSpeed;//right
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_A)
    	{
    		x = -xSpeed;//left
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_SPACE)
    	{
    		fired = true;
    	}
    		
    }
/**
*   Sets the x and y value to increment the plane to zero, as well as whether or not the plane is currently firing to false.
*	@param e the KeyEvent performed
*/	
    public void keyReleased(KeyEvent e)
    {
    	if(e.getKeyCode() == KeyEvent.VK_UP)
    	{
    		y = 0; //up
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_DOWN)
    	{
    		y = 0;//down
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
    	{
    		x = 0; // right
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_LEFT)
    	{
    		x = 0; //left
    	}  
    	else if(e.getKeyCode() == KeyEvent.VK_W)
    	{
   	    	y = 0;//up	
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_S)
    	{
    		y = 0;//down
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_D)
    	{
    		x = 0;//right
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_A)
    	{
    		x = 0;//left
    	}
    	else if(e.getKeyCode() == KeyEvent.VK_SPACE)
    	{
    		fired = false;
    	}
    	
    }
    public void keyTyped(KeyEvent e)
    {
   	}
   
/**
*   Adds a projectile to the enemy projectiles array list.
*	@param p the projectile to add to the enemy projectiles array list.
*/	  	
   	public void addProjectile(Projectile p)
   	{
   		eProjectiles.add(p);
   	}
   	
/**
*   Resets the game to its initial state
*	@param p the plane which the game will use
*/   	public void reset(Plane p)
   	{
   		eProjectiles = new ArrayList<Projectile>();
		projectiles = new ArrayList<Projectile>();
		blocks = new Block[4];
		blocks[0] = new Block(0,-100,this);
		blocks[1] = new Block(350,-800,this);
		blocks[2] = new Block(600,-1500,this);
		blocks[3] = new Block(830,-2000, this);
		died = false;
		fired = false;
		
		plane = p;
		plane.setX(570);
		plane.setY(600);
		
		lives = plane.getBody().getLives();
		life.setText("Life Remaining: " + lives);
		
		xSpeed = (int)(plane.getXSpeedMod() * 14);
    	ySpeed = (int)(plane.getYSpeedMod() * 14);
    	
    	xSpeedModifier.setText("Total Horizontal Speed Modifier: " + xSpeed);
    	ySpeedModifier.setText("Total Vertical Speed Modifier: " + ySpeed);
    	
    	fireRate.setText("Fire Rate: " + plane.getGun().getAttackSpeed());
    	
   	}

 public void resEnemies()
 {
 	enemies = new ArrayList<Enemy>();
	enemies.add(new Enemy(200,100,10,this));
	enemies.add(new Enemy(400,100,10,this));
	enemies.add(new Enemy(600,100,10,this));
	enemies.add(new Enemy(800,100,10,this));
	enemies.add(new Enemy(1000,100,10,this));
	enemyNum = 5;
	enemyDeathCounter = 0;
			
	JOptionPane.showMessageDialog(null, "YOU DIED. Try Again?");
 }
	
 public void task()
 {
 	plane.increment(x,y);
		yImage += 4;
		if(yImage >= 400)
			yImage = 0;
		for(Enemy e: enemies)
		{
			e.animate();
		}	
		if(fired && System.currentTimeMillis() - time > plane.getGun().getAttackSpeed())
    	{
    		time = System.currentTimeMillis();
    		if(plane.getGun().getType().equals("fast"))
    			projectiles.add(new Projectile(plane.getX()+63, plane.getY(), -25, Color.BLUE));
    		else
    			projectiles.add(new LargeProjectile(plane.getX()+63, plane.getY(), -20, Color.GREEN));
    	}
		
		for(int p = 0; p < projectiles.size(); p++)
		{
			if(projectiles.get(p).animate())
			{
				projectiles.remove(p);
				p--;
			}
		}
		
		for(int pE = 0; pE < eProjectiles.size(); pE++)
		{
			if(eProjectiles.get(pE).animate())
			{
				eProjectiles.remove(pE);
				pE--;
			}
		}
		
		repaint();
 }
}	

