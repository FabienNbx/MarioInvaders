package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BigFireBall extends Projectile {

    public BigFireBall(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/rocket.png",true)),imageX,imageY);
    }

    @Override
    public void move() {
        updateImageView(getImageX(),getImageY()+5);

    }

}
