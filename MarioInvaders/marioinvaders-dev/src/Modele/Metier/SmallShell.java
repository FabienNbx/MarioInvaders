package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SmallShell extends Shell {
    private static int tailleImgX = 50;
    private static int tailleImgY = 42;
    public SmallShell(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/small.png",true)),imageX,imageY,2);
    }

    @Override
    public void move(){
        updateImageView(getImageX(),getImageY()+6);
    }

    public static int getTailleImgX(){ return tailleImgX;}

    public static int getTailleImgY(){ return  tailleImgY;}
}
