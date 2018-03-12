import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The StaminUp perk boosts the player's running speed.
 * 
 * @author Grant Messner
 */
public class StaminUp extends Perk
{
    GreenfootImage staminup =new GreenfootImage("StaminUp.png");
    final int IMAGE_SIZE=50;
    final int STAMINUP_BOOST=2;
    GreenfootSound staminUp=new GreenfootSound("staminup.mp3");

    /**
     * Constructor for objects of class StaminUp.
     */
    public StaminUp()
    {
        staminup.scale(IMAGE_SIZE,IMAGE_SIZE);
        setImage(staminup);
        perkName="Stamin-up";
        perkCost=2000;
    }

    /**
     * If the player is touching the StaminUp object and is holding down the
     * "e" button, StaminUp asks the player if they want to buy the perk.
     */
    public void act() 
    {
        if(isTouching(Player.class) && alreadyAsked==false)
        {
            super.act();
            alreadyAsked=true;
        }
        else if(!isTouching(Player.class))
            alreadyAsked=false;   
    }    

    /**
     * Gives the player more speed and plays the StaminUp song.
     */
    public void perkEffect()
    {
        Map zombieMap = (Map) getWorld();
        Player p=zombieMap.getPlayer();
        p.speed+=STAMINUP_BOOST;
        p.perks.add(this);
        staminUp.play();
    }
}
