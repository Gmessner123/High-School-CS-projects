import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.*;
import javax.swing.JOptionPane;
//import java.util.collection;
/**
 * The main world subclass in the game. This class facilitates the changing
 * of levels when the player walks through a door. It also contains many 
 * accessor and "add" methods (since most of this project was done before
 * we learned about static variables).
 * 
 * @author Grant Messner
 */
public class Map extends World
{
    Player player;
    final int MAX_X=870;
    final int MAX_Y=600;
    int [][] map=new int[MAX_X][MAX_Y];
    boolean isInstaKill=false;
    static int zombieAliveCount;
    static int zombieRoundCount;
    static int zombieRoundTotal;
    final static int MAX_ZOMBIES_ALIVE=6;
    private static int welcomeCount=0;

    GreenfootSound roundChange=new GreenfootSound("RoundChange.mp3");
    GreenfootSound roundChange2=new GreenfootSound("RoundChange2.mp3");

    /**
     * Constructor for objects of class MyWorld.
     */
    public Map()
    {    
        // Create a new world with 600x600 cells with a cell size of 1x1 pixels.
        super(870, 600, 1); 
        //welcomeCount=0;
        //System.out.println("\f");
        prepare();
        zombieRoundCount=Player.round*2+2;
        zombieRoundTotal=0;
        zombieAliveCount=0;
         printWelcome();
        //menuSong.playLoop();
        // super.setActOrder(Immovable.class,Bullet.class,Enemy.class,Player.class);
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        player = new Player();
        addObject(player,MAX_X/2,MAX_Y/2);
        Door door = new Door();
        addObject(door,MAX_X-25,50);
        ZombieDoor zombiedoor = new ZombieDoor();
        addObject(zombiedoor,25,MAX_Y-38);
        ZombieDoor zombiedoor2 = new ZombieDoor();
        addObject(zombiedoor2,25,37);
        makeLevel(Player.round%10);
    }

    /**
     * Changes from round to round when the player enters the door and wins the round.
     */
    public void changeRound(int currentRound)
    {
        List <Wall> walls=getObjects(Wall.class);
        List <AmmoSpot> ammospots=getObjects(AmmoSpot.class);
        List <Perk> perks=getObjects(Perk.class);
        //List <ZombieDoor> zombieDoors=getObjects(ZombieDoor.class);
        removeObjects(walls);
        removeObjects(ammospots);
        removeObjects(perks);
        //removeObjects(zombieDoors);
        makeLevel(currentRound+1);
        Player.round++;
        zombieRoundCount=Player.round*2+2;
        zombieRoundTotal=0;
        zombieAliveCount=0;
        ZombieDoor.spawnTime=0;
    }

    /**
     * Returns true if there are any enemies remaining in the world
     */
    public  boolean enemiesAlive()
    {
        if(getObjects(Enemy.class).size()!=0)
        {
            return false;
        }
        else
        {Player.round ++;
            return true;
        }
    }

    /**
     * Generates the given level for the player to play.
     */
    public void makeLevel(int level)
    {
        if(level!=1)
            roundChange.play();

        if(level%10==1)
        {
            makeLevel1();
        }
        else if(level%10==2)
        {
            makeLevel2();
        }
        else if(level%10==3)
        {
            makeLevel3();
        }
        else if(level%10==4)
        {
            makeLevel4();
        }
        else if(level%10==5)
        {
            makeLevel5();
        }
        else if(level%10==6)
        {
            makeLevel6();
        }
        else if(level%10==7)
        {
            makeLevel7();
        }
        else if(level%10==8)
        {
            makeLevel8();
        }
        else if(level%10==9)
        {
            makeLevel9();
        }
        else if(level%10==10)
        {
            makeLevel10();
        }
    }

