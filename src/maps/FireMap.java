package maps;

import java.awt.Color;
import java.awt.Dimension;

import pickups.HealthPack;
import player.Player;
import environments.Flames;
import environments.Platform;

/**
 * @author Martin
 *Fire map
 */
public class FireMap extends World {	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param player1 Player 1
	 * @param player2 Player 2
	 */
	public FireMap(Player player1, Player player2){
		super(player1, player2, "FIRE");
		
		setBackground(new Color(235,35,35)); //Sets the color for the world
		setPreferredSize(new Dimension(WIDTH * RATIO,HEIGHT * RATIO)); //Sets the size of the game screen
		
		//Creates The environement 
		getPlatformList().add(new Platform(-2,-2,3,World.HEIGHT + 2));					
		getPlatformList().add(new Platform(-2,World.HEIGHT - 1,World.WIDTH + 2,3));
		getPlatformList().add(new Platform(-2,-2,World.WIDTH + 2,3));
		getPlatformList().add(new Platform(World.WIDTH - 1,-2,3,World.WIDTH + 2));
		
		getPlatformList().add(new Platform(World.WIDTH /2 - 10,World.HEIGHT / 3,20,5));
		getPlatformList().add(new Platform(World.WIDTH /3 - 15, 2 * World.HEIGHT / 3,20,5));
		getPlatformList().add(new Platform(2 * World.WIDTH /3  -5, 2 * World.HEIGHT / 3,20,5));
		
		getPickupList().add(new HealthPack(World.WIDTH /2 - 2, World.HEIGHT / 3 + 5));
				
		getEnvironmentList().add(new Flames(World.WIDTH /2 - 10,World.HEIGHT/ 3 - 3,5,3));
		getEnvironmentList().add(new Flames(World.WIDTH /2,World.HEIGHT/ 3 - 3,5,3));
		getEnvironmentList().add(new Flames(World.WIDTH /2 - 5,World.HEIGHT/ 3 - 3,5,3));
		getEnvironmentList().add(new Flames(World.WIDTH /2 + 5,World.HEIGHT/ 3 - 3,5,3));
		
		getEnvironmentList().add(new Flames(World.WIDTH /3 - 15, 2 * World.HEIGHT/ 3 + 5,5,3,2));
		getEnvironmentList().add(new Flames(World.WIDTH /3 - 10, 2 * World.HEIGHT/ 3 + 5,5,3,2));
		getEnvironmentList().add(new Flames(World.WIDTH /3 - 5, 2 * World.HEIGHT/ 3 + 5,5,3,2));
		getEnvironmentList().add(new Flames(World.WIDTH /3, 2 * World.HEIGHT/ 3 + 5,5,3,2));
		getEnvironmentList().add(new Flames(2 * World.WIDTH /3  -5, 2 * World.HEIGHT/ 3 + 5,5,3,2));
		getEnvironmentList().add(new Flames(2 * World.WIDTH /3  +5, 2 * World.HEIGHT/ 3 + 5,5,3,2));
		
		
		getEnvironmentList().add(new Flames(2 * World.WIDTH /3, 2 * World.HEIGHT/ 3 + 5,5,3,2));
		getEnvironmentList().add(new Flames(2 * World.WIDTH /3  +10, 2 * World.HEIGHT/ 3 + 5,5,3,2));
	}

}
