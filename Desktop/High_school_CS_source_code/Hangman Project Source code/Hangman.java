
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * Description: This project allows you to play hangman.
 */

public class Hangman
{    
    String word1;
    String letter;
    String wrongLetters="";
    String s1="";
    String guessedLetters="";
    String word;

    int i;
    int a;
    int lives=8;
    int index;

    /**
     * Clears the window (basically a shortcut for System.out.print("\f");).
     */
    public void clear()
    {
        System.out.print("\f");
    }

    /**
     * Sets up the top part of the game display.
     */
    private void setUpTop()
    {
        word1 = JOptionPane.showInputDialog(null,"Enter a word");
        if (word1==null)
        {
            clear();
            System.out.print("Game Over");
        }
        else if (wordValid())
        {
            word=word1.toLowerCase();
            i=word.length();
            int b=0;
            System.out.print("\n");
            while(b<i)
            {
                System.out.print("-");
                b++;
            }
        }
        else
        {
            while(!wordValid())
            {
                word1 = JOptionPane.showInputDialog(null,"Enter a word");
            }
        }
    }

    /**
     * Resets the top part of the display after a letter is guessed.
     */
    private void setUpTop2()
    {

        word=word1.toLowerCase();
        i=word.length();
        int b=0;
        System.out.print("\n");
        while(b<i)
        {
            System.out.print("-");
            b++;
        }

    }

    /**
     * Determines whether or no the guessed word is valid (i.e., no characters that are not in the alphabet).
     */
    private boolean wordValid()
    {   i=word1.length();
        int counter=0;
        final String alphabet2= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQURSTUVWXYZ";
        String currentLetter=word1.substring(counter,counter+1);
        int d=alphabet2.indexOf(currentLetter);

        while (counter<i-1)
        {
            if(d>=0)
            {
                counter++;  
            }
            else
            {
                return false;   
            }
        }
        return true;
    }

    /**
     * Plays the actual game.
     */
    public void playHangman()
    {
        final String alphabet= "abcdefghijklmnopqrstuvwxyz";

        clear();
        a=0;
        setUpTop();
        while(a<i)
        {
            letter = JOptionPane.showInputDialog(null,"Enter a letter or word guess");

            if (letter==null)
            {
                a=a+i;
            }
            else
            {
                int j=letter.length();
                if (j>1)
                {
                    if (wordCorrect())
                    {
                        a=a+i;
                    }
                    else
                    {
                        clear();
                        rightLetter();
                        setUpTop2();
                        wrongGuess();
                        addBodyPart();
                        if(lives==1)
                        {
                            a=a+i;
                        }
                    }
                }

                else
                {
                    int k=alphabet.indexOf(letter);
                    if (k>=0)
                    {
                        if(letterCorrect())
                        {
                            rightLetter();
                            setUpTop2();
                            wrongGuess();
                            addBodyPart();
                        }
                        else
                        {   
                            clear();
                            rightLetter();
                            setUpTop2();
                            wrongGuess();
                            addBodyPart();
                            if(lives==1)
                            {
                                a=a+i;
                            }
                        }   
                    }
                    else
                    {
                        ;  
                    }
                }
            }

        }
        clear();
        if(word1!=null)
        {
            if(letter!=null)
            {  
                if(lives<=1)
                {
                    trollFace();
                    System.out.println("");
                    System.out.println("");
                    System.out.println("You lose. You have been trololo-ed...");
                }
                else
                {
                    johnCena();
                    System.out.println("");
                    System.out.println("");
                    System.out.println("You win! John Cena congratulates you!");
                }  
            }
            else
                System.out.print("Game Over");
        }
    }

    /**
     * Adds body parts to the hangman, keeps track of # of lives.
     */
    private void addBodyPart()
    {
        if(!letterCorrect())
        {
            lives=lives-1;
        }
        System.out.print("\n\n\n\n\n\n");
        if (lives==8)
        {
            hang0();
        }
        else if (lives==7)
        {
            hang1();
        }
        else if(lives==6)
        {
            hang2();
        }
        else if(lives==5)
        {
            hang3();
        }
        else if(lives==4)
        {
            hang4();
        }
        else if(lives==3)
        {
            hang5();
        }
        else if(lives==2)
        {
            hang6();
        }
        else if(lives==1)
        {
            clear();
            trollFace();
            System.out.println("");
            System.out.println("");
            System.out.println("You lose. You have been trololo-ed...");
        }
        else
        {
            clear();
            trollFace();
            System.out.println("");
            System.out.println("");
            System.out.println("You lose. You have been trololo-ed...");
        }
    }

