
package JavaFXApplication2;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Character extends Rectangle{
    
    boolean dead=false;
    private double mousex;
    private double mousey;
    static double playerx=PlayerLocation.xx;
    static double playery=PlayerLocation.yy;
        
    boolean reloading = false;
    long StartTime;
    double Age;
        
    final String type;
        
    Random rnd = new Random();
    double speedRandomizer;
        
    public double getAge(){
            Age = (System.nanoTime()-StartTime)*0.000000001;
            return Age;
        }

        
    Character(int x,int y,int w, int h,String type, Color color){
        super(w,h,color);
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
        this.StartTime = System.nanoTime();
        this.speedRandomizer = rnd.nextDouble();
        while (this.speedRandomizer < 0.004 | this.speedRandomizer > 0.006){this.speedRandomizer = rnd.nextDouble();}
    }
        

    Character(int x,int y,int w, int h,String type, Color color,double xx,double yy){
        super(w,h,color);
        this.type = type;
        setTranslateX(x);
        setTranslateY(y);
        mousex = xx;
        mousey = yy;
        this.StartTime = System.nanoTime();
  
    }
    
    public double returnMouseX(){
        return mousex;
        }
    
    public double returnMouseY(){
        return mousey;
        }
        
    void moveLeft(){
        setTranslateX(getTranslateX() -8);
    }
    
    void moveRight(){
        setTranslateX(getTranslateX() +8);
    }
    void moveUp(){
        setTranslateY(getTranslateY()-8);
    }
    
    void moveDown(){
        setTranslateY(getTranslateY()+8);
    }
    
    void moveBullet(){
        
        if (mousex>getTranslateX() & mousey>getTranslateY()){
            setTranslateX(getTranslateX()+(mousex-getTranslateX())*0.05);
            setTranslateY(getTranslateY()+(mousey-getTranslateY())*0.05);
        }
        else if (mousex<getTranslateX() & mousey>getTranslateY()){
            setTranslateX(getTranslateX()-(getTranslateX()-mousex)*0.05);
            setTranslateY(getTranslateY()+(mousey-getTranslateY())*0.05);
        }
        else if (mousex>getTranslateX() & mousey<getTranslateY()){
            setTranslateX(getTranslateX()+(mousex-getTranslateX())*0.05);
            setTranslateY(getTranslateY()-(getTranslateY()-mousey)*0.05);
        }
        else if (mousex<getTranslateX() & mousey<getTranslateY()){
            setTranslateX(getTranslateX()-(getTranslateX()-mousex)*0.05);
            setTranslateY(getTranslateY()-(getTranslateY()-mousey)*0.05);
        }
        
        
    }
    
    private boolean clockRunning =false;
    private long startClocktime;
    private long timeElapsed;
    private int movingRight;
    
    
    
    void moveZombie(){
        
        playerx=PlayerLocation.xx;
        playery=PlayerLocation.yy;
        
        
        
        if (clockRunning==false){
            movingRight = movingRight * (-1);
            startClocktime = System.nanoTime();
            clockRunning = true;
        }
        timeElapsed = System.nanoTime()-startClocktime;
        if (clockRunning==true){
            //setTranslateX(getTranslateX() + 50*movingRight);
            if (timeElapsed*0.000000001>1.5){
            clockRunning=false;
        }
        }
        
        
        
        if (playerx>getTranslateX() & playery>getTranslateY()){
            setTranslateX(getTranslateX()+(playerx-getTranslateX())*speedRandomizer*Zombies.difficultyLevel);
            setTranslateY(getTranslateY()+(playery-getTranslateY())*speedRandomizer*Zombies.difficultyLevel);
        }
        else if (playerx<getTranslateX() & playery>getTranslateY()){
            setTranslateX(getTranslateX()-(getTranslateX()-playerx)*speedRandomizer*Zombies.difficultyLevel);
            setTranslateY(getTranslateY()+(playery-getTranslateY())*speedRandomizer*Zombies.difficultyLevel);
        }
        else if (playerx>getTranslateX() & playery<getTranslateY()){
            setTranslateX(getTranslateX()+(playerx-getTranslateX())*speedRandomizer*Zombies.difficultyLevel);
            setTranslateY(getTranslateY()-(getTranslateY()-playery)*speedRandomizer*Zombies.difficultyLevel);
        }
        else if (playerx<getTranslateX() & playery<getTranslateY()){
            setTranslateX(getTranslateX()-(getTranslateX()-playerx)*speedRandomizer*Zombies.difficultyLevel);
            setTranslateY(getTranslateY()-(getTranslateY()-playery)*speedRandomizer*Zombies.difficultyLevel);
        }
        
    }
    
    }

