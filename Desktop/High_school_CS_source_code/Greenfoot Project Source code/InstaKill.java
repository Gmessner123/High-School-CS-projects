import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstaKill here.
 * 
 * @author Grant Messner
 */
public class InstaKill extends Powerup
{
    GreenfootImage instakill =new GreenfootImage("InstaKill.png");
    final int IMAGE_SIZE=75;
    GreenfootSound instaKill=new GreenfootSound("instakill.mp3");

    /**
     * Constructor for objects of class InstaKill.
     */
    public InstaKill()
    {
        instakill.scale(IMAGE_SIZE,IMAGE_SIZE);
        setImage(instakill);
    }

    /**
     * If the InstaKill object is touching the Player object, the player's
     * InstaKill boolean is set to "true" and the player is able to kill
     * zombies in one hit for a limited amount of time.
     */
    public void act() 
    {
        if(isTouching(Player.class))
        { 
            Map zombieMap = (Map) getWorld();
            Player p=zombieMap.getPlayer();
            zombieMap.setInstaKill(true);
            super.act();
            instaKill.play();
        }

        if(time>=MAX_POWERUP_TIME)
            super.act();
        time++;
    }    
}
