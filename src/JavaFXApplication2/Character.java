
package JavaFXApplication2;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


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
    
    void moveZombie(){
        
        playerx=PlayerLocation.xx;
        playery=PlayerLocation.yy;
        
        if (playerx>getTranslateX() & playery>getTranslateY()){
            setTranslateX(getTranslateX()+(playerx-getTranslateX())*0.005);
            setTranslateY(getTranslateY()+(playery-getTranslateY())*0.005);
        }
        else if (playerx<getTranslateX() & playery>getTranslateY()){
            setTranslateX(getTranslateX()-(getTranslateX()-playerx)*0.005);
            setTranslateY(getTranslateY()+(playery-getTranslateY())*0.005);
        }
        else if (playerx>getTranslateX() & playery<getTranslateY()){
            setTranslateX(getTranslateX()+(playerx-getTranslateX())*0.005);
            setTranslateY(getTranslateY()-(getTranslateY()-playery)*0.005);
        }
        else if (playerx<getTranslateX() & playery<getTranslateY()){
            setTranslateX(getTranslateX()-(getTranslateX()-playerx)*0.005);
            setTranslateY(getTranslateY()-(getTranslateY()-playery)*0.005);
        }
        
    }
    
    }

