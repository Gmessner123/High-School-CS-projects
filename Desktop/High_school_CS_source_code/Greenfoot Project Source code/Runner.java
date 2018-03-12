import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The abstract class runner is a superclass for moving enemies (as well as 
 * the Player class). It contains base methods for its child classes to expand
 * on.
 * 
 * @author Grant Messner 
 */
public abstract class Runner extends Actor implements Moveable
{
    public int speed;
    protected int wX;
    protected int wY;

    /**
     * Gets the wall class for Runner subclasses to reference when turning around.
     * This method is expanded upon in the subclasses.
     */
    public void turnSmaround()
    {        
        Wall w= (Wall)getOneIntersectingObject(Wall.class);
        wX=w.getX();
        wY=w.getY();
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
     * Removes the given Runner from the world.
     */
    public  void die()
    {
        World world;
        world=getWorld();
        world.removeObject(this);
    }

    /**
     * Returns true if the given Runner is touching an object of class Wall,
     * false otherwise.
     */
    protected boolean isWall2()
    {

        if(isTouching(Wall.class)) //&&!isTouching(Door.class))
            return true;
        /*else if(isTouching(Door.class))
        {
        Door d = (Door)getOneIntersectingObject(Door.class);
        if(d.alreadyOpened==false)
        return true;
        else
        return false;
        }*/
        else
            return false;
    }

    /**
     * Turns the Runner in the correct direction and moves them to the left.
     */
    public void moveLeft(int speed)
    {
        if(getRotation()==0)
        {
            turn(180);
            move(speed);
        }
        else if(getRotation()==90)
        {
            turn(90);
            move(speed);
        }
        else if(getRotation()==180)
        {
            move(speed);
        }
        else if(getRotation()==270)
        {
            turn(-90);
            move(speed);
        }
    }

    /**
     * Turns the Runner in the correct direction and moves them to the right.
     */
    public void moveRight(int speed)
    {
        if(getRotation()==0)
        {
            move(speed);
        }
        else if(getRotation()==90)
        {
            turn(-90);
            move(speed);
        }
        else if(getRotation()==180)
        {
            turn(180);
            move(speed);
        }
        else if(getRotation()==270)
        {
            turn(90);
            move(speed);
        }
    }

    /**
     * Turns the Runner in the correct direction and moves them downwards.
     */
    public void moveDown(int speed)
    {
        if(getRotation()==0)
        {
            turn(90);
            move(speed);
        }
        else if(getRotation()==90)
        {
            move(speed);
        }
        else if(getRotation()==180)
        {
            turn(-90);
            move(speed);
        }
        else if(getRotation()==270)
        {
            turn(180);
            move(speed);
        }
    }

    /**
     * Turns the Runner in the correct direction and moves them upwards.
     */
    public void moveUp(int speed)
    {
        if(getRotation()==0)
        {
            turn(-90);
            move(speed);
        }
        else if(getRotation()==90)
        {
            turn(180);
            move(speed);
        }
        else if(getRotation()==180)
        {
            turn(90);
            move(speed);
        }
        else if(getRotation()==270)
        {
            move(speed);
        }
    }
}
