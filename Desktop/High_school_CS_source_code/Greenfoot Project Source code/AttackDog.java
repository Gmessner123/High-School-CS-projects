import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The attack dog is a pretty dumb enemy. It only has the ability to run back and
 * fourth in a straight line and attack the player if the two come into contact 
 * with one another. More of a complimentary enemy to the zombie.
 * 
 * @author Grant Messner
 */
public class AttackDog extends Enemy
{
    final int IMAGE_SIZE=75;
    GreenfootImage attackdog =new GreenfootImage("AttackDog.png");
    int dogSpeed=10;

    /**
     * Constructor for objects of class AttackDog. Sets the rotation of the
     * AttackDog to the given parameter.
     */
    public AttackDog(int facing)
    {
        attackdog.scale(IMAGE_SIZE,IMAGE_SIZE);
        setImage(attackdog);
        damage=10;
        enemyHealth=20;
        attackdog.rotate(180);
        turn(facing);
    }

    /**
     * Turns the dog around when it runs into a wall.
     */
    public void turnSmaround()
    {
        int x=getX();
        int y=getY();
        Map zombieMap= (Map)getWorld();

        if(x<=2 || x>=zombieMap.getMaxX()-2)
        {
            turn(180);   
        }

        if(y<=2 || y>=zombieMap.getMaxY()-2)
        {
            turn(180);  
        }

        if(isTouching(Wall.class))
            turn(180);
    }

    /**
     *The attack dog will do damage to the player if there is one near to it,
     *otherwise it moves back and fourth and turns around when it runs into a wall.
     */
    public void act() 
    {
        super.attackPlayer();
        Move();
        turnSmaround();
        int x=getX();
        int y=getY();
        //Map zombieMap= (Map)getWorld();
        //if(x>3 && x<zombieMap.getMaxY()-3 &&y>3 &&y<zombieMap.getMaxY()-3)

        //if(getRotation()!=0)
        //attackdog.rotate(180);
    }

    /**
     * Not really used in the AttackDog class.
     */
    public boolean frontIsClear()
    {
        return false;
    }

    /**
     * Simply moves the dog forward at its set speed.
     */
    public void Move()
    {
        move(dogSpeed);
    }
}
