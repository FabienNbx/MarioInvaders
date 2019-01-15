package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class MediumShell extends Shell {

    // sizes of the image
    private static int tailleImgX = 75;
    private static int tailleImgY = 62;

    /**
     * Constructor
     * @param imageX : X position
     * @param imageY : Y position
     */
    public MediumShell(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/med.png",true)),imageX,imageY,3);
    }

    /**
     * How the MediumShell move
     */
    @Override
    public void move(){
        updateImageView(getImageX(),getImageY()+(2* Main.getVitesse()));
    }

    public static int getTailleImgX(){
        return tailleImgX;
    }

    public static int getTailleImgY() {
        return tailleImgY;
    }
}
