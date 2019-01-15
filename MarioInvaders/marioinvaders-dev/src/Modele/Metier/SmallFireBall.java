package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class SmallFireBall extends Projectile{
    //size of the SmallFireBall Image
    private static int tailleImgX = 73;
    private static int tailleImgY = 75;

    /**
     * Coonstructor
     * @param imageX : X coordinate
     * @param imageY : Y coordinate
     */
    public SmallFireBall(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/PetiteBouleDeFeu.png",true)),imageX,imageY);
    }

    /**
     * How the SmallFireBall move
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
