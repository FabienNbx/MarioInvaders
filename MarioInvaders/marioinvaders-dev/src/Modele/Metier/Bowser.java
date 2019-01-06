package Modele.Metier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bowser extends Boss{
    public Bowser (int imageX, int imageY){
        super(new ImageView(new Image("file:src/images/rocket.png",true)),imageX,imageY,100);
    }
    @Override
    public void move(){
        updateImageView(getImageX(),getImageY()-5);
    }
    @Override
    public void shoot(){

    }
}
