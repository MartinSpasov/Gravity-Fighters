package buffs;

import java.awt.Image;

import javax.swing.ImageIcon;

import player.Player;

/**
 * @author Martin
 *	The Abstract buff class represents an effect a player has applied to his fighter. It has a duration representing the time it will last and a currentDuration representing the time it has lasted for. When the abstract Tick method is called in Buff’s subclass, the unique effect is applied and the current duration is increased. When the current duration reaches the duration, the buff ends.
 */
public abstract class Buffs {
	/**
	 * total duration of the buff
	 */
	private int duration; //total duration of the buff
	/**
	 * current duration of the buff
	 */
	private int currentDuration; //current duration of the buff
	/**
	 * the number of the buff affecting the player, used for positioning icon
	 */
	private int buffNumber; //the number of the buff affecting the player, used for positioning icon
	/**
	 * icon of the buff
	 */
	private Image image; //icon of the buff
	/**
	 * true when duration = currentDuration
	 */
	private boolean ended; //when duration = currentduration
	/**
	 * Name of the buff
	 */
	private String buffType;
	
	/**
	 * @param source name of the buff beeing applied
	 * @param buffNumber number of the buff beeing applied
	 */
	protected Buffs(String source, int buffNumber){
		this.buffNumber = buffNumber;		
		buffType = source;
		java.net.URL resource1=getClass().getResource("/buffs/images/" + source + ".png");
		image = new ImageIcon(resource1).getImage();
	}
	
	/**
	 * method called every instance doing the buffs effect and updating the duration
	 * @param player Player affected by the buff
	 */
	public abstract void tick(Player player); //metho called every instance ging the buufs effect and updating the duration

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getCurrentDuration() {
		return currentDuration;
	}

	public void setCurrentDuration(int currentDuration) {
		this.currentDuration = currentDuration;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getBuffNumber() {
		return buffNumber;
	}

	public void setBuffNumber(int buffNumber) {
		this.buffNumber = buffNumber;
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public String getBuffType() {
		return buffType;
	}

	public void setBuffType(String buffType) {
		this.buffType = buffType;
	}
}
