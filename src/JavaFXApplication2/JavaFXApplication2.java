
package JavaFXApplication2;

import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
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
import javafx.scene.control.RadioButton;
import java.util.Random;

public class JavaFXApplication2 extends Application {
    
    
    boolean gameOverscreen=false;
    Random rnd = new Random();
    Toolkit toolkit;
    int ammoleft=7;    
    public Media spawnZombieSound = new Media(getClass().getResource("Resources/audio/spawnzombiesound.mp3").toString());
    MediaPlayer playZombieSpawnSound =new MediaPlayer(spawnZombieSound);
    
    
    
    private Character createZombie(int i){
        Character s = new Character((90+i*100),5+i*10,30,30,"zombie",Color.RED);
        Image ZombieImage = new Image(getClass().getResource("Resources/images/Zombie.png").toString());
        ImagePattern ZombieimagePattern = new ImagePattern(ZombieImage);
        s.setFill(ZombieimagePattern);
        return s;
        }
    
    Label scoreLabel;
    
    private void gameOver(){
        root.getChildren().clear();
        scoreLabel = new Label("You Survived "+Integer.toString((GameData.roundNumber-1))+" Rounds");
        Image gameOverimage = new Image(getClass().getResource("Resources/images/gameOver.png").toString());
        ImagePattern gameOverimagePattern = new ImagePattern(gameOverimage);
        gameOver.setFill(gameOverimagePattern);
        root.getChildren().add(gameOver);
        root.getChildren().add(restart);
        root.getChildren().add(scoreLabel);
        scoreLabel.setTranslateX(WIDTH/2-150);
        scoreLabel.setTranslateY(HEIGHT/2+50);
        scoreLabel.setFont(font);
        restart.setLayoutX(WIDTH/2-30);
        root.getChildren().add(exitGame);
        gameOverscreen=true;
        }
    
    
    Character character;
    void spawnTrap(){
        int x=rnd.nextInt(WIDTH);
        int y=rnd.nextInt(HEIGHT);
        character=new Character(x,y,30,30,"Trap",Color.BLACK);
        double playerX=player.getTranslateX();
        double playerY=player.getTranslateY();
        while (character.getBoundsInParent().intersects(player.getBoundsInParent())){
            x=rnd.nextInt(WIDTH);
            y=rnd.nextInt(HEIGHT);
            character=new Character(x,y,30,30,"Trap",Color.BLACK);
            }
            root.getChildren().add(character);
        
    }
    
    public Media StartMenuMusic = new Media(getClass().getResource("Resources/audio/StartMenuMusic.wav").toString());
    MediaPlayer playStartMenuMusic =new MediaPlayer(StartMenuMusic);
    
    Label ammoleftlabel = new Label("Ammo left: " + Integer.toString(ammoleft));
        
    Image gamePlayGround = new Image(getClass().getResource("Resources/images/gameBackGround.jpg").toString());
    ImagePattern gamePlayGroundPattern = new ImagePattern(gamePlayGround);

    RadioButton easy = new RadioButton();
    RadioButton normal = new RadioButton();
    RadioButton hard = new RadioButton();
    final ToggleGroup group = new ToggleGroup();
    
    Label easyLabel = new Label("easy");
    Label normalLabel = new Label("normal");
    Label hardLabel = new Label("hard");
    Label instructions = new Label("W -> Move Up\nA -> Move Left\nS -> Move Down\nD -> Move Right\nSPACEBAR -> shoot\nLeft click and hold down mouse to aim");
    
    Font font = Font.font("Verdana",20);
    
    private void StartMenu(){
        root.getChildren().clear();
        Image StartMenuImage = new Image(getClass().getResource("Resources/images/StartMenuImage.png").toString());
        ImagePattern StartMenuImagePattern = new ImagePattern(StartMenuImage);
        StartMenuScreen.setFill(StartMenuImagePattern);
        easy.setToggleGroup(group);
        normal.setToggleGroup(group);
        hard.setToggleGroup(group);
        root.getChildren().add(StartMenuScreen);
        root.getChildren().add(StartGame);
        root.getChildren().add(easy);
        root.getChildren().add(easyLabel);
        root.getChildren().add(normal);
        root.getChildren().add(normalLabel);
        root.getChildren().add(hard);
        root.getChildren().add(hardLabel);
        root.getChildren().add(instructions);
        easy.setTranslateX(WIDTH/2-100);
        easy.setTranslateY(HEIGHT/2);
        easyLabel.setTranslateX(WIDTH/2-250);
        easyLabel.setTranslateY(HEIGHT/2);
        easyLabel.setTextFill(Color.WHITE);
        easyLabel.setFont(font);
        normal.setTranslateX(WIDTH/2-100);
        normal.setTranslateY(HEIGHT/2+40);
        normalLabel.setFont(font);
        normalLabel.setTextFill(Color.WHITE);
        normalLabel.setTranslateX(WIDTH/2-250);
        normalLabel.setTranslateY(HEIGHT/2+40);
        hard.setTranslateX(WIDTH/2-100);
        hard.setTranslateY(HEIGHT/2+80);
        hardLabel.setTextFill(Color.WHITE);
        hardLabel.setFont(font);
        hardLabel.setTranslateX(WIDTH/2-250);
        hardLabel.setTranslateY(HEIGHT/2+80);
        instructions.setTextFill(Color.WHITE);
        instructions.setTranslateX(10);
        instructions.setTranslateY(10);
        StartGame.setLayoutX(WIDTH/2+100);
        StartGame.setLayoutY(HEIGHT/2+50);
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
        return root.getChildren().stream().filter(n -> !exitGame.equals(n)&!restart.equals(n)& !ammoleftlabel.equals(n) & !gameBackground.equals(n)).map(n -> (Character)n).collect(Collectors.toList());
        }
    
