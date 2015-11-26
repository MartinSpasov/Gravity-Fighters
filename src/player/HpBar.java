package player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * @author Martin
 *Health bar that represents the amount of health a player has
 */
public class HpBar extends Bar{
	/**
	 * Player who this bar represents
	 */
	private Player player;
	
	/**
	 * @param player Player who this bar represents
	 */
	public HpBar(Player player){
		this.player = player;
		setMaxValue(player.getFighter().getMaxHealth());
		setCurrentValue(player.getFighter().getCurrentHealth());
		setName("Health");
		setColor(Color.RED);
		
	}
	
	
	public void draw(Graphics g, int width, int height) {
		setCurrentValue(player.getFighter().getCurrentHealth());	
		double temp = getCurrentValue();
		g.setColor(Color.BLACK);
		g.fillRect(10, width + 20, width - 20 , 30);
		g.setColor(getColor());
		g.drawRect(10, width + 20, width - 20 , 30);
		g.fillRect(10, width + 20, (int)( (width - 20) * temp/getMaxValue()), 30);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString(Integer.toString(getCurrentValue()),width/2 - 20, width + 42);
	}	
	

}
