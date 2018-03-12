import greenfoot.*;
import java.util.ArrayList; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * The main player class for the user to control. The player has the ability 
 * to move (at different speeds), shoot bullets at enemies, reload, and 
 * interact with their environment. 
 * 
 * @author Grant Messner
 */
public class Player extends Runner 
{
    int speed=4;
    int MAX_HEALTH=50;
    final int BOOST_DELAY=100;
    final int STARTING_X=309;
    final int STARTING_Y=215;
    public int health;
    //public int meleeDamage=50;
    public int boostTime;
    int cash;
    int cashMultiplyer;
    boolean cantBoost;
    boolean isReloading;
    int ammo;
    int magazineSize;
    int ammoInMag;
    int ammoCapacity;
    int reloadTime=0;
    int reloadDelay=50;
    int reloadDelayMultiplyer;
    int lastX=STARTING_X;
    int lastY=STARTING_Y;
    final int HEALTH_DELAY=200;
    int healthCount=0;
    double x1=0;
    double y1=0;
    boolean isFirstRun=true;
    static boolean isAlive=true;
    ArrayList <Perk> perks;
    GreenfootSound roundChange=new GreenfootSound("RoundChange.mp3");
    GreenfootSound roundChange2=new GreenfootSound("RoundChange2.mp3");
    GreenfootSound menuSong=new GreenfootSound("menuSong.mp3");
    //public int [] movement=new int[2]; //1st # is is direction, 2nd is speed
    //left=0, right=1, up=2, down=3
    public static int round;
    final int MAX_POWERUP_TIME=1000;
    int instakillTime;
    int doublePointsTime;
    public static boolean gameStarted=false;
    boolean firstMove=true;GreenfootSound staminUp=new GreenfootSound("staminup.mp3");
    GreenfootImage speedcola =new GreenfootImage("SpeedCola.png");
    GreenfootImage staminup =new GreenfootImage("StaminUp.png");
    GreenfootImage juggernog =new GreenfootImage("Juggernog.png");

    /**
     * Constructor for objects of class Player.
     */
    public Player()
    {
        health=MAX_HEALTH;
        boostTime=0;
        cantBoost=false;
        ammo=150;
        magazineSize=8;
        ammoInMag=8;
        ammoCapacity=150;
        cash=3000;
        cashMultiplyer=1;
        reloadDelayMultiplyer=1;
        perks=new ArrayList();
        isReloading=false;
        round=1;
        instakillTime=0;
        doublePointsTime=0;
        menuSong.playLoop();
    }

    /**
     * Causes the player to respond to user inputs. The player will move based
     * on what key is being pressed (w=up, a=left, s=down, d=right), fire a 
     * bullet if the player clicks the mouse on the screen, sprint if the player
     * is holding the spacebar, reload if the user hits the r key, and interact
     * with the surrounding environment if the user hits the e key. The player also
     * regenerates health slowly over time if they do not have full health.
     */
    public void act() 
    {
        menuSong.pause();
        if(firstMove==true)
            roundChange2.play();
        Move();
        fireBullet();
        reload();
        display();
        refillHealth();
        if(health<=0)
            isAlive=false;

        Map zombieMap=(Map) getWorld();
        if(cashMultiplyer>1)
        {
            doublePointsTime++;
            if(doublePointsTime>MAX_POWERUP_TIME)
            {
                cashMultiplyer=1;
                doublePointsTime=0;
            }
        }

        if(zombieMap.getInstaKill()==true)
        {
            instakillTime++;
            if(instakillTime>MAX_POWERUP_TIME)
            {
                zombieMap.setInstaKill(false);
                instakillTime=0;
            }
        }

        setRotation(0);
        firstMove=false;
    }   

    /**
     * Slowly regenerates the player's health over time.
     */
    public void refillHealth()
    {
        if(health<MAX_HEALTH)
        {
            healthCount++;
            if(healthCount>=HEALTH_DELAY)
            {
                health+=25;
                if(health>MAX_HEALTH)
                    health=MAX_HEALTH;
                healthCount=0;
            }
        }
    }

    /*
     * This method was originally going to be used to make a scrolling screen, but I ended up not doing it.
    public void counterMove(int direction, int speed)
    {
    if(direction==0)
    setRotation(0);
    else if(direction==1)
    setRotation(180);
    else if(direction==2)
    setRotation(90);
    else
    setRotation(270);

    move(speed);
    }
     */

    /**
     * Extends the turnSmaround method in Runner.
     */
    public void turnSmaround()
    {
        super.turnSmaround();
        int x=getX();
        int y=getY();

        int xDiff=Math.abs(x-wX);
        int yDiff=Math.abs(y-wY);

        if(xDiff>yDiff)
        {
            if(x>wX)
            {
                setRotation(0);
                //movement[0]=0; 
                //movement[1]=2;
            }

            else
            {
                setRotation(180);
                //movement[0]=1;
                //movement[1]=2;
            }
        }
        else
        {
            if(y>wY)
            {
                setRotation(90);
                //movement[0]=2;
                //movement[1]=2;
            }
            else
            {
                setRotation(270);
                //movement[0]=3;
                //movement[1]=2;
            }
        }
        move(2);
    }

