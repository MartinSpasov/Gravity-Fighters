package maps;

import java.awt.Dimension;

import pickups.EnergyPack;
import pickups.HealthPack;
import player.Player;
import environments.Cloud;
import environments.Platform;

/**
 * @author Martin
 * Air map
 */
public class AirMap extends World {

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @param player1 Player 1
	 * @param player2 Player 2
	 */
	public AirMap(Player player1, Player player2){
		super(player1, player2, "AIR");
		
				
		setPreferredSize(new Dimension(WIDTH * RATIO,HEIGHT * RATIO)); //Sets the size of the game screen
		
		//Creates The environement            (test)
		getPlatformList().add(new Platform(-2,-2,3,World.HEIGHT + 2));					
		getPlatformList().add(new Platform(-2,World.HEIGHT - 1,World.WIDTH + 2,3));
		getPlatformList().add(new Platform(-2,-2,World.WIDTH + 2,3));
		getPlatformList().add(new Platform(World.WIDTH - 1,-2,3,World.WIDTH + 2));
		
		getPickupList().add(new HealthPack(World.WIDTH /2 - 2, World.HEIGHT / 2 - 2));
		getPickupList().add(new EnergyPack(World.WIDTH /2 - 2, 4));
		getPlatformList().add(new Platform(35, 84 ,28,4));
		getPlatformList().add(new Platform(35, 12 ,28,4));
		getPlatformList().add(new Platform(74, 35 ,4,28));
		getPlatformList().add(new Platform(20, 35 ,4,28));
				
		getEnvironmentList().add(new Cloud(31, 31, 36, 36, 0, 0.1));
		getEnvironmentList().add(new Cloud(15, 17, 16, 16, 0.2, 0));
		getEnvironmentList().add(new Cloud(69, 67, 16, 16, -0.2, 0));
				
	}

}
