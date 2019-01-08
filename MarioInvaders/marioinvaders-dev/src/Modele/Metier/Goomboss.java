package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class Goomboss extends Boss{
    private static int tailleImgX = 245;
    private static int tailleImgY = 276;
    public Goomboss (int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/goomboss.png")),imageX,imageY,30);
    }

    @Override
    public void shoot(){

    }

    public static int getTailleImgX(){ return tailleImgX;}

    public static int getTailleImgY(){ return  tailleImgY;}
}
