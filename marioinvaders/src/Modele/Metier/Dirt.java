package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class Dirt extends Projectile {
    private static int tailleImgX = 74;
    private static int tailleImgY = 74;

    public Dirt(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/dirt.png",true)),imageX,imageY);
    }

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
