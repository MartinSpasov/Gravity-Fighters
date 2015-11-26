package lineProjectiles;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import maps.World;
import controller.HitBox;
import environments.Platform;
import player.Player;

/**
 * @author Martin & William
 * Abstract class representing all the line projectiles
 */
public abstract class LineProjectile {
	/**
	 * X pos of the projectile
	 */
	private int x; //x pos of the projectile
	/**
	 * Y pos of the projectile
	 */
	private int y; //y pos of the projectile
	/**
	 * Width of the projectile
	 */
	private int width; //with of the projectile
	/**
	 * Height of the projectile;
	 */
	private int height; //height of the projectile;
	/**
	 * X speed of the projectile
	 */
	private int xSpeed; // x speed of the projectile
	/**
	 * Y speed of the projecile
	 */
	private int ySpeed; // y speed of the projecile
	/**
	 * Dmg done upon colliding with a player
	 */
	private int dmg; //dmg done upon coliding with a player
	/**
	 * Player who used the attack;
	 */
	private Player player; //player who used the attack;
	/**
	 * Total  range the projectile will travel
	 */
	private int range; //total  range the projectile will travel
	/**
	 * Current range the projectile has traveled
	 */
	private int rangeTraveled; // current range the projectile has traveled
	/**
	 * Image of the projectile
	 */
	private Image image; //imgae of the projectile
	/**
	 * Hitbox of the projectile
	 */
	private HitBox hitBox; //hitbox of the projectile
	/**
	 * True once the collision has happened
	 */
	private boolean dead; //boolean one the colisaion ahs been made
	/**
	 * Current Frame of the image
	 */
	private int imageFrame;
	/**
	 * True if there is more than one frame in the projectile
	 */
	private boolean frame;
	/**
	 * The knockback speed a player colliding with the projectile will recieve
	 */
	private double knockBackSpeed;
	/**
	 * True if projectile heals the caster upon collision
	 */
	private boolean healing;
	/**
	 * True if projectile deals damage over time upon contact
	 */
	private boolean poisonous;
	
	java.net.URL resource1;
	java.net.URL resource;
	/**
	 * Last direction the projectile has been flying towards
	 */
	private String lastDirection;
	/**
	 * @param source Name of the projectile
	 * @param x X position of the projectile
	 * @param y Y position of the projectile
	 * @param width Width of the projectile
	 * @param height Height of the projectile
	 * @param lastDirection Direction towards which the projectile is been thrown
	 */
	protected LineProjectile(String source,int x, int y, int width, int height,String lastDirection){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setLastDirection(lastDirection);
		hitBox = new HitBox(x,y,x + width,y + height);
		if(lastDirection.equals("L")){
			resource1=getClass().getResource("/lineProjectiles/images/" + source + "L.png");
		}
		else{
			resource1=getClass().getResource("/lineProjectiles/images/" + source + ".png");
		}
		
		
		
	}
	/**
	 * @param source Name of the projectile
	 * @param source1 Name of the other animation the projectile might have
	 * @param x X position of the projectile
	 * @param y Y position of the projectile
	 * @param width Width of the projectile
	 * @param height Height of the projectile
	 * @param lastDirection Direction towards which the projectile is been thrown
	 */
	protected LineProjectile(String source,String source1,int x, int y, int width, int height,String lastDirection){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		hitBox = new HitBox(x,y,x + width,y + height);
		imageFrame=0;
		frame=true;
		if(lastDirection.equals("L")){
			resource1=getClass().getResource("/lineProjectiles/images/" + source + "L.png");
			resource=getClass().getResource("/lineProjectiles/images/" + source1 + "L.png");
		}
		else{
			resource1=getClass().getResource("/lineProjectiles/images/" + source + ".png");
			resource=getClass().getResource("/lineProjectiles/images/" + source1 + ".png");
		}
		
		
	}
	/**
	 * Method called every isntance of the game to move the projectile
	 */
	public void move(){
		x += xSpeed;
		y += ySpeed;
		if(xSpeed > 0)
			rangeTraveled += xSpeed;
		else
			rangeTraveled -= xSpeed;
		rangeTraveled += ySpeed;
		if(rangeTraveled >= range || rangeTraveled <= -range )
			setDead(true);
		hitBox.update(x,y,x + width,y + height);
		if(frame){
			if(imageFrame/5%2==0){
				setImage(new ImageIcon(resource).getImage());
			}
			else{
				setImage(new ImageIcon(resource1).getImage());
			}
			imageFrame++;
		}
		else{
				setImage(new ImageIcon(resource1).getImage());
			
		}
		

	}
	/**
	 * Method that draws the projectile
	 * @param g paint component
	 */
	public void draw(Graphics g){
		
		g.drawImage(image, x * World.RATIO, y * World.RATIO +10,  width * World.RATIO , + height * World.RATIO, null);
		
	}
	
	/**
	 * Method that checks if there is a collision with a player
	 * @param player Player that collision needs to be checked
	 * @return True if there is a collision with a player
	 */
	public boolean collidesWith(Player player){
		if(hitBox.collidesWith(player.getFighter().getHitBox())){			
			player.getFighter().getsHit(dmg, hitBox.getHitDirection(player.getFighter().getHitBox()), knockBackSpeed);
			setDead(true);
			if(healing){
				if (player.getPlayerNum() == 1){
					player.getWorld().getPlayer2().getFighter().getsHit(-dmg/2, null, 0);
				}
				else{
					player.getWorld().getPlayer1().getFighter().getsHit(-dmg/2, null, 0);
				}
			}
			return true;
		}
		return false;
		
	}
	
	/**
	 * Checks is the projectiel collides witha platform, if true deletes the projectile
	 * @param platform Platform to check the collision with
	 */
	public void collidesWith(Platform platform){
		if(hitBox.collidesWith(platform.getHitBox())){
			setDead(true);
		}		
	}
	/**
	 * Method that changes the direction of the projectile
	 */
	public void swapDirection(){
		xSpeed *= -1;
	}
	
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
	public int getxSpeed() {
		return xSpeed;
	}
	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	public int getDmg() {
		return dmg;
	}
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getRangeTraveled() {
		return rangeTraveled;
	}
	public void setRangeTraveled(int rangeTraveled) {
		this.rangeTraveled = rangeTraveled;
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public String getLastDirection() {
		return lastDirection;
	}
	public void setLastDirection(String lastDirection) {
		this.lastDirection = lastDirection;
	}
	public double getKnockBackSpeed() {
		return knockBackSpeed;
	}
	public void setKnockBackSpeed(double knockBackSpeed) {
		this.knockBackSpeed = knockBackSpeed;
	}
	public boolean isHealing() {
		return healing;
	}
	public void setHealing(boolean healing) {
		this.healing = healing;
	}
	public boolean isPoisonous() {
		return poisonous;
	}
	public void setPoisonous(boolean poisonous) {
		this.poisonous = poisonous;
	} 
	public void setFrame(boolean frame) {
		this.frame = frame;
	} 
	
	

}
