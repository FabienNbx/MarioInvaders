package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BigShell extends Shell {
    private static int tailleImgX = 100;
    private static int tailleImgY = 100;
    public BigShell(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/big.png",true)),imageX,imageY,10);
    }

    @Override
    public void move(){
        updateImageView(getImageX(),getImageY()+2);
    }

    public static int getTailleImgX(){
        return tailleImgX;
    }

    public static int getTailleImgY() {
        return tailleImgY;
    }
}
