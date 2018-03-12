import java.util.ArrayList;
/**
 *This class creates PokerHand objects, which are essentially ArrayLists of Card ojects with
 *special properties (i.e., they can be evaluated, sorted, etc.).
 * 
 * @author John Cena 
 * @version e
 */
public class PokerHand
{
    ArrayList <Card> hand=new ArrayList<Card>();
    final int MAX_CARDS=10;
     int numCards;
    Card noneCard=new Card ("red","diamonds",5,true);
    
    /**
     * Constructor for objects of class PokerHand
     */
    public PokerHand()
    {
        numCards=0;
    }

    /**
     * Adds the given card to the PokerHand ArrayList.
     */
    public void add2(Card c)
    {
        if(numCards+1<=MAX_CARDS)
        {hand.add(c);
         numCards++;
        }
    }
    
    /**
     * Returns the # of cards in the given hand.
     */
    public int getNumCards()
    {
     return numCards;
    }
    
    /**
     * Adds the given card to the given index in the PokerHand ArrayList.
     */
    public void add(int i, Card c)
    {
        if(numCards+1<=MAX_CARDS)
        {hand.add(i,c);
            numCards++;
        }
    }
    
    /**
     * Returns the card at the given index in the PokerHand ArrayList.
     */
    public Card get(int i)
    {
     return hand.get(i);
    }

    /**
     * Removes the given card to the given index in the ArrayList.
     */

    public void remove(int i)
    {
        if(numCards-1>=5)
        {hand.remove(i);
            numCards--;
        }
    }

    /**
     * The toString method for the PokerHand class.
     */
    public String toString()
    {
        String handString="";
        for (int i=0;i<hand.size();i++)
            handString=handString+hand.get(i)+"\n";
        return handString;
    }

    /**
     * Sets the given card to the given index in the hand
     */
    public void set(int i, Card c)
    {
        if(i<numCards)
            hand.set(i,c);
    }

    /**
     * Returns the result of the given the hand. Checks best hands first, then works its way down to the
     * worst hands.
     */
    public String evaluate()
    {
        if(royalFlush("spades") || royalFlush("hearts") || royalFlush("diamonds") || royalFlush("clubs"))
            return "Royal Flush";
        else if(straightFlush("spades")>0 || straightFlush("hearts")>0 || straightFlush("diamonds")>0 || straightFlush("clubs")>0)
            return "Straight Flush";
        else if(fourOfAKind())
            return "Four of a Kind";
        else if(pairCount()>=1 && tripleCount()>=1)
            return "Full House";
        else if(flush("spades") ||flush("hearts") ||flush("diamonds") ||flush("clubs"))
            return "Flush";
        else if(straight()>0 || straight2()>0)
            return "Straight";
        else if(tripleCount()>=1)
            return "Three of a Kind";
        else if(pairCount()>=2)
            return "Two Pair";
        else if(pairCount()==1)
            return "Pair";
        else
            return "Junk";
    }
    
    /**
     * Returns a number corresponding to how good the given hand is (10=best, 1=worst).
     */
    public int handRanking(PokerHand p)
    {
     if(p.evaluate().equals("Royal Flush"))
     return 10;
     else if(p.evaluate().equals("Straight Flush"))
     return 9;
     else if(p.evaluate().equals("Four of a Kind"))
     return 8;
     else if(p.evaluate().equals("Full House"))
     return 7;
     else if(p.evaluate().equals("Flush"))
     return 6;
     else if(p.evaluate().equals("Straight"))
     return 5;
     else if(p.evaluate().equals("Three of a Kind"))
     return 4;
     else if(p.evaluate().equals("Two Pair"))
     return 3;
     else if(p.evaluate().equals("Pair"))
     return 2;
     else 
     return 1;
    }

