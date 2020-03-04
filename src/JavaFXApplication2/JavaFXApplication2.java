
package JavaFXApplication2;


import javafx.scene.text.Font;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.control.Button;
import javafx.scene.paint.ImagePattern;
import java.awt.*;
import javafx.event.EventHandler; 
import javafx.event.ActionEvent; 
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;


public class JavaFXApplication2 extends Application {
    
    
    boolean gameOverscreen=false;

    Toolkit toolkit;
    int ammoleft=8;    
    public Media spawnZombieSound = new Media(getClass().getResource("Resources/audio/spawnzombiesound.mp3").toString());
    MediaPlayer playZombieSpawnSound =new MediaPlayer(spawnZombieSound);
    
    
    
    private Character createZombie(int i){
        Character s = new Character((90+i*100),300+i*10,30,30,"zombie",Color.RED);
        Image gameOverimage = new Image(getClass().getResource("Resources/images/Zombie.png").toString());
        ImagePattern gameOverimagePattern = new ImagePattern(gameOverimage);
        s.setFill(gameOverimagePattern);
        return s;
        }
    
    private void gameOver(){
        root.getChildren().clear();
        Image gameOverimage = new Image(getClass().getResource("Resources/images/gameOver.png").toString());
        ImagePattern gameOverimagePattern = new ImagePattern(gameOverimage);
        gameOver.setFill(gameOverimagePattern);
        root.getChildren().add(gameOver);
        root.getChildren().add(restart);
        restart.setLayoutX(WIDTH/2-30);
        root.getChildren().add(exitGame);
        gameOverscreen=true;
        }

    
    public Media StartMenuMusic = new Media(getClass().getResource("Resources/audio/StartMenuMusic.wav").toString());
    MediaPlayer playStartMenuMusic =new MediaPlayer(StartMenuMusic);
    
    Label ammoleftlabel = new Label("Ammo left: " + Integer.toString(ammoleft));
        
    Image gamePlayGround = new Image(getClass().getResource("Resources/images/gameBackGround.jpg").toString());
    ImagePattern gamePlayGroundPattern = new ImagePattern(gamePlayGround);
    
        
    private void StartMenu(){
        root.getChildren().clear();
        Image StartMenuImage = new Image(getClass().getResource("Resources/images/StartMenuImage.png").toString());
        
        ImagePattern StartMenuImagePattern = new ImagePattern(StartMenuImage);
        StartMenuScreen.setFill(StartMenuImagePattern);
        root.getChildren().add(StartMenuScreen);
        root.getChildren().add(StartGame);
        StartGame.setLayoutX(WIDTH/2);
        StartGame.setLayoutY(HEIGHT/2);
        StartMenuOn = true;
        playStartMenuMusic.play();
        }
    
    boolean StartMenuOn=false;
    boolean StartMenuShown=false;
    Button StartGame=new Button("Start Game");
    
    private Pane root = new Pane();
    
    int WIDTH=600,HEIGHT=800;
    
    Character gameBackground = new Character(0,0,WIDTH,HEIGHT,"StartMenuScreen",Color.RED);
    Character StartMenuScreen = new Character(0,0,WIDTH,HEIGHT,"StartMenuScreen",Color.RED);
    Character gameOver = new Character(0,0,WIDTH,HEIGHT,"gameOver",Color.RED);
    private Character player = new Character(300,500,40,40,"player",Color.BLUE);
    
    Button restart = new Button("Restart");
    Button exitGame = new Button("Quit");
    
