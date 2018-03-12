
/**
 * Tests all of the evaluate helper methods, tiebreaker methods, etc.. Also contains the 
 * PokerStats method that simulates the given number of trials for the given hand size.
 * 
 * @author Grant Messner
 * @version Pi
 */
public class TestClass
{
    
    /**
     * Tests the binary search algorithm for the Deck class.
     */
    public void binarySearchTest()
    {
     System.out.println("\f");
     Deck d=new Deck();
     Card c1=d.findCardBinary(1,"spades");
     Card c2=d.findCardBinary(2,"spades");
     Card c3=d.findCardBinary(3,"spades");
     Card c4=d.findCardBinary(4,"spades");
     Card c5=d.findCardBinary(5,"spades");
     Card c6=d.findCardBinary(6,"spades");
     Card c7=d.findCardBinary(7,"spades");
     Card c8=d.findCardBinary(8,"spades");
     Card c9=d.findCardBinary(9,"spades");
     Card c10=d.findCardBinary(10,"spades");
     Card c11=d.findCardBinary(11,"spades");
     Card c12=d.findCardBinary(12,"spades");
     Card c13=d.findCardBinary(13,"spades");
     System.out.println(c1);
     System.out.println(c2);
     System.out.println(c3);
     System.out.println(c4);
     System.out.println(c5);
     System.out.println(c6);
     System.out.println(c7);
     System.out.println(c8);
     System.out.println(c9);
     System.out.println(c10);
     System.out.println(c11);
     System.out.println(c12);
     System.out.println(c13);
    }
    
    /**
     * Tests the sequential search algorithm for the Deck class.
     */
    public void sequentialSearchTest()
    {
     System.out.println("\f");
     Deck d=new Deck();
     Card c=d.findCardSequential(5,"spades");
     System.out.println(c);
    }

    /**
     * Tests the Royal Flush method within the PokerHand class.
     */
    public void royalFlushTest()
    {
        System.out.println("\f");
        Card c1=new Card ("red","diamonds",10,true);
        Card c2=new Card ("red","diamonds",11,true);
        Card c3=new Card ("red","diamonds",12,true);
        Card c4=new Card ("red","diamonds",13,true);
        Card c5=new Card ("red","diamonds",1,true);
        Card c6=new Card ("red","diamonds",4,true);
        Card c7=new Card ("red","diamonds",5,true);
        PokerHand p=new PokerHand();
        p.add(0,c1);
        p.add(1,c2);
        p.add(2,c3);
        p.add(3,c4);
        p.add(4,c5);
        p.add(5,c6);
        p.add(6,c7);

        if(p.evaluate().equals("Royal Flush"))
            System.out.println("true");
        else
            System.out.println("false");

        System.out.println("");
        p.set(0,c7);
        if(p.evaluate().equals("Royal Flush"))
            System.out.println("true");
        else
            System.out.println("false");
    }

    /**
     * Tests the Straight Flush method within the PokerHand class.
     */
    public void straightFlushTest()
    {
        System.out.println("\f");
        Card c1=new Card ("red","diamonds",3,true);
        Card c2=new Card ("red","diamonds",4,true);
        Card c3=new Card ("red","diamonds",5,true);
        Card c4=new Card ("red","diamonds",13,true);
        Card c5=new Card ("red","diamonds",1,true);
        Card c6=new Card ("red","diamonds",2,true);
        Card c7=new Card ("red","diamonds",10,true);
        Card c8=new Card ("red","spades",3,true);
        Card c9=new Card ("red","hearts",3,true);
        PokerHand p=new PokerHand();
        p.add(0,c1);
        p.add(1,c2);
        p.add(2,c3);
        p.add(3,c4);
        p.add(4,c5);
        p.add(5,c6);
        p.add(6,c7);

        System.out.println(p.evaluate());
        System.out.println(p);
        System.out.println("");
        p.set(0,c7);

        System.out.println(p.evaluate());
        System.out.println(p);

        System.out.println("");
        p.set(0,c1);
        p.set(1,c2);
        p.set(2,c3);
        p.set(3,c8);
        p.set(4,c5);
        p.set(5,c6);
        p.set(6,c7);
        System.out.println(p);
        System.out.println(p.evaluate());
        
        System.out.println("");
        p.set(6,c9);
        System.out.println(p);
        System.out.println(p.evaluate());
    }

