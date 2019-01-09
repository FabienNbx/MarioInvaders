package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class BowserJr extends Boss{
    private static int tailleImgX = 136;
    private static int tailleImgY = 207;
    private final int vitesseDeplacement = 2;
    public BowserJr (int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/bowserjr.png")),imageX,imageY,50);
    }

    @Override
    public void shoot(){

    }

    public static int getTailleImgX(){ return tailleImgX;}

    public static int getTailleImgY(){ return  tailleImgY;}

    @Override
    public void moveH(){
        if(super.moveH==0) {
            updateImageView(getImageX()-vitesseDeplacement*Main.getVitesse(), getImageY());
            if(getImageX()<=0)
                super.moveH=1;
        }
        else{
            updateImageView(getImageX()+vitesseDeplacement*Main.getVitesse(), getImageY());
            int taille[] = tailleBoss();
            if(getImageX()+taille[0]>=Main.getTailleXS())
                super.moveH=0;
        }
    }
}
