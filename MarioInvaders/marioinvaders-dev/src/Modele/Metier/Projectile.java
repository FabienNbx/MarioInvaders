package Modele.Metier;

import javafx.scene.image.ImageView;
import sample.Main;

public abstract class Projectile {
    private ImageView iV;
    private int imageX;
    private int imageY;
    private boolean isH;
    private static int tailleImgX;
    private static int tailleImgY;

    public Projectile(ImageView iV, int imageX,int imageY){
        this.imageX=imageX;
        this.imageY=imageY;
        this.iV=iV;
        this.iV.setX(imageX);
        this.iV.setY(imageY);
        isH=false;
    }

    public abstract void move();

    public void isHitting(){
        if(imageX<0 || imageX>1290 || imageY<0 || imageY>900){
            isH=true;
        }
    }

    public boolean isH() {
        return isH;
    }

    public void updateImageView(int imageX,int imageY) {
        this.imageX = imageX;
        this.imageY = imageY;
        iV.setX(this.imageX);
        iV.setY(this.imageY);
    }


    public ImageView getiV() {
        return iV;
    }
    public int getImageX(){
        return imageX;
    }
    public void setImageY(int imageY){
        if(imageY>(int) (Main.getTailleYS()-iV.getFitWidth())){
            imageY= (int) (Main.getTailleYS()-iV.getFitWidth());
            System.out.println(iV.getFitWidth());
        }
        this.imageY=imageY;
        isHitting();
    }
    public int getImageY(){
        return imageY;
    }

}
