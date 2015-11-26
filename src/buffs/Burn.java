package buffs;

import player.Player;

/**
 * @author Martin
 * A buff which deals damage over time to the affected player
 */
public class Burn extends Buffs {

	/**
	 * @param buffNumber the number of the buff
	 */
	public Burn(int buffNumber) {
		super("burn", buffNumber);
		
		setDuration(200); 
	}

	
	public void tick(Player player) {
		if(getCurrentDuration() >= getDuration())
			setEnded(true);
		
		else{
			if(getCurrentDuration() % 50 ==0 && player.getFighter().getCurrentHealth() != 0)
				player.getFighter().setCurrentHealth(player.getFighter().getCurrentHealth() - 1);
			setCurrentDuration(getCurrentDuration() + 1);
		}
		
	}

}
