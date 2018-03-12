import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The door opens at the end of the round when the player has eliminated all of 
 * the enemy zombies. The player has to be touching the door and press the "e"
 * button on their keyboard. 
 * 
 * @author Grant Messner
 */
public class Door extends Actor
{
    boolean alreadyAsked=false;
    boolean alreadyOpened=false;
    int doorCost=0;
    int count=0;
    GreenfootImage door =new GreenfootImage("door.png");
    final int IMAGE_SIZE=50;
    GreenfootSound gameOver=new GreenfootSound("buried-ending.mp3");

    /**
     * Constructor for objects of class Door.
     */
    public Door()
    {
        door.scale(IMAGE_SIZE,IMAGE_SIZE+50);
        setImage(door);
    }

    /**
     *If the round is over and the player presses "e" next to the door,
     *the door advances the round. Also handles much of the "game over" 
     *process and plays various sounds.
     */
    public void act() 
    {
        if(isTouching(Player.class) )//&& alreadyAsked==false && alreadyOpened==false)
        {
            if(Greenfoot.isKeyDown("e"))
            {
                //askPlayer();
                //alreadyAsked=true;
                advanceRound();
            }
        }
        //else if(!isTouching(Player.class))
        //alreadyAsked=false;   

        //super.act();
        Map zombieMap = (Map) getWorld();
        if(Player.isAlive==false &&count==0)
        {
            gameOver.play();
            zombieMap.drawText("Game Over!",zombieMap.getMaxX()/2-75,zombieMap.getMaxY()/2);
            if(Player.round!=1)
                zombieMap.drawText("You survived "+Player.round+" rounds",zombieMap.getMaxX()/2-150,zombieMap.getMaxY()/2+50);
            else
                zombieMap.drawText("You survived "+Player.round+" round",zombieMap.getMaxX()/2-150,zombieMap.getMaxY()/2+50);
            count++;
        }
    }    

    /**
     * Advances the round once all the enemies on the map have been defeated.
     */
    public void advanceRound()
    {
        Map zombieMap=(Map) getWorld();
        if(Map.zombieAliveCount==0 && Map.zombieRoundTotal==Map.zombieRoundCount)
        {
            zombieMap.changeRound(Player.round);
            Player p=zombieMap.getPlayer();
            p.setLocation(zombieMap.MAX_X/2,zombieMap.MAX_Y/2);
        }
    }

    /*
    public void askPlayer()
    {
    Map zombieMap = (Map) getWorld();
    Player p=zombieMap.getPlayer();
    String input=Greenfoot.ask("Buy door for "+doorCost+"?");

    if(input==null)
    ;
    else if(input.equals("yes"))
    {
    if(p.cash<doorCost)
    {
    System.out.println("You cannot afford this door!");
    }
    else
    {
    p.cash-=doorCost;
    alreadyOpened=true;
    }
    }
    else if(input.equals("no"))
    {
    ;
    }
    }
     */
}
