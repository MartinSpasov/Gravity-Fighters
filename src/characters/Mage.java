package characters;

import lineProjectiles.Lightning;
import lobProjectiles.FireBall;
import player.Player;
import sounds.Sound;
import ultimates.StarStorm;

/**
 * @author Martin
 *A class representing the Mage character, setting all the values to its superclass
 */
public class Mage extends Fighter {
	/**
	 * @param startSpot Position when the game starts
	 */
	public Mage(int startSpot) {
		super("MAGE", startSpot);
		setCurrentHealth(80);
		setMaxHealth(80);
		setCurrentEnergy(100);
		setMaxEnergy(100);
		setLength(8);
		setStandartSpeed(0.9);


	}

	@Override
	public void AttackLob(Player caster) {
		Sound.playSound("FireFlyby1");
		if(caster.getPlayerNum() == 1){
			caster.getWorld().getP1LobProjectileList().add(new FireBall(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP1LobProjectileList().get(caster.getWorld().getP1LobProjectileList().size() - 1).swapDirection();
		}
		else if (caster.getPlayerNum() == 2){
			caster.getWorld().getP2LobProjectileList().add(new FireBall(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP2LobProjectileList().get(caster.getWorld().getP2LobProjectileList().size() - 1).swapDirection();
		}
	}

	@Override
	public void AttackMelee(Fighter enemy,Player caster) {
		if(!caster.getFighter().isAttacking()){
			Sound.playSound("steelsword");
		}
		getHitBox().update(getX(), getY(), getX() + getLength(),getY() + getLength());
		
		if(getHitBox().collidesWith(enemy.getHitBox()) && !enemy.isIncapacitated()){	
			enemy.getsHit(5, getHitBox().getHitDirection(enemy.getHitBox()), 0.3);
			increaseSpec(5);
		}
		
	}

	@Override
	public void AttackLine(Player caster) {
		Sound.playSound("ESPARK1");
		if(caster.getPlayerNum() == 1){
			caster.getWorld().getP1LineProjectileList().add(new Lightning(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP1LineProjectileList().get(caster.getWorld().getP1LineProjectileList().size() - 1).swapDirection();
		}
		else if(caster.getPlayerNum() == 2){
			caster.getWorld().getP2LineProjectileList().add(new Lightning(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP2LineProjectileList().get(caster.getWorld().getP2LineProjectileList().size() - 1).swapDirection();
		}

	}


	@Override
	public void ult(Player caster) {
		Sound.playSound("mw2fire");
		Sound.playSound("FireFlyby");
		caster.getBuffList().add(new StarStorm(caster.getBuffList().size()));
		
	}
}
