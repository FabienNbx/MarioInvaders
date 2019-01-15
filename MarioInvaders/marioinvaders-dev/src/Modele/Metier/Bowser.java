package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class Bowser extends Boss{
    //Size of Browser's image
    private static int tailleImgX = 434;
    private static int tailleImgY = 394;
    //Speed of Browser
    private final int vitesseDeplacement = 3;
    /**
     * @param imageX : X coordinate
     * @param imageY : Y coordinate
     */
    public Bowser (int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/bowser.png",true)),imageX,imageY,100);
    }

    /**
     * If super.moveH==0 Browser go on the left / if super.moveH==1 Browser go on the right
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
