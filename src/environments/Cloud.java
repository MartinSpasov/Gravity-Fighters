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
 * A cloud is an environment object that moves around the map, it dosent do anything special other than blocking the view of things behind it
 */
public class Cloud extends Environment{
	
	/**
	 * Iamge of the cloud
	 */
	private Image image;//image of the spike
	/**
	 * Direction of the image, used for rotation
	 */
	private int direction;
	/**
	 * Speed in the x axis
	 */
	private double xSpeed;
	/**
	 * Direction in the y axis
	 */
	private double ySpeed;
	
	/**
	 * @param x X position of the cloud
	 * @param y Y position of the cloud
	 * @param length Lenght of the cloud
	 * @param height Height of the cloud
	 * @param xSpeed X Speed of the cloud
	 * @param ySpeed Y Speed of the cloud
	 */
	public Cloud(int x, int y, int length, int height, double xSpeed, double ySpeed){
		super.height = height;
		super.length = length;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		
		java.net.URL resource0=getClass().getResource("/environments/images/cloud0.png");
		
		setImage(new ImageIcon(resource0).getImage());
	}
	/**
	 * @param x X position of the cloud
	 * @param y Y position of the cloud
	 * @param length Lenght of the cloud
	 * @param height Height of the cloud
	 * @param xSpeed X Speed of the cloud
	 * @param ySpeed Y Speed of the cloud
	 * @param direction Direction of the image
	 */
	public Cloud(int x, int y, int length, int height, double xSpeed, double ySpeed, int direction){
		super.height = height;
		super.length = length;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);
		this.setDirection(direction);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		
		java.net.URL resource0=getClass().getResource("/environments/images/cloud" + direction + ".png");
		
		setImage(new ImageIcon(resource0).getImage());
	}

	@Override
	public void rotateImage(String orientation) {
		if(orientation == "L"){
			if(direction == 0)
				direction = 3;
			else
				direction--;	
			
			double temp = xSpeed;
			xSpeed = ySpeed;
			ySpeed = -temp;
		}
		if(orientation == "R"){
			if(direction == 3)
				direction = 0;
			else
				direction++;	
			
			double temp = xSpeed;
			xSpeed = -ySpeed;
			ySpeed = temp;
		}
		
		java.net.URL resource0=getClass().getResource("/environments/images/cloud" + direction + ".png");
		image = new ImageIcon(resource0).getImage();
		
	}
	@Override
	public void tick(ArrayList<Platform> platFormList) {
		setX(getX() + xSpeed);
		setY(getY() + ySpeed);
		hitBox.update(x, y, x + length, y + height);
		for(int i = 0; i < platFormList.size(); i++){
			if(hitBox.collidesWith(platFormList.get(i).getHitBox())){
				xSpeed *= -1;
				ySpeed *= -1;
			}
		}
		
	}


	@Override
	public void draw(Graphics g) {
		g.drawImage(image,(int) (getX() * World.RATIO),(int) (getY() * World.RATIO), length * World.RATIO, height * World.RATIO, null);
		
	}

	@Override
	public void onTouch(Player player) {
		//chill
		
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
	public double getxSpeed() {
		return xSpeed;
	}
	public void setxSpeed(double xSpeed) {
		this.xSpeed = xSpeed;
	}
	public double getySpeed() {
		return ySpeed;
	}
	public void setySpeed(double ySpeed) {
		this.ySpeed = ySpeed;
	}

}
