package lineProjectiles;

import player.Player;

/**
 * @author Martin
 * Line attack used by the mage
 */
public class Lightning extends LineProjectile{

	/**
	 * @param player Player Casting the attack
	 */
	public Lightning(Player player) {
		super("lightning","lightning1", (int)player.getFighter().getX() +2 , (int)player.getFighter().getY() +2, 5, 2,player.getFighter().getLastDirection());		
		setDmg(5);
		setRange(100);
		setxSpeed(1);
		setKnockBackSpeed(0.5);
	}		
}