    /**
     * Tests the Four of a Kind method within the PokerHand class.
     */
    public void fourOfAKindTest()
    {
        System.out.println("\f");
        Card c1=new Card ("red","diamonds",4,true);
        Card c2=new Card ("red","spades",4,true);
        Card c3=new Card ("red","hearts",12,true);
        Card c4=new Card ("red","diamonds",4,true);
        Card c5=new Card ("red","diamonds",1,true);
        Card c6=new Card ("red","clubs",4,true);
        Card c7=new Card ("red","diamonds",5,true);
        PokerHand p=new PokerHand();
        p.add(0,c1);
        p.add(1,c2);
        p.add(2,c3);
        p.add(3,c4);
        p.add(4,c5);
        p.add(5,c6);
        p.add(6,c7);

        if(p.evaluate().equals("Four of a Kind"))
            System.out.println("true");
        else
            System.out.println("false");

        System.out.println("");
        p.set(1,c7);
        if(p.evaluate().equals("Four of a Kind"))
            System.out.println("true");
        else
            System.out.println("false");

    }

    /**
     * Tests the Full House method within the PokerHand class.
     */
    public void fullHouseTest()
    {
        System.out.println("\f");
        Card c1=new Card ("red","diamonds",10,true);
        Card c2=new Card ("red","spades",10,true);
        Card c3=new Card ("red","spades",10,true);
        Card c4=new Card ("red","clubs",13,true);
        Card c5=new Card ("red","hearts",2,true);
        Card c6=new Card ("red","hearts",2,true);
        Card c7=new Card ("red","clubs",5,true);
        PokerHand p=new PokerHand();
        p.add(0,c1);
        p.add(1,c2);
        p.add(2,c3);
        p.add(3,c4);
        p.add(4,c5);
        p.add(5,c6);
        p.add(6,c7);

        System.out.println(p.evaluate());
        System.out.println("");
        p.remove(0);
        System.out.println(p.evaluate());
        System.out.println("");
        p.set(0,c1);
        p.set(1,c2);
        p.set(2,c3);
        p.set(3,c4);
        p.set(4,c5);
        p.set(5,c6);
        p.set(6,c6);
        System.out.println(p.evaluate());
    }

    /**
     * Tests the Flush method within the PokerHand class.
     */
    public void flushTest()
    {
        System.out.println("\f");
        Card c1=new Card ("red","diamonds",2,true);
        Card c2=new Card ("red","diamonds",4,true);
        Card c3=new Card ("red","hearts",6,true);
        Card c4=new Card ("red","diamonds",8,true);
        Card c5=new Card ("red","diamonds",10,true);
        Card c6=new Card ("red","diamonds",12,true);
        Card c7=new Card ("red","spades",1,true);
        PokerHand p=new PokerHand();
        p.add(0,c1);
        p.add(1,c2);
        p.add(2,c3);
        p.add(3,c4);
        p.add(4,c5);
        p.add(5,c6);
        p.add(6,c7);

        System.out.println(p.evaluate());
        System.out.println("");
        p.set(1,c7);
        System.out.print(p.evaluate());

    }