    /**
     * Prints out the incorrectly guessed letters. 
     */
    private void wrongGuess()
    {
        if(!letterCorrect())
        {
            wrongLetters=wrongLetters+letter+" ";
        }
        System.out.print("         Incorrect guesses: "+wrongLetters);
    }

    /**
     * Prints out letters that have already been guessed, as well as the new correct letters.
     */
    private void rightLetter()
    {
        word=word1.toLowerCase();
        int i=word.length();
        int count=0;
        //String userGuessedWord="";

        if(letterCorrect())
        {
            clear();
        }

        while(count<i)
        {
            String nextLetter=word.substring(count,count+1);
            int q=guessedLetters.indexOf(nextLetter);

            if (q==-1)
            {
                if (nextLetter.equals(letter))
                {
                    System.out.print(nextLetter);
                    guessedLetters=guessedLetters+letter;
                    a=a+letterOccurance();
                    count++;
                }
                else
                {
                    System.out.print(" ");
                    count++;
                }
            }
            else
            {
                System.out.print(nextLetter);    
                count=count+1;
            }
        }

    }

    /**
     * Returns the # of occurances of a letter in the word.
     */
    public int letterOccurance()
    {
        word=word1.toLowerCase();
        index=word.indexOf(letter);
        int last=word.lastIndexOf(letter);
        int counter2=1;

        while(index!=last)
        {
            index=word.indexOf(letter,index+1);
            counter2=counter2+1;
        }
        return counter2;
    }

    /**
     * Determines whether or not a guessed word is correct.
     */
    private boolean wordCorrect()

