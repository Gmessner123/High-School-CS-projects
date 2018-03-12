import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Powerups are temporary upgrades to the player's abilities. They can do 
 * things like eliminate all of the zombies on the map or double the points
 * a player earns when they hit a zombie. All powerup ideas are based off of 
 * the Call of Duty Zombies series.
 * 
 * @author Grant Messner
 */
public abstract class Powerup extends Actor
{
    int time;
    int MAX_POWERUP_TIME=400;

    /**
     * Constructor for objects of class Powerup.
     */
    public Powerup()
    {
        time=0;
    }

    /**
     * Removes the PowerUp from the world when it either times out or is 
     * picked up by the player.
     */
    public void act() 
    { 
        World world;
        world=getWorld();
        world.removeObject(this);
    }    
}
