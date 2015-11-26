package player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * @author Martin
 *Energy bar that represents the energy used for gravity control of a player
 */
public class EnergyBar extends Bar{
	/**
	 * Player the this energy bar represents
	 */
	private Player player;
	
	/**
	 * @param player Player the this energy bar represents
	 */
	public EnergyBar(Player player){
		this.player = player;
		setMaxValue(player.getFighter().getMaxEnergy());
		setCurrentValue(player.getFighter().getCurrentEnergy());
		setName("Energy");
		setColor(Color.YELLOW);
		
	}
	
	
	public void draw(Graphics g, int width, int height) {
		setCurrentValue(player.getFighter().getCurrentEnergy());	
		double temp = getCurrentValue();
		g.setColor(Color.BLACK);
		g.fillRect(10, width + 60, width - 20 , 30);
		g.setColor(getColor());
		g.drawRect(10, width + 60, width - 20 , 30);
		g.fillRect(10, width + 60, (int)( (width - 20) * temp/getMaxValue()), 30);
		g.setColor(Color.GRAY);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString(Integer.toString(getCurrentValue()),width/2 - 20, width + 82);
	}	
	

}
