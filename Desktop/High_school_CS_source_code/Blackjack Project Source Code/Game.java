import javax.swing.*;
/**
 * Runs the actual project.
 * 
 * @author Grant Messner 
 * @version 1
 */
public class Game
{
    boolean userGameOver=false;
    boolean cenaGameOver=false;
    boolean win=true;
    boolean push=false;
    boolean quit=false;
    boolean fold=true;
    boolean handOneOver=false;
    boolean handTwoOver=false;
    boolean specialUsed=false;
    boolean insurance=false;
    boolean userBlackjack=false;
    boolean insuranceOverride=false;

    double bet;
    double funds;

    int turn;

    final double MIN_BET=5;
    final double MAX_BET=100;
    final double STARTING_$=100;
    final double GOAL_$=500;

    String word1;
    String word2;
    String word3;

    Hand userHand=new Hand();
    Hand cenaHand=new Hand();

    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        funds=STARTING_$;
    }

    /**
     * Plays the entire game, including bets.
     */
    public void playGame()
    {
        System.out.println("\f");
        printWelcome();
        while(funds>=MIN_BET && funds<GOAL_$ && quit==false)
        {
            String Bet = JOptionPane.showInputDialog(null,"Enter a bet amount.");
            if(Bet==null)
                ;
            else
            {
                bet= Double.parseDouble(Bet);
                if(bet>=MIN_BET && bet<=funds && bet<=MAX_BET)
                {
                    playBlackjack();
                }

                else if(bet<MIN_BET)
                {
                    System.out.println("Bet amount must be above table minimum of $"+MIN_BET+".");
                }
                else if(bet>funds)
                    System.out.println("Bet amount must be within what you can afford.");
                else if(bet>MAX_BET)
                    System.out.println("Bet amount must be below table maximum of $"+MAX_BET+".");
            }
        }
    }

    /**
     * Ends the entire game, displaying whether you won or lost. 
     */
    public void endGame()
    {
        System.out.println("\f");
        if(funds<MIN_BET)
        {
            System.out.println("You lose. JOHN CENA is the blackjack champion!");
        }
        else if(funds>=GOAL_$)
        {
            System.out.println("You win! You have taken down WWE CHAMPION JOHN CENA and earned blackjack supremacy!");
        }
        else
        {
            System.out.println("Game Over");
        }
    }

    /**
     * Determines the result of a given blackjack hand. 0=loss,1=tie,2=win.
     */
    public int isWin()
    {
        if(userHand.handSumMax()<21 && cenaHand.handSumMax()<21)
        {
            if(userHand.handSumMax()>cenaHand.handSumMax())
            {
                return 2;
            }
            else if(userHand.handSumMax()==cenaHand.handSumMax())
            {
                return 1;  
            }
            else 
                return 0;
        }
        else if(userHand.handSumMax()>21 && cenaHand.handSumMax()<21)
        {
            if(userHand.handSumMin()>21)
            {
                return 0;
            }
            else if(userHand.handSumMin()>cenaHand.handSumMax())
                return 2;
            else if(userHand.handSumMin()==cenaHand.handSumMax()) //maybe fix this later to account for multiple aces
                return 1;
            else
                return 0;
        }
        else if(userHand.handSumMax()<21 && cenaHand.handSumMax()>21)
        {
            if(cenaHand.handSumMin()>21)
                return 2;
            else if(userHand.handSumMax()>cenaHand.handSumMin())
                return 2;
            else if(userHand.handSumMax()==cenaHand.handSumMin())
                return 1;
            else
                return 0;
        }
        else
        {
            if(userHand.handSumMin()>21)
                return 0;
            else if(cenaHand.handSumMin()>21)
                return 2;
            else if(userHand.handSumMin()>cenaHand.handSumMin())
                return 2;
            else if(userHand.handSumMin()==cenaHand.handSumMin())
                return 1;
            else
                return 0;
        }
    }

    /**
     * Plays a single game.
     */
    public void playBlackjack()
    {
        System.out.println("\f");
        System.out.println("");
        userGameOver=false;
        cenaGameOver=false;

        userHand.reset();
        cenaHand.reset();

        cenaHand.printFirstCard();
        System.out.println("");
        userHand.printHand();
        turn=1;

        if(cenaHand.getValue(1)==1) //if card showing is an ace...
        {
            word3 = JOptionPane.showInputDialog(null,"Type yes to buy insurance, no to decline.");

            if(word3==null)
                ;
            else if(word3.equals("yes"))
            {
                if(isBlackjack())
                {
                    userGameOver=true;
                    cenaGameOver=true;
                    insurance=true;
                    insuranceOverride=true;
                }
                else
                {
                    insurance=true;
                    funds=funds-bet;
                    System.out.println("You lost your insurance");
                }
            }
            else if(word3.equals("no"))
            {
                if(isBlackjack())
                {
                    userGameOver=true;
                    cenaGameOver=true;
                    win=false;
                }
            }
            else
            {
                System.out.println("Please enter either yes or no");
            }
        }

        while(!userGameOver)
        {
            getInput();
        }

        while(!cenaGameOver)
        {
            AI();
        }

        if(insuranceOverride==false)
        {
            if(win==false)
            {
                if(insurance==true)
                {
                    funds=funds-bet*2.0;
                    System.out.println("\f");
                    System.out.println("You lost the previous hand and you lost $"+bet*2.0+" since you");
                    System.out.println("bought insurance.");
                    System.out.println("Your balance is now $"+funds+".");
                }
                else
                {
                    funds=funds-bet;
                    System.out.println("\f");
                    System.out.println("You lost the previous hand and lost $"+bet+".");
                    System.out.println("Your balance is now $"+funds+".");
                }
            }
            else
            {
                if(insurance==true)
                {
                    if(isWin()==0)
                    {
                        funds=funds-bet*2.0;
                        System.out.println("\f");
                        System.out.println("You lost the previous hand and you lost $"+bet*2.0+" since you");
                        System.out.println("bought insurance.");
                        System.out.println("Your balance is now $"+funds+".");
                    }
                    else if(isWin()==1)
                    {
                        funds=funds-bet;
                        System.out.println("\f");
                        System.out.println("The previous hand was a push. You lost $"+bet+" since you bought insurance.");
                        System.out.println("Your balance is now $"+funds+".");
                    }
                    else if(isWin()==2)
                    {
                        System.out.println("\f");
                        System.out.println("You won, but neither won or lost money because you had to pay off ");
                        System.out.println("your insurance.");
                        System.out.println("Your balance is now $"+funds+".");
                    }
                }
                else
                {
                    if(isWin()==0)
                    {
                        funds=funds-bet;
                        System.out.println("\f");
                        System.out.println("You lost the previous hand and lost $"+bet+".");
                        System.out.println("Your balance is now $"+funds+".");
                    }
                    else if(isWin()==1)
                    {
                        System.out.println("\f");
                        System.out.println("The previous hand was a push. You neither won nor lost any money.");
                        System.out.println("Your balance is now $"+funds+".");
                    }
                    else if(isWin()==2)
                    {
                        funds=funds+bet;
                        System.out.println("\f");
                        System.out.println("You won the previous hand and won $"+bet+".");
                        System.out.println("Your balance is now $"+funds+".");
                    }
                }
            }
        }
        else
        {
            funds=funds+bet;
            System.out.println("\f");
            System.out.println("You correctly guessed that the dealer had a blackjack and earned $"+bet);
        }
    }

    /**
     * Checks to see whether or not the dealer's hand is a blackjack, given that the first
     * card is an ace.
     */
    public boolean isBlackjack()
    {
        if(cenaHand.getValue(1)==10)
            return true;

        else
            return false;
    }

    /**
     * Prints out a welcome message for the player at the beginning of the game.
     */
    public void printWelcome()
    {
        System.out.println("Welcome to the beautiful Casino Royale in Las Vegas, Nevada!");
        System.out.println("Here you will have the chance to battle WWE legend JOHN CENA ");
        System.out.println("for blackjack supremacy! Do you have what it takes to take down ");
        System.out.println("the mightiest champion in WWE history?");
    }

    /**
     * Processes the user input and determines whether or not it is valid.
     */
    public void getInput()
    {
        word1 = JOptionPane.showInputDialog(null,"Enter a command.");

        if(word1==null)
        {
            ;
        }
        else if(word1.equals("hit"))
        {
            userHand.addCard();
            turn++;
            if(userHand.handSumMin()>21)
            {
                userGameOver=true;
                win=false;
            }
        }
        else if(word1.equals("stay"))
        {
            userGameOver=true;
            turn++;
        }
        else if(word1.equals("split"))
        {
            if((userHand.getValue(0)==userHand.getValue(1)) && turn==1)
            {
                splitMethod();
                turn++;
            }
            else if(turn==1)
            {
                System.out.println("");
                System.out.println("You cannot split two different-valued cards");
            }
            else
            {
                System.out.println("");
                System.out.println("You cannot split after the first turn.");
            }
        }
        else if(word1.equals("double down"))
        {
            if(turn==1)
            {
                userHand.addCard();
                bet=bet*2.0;
                userGameOver=true;
                turn++;
            }
            else
            {
                System.out.println("");
                System.out.println("You cannot double down after the first turn");
            }
        }
        else if(word1.equals("fold"))
        {
            if(turn==1)
            {
                userGameOver=true;
                win=false;
                fold=true;
                turn++;
            }
            else
            {
                System.out.println("");
                System.out.println("You cannot fold after the first turn.");
            }
        }
        else if(word1.equals("quit"))
        {
            userGameOver=true;
        }
        else if(word1.equals("help"))
        {
            printHelp();
        }
        else
        {
            System.out.println("");
            System.out.println("Invalid command word.");
        }
    }

    /**
     * Prints out a list of commands for the user.
     */
    public void printHelp()
    {
        System.out.println("Your command words are:");
        System.out.println("");
        System.out.println("hit-->adds a card to your hand, but at the risk of going over 21 (busting)");
        System.out.println("");
        System.out.println("stay-->ends the current hand with the cards at their current value");
        System.out.println("");
        System.out.println("double down-->doubles the current bet and automatically adds one, and only");
        System.out.println("one card to the current hand. Can only be used on the initial deal");
        System.out.println("");
        System.out.println("split-->splits the current hand into two separate hands, each of which are");
        System.out.println("played separately. Can only be used on the initial deal.");
        System.out.println("");
        System.out.println("fold-->forfiets the current hand, giving you half of your current bet back.");
        System.out.println("Can only be used on the initial deal");
        System.out.println("");
        System.out.println("insurance-->when the dealer has an ace showing, you can bet on whether or");
        System.out.println("not the dealer has a blackjack by buying insurance. If you bet correctly,");
        System.out.println("you earn double your bet as profit. If not, you lose the money you used to");
        System.out.println("buy the insurance");
        System.out.println("");
        System.out.println("help-->prints out this screen");
        System.out.println("");
        System.out.println("quit-->ends the game and displays how much you won or lost");
    }

    /**
     * Processes the user input and determines whether or not it is valid.
     * Only used on the first hand in a split.
     */
    public void getInput2()
    {
        word2 = JOptionPane.showInputDialog(null,"Enter a command.");

        if(word2==null)
        {
            ;
        }
        else if(word2.equals("hit"))
        {
            userHand.addCard2();
            turn++;
            if(userHand.handSumMin2()>21)
            {
                handTwoOver=true;
            }
        }
        else if(word2.equals("stay"))
        {
            handTwoOver=true;
            turn++;
        }
        else if(word2.equals("split"))
        {
            System.out.println("This casino does not allow you to split twice.");
        }
        else if(word2.equals("double down"))
        {
            System.out.println("This casino does not allow you to double down after splitting");
        }
        else if(word2.equals("fold"))
        {
            System.out.println("You are not allowed to fold after splitting.");
        }
        else if(word2.equals("quit"))
        {
            userGameOver=true;
            handTwoOver=true;
            System.out.println("\f");
            System.out.println("Thanks for playing. Goodbye");
        }
        else
        {
            System.out.println("\f");
            System.out.println("Invalid command word.");
        }
    }

    /**
     * Runs the method for splitting a hand
     */
    public void splitMethod()
    {
        turn++;
        userHand.changeCards();
        while(!userGameOver)
        {
            getInput();
        }

        while(!handTwoOver)
        {
            getInput2();
        }

        while(!cenaGameOver)
        {
            AI();
        }
    }

    /**
     * Causes the JOHN CENA to make a move as the dealer. 
     */
    public void AI()
    {
        if(cenaHand.handSumMax()>17)
        {
            cenaGameOver=true;
        }
        else if(cenaHand.handSumMax()==17)
        {
            if(cenaHand.handSumMin()<17)
            {
                while(cenaHand.handSumMin()<17)
                    cenaHand.addCard();
                cenaGameOver=true;
            }
            else
            {
                cenaGameOver=true;
            }
        }
        else //card value less than 17
        {
            cenaHand.addCard();
        }

    }
}
