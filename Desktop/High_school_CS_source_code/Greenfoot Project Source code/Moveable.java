import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An interface for objects that are able to move.
 * 
 * @author Grant Messner
 */
public interface Moveable 
{
    /**
     * Moves the moveable actor. Implemented in child class.
     */
    public void Move();

    /**
     * Determines whether or not there is a wall in front of the moveable actor.
     * Implemented in child class.
     */
    public boolean frontIsClear();

    /**
     * Causes the moveable actor to be removed from the world.
     */
    public void die();

    /**
     * Turns the moveable actor around.
     */
    public void turnSmaround();
}
