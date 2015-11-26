package characters;


import lineProjectiles.Knife;
import lobProjectiles.Axe;
import player.Player;
import sounds.Sound;
import ultimates.Rage;

/**
 * @author Martin
 *A class representing the Warrior character, setting all the values to its superclass
 */
public class Olaf extends Fighter{
			
	/**
	 * @param startSpot Position when the game starts
	 */
	public Olaf(int startSpot){
		super("OLAF", startSpot);
		setCurrentHealth(125);
		setMaxHealth(125);
		setCurrentEnergy(75);
		setMaxEnergy(75);
		setLength(10);
		setStandartSpeed(0.9);
		
		
		
	}

	@Override
	public void AttackLob(Player caster) {
		Sound.playSound("OlafLob");
		if(caster.getPlayerNum() == 1){
			caster.getWorld().getP1LobProjectileList().add(new Axe(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP1LobProjectileList().get(caster.getWorld().getP1LobProjectileList().size() - 1).swapDirection();
		}
		else if (caster.getPlayerNum() == 2){
			caster.getWorld().getP2LobProjectileList().add(new Axe(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP2LobProjectileList().get(caster.getWorld().getP2LobProjectileList().size() - 1).swapDirection();
		}
	}

	@Override
	public void AttackMelee(Fighter enemy,Player caster) {
		
		if(!caster.getFighter().isAttacking()){
			Sound.playSound("steelsword");
		}
		getHitBox().update(getX(), getY(), getX() + getLength(), getY() + getLength());
		
		if(getHitBox().collidesWith(enemy.getHitBox()) && !enemy.isIncapacitated()){	
			if(isRageing())
				enemy.getsHit(15, getHitBox().getHitDirection(enemy.getHitBox()), 0.8);	
			else{
				enemy.getsHit(10, getHitBox().getHitDirection(enemy.getHitBox()), 0.8);		
				increaseSpec(10);
			}
		}
	}
	

	@Override
	public void AttackLine(Player caster) {
		Sound.playSound("throwknife");
		if(caster.getPlayerNum() == 1){
			caster.getWorld().getP1LineProjectileList().add(new Knife(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP1LineProjectileList().get(caster.getWorld().getP1LineProjectileList().size() - 1).swapDirection();
		}
		else if(caster.getPlayerNum() == 2){
			caster.getWorld().getP2LineProjectileList().add(new Knife(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP2LineProjectileList().get(caster.getWorld().getP2LineProjectileList().size() - 1).swapDirection();
		}
		
	}


	@Override
	public void ult(Player caster) {
		Sound.playSound("Anger");
		caster.getBuffList().add(new Rage("rage", caster.getBuffList().size()));
	
		
	}	
		
	
}
