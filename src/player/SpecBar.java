package player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * @author Martin
 * The special bar which represents the amount of special attack the player has
 */
public class SpecBar extends Bar{
	/**
	 * The player who this bar represents
	 */
	private Player player;
	
	/**
	 * @param player Player who this bar represents
	 */
	public SpecBar(Player player){
		this.player = player;
		setMaxValue(100);
		setCurrentValue(0);
		setName("Spec");
		setColor(Color.BLUE);
		
	}
	
	
	public void draw(Graphics g, int width, int height) {
		setCurrentValue(player.getFighter().getCurrentSpec());	
		double temp = getCurrentValue();
		g.setColor(Color.BLACK);
		g.fillRect(10, width + 100, width - 20 , 30);
		g.setColor(getColor());
		g.drawRect(10, width + 100, width - 20 , 30);
		g.fillRect(10, width + 100, (int)( (width - 20) * temp/getMaxValue()), 30);
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString(Integer.toString(getCurrentValue()),width/2 - 20, width + 122);
	}	
	

}
