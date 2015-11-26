package controller;

/**
 * @author Martin
 * A hit box which is used for all collision purpouses almost every game object has one that follows it when moving
 */
public class HitBox {
	/**
	 * Top left X position of the hitbox
	 */
	private double topLeftX; //top left position of the hitbox
	/**
	 * Top left Y position of the hitbox
	 */
	private double topLeftY;
	/**
	 * Bottom Right X position of the hitbox
	 */
	private double bottomRightX;
	/**
	 * Bottom Right Y position of the hitbox
	 */
	private double bottomRightY;//bottomRight position of the hitbox
	
	/**
	 * @param topLeftX Top left x position of the object which owns the hitbox
	 * @param topLeftY Top left y position of the object which owns the hitbox
	 * @param bottomRightX Bottom right x position of the object which owns the hitbox
	 * @param bottomRightY Bottom right y position of the object which owns the hitbox
	 */
	public HitBox(double topLeftX,double topLeftY, double bottomRightX, double bottomRightY){ //Constructor
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.bottomRightX = bottomRightX;
		this.bottomRightY = bottomRightY;
	}
	/**
	 * Method to see if two hitboxes collide
	 * @param h Second hitbox to check collision
	 * @return True if there is a collision
	 */
	public boolean collidesWith(HitBox h){ //Method that checks if 2 hitboxes are colliding
		
		if (topLeftX >= h.getBottomRightX() ^ h.getTopLeftX() >= bottomRightX){ //Checks if the hitbox is on the left of the other			
			return false;		
		}
		if(topLeftY <= h.getBottomRightY() ^ h.getTopLeftY() <= bottomRightY){ //Checks if the hitbox is below the other			
			return false;
		}
						
		return true;
		
	}
	/**
	 * Method to get the direction from which two hitboxes collide
	 * @param enemy Hitbox which collides with this one
	 * @return "left" or "right" depending on the direction of the collision
	 */
	public String getHitDirection(HitBox enemy){ //Method that returns a string indicating what direction should the oppenent get knocked back
		if(getTopLeftX() - enemy.getTopLeftX() > 0)
			return "left";
		else if(getTopLeftX() - enemy.getTopLeftX() < 0)
			return "right";
		else
			return "left";
	}
		
		
	
	/**
	 * updates the position of the hitbox
	 * @param topLeftX Top left x position of the object which owns the hitbox
	 * @param topLeftY Top left y position of the object which owns the hitbox
	 * @param bottomRightX Bottom right x position of the object which owns the hitbox
	 * @param bottomRightY Bottom right y position of the object which owns the hitbox
	 */
	public void update(double topLeftX,double topLeftY, double bottomRightX, double bottomRightY){ //Updates the hitbox to follow the object
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		this.bottomRightX = bottomRightX;
		this.bottomRightY = bottomRightY;
	}
	public String toString(){
		return "x1: " + topLeftX + " y1: " + topLeftY + " x2: " + bottomRightX + " y2: " + bottomRightY; 
	}
	
	public double getTopLeftX() {
		return topLeftX;
	}
	public double getTopLeftY() {
		return topLeftY;
	}
	public double getBottomRightX() {
		return bottomRightX;
	}
	public double getBottomRightY() {
		return bottomRightY;
	}
	

}
