package environments;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import buffs.Burn;
import maps.World;
import controller.HitBox;
import player.Player;

/**
 * @author Martin & William
 *
 */
public class Flames extends Environment{
	
	/**
	 * Image of the Flame
	 */
	private Image image;
	java.net.URL resource0;
	java.net.URL resource01;
	/**
	 * Direction of the image
	 */
	private int direction;
	/**
	 * Frame of the image
	 */
	private int imageFrame;
	
	/**
	 * @param x X position of the flame
	 * @param y Y position of the flame
	 * @param length Lenght of the flame
	 * @param height Height of the flame
	 */
	public Flames(int x, int y, int length, int height){
		super.height = height;
		super.length = length;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);
		
		resource0=getClass().getResource("/environments/images/flames10.png");
		resource01=getClass().getResource("/environments/images/flames101.png");

	}
	/**
	 * @param x X position of the flame
	 * @param y Y position of the flame
	 * @param length Lenght of the flame
	 * @param height Height of the flame
	 * @param direction of the image
	 */
	public Flames(int x, int y, int length, int height, int direction){
		super.height = height;
		super.length = length;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);
		this.direction = direction;
		
		resource0=getClass().getResource("/environments/images/flames1" + direction + ".png");
		resource01=getClass().getResource("/environments/images/flames1" + direction + "1.png");
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
		resource0=getClass().getResource("/environments/images/flames1" + direction + ".png");
		resource01=getClass().getResource("/environments/images/flames1" + direction + "1.png");
		
	}

	@Override
	public void draw(Graphics g) {
		
		g.drawImage(image,(int) (getX() * World.RATIO),(int) (getY() * World.RATIO), length * World.RATIO, height * World.RATIO, null);
		
	}

	@Override
	public void onTouch(Player player) {
		boolean applyable = true;
		for (int i = 0; i < player.getBuffList().size(); i++){
			if(player.getBuffList().get(i).getBuffType() == "burn")
				applyable = false;
		}
		if(applyable)
			player.getBuffList().add(new Burn(player.getBuffList().size()));
		
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
	@Override
	public void tick(ArrayList<Platform> platFormList) {
		if(imageFrame<=10){
			setImage(new ImageIcon(resource0).getImage());
		}
		else{
			setImage(new ImageIcon(resource01).getImage());
		}
		imageFrame++;
		if(imageFrame>20){
			imageFrame=0;
		}//chill
		
	}

}