    {
        String word=word1.toLowerCase();
        if (word.equals(letter))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Prints out a John Cena image when the user wins the game.
     */
    public void johnCena()
    {
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMZN+?.??+ZMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMDDDNII..:7OMMMMMMD8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMDDNNN~+7?+MMMMMMMMMNDMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMODDNDDD$ODDO8NMMMMMMN8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMM8DDDNMMMMMMMI7ZDNMMMDDMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMM$O8MMMMMMMMMMM$7ZONMNNOMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMZ$MMMMMMMN8OO8DM7$Z8$MDMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMZMMMMMNO7II7$O8DDM7N=?MOMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMZNMMMMDO$IIZD87ZOD8??ZM8D8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMM8=?MNON8O?=NO=O7ZZO8+:NN8OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMN::~$8$II?==$7+=++?7O+.DN88MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMM77?~=======~+ZI==+?7Z+~DD8OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMN=~,~~~:~~=+IOO7??I$8$:ND87MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMM:~=~=~~:?D$D8OZ7?IDOD,MD8MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMM:==+==~===?~+7O7IZ$8=:,MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMM~=8+~==+=IO8$$OZ=ZDDZ~~MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMM=~=7?==?77$O$?8DD8IIMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMM+++~=~++??I7I$DDDONDMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMM:?===~~~==II$DD88NNNDMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMO~?==++?IIZO8ODDNNND~MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMM~==7Z8D8888::INMNND??MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMM:==+?78NNN?~~7NNNDDZ88IMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMM:====+?7$?~+ZDNND88$7Z$$$?DMMMMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMM,+=====???:=$Z88D8$~~?O$7777I=NMMMMMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMM:~+=~?====?++~~?O$ZZZ~+I8D$7I??????I??MMMMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMMMM,7=~+:==+++?~=?O777=~~7N$87II?++++???I7?NMMMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMMMM::~~++++~=++++:+OZ7?:=?$87O7II????+++++?I77+MMMMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMMMD~=~~~~~===+==+=~=7$I+~+?8ZI?7II+=,7II?+??III?:~=NMMMMMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMMMM~~~~~~~~~=::::~==~?=::~+OD+=+?=:=?8NI+??++??+=+IIII7+~MMMMMMMMMM");
        System.out.println("MMMMMMMMMMMMMM,:=..,?======~~~~~=+=~::?88+?$==+I8N???++++++??I?II7II$$=:NMMMMMMM");
        System.out.println("MMMMMMMMMMMMO::::==++?++======~=====+?ODDI~+IZN$+???++++++++????III77$ZI,NMMMMMM");
        System.out.println("MMMMMMMM:.:.:,======+I+==========++?78N7~+8N=+~=+=??+?+=+++++???I7I7$$$O~,MMMMMM");
        System.out.println("MMMMMM.~,::::==~~==+??+=++~====+++I?ZO??NNNN8$77?I7$O+++++++++??III7$$$ZO=,MMMMM");
        System.out.println("MMMMM,:=~=====~~~==?+?+=++~===+==I?ZZ$OOOZI?I$NNMI=++?+++++++++?III77$$OD$:MMMMM");
        System.out.println("MMMM.:~==~==~~~~=+?I7I?++I~~~==?II7IZOO$$8MZ=+++++??????+?++++???II7$$ZO8DI$MMMM");
        System.out.println("MMM8,~~==~~~~~~=+I$$$I?+:+Z~~=???I7$7O8NMN=++++++?+??II7I??????I?II7$$Z8DNO=MMMM");
        System.out.println("MMM.:~~=~~~~~==+I$OO$I7++?IOZ++?I7ZODNMMO+++++++++??I$Z8$7IIII??III7$ZO8DN8?MMMM");
        System.out.println("MMM.~==~~~~~==+?$ODNZ=.~+=778OZ+IZNMMM++?+++++++???I$Z888$$$II?I7$ZZZZO8DNDIMMMM");
        System.out.println("MMM:~=~~~~~~~=+?7O88O$+:7?$IOODDDMN$7?+=+++++++??I7$ODD8D7ZO88DDDDNDDNNNND$?MMMM");
        System.out.println("MMM:==~~~~==~~==+?:+$Z?II?O88DNMMMN78I?Z+++++??II7$$Z$$OZOO88+=8?=OZZ7?ZNNNNO$MM");
        System.out.println("MMMM=======~~~~~~~+IIZZZ788DDNMMMM$8N7II??????II77$$$$Z8O8DDN?+O$+88$7D87NND8ZMM");
        System.out.println("MMMM:~::~~~~~~==?+=I7:O88D8DNMMMM7ZODO??????II77$$ZZZZ8O8NNNNNDDNNDDD8DNZNNNDODM");
        System.out.println("MMMMM,:~~~~~~~:~+++~O8888DDNNMMM778O8ZII??II77$$ZZZZO8NDOI77$$$$$$$$Z8NMMMMMNOIM");
        System.out.println("MMMM.,:~===~,~:$Z$OO88D88NNMMMMD77O8DO7$II77$$ZOOOO8DDM7$7$$$$$$ZZ$ZZZZZZO8DMMMM");
        System.out.println("MMMO,:~~=:,~:~=+OO8888DDNMMMMM8$$ZZDDOZZ77$ZZOOOD8DDNNNZ$ZZZZ$$ZZZZZZZZZOO8DD$NM");
        System.out.println("MMM.,::,::~:==+++888DDDNNMMMMMZZOO8N87??ZZZOO88NONNNMMMZZOOOZZZZZZZZZZZZOO8D8$MM");
        System.out.println("MM?,,,:~=+~+????+?8NDMMMMMMMMOO8ZDZ$7?+?7$ZZOO8DNMMMMMMOOOOOOZ$Z$ZZOZZZZOO8DNZMM");
        System.out.println("MM,:~==+++??????I7ZOZ=+DMMMMZZ$7I?I77?+???77$ZO888DNMMMN888OZ$$$ZZOZZZZZZO8DNOMM");
        System.out.println("MM=~+?++???I+?II$888?7NMMMMDZ$7????$II?I?II7$ZOO8DDDNNNM88O$$$$ZZZOOZZZZOO8DNDMM");
        System.out.println("MM+=+?I:~?++?I7ODZO878NMMMN8Z$II??I7I7IIII7$ZOO88DDDDNM$777777$$ZOOOZ$$ZZODNMDMM");
        System.out.println("MMM+++I+=~=??IZ$7ZO8O$MNNDN8Z$7IIIIIDO$77$ZOO888DD8Z777II777777$ZZZZ$$$$O8NMMNMM");

    }

    /**
     * Prints out a troll face when the user loses the game.
     */
    public void trollFace()
    {

        System.out.println("................................................................................");
        System.out.println("................................................................................");
        System.out.println("................................................................................");
        System.out.println("................................................................................");
        System.out.println("................................................................................");
        System.out.println("....................... ~MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM..................");
        System.out.println("................. ,MMMMMMMN=.. .... .NNNM=....MM=,...........MMN................");
        System.out.println("...............NMM=.......... ........ ....  ..... ~M..........MM...............");
        System.out.println("............MMM...~  . $MMMN8NNNNNM8 ............MM . O$........NMO.............");
        System.out.println("..........MMM........... ............... .. .,DMN, ..N.. N...... MM:............");
        System.out.println(".........~M .......D.........M.........  ...........8?.$:..M..... NM............");
        System.out.println(".........MM......  .........N..........=...............M. .........NN...........");
        System.out.println(".........MN..................?............. ..NMMN.. ...8.. .......IM...........");
        System.out.println("........MN......... ........ I...........MMNN:MMMMMMMM?.............NMM.........");
        System.out.println("......?MN........ MMNMMNMMMM ......... MN7....MMMMMM,.MM. ...........+MM........");
        System.out.println(".....MM$.MNO......NMMMMMMMMMMM,MM.....NM...MMMMNMMMMMNMMM.7M,.......,M.8MM .....");
        System.out.println("....MM.M. ..................MMNN.......MMNM.....NN. ....... 7MMMMMM+.... ,MD....");
        System.out.println("...NM?M M. ,MMM?.............M...................MMN.....+MMMM. . .MMN.... MM...");
        System.out.println("...MM...M.MMO ..MNN NN.......M ................... ,MMMMMM.. ..M.....MM..N.:N...");
        System.out.println("...MM...M.....N, MMMM......MMN ..............................DMMN.....M..M..MN..");
        System.out.println("....M. .M.... M .........MM8 ......... MMMMMM.............MMMM..MMM,. M..M..MN..");
        System.out.println("....MNM. .O..MM:.......MMMM..........:....MM......... MNMMN,...MM.$M~MM.....M~..");
        System.out.println(".....MM ZN .MMMM ...N...?.MM......ZMNMMM.MMO......MNMMMM......NMM....M... .MM...");
        System.out.println("......NM.. .MNMMMM..........MM M .......... .MMMMMN .. M....NMMMI......+, MM....");
        System.out.println("...... MZ...MMM MMMMMN.......?M .......MMMMMM, .......MMMMMMMMM...... ..NMM.....");
        System.out.println(".......MM...MMM.MM..NMMMMMMMMMMMMMMMMMMM  ..MM....  MMMMN7..MM.........MN,......");
        System.out.println(".......MN...MMN.MN..NM......MD. ..ZM........MM MMMMMMMDM...NM........MMM........");
        System.out.println(".......=M...MMMMMMM M8 .....N.....ZM..... NMMMMMMMN,..MM  MN.........MM.........");
        System.out.println("........M...MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNMM........NMMM ........MM..........");
        System.out.println(".......~M...MMNMMMMMMMMMMMMMMMMMMMMMMMMMMM...MM.......MMM..........?M...........");
        System.out.println(".......MM...MM MNMMMMMMMMMMMMMMMMMMMMI.......=MZ... MMN:..........DM?...........");
        System.out.println(".......MM....NM:M 8M,. M ...MM......MN........MM $MMM ...........MM.............");
        System.out.println(".......MN....,MM...MM .MN ...M .....MM........MMMMM.....?...$..NNM..............");
        System.out.println(".......N~......NMMMMMM? MM ..MM.....MM$:NMMMMMM.....M+..,M7. MMM................");
        System.out.println(".......M......... .,MMMMMMMMMMMMMMMMMMMMMM= .....N:..,MZ..~MMN. ................");
        System.out.println("......+M...M...:.............................+M...NM ..:MMM, ...................");
        System.out.println("......MM...O.... M ......... ............~N.. NN....OMMM .......................");
        System.out.println("......MM....O .... ..,8MMDOI==,. . .....,MZ......MMMN,..........................");
        System.out.println("......MM...... NM .............. DM?.. .......MMM8 .............................");
        System.out.println("...... MN..................................NMN .................................");
        System.out.println("....... MM......................... MMMNMMM:....................................");
        System.out.println(".........NMM ..................NMMMMMNM. .......................................");
        System.out.println("...........NMMMMMMMMNMNMNMMMMMMZ................................................");
        System.out.println("............... .ZMMMN+.........................................................");
        System.out.println("................................................................................");
        System.out.println("................................................................................");
        System.out.println("................................................................................");

    }

    /**
     * Determines whether or not a guessed letter is in the word.
     */
    private boolean letterCorrect()
    {
        String word=word1.toLowerCase();
        int g= word.indexOf(letter);
        if(g==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Displays how much of the hangman the person has left with 0 wrong guesses.
     */
    private void hang0 ()
    {
        System.out.print("   _________ \n");
        System.out.print("   |       |\n");
        System.out.print("   |       |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("_______\n");
    }

    /**
     * Displays how much of the hangman the person has left with 1 wrong guess.
     */
    private void hang1 ()
    {
        System.out.print("   _________ \n");
        System.out.print("   |       |\n");
        System.out.print("   |       |\n");
        System.out.print("   |      :)\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("_______\n");
    }

    /**
     * Displays how much of the hangman the person has left with 2 wrong guesses.
     */
    private void hang2 ()
    {
        System.out.print("   _________ \n");
        System.out.print("   |       |\n");
        System.out.print("   |       |\n");
        System.out.print("   |      :)\n");
        System.out.print("   |       |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("_______\n");
    }

    /**
     * Displays how much of the hangman the person has left with 3 wrong guesses.
     */
    private void hang3 ()
    {
        System.out.print("   _________ \n");
        System.out.print("   |       |\n");
        System.out.print("   |       |\n");
        System.out.print("   |      :)\n");
        System.out.print("   |       |\n");
        System.out.print("   |     --+--\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("_______\n");
    }

    /**
     * Displays how much of the hangman the person has left with 4 wrong guesses.
     */
    private void hang4 ()
    {
        System.out.print("   _________ \n");
        System.out.print("   |       |\n");
        System.out.print("   |       |\n");
        System.out.print("   |      :)\n");
        System.out.print("   |       |\n");
        System.out.print("   |     --+--\n");
        System.out.print("   |       |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("_______\n");
    }

    /**
     * Displays how much of the hangman the person has left with 5 wrong guesses.
     */
    private void hang5 ()
    {
        System.out.print("   _________ \n");
        System.out.print("   |       |\n");
        System.out.print("   |       |\n");
        System.out.print("   |      :)\n");
        System.out.print("   |       |\n");
        System.out.print("   |     --+--\n");
        System.out.print("   |       |\n");
        System.out.print("   |      / \n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("_______\n");
    }

    /**
     * Displays how much of the hangman the person has left with 6 wrong guesses.
     */
    private void hang6 ()
    {
        System.out.print("   _________ \n");
        System.out.print("   |       |\n");
        System.out.print("   |       |\n");
        System.out.print("   |      :)\n");
        System.out.print("   |       |\n");
        System.out.print("   |     --+--\n");
        System.out.print("   |       |\n");
        System.out.print("   |      / \\\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("_______\n");
    }

    /**
     * Displays how much of the hangman the person has left with 7 wrong guesses.
     */
    private void hang7 ()
    {
        System.out.print("   _________ \n");
        System.out.print("   |       |\n");
        System.out.print("   |       |\n");
        System.out.print("   |      :)\n");
        System.out.print("   |       |\n");
        System.out.print("   |     --+--\n");
        System.out.print("   |       |\n");
        System.out.print("   |     _/ \\\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("_______\n");
    }

    /**
     * Displays how much of the hangman the person has left with 8 wrong guesses.
     */
    private void hang8 ()
    {
        System.out.print("   _________ \n");
        System.out.print("   |       |\n");
        System.out.print("   |       |\n");
        System.out.print("   |      :O\n");
        System.out.print("   |       |\n");
        System.out.print("   |     --+--\n");
        System.out.print("   |       |\n");
        System.out.print("   |     _/ \\_\n");
        System.out.print("   |\n");
        System.out.print("   |\n");
        System.out.print("_______\n");
    }

}
