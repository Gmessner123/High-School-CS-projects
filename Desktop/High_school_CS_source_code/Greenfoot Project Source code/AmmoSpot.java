import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *The ammo spot is a place for the player to buy more ammo.
 * 
 * @author Grant Messner
 */
public class AmmoSpot extends Actor
{
    GreenfootImage chalk =new GreenfootImage("chalk.jpg");
    final int IMAGE_SIZE=75;
    final int STAMINUP_BOOST=2;
    int ammoCost=1000;
    boolean alreadyAsked=false;

    /**
     * Constructor for objects of class AmmoSpot.
     */
    public AmmoSpot()
    {
        chalk.scale(IMAGE_SIZE,IMAGE_SIZE);
        setImage(chalk);
    }

    /**
     * If the player is touching the AmmoSpot and is holding down the "e"
     * button, the player is asked whether or not thhey want to but ammo.
     */
    public void act() 
    {
        if(isTouching(Player.class) && alreadyAsked==false &&Greenfoot.isKeyDown("e"))
        {
            askPlayer();
            alreadyAsked=true;
        }
        else if(!isTouching(Player.class))
            alreadyAsked=false;   
        super.act();
    }    

    /**
     * Asks the player if they would like to buy ammo.
     */
    public void askPlayer()
    {
        Map zombieMap = (Map) getWorld();
        Player p=zombieMap.getPlayer();
        String input=Greenfoot.ask("Type \"yes\" to buy ammo for "+ammoCost+".");

        if(input==null)
            ;
        else if(input.equals("yes"))
        {
            if(p.cash<ammoCost)
            {
                System.out.println("You cannot afford more ammo!");
            }
            else
            {
                p.cash-=ammoCost;
                p.ammo=p.ammoCapacity;
            }
        }
        else if(input.equals("no"))
        {
            ;
        }
    }
}
