package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class BowserJr extends Boss{
    private static int tailleImgX = 150;
    private static int tailleImgY = 230;
    public BowserJr (int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/bowserjr.png")),imageX,imageY,50);
    }

    @Override
    public void shoot(){

    }

    public static int getTailleImgX(){ return tailleImgX;}

    public static int getTailleImgY(){ return  tailleImgY;}
}
