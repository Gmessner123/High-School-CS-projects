import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Doubles the player's earned points when they hit or kill an enemy for a 
 * limited time.
 * 
 * @author Grant Messner
 */
public class DoublePoints extends Powerup
{
    GreenfootImage doublepoints =new GreenfootImage("DoublePoints.png");
    final int IMAGE_SIZE=75;
    GreenfootSound doublePoints=new GreenfootSound("doublePoints.mp3");

    /**
     * Constructor for objects of class DoublePoints.
     */
    public DoublePoints()
    {
        doublepoints.scale(IMAGE_SIZE,IMAGE_SIZE);
        setImage(doublepoints);
    }

    /**
     * If the Player object is touching the DoublePoints object, the player's 
     * point multiplyer is set to 2 for a limited time. 
     */
    public void act() 
    {
        if(isTouching(Player.class))
        { 
            Map zombieMap = (Map) getWorld();
            Player p=zombieMap.getPlayer();
            p.setMultiplyer(2);
            super.act();
            doublePoints.play();
        }

        if(time>=MAX_POWERUP_TIME)
            super.act();
        time++;
    }    
}
