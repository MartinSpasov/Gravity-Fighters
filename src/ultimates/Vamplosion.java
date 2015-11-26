package ultimates;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import player.Player;
import controller.HitBox;
import lobProjectiles.Explosion;
import maps.World;

/**
 * @author Martin
 * Ultimate of the vampire causes a explosion of blood around the vampire
 */
public class Vamplosion extends Explosion {

	/**
	 * X position of the explosion
	 */
	private double x;
	/**
	 * Y position of the explosion
	 */
	private double y;
	/**
	 * Image of the explosion
	 */
	private Image image;
	/**
	 * diameter of the explosion
	 */
	private int diameter = 1;
	/**
	 * Final diameter of the explosion
	 */
	private int finalDiameter = 60;
	/**
	 * Damage dealt when a player walks in the explosion
	 */
	private int dmg = 15;
	/**
	 * Hitbox of the explosion
	 */
	private HitBox hitbox;
	/**
	 * True when the explosion has ended
	 */
	private boolean isDead;
	/**
	 * Knockback speed a player that enters the explosion will suffer
	 */
	private double knockBackSpeed = 1;
	/**
	 * True if healing
	 */
	private boolean healing = true;
	/**
	 * Player who used the ultimate
	 */
	private Player caster;
	
	
	/**
	 * @param source Name of the explosion
	 */
	public Vamplosion (Player source){
		
		caster = source;
		setX((int)source.getFighter().getX() + 4);
		setY((int)source.getFighter().getY() + 4);
		hitbox = new HitBox(x, y, x + diameter, y + diameter);
		java.net.URL resource1=getClass().getResource("/lobProjectiles/images/bloodex.png");
		setImage(new ImageIcon(resource1).getImage());
	}
	public void update(Player p1, Player p2){
		if(caster.getPlayerNum() == 2){
			if(hitbox.collidesWith(p1.getFighter().getHitBox())){			
				p1.getFighter().getsHit(dmg, hitbox.getHitDirection(p1.getFighter().getHitBox()), knockBackSpeed);
				
				p2.getFighter().getsHit(-10, null, 0);
			}
		}
		if(caster.getPlayerNum() ==1){
			if(hitbox.collidesWith(p2.getFighter().getHitBox())){			
				p2.getFighter().getsHit(dmg, hitbox.getHitDirection(p2.getFighter().getHitBox()), knockBackSpeed);
				
				p1.getFighter().getsHit(-10, null, 0);
				
			}
		}
		if(diameter <= finalDiameter){
			x -= 0.5;
			y -= 0.5;
			diameter += 1;
			hitbox.update(x, y, x + diameter, y + diameter);
		}
		else{
			isDead = true;
		}
		
	}
	public void draw(Graphics g){
		g.drawImage(image,(int) (x * World.RATIO) ,(int)(y * World.RATIO), diameter * World.RATIO, diameter * World.RATIO, null);
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


	public void setY(int y) {
		this.y = y;
	}


	public double getX() {
		return x;
	}


	public void setX(int x) {
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
