package lobProjectiles;

import player.Player;

/**
 * @author Martin
 * Lobbed blood orb casted by the vampire that causes an explosion that heals the vampire upon contact
 */
public class BloodLob extends LobProjectile {

	/**
	 * @param player Player that casted the blood orb
	 */
	public BloodLob(Player player) {
		super("blood",(int)player.getFighter().getX() +2, (int)player.getFighter().getY()+2, 3, 3, true);
		setxSpeed(0.5);
		setySpeed(-80);
		setRadius(5);
		setDmg(6);
		setKnockBackSpeed(0.6);
		setHealing(true);
		setPlayer(player);
	}

}
