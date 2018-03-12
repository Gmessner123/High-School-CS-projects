import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * The bullet class is a class of objects that can attack enemies if it runs into them.
 * Basically, the bullet can move forwards and collide with an enemy, taking away a 
 * certain amount of damage from their health. A bullet is generated every time the player
 * clicks the mouse on the screen, and the bullet dies when it runs into a wall, and enemy,
 * or some other object on the map.
 * 
 * @author Grant Messner 
 */
public class Bullet extends Actor implements Moveable
{
    final int bulletSpeed=100;
    final int offset=20;
    final int IMAGE_SIZE=25;
    int damage=10;
    boolean isAlive=true;

    GreenfootImage bullet =new GreenfootImage("bullet-hi.png");

    /**
     * Constructor for objects of class bullet. Sets up the bullet image and
     * gives it the correct rotation.
     */
    public Bullet()
    {
        bullet.scale(IMAGE_SIZE,IMAGE_SIZE);
        setImage(bullet);
        bullet.rotate(180);
    }

    /**
     * Not used in the bullet class (since the bullet does not turn).
     */
    public void turnSmaround()
    {
        ;
    }

    /**
     * The bullet will do damage to an enemy if there is one present at its current location.
     * It also moves forward and, if it runs into a wall (or hits an enemy), is removed from 
     * the world.
     */
    public void act() 
    {
        attackEnemy();
        Move();
    }    

    /**
     * If there is an enemy, the bullet will do damage to the enemy. If the enemy runs out of
     * health (or if the player has the InstaKill powerup), the bullet removes the enemy 
     * from the world and adds points to the player's score.
     */
    public void attackEnemy()
    {
        if(this!=null)
        {
            Map zombieMap = (Map) getWorld();
            Player p=zombieMap.getPlayer();
            Actor Enemy;

            Enemy e= (Enemy)getOneObjectAtOffset(offset,offset,Enemy.class);
            //Enemy e=zombieMap.getEnemy(Enemy);
            if(e!=null)
            {
                World world;
                world = getWorld();
                //isAlive=false;

                if(zombieMap.getInstaKill()==false)
                {
                    e.enemyHealth=e.enemyHealth-damage;
                    p.changeCash(20*p.cashMultiplyer);
                }
                else
                    e.enemyHealth=0;
                //System.out.println(e.enemyHealth);

                if(e.enemyHealth<=0)
                {
                    //world.removeObject(e);
                    e.die();
                    Map.zombieAliveCount--;
                    p.changeCash(100*p.cashMultiplyer);
                }
            }
        }
    }

    /**
     * Changes the damage the bullet does to the given number.
     */
    public  void changeDamage(int num)
    {
        damage=num;
    }

    /**
     * The bullet will either move forward (if there is nothing in front of it) or die (if something
     * is).
     */
    public void Move()
    {
        if(frontIsClear() && isAlive==true)
        {
            move(bulletSpeed);
        }
        else
        {
            die();
        }      
    }

    /**
     * Removes the bullet from the world after it hits something.
     */
    public void die()
    {
        World world;
        world = getWorld();
        world.removeObject(this);
    }

    /**
     * Returns true if the bullet is not blocked by another object
     */
    public boolean frontIsClear()
    {
        Map zombieMap = (Map) getWorld();
        int x=getX();
        int y=getY();
        Player p=zombieMap.getPlayer();
        if((x==0 && p.getX()>=1 )|| (x>=zombieMap.getMaxX()-1 && p.getX()<=zombieMap.getMaxX()-2))
            return false;
        else if((y==0 && p.getY()>=1) || (y>=zombieMap.getMaxY()-1 &&p.getY()<=zombieMap.getMaxY()-2))
            return false;
        else if(isTouching(Wall.class))
            return false;
        else
            return true;
    }
}
