package player;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Martin
 *The Abstract Bar class represents a bar that will always be visible at some location on the screen while the game is running, displaying a certain value. It has a defined color and position, aswell as a current and maximum value.
 */
public abstract class Bar {
	/**
	 * Name of the bar
	 */
	private String name;
	/**
	 * Color of the bar
	 */
	private Color color;
	/**
	 * Minimum value of the bar
	 */
	private int maxValue;
	/**
	 * Current value of the bar
	 */
	private int currentValue;
	
	
	/**
	 * Draws the bar
	 * @param g PaintComponent
	 * @param width Width of the bar
	 * @param height height of the bar
	 */
	public abstract void draw(Graphics g, int width, int height);
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public int getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}	
}
