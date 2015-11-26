package environments;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import maps.World;
import controller.HitBox;
import player.Player;

/**
 * @author Martin
 * Spikes that do damage to anyone who touches them
 */
public class Spikes extends Environment{
	/**
	 * Amount of damage dealt upon touch
	 */
	private int damage; //dmg dealt upon contact;
	/**
	 * Image of the spikes
	 */
	private Image image;//image of the spike
	/**
	 * Direction of the image
	 */
	private int direction;
	
	/**
	 * @param x X position of the spikes
	 * @param y Y position of the spikes
	 * @param length Lenght of the spikes
	 * @param height Height of the spikes
	 */
	public Spikes(int x, int y, int length, int height){
		super.height = height;
		super.length = length;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);
		damage = 3;
		
		java.net.URL resource0=getClass().getResource("/environments/images/spikes0.png");
	
		image = new ImageIcon(resource0).getImage();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image,(int) (getX() * World.RATIO),(int) (getY() * World.RATIO), length * World.RATIO, height * World.RATIO, null);		
	}

	@Override
	public void onTouch(Player player) {
		if(!player.getFighter().isIncapacitated())
			player.getFighter().getsHit(damage, getHitBox().getHitDirection(player.getFighter().getHitBox()), 0.5);
		
	}
	@Override
	public void rotateImage(String orientation) {
		if(orientation == "L"){
			if(direction == 0)
				direction = 3;
			else
				direction--;		
		}
		if(orientation == "R"){
			if(direction == 3)
				direction = 0;
			else
				direction++;		
		}
		java.net.URL resource0=getClass().getResource("/environments/images/spikes" + direction + ".png");
		image = new ImageIcon(resource0).getImage();
		
	}
	@Override
	public void tick(ArrayList<Platform> platFormList) {
		// chill
		
	}


	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	

}
