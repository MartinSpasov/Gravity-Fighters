package characters;

import lineProjectiles.Blood;
import lobProjectiles.BloodLob;
import player.Player;
import sounds.Sound;
import ultimates.Vamplosion;

/**
 * @author Martin
 *	A class representing the Vampire character, setting all the values to its superclass
 */
public class BloodLordVamp extends Fighter{
	
	/**
	 * @param startSpot position when the game starts
	 */
	public BloodLordVamp(int startSpot){
		super("BLV", startSpot);
		setCurrentHealth(100);
		setMaxHealth(100);
		setCurrentEnergy(100);
		setMaxEnergy(100);
		setLength(8);		
		setStandartSpeed(1);
		
	}

	@Override
	public void AttackLob(Player caster) {
		Sound.playSound("BLLOB");
		if(caster.getPlayerNum() == 1){
			caster.getWorld().getP1LobProjectileList().add(new BloodLob(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP1LobProjectileList().get(caster.getWorld().getP1LobProjectileList().size() - 1).swapDirection();
		}
		else if (caster.getPlayerNum() == 2){
			caster.getWorld().getP2LobProjectileList().add(new BloodLob(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP2LobProjectileList().get(caster.getWorld().getP2LobProjectileList().size() - 1).swapDirection();
		}
	}

	@Override
	public void AttackMelee(Fighter enemy, Player caster){
		if(!caster.getFighter().isAttacking()){
			Sound.playSound("steelsword");
		}
		getHitBox().update(getX(), getY(), getX() + getLength(), getY() + getLength());
		
		if(getHitBox().collidesWith(enemy.getHitBox()) && !enemy.isIncapacitated()){	
			enemy.getsHit(7, getHitBox().getHitDirection(enemy.getHitBox()), 0.5);	
			increaseSpec(7);
		}
	}		
	

	@Override
	public void AttackLine(Player caster) {
		Sound.playSound("bloodthrow");
		if(caster.getPlayerNum() == 1){
			caster.getWorld().getP1LineProjectileList().add(new Blood(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP1LineProjectileList().get(caster.getWorld().getP1LineProjectileList().size() - 1).swapDirection();
		}
		else if(caster.getPlayerNum() == 2){
			caster.getWorld().getP2LineProjectileList().add(new Blood(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP2LineProjectileList().get(caster.getWorld().getP2LineProjectileList().size() - 1).swapDirection();
		}
		
	}

	@Override
	public void ult(Player caster) {
		Sound.playSound("BLULT");
		caster.getWorld().getExplosionList().add(new Vamplosion(caster));
		setIncapacitated(true);
		setMovementSpeed(0);
		setYSpeed(0);
		
	}
}