    /**
     * Tests the Straight method within the PokerHand class.
     */
    public void straightTest()
    {
        System.out.println("\f");
        Card c1=new Card ("red","spades",3,true);
        Card c2=new Card ("red","diamonds",4,true);
        Card c3=new Card ("red","clubs",5,true);
        Card c4=new Card ("red","hearts",13,true);
        Card c5=new Card ("red","spades",1,true);
        Card c6=new Card ("red","clubs",2,true);
        Card c7=new Card ("red","hearts",10,true);
        Card c8=new Card ("red","hearts",11,true);
        Card c9=new Card ("red","hearts",12,true);
        Card c10=new Card ("red","hearts",13,true);
        Card c11=new Card ("red","clubs",1,true);
        PokerHand p=new PokerHand();
        p.add(0,c1);
        p.add(1,c2);
        p.add(2,c3); 
        p.add(3,c4); 
        p.add(4,c5); 
        p.add(5,c6); 
        p.add(6,c7);

        System.out.println(p.evaluate());
        System.out.println(p);
        System.out.println("");
        p.set(0,c7);
        System.out.println(p.evaluate());
        System.out.println(p);
        System.out.println("");
        p.set(0,c1);
        p.set(1,c2);
        p.set(2,c3);
        p.set(3,c1);
        p.set(4,c5);
        p.set(5,c6);
        p.set(6,c7);
        System.out.println(p.evaluate());
        System.out.println(p);
        System.out.println("");
        p.set(0,c1);
        p.set(1,c2);
        p.set(2,c7);
        p.set(3,c8);
        p.set(4,c9);
        p.set(5,c10);
        p.set(6,c11);
        System.out.println(p.evaluate());
        System.out.println(p);
    }

    /**
     * Tests the Three of a Kind method within the PokerHand class.
     */
    public void threeOfAKindTest()
    {
        System.out.println("\f");
        Card c1=new Card ("red","diamonds",10,true);
        Card c2=new Card ("red","spades",10,true);
        Card c3=new Card ("red","spades",10,true);
        Card c4=new Card ("red","clubs",13,true);
        Card c5=new Card ("red","hearts",1,true);
        Card c6=new Card ("red","hearts",2,true);
        Card c7=new Card ("red","clubs",5,true);
        PokerHand p=new PokerHand();
        p.add(0,c1);
        p.add(1,c2);
        p.add(2,c3);
        p.add(3,c4);
        p.add(4,c5);
        p.add(5,c6);
        p.add(6,c7);

        System.out.println(p.evaluate());
        System.out.println("");
        System.out.println(p);
        System.out.println("");
        p.remove(0);
        System.out.println(p.evaluate());
        System.out.println("");
        System.out.println(p);
        System.out.println("");
        p.set(0,c1);
        p.set(1,c1);
        p.set(2,c6);
        p.set(3,c6);
        p.set(4,c5);
        p.set(5,c5);
        p.set(6,c7);
        System.out.println(p.evaluate());
        System.out.println(p);
    }

    /**
     * Tests the pair and twoPair methods within the PokerHand class.
     */
    public void pairTwoPairTest()
    {
        System.out.println("\f");
        Card c1=new Card ("red","diamonds",5,true);
        Card c2=new Card ("red","spades",10,true);
        Card c3=new Card ("red","spades",10,true);
        Card c4=new Card ("red","clubs",13,true);
        Card c5=new Card ("red","hearts",2,true);
        Card c6=new Card ("red","hearts",2,true);
        Card c7=new Card ("red","clubs",6,true);
        PokerHand p=new PokerHand();
        p.add(0,c1);
        p.add(1,c2);
        p.add(2,c3);
        p.add(3,c4);
        p.add(4,c5);
        p.add(5,c6);
        p.add(6,c7);

        System.out.println(p.evaluate());
        System.out.println("");
        p.remove(0);
        System.out.println(p.evaluate());
        System.out.println("");
        p.set(0,c1);
        p.set(1,c2);
        p.set(2,c3);
        p.set(3,c4);
        p.set(4,c5);
        p.set(5,c6);
        System.out.println(p.evaluate());
    }

    /**
     * Tests the deck's ability to deal cards.
     */
    public void dealTest()
    {
        System.out.println("\f");
        Deck d=new Deck();
        d.shuffle();
        PokerHand p=d.dealHand(7);
        System.out.println(p);
    }

