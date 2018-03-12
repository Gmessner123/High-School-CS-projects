import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The Nuke power up detonates and blows up all of the zombies on the map.
 * 
 * @author Grant Messner
 */
public class Nuke extends Powerup
{
    GreenfootImage nuke =new GreenfootImage("Nuke.png");
    final int IMAGE_SIZE=75;
    GreenfootSound kaboom=new GreenfootSound("kaboom.mp3");

    /**
     * Constructor for objects of class Nuke.
     */
    public Nuke()
    {
        nuke.scale(IMAGE_SIZE,IMAGE_SIZE);
        setImage(nuke);
    }

    /**
     * Detonates the nuke and blows up all the zombies on the map.
     */
    public void boom()
    {
        World world;
        world = getWorld();
        List <Enemy> enemies=world.getObjects(Enemy.class);
        world.removeObjects(enemies);
        Map.zombieAliveCount=0;
    }

    /**
     * If the player touches the Nuke, the Nuke explodes and eliminates
     * all of the zombies on the map, giving the player 400 points. The
     * Nuke then is removed from the map and the Nuke sound plays. However,
     * if the Nuke has been laying around for too long, it disappears and
     * the player earns no points.
     */
    public void act() 
    {
        if(isTouching(Player.class))
        {
            boom();
            Map zombieMap = (Map) getWorld();
            Player p=zombieMap.getPlayer();
            p.changeCash(400*p.cashMultiplyer);
            super.act();
            kaboom.play();
        }

        if(time>=MAX_POWERUP_TIME)
            super.act();
        time++;
    }   
}
