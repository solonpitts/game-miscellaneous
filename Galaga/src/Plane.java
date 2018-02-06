/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * Plane.java
 */
import java.util.*;

/**
*   The Plane that contains all of the PlaneParts
*/
public class Plane
{
	private Map<String,PlanePart> map;
	private int x;
	private int y;
	
	/**
	*   Constructs a new Plane with default PlaneParts
	*/
	public Plane()
	{
		map = new TreeMap<String,PlanePart>();
		map.put("body",new BodyPart("BlendedFuselage", 2, .9, .9));
		map.put("wing" , new WingPart("FlyingWing", 1.1, .9));
		map.put("engine" , new EnginePart("LargeJetEngine", .9, 1.1));
		map.put("nose" , new NosePart("ConicNose", .9, 1.1));
		map.put("tail" , new TailPart("T-Tail", 1.1, .9));
		map.put("gun" , new GunPart("SingleGun",.9,.9,"slow", 700));
		x = 500;
		y = 500;
	}
	
	/**
	*   Gets the horizontal speed modifier.
	*	@return the horizontal speed modifier.
	*/
	public double getXSpeedMod()
	{
		return map.get("body").getHorizontalSpeedModifier() * map.get("wing").getHorizontalSpeedModifier()
			 * map.get("engine").getHorizontalSpeedModifier() * map.get("nose").getHorizontalSpeedModifier()
			 * map.get("tail").getHorizontalSpeedModifier() * map.get("gun").getHorizontalSpeedModifier();
	}
	
	/**
	*   Gets the vertical speed modifier.
	*	@return the vertical speed modifier.
	*/
	public double getYSpeedMod()
	{
		return map.get("body").getVerticalSpeedModifier() * map.get("wing").getVerticalSpeedModifier()
			 * map.get("engine").getVerticalSpeedModifier() * map.get("nose").getVerticalSpeedModifier()
			 * map.get("tail").getVerticalSpeedModifier() * map.get("gun").getVerticalSpeedModifier();
		
	}
	
	/**
	*   Increments the x and y values by a given amount.
	*	@param x1 the amount to increment om the x axis
	*	@param y1 the amount to increment on the y axis
	*/
	public void increment(int x1, int y1)
	{
		if((x1 > 0 && x + 150 < 1280) || (x1 <= 0 && x -20 > 0))
			x+=x1;
		if((y1 > 0 && y + 160 < 987) || (y1 <= 0 && y - 20 > 400))
			y+=y1;
	}
	
	/**
	*   Sets the x value.
	*	@param newX the new x value
	*/
	public void setX(int newX)
	{
		x = newX;
	}
	
	/**
	*   Sets the y value
	*	@param newY the new y value 
	*/
	public void setY(int newY)
	{
		y = newY;
	}
	
	/**
	*   Gets the x value.
	*	@return the x.
	*/
	public int getX()
	{
		return x;
	}
	
	/**
	*   Gets the y value.
	*	@return the y.
	*/
	public int getY()
	{
		return y;
	}
	
	/**
	*   Sets the Wing of the plane.
	*	@param w the new Wing.
	*/	
	public void setWing(WingPart w)
	{
		map.put("wing", w);
	}
	/**
	*   Sets the Gun of the plane.
	*	@param g the new GunPart.
	*/	
	public void setGun(GunPart g)
	{
		map.put("gun", g);
	}
	/**
	*   Sets the Body of the plane.
	*	@param b the new Body.
	*/		
	public void setBody(BodyPart b)
	{
		map.put("body", b);
	}
	/**
	*   Sets the Tail of the plane.
	*	@param t the new Tail.
	*/	
	public void setTail(TailPart t)
	{
		map.put("tail", t);
	}
	/**
	*   Sets the Engine of the plane.
	*	@param e the new Engine.
	*/	
	public void setEngine(EnginePart e)
	{
		map.put("engine", e);;
	}
	/**
	*   Sets the Nose of the plane.
	*	@param n the new Nose.
	*/	
	public void setNose(NosePart n)
	{
		map.put("nose", n);
	}
	/**
	*   Gets the Wing of the plane.
	*	@return the WingPart
	*/	
	public WingPart getWing()
	{
		return (WingPart)map.get("wing");
	}
	/**
	*   Gets the Body of the plane.
	*	@return the BodyPart
	*/	
	public BodyPart getBody()
	{
		return (BodyPart)map.get("body");
	}
	/**
	*   Gets the Tail of the plane.
	*	@return the TailPart
	*/	
	public TailPart getTail()
	{
		return (TailPart)map.get("tail");
	}
	/**
	*   Gets the Engine of the plane.
	*	@return the EnginePart
	*/	
	public EnginePart getEngine()
	{
		return (EnginePart)map.get("engine");
	}
	/**
	*   Gets the Nose of the plane.
	*	@return the NosePart
	*/	
	public NosePart getNose()
	{
		return (NosePart)map.get("nose");
	}
	/**
	*   Gets the Gun of the plane.
	*	@return the GunPart
	*/	
	public GunPart getGun()
	{
		return (GunPart)map.get("gun");
	}
	
}