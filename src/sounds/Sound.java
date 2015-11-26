package sounds;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Martin & William
 *Sound player class that plays any sound from anywhere in the code
 */
public final class Sound {
	
	/**
	 * Music clip that will loop, only one music can be played at a time
	 */
	static private Clip music;
	/**
	 * true if sounds will play
	 */
	static private boolean on = true;
	
	
	private Sound(){
	}
	/**
	 * Method that plays any sound
	 * @param path Path to the sound
	 */
	public static void playSound(String path){
		if(on){
			Clip sound;
			try {
				sound = AudioSystem.getClip();
				sound.open(AudioSystem.getAudioInputStream(Sound.class.getResource(path+".wav")));
				sound.start();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			 catch (IOException e) {
				e.printStackTrace();
			} 
			catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Method that plays any music
	 * @param path Path to the music
	 */
	public static void playMusic(String path){
		if(on){
			try {
				try{
					music.stop();
				}
				catch(NullPointerException e){
					System.out.println("no music playing");
				}
				music = AudioSystem.getClip();
				music.open(AudioSystem.getAudioInputStream(Sound.class.getResource(path+".wav")));
				FloatControl volume = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
				volume.setValue(-12.0f);
				music.start();
				music.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			 catch (IOException e) {
				e.printStackTrace();
			} 
			catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void onOff(){
		if (on){
			on = false;
			music.stop();
		}
		else if (!on){
			on = true;
			music.start();
			music.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
}
