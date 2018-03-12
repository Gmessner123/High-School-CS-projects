import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An abstract class representing the different perks the player can get.
 * Perks are like perminant power ups that the player can get that boost
 * their speed, strength, etc. All perk ideas are taken from the Call of
 * Duty Zombies series.
 * 
 * @author Grant Messner
 */
public abstract class Perk extends Actor
{
    protected String perkName;
    protected int perkCost;
    protected String input;
    protected Map zombieMap;
    protected Player p;
    protected boolean alreadyAsked=false;

    /**
     * Asks the player if they want to buy the perk if the player is touching
     * the Perk object and is pressing the "e" button. If the player buys the
     * perk, the perk effect takes hold via polymorphism. If the player does not,
     * nothing happens.
     */
    public void act() 
    {   
        Map zombieMap = (Map) getWorld();
        Player p=zombieMap.getPlayer();
        input=Greenfoot.ask("Type \"yes\" to buy "+perkName+" for "+perkCost+".");
        if(input==null)
            ;
        else if(input.equals("yes"))
        {
            if(p.cash<perkCost)
            {
                System.out.println("You cannot afford this perk!");
            }
            else
            {
                p.cash-=perkCost;
                perkEffect();
            }
        }
        else if(input.equals("no"))
        {
            ;
        }
    } 

    /**
     * The effect of the perk. Implemented in child class.
     */
    public abstract void perkEffect();
}