    /**
     * Generates the 1st level of the game, and all levels that end in the
     * number 1 (1,11,21,...).
     */
    public void makeLevel1()
    {
        Wall wall = new Wall();
        addObject(wall,104,111);
        //Wall wall2 = new Wall();
        //addObject(wall2,111,168);
        //wall2.setLocation(240,308);
        Wall wall3 = new Wall();
        addObject(wall3,245,426);
        Wall wall4 = new Wall();
        addObject(wall4,397,437);
        wall3.setLocation(241,426);
        wall4.setLocation(397,428);
        Wall wall5 = new Wall();
        addObject(wall5,557,436);
        Wall wall6 = new Wall();
        addObject(wall6,671,441);
        //Wall wall7 = new Wall();
        //addObject(wall7,672,321);
        Wall wall8 = new Wall();
        addObject(wall8,676,205);
        Wall wall9 = new Wall();
        addObject(wall9,565,209);
        Wall wall10 = new Wall();
        addObject(wall10,397,203);
        wall9.setLocation(552,204);
        wall8.setLocation(663,205);
        wall5.setLocation(557,431);
        wall6.setLocation(670,433);
        //wall7.setLocation(661,320);
        wall6.setLocation(659,432);
        wall5.setLocation(542,429);
        wall9.setLocation(541,204);
        AmmoSpot ammospot = new AmmoSpot();
        addObject(ammospot,812,548);
        ammospot.setLocation(483,563);
        wall.setLocation(245,201);
    }

    /**
     * Generates the 1st level of the game, and all levels that end in the
     * number 2 (2,12,22,...).
     */
    public void makeLevel2()
    {
        Wall wall11 = new Wall();
        addObject(wall11,277,208);
        Wall wall12 = new Wall();
        addObject(wall12,339,217);
        Wall wall13 = new Wall();
        addObject(wall13,414,221);
        Wall wall14 = new Wall();
        addObject(wall14,534,222);
        Wall wall15 = new Wall();
        addObject(wall15,477,220);
        Wall wall16 = new Wall();
        addObject(wall16,597,222);
        Wall wall17 = new Wall();
        addObject(wall17,261,451);
        Wall wall18 = new Wall();
        addObject(wall18,332,460);
        Wall wall19 = new Wall();
        addObject(wall19,420,453);
        Wall wall20 = new Wall();
        addObject(wall20,504,419);
        Wall wall21 = new Wall();
        addObject(wall21,560,425);
        Wall wall22 = new Wall();
        addObject(wall22,615,423);
        wall22.setLocation(611,420);
        wall21.setLocation(561,420);
        wall20.setLocation(511,420);
        wall19.setLocation(461,420);
        wall18.setLocation(411,420);
        wall17.setLocation(363,420);
        Wall wall23 = new Wall();
        addObject(wall23,322,425);
        wall23.setLocation(314,420);
        Wall wall24 = new Wall();
        addObject(wall24,271,426);
        wall24.setLocation(266,420);
        wall11.setLocation(267,221);
        wall12.setLocation(317,221);
        wall13.setLocation(368,222);
        wall13.setLocation(367,222);
        wall13.setLocation(367,221);
        wall15.setLocation(417,221);
        wall14.setLocation(467,221);
        wall16.setLocation(517,221);
        Wall wall25 = new Wall();
        addObject(wall25,574,228);
        wall25.setLocation(567,221);
        Wall wall26 = new Wall();
        addObject(wall26,624,229);
        wall26.setLocation(617,221);
        Juggernog juggernog = new Juggernog();
        addObject(juggernog,822,554);
        juggernog.setLocation(841,565);
        AmmoSpot ammospot = new AmmoSpot();
        addObject(ammospot,812,548);
        ammospot.setLocation(483,563);
    }

