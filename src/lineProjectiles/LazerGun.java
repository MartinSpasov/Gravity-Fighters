package lineProjectiles;

import player.Player;

/**
 * @author Martin
 * Gravitron line attack
 */
public class LazerGun extends LineProjectile{

	/**
	 * @param player Player casting the attack
	 */
	public LazerGun(Player player) {
		super("lazer", (int)player.getFighter().getX() +2 , (int)player.getFighter().getY() +2, 5, 2,player.getFighter().getLastDirection());		
		setDmg(5);
		setRange(50);
		setxSpeed(3);
		setKnockBackSpeed(0.5);
	}		
}
