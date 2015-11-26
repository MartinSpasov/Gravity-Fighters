package menus;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import sounds.Sound;
import characters.*;

/**
 * @author Martin
 * Menu where playrs select thier characters
 */
public class CharSelectMenu extends JPanel {
	java.net.URL resource1 = getClass().getResource("/Menus/bg.jpg");
	java.net.URL resource2 = getClass().getResource("/Menus/backb.png");
	java.net.URL resource3 = getClass().getResource("/Menus/contb.png");
	java.net.URL resource4 = getClass().getResource("/Menus/NINJA.png");
	java.net.URL resource5 = getClass().getResource("/Menus/WARRIOR.png");
	java.net.URL resource6 = getClass().getResource("/Menus/MAGE.png");
	java.net.URL resource7 = getClass().getResource("/Menus/gravitron.png");
	java.net.URL resource8 = getClass().getResource("/Menus/BLV.png");
	java.net.URL resource9 = getClass().getResource("/Menus/P1CHOSE.png");
	java.net.URL resource10 = getClass().getResource("/Menus/P2CHOSE.png");
	java.net.URL resource11 = getClass().getResource("/Menus/NinjaStats.png");
	java.net.URL resource12 = getClass().getResource("/Menus/GVTStats.png");
	java.net.URL resource13 = getClass().getResource("/Menus/MageStats.png");
	java.net.URL resource14 = getClass().getResource("/Menus/BLoodLordStats.png");
	java.net.URL resource15 = getClass().getResource("/Menus/OlafStats.png");
	private static final long serialVersionUID = 1L;
	/**
	 * Image of the background
	 */
	private Image background;
	/**
	 * Image of the text saying its player 1 turn to chose
	 */
	private Image p1turn;
	/**
	 * Image of the text saying its player 2 turn to chose
	 */
	private Image p2turn;
	/**
	 * Back button
	 */
	private JButton back;
	/**
	 * Continue button
	 */
	private JButton cont;
	/**
	 * Select ninja button
	 */
	private JButton ninja;
	/**
	 * Select warrior button
	 */
	private JButton warrior;
	/**
	 * Select vampire button
	 */
	private JButton vampire;
	/**
	 * Select gravitron button
	 */
	private JButton gravitron;
	/**
	 * Select mage button
	 */
	private JButton mage;
	/**
	 * Image in the back button
	 */
	private ImageIcon backb;
	/**
	 * Image in the continue button
	 */
	private ImageIcon contb;
	/**
	 * Image in the select ninja button
	 */
	private ImageIcon ninjab;
	/**
	 * Image in the select warrior button
	 */
	private ImageIcon warriorb;
	/**
	 * Image in the select mage button
	 */
	private ImageIcon mageb;
	/**
	 * Image in the select gravitron button
	 */
	private ImageIcon gravitronb;
	/**
	 * Image in the select vampire button
	 */
	private ImageIcon vampireb;
	/**
	 * Image of the stats of the selected character by player 1
	 */
	private Image imageStats1;
	/**
	 * Image of the stats of the selected character by player 2
	 */
	private Image imageStats2;
	/**
	 * A temporary image used for scaleing
	 */
	private Image temp; // A temporary image used for scaleing
	/**
	 * Fighter player 1 has selected
	 */
	private Fighter p1Selected;
	/**
	 * Fighter player 2 has selected
	 */
	private Fighter p2Selected;
	/**
	 * True as long as player 1 hasn't slected a fighter
	 */
	private boolean p1Selecting = true;	
	/**
	 * True as long as player 2 hasn't selected a fighter
	 */
	private boolean p2Selecting = true;	
	/**
	 * Panel containing the select fighter buttons
	 */
	private JPanel charPanel;
	/**
	 * Panel containing the back button
	 */
	private JPanel backPanel;
	/**
	 * Mouse Listener
	 */
	private Listener l;
	

	
	/**
	 * Frame containing the menu
	 */
	@SuppressWarnings("unused")
	private Window container; // Window containing this panel

