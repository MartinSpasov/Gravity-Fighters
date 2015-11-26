package player;

import java.awt.Image;
import java.util.ArrayList;

import buffs.Buffs;
import characters.Fighter;
import maps.World;

/**
 * @author Martin
 * Class that represents a player
 */
public class Player {
	/**
	 * The world that the players are playing in
	 */
	private World world; //World containging the palyer
	/**
	 * The current fighter the player is playing
	 */
	private Fighter fighter; //The chosen fighter of the player
	/**
	 * Standing image of the fighter
	 */
	private Image fighterImage; //Standing image of the fighter
	/**
	 * Player number, either 1 or 2
	 */
	private int playerNum;  //Player number, either 1 or 2
	/**
	 * ArrayList containing all the buffs affecting the player
	 */
	private ArrayList<Buffs> buffList = new ArrayList<Buffs>();//ArrayList containing all the buffs affecting the player
	
	/**
	 * @param playerNum Player number, either 1 or 2
	 */
	public Player(int playerNum){ //default constructor with player number
		this.playerNum = playerNum;
		
	}
	
	/**
	 * Increases the gravity of the world
	 */
	public void incGrav(){ //Increases the gravity of the world
		if (world.getGravity() < 15)
			if(fighter.getCurrentEnergy() >= 10){
				world.setGravity(world.getGravity() + 0.75);
				fighter.setCurrentEnergy(fighter.getCurrentEnergy() - 10);
			}
	}
	/**
	 * Decreases the gravity of the world
	 */
	public void decGrav(){//Decreases the gravity of the world
		if (world.getGravity() > 5)
			if(fighter.getCurrentEnergy() >= 10){
				world.setGravity(world.getGravity() - 0.75);
				fighter.setCurrentEnergy(fighter.getCurrentEnergy() - 10);
			}
	}
	
	

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Fighter getFighter() {
		return fighter;
	}

	public void setFighter(Fighter fighter) {
		this.fighter = fighter;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	
	public Image getFighterImage() {
		return fighterImage;
	}

	
public void setFighterImage(Image fighterImage) { 
		this.fighterImage = fighterImage;
	}

public ArrayList<Buffs> getBuffList() {
	return buffList;
}

public void setBuffList(ArrayList<Buffs> buffList) {
	this.buffList = buffList;
}
	

}
