package lobProjectiles;

import player.Player;

/**
 * @author Martin
 * Lobbed projectile used by the ninja. 
 */
public class Shuriken extends LobProjectile {

	/**
	 * @param player Player casted the shuriken
	 */
	public Shuriken(Player player) {
		super("shuriken","shuriken1",(int)player.getFighter().getX()+2, (int)player.getFighter().getY()+2, 3, 3, false);
		setxSpeed(0.8);
		setySpeed(-60);
		setRadius(1);
		setDmg(8);
		setKnockBackSpeed(0.2);
	}

}
