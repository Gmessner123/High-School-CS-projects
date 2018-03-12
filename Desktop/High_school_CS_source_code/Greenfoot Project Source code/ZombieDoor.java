import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The ZombieDoor class generates zombies at a set rate.  
 * 
 * @author Grant Messner 
 */
public class ZombieDoor extends Actor
{
    GreenfootImage zombiedoor =new GreenfootImage("zombiedoor.jpg");
    final int IMAGE_SIZE=50;
    final int SPAWN_DELAY=500;
    static int spawnTime;

    /**
     * Constructor for objects of class ZombieDoor. Sets the spawn time 
     * (a count up to the maximum SPAWN_DELAY) to 0, indicating that zombies
     * must wait at least SPAWN_DELAY more moves until they can spawn.
     */
    public ZombieDoor()
    {
        zombiedoor.scale(IMAGE_SIZE,IMAGE_SIZE+25);
        setImage(zombiedoor);
        spawnTime=0;
    }

    /**
     * Adds a zombie to the map and resets the spawnTime to 0, or, if the 
     * spawnTime is less than the SPAWN_DELAY, adds one to the spawnTime
     * count.
     */
    public void act() 
    {  
        if(spawnTime>=SPAWN_DELAY && Map.zombieRoundTotal<Map.zombieRoundCount && Map.zombieAliveCount<Map.MAX_ZOMBIES_ALIVE)
        {
            addZombie();
            spawnTime=0;
        }
        else
            spawnTime++;
    }

    /**
     * Adds a zombie to the world and adjusts the count of the # of zombies in the world.
     */
    public void addZombie()
    {
        int x=this.getX();
        int y=this.getY();
        Map zombieMap = (Map) getWorld();
        Zombie z=new Zombie();
        zombieMap.addZombie(x,y,z);
        Map.zombieAliveCount++;
        Map.zombieRoundTotal++;
    }
}
