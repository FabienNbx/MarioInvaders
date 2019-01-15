package Modele.Metier;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import sample.Main;

public class Mario {
    private Image mario ;
    private ImageView iV ;

    // position of Mario
    private int imageX;
    private int imageY;

    // number of lifes
    public SimpleIntegerProperty nbLife;

    // booleans links to event press a key
    private boolean goUp;
    private boolean goDown;
    private boolean goRight;
    private boolean goLeft;

    // boolean links to event press SPACE
    private boolean missiles = false;

    // true if Mario dead
    private boolean isDead = false;

    // true if Mario is hitted
    private boolean isFlashing = false;

    /**
     * Constructor
     * @param img : character's image
     */
    public Mario(Image img){
        mario = img;
        iV = new ImageView(mario);

        // begin at this position
        imageX=620;
        imageY=850;

        nbLife = new SimpleIntegerProperty(5);

        iV.setX(imageX);
        iV.setY(imageY);
    }

    /**
     * Actions for events "Press a key"
     * @param key : key pressed
     */
    public void actions(KeyEvent key) {
        if (key.getCode().equals(KeyCode.RIGHT)) {
            goRight = true;
        }
        else if (key.getCode().equals(KeyCode.LEFT)) {
            goLeft = true;
        }
        else if (key.getCode().equals(KeyCode.UP)) {
            goUp = true;
        }
        else if (key.getCode().equals(KeyCode.DOWN)) {
            goDown = true;
        }
        else if (key.getCode().equals(KeyCode.SPACE)) {
            shoot();
        }
    }

    /**
     * Update position of Mario
     * @param imageX : X position
     * @param imageY : Y position
     */
    public void updateImageView(int imageX,int imageY) {
        this.imageX = imageX;
        this.imageY = imageY;
        iV.setX(this.imageX);
        iV.setY(this.imageY);
    }

    /**
     * Actions of event "Release a key"
     * @param key : key released
     */
    public void stop(KeyEvent key) {
        switch (key.getCode()) {
            case UP:    goUp = false; break;
            case DOWN:  goDown = false; break;
            case LEFT:  goLeft  = false; break;
            case RIGHT: goRight  = false; break;
            case SPACE: stopMissiles(); break;
        }
    }

    /**
     * SPACE key pressed
     */
    public void shoot(){
        missiles=true;
    }

    /**
     * When nbLife = 0, Mario dies and leave the screen by the bottom
     */
    public void die(){
        Image mort = new Image("file:src/images/mort.png",true);
        iV.setImage(mort);
        isDead = true;
    }

    /**
     * When Mario hitted, image flashs and Mario can't be hitted for 4 seconds
     */
    public void flash(){
        isFlashing=true;
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(200),
                c -> changeIV()));
        timeline.setCycleCount(20);
        timeline.play();
        timeline.setOnFinished(e -> isFlashing = false);
    }

    /**
     * For flashing, Mario's image changes between Mario and white
     */
    public void changeIV(){
        if(iV.getImage() == mario) {
            iV.setImage(new Image("file:src/images/blanc.png", true));
        }
        else{
            iV.setImage(mario);
        }
    }

    /**
     * Decrease number of lifes by power if possible and check if dead
     * @param power : damages
     */
    public void beHitted(int power){
        if(power<0 && isFlashing && isDead){
            return;
        }
        nbLife.set(nbLife.get()-power);
        Main.playLifeSound();
        if(nbLife.get()<=0){
            nbLife.set(0);
            die();
        }
        else{
            flash();
        }
    }

    /**
     * SPACE key released
     */
    public void stopMissiles(){
        missiles = false;
    }



    public boolean isGoUp() {
        return goUp;
    }
    public boolean isGoDown() {
        return goDown;
    }
    public boolean isGoRight() {
        return goRight;
    }
    public boolean isGoLeft() {
        return goLeft;
    }
    public int isImageX() {
        return imageX;
    }
    public int isImageY() {
        return imageY;
    }
    public ImageView isIV() {
        return iV;
    }
    public boolean isMissiles() {
        return missiles;
    }
    public boolean isDead() {
        return isDead;
    }
    public boolean isFlashing() {
        return isFlashing;
    }
}