    /**
     * Returns true if the player has a royal flush, false otherwise. Has booleans corresponding to each card in the
     * Royal Flush (10, Jack, Queen, etc.) to make sure that it counts each card only once. If it counts 5 of the cards 
     * in the Royal Flush, it returns true.Ran four times for each suit to see if any have Royal Flushes.
     */
    public boolean royalFlush(String suit)
    {
        int count=0;
        boolean already10=false;
        boolean alreadyJack=false;
        boolean alreadyQueen=false;
        boolean alreadyKing=false;
        boolean alreadyAce=false;

        for(int i=0;i<hand.size();i++)
        {
            if(hand.get(i).getRank()==10 && !already10 && hand.get(i).getSuit().equals(suit))
            {
                already10=true;
                count++;
            }

            if(hand.get(i).getRank()==11 && !alreadyJack && hand.get(i).getSuit().equals(suit))
            {
                alreadyJack=true;
                count++;
            }

            if(hand.get(i).getRank()==12 && !alreadyQueen && hand.get(i).getSuit().equals(suit))
            {
                alreadyQueen=true;
                count++;
            }

            if(hand.get(i).getRank()==13 && !alreadyKing && hand.get(i).getSuit().equals(suit))
            {
                alreadyKing=true;
                count++;
            }

            if(hand.get(i).getRank()==1 && !alreadyAce && hand.get(i).getSuit().equals(suit))
            {
                alreadyAce=true;
                count++;
            }
        }

        if(count==5)
            return true;
        else
            return false;
    }
    
    
    
    
    /**
     * Returns true if the player has a straight flush, false otherwise. The first if statement works exactly 
     * as the "straight()" method does below, but the other if statements are to account for the fact that the
     * cards can be neumerically in line but the suits may not match up. 
     */
    public int straightFlush(String Suit)
    {
        selectionSort(hand);
        int count=0;
        int cardNum=0;
        for(int i=0;i<numCards-1;i++)
        {
            if(hand.get(i).getRank()==hand.get(i+1).getRank()-1 && hand.get(i).getSuit().equals(Suit) && hand.get(i+1).getSuit().equals(Suit))
            {
              count++;
              if(count==4)
               cardNum=hand.get(i+1).getRank();
            }
            else if(i<numCards-2 && hand.get(i).getRank()==hand.get(i+2).getRank()-1 && hand.get(i).getSuit().equals(Suit) && hand.get(i+2).getSuit().equals(Suit))
            {
              i++;
              count++;
              if(count==4)
               cardNum=hand.get(i+1).getRank();
            }
            else if(i<numCards-3 && hand.get(i).getRank()==hand.get(i+3).getRank()-1 && hand.get(i).getSuit().equals(Suit) && hand.get(i+3).getSuit().equals(Suit))
            {
              i=i+2;
              count++;
              if(count==4)
               cardNum=hand.get(i+1).getRank();
            }
            else if(i<numCards-4 && hand.get(i).getRank()==hand.get(i+4).getRank()-1 && hand.get(i).getSuit().equals(Suit) && hand.get(i+4).getSuit().equals(Suit))
            {
              i=i+3;
              count++;
              if(count==4)
               cardNum=hand.get(i+1).getRank();
            }
            
            else if(hand.get(i).getRank()!=hand.get(i+1).getRank() && count<4)
            count=0;
        }
        
        return cardNum;
    }
    

    /**
     * Returns true if the hand has a 4 of a kind, false otherwise. Goes through and counts the # of cards at each rank,
     * returning true if there are 4 cards of a given rank.
     */
    public boolean fourOfAKind()
    {
        boolean is4=false;
        for(int rank=1;rank<=13;rank++)
        {
            int count=0;
            for(int i=0;i<numCards;i++)
            {
                if(hand.get(i).getRank()==rank)
                    count++;
            }
            if(count==4)
                is4=true;
        }
        return is4;
    }

    /**
     * Returns true if there is a flush in the given suit, false otherwise. Counts the # of cards at the 
     * given suit, and if the count ever is greater than or equal to 5, returns true. Ran 4 times for 
     * each of the 4 suits.
     */
    public boolean flush(String suit)
    {
        boolean isFlush=false;
        int count=0;
        for(int i=0;i<hand.size();i++)
        {
            if(hand.get(i).getSuit().equals(suit))
                count++;
            if(count>=5)
                isFlush=true;
        }
        return isFlush;
    }

