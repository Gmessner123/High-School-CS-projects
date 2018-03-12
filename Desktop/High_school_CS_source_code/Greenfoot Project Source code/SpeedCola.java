import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Speed Cola increases the player's reload speed.
 * 
 * @author Grant Messner
 */
public class SpeedCola extends Perk
{
    GreenfootImage speedcola =new GreenfootImage("SpeedCola.png");
    final int IMAGE_SIZE=50;
    GreenfootSound speedCola=new GreenfootSound("speedcola.mp3");

    /**
     * Constructor for objects of class SpeedCola.
     */
    public SpeedCola()
    {
        speedcola.scale(IMAGE_SIZE,IMAGE_SIZE);
        setImage(speedcola);
        perkName="Speed Cola";
        perkCost=3000;
    }

    /**
     * If the player is touching the SpeedCola object and is holding down the
     * "e" button, SpeedCola asks the player if they want to buy the perk.
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
     * Cuts the reload delay (time it takes the player to reload) in half.
     */
    public void perkEffect()
    {
        Map zombieMap = (Map) getWorld();
        Player p=zombieMap.getPlayer();
        p.reloadDelayMultiplyer=2;
        p.perks.add(this);
        speedCola.play();
    }
}
