package characters;


import lineProjectiles.Kunai;
import lobProjectiles.Shuriken;
import player.Player;
import sounds.Sound;
import ultimates.FanOKnife;

/**
 * @author Martin
 *A class representing the Ninja character, setting all the values to its superclass
 */
public class Ninja extends Fighter{

	/**
	 * @param startSpot Position when the game starts
	 */
	public Ninja(int startSpot){
		
		super("NINJA", startSpot);
		setCurrentHealth(100);
		setMaxHealth(100);
		setCurrentEnergy(100);
		setMaxEnergy(100);
		setLength(7);
		setStandartSpeed(1.25);
		
	}

	@Override
	public void AttackLob(Player caster) {
		Sound.playSound("Sword1");
		if(caster.getPlayerNum() == 1){
			
			caster.getWorld().getP1LobProjectileList().add(new Shuriken(caster));
			
			if(getLastDirection() == "L")
				caster.getWorld().getP1LobProjectileList().get(caster.getWorld().getP1LobProjectileList().size() - 1).swapDirection();
		}
		else if (caster.getPlayerNum() == 2){
			caster.getWorld().getP2LobProjectileList().add(new Shuriken(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP2LobProjectileList().get(caster.getWorld().getP2LobProjectileList().size() - 1).swapDirection();
		}
		
	}

	@Override
	public void AttackMelee(Fighter enemy, Player caster) {
		if(!caster.getFighter().isAttacking()){
			Sound.playSound("steelsword");
		}
		getHitBox().update(getX(), getY(), getX() + getLength(), getY() + getLength());
		
		if(getHitBox().collidesWith(enemy.getHitBox()) && !enemy.isIncapacitated()){	
			enemy.getsHit(6, getHitBox().getHitDirection(enemy.getHitBox()), 0.3);	
			increaseSpec(6);
		}
	}
	

	@Override
	public void AttackLine(Player caster) {
		Sound.playSound("throwknife");
		if(caster.getPlayerNum() == 1){
			caster.getWorld().getP1LineProjectileList().add(new Kunai(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP1LineProjectileList().get(caster.getWorld().getP1LineProjectileList().size() - 1).swapDirection();
		}
		else if(caster.getPlayerNum() == 2){
			caster.getWorld().getP2LineProjectileList().add(new Kunai(caster));
			if(getLastDirection() == "L")
				caster.getWorld().getP2LineProjectileList().get(caster.getWorld().getP2LineProjectileList().size() - 1).swapDirection();
		}
		
	}


	@Override
	public void ult(Player caster) {
		Sound.playSound("rage_of_blades");
		caster.getBuffList().add(new FanOKnife(caster.getBuffList().size()));
		
	}
	
	

}
