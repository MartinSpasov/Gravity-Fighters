package maps;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import lineProjectiles.LineProjectile;
import lobProjectiles.Explosion;
import lobProjectiles.LobProjectile;
import menus.SidePanel;
import pickups.Pickup;
import player.Player;
import environments.Environment;
import environments.Platform;

/**
 * @author Martin & William
 *World is an abstract class representing the world the fighters are in. It extends JPanel therefore once the users have passed the few menu screens, the window will contain a world class. The Ratio variable is going to be different depending on what computer the program is running, making sure that the window fits any screen. The gravity will be changed in the Player class. Since every map contains platforms, they will be stored in the world superclass. The world also has a reference to both players used for all interactions. Paintcomponent method will be overwritten in the subclasses.
 */
public abstract class World extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Width of the map in game units
	 */
	public static final int WIDTH = 100; // Width of the map in game units
	/**
	 * Height of the map in game units
	 */
	public static final int HEIGHT = 100; // Height of the map in game units
	/**
	 * Ratio from game units to pixels
	 */
	public static final int RATIO = 7; // Ratio from game units to pixels

	/**
	 * Gravity of the world, by default 9.8
	 */
	private double gravity = 9.8; // Gravity of the world, by default 9.8

	/**
	 * List containing all the platforms in the current world
	 */
	private ArrayList<Platform> platformList = new ArrayList<Platform>(); // List containing all the platforms in the current world
	/**
	 * List containing all the Environments except the platforms in the current world
	 */
	private ArrayList<Environment> environmentList = new ArrayList<Environment>();
	/**
	 * List containing all the Pickups in the current world
	 */
	private ArrayList<Pickup> pickupList = new ArrayList<Pickup>();
	/**
	 * List containing all the Projectiles of player 1 in the current world
	 */
	private ArrayList<LineProjectile> p1LineProjectileList = new ArrayList<LineProjectile>();
	/**
	 * List containing all the Projectiles of player 2 in the current world
	 */
	private ArrayList<LineProjectile> p2LineProjectileList = new ArrayList<LineProjectile>();
	/**
	 * List containing all the LobProjectiles of player 1 in the current world
	 */
	private ArrayList<LobProjectile> p1LobProjectileList = new ArrayList<LobProjectile>();
	/**
	 * List containing all the LobProjectiles of player 2 in the current world
	 */
	private ArrayList<LobProjectile> p2LobProjectileList = new ArrayList<LobProjectile>();
	/**
	 * List containing all the Explosions in the current world
	 */
	private ArrayList<Explosion> explosionList = new ArrayList<Explosion>();
	/**
	 * Player 1
	 */
	private Player player1;
	/**
	 * Player 2
	 */
	private Player player2;
	/**
	 * True while game is running, false ot pause the game
	 */
	private boolean isRunning;
	/**
	 * The side panel containing information for player 1
	 */
	private SidePanel p1Panel;
	/**
	 * The side panel containing information for player 2
	 */
	private SidePanel p2Panel;
	/**
	 * True while map is rotating towards the left
	 */
	private boolean rotatingLeft;
	/**
	 * true while map is rotaing towards the right
	 */
	private boolean rotatingRight;
	/**
	 * The anlge of rotation
	 */
	private double rotationAngle;
	/**
	 * The back ground image
	 */
	private Image bg;

	/**
	 * @param player1 Player 1
	 * @param player2 Player 2
	 * @param source Name of the world
	 */
	protected World(Player player1, Player player2,String source) {
			
		this.player1 = player1;
		this.player2 = player2;
		player1.setWorld(this);
		player2.setWorld(this);
		java.net.URL resource1 = getClass().getResource("/maps/" + source + ".png");
		bg = new ImageIcon(resource1).getImage();
		isRunning = true;
		
	}

	/**
	 * Custom game loop that is the main driving force of the game, all updates happen here
	 */
	public void gameLoop() { // Custom game loop that runs at 60 fps
		Thread loop = new Thread() {
			public void run() {

				long lastLoopTime = System.nanoTime();
				final int TARGET_FPS = 60;
				final long OPTIMAL_TIME = 1000000000 / TARGET_FPS; // same as 1/60 secodns
				long lastFpsTime = 0;
				int fps = 0;
				long updateLength = 0;
				long delay = 0;
				
				long lastFrameUpdate = 0;
				long lastFrameUpdate2 = 0;
				long deathFrameUpdate = 0;

				while (isRunning) {
					
					long now = System.nanoTime();
					updateLength = now - lastLoopTime;					
				
					if(updateLength < OPTIMAL_TIME){
						delay = OPTIMAL_TIME - updateLength;
						
						try {
							long yolo = System.currentTimeMillis();
							Thread.sleep(delay/1000000); //(int) ((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) % 1000000));
							if (System.currentTimeMillis() - yolo > 16)
								System.out.println("overslept for :" + (System.currentTimeMillis() - yolo));
					
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						updateLength += delay;
					}
					else {
						System.out.print("NO DELAY");
					}
					
					
					lastLoopTime = System.nanoTime();
					
					doGameUpdates(updateLength);
					
					lastFrameUpdate += updateLength;
					lastFrameUpdate2 = lastFrameUpdate;
					deathFrameUpdate += updateLength;
					lastFpsTime += updateLength; // counts the time since last fps update
					
					if (deathFrameUpdate >= 500000000 && player1.getFighter().isDead()) {
						deathUpdate(player1);
						deathFrameUpdate = 0;
					}
					else if (lastFrameUpdate >= 100000000 && !player1.getFighter().isDead()) {
						doSlowGameUpdates(player1);
						lastFrameUpdate = 0;	
					}
					if (deathFrameUpdate >= 500000000 && player2.getFighter().isDead()) {
						deathUpdate(player2);
						deathFrameUpdate = 0;
					}	
					else if (lastFrameUpdate2 >= 100000000 && !player2.getFighter().isDead()) {
						doSlowGameUpdates(player2);
						lastFrameUpdate2 = 0;
					}
					
					render();
					
					if (lastFpsTime >= 1000000000) { // after every second has passed prints the fps
						System.out.println("FPS: " + fps);

						lastFpsTime = 0;
						fps = 0;
					}
					else{
						fps++;
					}
									
					
					
				}
				

			}
			
		};
		loop.start();
	}

	/**
	 * Does slower updates for a player
	 * @param player Player who will be updated
	 */
	private void doSlowGameUpdates(Player player) { // method for all updates happening
		// slower that 60 times per second
		player.getFighter().setCurrentImage();
		if (player.getFighter().getCurrentEnergy() < player.getFighter().getMaxEnergy())
			player.getFighter().setCurrentEnergy(player.getFighter().getCurrentEnergy() + 1);
		
	}

	/**
	 * Does the updates once a payer is dead
	 * @param player Player who will get updated
	 */
	private void deathUpdate(Player player) {
		player.getFighter().setCurrentImage();

	}

	/**
	 * Does all the updates in the world
	 * @param delta Time it took for the updates
	 */
	private void doGameUpdates(double delta) {
		
		player1.getFighter().falling(platformList, 3 * gravity, delta);
		player2.getFighter().falling(platformList, 3 * gravity, delta);

		if (player1.getFighter().isL() && (!player1.getFighter().isIncapacitated() || player1.getFighter().isRageing())) {
			player1.getFighter().setMovementSpeed(-player1.getFighter().getStandartSpeed());
		}
		if (player1.getFighter().isR() && (!player1.getFighter().isIncapacitated() || player1.getFighter().isRageing())) {
			player1.getFighter().setMovementSpeed(player1.getFighter().getStandartSpeed());
		}

		if (player2.getFighter().isL() && (!player2.getFighter().isIncapacitated() || player1.getFighter().isRageing())) {
			player2.getFighter().setMovementSpeed(-player2.getFighter().getStandartSpeed());
		}
		if (player2.getFighter().isR() && (!player2.getFighter().isIncapacitated()|| player1.getFighter().isRageing())) {
			player2.getFighter().setMovementSpeed(player2.getFighter().getStandartSpeed());
		}
		
		for (int i = 0; i < environmentList.size(); i++){
			if(player1.getFighter().getHitBox().collidesWith(environmentList.get(i).getHitBox()))
				environmentList.get(i).onTouch(player1);
			if(player2.getFighter().getHitBox().collidesWith(environmentList.get(i).getHitBox()))
				environmentList.get(i).onTouch(player2);
			environmentList.get(i).tick(platformList);
			
		}
		

		for (int i = 0; i < pickupList.size(); i++) { // checks if player 1 is on a pickup
			if (player1.getFighter().getHitBox().collidesWith(pickupList.get(i).getHitBox()) && !pickupList.get(i).isUsed()) {
				pickupList.get(i).pickup(player1.getFighter());
				pickupList.get(i).setUsed(true);				
			}
			if (player2.getFighter().getHitBox().collidesWith(pickupList.get(i).getHitBox()) && !pickupList.get(i).isUsed()) {
				pickupList.get(i).pickup(player2.getFighter());
				pickupList.get(i).setUsed(true);			
			}
		}
		
		if (player1.getFighter().isAttacking()) {
			player1.getFighter().AttackMelee(player1.getWorld().getPlayer2().getFighter(), player1);
		}
		if (player2.getFighter().isAttacking()) {
			player2.getFighter().AttackMelee(player2.getWorld().getPlayer1().getFighter(), player2);
		}
		
		for (int i = 0; i < p1LineProjectileList.size(); i++){ //For loop updating the line projectiles of player 1
			p1LineProjectileList.get(i).move();
			if (p1LineProjectileList.get(i).collidesWith(player2))
				player1.getFighter().increaseSpec(5);
			
			for(int j = 0; j < platformList.size(); j++){
				p1LineProjectileList.get(i).collidesWith(platformList.get(j));			
			}
			if(p1LineProjectileList.get(i).isDead()){
				p1LineProjectileList.remove(i);
			}
		}
		for (int i = 0; i < p2LineProjectileList.size(); i++){ //For loop updating the line projectiles of player 2
			p2LineProjectileList.get(i).move();
			if (p2LineProjectileList.get(i).collidesWith(player1))
				player2.getFighter().increaseSpec(5);
			
			for(int j = 0; j < platformList.size(); j++){
				p2LineProjectileList.get(i).collidesWith(platformList.get(j));			
			}
			if(p2LineProjectileList.get(i).isDead()){
				p2LineProjectileList.remove(i);
			}
		}
		for (int i = 0; i < p1LobProjectileList.size(); i++){ //For loop updating the lob projectiles of player 1
			p1LobProjectileList.get(i).move(gravity, delta);
			if (p1LobProjectileList.get(i).collidesWith(player2))
				player1.getFighter().increaseSpec(10);
			
			for(int j = 0; j < platformList.size(); j++){
				p1LobProjectileList.get(i).collidesWith(platformList.get(j), this);			
			}
			if(p1LobProjectileList.get(i).isDead()){
				p1LobProjectileList.remove(i);
			}
		}
		for (int i = 0; i < p2LobProjectileList.size(); i++){ //For loop updating the lob projectiles of player 1
			p2LobProjectileList.get(i).move(gravity, delta);
			if (p2LobProjectileList.get(i).collidesWith(player1))
				player2.getFighter().increaseSpec(10);
			
			for(int j = 0; j < platformList.size(); j++){
				p2LobProjectileList.get(i).collidesWith(platformList.get(j), this);			
			}
			if(p2LobProjectileList.get(i).isDead()){
				p2LobProjectileList.remove(i);
			}
		}
		for (int i = 0; i < explosionList.size(); i++){ //Forloop that checks the explosions
			explosionList.get(i).update(player1, player2);
			if(explosionList.get(i).isDead())
				explosionList.remove(i);
		}
		
		for (int i = 0; i < player1.getBuffList().size(); i++){
			player1.getBuffList().get(i).tick(player1);
			if(player1.getBuffList().get(i).isEnded()){
				player1.getBuffList().remove(i);
			}
		}
		for (int i = 0; i < player2.getBuffList().size(); i++){
			player2.getBuffList().get(i).tick(player2);
			if(player2.getBuffList().get(i).isEnded()){
				player2.getBuffList().remove(i);
			}
		}
		
		if (player1.getFighter().getCurrentHealth() <= 0) {
			player1.getFighter().setDead(true);
		}
		if (player2.getFighter().getCurrentHealth() <= 0) {
			player2.getFighter().setDead(true);
		}
		
	

		player1.getFighter().movement(platformList);
		player2.getFighter().movement(platformList);
		player1.getFighter().focus();
		player2.getFighter().focus();
		p1Panel.repaint();
		p2Panel.repaint();
		

	}

	/**
	 * Repaints the world
	 */
	protected void render(){
		repaint();
	}

	/**
	 * Flips the world and all object 90 degrees right
	 */
	public void flipRight() { // Rotates the world	
		for(int i = 0; i < platformList.size(); i++){ //forloop for the rotations of the platforms
			double ogX = platformList.get(i).getX();
			double ogY = platformList.get(i).getY();
			int ogLenght = platformList.get(i).getLength();
			int ogHeight = platformList.get(i).getHeight();				
			
			platformList.get(i).setX(100 - ogY - ogHeight);
			platformList.get(i).setY(ogX);
			platformList.get(i).setLength(ogHeight);
			platformList.get(i).setHeight(ogLenght);
				
			platformList.get(i).getHitBox().update(100 - ogY - ogHeight, ogX, 100 - ogY , ogX + ogLenght);
		}
		for(int i = 0; i < environmentList.size(); i++){ //forloop for the rotations of the platforms
			double ogX = environmentList.get(i).getX();
			double ogY = environmentList.get(i).getY();
			int ogLenght = environmentList.get(i).getLength();
			int ogHeight = environmentList.get(i).getHeight();				
			
			environmentList.get(i).setX(100 - ogY - ogHeight);
			environmentList.get(i).setY(ogX);
			environmentList.get(i).setLength(ogHeight);
			environmentList.get(i).setHeight(ogLenght);
				
			environmentList.get(i).getHitBox().update(100 - ogY - ogHeight, ogX, 100 - ogY , ogX + ogLenght);
			environmentList.get(i).rotateImage("R");
		}
		for(int i = 0; i < pickupList.size(); i++){ //for loop for the rotation of the pickups
			int ogX = pickupList.get(i).getX();
			int ogY = pickupList.get(i).getY();							
			
			pickupList.get(i).setX(100 - ogY - Pickup.LENGHT);
			pickupList.get(i).setY(ogX);			
				
			pickupList.get(i).getHitBox().update(100 - ogY - Pickup.LENGHT, ogX, 100 - ogY , ogX + Pickup.LENGHT);
		}
		for(int i = 0; i < p1LobProjectileList.size(); i++){ //forloop for the rotations of the lob projectile of player 1
			double ogX = p1LobProjectileList.get(i).getX();
			double ogY = p1LobProjectileList.get(i).getY();
			
			int ogLenght = p1LobProjectileList.get(i).getWidth();
			int ogHeight = p1LobProjectileList.get(i).getHeight();				
			
			p1LobProjectileList.get(i).setX(100 - ogY - ogHeight);
			p1LobProjectileList.get(i).setY(ogX);
			p1LobProjectileList.get(i).setWidth(ogHeight);
			p1LobProjectileList.get(i).setHeight(ogLenght);		
				
			p1LobProjectileList.get(i).getHitBox().update(100 - ogY - ogHeight, ogX, 100 - ogY , ogX + ogLenght);
		}
		for(int i = 0; i < p2LobProjectileList.size(); i++){ //forloop for the rotations of the lob projectile of player 2
			double ogX = p2LobProjectileList.get(i).getX();
			double ogY = p2LobProjectileList.get(i).getY();
			int ogLenght = p2LobProjectileList.get(i).getWidth();
			int ogHeight = p2LobProjectileList.get(i).getHeight();				
			
			p2LobProjectileList.get(i).setX(100 - ogY - ogHeight);
			p2LobProjectileList.get(i).setY(ogX);
			p2LobProjectileList.get(i).setWidth(ogHeight);
			p2LobProjectileList.get(i).setHeight(ogLenght);
				
			p2LobProjectileList.get(i).getHitBox().update(100 - ogY - ogHeight, ogX, 100 - ogY , ogX + ogLenght);
		}
		for(int i = 0; i < explosionList.size(); i++){ //for loop for the rotation of the explosions
			double ogX = explosionList.get(i).getX();
			double ogY = explosionList.get(i).getY();	
			int ogWidth = explosionList.get(i).getDiameter();
			
			explosionList.get(i).setX(100 - ogY - ogWidth);
			explosionList.get(i).setY(ogX);			
				
			explosionList.get(i).getHitbox().update(100 - ogY - ogWidth, ogX, 100 - ogY , ogX + ogWidth);
		}
		
	}

	/**
	 * Flips the world and all object 90 degrees to the left
	 */
	public void flipLeft() { // Rotates the world
		for(int i = 0; i < platformList.size(); i++){  //forloop for the rotations of the platforms
			double ogX = platformList.get(i).getX();
			double ogY = platformList.get(i).getY();
			int ogLenght = platformList.get(i).getLength();
			int ogHeight = platformList.get(i).getHeight();				
			
			platformList.get(i).setX(ogY);
			platformList.get(i).setY(100 - ogX -ogLenght);
			platformList.get(i).setLength(ogHeight);
			platformList.get(i).setHeight(ogLenght);
				
			platformList.get(i).getHitBox().update(ogY, 100 - ogX -ogLenght, ogY + ogHeight ,100 - ogX);
		}
		for(int i = 0; i < environmentList.size(); i++){  //forloop for the rotations of the platforms
			double ogX = environmentList.get(i).getX();
			double ogY = environmentList.get(i).getY();
			int ogLenght = environmentList.get(i).getLength();
			int ogHeight = environmentList.get(i).getHeight();				
			
			environmentList.get(i).setX(ogY);
			environmentList.get(i).setY(100 - ogX -ogLenght);
			environmentList.get(i).setLength(ogHeight);
			environmentList.get(i).setHeight(ogLenght);
				
			environmentList.get(i).getHitBox().update(ogY, 100 - ogX -ogLenght, ogY + ogHeight ,100 - ogX);
			environmentList.get(i).rotateImage("L");
		}
		
		for(int i = 0; i < pickupList.size(); i++){ //for loop for the rotation of the pickups
			int ogX = pickupList.get(i).getX();
			int ogY = pickupList.get(i).getY();						
			
			pickupList.get(i).setX(ogY);
			pickupList.get(i).setY(100 - ogX - Pickup.LENGHT);
		
				
			pickupList.get(i).getHitBox().update(ogY, 100 - ogX - Pickup.LENGHT, ogY + Pickup.LENGHT ,100 - ogX);
		}
		for(int i = 0; i < p1LobProjectileList.size(); i++){  //forloop for the rotations of the player 1 lob attacks
			double ogX = p1LobProjectileList.get(i).getX();
			double ogY = p1LobProjectileList.get(i).getY();
			int ogLenght = p1LobProjectileList.get(i).getWidth();
			int ogHeight = p1LobProjectileList.get(i).getHeight();				
			
			p1LobProjectileList.get(i).setX(ogY);
			p1LobProjectileList.get(i).setY(100 - ogX -ogLenght);
			p1LobProjectileList.get(i).setWidth(ogHeight);
			p1LobProjectileList.get(i).setHeight(ogLenght);
				
			p1LobProjectileList.get(i).getHitBox().update(ogY, 100 - ogX -ogLenght, ogY + ogHeight ,100 - ogX);
		}
		for(int i = 0; i < p2LobProjectileList.size(); i++){  //forloop for the rotations of the player 2 lob attacks
			double ogX = p2LobProjectileList.get(i).getX();
			double ogY = p2LobProjectileList.get(i).getY();
			int ogLenght = p2LobProjectileList.get(i).getWidth();
			int ogHeight = p2LobProjectileList.get(i).getHeight();				
			
			p2LobProjectileList.get(i).setX(ogY);
			p2LobProjectileList.get(i).setY(100 - ogX -ogLenght);
			p2LobProjectileList.get(i).setWidth(ogHeight);
			p2LobProjectileList.get(i).setHeight(ogLenght);
				
			p2LobProjectileList.get(i).getHitBox().update(ogY, 100 - ogX -ogLenght, ogY + ogHeight ,100 - ogX);
		}
		for(int i = 0; i < explosionList.size(); i++){ //for loop for the rotation of the pickups
			double ogX = explosionList.get(i).getX();
			double ogY = explosionList.get(i).getY();						
			int ogWidth = explosionList.get(i).getDiameter();
			
			explosionList.get(i).setX(ogY);
			explosionList.get(i).setY(100 - ogX - ogWidth);
		
				
			explosionList.get(i).getHitbox().update(ogY, 100 - ogX - ogWidth, ogY + ogWidth ,100 - ogX);
		}
	}
	
	protected void paintComponent(Graphics g) { //Paints everything
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(bg, 0, 0, 700, 700, null);
		getPlayer1().getFighter().draw(g2d);
		getPlayer2().getFighter().draw(g2d);
		
		
		for(int i = 0; i < getP1LineProjectileList().size(); i++){
			getP1LineProjectileList().get(i).draw(g2d);
		}
		for(int i = 0; i < getP2LineProjectileList().size(); i++){
			getP2LineProjectileList().get(i).draw(g2d);
		}
		
		if(rotatingRight){
			if(rotationAngle < Math.PI/2){
				rotationAngle += Math.PI/30;
				g2d.rotate(rotationAngle, WIDTH/2 * RATIO, HEIGHT/2 * RATIO);
			}
			else{
				rotatingRight = false;
				rotationAngle = 0;
				g2d.rotate(-Math.PI/120, WIDTH/2 * RATIO, HEIGHT/2 * RATIO);
				flipRight();
			}
		}
		if(rotatingLeft){
			if(rotationAngle > -Math.PI/2){
				rotationAngle -= Math.PI/30;
				g2d.rotate(rotationAngle, WIDTH/2 * RATIO, HEIGHT/2 * RATIO);
			}
			else{
				rotatingLeft = false;
				rotationAngle = 0;
				g2d.rotate(Math.PI/120, WIDTH/2 * RATIO, HEIGHT/2 * RATIO);
				flipLeft();
			}
		}		
		
		for(int i = 0; i < platformList.size(); i++){
			platformList.get(i).draw(g2d);
		}
		for(int i = 0; i < pickupList.size(); i++){
			pickupList.get(i).draw(g2d);
		}
		
		for(int i = 0; i < p1LobProjectileList.size(); i++){
			p1LobProjectileList.get(i).draw(g2d);
		}
		for(int i = 0; i < p2LobProjectileList.size(); i++){
			p2LobProjectileList.get(i).draw(g2d);
		}
		for(int i = 0; i < explosionList.size(); i++){
			explosionList.get(i).draw(g2d);
		}
		for (int i = 0; i < environmentList.size(); i++){
			environmentList.get(i).draw(g2d);
		}
		
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public ArrayList<Platform> getPlatformList() {
		return platformList;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public SidePanel getP1Panel() {
		return p1Panel;
	}

	public void setP1Panel(SidePanel p1Panel) {
		this.p1Panel = p1Panel;
	}

	public SidePanel getP2Panel() {
		return p2Panel;
	}

	public void setP2Panel(SidePanel p2Panel) {
		this.p2Panel = p2Panel;
	}

	public ArrayList<Pickup> getPickupList() {
		return pickupList;
	}

	public void setPickupList(ArrayList<Pickup> pickupList) {
		this.pickupList = pickupList;
	}

	public ArrayList<LineProjectile> getP2LineProjectileList() {
		return p2LineProjectileList;
	}

	public void setP2LineProjectileList(ArrayList<LineProjectile> p2LineProjectileList) {
		this.p2LineProjectileList = p2LineProjectileList;
	}

	public ArrayList<LineProjectile> getP1LineProjectileList() {
		return p1LineProjectileList;
	}

	public void setP1LineProjectileList(ArrayList<LineProjectile> p1LineProjectileList) {
		this.p1LineProjectileList = p1LineProjectileList;
	}

	public ArrayList<LobProjectile> getP2LobProjectileList() {
		return p2LobProjectileList;
	}

	public void setP2LobProjectileList(ArrayList<LobProjectile> p2LobProjectileList) {
		this.p2LobProjectileList = p2LobProjectileList;
	}

	public ArrayList<LobProjectile> getP1LobProjectileList() {
		return p1LobProjectileList;
	}

	public void setP1LobProjectileList(ArrayList<LobProjectile> p1LobProjectileList) {
		this.p1LobProjectileList = p1LobProjectileList;
	}


	public ArrayList<Explosion> getExplosionList() {
		return explosionList;
	}

	public void setExplosionList(ArrayList<Explosion> explosionList) {
		this.explosionList = explosionList;
	}

	public boolean isRotatingLeft() {
		return rotatingLeft;
	}

	public void setRotatingLeft(boolean rotatingLeft) {
		this.rotatingLeft = rotatingLeft;
	}

	public boolean isRotatingRight() {
		return rotatingRight;
	}

	public void setRotatingRight(boolean rotatingRight) {
		this.rotatingRight = rotatingRight;
	}

	public double getRotationAngle() {
		return rotationAngle;
	}

	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	public ArrayList<Environment> getEnvironmentList() {
		return environmentList;
	}

	public void setEnvironmentList(ArrayList<Environment> environmentList) {
		this.environmentList = environmentList;
	}

	public Image getBg() {
		return bg;
	}

	public void setBg(Image bg) {
		this.bg = bg;
	}

}