    private List<Character> characters(){
        return root.getChildren().stream().filter(n -> !ammoleftlabel.equals(n) & !gameBackground.equals(n)).map(n -> (Character)n).collect(Collectors.toList());
        }
    
    
    private Parent createContent(){
        
        root.setPrefSize(WIDTH,HEIGHT);
        root.getChildren().add(player);
        Font font = Font.font("Verdana",20);
        ammoleftlabel.setMaxSize(50, 100);
        ammoleftlabel.setTranslateX(WIDTH-100);
        ammoleftlabel.setTranslateY(HEIGHT-50);
        ammoleftlabel.setFont(font);
        boolean qwe =root.getChildren().contains(ammoleftlabel);
        
        if(qwe==false){
            root.getChildren().add(ammoleftlabel);
            }
        
        AnimationTimer timer =new AnimationTimer(){
            @Override
            public void handle(long now){
                update();
            }
        };
        timer.start();
        return root;
    }
    
    
    private void nextLevel(){
        playZombieSpawnSound.stop();
        for(int i=0;i<(5+Zombies.roundNumber);i++){
            Character s = createZombie(i);
            root.getChildren().add(s);
            }
        playZombieSpawnSound.play();
    }
    
    
    private void update(){
        if (StartMenuShown == false & StartMenuOn==false)
            {
            StartMenu();
            }
        if (StartMenuOn==true){return;}
        playStartMenuMusic.dispose();

        if (player.dead==true & gameOverscreen==false)
            {
            gameOver();
            return;
            }
        
        PlayerLocation.xx=player.getTranslateX();
        PlayerLocation.yy=player.getTranslateY();
        
        characters().forEach(s ->{
            switch(s.type){
                case "zombie":
                    Zombies.zombiecount=Zombies.zombiecount+1;
                    s.moveZombie();
                    
                    if(s.getBoundsInParent().intersects(player.getBoundsInParent()))
                        {
                        player.dead=true;
                        }
                    break;
                case "playerbullet":{
                    s.moveBullet();
                    double asd=s.returnMouseX();
                    double asdy=s.returnMouseY();
                    if (s.getBoundsInParent().intersects(asd, asdy,3,3))
                            {
                            root.getChildren().remove(s);
                            s=null;
                            break;
                            }
                    if (s.getAge()>1)
                        {
                        root.getChildren().remove(s);
                        s=null;break;
                        }
                    
                    for(Character z:characters()){
                        
                        if(z.type=="zombie")
                            {
                            if(s.getBoundsInParent().intersects(z.getBoundsInParent()))
                                {
                                s.dead=true;
                                root.getChildren().remove(s);
                                s=null;
                                root.getChildren().remove(z);
                                z=null;
                                break;
                                }
                            }
                        }
                    };
                }
        });
        
        if (Zombies.zombiecount==0)
            { 
            nextLevel();
            Zombies.roundNumber++;
            }
        
        Zombies.zombiecount=0;
        
        if(ammoleft == 0){
            if (reloading ==false){
                startReloadTime = System.nanoTime();
                reloading = true;
                }
            timeElapsedReloading = System.nanoTime()-startReloadTime;
            Font font = Font.font("Verdana",10);
            ammoleftlabel.setFont(font);
            ammoleftlabel.setText("Reloading");
            if (timeElapsedReloading*0.000000001>5){
                ammoleft=7;reloading=false;}
            return;
            }
        root.getChildren().remove(ammoleftlabel);
        ammoleftlabel.setText(Integer.toString(ammoleft)+"/7");
        root.getChildren().add(ammoleftlabel);
    }
    
    
    @Override
    public void start(Stage stage){
        
        stage.setTitle("Zombie shooter game");
        
        PlayerLocation.xx=player.getTranslateX();
        PlayerLocation.yy=player.getTranslateX();
        Scene scene = new Scene(createContent());
        gameBackground.setFill(gamePlayGroundPattern);
    
        scene.setOnKeyPressed(e -> {
            
            switch (e.getCode()){
                case A:
                    if(player.getTranslateX()>0)
                        {player.moveLeft();}
                    break;
                case D:
                    if(player.getTranslateX()<WIDTH)
                    {player.moveRight();}
                    break;
                case W:
                    if(player.getTranslateY()>0)
                    {player.moveUp();}
                    break;
                case S:
                    if(player.getTranslateY()<HEIGHT)
                    {player.moveDown();}
                    break;
                case SPACE:
                    shoot(player);
                    break;
            }
            update();
            
        });
        
        restart.setOnAction(e -> {
            root.getChildren().clear();
            player.dead=false;
            gameOverscreen=false;
            player = new Character(300,500,40,40,"player",Color.BLUE);
            root.getChildren().add(gameBackground);
            root.getChildren().add(player);
            ammoleft=7;
            Zombies.roundNumber=0;
        });
        StartGame.setOnAction(e -> {
            root.getChildren().clear();
            player = new Character(300,500,40,40,"player",Color.BLUE);
            root.getChildren().add(gameBackground);
            root.getChildren().add(player);
            StartMenuOn=false;
            StartMenuShown=true;
        });
        
        exitGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }

        });
        
        stage.setScene(scene);
        stage.show();
        
    }
    
        public Media shootingSound = new Media(getClass().getResource("Resources/audio/shooting.mp3").toString());
        MediaPlayer playshootingSound =new MediaPlayer(shootingSound);
        Character bullet;
        boolean reloading = false;
        long startReloadTime;
        long timeElapsedReloading;
        
        public void shoot(Character shooter){

            if(ammoleft == 0){
                if (reloading ==false){
                    startReloadTime = System.nanoTime(); reloading = true;}
                timeElapsedReloading = System.nanoTime()-startReloadTime;
                if (timeElapsedReloading*0.000000001>5){
                    ammoleft=7;reloading=false;
                }
                return;
            }
            
        root.setOnMouseDragged(e -> {
            MouseXYdragged.xx = e.getX();
            MouseXYdragged.yy = e.getY();
        });
        
        int playerbulletCount=0;
        
        for(Character b : characters()){
            if (b.type == "playerbullet"){
                playerbulletCount++;
            }
        }
        
        if(playerbulletCount < 3 & MouseXYdragged.xx != -5.0){
            bullet = new Character((int)shooter.getTranslateX()+20,(int)shooter.getTranslateY(),5,5,"playerbullet",Color.BLACK,MouseXYdragged.xx,MouseXYdragged.yy);
            root.getChildren().add(bullet);
            playshootingSound.stop();
            playshootingSound.play();
            ammoleft--;
            }
    }

        
    public static void main(String[] args) {
        launch(args);
    }
    
}
