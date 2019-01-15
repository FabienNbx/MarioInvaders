package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class Goomboss extends Boss{
    //Size of BrowserJr's image
    private static int tailleImgX = 227;
    private static int tailleImgY = 271;
    //Speed of Browser
    private final int vitesseDeplacement = 1;
    /**
     * @param imageX : X coordinate
     * @param imageY : Y coordinate
     */
    public Goomboss (int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/goomboss.png")),imageX,imageY,30);
    }

    /**
     * If super.moveH==0 Goomboss go on the left / if super.moveH==1 Goomboss go on the right
     * @throws Exception : if super.moveH isn't 0 or 1
     */
    @Override
    public void moveH() throws Exception{
        if(super.moveH==0) {
            updateImageView(getImageX()-vitesseDeplacement*Main.getVitesse(), getImageY());
            if(getImageX()<=0)
                super.moveH=1;
        }
        else if(super.moveH==1){
            updateImageView(getImageX()+vitesseDeplacement*Main.getVitesse(), getImageY());
            int taille[] = tailleBoss();
            if(getImageX()+taille[0]>=Main.getTailleXS())
                super.moveH=0;
        }
        else{
            throw new Exception("Probleme moveH != de 0 ou 1");
        }
    }


    public static int getTailleImgX(){ return tailleImgX;}

    public static int getTailleImgY(){ return  tailleImgY;}
}
