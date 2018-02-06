/* 
 * Solon Pitts
 * Mrs G.
 * 2nd
 * BodyPart.java
 */

/**
*   The body of the plane
*/ 
public class BodyPart extends PlanePart
{
	private int lives;
	
	/**
	*   Constructs a new BodyPart with a given name, horizontal speed, vertical speed, and lives
	*	@param name the name
	*	@param s the horizontal speed
	*	@param y the vertical speed
	*	@param l the lives
	*/	
	public BodyPart(String name,int l, double s,double y)
	{
		super(name,s,y);
		lives = l;
	}
	
	/**
	*   gets the number of lives
	*	@return the number of lives
	*/
	public int getLives()
	{
		return lives;
	}
}