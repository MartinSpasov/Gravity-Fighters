package ultimates;

import environments.Platform;
import player.Player;
import lineProjectiles.LineProjectile;

/**
 * @author Martin
 * The gravitron ultimate, big lazer that ignores collisions
 */
public class BigLazer extends LineProjectile{
	/**
	 * @param player Player that casts the ultimate
	 */
	public BigLazer(Player player) {
		super("lazer", (int)player.getFighter().getX() +2 , (int)player.getFighter().getY() -2 , 20, 10,player.getFighter().getLastDirection());		
		setDmg(20);
		setRange(150);
		setxSpeed(2);
		setKnockBackSpeed(2);
		
	}		
	public boolean collidesWith(Player player){
		if(getHitBox().collidesWith(player.getFighter().getHitBox())){			
			player.getFighter().getsHit(getDmg(), getHitBox().getHitDirection(player.getFighter().getHitBox()), getKnockBackSpeed());
		}	
		return false;
		
	}
	
	
	public void collidesWith(Platform platform){
//		if(getHitBox().collidesWith(platform.getHitBox())){
//			swapDirection();
//			if(getHitBox().getHitDirection(platform.getHitBox()) == "left"){
//				setX((int) (platform.getX() + platform.getLength()));
//			}
//			else{
//				setX((int) (platform.getX() - getWidth()));
//			}
//		}		
	}

}