    /**
     * Generates the 1st level of the game, and all levels that end in the
     * number 3 (3,13,23,...).
     */
    public void makeLevel3()
    {

        Wall wall11 = new Wall();
        addObject(wall11,444,219);
        wall11.setLocation(431,218);
        Wall wall12 = new Wall();
        addObject(wall12,437,113);
        wall11.setLocation(432,240);
        wall12.setLocation(430,115);
        Wall wall13 = new Wall();
        addObject(wall13,436,1);
        wall13.setLocation(436,1);
        wall13.setLocation(428,0);
        wall11.setLocation(432,243);
        wall12.setLocation(432,120);
        wall13.setLocation(432,4);
        Wall wall14 = new Wall();
        addObject(wall14,550,309);
        Wall wall15 = new Wall();
        addObject(wall15,691,320);
        wall15.setLocation(692,313);
        Wall wall16 = new Wall();
        addObject(wall16,846,321);
        wall16.setLocation(846,311);
        wall15.setLocation(696,311);
        Wall wall17 = new Wall();
        addObject(wall17,442,362);
        Wall wall18 = new Wall();
        addObject(wall18,447,479);
        Wall wall19 = new Wall();
        wall18.setLocation(441,471);
        Wall wall20 = new Wall();
        addObject(wall20,450,591);
        wall17.setLocation(438,399);
        wall18.setLocation(438,448);
        wall20.setLocation(440,591);
        wall11.setLocation(433,208);
        wall18.setLocation(438,476);
        wall17.setLocation(437,383);
        wall18.setLocation(436,450);
        wall12.setLocation(433,144);
        Wall wall21 = new Wall();
        addObject(wall21,327,311);
        Wall wall22 = new Wall();
        addObject(wall22,202,317);
        Wall wall23 = new Wall();
        addObject(wall23,35,319);
        wall22.setLocation(181,310);
        wall23.setLocation(25,310);
        AmmoSpot ammospot = new AmmoSpot();
        addObject(ammospot,812,548);
        ammospot.setLocation(483,563);
    }

    /**
     * Generates the 1st level of the game, and all levels that end in the
     * number 4 (4,14,24,...).
     */
    public void makeLevel4()
    {
        Wall wall13 = new Wall();
        addObject(wall13,226,112);
        Wall wall14 = new Wall();
        addObject(wall14,334,220);
        Wall wall15 = new Wall();
        addObject(wall15,536,401);
        Wall wall16 = new Wall();
        addObject(wall16,654,513);
        Wall wall17 = new Wall();
        addObject(wall17,575,223);
        Wall wall18 = new Wall();
        addObject(wall18,702,134);
        wall17.setLocation(569,220);
        wall18.setLocation(690,127);
        Wall wall19 = new Wall();
        addObject(wall19,334,405);
        wall19.setLocation(328,403);
        wall17.setLocation(542,215);
        wall19.setLocation(329,399);
        wall17.setLocation(541,218);
        wall18.setLocation(675,102);
        Wall wall20 = new Wall();
        addObject(wall20,224,509);
        wall20.setLocation(218,507);
        wall16.setLocation(668,510);
        wall16.setLocation(672,505);
        wall15.setLocation(538,399);
        StaminUp staminup = new StaminUp();
        addObject(staminup,822,554);
        staminup.setLocation(841,565);
        AmmoSpot ammospot = new AmmoSpot();
        addObject(ammospot,812,548);
        ammospot.setLocation(483,563);
    }

    /**
     * Generates the 1st level of the game, and all levels that end in the
     * number 5 (5,15,25,...).
     */
    public void makeLevel5()
    {
        AmmoSpot ammospot = new AmmoSpot();
        addObject(ammospot,862,586);
        ammospot.setLocation(832,562);
        Wall wall = new Wall();
        addObject(wall,645,580);
        wall.setLocation(638,564);
        Wall wall2 = new Wall();
        addObject(wall2,734,464);
        wall.setLocation(603,573);
        wall2.setLocation(720,446);
        Wall wall3 = new Wall();
        addObject(wall3,853,331);
        wall3.setLocation(848,329);
        Wall wall4 = new Wall();
        addObject(wall4,401,582);
        Wall wall5 = new Wall();
        addObject(wall5,512,445);
        Wall wall6 = new Wall();
        addObject(wall6,626,321);
        wall.setLocation(612,574);
        Wall wall7 = new Wall();
        addObject(wall7,742,213);
        wall7.setLocation(735,214);
        Wall wall8 = new Wall();
        addObject(wall8,208,582);
        Wall wall9 = new Wall();
        addObject(wall9,308,438);
        Wall wall10 = new Wall();
        addObject(wall10,527,203);
        Wall wall11 = new Wall();
        addObject(wall11,646,78);
        Wall wall12 = new Wall();
        addObject(wall12,115,441);
        Wall wall13 = new Wall();
        addObject(wall13,215,308);
        Wall wall14 = new Wall();
        addObject(wall14,337,205);
        Wall wall15 = new Wall();
        addObject(wall15,443,76);
        Wall wall16 = new Wall();
        addObject(wall16,26,311);
        Wall wall17 = new Wall();
        addObject(wall17,130,198);
        Wall wall18 = new Wall();
        addObject(wall18,223,87);
        wall17.setLocation(122,199);
    }

