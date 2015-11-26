package lobProjectiles;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

import maps.World;
import controller.HitBox;
import environments.Platform;
import player.Player;

/**
 * @author Martin & William
 *
 */
public abstract class LobProjectile {
	/**
	 * X pos of the projectile
	 */
	private double x; //x pos of the projectile
	/**
	 * Y pos of the projectile
	 */
	private double y; //y pos of the projectile
	/**
	 * Width of the projectile
	 */
	private int width; //with of the projectile
	/**
	 * Height of the projectile;
	 */
	private int height; //height of the projectile;
	/**
	 *  X speed of the projectile
	 */
	private double xSpeed; // x speed of the projectile
	/**
	 * Y speed of the projecile
	 */
	private double ySpeed; // y speed of the projecile
	/**
	 * Dmg done upon coliding with a player
	 */
	private int dmg; //dmg done upon coliding with a player
	/**
	 * Radius of the explosion
	 */
	private int radius; //radius of the explosion
	/**
	 * Player who used the attack
	 */
	private Player player; //player who used the attack;
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
	 * Name of the projectile
	 */
	private String source;
	/**
	 * Knockback speed the player collliding with the projectile will experience
	 */
	private double knockBackSpeed;
	/**
	 * True if projectile will cause an explosion upon collison
	 */
	private boolean explosive;
	/**
	 * True if the projectile will heal the caster upon collision
	 */
	private boolean healing;
	/**
	 * Frame number for 2 framed projectiles
	 */
	private int spinning;
	/**
	 * Frame number for 4 framed projectiles
	 */
	private int spinning4;
	private java.net.URL resource;
	private java.net.URL resource1;
	private java.net.URL resource2;
	private java.net.URL resource3;
	
	/**
	 * @param source Name of the projectile
	 * @param x X position of the projectile
	 * @param y Y position of the projectile
	 * @param width Width of the projectile
	 * @param height Height of the projectile
	 * @param explosive True if causes an explosion upon collision
	 */
	protected LobProjectile(String source,int x, int y, int width, int height, boolean explosive){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setSource(source);
		this.explosive = explosive;
		hitBox = new HitBox(x,y,x + width,y + height);
		
		resource1=getClass().getResource("/lobProjectiles/images/" + source + ".png");
		setImage(new ImageIcon(resource1).getImage());
	}
	/**
	 * @param source Name of the projectile
	 * @param source1 Name of the particules second image
	 * @param x X position of the projectile
	 * @param y Y position of the projectile
	 * @param width Width of the projectile
	 * @param height Height of the projectile
	 * @param explosive True if causes an explosion upon collision
	 */
	protected LobProjectile(String source,String source1,int x, int y, int width, int height, boolean explosive){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setSource(source);
		this.explosive = explosive;
		hitBox = new HitBox(x,y,x + width,y + height);
		spinning=1;
		
		resource1=getClass().getResource("/lobProjectiles/images/" + source + ".png");
		resource=getClass().getResource("/lobProjectiles/images/" + source1 + ".png");
		setImage(new ImageIcon(resource1).getImage());
	}
	/**
	 * @param source Name of the projectile
	 * @param source1 Name of the particules second image
	 * @param source2 Name of the particules third image
	 * @param source3 Name of the particules forth image
	 * @param x X position of the projectile
	 * @param y Y position of the projectile
	 * @param width Width of the projectile
	 * @param height Height of the projectile
	 * @param explosive True if causes an explosion upon collision
	 */
	protected LobProjectile(String source,String source1,String source2,String source3,int x, int y, int width, int height, boolean explosive){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.setSource(source);
		this.explosive = explosive;
		hitBox = new HitBox(x,y,x + width,y + height);
		spinning4=1;
		
		resource=getClass().getResource("/lobProjectiles/images/" + source + ".png");
		resource1=getClass().getResource("/lobProjectiles/images/" + source1 + ".png");
		resource2=getClass().getResource("/lobProjectiles/images/" + source2 + ".png");
		resource3=getClass().getResource("/lobProjectiles/images/" + source3 + ".png");
		setImage(new ImageIcon(resource1).getImage());
	}
	/**
	 * Updates position of the lobbed projectile depending of the gravity
	 * @param gravity Gravity of the map
	 * @param delta Time elapsed since last game update
	 */
	public void move(double gravity, double delta){
		delta /= 1000000000.0;
		gravity*=15;
		ySpeed = ySpeed + (gravity * delta);
		
		y =  (y + (ySpeed * delta) + (0.5 * gravity * Math.pow(delta, 2)));
		x += xSpeed;		
		hitBox.update(x,y,x + width,y + height);
		if(spinning==1){
			setImage(new ImageIcon(resource1).getImage());
			spinning=2;
		}
		else if(spinning==2){
			setImage(new ImageIcon(resource).getImage());
			spinning=1;
		}
		else if(spinning4<=4 && spinning4 != 0){
			setImage(new ImageIcon(resource).getImage());
			spinning4++;
		}
		else if(spinning4 > 4 &&  spinning4 <= 8 && spinning4 != 0){
			setImage(new ImageIcon(resource1).getImage());
			spinning4++;
		}
		else if(spinning4 > 8 &&  spinning4 <= 12 && spinning4 != 0){
			setImage(new ImageIcon(resource2).getImage());
			spinning4++;
		}
		else if(spinning4 > 12 &&  spinning4 <= 16 && spinning4 != 0){
			setImage(new ImageIcon(resource3).getImage());
			spinning4++;
		}
		else if (spinning4 != 0){
			spinning4=1;
			setImage(new ImageIcon(resource1).getImage());
		}
	}
	/**
	 * Draws the Projectile
	 * @param g Paint component
	 */
	public void draw(Graphics g){
		g.drawImage(image,(int) (x * World.RATIO),(int) (y * World.RATIO),  width * World.RATIO, + height * World.RATIO, null);
	}
	/**
	 * Collision method with a Player
	 * @param player Player that collision could happen
	 * @return True if there is a collision with a player
	 */
	public boolean collidesWith(Player player){
		if(hitBox.collidesWith(player.getFighter().getHitBox())){			
			if(explosive)
				player.getWorld().getExplosionList().add(new Explosion(this));
			else
				player.getFighter().getsHit(dmg, hitBox.getHitDirection(player.getFighter().getHitBox()), knockBackSpeed);
			setDead(true);
			return true;
		}
		return false;
	}
	
	/**
	 *  Collision method with a Platform
	 * @param platform Platform with which collision might happen
	 * @param world World which cointains the platform
	 */
	public void collidesWith(Platform platform, World world){
		if(hitBox.collidesWith(platform.getHitBox())){
			setDead(true);
			if(explosive)
				world.getExplosionList().add(new Explosion(this));
		}		
	}
	/**
	 * Swaps the direction of the projectile
	 */
	public void swapDirection(){
		xSpeed *= -1;
	}
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
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public boolean isExplosive() {
		return explosive;
	}
	public void setExplosive(boolean explosive) {
		this.explosive = explosive;
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
	
	

}
