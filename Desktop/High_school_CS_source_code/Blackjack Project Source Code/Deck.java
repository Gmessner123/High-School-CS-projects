
/**
 * Creates the deck of cards.
 * 
 * @author Grant Messner 
 * @version 1
 */
public class Deck
{
    Card[] deck=new Card[52];
    Card[] deck2=new Card[deck.length];

    Card noneCard=new Card("none", "diamonds", 5, false);
    /**
     * Constructor for objects of class Deck.
     */
    public Deck()
    {
        makeCards();
    }

    /**
     * Creates the 52 card deck.
     */
    public void makeCards()
    {
        for(int i=0;i<4;i++)
        {
            for(int rank1=1;rank1<=13;rank1++)
            {
                if(i==0) //spades
                {
                    Card card= new Card("black","spades",rank1,true);
                    deck[rank1-1]=card;
                }
                else if(i==1) // clubs
                {
                    Card card= new Card("black","clubs",rank1,true);
                    deck[rank1+12]=card;
                }
                else if(i==2) // diamonds
                {
                    Card card= new Card("red","diamonds",rank1,true);
                    deck[rank1+25]=card;
                }
                else if(i==3) //hearts
                {
                    Card card= new Card("red","hearts",rank1,true);
                    deck[rank1+38]=card;
                }
            }
        }    
    }

    /**
     * Prints out all of the cards in the deck.
     */
    public String toString()
    {
        System.out.println("\f");
        String total="";
        for(int i=0;i<deck.length;i++)
        {
            total+=deck[i].toString()+"\n";
        }
        return total;
    }

    /**
     * True if the deck is empty. False otherwise.
     */
    public boolean empty()
    {
        if(numCards()==0)
            return true;
        else
            return false;
    }

    /**
     * Returns the number of cards in the deck.
     */
    public int numCards()
    {
        int count=0;
        for(int i=0;i<deck.length;i++)
        {
            if(deck[i].toString().equals("no card"))
                ;
            else
                count++;
        }
        return count;
    }

    /**
     * Deals the first card at the top of the deck.
     */
    public Card dealCard()
    {
        int j=0;
        Card returnCard=noneCard;
        while(deck[j].toString().equals("no card"))
        {
        j++;
        }
        returnCard=deck[j];
        deck[j]=noneCard;
        return returnCard;
    }

    /**
     * Shuffles the deck of cards
     */
    public void shuffle()
    {
        System.out.println("\f");
        String usedNumbers="";
        int i=0;

        while(i<deck.length)
        {
            int rand=getRandomNumber(deck.length);
            if(usedNumbers.indexOf(rand+"-")==-1||usedNumbers.indexOf("0"+rand+"-")==-1)
            {
                deck2[i]=deck[rand];
                if(rand>9)
                    usedNumbers=usedNumbers+rand+"-";
                else
                {
                    usedNumbers=usedNumbers+"0"+rand+"-";
                }
                i++;
            }
        }
        deck=deck2;
    }

    /**
     * returns a random number from 0 to high-1
     * i.e. getRandomNumber(10) would return a random number from 0 to 9.
     */
    private int getRandomNumber(int high)
    {
        java.util.Random rand = new java.util.Random();
        return rand.nextInt(high);
    }
}