    /**
     * Returns the highest number card if there is a straight, 0 otherwise. Checks each card in the hand (once they have been
     * sorted) to see if 5 consecutive cards are sorted in order, ignoring cards that are the same rank in this 
     * count.
     */
    public int straight()  
    {
        selectionSort(hand);
        int count=0;
        int cardNum=0;
        for(int i=0;i<numCards-1;i++)
        {
            if(hand.get(i).getRank()==hand.get(i+1).getRank()-1)
            {
              count++;
              if(count==4)
              cardNum=hand.get(i+1).getRank();
            }
            else if(hand.get(i).getRank()!=hand.get(i+1).getRank() && count<4)
            count=0;
        }
        return cardNum;
    }

    /**
     * Returns 14 if there is an ace high straight, 0 otherwise. This method works very much like the 
     * Royal Flush method does as it checks for an ace high straight, but the cards don't need to all be of
     * the same suit.
     */
    public int straight2()
    {
         int count=0;
        boolean already10=false;
        boolean alreadyJack=false;
        boolean alreadyQueen=false;
        boolean alreadyKing=false;
        boolean alreadyAce=false;

        for(int i=0;i<hand.size();i++)
        {
            if(hand.get(i).getRank()==10 && !already10 )
            {
                already10=true;
                count++;
            }

            if(hand.get(i).getRank()==11 && !alreadyJack )
            {
                alreadyJack=true;
                count++;
            }

            if(hand.get(i).getRank()==12 && !alreadyQueen )
            {
                alreadyQueen=true;
                count++;
            }

            if(hand.get(i).getRank()==13 && !alreadyKing )
            {
                alreadyKing=true;
                count++;
            }

            if(hand.get(i).getRank()==1 && !alreadyAce )
            {
                alreadyAce=true;
                count++;
            }
        }

        if(count==5)
            return 14;
        else
            return 0;
    }

    /**
     * Returns the # of triples in the given hand. Counts the total # of triples from each rank of cards
     * and returns that number.
     */
    public int tripleCount()
    {
        int Count=0; //counts the # of triples overall
        for(int rank=1;rank<=13;rank++)
        {
            int count=0; //counts the # of cards that match the given rank
            for(int i=0;i<numCards;i++)
            {
                if(hand.get(i).getRank()==rank)
                    count++;
            }
            if(count==3)
                Count++;
        }
        return Count;
    }

    /**
     * Returns the # of pairs in the given hand. Works similar to the "tripleCount()" method, but 
     * instead counts the # of pairs.
     */
    public int pairCount()
    {
        int Count=0; //counts the # of pairs overall
        for(int rank=1;rank<=13;rank++)
        {
            int count=0; //counts the # of cards that match the given rank
            for(int i=0;i<numCards;i++)
            {
                if(hand.get(i).getRank()==rank)
                    count++;
            }
            if(count==2)
                Count++;
        }
        return Count;
    }

    /**
     * Sorts the hand from aces to kings/queens/jacks/10's using the selection method.
     */
    public void selectionSort(ArrayList <Card> hand)
    {
        for(int i=0;i<hand.size();i++)
        {
            int small=15;
            int p=0;
            Card temp=noneCard;
            for(int j=i;j<hand.size();j++)
            {
                if(hand.get(j).getRank()<small)
                {
                    small=hand.get(j).getRank();
                    p=j;
                }
            }
            temp=hand.get(i);
            hand.set(i,hand.get(p));
            hand.set(p,temp);
        }
    }

    /**
     * Sorts the hand from 2's to aces using the selection method.
     */
    public void selectionSort2(ArrayList <Card> hand)
    {
        for(int i=0;i<hand.size();i++)
        {
            int small=15;
            int p=0;
            Card temp=noneCard;
            for(int j=i;j<hand.size();j++)
            {
                if(hand.get(j).getRank2()<small)
                {
                    small=hand.get(j).getRank2();
                    p=j;
                }
            }
            temp=hand.get(i);
            hand.set(i,hand.get(p));
            hand.set(p,temp);
        }
    }
}