import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class RunProject
{
    Sounds sound1=new Sounds();
    Hangman hangman1=new Hangman();    

    public void RunProject()  
    {

        hangman1.playHangman();
        if(hangman1.lives<=1)
        {
            if (hangman1.word1!=null)
            {
                if (hangman1.letter!=null)
                {
                    sound1.playSounds2();
                }
            }
        }
        else
        {
            if (hangman1.word1!=null)
            {
                if (hangman1.letter!=null)
                {
                    sound1.playSounds();
                }
            }
        }

    }  

    
    
    
    
    
}