    Image playerImage = new Image(getClass().getResource("Resources/images/Survivor_by_Riley_Gombart_right.png").toString());
        ImagePattern playerimagePattern = new ImagePattern(playerImage);
        
    
    private Parent createContent(){
        
        root.setPrefSize(WIDTH,HEIGHT);
        root.getChildren().add(player);
        player.setFill(playerimagePattern);
        
        ammoleftlabel.setTranslateX(WIDTH-110);
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
        for(int i=0;i<(5+GameData.roundNumber);i++){
            Character s = createZombie(i);
            root.getChildren().add(s);
        }
        for (int i=0;i<GameData.roundNumber;i++){
            spawnTrap();
        }
        playZombieSpawnSound.play();
    }
    
    
    private void update(){
        if (StartMenuShown == false & StartMenuOn==false)
            {
            StartMenu();
            }
        if (StartMenuOn==true){
            return;
        }
        playStartMenuMusic.dispose();

        if (player.dead==true & gameOverscreen==false){
            gameOver();
            return;
            }
        if (gameOverscreen == true){
            return;
        }
        
        PlayerLocation.xx=player.getTranslateX();
        PlayerLocation.yy=player.getTranslateY();
        
        characters().forEach(s ->{
            switch(s.type){
                case "zombie":
                    GameData.zombiecount=GameData.zombiecount+1;
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
                    break;
                    }
                case "Trap":{
                    if (s.getBoundsInParent().intersects(player.getBoundsInParent()))
                        {
                        player.dead=true;
                        
                        }
                
                
                }
                    
                }
        });
        
        if (GameData.zombiecount==0)
            { 
            nextLevel();
            GameData.roundNumber=GameData.roundNumber+1;
            }
        
        GameData.zombiecount=0;
        
        if(ammoleft == 0){
            if (reloading ==false){
                startReloadTime = System.nanoTime();
                reloading = true;
                }
            timeElapsedReloading = System.nanoTime()-startReloadTime;
            Font font = Font.font("Verdana",20);
            ammoleftlabel.setFont(font);
            ammoleftlabel.setText("Reloading...");
            if (timeElapsedReloading*0.000000001>3.5){
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
                    playerImage = new Image(getClass().getResource("Resources/images/Survivor_by_Riley_Gombart_left.png").toString());
                    playerimagePattern = new ImagePattern(playerImage);
                    player.setFill(playerimagePattern);
                    break;
                case D:
                    if(player.getTranslateX()<WIDTH-player.getWidth())
                        {player.moveRight();}
                    playerImage = new Image(getClass().getResource("Resources/images/Survivor_by_Riley_Gombart_right.png").toString());
                    playerimagePattern = new ImagePattern(playerImage);
                    player.setFill(playerimagePattern);
                    break;
                case W:
                    if(player.getTranslateY()>0)
                        {player.moveUp();}
                    playerImage = new Image(getClass().getResource("Resources/images/Survivor_by_Riley_Gombart_up.png").toString());
                    playerimagePattern = new ImagePattern(playerImage);
                    player.setFill(playerimagePattern);
                    break;
                case S:
                    if(player.getTranslateY()<HEIGHT-player.getWidth())
                        {player.moveDown();}
                    playerImage = new Image(getClass().getResource("Resources/images/Survivor_by_Riley_Gombart_down.png").toString());
                    playerimagePattern = new ImagePattern(playerImage);
                    player.setFill(playerimagePattern);
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
            player.setFill(playerimagePattern);
            root.getChildren().add(gameBackground);
            root.getChildren().add(player);
            ammoleft=7;
            GameData.roundNumber=1;
        });
        
        StartGame.setOnAction(e -> {
            
            if(hard.isSelected() || easy.isSelected() || normal.isSelected()){
            root.getChildren().clear();
            if (easy.isSelected()){
                GameData.difficultyLevel=1;
            }
            if (normal.isSelected()){
                GameData.difficultyLevel=2;
            }
            if (hard.isSelected()){
                GameData.difficultyLevel=3;
            }
            player = new Character(300,500,40,40,"player",Color.BLUE);
            player.setFill(playerimagePattern);
            root.getChildren().add(gameBackground);
            root.getChildren().add(player);
            StartMenuOn=false;
            StartMenuShown=true;}
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
    
    public Media emptyClipSound = new Media(getClass().getResource("Resources/audio/emptyClip.wav").toString());
    MediaPlayer playEmptyClipSound =new MediaPlayer(emptyClipSound);
    
    Character bullet;
    boolean reloading = false;
    long startReloadTime;
    long timeElapsedReloading;
        
    public void shoot(Character shooter){

        if(ammoleft == 0){
            playEmptyClipSound.stop();
            playEmptyClipSound.play();
            if (reloading ==false){
                startReloadTime = System.nanoTime(); reloading = true;
            }
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
