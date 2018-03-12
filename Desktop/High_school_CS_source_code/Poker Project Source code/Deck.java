import java.util.ArrayList;
/**
 * Creates the deck of cards.
 * 
 * @author Grant Messner 
 * @version 666
 */
public class Deck
{
    ArrayList <Card> deck=new ArrayList <Card>();

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
                    deck.add(rank1-1,card);
                }
                else if(i==1) // clubs
                {
                    Card card= new Card("black","clubs",rank1,true);
                    deck.add(rank1+12,card);
                }
                else if(i==2) // diamonds
                {
                    Card card= new Card("red","diamonds",rank1,true);
                    deck.add(rank1+25,card);
                }
                else if(i==3) //hearts
                {
                    Card card= new Card("red","hearts",rank1,true);
                    deck.add(rank1+38,card);
                }
            }
        }    
    }

    /**
     * Prints out all of the cards in the deck.
     */
    public String toString()
    {
        //System.out.println("\f");
        String total="";
        for(int i=0;i<deck.size();i++)
        {
            total+=deck.get(i).toString()+"\n";
        }
        return total;
    }

    /**
     * Sorts the cards using the bogosort method :)
     */
    public void bogoSort(ArrayList <Card> d)
    {
        boolean done=false;
        while(!done)
        {
            done=true;
            shuffle2(d);
            for(int i=0;i<d.size()-1;i++)
            {
                if(d.get(i).getRankInteger()>d.get(i+1).getRankInteger())
                    done=false;
            }
        }
    }

    /**
     * Tests the bogoSort method :O
     */
    public void bogoTest()
    {
        System.out.println("\f");
        ArrayList <Card> d=new ArrayList<Card>();
        Card c1=new Card ("red","diamonds",1,true);
        Card c2=new Card ("red","diamonds",7,true);
        Card c3=new Card ("red","diamonds",3,true);
        Card c4=new Card ("red","diamonds",2,true);
        Card c5=new Card ("red","diamonds",6,true);
        Card c6=new Card ("red","diamonds",4,true);
        Card c7=new Card ("red","diamonds",5,true);
        Card c8=new Card ("red","diamonds",9,true);
        Card c9=new Card ("red","diamonds",8,true);
        Card c10=new Card ("red","diamonds",10,true);

        d.add(c1);
        d.add(c2);
        d.add(c3);
        d.add(c4);
        d.add(c5);
        d.add(c6);
        d.add(c7);
        d.add(c8);
        d.add(c9);
        d.add(c10);

        shuffle2(d);
        bogoSort(d);
        System.out.println(d);
    }

    /**
     * Sorts the deck from aces (at the beginning) to kings/queens/jacks/10's (at the end) using the
     * insertion sort method. 
     */
    public void insertionSort()
    {
        for(int i=0;i<deck.size();i++)
        {
            for(int j=i;j>0;j--)
            {
                if(deck.get(j).getRank()<=deck.get(j-1).getRank())
                {
                    Card tempCard=deck.get(j-1);
                    deck.set(j-1,deck.get(j));
                    deck.set(j,tempCard);
                }
                else
                    break;
            }
        }
    }

    /**
     * Sorts the deck from aces to kings/queens/jacks/10's using the selection method.
     */
    public void selectionSort()
    {
        for(int i=0;i<deck.size();i++)
        {
            int small=15;
            int p=0;
            Card temp=noneCard;
            for(int j=i;j<deck.size();j++)
            {
                if(deck.get(j).getRank()<small)
                {
                    small=deck.get(j).getRank();
                    p=j;
                }
            }
            temp=deck.get(i);
            deck.set(i,deck.get(p));
            deck.set(p,temp);
        }
    }

    /**
     * Tests the shuffle method and the insertion/selection sort methods.
     */
    public void print()
    {
        System.out.println("\f");
        System.out.println(deck);
        System.out.println("");
        shuffle2(deck);
        System.out.println(deck);
        System.out.println("");
        insertionSort();
        System.out.println(deck);
        System.out.println("");
        shuffle2(deck);
        selectionSort();
        System.out.println(deck);
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
     * Adds the given card to the deck.
     */
    public void addCard(Card c)
    {
        if(deck.size()<52)
        {
            deck.add(c);
        }
    }

    /**
     * Adds the given hand to the deck.
     */
    public void addHand(PokerHand p)
    {
        for(int i=0;i<p.getNumCards();i++)
            deck.add(p.get(i));
    }

    /**
     * Returns the number of cards in the deck.
     */
    public int numCards()
    {
        int count=0;
        for(int i=0;i<deck.size();i++)
        {
            if(deck.get(i).toString().equals("no card"))
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
        Card returnCard=deck.get(deck.size()-1);
        deck.remove(deck.size()-1);
        return returnCard;
    }

    /**
     * Finds the given card in the deck by sorting the deck and then checking every card in it.
     */
    public Card findCardSequential(int value, String suit)
    {
        insertionSort();
        for(int i=0;i<deck.size();i++)
        {
            if(deck.get(i).getRank()==value && deck.get(i).getSuit().equals(suit))
                return deck.get(i);
        }
        return noneCard;
    }

    /**
     * Finds the given card in the deck using the binary search method.
     */
    public Card findCardBinary(int value, String suit)
    {
        insertionSort();
        int hi=deck.size();
        int lo=0;
        int i=0;
        boolean done=false;
        boolean isClose=false;

        if(deck.get(0).getRank()==value && deck.get(i).getSuit().equals(suit))
        {
            return deck.get(0);
        }
        else if(deck.get(deck.size()-1).getRank()==value && deck.get(deck.size()-1).getSuit().equals(suit))
        {
            return deck.get(deck.size()-1);
        }

        while(!done)
        {
            if(!isClose)
            i=(hi+lo)/2;
            
            if(deck.get(i).getRank()==value && deck.get(i).getSuit().equals(suit))
            {
                done=true;
                return deck.get(i);   
            }
            else if(deck.get(i).getRank()==value)
            {
                int temp=i;
                for(i=i;i<temp+4;i++)
                {
                   if(i<deck.size())
                   {
                     if(deck.get(i).getSuit().equals(suit) && deck.get(i).getRank()==value)
                     return deck.get(i);
                   }
                   else
                   break;
                }
                i=temp;
                
                for(i=i;i>temp-4;i--)
                {
                  if(i>=0)
                   {
                     if(deck.get(i).getSuit().equals(suit) && deck.get(i).getRank()==value)
                     return deck.get(i);
                   }
                   else
                   break;
                }
                i=temp;
            }
            else if(deck.get(i).getRank()==value+1)
            {
                i--;
                isClose=true;
            }
            else if(deck.get(i).getRank()==value-1)
            {
                i++;
                isClose=true;
            }
            else if(deck.get(i).getRank()>value)
            {
                hi=i;
            }
            else
            {
                lo=i;
            }
        }
        return noneCard;
    }

    /**
     * Deals out a hand of specified length.
     */
    public PokerHand dealHand(int length)
    {
        PokerHand p=new PokerHand();
        for(int i=0;i<length;i++)
        {
            Card c=dealCard();
            p.add2(c);
        }
        return p;
    }

    /**
     * Shuffles the deck of cards.
     */
    public void shuffle()
    {
        shuffle2(deck);
    }

    /**
     * Shuffles the given Card ArrayList.
     */
    public void shuffle2(ArrayList <Card> deck)
    {
        for(int i=0;i<deck.size();i++)
        {
            int rand=getRandomNumber(deck.size());
            Card temp=deck.get(i);
            deck.set(i,deck.get(rand));
            deck.set(rand,temp);
        }
    }

    /**
     * Returns the card at the given position in deck.
     */
    public Card get(int i)
    {
        return deck.get(i);
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
