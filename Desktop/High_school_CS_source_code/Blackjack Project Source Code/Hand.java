
/**
 * Creates the hand of cards for the user and dealer.
 * 
 * @author Grant Messner 
 * @version 1
 */
public class Hand
{
    Card[] hand=new Card[11];
    Card[] hand2=new Card[11];
    int size=2;
    int size2=0;
    Deck deck2=new Deck();
    Card noneCard=new Card("none", "diamonds", 0, false);

    /**
     * Constructor for objects of class Hand
     */
    public Hand()
    {
        reset();
    }

    /**
     * Reshuffles the deck and refills the hands.
     */
    public void reset()
    {
        deck2.shuffle();
        for(int i=0;i<size;i++)
        {
            hand[i]=deck2.dealCard();   
        }

        for(int j=size;j<=10;j++)
        {
            hand[j]=noneCard;  
        }

        for(int k=0;k<=10;k++)
        {
            hand2[k]=noneCard;
        }
    }

    /**
     * Prints out the hand of cards the player has.
     */
    public void printHand()
    {
        //System.out.println("\f");
        String total="";
        for(int i=0;i<size;i++)
        {
            total+=hand[i].toString()+"\n";
        }
        System.out.println("Your cards are:");
        System.out.println("");
        System.out.print(total);
    }

    /**
     * Prints out the hand of cards the player has in hand 2 (only for splitting).
     */
    public void printHand2()
    {
        //System.out.println("\f");
        String total="";
        for(int i=0;i<size2;i++)
        {
            total+=hand2[i].toString()+"\n";
        }
        System.out.println("Your cards are:");
        System.out.println("");
        System.out.print(total);
    }

    /**
     * Prints out the up-facing card from the dealer (i.e., JOHN CENA).
     */
    public void printFirstCard()
    {
        System.out.println("The dealer's first card is a:");
        System.out.println(hand[0].toString());
        System.out.println("");
    }
    
    /**
     * Determines if two hands are equal.
     */
    public boolean equals(Hand h1, Hand h2)
    {
        boolean doesEqual=true;
        
        for(int i=0;i<h1.size;i++)
        {
            if(h1.getCard(i)!=h2.getCard(i))
            {
            doesEqual=false;
            }
        }
        return doesEqual;
    }

    /**
     * Adds a dealt card to the given hand and prints out the new hand.
     */
    public void addCard()
    {
        int count=0;
        while(count<1)
        {
            hand[size]=deck2.dealCard();
            if(hand[size].toString()!="no card")
            {
                size++;
                System.out.println("\f");
                printHand();
                count++;
            }
        }
    }

    /**
     * Adds a dealt card to the 2nd hand and prints out the new hand.
     */
    public void addCard2()
    {
        int count=0;
        while(count<1)
        {
            hand2[size]=deck2.dealCard();
            if(hand2[size].toString()!="no card")
            {
                size2++;
                System.out.println("\f");
                printHand2();
                count++;
            }
        }
    }

    /**
     * Returns the card value of a card in the hand (Ace=11).
     */
    public int getValue(int cardNum)
    {
        return hand[cardNum].getRankInteger();
    }

    /**
     * Returns the actual card at the given cardNum in the hand.
     */
    public Card getCard(int cardNum)
    {
        return hand[cardNum];
    }

    /**
     * Switches the first card in the second hand with the second card in the first hand.
     * Only used when the player calls "split."
     */
    public void changeCards()
    {
        hand2[0]=hand[1];
        hand[1]=noneCard;
        size--;
        size2++;
    }

    /**
     * Returns the minimum value of the blackjack hand (i.e., all aces are counted as 1's).
     */
    public int handSumMin()
    {
        int sum=0;
        for(int i=0;i<size;i++)
        {
            sum+=hand[i].getRankInteger();
        }
        return sum;
    }

    /**
     * Returns the minimum value of the blackjack hand #2(i.e., all aces are counted as 1's).
     */
    public int handSumMin2()
    {
        int sum=0;
        for(int i=0;i<size2;i++)
        {
            sum+=hand2[i].getRankInteger();
        }
        return sum;
    }

    /**
     * Returns the maximum value of the blackjack hand (i.e., all aces are counted as 11's).
     */
    public int handSumMax()
    {
        int sum=0;
        for(int i=0;i<size;i++)
        {
            if(hand[i].getRankInteger()!=1)
            {
                sum+=hand[i].getRankInteger(); 
            }
            else
            {
                sum+=11;
            }
        }
        return sum;
    }

    /**
     * Returns the maximum value of the blackjack hand #2 (i.e., all aces are counted as 11's).
     */
    public int handSumMax2()
    {
        int sum=0;
        for(int i=0;i<size2;i++)
        {
            if(hand[i].getRankInteger()!=1)
            {
                sum+=hand2[i].getRankInteger(); 
            }
            else
            {
                sum+=11;
            }
        }
        return sum;
    }
}
