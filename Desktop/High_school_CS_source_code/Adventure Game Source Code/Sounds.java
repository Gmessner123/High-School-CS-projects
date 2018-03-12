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
    public void cenaSounds()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test Sound Clip");
        this.setSize(300, 200);
        this.setVisible(false);

        try {
            // Open an audio input stream.
            //URL url = this.getClass().getClassLoader().getResource("gameover.wav");
            //AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

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
    public void undertakerSounds()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test Sound Clip");
        this.setSize(300, 200);
        this.setVisible(false);

        try {
            // Open an audio input stream.
            URL url= new URL("http://shortmp3.mobi/u/files/WAV/39633-Undertaker_(ShortMp3.com).wav");
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
    
    /**
     * Plays the sounds when you lose the game.
     */
    public void tripleHSounds()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test Sound Clip");
        this.setSize(300, 200);
        this.setVisible(false);

        try {
            // Open an audio input stream.
            URL url= new URL("http://shortmp3.mobi/u/files/WAV/39629-Triple_H_(ShortMp3.com).wav");
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
    
    /**
     * Plays the sounds when you lose the game.
     */
    public void bigShowSounds()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test Sound Clip");
        this.setSize(300, 200);
        this.setVisible(false);

        try {
            // Open an audio input stream.
            URL url= new URL("http://shortmp3.mobi/u/files/WAV/39586-Big_Show_(ShortMp3.com).wav");
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
    
    /**
     * Plays the sounds when you lose the game.
     */
    public void theRockSounds()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Test Sound Clip");
        this.setSize(300, 200);
        this.setVisible(false);

        try {
            // Open an audio input stream.
            URL url= new URL("http://shortmp3.mobi/u/files/WAV/39627-The_Rock_(ShortMp3.com).wav");
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