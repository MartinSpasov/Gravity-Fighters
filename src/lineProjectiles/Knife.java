package lineProjectiles;

import player.Player;

/**
 * @author Martin
 * Line projectile of the Warrior
 */
public class Knife extends LineProjectile{

	/**
	 * @param player PLayer casting the attack
	 */
	public Knife(Player player) {
		super("knife", (int)player.getFighter().getX() + 3, (int)player.getFighter().getY() + 3, 5, 2,player.getFighter().getLastDirection());		
		setDmg(5);
		setRange(40);
		setxSpeed(3);
		setKnockBackSpeed(0.6);
	}		
}
