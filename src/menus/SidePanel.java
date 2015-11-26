package menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Image;

import player.EnergyBar;
import player.HpBar;
import player.Player;
import player.SpecBar;

public class SidePanel extends JPanel{
	
	java.net.URL resource1 = getClass().getResource("/Menus/Controls1.png");
	java.net.URL resource2 = getClass().getResource("/Menus/Controls2.png");
	/**
	 * Image of the controls
	 */
	private Image controls;
	private static final long serialVersionUID = 1L;
	/**
	 * Player who the panel is representing
	 */
	private Player player;
	/**
	 * Frame containing the panel
	 */
	@SuppressWarnings("unused")
	private JFrame container;
	/**
	 * Hp bar of the player
	 */
	private HpBar hp; 
	/**
	 * Energy bar of the player
	 */
	private EnergyBar energy;
	/**
	 * Special bar of the player
	 */
	private SpecBar spec;
	/**
	 * Numer of the panel
	 */
	private int panelNumber;
	/**
	 * @param player Player the panel represents
	 * @param container Frame containing the panel
	 * @param panelnumber Number of the panel
	 */
	public SidePanel(Player player, JFrame container, int panelnumber){
		if(panelnumber==1){
			controls=new ImageIcon(resource1).getImage();;
		}
		else if(panelnumber==2){
			controls=new ImageIcon(resource2).getImage();
		}
		this.player = player;
		this.container = container;
		this.panelNumber=panelnumber;
		hp = new HpBar(player);
		energy = new EnergyBar(player);
		setSpec(new SpecBar(player));
	
		setBackground(new Color(25,25,25));	
		setPreferredSize(new Dimension(210,container.getHeight()));
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(panelNumber==1){
			g.drawImage(controls, 5, 370, getWidth() - 10, getWidth() - 5, null);
		}
		else if(panelNumber==2){
			g.drawImage(controls, 5, 370, getWidth() - 10, getWidth() - 5, null);
		}
		g.drawImage(player.getFighterImage(), 10, 10, getWidth() - 10, getWidth() - 10, null);
		hp.draw(g, getWidth(), getHeight());
		energy.draw(g, getWidth(), getHeight());
		spec.draw(g, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		for (int i = 0; i < player.getBuffList().size(); i++){
			g.drawImage(player.getBuffList().get(i).getImage(),player.getBuffList().get(i).getBuffNumber() * getWidth()/6 + 20 , 4 * getHeight() /9 + 40, 30, 30, null);
			g.drawString(Integer.toString((player.getBuffList().get(i).getDuration() - player.getBuffList().get(i).getCurrentDuration())/60 + 1), player.getBuffList().get(i).getBuffNumber() * getWidth()/6 + 35, 4 * getHeight() /9 + 65);
		}
		
		if(player.getWorld().getGravity() <= 5 || player.getWorld().getGravity() >= 15)
			g.setColor(Color.RED);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("Gravity: " + String.format("%.2f", player.getWorld().getGravity()), getWidth()/6, 7 * getHeight()/ 8);
		
	}

	public SpecBar getSpec() {
		return spec;
	}

	public void setSpec(SpecBar spec) {
		this.spec = spec;
	}
	
}