    /**
     * Generates the 1st level of the game, and all levels that end in the
     * number 6 (6,16,26,...).
     */
    public void makeLevel6()
    {
        Wall wall9 = new Wall();
        addObject(wall9,286,580);
        Wall wall10 = new Wall();
        addObject(wall10,292,538);
        Wall wall11 = new Wall();
        addObject(wall11,293,494);
        Wall wall12 = new Wall();
        addObject(wall12,305,454);
        Wall wall13 = new Wall();
        addObject(wall13,291,415);
        Wall wall14 = new Wall();
        addObject(wall14,303,370);
        wall10.setLocation(286,530);
        wall11.setLocation(287,481);
        wall12.setLocation(288,433);
        wall13.setLocation(287,384);
        wall14.setLocation(288,335);
        Wall wall15 = new Wall();
        addObject(wall15,294,292);
        wall15.setLocation(288,286);
        Wall wall16 = new Wall();
        addObject(wall16,292,96);
        Wall wall17 = new Wall();
        addObject(wall17,298,54);
        wall17.setLocation(292,48);
        Wall wall18 = new Wall();
        addObject(wall18,299,4);
        wall18.setLocation(293,0);
        wall16.setLocation(291,97);
        wall15.setLocation(336,384);
        wall14.setLocation(386,384);
        Wall wall19 = new Wall();
        addObject(wall19,443,391);
        wall19.setLocation(436,385);
        Wall wall20 = new Wall();
        addObject(wall20,493,391);
        wall20.setLocation(486,384);
        wall20.setLocation(486,385);
        wall10.setLocation(337,481);
        wall9.setLocation(386,480);
        Wall wall21 = new Wall();
        addObject(wall21,443,487);
        wall21.setLocation(436,480);
        Wall wall22 = new Wall();
        addObject(wall22,494,484);
        wall22.setLocation(486,480);
        Wall wall23 = new Wall();
        addObject(wall23,542,391);
        wall23.setLocation(536,385);
        Wall wall24 = new Wall();
        addObject(wall24,541,441);
        wall24.setLocation(535,433);
        Wall wall25 = new Wall();
        addObject(wall25,542,488);
        wall25.setLocation(536,481);
        Wall wall26 = new Wall();
        addObject(wall26,553,30);
        wall26.setLocation(550,25);
        wall16.setLocation(291,107);
        wall17.setLocation(292,57);
        wall18.setLocation(293,23);
        wall16.setLocation(290,120);
        wall17.setLocation(292,73);
        wall16.setLocation(290,122);
        Wall wall27 = new Wall();
        addObject(wall27,296,179);
        wall27.setLocation(290,172);
        Wall wall28 = new Wall();
        addObject(wall28,556,80);
        Wall wall29 = new Wall();
        addObject(wall29,536,132);
        Wall wall30 = new Wall();
        addObject(wall30,537,187);
        wall30.setLocation(537,180);
        wall26.setLocation(545,19);
        wall28.setLocation(545,68);
        wall29.setLocation(544,117);
        wall30.setLocation(544,168);
        wall30.setLocation(544,167);
        SpeedCola speedcola = new SpeedCola();
        addObject(speedcola,845,480);
        speedcola.setLocation(846,574);
        AmmoSpot ammospot = new AmmoSpot();
        addObject(ammospot,30,304);
    }

