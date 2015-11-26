package menus;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import sounds.Sound;
import maps.World;

/**
 * @author Martin & William
 *A popup menu that appears inside the game
 */
public class PopupMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Background image
	 */
	private Image background;
	/**
	 * Image inside the resume button
	 */
	private ImageIcon resumeb;
	/**
	 * Image inside the option button
	 */
	private ImageIcon optionb;
	/**
	 * Image of the title
	 */
	private Image title;
	/**
	 * Image inside the mainmenu button
	 */
	private ImageIcon mainmenub;
	/**
	 * Play button
	 */
	private JButton play;
	/**
	 * Option button
	 */
	private JButton option;
	/**
	 * Back to main menu button
	 */
	private JButton mainmenu;
	/**
	 * Frame containing the menu
	 */
	@SuppressWarnings("unused")
	private Window container; //Window containing this panel
	/**
	 * A temporary image used for scaleing
	 */
	private Image temp; //A temporary image used for scaleing
	

	/**
	 * @param container Window containing this panel
	 */
	public PopupMenu(Window container) {
		
		this.container = container;		
		setSize(1120, 700);
		setPreferredSize(new Dimension(500, 690));
		java.net.URL resource1 = getClass().getResource("/Menus/bg.jpg");
		java.net.URL resource2 = getClass().getResource("/Menus/resumeb.png");
		java.net.URL resource3 = getClass().getResource("/Menus/soundonb.png");
		java.net.URL resource4 = getClass().getResource("/Menus/mainmenub.png"); //Loads the resources for the images
		java.net.URL resource5 = getClass().getResource("/Menus/GAMEPAUSED.png");
		
		background = new ImageIcon(resource1).getImage();
		resumeb = new ImageIcon(resource2);
		optionb = new ImageIcon(resource3);
		mainmenub = new ImageIcon(resource4); //Sets the images
		title = new ImageIcon(resource5).getImage();
		
		temp = resumeb.getImage().getScaledInstance(450, 75, Image.SCALE_SMOOTH); //Everything to do with the play button
		resumeb = new ImageIcon(temp);
		play = new JButton();
		play.setIcon(resumeb);
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				World world = container.getListener1().getPlayer().getWorld();
				world.setRunning(true);
				world.gameLoop();
				container.popdown();
				

			}

		});

		temp = optionb.getImage()
				.getScaledInstance(450, 75, Image.SCALE_SMOOTH); //Everything to do with the option button
		optionb = new ImageIcon(temp);
		option = new JButton();
		option.setIcon(optionb);
		option.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Sound.onOff();
				
			}
			
		});

		temp = mainmenub.getImage().getScaledInstance(450, 75, Image.SCALE_SMOOTH);//Everything to do with the exit button
		mainmenub = new ImageIcon(temp);
		mainmenu = new JButton();
		mainmenu.setIcon(mainmenub);
		mainmenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Window.getWindow().setListener1(null);
				//Window.getWindow().setListener2(null);
				//Window.getMap().setPlayer1(null);
				//Window.getMap().setPlayer2(null);
				container.changePanel(new MainMenu(container));
			}

		});

		play.setMaximumSize(new Dimension(450, 70)); //sets dimensions of the buttons
		option.setMaximumSize(new Dimension(450, 70));
		mainmenu.setMaximumSize(new Dimension(450, 70));

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		play.setAlignmentX(Component.CENTER_ALIGNMENT); //Places the buttons correctly on the screen
		option.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainmenu.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0, 250)));
		add(play);
		add(Box.createRigidArea(new Dimension(0, 70)));
		add(option);
		add(Box.createRigidArea(new Dimension(0, 70)));
		add(mainmenu);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, 1120, 700, null); // sets the background
		g.drawImage(title, 10, getHeight()/16, getWidth() , getHeight()/4, null);

	}
	

}
