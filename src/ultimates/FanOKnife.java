package ultimates;

import buffs.Buffs;
import lineProjectiles.Kunai;
import player.Player;

/**
 * @author Martin
 * The ninja ultimate, shoots a lot of shuikens in diffrent directions
 */
public class FanOKnife extends Buffs{
	private int number =0;
	
	/**
	 * @param buffNumber Number of the buff
	 */
	public FanOKnife(int buffNumber) {
		super("fano", buffNumber);
		
		setDuration(240); 
	}

	@Override
	public void tick(Player player) {
		if(getCurrentDuration() >= getDuration()){
			setEnded(true);
		}
		
		else{
		
			if(getCurrentDuration() % 15 == 0){
				if(player.getPlayerNum() == 1){
					switch (number){
						case 0: player.getWorld().getP1LineProjectileList().add(new Kunai(player, 2,0));
							break;
						case 1: player.getWorld().getP1LineProjectileList().add(new Kunai(player, 2,-2));
							break;
						case 2: player.getWorld().getP1LineProjectileList().add(new Kunai(player, 0,-2));
							break;
						case 3: player.getWorld().getP1LineProjectileList().add(new Kunai(player, -2,-2));
							break;
						case 4: player.getWorld().getP1LineProjectileList().add(new Kunai(player, -2,0));
							break;
						case 5: player.getWorld().getP1LineProjectileList().add(new Kunai(player, -2,2));
							break;
						case 6: player.getWorld().getP1LineProjectileList().add(new Kunai(player, 0,2));
							break;
						case 7: player.getWorld().getP1LineProjectileList().add(new Kunai(player, 2,2));
							break;
					
					}
				
				}
				if(player.getPlayerNum() == 2){
					switch (number){
					case 0: player.getWorld().getP2LineProjectileList().add(new Kunai(player, 2,0));
						break;
					case 1: player.getWorld().getP2LineProjectileList().add(new Kunai(player, 2,-2));
						break;
					case 2: player.getWorld().getP2LineProjectileList().add(new Kunai(player, 0,-2));
						break;
					case 3: player.getWorld().getP2LineProjectileList().add(new Kunai(player, -2,-2));
						break;
					case 4: player.getWorld().getP2LineProjectileList().add(new Kunai(player, -2,0));
						break;
					case 5: player.getWorld().getP2LineProjectileList().add(new Kunai(player, -2,2));
						break;
					case 6: player.getWorld().getP2LineProjectileList().add(new Kunai(player, 0,-2));
						break;
					case 7: player.getWorld().getP2LineProjectileList().add(new Kunai(player, 2,-2));
						break;
				
					}
				}
			}		
			setCurrentDuration(getCurrentDuration() + 1);
			
			number += 1;
			if(number == 8)
				number = 0;
		}
		
	}
}
