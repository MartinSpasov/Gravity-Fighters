package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import menus.Window;

/**
 * @author William
 * Listener that does actions to players depending of the key input
 */
public class Listener implements KeyListener {
	/**
	 * Player that this listener is listening to
	 */
	private Player player;
	/**
	 * The window this listener is located in
	 */
	private Window container;

	/**
	 * @param player Player that this listener is listening to
	 * @param container The window this listener is located in
	 */
	public Listener(Player player, Window container) {
		this.container = container;
		this.player = player;
	}

	public void keyPressed(KeyEvent t) {
				
		if (player.getPlayerNum() == 1) {
			if (t.getKeyCode() == KeyEvent.VK_ESCAPE) {
				if(player.getWorld().isRunning()){
					player.getWorld().setRunning(false);
					container.popup();
				}
				else{
					player.getWorld().setRunning(true);
					player.getWorld().gameLoop();
					container.popdown();
				}
			}
			if(t.getKeyCode() == KeyEvent.VK_SPACE){
				if(player.getFighter().getCurrentSpec() == 100){	
					if(player.getFighter().getProjectileTimer()==45){
						player.getFighter().setLobbing(true);
						player.getFighter().ult(player);
						player.getFighter().setProjectileTimer(0);
					}
					player.getFighter().setCurrentSpec(0);
				}
			}
			
			if (t.getKeyCode() == KeyEvent.VK_H) {
				if(player.getFighter().getProjectileTimer()==45){
					player.getFighter().setLobbing(true);
					player.getFighter().setProjectileTimer(0);
					player.getFighter().AttackLob(player);
				}
				
			}
			
			if (t.getKeyCode() == KeyEvent.VK_U) {
				if (player.getFighter().getCurrentEnergy() >= 50 && !player.getWorld().isRotatingLeft()){
					player.getWorld().setRotatingRight(true);
					player.getFighter().setCurrentEnergy(player.getFighter().getCurrentEnergy() - 50);
				}
			}
			if (t.getKeyCode() == KeyEvent.VK_I) {
				if (player.getFighter().getCurrentEnergy() >= 50 && !player.getWorld().isRotatingRight()){
					player.getWorld().setRotatingLeft(true);
					player.getFighter().setCurrentEnergy(player.getFighter().getCurrentEnergy() - 50);
				}
			}
			
			if (t.getKeyCode() == KeyEvent.VK_K) {
				if(player.getFighter().getAttackingTimer()==30){
					
					player.getFighter().AttackMelee(player.getWorld().getPlayer2().getFighter(), player);
					player.getFighter().setAttacking(true);
					player.getFighter().setAttackingTimer(0);
					
					if (player.getFighter().isL()) {
						player.getFighter().setIML("LeftA");
					}
					else if (player.getFighter().isR()) {
						player.getFighter().setIMR("RightA");
					}
				}
				
			}
			if(t.getKeyCode() == KeyEvent.VK_J){
				if(player.getFighter().getProjectileTimer()==45){
					player.getFighter().setLobbing(true);
					player.getFighter().AttackLine(player);
					player.getFighter().setProjectileTimer(0);
				}
			}
			if (t.getKeyCode() == KeyEvent.VK_A) {
				
				player.getFighter().setLastDirection("L");
				if (!player.getFighter().isAttacking()
						&& !player.getFighter().isT() && !player.getFighter().isL()) {
					player.getFighter().setIML("Left");
				}
				player.getFighter().setL(true);
				player.getFighter().setR(false);

			}
			if (t.getKeyCode() == KeyEvent.VK_D) {
				
				player.getFighter().setLastDirection("R");
				if (!player.getFighter().isAttacking()
						&& !player.getFighter().isT() && !player.getFighter().isR()) {
					player.getFighter().setIMR("Right");
				}
				player.getFighter().setR(true);
				player.getFighter().setL(false);
			}
			if (t.getKeyCode() == KeyEvent.VK_W) {
				player.getFighter().jump();
			}
			if (t.getKeyCode() == KeyEvent.VK_S) {
				player.getFighter().down();
			}
//			if (t.getKeyCode() == KeyEvent.VK_Q) {
//				if (player.getFighter().getTumble() < 2
//						&& !player.getFighter().isT()) {
//					if (player.getFighter().isL()) {
//						player.getFighter().setT(true);
//						player.getFighter().dashLeft(
//								player.getWorld().getPlatformList());
//					}
//					if (player.getFighter().isR()) {
//						player.getFighter().setT(true);
//						player.getFighter().dashRight(
//								player.getWorld().getPlatformList());
//					}
//
//				}
//			}
		}
		if (player.getPlayerNum() == 2) {
			
			if(t.getKeyCode() == KeyEvent.VK_NUMPAD7){
				if(player.getFighter().getCurrentSpec() == 100){	
					if(player.getFighter().getProjectileTimer()==45){
						player.getFighter().setLobbing(true);
						player.getFighter().ult(player);
						player.getFighter().setProjectileTimer(0);
					}
					player.getFighter().setCurrentSpec(0);
				}
			}
			
			
			if (t.getKeyCode() == KeyEvent.VK_NUMPAD5) {
				
				if(player.getFighter().getProjectileTimer()==45){
					player.getFighter().setLobbing(true);
					player.getFighter().setProjectileTimer(0);
					player.getFighter().AttackLob(player);
				}
				
			}
			
			if (t.getKeyCode() == KeyEvent.VK_NUMPAD8) {
				if (player.getFighter().getCurrentEnergy() >= 50 && !player.getWorld().isRotatingLeft()){
					player.getWorld().setRotatingRight(true);
					player.getFighter().setCurrentEnergy(player.getFighter().getCurrentEnergy() - 50);
				}
			}
			if (t.getKeyCode() == KeyEvent.VK_NUMPAD9) {
				if (player.getFighter().getCurrentEnergy() >= 50 && !player.getWorld().isRotatingRight()){
					player.getWorld().setRotatingLeft(true);
					player.getFighter().setCurrentEnergy(player.getFighter().getCurrentEnergy() - 50);
				}
			}
			
			if(t.getKeyCode() == KeyEvent.VK_NUMPAD2){
				if(player.getFighter().getProjectileTimer()==45){
					player.getFighter().setLobbing(true);
					player.getFighter().AttackLine(player);
					player.getFighter().setProjectileTimer(0);
				}
			}
			
			if (t.getKeyCode() == KeyEvent.VK_LEFT) {
				
				player.getFighter().setLastDirection("L");
				if (!player.getFighter().isAttacking()
						&& !player.getFighter().isT() &&!player.getFighter().isL()) {
					player.getFighter().setIML("Left");
				}
				player.getFighter().setL(true);
				player.getFighter().setR(false);
			}
			if (t.getKeyCode() == KeyEvent.VK_RIGHT) {
				
				player.getFighter().setLastDirection("R");
				if (!player.getFighter().isAttacking()
						&& !player.getFighter().isT() && !player.getFighter().isR()) {
					player.getFighter().setIMR("Right");
				}
				player.getFighter().setR(true);
				player.getFighter().setL(false);
			}
			if (t.getKeyCode() == KeyEvent.VK_UP) {

				player.getFighter().jump();

			}
			if (t.getKeyCode() == KeyEvent.VK_DOWN) {
				player.getFighter().down();
			}
//			if (t.getKeyCode() == KeyEvent.VK_NUMPAD0) {
//				if (player.getFighter().getTumble() < 2
//						&& !player.getFighter().isT()) {
//					if (player.getFighter().isL()) {
//						player.getFighter().setT(true);
//						player.getFighter().dashLeft(
//								player.getWorld().getPlatformList());
//					}
//					if (player.getFighter().isR()) {
//						player.getFighter().setT(true);
//						player.getFighter().dashRight(
//								player.getWorld().getPlatformList());
//					}
//				}
//			}
			if (t.getKeyCode() == KeyEvent.VK_NUMPAD6) {
				if(player.getFighter().getAttackingTimer()==30){
					player.getFighter().AttackMelee(player.getWorld().getPlayer1().getFighter(), player);
					player.getFighter().setAttacking(true);
					player.getFighter().setAttackingTimer(0);
					
					if (player.getFighter().isL()) {
						player.getFighter().setIML("LeftA");
					}
					else if (player.getFighter().isR()) {
						player.getFighter().setIMR("RightA");
					}
					
				}
			}
		}
	}

