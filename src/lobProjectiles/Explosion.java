package lobProjectiles;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import maps.World;
import controller.HitBox;
import player.Player;

/**
 * @author Martin
 * Explosion object which expands and deals damage to any charachter in it
 */
public class Explosion {
	
	/**
	 * X position of the explosion
	 */
	private double x;
	/**
	 * Y position of the Explosion
	 */
	private double y;
	/**
	 * Image of the explosion
	 */
	private Image image;
	/**
	 * Current diameter of the explosion
	 */
	private int diameter;
	/**
	 * Maximum diameter the explosion can have
	 */
	private int finalDiameter;
	/**
	 * Damage done to any character that enters the explosion
	 */
	private int dmg;
	/**
	 * Hit box of the explosion
	 */
	private HitBox hitbox;
	/**
	 * True when the explosion has finished
	 */
	private boolean isDead;
	/**
	 * Knock back any character that collides with the explosion will experience
	 */
	private double knockBackSpeed;
	/**
	 * True if the explosion will heal the casting player
	 */
	private boolean healing;
	/**
	 * Player who created the explosion
	 */
	private Player caster;
	
	/**
	 * No arg constructor
	 */
	public Explosion (){
		
	}
	
	/**
	 * @param source Name of the explosion
	 */
	public Explosion (LobProjectile source){
		diameter = source.getRadius();
		finalDiameter = diameter*2;
		dmg = source.getDmg();
		knockBackSpeed = source.getKnockBackSpeed();
		healing = source.isHealing();
		caster = source.getPlayer();
		setX((int)source.getX());
		setY((int)source.getY());
		hitbox = new HitBox(x, y, x + diameter, y + diameter);
		java.net.URL resource1=getClass().getResource("/lobProjectiles/images/" + source.getSource() + "ex.png");
		setImage(new ImageIcon(resource1).getImage());
	}
	/**
	 * Method that increases the size of the projectile and checks for collisions with players
	 * @param p1 Player 1
	 * @param p2 Player 2
	 */
	public void update(Player p1, Player p2){
		if(hitbox.collidesWith(p1.getFighter().getHitBox())){			
			p1.getFighter().getsHit(dmg, hitbox.getHitDirection(p1.getFighter().getHitBox()), knockBackSpeed);
			if(healing && !p2.getFighter().isIncapacitated() && p2 == caster)
				p2.getFighter().getsHit(-1, null, 0);
		}
		if(hitbox.collidesWith(p2.getFighter().getHitBox())){			
			p2.getFighter().getsHit(dmg, hitbox.getHitDirection(p2.getFighter().getHitBox()), knockBackSpeed);
			if(healing && !p1.getFighter().isIncapacitated() && p1 == caster){
				p1.getFighter().getsHit(-1, null, 0);
			}
		}
		if(diameter <= finalDiameter){
			x -= 1;
			y -= 1;
			diameter += 2;
			hitbox.update(x, y, x + diameter, y + diameter);
		}
		else{
			isDead = true;
		}
		
	}
	/**
	 * Draws the explosion
	 * @param g Paint component
	 */
	public void draw(Graphics g){
		g.drawImage(image, (int)(x * World.RATIO) ,(int)(y * World.RATIO), diameter * World.RATIO, diameter * World.RATIO, null);
	}
	

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public int getFinalDiameter() {
		return finalDiameter;
	}

	public void setFinalDiameter(int finalDiameter) {
		this.finalDiameter = finalDiameter;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}
	public HitBox getHitbox() {
		return hitbox;
	}
	public void setHitbox(HitBox hitbox) {
		this.hitbox = hitbox;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
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
	public Player getCaster() {
		return caster;
	}
	public void setCaster(Player caster) {
		this.caster = caster;
	}
}
