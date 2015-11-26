package lobProjectiles;

import player.Player;

/**
 * @author Martin
 * Granade lobbed projectile by the gravitron that causes an explosion
 */
public class Grenade extends LobProjectile {

	/**
	 * @param player Player who casted the granade
	 */
	public Grenade(Player player) {
		super("grenade",(int)player.getFighter().getX()+2, (int)player.getFighter().getY()+2, 5, 5, true);
		setxSpeed(0.5);
		setySpeed(-80);
		setRadius(7);
		setDmg(10);
		setKnockBackSpeed(0.8);
	}

}
