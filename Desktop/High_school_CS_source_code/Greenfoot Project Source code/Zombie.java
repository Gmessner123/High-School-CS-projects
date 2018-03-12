import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The main enemy for the player to deal with in the game. The zombie follows
 * the player around the map and attempts to do damage to them. Alone, zombies
 * are pretty easy to deal with, but combined they can be difficult to avoid.
 * 
 * @author Grant Messner
 */
public class Zombie extends Enemy 
{
    int speed;
    final int IMAGE_SIZE=75;
    //protected final int DAMAGE=25;
    boolean playerIsSafe=false;
    int safeMoveCount=0;
    final int MAX_SAFE_MOVES=100;
    GreenfootImage zombie =new GreenfootImage("zombie.png");

    /**
     * Constructs objects of class zombie. Sets the speed and heatlh of the
     * zombie (which vary by round).
     */
    public Zombie()
    {
        zombie.scale(IMAGE_SIZE,IMAGE_SIZE);
        setImage(zombie);
        damage=25;
        setSpeed();
        setZombieHealth();
    }

    /**
     * Determines the health of the zombies based on the given round.
     */
    public void setZombieHealth()
    {
        if(Player.round<=10)
            enemyHealth=Player.round*10+30;
        else
            enemyHealth=140;
    }

    /**
     * Sets the speed for the zombies to run at (varies by round).
     */
    public void setSpeed()
    {
        if(Player.round<=3)
            speed=1;
        else if(Player.round<=6)
            speed=2;
        else
            speed=3;
    }

    /**
     * Attacks the player (takes away from their health) if there is an alive
     * player in contact with the zombie. The zombie then moves if there is
     * no wall in its way and turns around if there is.
     */
    public void act() 
    {
        Map zombieMap = (Map) getWorld();
        Player p=zombieMap.getPlayer();
        if(p.isAlive==true)
            super.attackPlayer();

        if(!isWall2() && p.isAlive==true)
            Move();
        else if(p.isAlive==true)
            turnSmaround();
        //setRotation(0);
    } 
    
    /**
     * Overrides the parents' turnSmaround method and turns the zombie towards
     * the Player.
     */
    public void turnSmaround()
    {
        Map zombieMap = (Map) getWorld();
        Player p=zombieMap.getPlayer();
        int pX=p.getX();
        int pY=p.getY();
        turn(90);
        move(speed);
        turn(270);
    }
    

    /*
    public void turnSmaround2()
    {
        //super.turnSmaround();
        Map zombieMap = (Map) getWorld();
        Player p=zombieMap.getPlayer();
        int pX=p.getX();
        int pY=p.getY();

        //int rotation=getRotation();
        //turnTowards(pX,pY);
        //turn(180);
        //move(speed*3);
        //turn(180);
        if(getRotation()==0)
        {
            if(getY()>pY)
            {
                turn(270);
                move(speed);
                turn(90);
            }
            else
            {
                turn(90);
                move(speed);
                turn(270);
            }
            //zombie.rotate(0);
        }
        else if(getRotation()==180)
        {
            if(getY()>pY)
            {
                turn(90);
                move(speed);
                turn(270);
            }
            else
            {
                turn(270);
                move(speed);
                turn(90);
            }
            //zombie.rotate(180);
        }
        else if (getRotation()==90)
        {
            if(getX()>pX)
            {
                turn(90);
                move(speed);
                turn(270);
            }
            else
            {
                turn(270);
                move(speed);
                turn(90);
            }
            //zombie.rotate(270);
        }
        else
        {
            if(getX()>pX)
            {
                turn(270);
                move(speed);
                turn(90);
            }
            else
            {
                turn(90);
                move(speed);
                turn(270);
            }
            //zombie.rotate(90);
        }
    }
    */

    /**
     * Randomly drops one of the four power ups when the zombie dies.
     */
    public void dropPowerUp()
    {
        int x=this.getX();
        int y=this.getY();
        Map zombieMap = (Map) getWorld();
        Nuke n=new Nuke();
        InstaKill i=new InstaKill();
        MaxAmmo m=new MaxAmmo();
        DoublePoints d=new DoublePoints();
        int random=getRandomNumber(50);

        if(random==0)
            zombieMap.addPowerUp(x,y,n);
        else if(random==1)
            zombieMap.addPowerUp(x,y,i);
        else if(random==2)
            zombieMap.addPowerUp(x,y,m);
        else if(random==3)
            zombieMap.addPowerUp(x,y,d);
    }

