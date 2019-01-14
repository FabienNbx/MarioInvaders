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
    private int imageX;
    private int imageY;
    public SimpleIntegerProperty nbLife;
    private boolean goUp;
    private boolean goDown;
    private boolean goRight;
    private boolean goLeft;
    private boolean missiles = false;
    private boolean isDead = false;
    private boolean isFlashing = false;

    public Mario(Image img){
        mario = img;
        iV = new ImageView(mario);
        imageX=620;
        imageY=850;
        nbLife = new SimpleIntegerProperty(5);
        iV.setX(imageX);
        iV.setY(imageY);
    }

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
        else if (key.getCode().equals(KeyCode.S)) {
            if(!isFlashing) {
                beHitted(1);
            }
        }
    }

    public void updateImageView(int imageX,int imageY) {
        this.imageX = imageX;
        this.imageY = imageY;
        iV.setX(this.imageX);
        iV.setY(this.imageY);
    }

    public void stop(KeyEvent key) {
        switch (key.getCode()) {
            case UP:    goUp = false; break;
            case DOWN:  goDown = false; break;
            case LEFT:  goLeft  = false; break;
            case RIGHT: goRight  = false; break;
            case SPACE: stopMissiles(); break;
        }
    }


    public void shoot(){
        missiles=true;
    }

    public void die(){
        Image mort = new Image("file:src/images/mort.png",true);
        iV.setImage(mort);
        isDead = true;
    }

    public void flash(){
        isFlashing=true;
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(200),
                c -> changeIV()));
        timeline.setCycleCount(20);
        timeline.play();
        timeline.setOnFinished(e -> stopFlash());
    }

    public void stopFlash(){
        isFlashing = false;
    }

    public void changeIV(){
        if(iV.getImage() == mario) {
            iV.setImage(new Image("file:src/images/blanc.png", true));
        }
        else{
            iV.setImage(mario);
        }
    }

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
