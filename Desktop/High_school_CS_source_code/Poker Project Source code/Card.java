
/**
 * Constructs card objects with things like suit, color, rank, etc..
 * 
 * @author Grant Messner 
 * @version 1
 */
public class Card
{
    String color;
    String suit;
    int rank;
    boolean face;
    
    
    String red,black;
    String spades,clubs,hearts,diamonds;
    

    /**
     * Constructor for objects of class Cards.
     */
    public Card(String Color,String Suit, int Rank, boolean Face)
    {
       color=Color;
       suit=Suit;
       rank=Rank;
       face=Face;
    }

    /**
     * Flips the given card over (i.e., if the card is face up, it will be face down, and vice versa).
     */
    public void flip()
    {
       if(face==true)
        face=false;
       else
       face=true;
    }
    
    /**
     * Returns the card's color, rank, suit and whether it is facing up/down.
     */
    public String toString()
    {
       if(getColor()!="none")
       return getRankString()+" of "+getSuit();
       else
       return "no card";
    }
    
    /**
     * Returns true if the two cards are the same, false otherwise.
     */
    public boolean equals(Card c1, Card c2)
    {
    if(c1.toString().equals(c2.toString()))
    {
    return true;
    }
    else
    return false;
    }
    
    /**
     * Returns the color of the card.
     */
    public String getColor()
    {
     return color;
    }
    
    /**
     * Returns the suit of the card.
     */
    public String getSuit()
    {
     return suit;
    }
    
    /**
     * Returns the integer value of the card's rank (aces are considered ones).
     */
    public int getRankInteger()
    {
       if(rank<11)
       return rank;
       else
       return 10;
    }
    
    /**
     * Returns the rank of the card with aces being considered 1's.
     */
    public int getRank2()
    {
    if(rank==1)
    return 14;
    else
    return rank;
    }
    
    /**
     * Returns the rank of the card.
     */
    public int getRank()
    {
       return rank;
    }
    
    /**
     * Returns the string version of the Card's rank.
     */
    public String getRankString()
    {
    if(rank==1)
     return "ace";
     else if(rank==2)
     return "2";
     else if(rank==3)
     return "3";
     else if(rank==4)
     return "4";
     else if(rank==5)
     return "5";
     else if(rank==6)
     return "6";
     else if(rank==7)
     return "7";
     else if(rank==8)
     return "8";
     else if(rank==9)
     return "9";
     else if(rank==10)
     return "10";
     else if(rank==11)
     return "jack";
     else if(rank==12)
     return "queen";
     else if(rank==13)
     return "king";
     else
     return "something is wrong";
    }
    
    /**
     * Returns whether the card is facing up (true) or down (false).
     */
    public String getFace()
    {
     if(face==true)
     return "facing up";
     else
     return "facing down";
    }
}
