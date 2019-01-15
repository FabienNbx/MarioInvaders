package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class BigFireBall extends Projectile {

    // sizes of the image
    private static int tailleImgX = 153;
    private static int tailleImgY = 147;

    /**
     * Constructor
     * @param imageX : X position
     * @param imageY : Y position
     */
    public BigFireBall(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/GrosseBouleDeFeu.png",true)),imageX,imageY);
    }

    /**
     * How the BigFireBall move
     */
    @Override
    public void move() {
        updateImageView(getImageX(),getImageY()+1* Main.getVitesse());
    }


    public static int getTailleImgX(){
        return tailleImgX;
    }

    public static int getTailleImgY() {
        return tailleImgY;
    }
}
