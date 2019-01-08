package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class Bowser extends Boss{
    private static int tailleImgX = 440;
    private static int tailleImgY = 400;
    public Bowser (int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/bowser.png",true)),imageX,imageY,100);
    }

    @Override
    public void shoot(){

    }

    public static int getTailleImgX(){ return tailleImgX;}

    public static int getTailleImgY(){ return  tailleImgY;}
}