    /**
     * Tests the High Card tiebreaker method within the SimulateGame class.
     */
    public void highCardTest()
    {
        System.out.println("\f");
        SimulateGame s=new SimulateGame();
        Card c1=new Card ("red","diamonds",2,true);
        Card c2=new Card ("red","spades",4,true);
        Card c3=new Card ("red","spades",6,true);
        Card c4=new Card ("red","clubs",8,true);
        Card c5=new Card ("red","hearts",10,true);
        Card c6=new Card ("red","hearts",12,true);
        Card c7=new Card ("red","clubs",7,true);
        Card c8=new Card("red","spades",13,true);
        PokerHand p1=new PokerHand();
        p1.add(0,c2);
        p1.add(1,c2);
        p1.add(2,c3);
        p1.add(3,c4);
        p1.add(4,c5);
        p1.add(5,c6);
        p1.add(6,c7);
        
        PokerHand p2=new PokerHand();
        p2.add(0,c1);
        p2.add(1,c2);
        p2.add(2,c3);
        p2.add(3,c4);
        p2.add(4,c5);
        p2.add(5,c6);
        p2.add(6,c7);
        
        System.out.println(s.highCard(p1,p2));
        System.out.println("");
        System.out.print(p1);
        System.out.println("");
        System.out.print(p2);
    }
    
    /**
     * Tests the High Pair tiebreaker method within the SimulateGame class.
     */
    public void highPairTest()
    {
        System.out.println("\f");
        SimulateGame s=new SimulateGame();
        Card c1=new Card ("red","diamonds",2,true);
        Card c2=new Card ("red","spades",4,true);
        Card c3=new Card ("red","spades",6,true);
        Card c4=new Card ("red","clubs",8,true);
        Card c5=new Card ("red","hearts",10,true);
        Card c6=new Card ("red","hearts",12,true);
        Card c7=new Card ("red","clubs",7,true);
        Card c8=new Card("red","spades",13,true);
        PokerHand p1=new PokerHand();
        p1.add(0,c2);
        p1.add(1,c2);
        p1.add(2,c3);
        p1.add(3,c4);
        p1.add(4,c5);
        p1.add(5,c6);
        p1.add(6,c7);
        
        PokerHand p2=new PokerHand();
        p2.add(0,c1);
        p2.add(1,c2);
        p2.add(2,c3);
        p2.add(3,c4);
        p2.add(4,c5);
        p2.add(5,c6);
        p2.add(6,c6);
        
        System.out.println(s.highPair(p1,p2));
        System.out.println("");
        System.out.print(p1);
        System.out.println("");
        System.out.print(p2);
    }
    
    /**
     * Tests the High Pair tiebreaker method within the SimulateGame class. Assumes the tie is a Two Pair.
     */
    public void highTwoPairTest()
    {
        System.out.println("\f");
        SimulateGame s=new SimulateGame();
        Card c1=new Card ("red","diamonds",2,true);
        Card c2=new Card ("red","spades",4,true);
        Card c3=new Card ("red","spades",6,true);
        Card c4=new Card ("red","clubs",8,true);
        Card c5=new Card ("red","hearts",10,true);
        Card c6=new Card ("red","hearts",12,true);
        Card c7=new Card ("red","clubs",7,true);
        Card c8=new Card("red","spades",13,true);
        PokerHand p1=new PokerHand();
        p1.add(0,c2);
        p1.add(1,c2);
        p1.add(2,c3);
        p1.add(3,c4);
        p1.add(4,c4);
        p1.add(5,c6);
        p1.add(6,c6);
        
        PokerHand p2=new PokerHand();
        p2.add(0,c1);
        p2.add(1,c3);
        p2.add(2,c3);
        p2.add(3,c4);
        p2.add(4,c5);
        p2.add(5,c6);
        p2.add(6,c6);
        
        System.out.println(s.highPair(p1,p2));
        System.out.println("");
        System.out.print(p1);
        System.out.println("");
        System.out.print(p2);
    }
    
