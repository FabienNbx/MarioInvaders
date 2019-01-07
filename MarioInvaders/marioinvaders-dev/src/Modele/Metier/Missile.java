package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Missile extends Projectile{

    public Missile(int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/rocket.png",true)),imageX,imageY);
    }

    @Override
    public void move() {
        updateImageView(getImageX(),getImageY()-6);
    }
}
