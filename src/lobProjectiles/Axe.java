package lobProjectiles;

import player.Player;

/**
 * @author Martin
 * Lob projectile thrown by the Warrior
 */
public class Axe extends LobProjectile {

	/**
	 * @param player Player that thrown the Axe
	 */
	public Axe(Player player) {
		super("axe","axe1","axe2","axe3",(int)player.getFighter().getX() +3, (int)player.getFighter().getY() +3, 5, 5, false);
		setxSpeed(0.8);
		setySpeed(-60);
		setRadius(1);
		setDmg(8);
		setKnockBackSpeed(0.5);
	}

}