    /**
     * Tests the High 3 card tiebreaker method within the SimulateGame class.
     */
    public void highTripleTest()
    {
        System.out.println("\f");
        SimulateGame s=new SimulateGame();
        Card c1=new Card ("red","diamonds",2,true);
        Card c2=new Card ("red","spades",4,true);
        Card c3=new Card ("red","spades",6,true);
        Card c4=new Card ("red","clubs",8,true);
        Card c5=new Card ("red","hearts",10,true);
        Card c6=new Card ("red","hearts",12,true);
        Card c7=new Card ("red","clubs",7,true);
        Card c8=new Card("red","spades",13,true);
        PokerHand p1=new PokerHand();
        p1.add(0,c2);
        p1.add(1,c2);
        p1.add(2,c3);
        p1.add(3,c4);
        p1.add(4,c6);
        p1.add(5,c6);
        p1.add(6,c6);
        
        PokerHand p2=new PokerHand();
        p2.add(0,c1);
        p2.add(1,c3);
        p2.add(2,c3);
        p2.add(3,c3);
        p2.add(4,c5);
        p2.add(5,c6);
        p2.add(6,c6);
        
        System.out.println(s.highTriple(p1,p2));
        System.out.println("");
        System.out.print(p1);
        System.out.println("");
        System.out.print(p2);
        
        
        p2.set(4,c6);
        System.out.println("");
        System.out.println(s.highTriple(p1,p2));
        System.out.println("");
        System.out.print(p1);
        System.out.println("");
        System.out.print(p2);
    }

    /**
     * Calculates the probabilities of having each hand for a 5 card hand size.
     */
    public void PokerStats(int handSize, int numTrials)
    {
        double startTime = System.currentTimeMillis();
        System.out.println("\f");
        Deck d=new Deck();
        d.shuffle();
        double royalFlushCount=0.0;
        double straightFlushCount=0.0;
        double fourOfAKindCount=0.0;
        double fullHouseCount=0.0;
        double flushCount=0.0;
        double straightCount=0.0;
        double threeOfAKindCount=0.0;
        double twoPairCount=0.0;
        double pairCount=0.0;
        double bustCount=0.0;
        double n=numTrials;

        for(int i=0;i<n;i++)
        {
            PokerHand p=d.dealHand(handSize);
            String s=p.evaluate();

            if(s.equals("Junk"))
            {
                bustCount++;   
            }
            else if(s.equals("Pair"))
            {
                pairCount++;   
            }
            else if(s.equals("Two Pair"))
            {
                twoPairCount++;  
            }
            else if(s.equals("Three of a Kind"))
            {
                threeOfAKindCount++; 
            }
            else if(s.equals("Straight"))
            {
                straightCount++;   
            }
            else if(s.equals("Flush"))
            {
                flushCount++;   
            }
            else if(s.equals("Full House"))
            {
                fullHouseCount++;  
            }
            else if(s.equals("Four of a Kind"))
            {
                fourOfAKindCount++;  
            }
            else if(s.equals("Straight Flush"))
            {
                straightFlushCount++; 
            }
            else if(s.equals("Royal Flush"))
            {
                royalFlushCount++; 
            }
            
            d.addHand(p);
            d.shuffle();
        }
        System.out.println("\f");
        System.out.println("      Hand         "+"Statistic");
        System.out.println("_______________________________");
        System.out.println("Royal Flush:     | "+royalFlushCount/n);
        System.out.println("Straight Flush:  | "+straightFlushCount/n);
        System.out.println("Four of a Kind:  | "+fourOfAKindCount/n);
        System.out.println("Full House:      | "+fullHouseCount/n);
        System.out.println("Flush:           | "+flushCount/n);
        System.out.println("Straight:        | "+straightCount/n);
        System.out.println("Three of a Kind: | "+threeOfAKindCount/n);
        System.out.println("Two Pair:        | "+twoPairCount/n);
        System.out.println("Pair:            | "+pairCount/n);
        System.out.println("Junk:            | "+bustCount/n);
        System.out.println("");
        
        double sum=royalFlushCount/n+straightFlushCount/n+fourOfAKindCount/n+fullHouseCount/n
        +flushCount/n+straightCount/n+threeOfAKindCount/n+twoPairCount/n+pairCount/n+bustCount/n;
        
        System.out.println("Sum of probabilities: "+sum);
        double elapsedTime = System.currentTimeMillis() - startTime;
        double elapsedSeconds = elapsedTime / 1000;
        System.out.println("");
        System.out.println("Elapsed time: "+elapsedSeconds+" seconds");
    }
}
