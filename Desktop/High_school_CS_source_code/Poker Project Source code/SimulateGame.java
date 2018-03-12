import java.util.ArrayList;
/**
 * Simulates a game between two poker hands. Also contains the tiebreaker methods.
 * 
 * @author Steve Ballmer
 * @version 2.718281828459045
 */
public class SimulateGame
{
    PokerHand p1=new PokerHand();
    PokerHand p2=new PokerHand();
    Deck d=new Deck();

    /**
     * Simulates a hand of poker between two players.
     */
    public void simulate(int handSize)
    {
        d.shuffle();
        p1=d.dealHand(handSize);
        p2=d.dealHand(handSize);
        System.out.println("\f");
        System.out.println("Player 1's hand is: "+p1.evaluate());
        System.out.println(p1);
        System.out.println("");
        System.out.println("Player 2's hand is: "+p2.evaluate());
        System.out.println(p2);
        System.out.println("");
        if(p1.handRanking(p1)>p2.handRanking(p2))
        {
            System.out.println("Player 1 wins!");
        }
        else if(p2.handRanking(p2)>p1.handRanking(p1))
        {
            System.out.println("Player 2 wins!");
        }
        else
        {
            if(tieBreaker(p1,p2)==1)
                System.out.println("Player 1 wins!");
            else if(tieBreaker(p1,p2)==2)
                System.out.println("Player 2 wins!");
            else
                System.out.println("The game is a tie!");
        }
        d.addHand(p1);
        d.addHand(p2);
    }

    /**
     * Evaluates the hand given that there is a tie. 1=player 1 wins, 2=player 2 wins, 0=tie.
     */
    public int tieBreaker(PokerHand p1, PokerHand p2)
    {
        if(p1.evaluate().equals("Junk") || p1.evaluate().equals("Flush"))
        {
            return highCard(p1,p2);
        }
        else if(p1.evaluate().equals("Pair") || p1.evaluate().equals("Two Pair"))
        {
            if(highPair(p1,p2)==1 || highPair(p1,p2)==2)
            return highPair(p1,p2);
            else
            return highCard(p1,p2);
        }
        else if(p1.evaluate().equals("Three of a Kind")|| p1.evaluate().equals("Full House"))
        {
            if(highTriple(p1,p2)==1 || highTriple(p1,p2)==2)
            return highTriple(p1,p2);
            else
            return highCard(p1,p2);
        }
        else if(p1.evaluate().equals("Four of a Kind"))
        {
            if(highQuad(p1,p2)==1 || highQuad(p1,p2)==2)
            return highQuad(p1,p2);
            else
            return highCard(p1,p2);
        }
        else if(p1.evaluate().equals("Straight") || p1.evaluate().equals("Straight Flush"))
        {
            if(p1.straight()>p2.straight())
            return 1;
            else if(p2.straight()>p1.straight())
            return 2;
            else
            return highCard(p1,p2);
        }
        return 0;
    }

    /**
     * Returns 1 if p1 has the higher card, 2 if p2 does, and 0 if neither do.
     */
    public int highCard(PokerHand p1,PokerHand p2)
    {
        p1.selectionSort(p1.hand);
        p2.selectionSort(p2.hand);
        for(int i=p1.getNumCards()-1;i>=0;i--)
        {
            if(p1.get(i).getRank()>p2.get(i).getRank())
                return 1;
            if(p2.get(i).getRank()>p1.get(i).getRank())
                return 2;
        }
        return 0;
    }

    /**
     * Used to break pair ties.
     */
    public int highPair2(PokerHand p1, PokerHand p2)
    {
        int p1High=0;
        int p2High=0;
        for(int rank=1;rank<=13;rank++)
        {
            int count=0; //counts the # of cards that match the given rank
            for(int i=0;i<p1.getNumCards();i++)
            {
                if(p1.get(i).getRank()==rank)
                    count++;
            }
            if(count==2)
                p1High=rank;
        }

        for(int rank=1;rank<=13;rank++)
        {
            int count=0; //counts the # of cards that match the given rank
            for(int i=0;i<p2.getNumCards();i++)
            {
                if(p2.get(i).getRank()==rank)
                    count++;
            }
            if(count==2)
                p2High=rank;
        }

        if(p1High>p2High)
            return 1;
        else if(p2High>p1High)
            return 2;
        else
            return 0;
    }

    /**
     * Used to break pair ties.
     */
    public int highPair(PokerHand p1, PokerHand p2)
    {
        ArrayList<Integer> p1High=new ArrayList<Integer>();
        ArrayList<Integer> p2High=new ArrayList<Integer>();
        p1.selectionSort(p1.hand);
        p2.selectionSort(p2.hand);

        for(int i=p1.numCards-1;i>0;i--)
        {
            if(p1.get(i).getRank()==p1.get(i-1).getRank())
                p1High.add(p1.get(i).getRank());
        }

        for(int i=p2.numCards-1;i>0;i--)
        {
            if(p2.get(i).getRank()==p2.get(i-1).getRank())
                p2High.add(p2.get(i).getRank());
        }

        for(int i=0;i<p1High.size();i++)
        {
            if(p1High.get(i)>p2High.get(i))
                return 1;
            if(p2High.get(i)>p1High.get(i))
                return 2;
        }
        return 0;
    }

    /**
     * Used to break triple ties.
     */
    public int highTriple(PokerHand p1, PokerHand p2)
    {
        ArrayList<Integer> p1High=new ArrayList<Integer>();
        ArrayList<Integer> p2High=new ArrayList<Integer>();
        p1.selectionSort(p1.hand);
        p2.selectionSort(p2.hand);

        for(int i=p1.numCards-1;i>1;i--)
        {
            if(p1.get(i).getRank()==p1.get(i-1).getRank() && p1.get(i-1).getRank()==p1.get(i-2).getRank())
                p1High.add(p1.get(i).getRank());
        }

        for(int i=p2.numCards-1;i>1;i--)
        {
            if(p2.get(i).getRank()== p2.get(i-1).getRank() && p2.get(i-1).getRank()==p2.get(i-2).getRank())
                p2High.add(p2.get(i).getRank());
        }

        if(p1High.get(0)>p2High.get(0))
            return 1;
        else if(p2High.get(0)>p1High.get(0))
            return 2;
        else
            return 0;
    }
    
    /**
     * Used to break 4 of a kind ties.
     */
    public int highQuad(PokerHand p1, PokerHand p2)
    {
        ArrayList<Integer> p1High=new ArrayList<Integer>();
        ArrayList<Integer> p2High=new ArrayList<Integer>();
        p1.selectionSort(p1.hand);
        p2.selectionSort(p2.hand);

        for(int i=p1.numCards-1;i>2;i--)
        {
            if(p1.get(i).getRank()==p1.get(i-1).getRank() && p1.get(i-1).getRank()==p1.get(i-2).getRank() && p1.get(i-2).getRank()==p1.get(i-3).getRank())
                p1High.add(p1.get(i).getRank());
        }

        for(int i=p2.numCards-1;i>2;i--)
        {
            if(p2.get(i).getRank()== p2.get(i-1).getRank() && p2.get(i-1).getRank()==p2.get(i-2).getRank() && p2.get(i-2).getRank()==p2.get(i-3).getRank())
                p2High.add(p2.get(i).getRank());
        }

        if(p1High.get(0)>p2High.get(0))
            return 1;
        else if(p2High.get(0)>p1High.get(0))
            return 2;
        else
            return 0;
    }
}
