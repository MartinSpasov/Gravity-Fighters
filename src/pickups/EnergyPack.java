package pickups;

import characters.Fighter;

/**
 * @author Martin
 * An energy pack which increases the special attack bar of the player who walks on it
 */
public class EnergyPack extends Pickup{
	/**
	 * Amount of the bar it increases
	 */
	private int specRestored;
	
	/**
	 * @param x X position
	 * @param y Y position
	 */
	public EnergyPack(int x, int y){
		super("Spec", x , y);
		specRestored = 20;
	}

	@Override
	public void pickup(Fighter fighter) {	
		fighter.increaseSpec(specRestored);	
	}

}