	/**
	 * @param container Window containing the menu
	 * @param p 0 if no one has selected, 1 if player 1 has already slected, 2 if both players have already selected
	 */
	public CharSelectMenu(final Window container, int p) {
	
		Sound.playSound("choseChar");
		this.container = container;
		
		if (p == 0){
			//player1 = new Player(1);
			//player2 = new Player(2);
		}
		
		if (p == 1){
			p1Selecting = false;
		}
		
		setSize(1120, 700);
		setPreferredSize(new Dimension(1120, 700));
		

		background = new ImageIcon(resource1).getImage();
		backb = new ImageIcon(resource2);
		contb = new ImageIcon(resource3);
		ninjab = new ImageIcon(resource4);
		warriorb = new ImageIcon(resource5);
		mageb = new ImageIcon(resource6);
		gravitronb = new ImageIcon(resource7);
		vampireb = new ImageIcon(resource8);
		p1turn = new ImageIcon(resource9).getImage();
		p2turn = new ImageIcon(resource10).getImage();
		
		l = new Listener();

		temp = backb.getImage().getScaledInstance(200, 75, Image.SCALE_SMOOTH);
		backb = new ImageIcon(temp);
		back = new JButton();
		back.setIcon(backb);
		back.setBorder(null);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				container.changePanel(new MainMenu(container));
			}

		});
		
		temp = contb.getImage().getScaledInstance(450, 75, Image.SCALE_SMOOTH);
		contb = new ImageIcon(temp);
		cont = new JButton();
		cont.setIcon(contb);
		cont.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Window.p1.getFighter() != null && Window.p2.getFighter() != null){
					container.changePanel(new MapSelectMenu(container));
				}
				else if (Window.p1.getFighter() != null){
					container.changePanel(new CharSelectMenu(container,1));
				}
			}

		});
		
		temp = ninjab.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ninjab = new ImageIcon(temp);
		ninja = new JButton();
		ninja.setIcon(ninjab);
		ninja.addActionListener(l);
		
		temp = mageb.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		mageb = new ImageIcon(temp);
		mage = new JButton();
		mage.setIcon(mageb);
		mage.addActionListener(l);
		
		temp = warriorb.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		warriorb = new ImageIcon(temp);
		warrior = new JButton();
		warrior.setIcon(warriorb);
		warrior.addActionListener(l);
		
		temp = gravitronb.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		gravitronb = new ImageIcon(temp);
		gravitron = new JButton();
		gravitron.setIcon(gravitronb);
		gravitron.addActionListener(l);
		
		temp = vampireb.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		vampireb = new ImageIcon(temp);
		vampire = new JButton();
		vampire.setIcon(vampireb);
		vampire.addActionListener(l);
		
		ninja.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
		mage.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
		vampire.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
		gravitron.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
		warrior.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
			
		
		back.setMaximumSize(new Dimension(180, 70)); //sets dimensions of the buttons
		back.setContentAreaFilled(false);
		cont.setMaximumSize(new Dimension(450, 70));
		cont.setContentAreaFilled(false);
		mage.setMaximumSize(new Dimension(100, 100));
		mage.setContentAreaFilled(false);
		warrior.setMaximumSize(new Dimension(100, 100));
		warrior.setContentAreaFilled(false);
		gravitron.setMaximumSize(new Dimension(100, 100));
		gravitron.setContentAreaFilled(false);
		vampire.setMaximumSize(new Dimension(100, 100));
		vampire.setContentAreaFilled(false);
		ninja.setMaximumSize(new Dimension(100, 100));		
		ninja.setContentAreaFilled(false);
		
		backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backPanel.setOpaque(false);	
		backPanel.setMaximumSize(new Dimension(1000,100));
		backPanel.add(back);
		
		charPanel = new JPanel();
		charPanel.setOpaque(false);	
		charPanel.setMaximumSize(new Dimension(400,240));
		charPanel.add(mage);
		charPanel.add(ninja);
		charPanel.add(warrior);
		charPanel.add(gravitron);
		charPanel.add(vampire);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


		cont.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		
		add(backPanel);
		add(Box.createRigidArea(new Dimension(0, 100)));
		add(charPanel);
		add(Box.createRigidArea(new Dimension(0, 70)));
		add(cont);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, 1120, 700, null); // sets the background
		if(Window.p1.getFighterImage() != null)
			g.drawImage(Window.p1.getFighterImage(), 100, 125 , 200, 200, null);
		if(Window.p2.getFighterImage() != null)
			g.drawImage(Window.p2.getFighterImage(), 820, 125 , 200, 200, null);
		if(p1Selecting){
			g.drawImage(p1turn, getWidth()/4, getHeight()/16, getWidth()/4 * 2 , getHeight()/4, null);
			g.drawImage(imageStats1,25, 330 , 400, 200, null);
		}
			
		else{
			g.drawImage(p2turn, getWidth()/4, getHeight()/16, getWidth()/4 * 2 , getHeight()/4, null);
			g.drawImage(imageStats2,720, 330 , 400, 200, null);
		}
			
			
		
		
		

	}
	/**
	 * @author Martin
	 * Listener class that does something when a button is pressesd
	 */
	private class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
		
			if (p1Selecting){
				ninja.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
				mage.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
				vampire.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
				gravitron.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
				warrior.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
				b.setBorder(BorderFactory.createLineBorder(Color.RED));
				
				Window.p1.setFighterImage(((ImageIcon)b.getIcon()).getImage());
				
				if(b == ninja){
					p1Selected = new Ninja(1);
					imageStats1=new ImageIcon(resource11).getImage();
				}
					
				if(b == gravitron){
					p1Selected = new Gravitron(1);
					imageStats1=new ImageIcon(resource12).getImage();
				}
					
				if(b == warrior){
					p1Selected = new Olaf(1);
					imageStats1=new ImageIcon(resource15).getImage();
				}
				if(b == vampire){
					p1Selected = new BloodLordVamp(1);
					imageStats1=new ImageIcon(resource14).getImage();
				}
					
				if(b == mage){
					p1Selected = new Mage(1);
					imageStats1=new ImageIcon(resource13).getImage();
				}
				
				//p1Selected = new Ninja();
				Window.p1.setFighter(p1Selected);
				repaint();
			}
			else if (p2Selecting){
				ninja.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
				mage.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
				vampire.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
				gravitron.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
				warrior.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
				b.setBorder(BorderFactory.createLineBorder(Color.BLUE));
				
				Window.p2.setFighterImage(((ImageIcon)b.getIcon()).getImage());
				
				if(b == ninja){
					p2Selected = new Ninja(2);
					imageStats2=new ImageIcon(resource11).getImage();
				}
					
				if(b == gravitron){
					p2Selected = new Gravitron(2);
					imageStats2=new ImageIcon(resource12).getImage();
				}
					
				if(b == warrior){
					p2Selected = new Olaf(2);
					imageStats2=new ImageIcon(resource15).getImage();
				}
				if(b == vampire){
					p2Selected = new BloodLordVamp(2);
					imageStats2=new ImageIcon(resource14).getImage();
				}
					
				if(b == mage){
					p2Selected = new Mage(2);
					imageStats2=new ImageIcon(resource13).getImage();
				}
					
				
				Window.p2.setFighter(p2Selected);
				
				repaint();
			}
			
		}
		
	}
}
