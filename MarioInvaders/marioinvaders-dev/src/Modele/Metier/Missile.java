package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class Missile extends Projectile{
    private static int tailleImgX = 18;
    private static int tailleImgY = 30;
    public Missile(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/rocket.png",true)),imageX,imageY);
    }

    public static int getTailleImgX() {
        return tailleImgX;
    }

    public static int getTailleImgY() {
        return tailleImgY;
    }

    @Override
    public void move() {
        updateImageView(getImageX(),getImageY()-(3* Main.getVitesse()));
    }
}
