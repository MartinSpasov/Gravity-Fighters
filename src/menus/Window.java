package menus;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import player.Listener;
import player.Player;
import sounds.Sound;
import maps.World;

/**
 * @author Martin
 * Frame of the game
 */
public class Window extends JFrame { // Class hosting the main method and the
										// Frame for the game

	private static final long serialVersionUID = 1L;
	/**
	 * Jpanel containing everythig the game
	 */
	private JPanel bigPanel = new JPanel();
	/**
	 * Smaller panel used mainly for menus
	 */
	private JPanel panel; // The current panel
	/**
	 * The map that has been chosen
	 */
	private World map;
	/**
	 * Player1
	 */
	public static final Player p1  = new Player(1);
	/**
	 * Player2
	 */
	public static final Player p2  = new Player(2);

	/**
	 * Listener for player 1
	 */
	private Listener listener1;
	/**
	 * Listener for player 2
	 */
	private Listener listener2;
	/**
	 * Popup menu that appears upon pausing the game
	 */
	private PopupMenu popup;

	/**
	 * Daufault constructor
	 */
	public Window() {
		
	
		Sound.playMusic("music");
		bigPanel.setPreferredSize(new Dimension(1120, 700));
		panel = new SplashScreen(this);
		bigPanel.add(panel); // Creates and adds the world to the frame
		add(bigPanel);	
		setTitle("Gravity Fighters.exe");
		popup = new PopupMenu(this);
		//setIcon(new ImageIcon("Menus/logo.jpg"));
		
		listener1 = new Listener(p1, this);
		addKeyListener(listener1);
		listener2 = new Listener(p2, this);
		addKeyListener(listener2);
	

		setResizable(false);
		//setFocusable(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); // Packs the frame to the world size
	}

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {

		new Window();
		
	}

	/**
	 * Changes the Currently displayed panel
	 * @param newpanel New Panel that will be displayed
	 */
	public void changePanel(JPanel newpanel) {
		if(map != null){
			map.setRunning(false);
			p1.setFighter(null);
			p1.setFighterImage(null);
			p1.setWorld(null);
			p2.setFighter(null);
			p2.setFighterImage(null);
			p2.setWorld(null);
			listener1 = null;
			listener2 = null;
			setFocusable(false);
			map = null;
		}
		bigPanel.removeAll();
		remove(bigPanel);
		bigPanel = new JPanel();
		bigPanel.setPreferredSize(new Dimension(1120, 700));
		panel = newpanel;
		bigPanel.add(panel);
		
		add(bigPanel);
		revalidate();
		bigPanel.repaint();
	}
	/**
	 * Starts the Game
	 * @param map Map that has been chosen
	 * @param left Player 1 side Panel
	 * @param right Player 2 side Panel
	 */
	public void start(World map, SidePanel left, SidePanel right){
		this.map = map;
		bigPanel.remove(panel);
		bigPanel.setLayout(new BorderLayout());
		bigPanel.add(left, BorderLayout.WEST);
		bigPanel.add(right, BorderLayout.EAST);
		bigPanel.add(map,BorderLayout.CENTER);
		bigPanel.repaint();
		revalidate();
		this.setFocusable(true);
		
		requestFocusInWindow();
		map.repaint();
		map.setP1Panel(left);
		map.setP2Panel(right);
		
		
		
		map.gameLoop();

	}
	/**
	 * Displays the popup menu
	 */
	public void popup(){
		bigPanel.add(popup);
		popup.setFocusable(true);
		popup.setOpaque(true);
		bigPanel.remove(map);			
		popup.repaint();
		
		bigPanel.repaint();
		revalidate();
	
	}
	/**
	 * Remvoes the popup menu if its displayed
	 */
	public void popdown(){
		bigPanel.remove(popup);
		bigPanel.add(map, BorderLayout.CENTER);
		bigPanel.repaint();
		revalidate();
	}

	public Listener getListener1() {
		return listener1;
	}

	public Listener getListener2() {
		return listener2;
	}
	public JPanel getPanel() {
		return panel;
	}

}
