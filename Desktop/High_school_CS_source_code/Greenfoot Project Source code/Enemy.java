import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The abstract class representing all enemies in the game. Enemy classes
 * are classes that can do damage to players when they come into contact 
 * with them.
 * 
 * @author Grant Messner 
 */
public abstract class Enemy extends Runner implements Moveable
{
    public static int enemyHealth;
    protected int safeMoveCount=0;
    protected boolean playerIsSafe=false;
    protected int damage;
    protected final int MAX_SAFE_MOVES=100;
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Move();
        attackPlayer();
    }    

    /**
     * Implemented in child class.
     */
    public abstract void Move();

    /**
     * Implemented in child class.
     */
    public abstract boolean frontIsClear();

    /**
     * Implemented in child class.
     */
    public abstract void turnSmaround();

    /**
     * Does damage to the player if the player is in contact with the enemy.
     */
    public void attackPlayer()
    {
        Map zombieMap = (Map) getWorld();
        Player p=zombieMap.getPlayer();
        Actor Player;

        Player = getOneObjectAtOffset(0,0,Player.class);

        if(Player!=null)
        {
            if(playerIsSafe==false)
            {
                playerIsSafe=true;
                p.health=p.health-damage;
                p.healthCount=0;
                //System.out.println(p.health);
            }

            if(p.health<=0)
            {
                World world;
                world = getWorld();
                world.removeObject(Player);
                p.isAlive=false;
            }
        }

        if(playerIsSafe==true)
            safeMoveCount++;
        if(safeMoveCount>=MAX_SAFE_MOVES)
        {
            safeMoveCount=0;
            playerIsSafe=false;
        }
    }  
}
