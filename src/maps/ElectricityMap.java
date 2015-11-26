package maps;

import java.awt.Color;
import java.awt.Dimension;

import pickups.HealthPack;
import player.Player;
import environments.Platform;
import environments.ElectricBall;

/**
 * @author Martin
 * Electricity map
 */
public class ElectricityMap extends World {
	private static final long serialVersionUID = 1L;
	/**
	 * @param player1 Player 1
	 * @param player2 Player 2
	 */
	public ElectricityMap(Player player1, Player player2){
		super(player1, player2 , "ELECTRICITY");
		
		setBackground(new Color(150,175,0)); //Sets the color for the world
		setPreferredSize(new Dimension(WIDTH * RATIO,HEIGHT * RATIO)); //Sets the size of the game screen
		
		//Creates The environement            (test)
		getPlatformList().add(new Platform(-2,-2,3,World.HEIGHT + 2, Color.CYAN, false));					
		getPlatformList().add(new Platform(-2,World.HEIGHT - 1,World.WIDTH + 2,3, Color.CYAN, false));
		getPlatformList().add(new Platform(-2,-2,World.WIDTH + 2,3, Color.CYAN, false));
		getPlatformList().add(new Platform(World.WIDTH - 1,-2,3,World.WIDTH + 2, Color.CYAN, false));
		
		getPlatformList().add(new Platform(43,29,14,1, Color.CYAN));
		getPlatformList().add(new Platform(43,70,14,1, Color.CYAN));
		getPlatformList().add(new Platform(20,10,60,2, Color.CYAN));
		getPlatformList().add(new Platform(46, 12 ,8,5, Color.CYAN));
		getPlatformList().add(new Platform(74, 40 ,2,18, Color.CYAN));
		getPlatformList().add(new Platform(20, 40 ,2,18, Color.CYAN));
		getPlatformList().add(new Platform(46, 82 ,8,8, Color.CYAN));
		
		getPickupList().add(new HealthPack(World.WIDTH /2 - 2, World.HEIGHT / 2 - 2));
				
		getEnvironmentList().add(new ElectricBall(3, 3, 5, 5, 0.2, 0));
		getEnvironmentList().add(new ElectricBall(3, 92, 5, 5, 0, -0.2));
		getEnvironmentList().add(new ElectricBall(92, 92, 5, 5, -0.3, 0));
		getEnvironmentList().add(new ElectricBall(92, 3, 5, 5, 0, 0.3));
	}


}