    /**
     * Generates the 1st level of the game, and all levels that end in the
     * number 7 (7,17,27,...).
     */
    public void makeLevel7()
    {
        Wall wall = new Wall();
        addObject(wall,608,302);
        Wall wall2 = new Wall();
        addObject(wall2,599,256);
        Wall wall3 = new Wall();
        addObject(wall3,596,351);
        Wall wall4 = new Wall();
        addObject(wall4,361,445);
        Wall wall5 = new Wall();
        addObject(wall5,421,452);
        Wall wall6 = new Wall();
        addObject(wall6,485,457);
        wall5.setLocation(435,449);
        wall6.setLocation(485,449);
        wall4.setLocation(385,449);
        wall3.setLocation(607,351);
        wall2.setLocation(607,255);
        Wall wall7 = new Wall();
        addObject(wall7,446,146);
        Wall wall8 = new Wall();
        addObject(wall8,503,152);
        wall8.setLocation(495,146);
        Wall wall9 = new Wall();
        addObject(wall9,401,152);
        wall9.setLocation(397,146);
        Wall wall10 = new Wall();
        addObject(wall10,275,363);
        wall10.setLocation(272,352);
        Wall wall11 = new Wall();
        addObject(wall11,277,311);
        wall11.setLocation(272,302);
        Wall wall12 = new Wall();
        addObject(wall12,275,260);
        wall12.setLocation(272,255);
        AmmoSpot ammospot = new AmmoSpot();
        addObject(ammospot,355,312);
        ammospot.setLocation(332,305);
    }

    /**
     * Generates the 1st level of the game, and all levels that end in the
     * number 8 (8,18,28,...).
     */
    public void makeLevel8()
    {
        Wall wall = new Wall();
        addObject(wall,102,583);
        Wall wall2 = new Wall();
        addObject(wall2,146,500);
        Wall wall3 = new Wall();
        addObject(wall3,187,422);
        Wall wall4 = new Wall();
        addObject(wall4,256,333);
        Wall wall5 = new Wall();
        addObject(wall5,395,220);
        Wall wall6 = new Wall();
        addObject(wall6,558,320);
        Wall wall7 = new Wall();
        addObject(wall7,638,412);
        Wall wall8 = new Wall();
        addObject(wall8,707,511);
        wall8.setLocation(684,488);
        Wall wall9 = new Wall();
        addObject(wall9,705,577);
        wall4.setLocation(279,326);
        wall3.setLocation(214,416);
        wall2.setLocation(178,501);
        wall.setLocation(153,579);
        wall5.setLocation(428,221);
        StaminUp staminup = new StaminUp();
        addObject(staminup,850,576);
        staminup.setLocation(844,574);
        AmmoSpot ammospot = new AmmoSpot();
        addObject(ammospot,410,554);
        ammospot.setLocation(417,556);
    }

    /**
     * Generates the 1st level of the game, and all levels that end in the
     * number 9 (9,19,29,...).
     */
    public void makeLevel9()
    {
        Wall wall = new Wall();
        addObject(wall,319,159);
        Wall wall2 = new Wall();
        addObject(wall2,533,163);
        Wall wall3 = new Wall();
        addObject(wall3,704,327);
        wall3.setLocation(695,316);
        Wall wall4 = new Wall();
        addObject(wall4,168,324);
        wall4.setLocation(158,311);
        Wall wall5 = new Wall();
        addObject(wall5,530,517);
        Wall wall6 = new Wall();
        addObject(wall6,329,490);
        wall5.setLocation(543,483);
        wall6.setLocation(329,478);
        AttackDog attackdog = new AttackDog(0);
        addObject(attackdog,583,403);
        AttackDog attackdog2 = new AttackDog(180);
        addObject(attackdog2,298,223);
        removeObject(attackdog2);
        AttackDog attackdog3 = new AttackDog(90);
        addObject(attackdog3,225,211);
        attackdog3.setLocation(231,214);
        AmmoSpot ammospot = new AmmoSpot();
        addObject(ammospot,480,238);
        ammospot.setLocation(427,165);
    }

    /**
     * Generates the 1st level of the game, and all levels that end in the
     * number 10 (10,20,30,...). Level 10 is intentionally left as a mostly
     * blank level in order to challenge the player.
     */
    public void makeLevel10()
    {
        AmmoSpot ammospot = new AmmoSpot();
        addObject(ammospot,480,238);
        ammospot.setLocation(427,165);
    }

