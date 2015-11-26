package environments;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import player.Player;
import maps.World;
import controller.HitBox;

/**
 * @author Martin
 * A platform object that has total collision with players and projectile, allows be walked and not passed trough
 */
public class Platform extends Environment{ // A Object representing a single ingame platform
	
	/**
	 * The color of the platform, by default black
	 */
	private Color color = Color.BLACK; //The color of the platform, by default black	
	/**
	 * True if willing to have a black border on the platform
	 */
	private boolean bordered = true;

	/**
	 * @param x X position of the platform
	 * @param y Y position of the platform
	 * @param length Lenght of the platform
	 * @param height Height of the platform
	 */
	public Platform(int x, int y, int length, int height) { //Constructor for a single blackform with default color black
		super.height = height;
		super.length = length;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);

	}

	/**
	 * @param x X position of the platform
	 * @param y Y position of the platform
	 * @param length Lenght of the platform
	 * @param height Height of the platform
	 * @param color Color of the platform
	 */
	public Platform(int x, int y, int length, int height,  Color color) { //same as above constructor only this time with a specified color.
		this.height = height;
		this.length = length;
		this.color = color;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);
	}
	/**
	 * @param x X position of the platform
	 * @param y Y position of the platform
	 * @param length Lenght of the platform
	 * @param height Height of the platform
	 * @param color Color of the platform
	 * @param bordered True if platform has a black border
	 */
	public Platform(int x, int y, int length, int height,  Color color, boolean bordered) { //same as above constructor only this time with a specified color.
		this.height = height;
		this.length = length;
		this.color = color;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);
		this.bordered = bordered;
	}

	public void draw(Graphics g) { //Draw method which is called from the calss containing the paintcomponent to draw the platform.
		g.setColor(color);
		g.fillRect((int)x * World.RATIO, (int)y * World.RATIO, length * World.RATIO,height * World.RATIO);
		g.setColor(Color.BLACK);
		if (bordered)
			g.drawRect((int)x * World.RATIO, (int)y * World.RATIO, length * World.RATIO,height * World.RATIO);
	}



	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void onTouch(Player player) {
		//chill
		
	}

	@Override
	public void rotateImage(String direction) {
		// chill
		
	}

	@Override
	public void tick(ArrayList<Platform> platFormList) {
		// chill
		
	}

	public boolean isBordered() {
		return bordered;
	}

	public void setBordered(boolean bordered) {
		this.bordered = bordered;
	}

}