    /**
     * Moves the zombie around the map. Determines what the distance from the
     * player would be if the zombie moved in each of the four directions,
     * picks the smallest of these four, and moves in that direction. If the 
     * zombie is too close to another zombie, however, the zombies will randomly
     * move away from each other approximately 3 out of every 20 moves. The zombie
     * will also automatically try to move away from walls (if it comes near one).
     * The zombies also are allowed to clump together if they come near enough to 
     * a Player.
     */
    public void Move()
    {
        Map zombieMap = (Map) getWorld();
        Player p=zombieMap.getPlayer();

        int x=p.getX(); //player's x & y
        int y=p.getY();

        double [] distances=new double[4];
        double leftDistance=getDistance(getX()-speed,x, getY(),y);
        double rightDistance=getDistance(getX()+speed,x, getY(),y);
        double upDistance=getDistance(getX(),x, getY()-speed,y);
        double downDistance=getDistance(getX(),x, getY()+speed,y);

        distances[0]=leftDistance;
        distances[1]=rightDistance;
        distances[2]=upDistance;
        distances[3]=downDistance;

        if(getSmallest(distances)==leftDistance)
        {
            if(frontIsClear() ||getObjectsInRange(100,Player.class).size()!=0)
                moveLeft(speed);
            else if(!isWall2())
            {
                int random=getRandomNumber(20);
                if(random==0)
                {
                    moveRight(speed);
                    moveRight(speed);
                }
                else if(random==1)
                {
                    moveUp(speed);
                    moveUp(speed);
                }
                else if(random==2)
                {
                    moveDown(speed);
                    moveDown(speed);
                }
                else
                    moveLeft(speed);
            }
            else
            {
                moveRight(speed);
            }
        }
        else if(getSmallest(distances)==rightDistance)
        {
            if(frontIsClear()||getObjectsInRange(100,Player.class).size()!=0)
                moveRight(speed);
            else if(!isWall2())
            { 
                int random=getRandomNumber(20);
                if(random==0)
                {
                    moveLeft(speed);
                    moveLeft(speed);
                }
                else if(random==1)
                {
                    moveUp(speed);
                    moveUp(speed);
                }
                else if(random==2)
                {
                    moveDown(speed);
                    moveDown(speed);
                }
                else 
                    moveRight(speed);
            }
            else
            {
                moveLeft(speed);
            }
        }
        else if(getSmallest(distances)==upDistance)
        {
            if(frontIsClear()||getObjectsInRange(100,Player.class).size()!=0)
                moveUp(speed);
            else if(!isWall2())
            {
                int random=getRandomNumber(20);
                if(random==0)
                {
                    moveRight(speed);
                    moveRight(speed);
                }
                else if(random==1)
                {
                    moveLeft(speed);
                    moveLeft(speed);
                }
                else if(random==2)
                {
                    moveDown(speed);
                    moveDown(speed);
                }
                else
                    moveUp(speed);
            }
            else
            {
                moveDown(speed);
            }
        }
        else if(getSmallest(distances)==downDistance)
        {
            if(frontIsClear() ||getObjectsInRange(100,Player.class).size()!=0)
                moveDown(speed);
            else if(!isWall2())
            {
                int random=getRandomNumber(20);
                if(random==0)
                {
                    moveRight(speed);
                    moveRight(speed);
                }
                else if(random==1)
                {
                    moveLeft(speed);
                    moveLeft(speed);
                }
                else if(random==2)
                {
                    moveUp(speed);
                    moveUp(speed);
                }
                else
                    moveDown(speed);
            }
            else
            {
                moveUp(speed);
            }
        }
    }

    /**
     * Determines whether there is an object in front of the zombie in the given 
     * direction.
     */
    public boolean frontIsClear()
    {
        Actor Zombie;
        Zombie=getOneObjectAtOffset(0,0,Zombie.class);

        if(Zombie!=null ||isWall2()==true)
            return false;
        return true;
    }

    /**
     * Ends the life of this poor zombie.
     */
    public void die()
    {
        dropPowerUp();
        super.die();
    }

    /**
     * Returns the smallest value in an array of doubles.
     */
    public double getSmallest(double[] nums)
    {
        double smallest=nums[0];
        for (int i=0;i<nums.length;i++)
        {
            if(nums[i]<smallest)
                smallest=nums[i];
        }
        return smallest;
    }

    /**
     * Returns the distance between two sets of points on the grid.
     */
    public double getDistance(int x1, int x2, int y1, int y2)
    {
        //return  Math.sqrt((Math.pow((x1-x2),2)) + (Math.pow((y1-y2),2)));
        return  (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    }

    /**
     * returns a random number from 0 to high-1
     * i.e. getRandomNumber(10) would return a random number from 0 to 9.
     */
    private int getRandomNumber(int high)
    {
        java.util.Random rand = new java.util.Random();
        return rand.nextInt(high);
    }
}