    /**
     * Sets instakill to be true or false.
     */
    public void setInstaKill(boolean boo)
    {
        if(boo==true)
            isInstaKill=true;
        else
            isInstaKill=false;
    }

    /**
     * Returns whether or not instaKill is on.
     */
    public boolean getInstaKill()
    {
        return isInstaKill;
    }

    /**
     * Returns the player class in the game for other classes to use.
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Draws an image representation of the String str at the given x & y 
     * coordinates.
     */
    public void drawText(String str, int x, int y) 
    {
        GreenfootImage imageText = new GreenfootImage(str, 40, java.awt.Color.RED, java.awt.Color.GRAY); 
        getBackground().drawImage(imageText, x, y);
    }

    /**
     * Returns the enemy class in the game for other classes to use.
     */
    public Enemy getEnemy(Enemy e)
    {
        return e;
    }

    /**
     * Returns the wall class in the game for other classes to use.
     */
    public Wall getWall(Wall w)
    {
        return w;
    }

    /**
     * Returns the player's x position.
     */
    public int getPlayerX()
    {
        return player.getX();
    }

    /**
     * Returns the player's y position.
     */
    public int getPlayerY()
    {
        return player.getY();
    }

    /**
     * Returns the player's health.
     */
    public int getPlayerHealth()
    {
        return player.health;
    }

    /**
     * Returns the x-length of the world (in cells).
     */
    public int getMaxX()
    {
        return MAX_X;
    }

    /**
     * Returns the y-length of the world (in cells).
     */
    public int getMaxY()
    {
        return MAX_Y;
    }

    /**
     * Adds a bullet to the world at the given x & y coordinates and returns
     * the bullet as an object.
     */
    public Bullet addBullet(int X, int Y)
    {
        Bullet bullet = new Bullet();
        addObject(bullet,X,Y);
        return bullet;
    }

    /**
     * Adds the Powerup p to the world at the given x & y coordinates.
     */
    public void addPowerUp(int X, int Y, Powerup p)
    {
        addObject(p,X,Y);
    }

    /**
     * Adds the Zombie z to the world at the given x & y coordinates.
     */
    public void addZombie(int X, int Y, Zombie z)
    {
        addObject(z,X,Y);
    }

    /**
     * Draws an image representation of the String str in the bottom left
     * corner of the screen.
     */
    public void drawText(String str) 
    {
        GreenfootImage imageText = new GreenfootImage(str, 40, java.awt.Color.RED, java.awt.Color.GRAY); 
        getBackground().drawImage(imageText, 50, MAX_Y-75);

    }

    /**
     * Prints out a welcome message for the player in the JOptionPane when they 
     * begin the game.
     */
    public static int printWelcome()
    {
        int n=0;
        if(welcomeCount==0)
        {
            Object[] actions = {"Ok"};

            n = JOptionPane.showOptionDialog(null,welcome(),"",JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,actions,actions[0]);
            welcomeCount++;
            if(n==-1)
                return -1;
            else
                return 1;
        }
        return 0;
    }

    /**
     * Returns the welcome message for the player when they begin the game.
     */
    private static String welcome()
    {
        return "Welcome to Call of Greenfoot Zombies! Your goal is"+"\n"
        +"to survive for as many rounds as you can without being"+"\n"
        +"eaten by zombies. You can move up/down/left/right with"+"\n"
        +"w/s/a/d, sprint with the spacebar, fire your weapon by"+"\n"
        +"clicking the mouse, reload with r, and interact with"+"\n"
        +"doors, ammo stations, and perks (perminant power-ups)"+"\n"
        +"with e. Once all of the zombies in a round have been"+"\n"
        +"eliminated, players can go through the door to advance"+"\n"
        +"the round. Zombies get progressively stronger and faster"+"\n"
        +"as the rounds advance, but they are also dumb, and can only" +"\n"
        +"move in one direction when next to a wall. Use that to"+"\n"
        +"your advantage! All of the music, perk ideas and powerup" +"\n"
        +"ideas are based off of the Call of Duty Zombies series. Hit"+"\n"
        +"the run button to start the game. Good luck!";
    }
}
