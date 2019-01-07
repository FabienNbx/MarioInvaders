package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MediumShell extends Shell {
    private static int tailleImgX = 75;
    private static int tailleImgY = 62;
    public MediumShell(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/med.png",true)),imageX,imageY,5);
    }

    @Override
    public void move(){
        updateImageView(getImageX(),getImageY()+4);
    }

    public static int getTailleImgX(){
        return tailleImgX;
    }

    public static int getTailleImgY() {
        return tailleImgY;
    }
}
