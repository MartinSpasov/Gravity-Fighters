package environments;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import buffs.Slow;
import maps.World;
import controller.HitBox;
import player.Player;

/**
 * @author Martin
 * A snow spike object that does a slowing buff to whoever touches is
 */
public class Snow extends Environment{
	
	/**
	 * Image of the snow spike
	 */
	private Image image;//image of the spike
	/**
	 * Direction of the image
	 */
	private int direction;
	
	/**
	 * @param x X position of the snow
	 * @param y Y position of the snow
	 * @param length Lenght of the snow
	 * @param height Height of the snow
	 */
	public Snow(int x, int y, int length, int height){
		super.height = height;
		super.length = length;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);
		
		java.net.URL resource0=getClass().getResource("/environments/images/ice0.png");
		
		setImage(new ImageIcon(resource0).getImage());
	}
	/**
	 * @param x X position of the snow
	 * @param y Y position of the snow
	 * @param length Lenght of the snow
	 * @param height Height of the snow
	 * @param direction Direction of the image
	 */
	public Snow(int x, int y, int length, int height, int direction){
		super.height = height;
		super.length = length;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);
		this.setDirection(direction);
		
		java.net.URL resource0=getClass().getResource("/environments/images/ice" + direction + ".png");
		
		setImage(new ImageIcon(resource0).getImage());
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
		java.net.URL resource0=getClass().getResource("/environments/images/ice" + direction + ".png");
		image = new ImageIcon(resource0).getImage();
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image,(int) (getX() * World.RATIO),(int) (getY() * World.RATIO), length * World.RATIO, height * World.RATIO, null);
		
	}

	@Override
	public void onTouch(Player player) {
		boolean applyable = true;
		for (int i = 0; i < player.getBuffList().size(); i++){
			if(player.getBuffList().get(i).getBuffType() == "slow")
				applyable = false;
		}
		if(applyable)
			player.getBuffList().add(new Slow(player.getBuffList().size()));
		
	}
	
	@Override
	public void tick(ArrayList<Platform> platFormList) {
		// chill
		
	}

	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}
