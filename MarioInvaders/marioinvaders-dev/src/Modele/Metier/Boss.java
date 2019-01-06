package Modele.Metier;

import javafx.scene.image.ImageView;
import sample.Main;

public abstract class Boss {
    private int nbLife;
    private ImageView iV;
    private int imageX;
    private int imageY;

    public Boss (ImageView iV, int imageX, int imageY, int nbLife){
        this.iV=iV;
        this.imageX=imageX;
        this.imageY=imageY;
        this.nbLife=nbLife;
        this.iV.setX(imageX);
        this.iV.setY(imageY);
    }

    public abstract void move();

    public abstract void shoot();


    public void updateImageView(int imageX,int imageY) {
        this.imageX = imageX;
        this.imageY = imageY;
        iV.setX(this.imageX);
        iV.setY(this.imageY);
    }

    public void beHitted(int power){
        if(power>nbLife){
            nbLife=0;
        }
        else {
            nbLife = nbLife - power;
        }
    }
    public ImageView getiV() {
        return iV;
    }
    public int getImageX(){
        return imageX;
    }
    public void setImageY(int imageY){
        if(imageY>(int) (Main.getTailleYS()-iV.getFitHeight())){
            imageY= (int) (Main.getTailleYS()-iV.getFitHeight());
        }
        else if (imageY<0)
            imageY=0;
        this.imageY=imageY;
    }
    public void setImageX(int imageX){
        if(imageX>(int) (Main.getTailleXS()-iV.getFitWidth())){
            imageX= (int) (Main.getTailleXS()-iV.getFitWidth());
        }
        else if (imageX<0 ){
            imageX=0;
        }
        this.imageX=imageX;
    }
    public int getImageY(){
        return imageY;
    }
}
