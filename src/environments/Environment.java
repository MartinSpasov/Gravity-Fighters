package environments;

import java.awt.Graphics;
import java.util.ArrayList;

import player.Player;
import controller.HitBox;

/**
 * @author Martin
 * Environment is a small abstract class which defines the basic positioning of all of the objects found in the environment of the maps. Inside the PaintComponent, the abstract class draw will be called for every object of the current Map to render the it. 
 */
public abstract class Environment {
	/**
	 * X position in game untis
	 */
	protected double x; //y position in game untis
	/**
	 * Y position in game untis
	 */
	protected double y; //x position in game units
	/**
	 * Height (y axis) of the platform
	 */
	protected int height; //Height (y axis) of the platform
	/**
	 * Length (x axis) of the platform
	 */
	protected int length; //Length (x axis) of the platform
	/**
	 * HitBox of the environment
	 */
	protected HitBox hitBox;
	
	/**
	 * Rotates the image of the environment
	 * @param orientation Orientation the image will be rotated to
	 */
	public abstract void rotateImage(String orientation);
	/**
	 * Draws the image of the environment
	 * @param g Paintcomponent
	 */
	public abstract void draw(Graphics g);
	/**
	 * Action taken when a player touches the environment
	 * @param player Player that touches the environment 
	 */
	public abstract void onTouch(Player player);
	/**
	 * Updates the image and position for moving objects
	 * @param platFormList checks for moving objects if they enter in contact with a paltform
	 */
	public abstract void tick(ArrayList<Platform> platFormList);

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public HitBox getHitBox() {
		return hitBox;
	}

	public void setHitBox(HitBox hitBox) {
		this.hitBox = hitBox;
	}
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
