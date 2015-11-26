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
import maps.*;

public class MapSelectMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Image of the background
	 */
	private Image background;
	
	/**
	 * Back button
	 */
	private JButton back;
	/**
	 * Continue button
	 */
	private JButton cont;
	/**
	 * Select fire map button
	 */
	private JButton fire;
	/**
	 * Select ice map button
	 */
	private JButton ice;
	/**
	 * Select air map button
	 */
	private JButton air;
	/**
	 * Select earth map button
	 */
	private JButton earth;
	/**
	 * Select electricity map button
	 */
	private JButton electricity;
	/**
	 * Image inside the back button
	 */
	private ImageIcon backb;
	/**
	 * Image inside the continue button
	 */
	private ImageIcon contb;
	/**
	 * Image inside the select fire map button
	 */
	private ImageIcon fireb;
	/**
	 * Image inside the select ice map button
	 */
	private ImageIcon iceb;
	/**
	 * Image inside the select air map button
	 */
	private ImageIcon airb;
	/**
	 * Image inside the select earth map button
	 */
	private ImageIcon earthb;
	/**
	 * Image inside the select electricity map button
	 */
	private ImageIcon electricityb;
	/**
	 * A temporary image used for scaleing
	 */
	private Image temp; // A temporary image used for scaleing
	/**
	 * Image for the select map text
	 */
	private Image map;
	/**
	 * Window containing this menu
	 */
	@SuppressWarnings("unused")
	private Window container; // Window containing this panel
	/**
	 * Panel containing the select map buttons
	 */
	private JPanel mapPanel;
	/**
	 * Panel containing the back button
	 */
	private JPanel backPanel;
	/**
	 * The selected map
	 */
	private World mapSelected; 
	/**
	 * Action listener for when the buttons are pressed
	 */
	private Listener l;
	
	
	/**
	 * @param container Frame containing the menu
	 */
	public MapSelectMenu(final Window container) {
		this.container = container;
		
		Sound.playSound("selectArena");
		l = new Listener();
		setSize(1120, 700);
		setPreferredSize(new Dimension(1120, 700));
		java.net.URL resource1 = getClass().getResource("/Menus/bg.jpg");
		java.net.URL resource2 = getClass().getResource("/Menus/backb.png");
		java.net.URL resource3 = getClass().getResource("/Menus/contb.png");
		java.net.URL resource4 = getClass().getResource("/maps/FIRE.png");
		java.net.URL resource5 = getClass().getResource("/maps/ICE.png");
		java.net.URL resource6 = getClass().getResource("/maps/AIR.png");
		java.net.URL resource7 = getClass().getResource("/maps/EARTH.png");
		java.net.URL resource8 = getClass().getResource("/maps/ELECTRICITY.png");
		java.net.URL resource9 = getClass().getResource("/menus/MAPCHOSE.png");
		
				
		background = new ImageIcon(resource1).getImage();
		backb = new ImageIcon(resource2);
		contb = new ImageIcon(resource3);
		fireb = new ImageIcon(resource4);
		iceb = new ImageIcon(resource5);
		airb = new ImageIcon(resource6);
		earthb = new ImageIcon(resource7);
		electricityb = new ImageIcon(resource8);
		map = new ImageIcon(resource9).getImage();
		
		
		temp = backb.getImage().getScaledInstance(200, 75, Image.SCALE_SMOOTH);
		backb = new ImageIcon(temp);
		back = new JButton();
		back.setIcon(backb);
		back.setBorder(null);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				container.changePanel(new CharSelectMenu(container,0));
			}

		});
		
		temp = contb.getImage().getScaledInstance(450, 75, Image.SCALE_SMOOTH);
		contb = new ImageIcon(temp);
		cont = new JButton();
		cont.setIcon(contb);
		cont.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(mapSelected != null){
					container.start(mapSelected, new SidePanel(Window.p1, container,1),new SidePanel(Window.p2, container,2)); 
					Sound.playSound("fight");
				}		
			}

		});
		
		temp = fireb.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		fireb = new ImageIcon(temp);
		fire = new JButton();
		fire.setIcon(fireb);
		fire.addActionListener(l);
		
		temp = iceb.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		iceb = new ImageIcon(temp);
		ice = new JButton();
		ice.setIcon(iceb);
		ice.addActionListener(l);
		
		temp = airb.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		airb = new ImageIcon(temp);
		air = new JButton();
		air.setIcon(airb);
		air.addActionListener(l);
		
		temp = earthb.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		earthb = new ImageIcon(temp);
		earth = new JButton();
		earth.setIcon(earthb);
		earth.addActionListener(l);
		
		temp = electricityb.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		electricityb = new ImageIcon(temp);
		electricity = new JButton();
		electricity.setIcon(electricityb);
		electricity.addActionListener(l);
		
		fire.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
		air.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
		ice.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
		earth.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
		electricity.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
		
		back.setMaximumSize(new Dimension(180, 70)); //sets dimensions of the buttons
		back.setContentAreaFilled(false);
		cont.setMaximumSize(new Dimension(450, 70));
		cont.setContentAreaFilled(false);
		fire.setMaximumSize(new Dimension(100, 100));
		fire.setContentAreaFilled(false);
		ice.setMaximumSize(new Dimension(100, 100));
		ice.setContentAreaFilled(false);
		air.setMaximumSize(new Dimension(100, 100));
		air.setContentAreaFilled(false);
		earth.setMaximumSize(new Dimension(100, 100));
		earth.setContentAreaFilled(false);
		electricity.setMaximumSize(new Dimension(100, 100));		
		electricity.setContentAreaFilled(false);

		
		backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backPanel.setOpaque(false);	
		backPanel.setMaximumSize(new Dimension(1000,100));
		backPanel.add(back);
		
		mapPanel = new JPanel();
		mapPanel.setOpaque(false);	
		mapPanel.setMaximumSize(new Dimension(400,240));
		mapPanel.add(fire);
		mapPanel.add(air);
		mapPanel.add(ice);
		mapPanel.add(earth);
		mapPanel.add(electricity);
		
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		cont.setAlignmentX(Component.CENTER_ALIGNMENT);
		mapPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(backPanel);
		add(Box.createRigidArea(new Dimension(0, 100)));
		add(mapPanel);
		add(Box.createRigidArea(new Dimension(0, 70)));
		add(cont);
	}


	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, 1120, 700, null); // sets the background
		g.drawImage(Window.p1.getFighterImage(), 100, 200 , 200, 200, null);
		g.drawImage(Window.p2.getFighterImage(), 820, 200 , 200, 200, null);
		g.drawImage(map, getWidth()/4, getHeight()/16, getWidth()/4 * 2 , getHeight()/8, null);

	}
	
	/**
	 * @author Martin
	 * Listener that does things when a button is pressed
	 */
	private class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			fire.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
			air.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
			ice.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
			earth.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
			electricity.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,120)));
			b.setBorder(BorderFactory.createLineBorder(Color.RED));			
			
			if(b == fire)
				mapSelected = new FireMap(Window.p1, Window.p2);
			if(b == air)
				mapSelected = new AirMap(Window.p1, Window.p2);
			if(b == ice)
				mapSelected = new IceMap(Window.p1, Window.p2);
			if(b == earth)
				mapSelected = new EarthMap(Window.p1, Window.p2);
			if(b == electricity)
				mapSelected = new ElectricityMap(Window.p1, Window.p2);
			repaint();
						
		}
	}

}
