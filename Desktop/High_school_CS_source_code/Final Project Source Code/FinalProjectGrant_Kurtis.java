import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.ArrayList; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.JTextField; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FinalProjectGrant_Kurtis extends PApplet {








Environment e;
PFont f;
boolean running;
final float TOLERANCE=10;
final int LENGTH=1250;
final int WIDTH=700;
final static int FIELD=50;
private final int WALL_HEIGHT = 450;
private final int WALL_WIDTH = 10;
private final int GOAL_HEIGHT = 100;
private final int GOAL_WIDTH = 70;
private boolean isBField=false;

/**
 * Sets up the game and Environment.
 */
public void setup() 
{
  
  e = new Environment(); 
  running=false;
}

/**
 * Draws in the electric and magnetic fields, the walls, charges, goal, direction of 
 * net acceleration of the test charge, and path taken by the test charge. Also
 * updates these displays as the test charge moves.
 */
public void draw() 
{
  background(255);
  if (isBField==true)
    e.b.drawField();
  e.display();
  if (running==true)
    e.update();
}

/**
 * Performs different actions based on which key is pressed, as specified in the
 * "printHelp()" method.
 */
public void keyPressed()
{
  if (key=='p' && running==false)
  {
    Charge c1=new Charge(.01f, 1, mouseX, mouseY);
    e.addSourceCharge(c1);
  } else if (key=='n' && running==false)
  {
    Charge c1=new Charge(-.01f, 1, mouseX, mouseY);
    e.addSourceCharge(c1);
  } else if (key=='s' && running==false)
  {
    e.win=false;
    if (e.isAlive==true)
    {
      e.restart();
      running=true;
    } else
      e.restart();
  } else if (key=='s' && running==true)
  {
    e.win=false;
    running=false;
    e.restart();
  } else if (key=='r')
  {
    running=false;
    e.restart();
    e.charges=new ArrayList<Charge>();
    if (e.isLevelCreator==true)
      e.walls= new ArrayList<Wall>();
  } else if (key=='e' && running==false)
  {
    for (int i=0; i<e.charges.size(); i++)
    {
      float x=(float)(e.charges.get(i).x);
      float y=(float)(e.charges.get(i).y);
      float distance=sqrt((mouseX-x)*(mouseX-x)+(mouseY-y)*(mouseY-y));
      if (distance<TOLERANCE)
      {
        e.charges.remove(i);
        i=e.charges.size();
      }
    }
  } else if (key=='w' && e.isLevelCreator==true)
  {
    Wall wally = new Wall(mouseX-(WALL_WIDTH/2), mouseY-(WALL_HEIGHT/2), WALL_WIDTH, WALL_HEIGHT);
    e.walls.add(wally);
  } else if (key=='l')
  {
    running=false;
    e.restart();
    e.charges=new ArrayList<Charge>();
    e.walls= new ArrayList<Wall>();
    e.generateLevel();
  }
}

/**
 * The main class for this project. The Environment class has several functions,
 * including keeping track of and updating the position, velocity, and acceleration
 * of the test charge, generating and updating the display, calculating the acceleration
 * of the test charge based on the electromagnetic field it is in, and putting in new
 * source charges when the user hits the p/n keys. Overall, this class does much of the
 * "heavy lifting" in this project.
 */
class Environment {
  PVector location;
  PVector velocity;
  PVector acceleration;
  PVector mouse2;
  PVector position;
  boolean isAlive;
  boolean isLevelCreator;
  Charge testCharge;
  final int SIZE=20;
  final double K=8.9876f*pow((float)10, (float)9); //Coulomb's constant
  public ArrayList <Charge> charges=new ArrayList <Charge>();
  ArrayList <PVector> positions=new ArrayList <PVector>();
  ArrayList <PVector> moves=new ArrayList <PVector>();
  ArrayList<Wall> walls = new ArrayList<Wall>();
  boolean win;
  MagneticField b;
  Wall wall1;
  Wall wall2;
  Wall wall3;
  Wall wall4;
  Goal goal;

  /**
   * Constructor for objects of class Environment.
   */
  public Environment() {
    isLevelCreator=false;
    location = new PVector(40, height/2);
    position=location;
    velocity = new PVector(0, 0);
    acceleration=new PVector(0, 0);
    testCharge=new Charge(.01f, 500, 40, height/2);
    b=new MagneticField(0, 0, 0);
    generateLevel();
    isAlive=true;
    win=false;
  }

  /**
   * Generates the level chosen by the player.
   */
  public void generateLevel()
  {
    int level=getLevel();
    if (level!=4)
    {
      isLevelCreator=false;
    }
    if (level == 1)
    {
      wall1 = new Wall(400, 0, WALL_WIDTH, WALL_HEIGHT);
      walls.add(wall1);
      wall2 = new Wall(800, 700-WALL_HEIGHT, WALL_WIDTH, WALL_HEIGHT);
      walls.add(wall2);
      goal = new Goal(1200, 300, GOAL_WIDTH, GOAL_HEIGHT);
      b=new MagneticField(0, 0, 0);
    } else if (level == 0)
    {
      wall1 = new Wall(550, 0, WALL_WIDTH, 325);
      walls.add(wall1);
      wall2 = new Wall(550, 700 - 325, WALL_WIDTH, 325);
      walls.add(wall2);
      goal = new Goal(1200, 300, GOAL_WIDTH, GOAL_HEIGHT);
      b=new MagneticField(0, 0, 0);
    } else if (level == 2)
    {
      walls.add(new Wall(350, 0, 10, 275));  
      walls.add(new Wall(350, 700 - 275, 10, 275));
      walls.add(new Wall(325, 265, 60, 10));
      walls.add(new Wall(325, 700 - 275, 60, 10));
      walls.add(new Wall(625, 700 - 550, 10, 550));
      walls.add(new Wall(600, 700-550, 60, 10));
      walls.add(new Wall(900, 0, 10, 400));  
      walls.add(new Wall(900, 700 - 150, 10, 150));
      walls.add(new Wall(875, 400, 60, 10));
      walls.add(new Wall(875, 700 - 150, 60, 10));
      b=new MagneticField(0, 0, 0);
      goal = new Goal(1200, 300, GOAL_WIDTH, GOAL_HEIGHT);
    } else if (level == 3)
    {
      walls.add(new Wall(350, 0, 10, 400));  
      walls.add(new Wall(325, 400, 60, 10));
      walls.add(new Wall(625, 700 - 550, 10, 550));
      walls.add(new Wall(600, 700-550, 60, 10));
      walls.add(new Wall(900, 0, 10, 275));  
      walls.add(new Wall(900, 700 - 275, 10, 275));
      walls.add(new Wall(875, 265, 60, 10));
      walls.add(new Wall(875, 700 - 275, 60, 10));
      b=new MagneticField(200, 500, 300);
      isBField=true;
      goal = new Goal(1200, 300, GOAL_WIDTH, GOAL_HEIGHT);
    } else if (level==4)
    {
      goal = new Goal(1200, 300, GOAL_WIDTH, GOAL_HEIGHT);
      isLevelCreator=true;
      b=new MagneticField(0, 0, 0);
    } else if (level==5 || level==-1)
    {
      goal = new Goal(1200, 300, GOAL_WIDTH, GOAL_HEIGHT);
      b=new MagneticField(0, 0, 0);
    }
  }


  /**
   * Updates the position, acceleration, and velocity of the test charge as it moves
   * through the electric field generated by the source charges.
   */
  public void update() {
    checkWallsAndGoal();
    position = new PVector((float)testCharge.x, (float)testCharge.y);
    acceleration=getTotalAcceleration();
    if (isBField && b.inField(testCharge))
    {
      PVector p=getMagneticFieldAcceleration();
      acceleration.add(p);
    }
    velocity.add(acceleration);
    position.add(velocity);
    testCharge.x=position.x;
    testCharge.y=position.y;
    moves.add(position);
  }

  /**
   * Checks to see whether or not the charge has intersected a wall or goal, and ends the
   * game if the test charge has.
   */
  public void checkWallsAndGoal()
  {
    for (int i = 0; i < walls.size(); i++)
    {
      Wall temp = walls.get(i);
      if (temp.collisionTest(testCharge))
      {
        running = false;
        isAlive=false;
      }
    }
    if (goal.collisionTest(testCharge))
    {
      running = false;
      isAlive=false;
      win=true;
    }
  }

  /**
   * Prints out a message telling the player that they won.
   */
  public void win()
  {
    if (win==true)
    {
      textSize(52);
      text("You win!", width/2-100, height/2); 
      fill(100, 102, 153);
    }
  }

  /**
   * Prints out a message telling the player that the test charge collided with a wall.
   */
  public void collide()
  {
    if (isAlive==false && win==false)
    {
      textSize(52);
      fill(100, 200, 300);
      text("Collision!", width/2-100, height/2);
    }
  }



  /**
   * Essentially calls the Environment constructor in order to restart the test charge.
   */
  public void restart()
  {
    location = new PVector(40, height/2);
    position=location;
    velocity = new PVector(0, 0);
    acceleration=new PVector(0, 0);
    testCharge=new Charge(.01f, 200, 40, height/2);
    isAlive=true;
    moves=new ArrayList <PVector>();
  }

  /**
   * Prompts the player to select a level, and returns the selection in the array
   * as an int.
   */
  public int getLevel()
  {
    Object[] options = {"Level 1", "Level 2", "Level 3", "Level 4", "Level Editor", "Practice"};
    int input = JOptionPane.showOptionDialog(frame, 
      printHelp()+"\n"+"\n"+"Select a level below.", "", 
      JOptionPane.PLAIN_MESSAGE, 
      JOptionPane.QUESTION_MESSAGE, 
      null, 
      options, 
      options[0]); 
    return input;
  }

  /**
   * Returns the acceleration of the test charge due to the other charges.
   */
  public PVector getAcceleration(Charge source) 
  {
    double q=testCharge.getMag();
    double Q=source.getMag();
    double m=testCharge.getMass();

    double x1=source.x;
    double x2=testCharge.x;
    double y1=source.y;
    double y2=testCharge.y;

    double deltaX=x1-x2;
    double deltaY=y1-y2;

    double r=sqrt(pow((float)(y1-y2), 2)+pow((float)(x1-x2), 2));
    PVector p=new PVector((float)deltaX, (float)deltaY);
    p.normalize();
    double magnitude=1;
    magnitude=(K*q*Q)/(r*r*m);

    p.mult(-1*(float)magnitude);
    return p;
  }

  /**
   * Returns a vector representing the electric field due to a source charge at a given
   * point on the board.
   */
  public PVector getElectricField(Charge source, double x1, double y1)
  {
    double Q=source.getMag();
    double x2=source.x;
    //double x2=testCharge.x;
    double y2=source.y;
    //double y2=testCharge.y;
    double deltaX=x2-x1;
    double deltaY=y2-y1;

    double r=sqrt(pow((float)(y1-y2), 2)+pow((float)(x1-x2), 2));
    PVector p=new PVector((float)deltaX, (float)deltaY);
    p.normalize();
    double magnitude=1;
    magnitude=(K*Q)/(r*r);

    p.mult(-1*(float)magnitude);
    return p;
  }

  /**
   * Returns the net electric field at a point on the board due to the source charges.
   */
  public PVector getTotalElectricField(double x1, double y1)
  {
    PVector total=new PVector(0, 0);
    for (int i=0; i<charges.size(); i++)
    {
      Charge c1=charges.get(i);
      total.add(getElectricField(c1, x1, y1));
    }
    return total;
  }

  /**
   * Returns the net acceleration due to all of the source charges on the test charge.
   */
  public PVector getTotalAcceleration()
  {
    PVector total=new PVector(0, 0);
    for (int i=0; i<charges.size(); i++)
    {
      //PVector loc=positions.get(i);
      Charge c1=charges.get(i);
      total.add(getAcceleration(c1));
    }
    return total;
  }

  /**
   * Returns the acceleration of the test charge in the region of magnetic field
   */
  public PVector getMagneticFieldAcceleration()
  {
    PVector velo3D=new PVector(velocity.x, velocity.y, 0);
    PVector field3D=new PVector(0, 0, (float)b.getStrength());
    PVector cross=velo3D.cross(field3D);
    cross.normalize();

    double m=testCharge.getMass();
    double q=testCharge.getMag();
    double v=velocity.mag();
    double bMag=b.getStrength();
    double mag=q*v*bMag/m;

    PVector p=new PVector(cross.x, cross.y);
    p.mult((float)mag);
    return p;
  }


  /**
   *Adds the given source charge to the ArrayList of test charges, and its position
   *to the ArrayList of positions.
   */
  public void addSourceCharge(Charge c1)
  {
    charges.add(c1);
    PVector p=new PVector((float)c1.x, (float)c1.y);
    positions.add(p);
  }

  /**
   * Displays the source charges. 
   */
  public void displaySourceCharges()
  {
    for (int i=0; i<charges.size(); i++)
    {
      stroke(100);
      strokeWeight(2);
      if (charges.get(i).getMag()>0)
      {
        fill(100, 500, 40);
      } else
      {
        fill(200, 40, 100);
      }

      ellipse((float)charges.get(i).x, (float)charges.get(i).y, SIZE, SIZE);
      stroke(0);
      line((float)(charges.get(i).x+7), (float)(charges.get(i).y), (float)(charges.get(i).x-7), (float)(charges.get(i).y));
      if (charges.get(i).getMag()>0)
      {
        line((float)charges.get(i).x, (float)charges.get(i).y+7, (float)charges.get(i).x, (float)charges.get(i).y-7);
      }
    }
  }

  /**
   *Displays the path taken by the test charge
   */
  public void displayPath()
  {
    for (int i=0; i<moves.size()-1; i++)
      if (moves.size()>2 && i%5==0)
      {
        double x1=moves.get(i).x;
        double x2=moves.get(i+1).x;
        double y1=moves.get(i).y;
        double y2=moves.get(i+1).y;
        stroke(400, 0, 0);
        line((float)x1, (float) y1, (float)x2, (float)y2);
      }
  }

  /**
   * Draws a vector representing the net acceleration of the test charge.
   */
  public void drawNetAcceleration()
  {
    double x1=position.x;
    double y1=position.y;
    double x2=x1+100*acceleration.x;
    double y2=y1+100*acceleration.y;

    stroke(0, 400, 0);
    strokeWeight(3);
    drawArrow((float) x1, (float) y1, (float) x2, (float) y2);
  }

  /**
   * Displays the goal in yellow on the screen.
   */
  public void displayGoal()
  {
    stroke(100);
    fill(500, 100, 30);
    goal.displayWall();
  }

  /**
   * Displays all of the walls on the screen in black.
   */
  public void displayWalls()
  {
    stroke(0);
    fill(0);
    for (int i = 0; i < walls.size(); i++)
    {
      Wall temp = walls.get(i);
      temp.displayWall();
    }
  }

  /**
   * Displays the test charge along with the source charges, as well as the path
   * that the test charge takes.
   */
  public void display() {
    stroke(255);
    strokeWeight(2);
    fill(127);
    ellipse(position.x, position.y, SIZE, SIZE);
    displaySourceCharges();
    displayPath();
    displayWalls();
    displayGoal();
    acceleration=getTotalAcceleration();
    displayElectricField();
    drawNetAcceleration();
    collide();
    win();
  }

  /**
   * Prints a help message explaining how to control the simulation.
   */
  public String printHelp()
  {
    return "Hello and welcome to Electric Field Hockey, Processing edition!" +"\n"
      +"Your goal is to guide the test charge into the yellow goal on the right"+"\n"
      +"side of the screen using the positive and negative source charges."+"\n"
      +"If you hit a wall, you have to restart from the beginning. To"+"\n"
      +"place negative source charges, hold your mouse over the desired"+"\n"
      +"location and press the \"n\" key. For positive charges, press the"+"\n"
      +"\"p\" key. You can remove charges that you have already placed by"+"\n"
      +"pressing the \"e\" key. The \"r\" key removes all of the source"+"\n"
      +"charges at once. The \"s\" key starts/stops the game. The blue-to-"+"\n"
      +"red lines on the screen represent electric field vectors and indicate"+"\n"
      +"which direction a positive test charge would accelerate at that point"+"\n"
      +"on the board. Thicker lines mean stronger electric field in that"+"\n"
      +"region. The X's and dots on the screen represent regions of"+"\n"
      +"magnetic field. X's represent magnetic field vectors coming"+"\n"
      +"into the page, whereas dots represent field lines coming out of"+"\n"
      +"the page. The test charge will accelerate perpendicular to both"+"\n"
      +"the velocity of the charge and the magnetic field vectors. Lastly, to"+"\n"
      +"change the level you are playing, press \"l\". If you are playing the"+"\n"
      +"\"level editor\" level, you can use \"w\" to add walls, and they can be"+"\n"
      +"removed by using the \"r\" key. Good luck, and have fun!";
  }

  /**
   * Draws an arrow on the screen connecting the beginning point to the end point.
   */
  public void drawArrow(double x1, double y1, double x2, double y2)
  {
    stroke(0, 400, 0);
    line((float) x1, (float) y1, (float) x2, (float) y2);
    stroke(400, 0, 0);
    double halfX=(1.0f/2.0f)*(x1+x2);
    double halfY=(1.0f/2.0f)*(y1+y2);
    double almostX2=(1.0f/2.0f)*(halfX+x2);
    double almostY2=(1.0f/2.0f)*(halfY+y2);
    line((float) x2, (float) y2, (float) almostX2, (float) almostY2);
  }

  /**
   * Draws different colored arrows to represent the electric field lines.
   */
  public void drawArrow2(double x1, double y1, double x2, double y2)
  {
    stroke(0, 0, 400);
    line((float) x1, (float) y1, (float) x2, (float) y2);
    stroke(400, 0, 0);
    double halfX=(1.0f/2.0f)*(x1+x2);
    double halfY=(1.0f/2.0f)*(y1+y2);
    double almostX2=(1.0f/2.0f)*(halfX+x2);
    double almostY2=(1.0f/2.0f)*(halfY+y2);
    line((float) x2, (float) y2, (float) almostX2, (float) almostY2);
  }


  /**
   * Draws the electric field vectors at different positions along the game board.
   */
  public void displayElectricField()
  {
    for (int i=0; i<=LENGTH; i+=FIELD)
    {
      for (int j=0; j<=WIDTH; j+=FIELD)
      {
        PVector E=getTotalElectricField(i, j);
        if (E.mag()<2000)
          strokeWeight(E.mag()/500);
        else
          strokeWeight(4);
        E.normalize();
        E.mult(15);
        float x1=i;
        float x2=x1+E.x;
        float y1=j;
        float y2=y1+E.y;
        drawArrow2(x1, y1, x2, y2);
      }
    }
  }
}
/**
 * Charges are objects that feel forces towards/away from one another (depending on 
 * their relative sign, i.e. opposites attract and likes repel). The test charge
 * is free to move, while the source charges are fixed in place.
 */
public class Charge {

  public  double x;
  public  double y;
  private double mag;
  private double mass;

  public Charge(double Mag, double Mass, double X, double Y)
  {
    mag=Mag;
    mass=Mass;
    x=X;
    y=Y;
  }

  /**
   * Returns the charge strength (magnitude) of the charge.
   */
  public double getMag()
  {
    return mag;
  }

  /**
   * Returns the mass of the charge.
   */
  public double getMass()
  {
    return mass;
  }

  /**
   * Returns true if two charges have the same mass, coordinates, and charge, false otherwise.
   */
  public boolean equals(Charge c1, Charge c2)
  {
    if (c1.x==c2.x && c1.y==c2.y && c1.mag==c2.mag && c1.mass==c2.mass)
    {
      return true;
    } else
      return false;
  }
}
/**
 * A subclass of wall. Really more of a "placeholder" class that allows us to 
 * differentiate between between the Goal and regular Walls when the player collides
 * with one.
 */
public class Goal extends Wall {

  /**
   * Constructor for objects of class Goal.
   */
  public Goal(int x, int y, int h, int w)
  {
    super(x, y, h, w);
  }
}
/**
 * The MagneticField class is essentially a rectangular region on the board that 
 * accelerates the test charge in different directions. The direction the charge
 * will accelerate is always perpendicular to both the field and the velocity
 * of the test charge. It is represented on the grid by a rectangular region 
 * containing either x's (field into the screen) or dots (field out of the screen).
 */
public class MagneticField {
  public float x;
  public float w;
  private float strength;

  /**
   * Constructor for objects of class MagneticField.
   */
  public MagneticField(float X, float Strength, float Width)
  {
    x=X;
    strength=Strength;
    w=Width;
  }

  /**
   * Draws the region of uniform magnetic field on the board, and indicates whether the 
   * field is coming into or out of the page.
   */
  public void drawField()
  {
    stroke(0);
    strokeWeight(.5f);
    fill(255);
    rect(x, 0, w, height);

    for (int i=(int)x; i<=x+w; i+=FinalProjectGrant_Kurtis.FIELD)
    {
      for (int j=0; j<=height; j+=FinalProjectGrant_Kurtis.FIELD)
      {
        if (strength>0)
          drawX(i, j);
        else
        {
          fill(0);
          ellipse(i, j, 4, 4);
        }
      }
    }
  }

  public void drawX(float x, float y)
  {
    stroke(0);
    strokeWeight(1);
    line(x-10, y+10, x+10, y-10);
    line(x-10, y-10, x+10, y+10);
  }

  /**
   * Returns true if the test charge is within the range of the magnetic field, false otherwise.
   */
  public boolean inField(Charge c)
  {
    if (c.x>x && c.x<(x+w))
      return true;
    else
      return false;
  }

  /**
   * Returns the strength of the magnetic field
   */
  public double getStrength()
  {
    return strength;
  }
}
/**
 * Walls are objects that the test charge must try to avoid. If a test charge hits a wall,
 * the game ends and the player has to start over.
 */
public class Wall {
  private int xCoord;
  private int yCoord;
  private int wallHeight;
  private int wallWidth;

  /**
   * Constructor for objects of class Wall.
   */
  public Wall(int x, int y, int wallW, int wallH)
  {
    xCoord = x;
    yCoord = y;
    wallHeight = wallH;
    wallWidth = wallW;
    rect(x, y, wallH, wallW);
  }

  /**
   * Displays the rectangular wall on the screen.
   */
  public void displayWall()
  {
    rect(xCoord, yCoord, wallWidth, wallHeight);
  }

  /**
   * Determines whether or not the charge collided with a wall.
   */
  public boolean collisionTest(Charge c)
  {
    if ((c.x >= xCoord && c.x <= xCoord + wallWidth)&&(c.y >= yCoord && c.y <= yCoord + wallHeight))
    {
      return true;
    } else return false;
  }

  /**
   * Returns the x-coordinate of the wall.
   */
  public int getX()
  {
    return xCoord;
  }

  /**
   * Returns the y-coordinate of the wall.
   */
  public int getY()
  {
    return yCoord;
  }

  /**
   * Returns the height of the wall.
   */
  public int getHeight()
  {
    return wallHeight;
  }

  /**
   * Returns the width of the wall.
   */
  public int getWidth()
  {
    return wallWidth;
  }

  /**
   * Sets the x-coordinate of the wall.
   */
  public void setX(int newX)
  {
    xCoord = newX;
  }

  /**
   * Sets the y-coordinate of the wall.
   */
  public void setY(int newY)
  {
    yCoord = newY;
  }

  /**
   * Sets the width of the wall.
   */
  public void setWidth(int newWidth)
  {
    wallWidth = newWidth;
  }

  /**
   * Sets the height of the wall.
   */
  public void setHeight(int newHeight)
  {
    wallHeight = newHeight;
  }
}
  public void settings() {  size(1250, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FinalProjectGrant_Kurtis" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
