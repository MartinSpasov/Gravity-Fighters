package ultimates;

import buffs.Buffs;
import player.Player;

/**
 * @author Martin
 * Warrior ultimate that makes him ignore knockbacks and move faster for a few seconds
 */
public class Rage extends Buffs{
  	/**
  	 * Original movement speed
  	 */
  	private double ogSpeed;
	/**
	 * Original jump speed
	 */
	private double ogJumpSpeed;
	
	/**
	 * @param source Name of the buff
	 * @param buffNumber The number of the buff
	 */
	public Rage(String source, int buffNumber) {
		super(source, buffNumber);
		setDuration(600); 
	}

	@Override
	public void tick(Player player) {
		if(getCurrentDuration() >= getDuration()){
			setEnded(true);
			player.getFighter().setRageing(false);
			player.getFighter().setStandartSpeed(ogSpeed);
			player.getFighter().setJumpSpeed(ogJumpSpeed);
		}
		
		else{
			if(getCurrentDuration() == 0){
				player.getFighter().setRageing(true);
				ogSpeed = player.getFighter().getStandartSpeed();
				ogJumpSpeed = player.getFighter().getJumpSpeed();
				player.getFighter().setStandartSpeed(ogSpeed*1.15);
				player.getFighter().setJumpSpeed(ogJumpSpeed*1.15);
			}
				
			setCurrentDuration(getCurrentDuration() + 1);
		}
		
	}

	public double getOgJumpSpeed() {
		return ogJumpSpeed;
	}

	public void setOgJumpSpeed(double ogJumpSpeed) {
		this.ogJumpSpeed = ogJumpSpeed;
	}

	public double getOgSpeed() {
		return ogSpeed;
	}

	public void setOgSpeed(double ogSpeed) {
		this.ogSpeed = ogSpeed;
	}
		
	

}
