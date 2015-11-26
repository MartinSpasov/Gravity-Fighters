package lineProjectiles;

import player.Player;

/**
 * @author Martin
 * Line projectile used for the ninja's line and ultimate attacks
 */
public class Kunai extends LineProjectile{

	/**
	 * @param player Player casting the attack
	 */
	public Kunai(Player player) {
		super("kunai", (int)player.getFighter().getX() + 1, (int)player.getFighter().getY() + 1, 5, 2,player.getFighter().getLastDirection());		
		setDmg(5);
		setRange(50);
		setxSpeed(2);
		setKnockBackSpeed(0.2);
	}
	/**
	 * Constructor used for the ninja ultimate
	 * @param player Player casting the attack
	 * @param xSpeed X speed of the kunai
	 * @param ySpeed Y speed of the Kunai
	 */
	public Kunai(Player player, int xSpeed, int ySpeed) {
		super("shuriken", (int)player.getFighter().getX() + 1, (int)player.getFighter().getY() + 1, 5, 5,player.getFighter().getLastDirection());		
		setDmg(10);
		setRange(150);
		setxSpeed(xSpeed);
		setySpeed(ySpeed);
		setKnockBackSpeed(0.2);
		setPoisonous(true);
		setFrame(false);
	}
	
}