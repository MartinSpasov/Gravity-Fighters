package pickups;

import characters.Fighter;

/**
 * @author Martin
 * A health pack that restores health when a player walks on it
 */
public class HealthPack extends Pickup{
	/**
	 * Amount of health restored upon pcikup
	 */
	private int healthRestored;
	
	/**
	 * @param x X position of the health pickup
	 * @param y Y position of the health pickup
	 */
	public HealthPack(int x, int y){
		super("Health", x , y);
		healthRestored = 20;
	}

	@Override
	public void pickup(Fighter fighter) {
		if(fighter.getCurrentHealth() + healthRestored <= fighter.getMaxHealth())
			fighter.setCurrentHealth(fighter.getCurrentHealth() + healthRestored);
		else
			fighter.setCurrentHealth(fighter.getMaxHealth());
	}

}
