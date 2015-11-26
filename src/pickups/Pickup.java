package pickups;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import maps.World;
import controller.HitBox;
import characters.Fighter;

public abstract class Pickup {
	/**
	 * x position in game units of the pickup
	 */
	private int x; //x position in game units of the pickup
	/**
	 * y position in game units of the pickup
	 */
	private int y; //y position in game units of the pickup
	/**
	 * image of the pickup
	 */
	private Image image;// image of the pickup
	/**
	 * Lenght of the pickup
	 */
	public static final int LENGHT = 4;
	/**
	 * Hitbox of the pickup
	 */
	private HitBox hitBox;
	/**
	 * True once the pickup is used
	 */
	private boolean used;
	/**
	 * Time until the pickup will respawn
	 */
	private int usedTimer;
	
	/**
	 * @param source Name of the pickup
	 * @param x X position of the pickup
	 * @param y Y position of the pickup
	 */
	protected Pickup(String source, int x, int y){
		this.x = x;
		this.y = y;
		hitBox = new HitBox(x, y, x + LENGHT, y + LENGHT);
		
		java.net.URL resource1=getClass().getResource("/pickups/images/" + source + ".png");
		image = new ImageIcon(resource1).getImage();
	}
	
	/**
	 * Draws the pickup
	 * @param g Paintcomponent
	 */
	public void draw(Graphics g){
		if(used && usedTimer < 1800)
			usedTimer++;
		else{
			used = false;
			usedTimer =0;
			g.drawImage(image, x * World.RATIO, y * World.RATIO, LENGHT * World.RATIO, LENGHT * World.RATIO, null);
		}
		
	}
	/**
	 * Action that happens when a player walks on the pickup
	 * @param fighter fighter that walks on the pickup
	 */
	public abstract void pickup(Fighter fighter);
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public HitBox getHitBox() {
		return hitBox;
	}
	public void setHitBox(HitBox hitBox) {
		this.hitBox = hitBox;
	}

	public int getUsedTimer() {
		return usedTimer;
	}

	public void setUsedTimer(int usedTimer) {
		this.usedTimer = usedTimer;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

}
