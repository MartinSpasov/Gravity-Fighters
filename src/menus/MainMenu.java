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

/**
 * @author Martin
 * Main menu which appears after splash screen
 */
public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Image of the background
	 */
	private Image background;
	/**
	 * Image inside the play button
	 */
	private ImageIcon playb;
	/**
	 * Image inside the options button
	 */
	private ImageIcon optionb;
	/**
	 * Image of the title
	 */
	private Image title;
	/**
	 * Image inside the exist button
	 */
	private ImageIcon exitb; //Declares all images that are used in the main menu
	/**
	 * Play button
	 */
	private JButton play;
	/**
	 * Options button
	 */
	private JButton option;
	/**
	 * Exit button
	 */
	private JButton exit; //Declares all the buttons
	/**
	 * Window containing the menu
	 */
	@SuppressWarnings("unused")
	private Window container; //Window containing this panel
	/**
	 * A temporary image used for scaleing
	 */
	private Image temp; //A temporary image used for scaleing
	

	/**
	 * @param container Window containing the menu
	 */
	public MainMenu(final Window container) {
		
		setSize(1120, 700);
		setPreferredSize(new Dimension(1120, 700));
		java.net.URL resource1 = getClass().getResource("/Menus/bg.jpg");
		java.net.URL resource2 = getClass().getResource("/Menus/playb.png");
		java.net.URL resource3 = getClass().getResource("/Menus/soundonb.png");
		java.net.URL resource4 = getClass().getResource("/Menus/exitb.png"); //Loads the resources for the images
		java.net.URL resource5 = getClass().getResource("/Menus/TITLE.png");
		
		background = new ImageIcon(resource1).getImage();
		playb = new ImageIcon(resource2);
		optionb = new ImageIcon(resource3);
		exitb = new ImageIcon(resource4); //Sets the images
		title = new ImageIcon(resource5).getImage();
		
		temp = playb.getImage().getScaledInstance(450, 75, Image.SCALE_SMOOTH); //Everything to do with the play button
		playb = new ImageIcon(temp);
		play = new JButton();
		play.setIcon(playb);
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				container.changePanel(new CharSelectMenu(container, 0));

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

		temp = exitb.getImage().getScaledInstance(450, 75, Image.SCALE_SMOOTH);//Everything to do with the exit button
		exitb = new ImageIcon(temp);
		exit = new JButton();
		exit.setIcon(exitb);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		play.setMaximumSize(new Dimension(450, 70)); //sets dimensions of the buttons
		option.setMaximumSize(new Dimension(450, 70));
		exit.setMaximumSize(new Dimension(450, 70));

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		play.setAlignmentX(Component.CENTER_ALIGNMENT); //Places the buttons correctly on the screen
		option.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0, 250)));
		add(play);
		add(Box.createRigidArea(new Dimension(0, 70)));
		add(option);
		add(Box.createRigidArea(new Dimension(0, 70)));
		add(exit);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, 1120, 700, null); // sets the background
		g.drawImage(title, getWidth()/5, getHeight()/16, getWidth()/5 * 3 , getHeight()/4, null);

	}
}
