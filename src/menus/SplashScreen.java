package menus;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author William
 * Splash screen in the begining of the game
 */
public class SplashScreen extends JPanel{

	private static final long serialVersionUID = 1L;
	java.net.URL resourceFlash = getClass().getResource("flash.png");
	java.net.URL resource11 = getClass().getResource("bg.jpg");
	java.net.URL resourceTitle = getClass().getResource("TITLE.png");
	java.net.URL resource4 = getClass().getResource("NINJA.png");
	java.net.URL resource5 = getClass().getResource("WARRIOR.png");
	java.net.URL resource6 = getClass().getResource("MAGE.png");
	java.net.URL resource7 = getClass().getResource("gravitron.png");
	java.net.URL resource8 = getClass().getResource("BLV.png");
	java.net.URL resourceNIN1=getClass().getResource("NINJAR.png");
	java.net.URL resourceNIN2=getClass().getResource("NINJAR1.png");
	java.net.URL resourceNIN3=getClass().getResource("NINJAR2.png");
	java.net.URL resourceMAGE1=getClass().getResource("MAGER.png");
	java.net.URL resourceMAGE2=getClass().getResource("MAGER1.png");
	java.net.URL resourceMAGE3=getClass().getResource("MAGER2.png");
	java.net.URL resourceGVT1=getClass().getResource("GVTL.png");
	java.net.URL resourceGVT2=getClass().getResource("GVTL1.png");
	java.net.URL resourceGVT3=getClass().getResource("GVTL2.png");
	java.net.URL resourceOLAF1=getClass().getResource("OLAFL.png");
	java.net.URL resourceOLAF2=getClass().getResource("OLAFL1.png");
	java.net.URL resourceOLAF3=getClass().getResource("OLAFL2.png");

	Image flash=new ImageIcon(resourceFlash).getImage();
	Image title=new ImageIcon(resourceTitle).getImage();
	Image background = new ImageIcon(resource11).getImage();
	Image ninja = new ImageIcon(resource4).getImage();
	Image warrior = new ImageIcon(resource5).getImage();
	Image mage = new ImageIcon(resource6).getImage();
	Image gravitron = new ImageIcon(resource7).getImage();
	Image vampire = new ImageIcon(resource8).getImage();
	

	/**
	 * Container that the splash screen is in
	 */
	private Window container;
	/**
	 * Current positions of the images
	 */
	private int position=1;
	/**
	 * X position of the images
	 */
	private double x=1;
	/**
	 * Y position of the images
	 */
	private double y=1;
	/**
	 * True when the logo appears
	 */
	private boolean logo=false;
	/**
	 * X Size of the logo
	 */
	private int logoSizex=0;
	/**
	 * Y Size of the logo
	 */
	private int logoSizey=0;
	/**
	 * True when the animation has finished
	 */
	private boolean isFinished=false;
	
	/**
	 * Timer that decides when the main timer starts
	 */
	private Timer TimerStarter=new Timer(1,new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(isFinished){
				
				
				TimerStarter.stop();
				container.changePanel(new MainMenu(container));
			}
		}
	});
	/**
	 *  Timer that makes the movements of the splash screen
	 */
	private Timer timer=new Timer(1000/60,new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(position==6){
				ninja=new ImageIcon(resourceNIN1).getImage();
				mage=new ImageIcon(resourceMAGE1).getImage();
				gravitron=new ImageIcon(resourceGVT1).getImage();
				warrior=new ImageIcon(resourceOLAF1).getImage();
			}
			else if(position==12){
				ninja=new ImageIcon(resourceNIN2).getImage();
				mage=new ImageIcon(resourceMAGE2).getImage();
				gravitron=new ImageIcon(resourceGVT2).getImage();
				warrior=new ImageIcon(resourceOLAF2).getImage();
				
			}
			else if(position==18){
				ninja=new ImageIcon(resourceNIN3).getImage();
				mage=new ImageIcon(resourceMAGE3).getImage();
				gravitron=new ImageIcon(resourceGVT3).getImage();
				warrior=new ImageIcon(resourceOLAF3).getImage();
				
			}
			else if(position==24){
				ninja=new ImageIcon(resourceNIN2).getImage();
				mage=new ImageIcon(resourceMAGE2).getImage();
				gravitron=new ImageIcon(resourceGVT2).getImage();
				warrior=new ImageIcon(resourceOLAF2).getImage();
				position=0;
			}
			if(x>500 && x<600){
				logo=true;
				timerLogo.start();
			}
			if(x>1200){
				timer.stop();
				isFinished=true;
			}
			position++;
			x+=5;
			y+=3;
			repaint();
		}
	}); 
	/**
	 * Timer that controls the logo
	 */
	Timer timerLogo=new Timer(1,new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			logoSizex+=8;
			logoSizey+=4;
			if(logoSizex>1120){
				timerLogo.stop();
				
			}
			repaint();
		}
	});
	
	/**
	 * @param container Window containing the splash screen
	 */
	public SplashScreen(Window container){
		this.container = container;
		setPreferredSize(new Dimension(1120, 700));
		setSize(1120, 700);
		timer.start();
		TimerStarter.start();
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, 1120, 700, null); // sets the background
		g.drawImage(vampire, (getWidth()/2)-50, (int)y+20, 100, 100, null);
		g.drawImage(ninja, (int)x, (int)y, 100, 100, null);
		g.drawImage(mage, (int)x, getHeight()-100-(int)y, 100, 100, null);
		g.drawImage(warrior, getWidth()-100-(int)x, (int)y, 100, 100, null);
		g.drawImage(gravitron, getWidth()-100-(int)x, getHeight()-100-(int)y, 100, 100, null);
		
		
		if(logo){
			g.drawImage(flash, (int)(getWidth()/2)-(logoSizex/2), (int)(getHeight()/2)-(logoSizey/2), logoSizex, logoSizey, null);
			g.drawImage(title, (int)(getWidth()/2)-(logoSizex/2), (int)(getHeight()/2)-(logoSizey/2), logoSizex, logoSizey, null);
		}

	}

//	@SuppressWarnings("static-access")
//	public static void main(String[] args) {
//		frame=new JFrame();
//		SplashScreen panel=new SplashScreen();
//		frame.add(panel);
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//		frame.setSize(1120, 700);
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
//	}
	public Timer getTimerStarter() {
		return TimerStarter;
	}
	public void setTimerStarter(Timer timerStarter) {
		TimerStarter = timerStarter;
	}

}
