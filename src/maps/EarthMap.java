package maps;

import java.awt.Color;
import java.awt.Dimension;

import pickups.HealthPack;
import player.Player;
import environments.Platform;
import environments.Spikes;

/**
 * @author Martin
 *Earth map
 */
public class EarthMap extends World {

	private static final long serialVersionUID = 1L;
	/**
	 * @param player1 Player 1
	 * @param player2 Player 2
	 */
	public EarthMap(Player player1, Player player2){
		super(player1, player2, "EARTH");
	
		setBackground(new Color(134,94,25)); //Sets the color for the world
		setPreferredSize(new Dimension(WIDTH * RATIO,HEIGHT * RATIO)); //Sets the size of the game screen
		
		//Creates The environement            (test)
		getPlatformList().add(new Platform(-2,-2,3,World.HEIGHT + 2));					
		getPlatformList().add(new Platform(-2,World.HEIGHT - 1,World.WIDTH + 2,3));
		getPlatformList().add(new Platform(-2,-2,World.WIDTH + 2,3));
		getPlatformList().add(new Platform(World.WIDTH - 1,-2,3,World.WIDTH + 2));
		
		getPlatformList().add(new Platform(45,70,10,2, new Color(66,63,15)));
		getPlatformList().add(new Platform(20,75,10,1, new Color(66,63,15)));
		getPlatformList().add(new Platform(70,75,10,1, new Color(66,63,15)));
		getPlatformList().add(new Platform(70,30,10,1, new Color(66,63,15)));
		getPlatformList().add(new Platform(20,30,10,1, new Color(66,63,15)));
		getPlatformList().add(new Platform(45,40,10,2, new Color(66,63,15)));
		getPlatformList().add(new Platform(48,1,4,15, new Color(66,63,15)));
		
		getPickupList().add(new HealthPack(48, 65));	
		getEnvironmentList().add(new Spikes(3, 97, 5, 2));		
		getEnvironmentList().add(new Spikes(23, 97, 5, 2));
		getEnvironmentList().add(new Spikes(43, 97, 5, 2));
		getEnvironmentList().add(new Spikes(53, 97, 5, 2));
		getEnvironmentList().add(new Spikes(73, 97, 5, 2));
		getEnvironmentList().add(new Spikes(93, 97, 5, 2));
		
	}


}
