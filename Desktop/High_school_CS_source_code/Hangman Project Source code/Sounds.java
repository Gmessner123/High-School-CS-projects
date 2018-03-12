
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class Sounds extends JFrame {

    /**
     * Constructor for "Sounds" class.
     */
    public Sounds() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test Sound Clip");
        this.setSize(300, 200);
        this.setVisible(false);
    }

    /**
     * Plays the sounds when you win the game
     */
    public void playSounds()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test Sound Clip");
        this.setSize(300, 200);
        this.setVisible(false);

        try {
            // Open an audio input stream.
            //URL url = this.getClass().getClassLoader().getResource("gameover.wav");
            //AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

            //URL url = new URL("http://www.zzz.com/eatfood.wav");
            URL url= new URL("http://shortmp3.mobi/u/files/WAV/39603-John_Cena_Msg_Alert_(ShortMp3.com).wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            
            //You can change this URL to be whatever .wav file you want

            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    /**
     * Plays the sounds when you lose the game.
     */
    public void playSounds2()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test Sound Clip");
        this.setSize(300, 200);
        this.setVisible(false);

        try {
            // Open an audio input stream.
            URL url= new URL("http://www.atenlabs.com/trololo/trololo2.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Sounds();
    }
}