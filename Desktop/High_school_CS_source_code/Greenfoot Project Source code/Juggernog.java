import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Juggernog increases the maximum health of the player (i.e., they are able
 * to take more damage from zombies).
 * 
 * @author Grant Messner
 */
public class Juggernog extends Perk
{
    GreenfootImage juggernog =new GreenfootImage("Juggernog.png");
    final int IMAGE_SIZE=50;
    final int juggernogBoost=2;
    GreenfootSound Juggernog=new GreenfootSound("juggernog.mp3");

    /**
     * Constructor for objects of class Juggernog.
     */
    public Juggernog()
    {
        juggernog.scale(IMAGE_SIZE,IMAGE_SIZE);
        setImage(juggernog);
        perkName="Juggernog";
        perkCost=2500;
    }

    /**
     * If the player is touching the Juggernog object and is holding down the
     * "e" button, Juggernog asks the player if they want to buy the perk.
     */
    public void act() 
    {
        if(isTouching(Player.class) && alreadyAsked==false)
        {
            super.act();
            alreadyAsked=true;
        }
        else if(!isTouching(Player.class))
            alreadyAsked=false;      
    }    

    /**
     * Gives the player more maximum health (i.e., the player can survive more 
     * hits from zombies).
     */
    public void perkEffect()
    {   
        Map zombieMap = (Map) getWorld();
        Player p=zombieMap.getPlayer();
        p.MAX_HEALTH*=juggernogBoost;     
        p.health=p.MAX_HEALTH;
        p.perks.add(this);
        Juggernog.play();
    }
}
