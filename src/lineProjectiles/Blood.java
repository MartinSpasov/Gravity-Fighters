package lineProjectiles;

import player.Player;

/**
 * @author Martin
 * Line attack of the vampire, heals vampire if connects
 */
public class Blood extends LineProjectile{

	/**
	 * @param player PLayer casting the attack
	 */
	public Blood(Player player) {
		super("blood", (int)player.getFighter().getX() +2, (int)player.getFighter().getY() +2 , 5, 2,player.getFighter().getLastDirection());		
		setDmg(4);
		setRange(50);
		setxSpeed(2);
		setKnockBackSpeed(0.5);
		setHealing(true);
	}		
}
