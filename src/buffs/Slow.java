package buffs;

import player.Player;

/**
 * @author Martin
 * A buff which slows the movement speed of the affected player for the duration
 */
public class Slow extends Buffs{
	
	 /**
	 * Original speed of the affected player
	 */
	private double ogSpeed;
	 /**
	 * Original jump speed of the affected player
	 */
	private double ogJumpSpeed;
	
	/**
	 * @param buffNumber Number of the buff
	 */
	public Slow(int buffNumber) {
		super("slow", buffNumber);
		
		setDuration(300); 
	}

	@Override
	public void tick(Player player) {
		if(getCurrentDuration() >= getDuration()){
			setEnded(true);
			player.getFighter().setStandartSpeed(ogSpeed);
			player.getFighter().setJumpSpeed(ogJumpSpeed);
		}
		
		else{
			if(getCurrentDuration() == 0){
				ogSpeed = player.getFighter().getStandartSpeed();
				ogJumpSpeed = player.getFighter().getJumpSpeed();
				player.getFighter().setStandartSpeed(ogSpeed/2);
				player.getFighter().setJumpSpeed(ogJumpSpeed/2);
			}
				
			setCurrentDuration(getCurrentDuration() + 1);
		}
		
	}

	public double getOgSpeed() {
		return ogSpeed;
	}

	public void setOgSpeed(double ogSpeed) {
		this.ogSpeed = ogSpeed;
	}

	public double getOgJumpSpeed() {
		return ogJumpSpeed;
	}

	public void setOgJumpSpeed(double ogJumpSpeed) {
		this.ogJumpSpeed = ogJumpSpeed;
	}
	
}
