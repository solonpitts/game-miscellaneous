/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * GunPart.java
 */

/**
*   The gun of the ship
*/	
public class GunPart extends PlanePart
{ 
	private String type;
	private int attackSpeed;
	
	/**
	*   Constructs a new GunPart with a given name, horizontal speed, vertical speed, a type, and a fire rate.
	*	@param name the name
	*	@param s the horizontal speed
	*	@param y the vertical speed
	*	@param t the type
	*	@param n the fire rate
	*/	
	public GunPart(String name, double s,double y, String t, int n)
	{
		super(name,s,y);
		type = t;
		attackSpeed = n;
	}
	
	/**
	*   Gets the type of the gun.
	*	@return the type
	*/	
	public String getType()
	{
		return type;
	}
	
	/**
	*   Gets the attack speed of the gun.
	*	@return the the attack speed
	*/	
	public int getAttackSpeed()
	{
		return attackSpeed;
	}
}