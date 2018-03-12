


/**
 * The test class for the hand, deck, and card classes.
 *
 * @author  Grant Messner
 * @version 1
 */
public class Test
{
    String red,black;
    String spades,clubs,hearts,diamonds;
    String color,Color;
    String suit,Suit;
    
    Card c1=new Card("red","hearts",3,true);
    Card c2=new Card("black", "diamonds",5,false);
    Card c3=new Card("none", "diamonds", 5, false);
    Deck deck=new Deck();
    Hand hand=new Hand();
    //Deck deck2=new Deck();
    
    /**
     * Tests objects of class card.
     */
    public void cardTest()
    {
      System.out.println("\f");
      System.out.println(c1);
      System.out.println(c2); 
      System.out.println(c3);
      
      if(c1.equals(c1))
      System.out.println("True");
      else
      System.out.println("False");
    }
    
    /**
     * Tests objects of class deck.
     */
    public void deckTest()
    {
    System.out.println("\f");
    deck.shuffle();
    System.out.println(deck);
    System.out.println(deck.numCards());
    }

    /**
     * Tests the ability of the deck to deal a card.
     */
    public void dealTest()
    {
    System.out.println("\f");
    deck.shuffle();
    System.out.println(deck.dealCard());
    System.out.println(deck.dealCard());
    System.out.println(deck.dealCard());
    System.out.println(deck.dealCard());
    System.out.println(deck.dealCard());
    System.out.println(deck.dealCard());
    System.out.println(deck.dealCard());
    System.out.println(deck.dealCard());
    }
    
    /**
     * Tests objects of class hand.
     */
    public void handTest()
    {
      System.out.println("\f");  
      deck.shuffle();
      hand.addCard();
    }
}

