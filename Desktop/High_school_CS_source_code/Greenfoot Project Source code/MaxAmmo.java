import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Resets the player's ammo to the maximum amount. 
 * 
 * @author Grant Messner
 */
public class MaxAmmo extends Powerup
{
    GreenfootImage maxammo =new GreenfootImage("MaxAmmo.png");
    final int IMAGE_SIZE=75;
    GreenfootSound maxAmmo=new GreenfootSound("maxAmmo.mp3");

    /**
     * Constructor for objects of class MaxAmmo.
     */
    public MaxAmmo()
    {
        maxammo.scale(IMAGE_SIZE,IMAGE_SIZE);
        setImage(maxammo);
    }

    /**
     * If the MaxAmmo object is touching the Player object, the player's
     * ammo is set to the maximum amount.
     */
    public void act() 
    {
        if(isTouching(Player.class))
        {
            Map zombieMap = (Map) getWorld();
            Player p=zombieMap.getPlayer(); 
            p.setAmmo(p.ammoCapacity);
            super.act();
            maxAmmo.play();
        }

        if(time>=MAX_POWERUP_TIME)
            super.act();
        time++;
    }    
}
