package characters;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import player.Player;
import sounds.Sound;
import controller.HitBox;
import environments.Platform;
import maps.World;


/**
 * @author Martin & William
 *The abstract class Fighter is a large class which defines many of the properties that the fighters that the players are playing have. It defines their position, their speed, their health and energy. The move, dash and jump methods are for positioning and are the same for every subclass of fighter, therefore are defined in fighter. The Attacks methods are unique to the every fighter therefore are abstract and will be defined in the subclasses. 
 */
public abstract class Fighter {
	
	/**
	 * Alpha composite used for the flashing when someone gets hit
	 */
	private AlphaComposite a =AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
	/**
	 *  Font used for drawing the game over when a fighter dies
	 */
	private Font font = new Font("Comic", 70, 70);
	/**
	 * X position of the character
	 */
	private double x;
	/**
	 * Y position of the character
	 */
	private double y;
	/**
	 * Current x axis speed of the character
	 */
	private double movementSpeed;
	/**
	 * Normal movement speed of the character when unaffected by anything
	 */
	private double standartSpeed;
	/**
	 * A temporary value for movement speed when affected by a movement speed modifier
	 */
	private double temporarySpeed;
	/**
	 * Current y axis position of the character
	 */
	private double ySpeed;
	/**
	 * Y speed modifier when jumping
	 */
	private double jumpSpeed;
	/**
	 * Max health the character can have and starts with
	 */
	private int maxHealth;
	/**
	 *  The current amount of health the character has
	 */
	private int currentHealth;
	/**
	 * Max energy the character can have and starts with
	 */
	private int maxEnergy;
	/**
	 * The current amount of energy the character has
	 */
	private int currentEnergy;
	/**
	 * The current amount of special bar the character has
	 */
	private int currentSpec;
	/**
	 * a hash map containing all the images of the selected character
	 */
	private HashMap<String, Image> map = new HashMap<String, Image>();
	/**
	 * width/height of the character
	 */
	private int length;
	/**
	 * Hitbox of the character
	 */
	private HitBox hitBox;
	/**
	 * True when the character is flashing, therefore unable to move or get hit
	 */
	private boolean incapacitated;
	/**
	 * Used with he alpha composite to determine if the character is flashing
	 */
	private float incapacitatedInt;
	/**
	 * how long the fighter is incapacitated
	 */
	private int incapacitationTimer;
	/**
	 * Cooldown for the melee attacks, making it so that players cant spam melee attacks
	 */
	private int attackingTimer;
	/**
	 * Cooldown for the range attacks, making it so that players cant spam range attacks
	 */
	private int projectileTimer;
	/**
	 * True when player is increasing gravity
	 */
	private boolean increasingGrav;
	/**
	 * True when player is decreasing gravity
	 */
	private boolean decreasingGrav;
	/**
	 * Cooldown for changeing gravity, so you cant spam it
	 */
	private int gravTimer;
	/**
	 * True when currentHealth = 0
	 */
	private boolean dead;
	/**
	 * Used only for Warrior ultimate ignores knockback when true
	 */
	private boolean rageing;
	/**
	 * Text written when dead
	 */
	private String imDead;

	
	/**
	 * String either L or R depending the last direction the fighter has faced
	 */
	private String lastDirection;
	/**
	 * True when player is moving left
	 */
	private boolean L; //movement control variables
	/**
	 * True when player is moving right
	 */
	private boolean R;
	/**
	 * True when player is jumping
	 */
	private boolean J;

	/**
	 * True when player is in the air
	 */
	private boolean AB;
	/**
	 * True while an attacking key is pressed
	 */
	private boolean AttackPressed;
	/**
	 * Amount of jumps the player has performed, resets when he touches the ground
	 */
	private int jump;
	/**
	 * Amount of tiems the player has increased his fall speed
	 */
	private int down;
	/**
	 * String used for fetching images in the hashmap
	 */
	private String IML;
	/**
	 * String used for fetching images in the hashmap
	 */
	private String IMR;
	private boolean T;
	/**
	 * Tue while player is doing a meele attacking
	 */
	private boolean Attacking;
	/**
	 * True when player is performing a range attack
	 */
	private boolean Lobbing;
	/**
	 * True when player is getting knocked back towards the left
	 */
	private boolean knockedBackLeft;
	/**
	 * True when player is knocked back to the right
	 */
	private boolean knockedBackRight;
	
