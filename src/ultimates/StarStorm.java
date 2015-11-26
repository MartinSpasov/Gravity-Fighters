package ultimates;

import buffs.Buffs;
import lobProjectiles.FireBall;
import player.Player;

/**
 * @author Martin
 * Mage ultimate that causes fireballs to fall form the sky
 */
public class StarStorm extends Buffs{
	//PK-STARSTORM
	/**
	 * @param buffNumber Number of the buff
	 */
	public StarStorm(int buffNumber) {
		super("sstorm", buffNumber);
		
		setDuration(200); 
	}

	@Override
	public void tick(Player player) {
		if(getCurrentDuration() >= getDuration()){
			setEnded(true);
			player.getFighter().setIncapacitated(false);
		}
		
		else{
			if(getCurrentDuration() == 0){
				player.getFighter().setIncapacitated(true);
			}
			if(getCurrentDuration() % 25 == 0){
				if(player.getPlayerNum() == 1){
					player.getWorld().getP1LobProjectileList().add(new FireBall((int)(Math.random() * 90)));
				}
				if(player.getPlayerNum() == 2){
					player.getWorld().getP2LobProjectileList().add(new FireBall((int)(Math.random() * 90)));
				}
			}
			player.getFighter().setIncapacitationTimer(1);
			setCurrentDuration(getCurrentDuration() + 1);
		}
		
	}

}
