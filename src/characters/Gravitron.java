package characters;

import lineProjectiles.LazerGun;
import lobProjectiles.Grenade;
import player.Player;
import sounds.Sound;
import ultimates.BigLazer;

/**
 * @author Martin
 *	A class representing the Gravitron character, setting all the values to its superclass
 */
public class Gravitron extends Fighter{
	
	/**
	 * @param startSpot position when the game starts
	 */
	public Gravitron(int startSpot){
		super("GVT", startSpot);
		setCurrentHealth(100);
		setMaxHealth(100);
		setCurrentEnergy(150);
		setMaxEnergy(150);
		setLength(8);
		setStandartSpeed(1);
		
		
		
	}

	@Override
	public void AttackLob(Player caster) {
		Sound.playSound("GVTLob");
		if(caster.getPlayerNum() == 1){
			caster.getWorld().getP1LobProjectileList().add(new Grenade(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP1LobProjectileList().get(caster.getWorld().getP1LobProjectileList().size() - 1).swapDirection();
		}
		else if (caster.getPlayerNum() == 2){
			caster.getWorld().getP2LobProjectileList().add(new Grenade(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP2LobProjectileList().get(caster.getWorld().getP2LobProjectileList().size() - 1).swapDirection();
		}
		
	}

	@Override
	public void AttackMelee(Fighter enemy,Player caster) {
		if(!caster.getFighter().isAttacking()){
			Sound.playSound("GVTMelee");
		}
		getHitBox().update(getX(), getY(), getX() + getLength(), getY() + getLength());
		
		if(getHitBox().collidesWith(enemy.getHitBox()) && !enemy.isIncapacitated()){	
			enemy.getsHit(7, getHitBox().getHitDirection(enemy.getHitBox()), 0.5);		
			increaseSpec(7);
		}
	}

	@Override
	public void AttackLine(Player caster) {	
		Sound.playSound("raygun");
		if(caster.getPlayerNum() == 1){
			caster.getWorld().getP1LineProjectileList().add(new LazerGun(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP1LineProjectileList().get(caster.getWorld().getP1LineProjectileList().size() - 1).swapDirection();
		}
		else if(caster.getPlayerNum() == 2){
			caster.getWorld().getP2LineProjectileList().add(new LazerGun(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP2LineProjectileList().get(caster.getWorld().getP2LineProjectileList().size() - 1).swapDirection();
		}
	}

	@Override
	public void ult(Player caster) {
		Sound.playSound("GVTULT");
		if(caster.getPlayerNum() == 1){
			caster.getWorld().getP1LineProjectileList().add(new BigLazer(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP1LineProjectileList().get(caster.getWorld().getP1LineProjectileList().size() - 1).swapDirection();
		}
		else if(caster.getPlayerNum() == 2){
			caster.getWorld().getP2LineProjectileList().add(new BigLazer(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP2LineProjectileList().get(caster.getWorld().getP2LineProjectileList().size() - 1).swapDirection();
		}
	}
		
		
		
	
}
