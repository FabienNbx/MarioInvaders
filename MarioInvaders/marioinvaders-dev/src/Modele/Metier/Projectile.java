package Modele.Metier;

import javafx.scene.image.ImageView;
import sample.Main;

public abstract class Projectile {
    //Image of the Projectile
    private ImageView iV;

    //coordinates of the projectile
    private int imageX;
    private int imageY;

    //true if the projectile is out of screen
    private boolean isH;

    // size of the image
    private static int tailleImgX;
    private static int tailleImgY;

    /**
     * Constructor
     * @param iV : image
     * @param imageX : X position
     * @param imageY : Y position
     */
    public Projectile(ImageView iV, int imageX,int imageY){
        this.imageX=imageX;
        this.imageY=imageY;
        this.iV=iV;
        this.iV.setX(imageX);
        this.iV.setY(imageY);
        isH=false;
    }

    /**
     * Need to be redefine, describe how the projectile move
     */
    public abstract void move();


    /**
     * check if out of screen
     */
    public void isHitting(){
        if(imageY>Main.getTailleYS()){
            isH=true;
        }
    }


    /**
     * Update position of the Projectile
     * @param imageX : X position
     * @param imageY : Y position
     */
    public void updateImageView(int imageX,int imageY) {
        this.imageX = imageX;
        this.imageY = imageY;
        iV.setX(this.imageX);
        iV.setY(this.imageY);
    }

    /**
     * Set a new Y coordinate
     * @param imageY : new Y coordinate
     */
    public void setImageY(int imageY){
        if(imageY>(int) (Main.getTailleYS()-iV.getFitWidth())){
            imageY= (int) (Main.getTailleYS()-iV.getFitWidth());
            System.out.println(iV.getFitWidth());
        }
        this.imageY=imageY;
        isHitting();
    }

    public ImageView getiV() {
        return iV;
    }
    public int getImageX(){
        return imageX;
    }
    public int getImageY(){
        return imageY;
    }
    public boolean isH() {
        return isH;
    }


}