 	/**
 	 * @param source Name of the fighter, used to fetch images
 	 * @param startSpot Starting location for the plaeyr
 	 */
 	public Fighter(String source, int startSpot){
		setAttackingTimer(30);
		setGravTimer(10);
		setLastDirection("L");
		setJumpSpeed(-50);
		setIML("Stand");
		setIMR("Stand");
		if (startSpot == 1){
			x = 25;
			y = 25;
		}
		if (startSpot == 2){
			x = 75;
			y = 25;
		}
		hitBox = new HitBox(x, y, x + length, y + length);
		
		
		java.net.URL resource1=getClass().getResource("/characters/images/" + source + ".png");
		java.net.URL resourceL=getClass().getResource("/characters/images/" + source + "L.png");
		java.net.URL resourceL1=getClass().getResource("/characters/images/" + source + "L1.png");
		java.net.URL resourceL2=getClass().getResource("/characters/images/" + source + "L2.png");
		java.net.URL resourceTL=getClass().getResource("/characters/images/" + source + "TL.png");
		java.net.URL resourceTL1=getClass().getResource("/characters/images/" + source + "TL1.png");
		java.net.URL resourceTL2=getClass().getResource("/characters/images/" + source + "TL2.png");
		java.net.URL resourceR=getClass().getResource("/characters/images/" + source + "R.png");
		java.net.URL resourceR1=getClass().getResource("/characters/images/" + source + "R1.png");
		java.net.URL resourceR2=getClass().getResource("/characters/images/" + source + "R2.png");
		java.net.URL resourceTR=getClass().getResource("/characters/images/" + source + "TR.png");
		java.net.URL resourceTR1=getClass().getResource("/characters/images/" + source + "TR1.png");
		java.net.URL resourceTR2=getClass().getResource("/characters/images/" + source + "TR2.png");
		java.net.URL resourceLA=getClass().getResource("/characters/images/" + source + "LA.png");
		java.net.URL resourceRA=getClass().getResource("/characters/images/" + source + "RA.png");
		java.net.URL resourceLA1=getClass().getResource("/characters/images/" + source + "LA1.png");
		java.net.URL resourceRA1=getClass().getResource("/characters/images/" + source + "RA1.png");
		java.net.URL resourceD=getClass().getResource("/characters/images/" + source + "D.png");
		java.net.URL resourceD1=getClass().getResource("/characters/images/" + source + "D1.png");
		java.net.URL resourceD2=getClass().getResource("/characters/images/" + source + "D2.png");
		java.net.URL resourceD3=getClass().getResource("/characters/images/" + source + "D3.png");
		java.net.URL resourceD4=getClass().getResource("/characters/images/" + source + "D4.png");
		java.net.URL resourceLAL=getClass().getResource("/characters/images/" + source + "LALOB.png");
		java.net.URL resourceRAL=getClass().getResource("/characters/images/" + source + "RALOB.png");
		java.net.URL resourceIG=getClass().getResource("/characters/images/gravInc.png");
		java.net.URL resourceDG=getClass().getResource("/characters/images/gravDec.png");
		
		map.put("Stand", new ImageIcon(resource1).getImage());
		map.put("Left", new ImageIcon(resourceL).getImage());
		map.put("Left1", new ImageIcon(resourceL1).getImage());
		map.put("Left2", new ImageIcon(resourceL2).getImage());
		map.put("Left3", new ImageIcon(resourceL1).getImage());
		map.put("Right", new ImageIcon(resourceR).getImage());
		map.put("Right1", new ImageIcon(resourceR1).getImage());
		map.put("Right2", new ImageIcon(resourceR2).getImage());
		map.put("Right3", new ImageIcon(resourceR1).getImage());
		map.put("TLeft", new ImageIcon(resourceL).getImage());
		map.put("TLeft1", new ImageIcon(resourceTL).getImage());
		map.put("TLeft2", new ImageIcon(resourceTL1).getImage());
		map.put("TLeft3", new ImageIcon(resourceTL2).getImage());
		map.put("TRight", new ImageIcon(resourceR).getImage());
		map.put("TRight1", new ImageIcon(resourceTR).getImage());
		map.put("TRight2", new ImageIcon(resourceTR1).getImage());
		map.put("TRight3", new ImageIcon(resourceTR2).getImage());
		map.put("LeftA", new ImageIcon(resourceLA).getImage());
		map.put("RightA", new ImageIcon(resourceRA).getImage());
		map.put("LeftA1", new ImageIcon(resourceLA1).getImage());
		map.put("RightA1", new ImageIcon(resourceRA1).getImage());
		map.put("Death", new ImageIcon(resourceD).getImage());
		map.put("Death1", new ImageIcon(resourceD1).getImage());
		map.put("Death2", new ImageIcon(resourceD2).getImage());
		map.put("Death3", new ImageIcon(resourceD3).getImage());
		map.put("Death4", new ImageIcon(resourceD4).getImage());
		map.put("GravInc", new ImageIcon(resourceIG).getImage());
		map.put("GravDec", new ImageIcon(resourceDG).getImage());
		map.put("LeftAL", new ImageIcon(resourceLAL).getImage());
		map.put("RightAL", new ImageIcon(resourceRAL).getImage());
		map.put("LeftAL1", new ImageIcon(resourceLAL).getImage());
		map.put("RightAL1", new ImageIcon(resourceRAL).getImage());
	}
	
	
	
