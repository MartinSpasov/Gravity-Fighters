package environments;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import maps.World;
import controller.HitBox;
import player.Player;

/**
 * @author Martin & William
 * A moving electricity ball that damages anything it hits
 */
public class ElectricBall extends Environment{
	/**
	 * Damage done upon contact with a player
	 */
	private int damage;
	/**
	 * Image of the Ball
	 */
	private Image image;
	/**
	 * Frame of the image
	 */
	private int imageFrame;
	private java.net.URL resource0;
	private java.net.URL resource01;
	/**
	 * Direction of the image
	 */
	private int direction;
	/**
	 * X speed of the ball
	 */
	private double xSpeed;
	/**
	 * Y speed of the ball
	 */
	private double ySpeed;
	
	/**
	 * @param x X position of the ball
	 * @param y Y position of the ball
	 * @param length Lenght of the ball
	 * @param height Height of the ball
	 * @param xSpeed X Speed of the ball
	 * @param ySpeed Y Speed of the ball
	 */
	public ElectricBall(int x, int y, int length, int height, double xSpeed, double ySpeed){
		super.height = height;
		super.length = length;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		damage = 3;
		
		resource0=getClass().getResource("/environments/images/ball0.png");
		resource01=getClass().getResource("/environments/images/ball01.png");
		
	}
	/**
	 * @param x X position of the ball
	 * @param y Y position of the ball
	 * @param length Lenght of the ball
	 * @param height Height of the ball
	 * @param xSpeed X Speed of the ball
	 * @param ySpeed Y Speed of the ball
	 * @param direction Direction of the image
	 */
	public ElectricBall(int x, int y, int length, int height, double xSpeed, double ySpeed, int direction){
		super.height = height;
		super.length = length;
		super.x = x;
		super.y = y;
		super.hitBox = new HitBox(x, y, x + length, y + height);
		this.setDirection(direction);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		damage = 3;
		
		resource0=getClass().getResource("/environments/images/ball" + direction + ".png");
		resource01=getClass().getResource("/environments/images/ball" + direction + "1.png");
		
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
		
		resource0=getClass().getResource("/environments/images/ball" + direction + ".png");
		resource01=getClass().getResource("/environments/images/ball" + direction + "1.png");
		
	}
	@Override
	public void tick(ArrayList<Platform> platFormList) {
		setX(getX() + xSpeed);
		setY(getY() + ySpeed);
		
		if(imageFrame<=10){
			setImage(new ImageIcon(resource0).getImage());
		}
		else{
			setImage(new ImageIcon(resource01).getImage());
		}
		imageFrame++;
		if(imageFrame>20){
			imageFrame=0;
		}
			
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
		if(!player.getFighter().isIncapacitated())
			player.getFighter().getsHit(damage, getHitBox().getHitDirection(player.getFighter().getHitBox()), 0.2);
		
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
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