    /**
     * Displays things like ammo count, health, and money.
     */
    public void display()
    {
        World world;
        world = getWorld();
        Map zombieMap = (Map) getWorld();
        world.showText("Health: "+health,70,15);
        world.showText("Cash: "+cash,70,40);
        int excessAmmo=ammo-ammoInMag;
        if(excessAmmo>=0)
            world.showText("Ammo "+ammoInMag+" / "+excessAmmo,zombieMap.getMaxX()-90,zombieMap.getMaxY()-50);
        else
            world.showText("Ammo "+ammo+" / "+"0",zombieMap.getMaxX()-90,zombieMap.getMaxY()-50);
        zombieMap.drawText(""+round);
    }

    /**
     * Sets the player's ammo to the given amount.
     */
    public void setAmmo(int amount)
    {
        ammo=amount;
    }

    /**
     * Changes the amount of money the player has by the given amount.
     */
    public void changeCash(int amount)
    {
        cash+=amount;
    }

    /**
     * Sets the player's multiplyer to the given amount.
     */
    public void setMultiplyer(int amount)
    {
        cashMultiplyer=amount;
    }

    /**
     * Fires a bullet class towards enemy zombies. Generates a bullet at the 
     * same position as the player, aims it towards wherever the mouse was clicked, 
     * and fires it.
     */
    public void fireBullet()
    {
        Map zombieMap = (Map) getWorld();
        double a=getAngle();
        if(a!=0 && ammo>0 && ammoInMag>0)
        {
            Bullet b=zombieMap.addBullet((int)x1,(int)y1);
            b.turn(-(int)a);
            ammo--;
            ammoInMag--;
            isReloading=false;
            reloadTime=0;
        }
    }

    /**
     * Reloads the magazine for the player to fire again. 
     */
    public void reload()
    {
        if((Greenfoot.isKeyDown("r") || isReloading==true) && ammoInMag<magazineSize)
        {
            reloadTime++;
            isReloading=true;
            //System.out.println("reloading");
            if(reloadTime>=reloadDelay/reloadDelayMultiplyer)
            {
                //System.out.println("reloading complete");
                reloadTime=0;
                ammoInMag=magazineSize;
                isReloading=false;
            }
        }
    }

    /**
     * Returns the angle between the player's current position and the 
     * click position (relative to the x-axis). Uses the pythagorean 
     * theorem and the inverse sine function to determine the angle.
     */
    public double getAngle()
    {
        MouseInfo m = Greenfoot.getMouseInfo();
        if(Greenfoot.mouseClicked(null)==true)
        {
            x1=getX();
            y1=getY();
            double x2=m.getX();
            double y2=m.getY();

            double dX=x1-x2; //x-displacement
            double dY=y1-y2; //y-displacement
            double r=Math.sqrt((Math.pow(dX,2))+(Math.pow(dY,2)));
            double theta=0;
            double degree=0;

            if(dX>0)
                theta=Math.PI-Math.atan(dY/dX);
            else
                theta=Math.asin(dY/r);

            if(theta>0)
                degree=Math.toDegrees(theta);
            else if(theta==0)
                degree=0.01;
            else
                degree=Math.toDegrees(theta)+360;
            //System.out.println(degree);
            return degree;
        }
        else 
            return 0;
    }

    /**
     * Moves the player in either the up (w), down (s), left (a) or right (d)
     * direction depending on what key is pressed. Prevents the player from moving
     * if there is a wall in the way.
     */
    public void Move()
    {
        if(Greenfoot.isKeyDown("space") && boostTime<BOOST_DELAY && cantBoost==false)
        {
            if(Greenfoot.isKeyDown("a") &&!isWall2())
            {
                moveLeft(speed*2);
                //movement[0]=0;
                // movement[1]=speed*2;
            }

            if(Greenfoot.isKeyDown("d") &&!isWall2())
            {
                moveRight(speed*2);
                //movement[0]=1;
                //movement[1]=speed*2;
            }

            if(Greenfoot.isKeyDown("w") &&!isWall2())
            { 
                moveUp(speed*2);
                //movement[0]=2;
                //movement[1]=speed*2;
            }

            if(Greenfoot.isKeyDown("s") &&!isWall2())
            {
                moveDown(speed*2);
                //movement[0]=3;
                //movement[1]=speed*2;
            }
            boostTime++;
        }
        else
        {
            if(boostTime>=BOOST_DELAY)
            {
                cantBoost=true;
            }

            if(Greenfoot.isKeyDown("a") &&!isWall2())
            {
                moveLeft(speed);
                //movement[0]=0;
                //movement[1]=speed;
            }

            if(Greenfoot.isKeyDown("d") &&!isWall2())
            { 
                moveRight(speed);
                //movement[0]=1;
                //movement[1]=speed;
            }

            if(Greenfoot.isKeyDown("w") &&!isWall2())
            {
                moveUp(speed);
                //movement[0]=2;
                //movement[1]=speed;
            }

            if(Greenfoot.isKeyDown("s") &&!isWall2())
            {
                moveDown(speed);
                //movement[0]=3;
                //movement[1]=speed;
            }
            boostTime--;

            if(boostTime==0)
                cantBoost=false;
        }

        if(isWall2())
            turnSmaround();

        setRotation(0);
    }

    /**
     * Ends the life of the player.
     */
    public void die()
    {
        isAlive=false;
        super.die();
    }

    /**
     * This method is not really used in the player class, more here as a placeholder.
     */
    public boolean frontIsClear()
    {
        return true;
    }
}