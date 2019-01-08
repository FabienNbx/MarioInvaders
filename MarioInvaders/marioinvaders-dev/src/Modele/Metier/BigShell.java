package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class BigShell extends Shell {
    private static int tailleImgX = 100;
    private static int tailleImgY = 100;
    public BigShell(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/big.png",true)),imageX,imageY, 5);
    }

    @Override
    public void move(){
        updateImageView(getImageX(),getImageY()+(1* Main.getVitesse()));
    }

    public static int getTailleImgX(){
        return tailleImgX;
    }

    public static int getTailleImgY() {
        return tailleImgY;
    }
}