	public void keyReleased(KeyEvent r) {
			
		if (player.getPlayerNum() == 1) {
			
			if (r.getKeyCode() == KeyEvent.VK_Y) {
				System.out.println(player.getWorld().isRunning());
				player.incGrav();
				player.getFighter().setIncreasingGrav(true);
				player.getFighter().setGravTimer(0);
			}
			if (r.getKeyCode() == KeyEvent.VK_G) {
				player.decGrav();
				player.getFighter().setDecreasingGrav(true);
				player.getFighter().setGravTimer(0);
			}
			if (r.getKeyCode() == KeyEvent.VK_K) {
				player.getFighter().setAttackPressed(false);
			}
			if (r.getKeyCode() == KeyEvent.VK_D) {
				player.getFighter().setR(false);
				player.getFighter().setT(false);
				player.getFighter().setIMR("Stand");
				player.getFighter().setMovementSpeed(0);
			}
			if (r.getKeyCode() == KeyEvent.VK_A) {
				player.getFighter().setL(false);
				player.getFighter().setT(false);
				player.getFighter().setIML("Stand");
				player.getFighter().setMovementSpeed(0);
			}
		}
		if (player.getPlayerNum() == 2) {
			
			if (r.getKeyCode() == KeyEvent.VK_NUMPAD4) {
				player.incGrav();
				player.getFighter().setIncreasingGrav(true);
				player.getFighter().setGravTimer(0);
			}
			if (r.getKeyCode() == KeyEvent.VK_NUMPAD1) {
				player.decGrav();
				player.getFighter().setDecreasingGrav(true);
				player.getFighter().setGravTimer(0);
			}
			if (r.getKeyCode() == KeyEvent.VK_RIGHT) {
				player.getFighter().setR(false);
				player.getFighter().setT(false);
				player.getFighter().setIMR("Stand");
				player.getFighter().setMovementSpeed(0);
			}
			if (r.getKeyCode() == KeyEvent.VK_LEFT) {
				player.getFighter().setL(false);
				player.getFighter().setT(false);
				player.getFighter().setIML("Stand");
				player.getFighter().setMovementSpeed(0);
			}
			
		}
	}
	

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
