package maps;

import java.awt.Color;
import java.awt.Dimension;

import pickups.HealthPack;
import player.Player;
import environments.Platform;
import environments.Snow;

/**
 * @author Martin
 * Ice Map
 */
public class IceMap extends World {
	

	private static final long serialVersionUID = 1L;
	/**
	 * @param player1 Player 1
	 * @param player2 Player 2
	 */
	public IceMap(Player player1, Player player2){
		super(player1, player2, "ICE" );
		
		setBackground(new Color(35,235,195)); //Sets the color for the world
		setPreferredSize(new Dimension(WIDTH * RATIO,HEIGHT * RATIO)); //Sets the size of the game screen
		
		//Creates The environement            (test)
		getPlatformList().add(new Platform(-2,-2,3,World.HEIGHT + 2));					
		getPlatformList().add(new Platform(-2,World.HEIGHT - 1,World.WIDTH + 2,3));
		getPlatformList().add(new Platform(-2,-2,World.WIDTH + 2,3));
		getPlatformList().add(new Platform(World.WIDTH - 1,-2,3,World.WIDTH + 2));
		
		getPlatformList().add(new Platform(1, 46 ,37,8, new Color(222,227,249)));
		getPlatformList().add(new Platform(62, 46 ,37,8,  new Color(222,227,249)));
		
		getPlatformList().add(new Platform(42, 75 ,16,1,  new Color(222,227,249)));
		getPlatformList().add(new Platform(42, 25 ,16,1,  new Color(222,227,249)));
		
		getPickupList().add(new HealthPack(World.WIDTH /2 - 2, World.HEIGHT / 2 - 2));
		
		getEnvironmentList().add(new Snow(38, 47, 3, 6, 1));
		getEnvironmentList().add(new Snow(59, 47, 3, 6, 3));
	}


}
