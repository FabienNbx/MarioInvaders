package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class SmallShell extends Shell {
    //Size of the SmallShell image
    private static int tailleImgX = 50;
    private static int tailleImgY = 42;


    /**
     * @param imageX : X coordinate
     * @param imageY : Y coordinate
     */
    public SmallShell(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/small.png",true)),imageX,imageY,1);
    }

    /**
     * How the SmallShell move
     */
    @Override
    public void move(){
        updateImageView(getImageX(),getImageY()+(3* Main.getVitesse()));
    }

    public static int getTailleImgX(){ return tailleImgX;}

    public static int getTailleImgY(){ return  tailleImgY;}
}