	/**
	 * Method called every instance to draw the character.
	 * @param g Graphics component from JPanel 
	 */
	public void draw(Graphics g){
		if(getIncapacitationTimer()%2!=0){
			setIncapacitatedInt(0.3f);
		}
		else{
			setIncapacitatedInt(1);
		}
		Graphics2D g2=(Graphics2D)g;
		g2.setComposite(a);
		a=a.derive(incapacitatedInt);
		
		if(dead){
			g.drawImage(getMap().get(getImDead()), (int)(getX() * World.RATIO) , (int)(getY() * World.RATIO) , (int)(getLength() * World.RATIO) , (int)(getLength() * World.RATIO) , null);
			g.setFont(font);
			g.drawString("GAME OVER", 100, 100);
		}
		else if(isL()){
			g.drawImage(getMap().get(getIML()), (int)(getX() * World.RATIO) , (int)(getY() * World.RATIO) , (int)(getLength() * World.RATIO) , (int)(getLength() * World.RATIO) , null);
			
		}
		else if(isR()){
			g.drawImage(getMap().get(getIMR()), (int)(getX() * World.RATIO) , (int)(getY() * World.RATIO) , (int)(getLength() * World.RATIO) , (int)(getLength() * World.RATIO) , null);
		}
		else if((isAttacking() || isLobbing()) && (getLastDirection().equals("R2") || getLastDirection().equals("R1") || getLastDirection().equals("R3"))){
			g.drawImage(getMap().get(getIMR()), (int)(getX() * World.RATIO) , (int)(getY() * World.RATIO) , (int)(getLength() * World.RATIO) , (int)(getLength() * World.RATIO) , null);
			if(getLastDirection().equals("R3")){
				setLastDirection("R");
				setAttacking(false);
				setLobbing(false);
				setAttackingTimer(0);
			}
		}
		else if((isAttacking() || isLobbing()) && (getLastDirection().equals("L2") || getLastDirection().equals("L3") || getLastDirection().equals("L1"))){
			g.drawImage(getMap().get(getIML()), (int)(getX() * World.RATIO) , (int)(getY() * World.RATIO) , (int)(getLength() * World.RATIO) , (int)(getLength() * World.RATIO) , null);
			if(getLastDirection().equals("L3")){
				setLastDirection("L");
				setLobbing(false);
				setAttacking(false);
				setAttackingTimer(0);
			}
		}
		
		else{
			g.drawImage(getMap().get("Stand"), (int)(getX() * World.RATIO) , (int)(getY() * World.RATIO) , (int)(getLength() * World.RATIO) , (int)(getLength() * World.RATIO) , null);
		}
		if(decreasingGrav){
			g.drawImage(getMap().get("GravDec"), (int)(getX() * World.RATIO)-10 , (int)(getY() * World.RATIO)-10 , (int)(3 * World.RATIO) , (int)(3 * World.RATIO) , null);
			
		}
		if(increasingGrav){
			g.drawImage(getMap().get("GravInc"), (int)(getX() * World.RATIO)-10 , (int)(getY() * World.RATIO)-10 , (int)(3 * World.RATIO) , (int)(3 * World.RATIO) , null);
			
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
	}
	
	/**
	 * Method that sets the image of the player depending on the IMR & IML
	 */
	public void setCurrentImage(){
				
		if(isL()){
			if(AB){
				setIML("Left1");
			}
			
			else if((getIML()=="Stand" || getIML()=="Left")){
				setIML("Left1");
				//System.out.println("moving");
			}
			else if(getIML()=="Left1"){
				setIML("Left2");
			//	System.out.println("moving2");
			}
			else if(getIML()=="Left2"){
				setIML("Left3");
			//	System.out.println("moving3");
			}
			else if(getIML()=="Left3"){
				setIML("Left");
			//	System.out.println("moving4");
			}
			
			else if(getIML()=="TLeft"){
				setIML("TLeft1");
			}
			else if(getIML()=="TLeft1"){
				setIML("TLeft2");
			}
			else if(getIML()=="TLeft2"){
				setIML("TLeft3");
			}
			else if(getIML()=="TLeft3"){
				setIML("Left");
				setT(false);
			}
			
			else if(getIML()=="LeftA"){
				setIML("LeftA1");
			}
			else if(getIML()=="LeftA1"){
				setIML("Left");
				setAttacking(false);
			}

		}
		else if(isR()){
			if(AB){
				setIMR("Right1");
			}
			
			else if((getIMR()=="Stand" || getIMR()=="Right")){
				setIMR("Right1");
			}
			else if(getIMR()=="Right1"){
				setIMR("Right2");
			}
			else if(getIMR()=="Right2"){
				setIMR("Right3");
			}
			else if(getIMR()=="Right3"){
				setIMR("Right");
			}
			
			else if(getIMR()=="TRight"){
				setIMR("TRight1");
			}
			else if(getIMR()=="TRight1"){
				setIMR("TRight2");
			}
			else if(getIMR()=="TRight2"){
				setIMR("TRight3");
			}
			else if(getIMR()=="TRight3"){
				setIMR("Right");
				setT(false);
			}
			
			else if(getIMR()=="RightA"){
				setIMR("RightA1");
			}
			else if(getIMR()=="RightA1"){
				setIMR("Right");
				setAttacking(false);
			}
		}
		else if(!isR() && !isL() && isAttacking() && getLastDirection().equals("R1")){
			setIMR("RightA1");
			setLastDirection("R2");
		}else if(!isR() && !isL() && isAttacking() && getLastDirection().equals("R2")){
			setIMR("RightA1");
			setLastDirection("R3");
		}
		else if(!isR() && !isL() && isAttacking() && getLastDirection().equals("R")){
			setIMR("RightA");
			setLastDirection("R1");
		}
		else if(!isR() && !isL() && isAttacking() && getLastDirection().equals("L1")){
			setIML("LeftA1");
			setLastDirection("L2");
		}
		else if(!isR() && !isL() && isAttacking() && getLastDirection().equals("L2")){
			setIML("LeftA1");
			setLastDirection("L3");
		}
		else if(!isR() && !isL() && isAttacking() && getLastDirection().equals("L")){
			setIML("LeftA");
			setLastDirection("L1");
		}
		//
		else if(!isR() && !isL() && isLobbing() && getLastDirection().equals("R1")){
			setIMR("RightAL1");
			setLastDirection("R2");
		}else if(!isR() && !isL() && isLobbing() && getLastDirection().equals("R2")){
			setIMR("RightAL1");
			setLastDirection("R3");
		}
		else if(!isR() && !isL() && isLobbing() && getLastDirection().equals("R")){
			setIMR("RightAL");
			setLastDirection("R1");
		}
		else if(!isR() && !isL() && isLobbing() && getLastDirection().equals("L1")){
			setIML("LeftAL1");
			setLastDirection("L2");
		}
		else if(!isR() && !isL() && isLobbing() && getLastDirection().equals("L2")){
			setIML("LeftAL1");
			setLastDirection("L3");
		}
		else if(!isR() && !isL() && isLobbing() && getLastDirection().equals("L")){
			setIML("LeftAL");
			setLastDirection("L1");
		}
		//
		else{
			setIMR("Stand");
			setIML("Stand");
		}
		if(dead){
			if(getImDead()==null){
				setImDead("Death");
			}
			else if(getImDead()=="Death"){
				setImDead("Death1");
			}
			else if(getImDead()=="Death1"){
				setImDead("Death2");
			}
			else if(getImDead()=="Death2"){
				setImDead("Death3");
			}
			else if(getImDead()=="Death3"){
				setImDead("Death4");
			}
		}
		
		
	}
		


	public HashMap<String, Image> getMap() {
		return map;
	}


	/**
	 * Method called every instance of the game that affects the y positioning
	 * @param platformList List of the platfarms in the map
	 * @param gravity Current gravity of the map
	 * @param delta Time elapsed during that instance of the game
	 */
	public void falling(ArrayList<Platform> platformList, double gravity, double delta){
		gravity *= 2;
		delta /= 1000000000.0;
		ySpeed = ySpeed + (gravity* delta);
		if(ySpeed > 100)
			ySpeed = 100;
		
		
		for(int i = 0; i < platformList.size(); i++){
			
			if(!J){
				if(hitBox.collidesWith(platformList.get(i).getHitBox())){				
					if(platformList.get(i).getHitBox().getTopLeftY() - hitBox.getBottomRightY() >= -2){				
							AB = false;
							ySpeed = 0;
							jump = 0;
							down=0;	
							movementSpeed = 0;
							y=platformList.get(i).getHitBox().getTopLeftY()-length;
							hitBox.update(x, y, x + length, y + length);
							return;								
					}
					if(platformList.get(i).getHitBox().getBottomRightY() - hitBox.getTopLeftY() <= 1 && platformList.get(i).getHeight() != 1 && ySpeed <=0){
						ySpeed = 1;
						
						
					}
				}
			}
			else{
				jump();
			}			
		}
		y = y + (ySpeed * delta) + (0.5 * gravity * Math.pow(delta, 2));
		J = false;
		
		hitBox.update(x, y, x + length, y + length);
			
	}
	/**
	 * Method called every instance of the game affecting the x axis positioning
	 * @param platformList List of all the platforms in the map
	 */
	public void movement(ArrayList<Platform> platformList){
		if(incapacitated){
			if (incapacitationTimer == 60){
				incapacitated = false;
				knockedBackLeft = false;
				knockedBackRight = false;
				incapacitationTimer =0;
				temporarySpeed = 0;				
			}
			else
				incapacitationTimer += 1;
		}
		
		
		if(attackingTimer<30){
			attackingTimer+=1;
		}
		
		if(projectileTimer<45){
			projectileTimer+=1;
		}
		
		
		if(gravTimer==10){
			setDecreasingGrav(false);
			setIncreasingGrav(false);
		}
		else{
			gravTimer++;
		}
		if(knockedBackLeft)
			movementSpeed = -temporarySpeed;
		if(knockedBackRight)
			movementSpeed = temporarySpeed;
		
		if(movementSpeed > 0 ){	
			for(int i = 0; i < platformList.size(); i++){  //Following loops check if the fighter is right next to a platform on the left. if he stops	
				if(hitBox.collidesWith(platformList.get(i).getHitBox()) && (hitBox.getBottomRightX() - platformList.get(i).getHitBox().getTopLeftX() >= 0) && (hitBox.getBottomRightX() - platformList.get(i).getHitBox().getTopLeftX() <= 2)){	
					if(hitBox.getBottomRightY() != platformList.get(i).getHitBox().getTopLeftY()){
						if(platformList.get(i).getHeight()!=1){
							if(knockedBackLeft || knockedBackRight)
								temporarySpeed *=-1;
							movementSpeed=0;
						}
					}				
				}			
			}	
		}
		if(movementSpeed < 0 ){	
			for(int i = 0; i < platformList.size(); i++){ //Following loops check if the fighter is right next to a platform on the right. if he stops
				if(hitBox.collidesWith(platformList.get(i).getHitBox()) && (hitBox.getTopLeftX() - platformList.get(i).getHitBox().getBottomRightX() <= 0) && (hitBox.getTopLeftX() - platformList.get(i).getHitBox().getBottomRightX() >= -2 )){					
					if(hitBox.getBottomRightY() != platformList.get(i).getHitBox().getTopLeftY()){
						if(platformList.get(i).getHeight()!=1){
							if(knockedBackLeft || knockedBackRight)
								temporarySpeed *=-1;
							movementSpeed=0;
						}
					}
				}
			}
		}
		x += movementSpeed;
		hitBox.update(x, y, x + length, y + length);
		
	}
	
	
	/**
	 * Method called every instance to check if the character has exited the boundries of the map for some reason, and puts him back in if he did
	 */
	public void focus(){
		if (x < 0 )
			x = 2; 
		if(x > 100)
			x = 98;
		if(y < 0)
			y = 2;
		if(y > 100)
			y = 98;
		
	 
	}
	/**
	 * Method that makes the character jump upon key input
	 */
	public void jump(){
		setJump(getJump()+1);
		
		if(getJump()<=2){
			Sound.playSound("jump");
			AB = true;
			y -= 0.10;
			hitBox.update(x, y, x + length, y + length);
			setYSpeed(jumpSpeed);
		}
		
		
	}
	/**
	 * Method that makes the character fall faster upon key input
	 */
	public void down(){
		setDown(getDown()+1);
			
		if(getDown()<=3){
			y += 0.10;
			hitBox.update(x, y, x + length, y + length);
			setYSpeed(getYSpeed()-jumpSpeed);
		}
		
		
	}
	
	/**
	 * Mehtod that is called whenever the character gets hit by something
	 * @param damage amount of health he will lose
	 * @param direction direction from which he got hit
	 * @param knockSpeed the speed at which he will get knocked back
	 */
	public void getsHit(int damage, String direction, double knockSpeed){
		
		if(!incapacitated){			
				System.out.println("nigaga");		
			currentHealth -= damage;
			increaseSpec((int)Math.ceil(damage /2.0));
			
			if(currentHealth > maxHealth)
				currentHealth = maxHealth;
			if(currentHealth < 0)
				currentHealth = 0;
			
			if (direction == "left"){				
				incapacitated = true;
				if(!rageing){
					
					J = true;
					knockedBackLeft = true;
					temporarySpeed = knockSpeed;
				}
			}
			if(direction == "right"){
				incapacitated = true;
				if(!rageing){
					
					J = true;
					knockedBackRight = true;
					temporarySpeed = knockSpeed;
				}
			}
		}
	}
	/**
	 * Methoid which is called to increase the amount of special bar
	 * @param amount
	 */
	public void increaseSpec(int amount){
		currentSpec += amount;
		if (currentSpec > 100){
			currentSpec = 100;
		}
	}
	
	/**
	 * Abstract method to make the character performs his lobbing attack upon key input
	 * @param caster Player who performed the attack
	 */
	public abstract void AttackLob(Player caster);
		
	/**
	 * Abstract method to make the character performs his melee attack upon key input
	 * @param Enemy enemy player
	 * @param Caster attacking player
	 */
	public abstract void AttackMelee(Fighter Enemy, Player Caster);
	
	/**
	 * Abstract method to make the character performs his line attack upon key input
	 * @param caster Player who performed the attack
	 */
	public abstract void AttackLine(Player caster);
	
	/**
	 * Abstract method to make the character performs his ultimate attack upon key input
	 * @param caster Player who performed the attack
	 */
	public abstract void ult(Player caster);
	
	
	

	public void setMap(HashMap<String, Image> map) {
		this.map = map;
	}



	public double getJumpSpeed() {
		return jumpSpeed;
	}



	public void setJumpSpeed(double jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}



	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public double getX() {
		return x;
	}
	public void setX(double pox) {
		this.x = pox;
	}
	public double getY() {
		return y;
	}
	public void setY(double poy) {
		this.y = poy;
	}
	public double getMovementSpeed() {
		return movementSpeed;
	}
	public void setMovementSpeed(double spdx) {
		this.movementSpeed = spdx;
	}
	public double getYSpeed() {
		return ySpeed;
	}
	public void setYSpeed(double d) {
		this.ySpeed = d;
	}
	public boolean isL() {
		return L;
	}
	public void setL(boolean l) {
		L = l;
	}
	public boolean isR() {
		return R;
	}
	public void setR(boolean r) {
		R = r;
	}
	public boolean isJ() {
		return J;
	}
	public void setJ(boolean j) {
		J = j;
	}
	public boolean isAB() {
		return AB;
	}
	public void setAB(boolean aB) {
		AB = aB;
	}
	public int getJump() {
		return jump;
	}
	public void setJump(int jump) {
		this.jump = jump;
	}
	public String getIML() {
		return IML;
	}
	public void setIML(String iML) {
		IML = iML;
	}
	public String getIMR() {
		return IMR;
	}
	public void setIMR(String iMR) {
		IMR = iMR;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	public int getMaxEnergy() {
		return maxEnergy;
	}
	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}
	public int getCurrentEnergy() {
		return currentEnergy;
	}
	public void setCurrentEnergy(int currentEnergy) {
		this.currentEnergy = currentEnergy;
	}
	
	public int getCurrentSpec() {
		return currentSpec;
	}
	public void setCurrentSpec(int currentShield) {
		this.currentSpec = currentShield;
	}


	public HitBox getHitBox() {
		return hitBox;
	}



	public void setHitBox(HitBox hitBox) {
		this.hitBox = hitBox;
	}



	public boolean isAttackPressed() {
		return AttackPressed;
	}



	public void setAttackPressed(boolean attackPressed) {
		AttackPressed = attackPressed;
	}



	public boolean isT() {
		return T;
	}



	public void setT(boolean t) {
		T = t;
	}



	public boolean isAttacking() {
		return Attacking;
	}



	public void setAttacking(boolean attacking) {
		Attacking = attacking;
	}



	public boolean isIncapacitated() {
		return incapacitated;
	}



	public void setIncapacitated(boolean incapacitated) {
		this.incapacitated = incapacitated;
	}



	public int getIncapacitationTimer() {
		return incapacitationTimer;
	}



	public void setIncapacitationTimer(int incapacitationTimer) {
		this.incapacitationTimer = incapacitationTimer;
	}



	public String getLastDirection() {
		return lastDirection;
	}



	public void setLastDirection(String lastDirection) {
		this.lastDirection = lastDirection;
	}



	public float getIncapacitatedInt() {
		return incapacitatedInt;
	}



	public void setIncapacitatedInt(float incapacitatedInt) {
		this.incapacitatedInt = incapacitatedInt;
	}



	public int getAttackingTimer() {
		return attackingTimer;
	}



	public void setAttackingTimer(int attackingTimer) {
		this.attackingTimer = attackingTimer;
	}



	public boolean isIncreasingGrav() {
		return increasingGrav;
	}



	public void setIncreasingGrav(boolean increasingGrav) {
		this.increasingGrav = increasingGrav;
	}
	
	public boolean isDecreasingGrav() {
		return decreasingGrav;
	}



	public void setDecreasingGrav(boolean decreasingGrav) {
		this.decreasingGrav = decreasingGrav;
	}



	public int getGravTimer() {
		return gravTimer;
	}



	public void setGravTimer(int gravTimer) {
		this.gravTimer = gravTimer;
	}



	public boolean isDead() {
		return dead;
	}



	public void setDead(boolean dead) {
		this.dead = dead;
	}



	public String getImDead() {
		return imDead;
	}



	public void setImDead(String imDead) {
		this.imDead = imDead;
	}



	public int getDown() {
		return down;
	}



	public void setDown(int down) {
		this.down = down;
	}



	public boolean isKnockedBackLeft() {
		return knockedBackLeft;
	}



	public void setKnockedBackLeft(boolean knockedBackLeft) {
		this.knockedBackLeft = knockedBackLeft;
	}



	public boolean isKnockedBackRight() {
		return knockedBackRight;
	}



	public void setKnockedBackRight(boolean knockedBackRight) {
		this.knockedBackRight = knockedBackRight;
	}



	public int getProjectileTimer() {
		return projectileTimer;
	}



	public void setProjectileTimer(int projectileTimer) {
		this.projectileTimer = projectileTimer;
	}



	public double getStandartSpeed() {
		return standartSpeed;
	}



	public void setStandartSpeed(double standartSpeed) {
		this.standartSpeed = standartSpeed;
	}



	public double getTemporarySpeed() {
		return temporarySpeed;
	}



	public void setTemporarySpeed(double temporarySpeed) {
		this.temporarySpeed = temporarySpeed;
	}
	public boolean isLobbing() {
		return Lobbing;
	}



	public void setLobbing(boolean lobbing) {
		Lobbing = lobbing;
	}


	public boolean isRageing() {
		return rageing;
	}
	public void setRageing(boolean rageing) {
		this.rageing = rageing;
	}





}
