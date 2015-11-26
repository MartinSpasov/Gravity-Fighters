package lobProjectiles;

import player.Player;

/**
 * @author Martin
 * A lobbed explosive projectile by mage
 */
public class FireBall extends LobProjectile {

	/**
	 * @param Player who casted the fireball
	 */
	public FireBall(Player player) {
		super("fireball",(int)player.getFighter().getX()+2, (int)player.getFighter().getY()+2, 7, 7, true);
		setxSpeed(0.5);
		setySpeed(-80);
		setRadius(10);
		setDmg(10);
		setKnockBackSpeed(1.2);
	}
	/**
	 * Constructor used when fireball is used in the mage's ultimate
	 * @param X position of the fireball
	 */
	public FireBall(int x) {
		super("fireball",x, 2, 7, 7, true);
		setxSpeed(0.2);
		setySpeed(0);
		setRadius(20);
		setDmg(15);
		setKnockBackSpeed(1.2);
	}

}
