package Modele.Metier;

import javafx.scene.image.ImageView;
import sample.Main;

public abstract class Boss {
    private int nbLife;
    private ImageView iV;
    private int imageX;
    private int imageY;
    private int moveH = 0; // 0 = gauche / 1 = droite

    public Boss (ImageView iV, int imageX, int imageY, int nbLife){
        this.iV=iV;
        this.imageX=imageX;
        this.imageY=imageY;
        this.nbLife=nbLife;
        this.iV.setX(imageX);
        this.iV.setY(imageY);
    }
    public int getNbLife() {
        return nbLife;
    }


    public void moveUp(){
        updateImageView(getImageX(),getImageY()-1* Main.getVitesse());
    }

    public void moveDown(){
        updateImageView(getImageX(),getImageY()+1* Main.getVitesse());
    }

    public void moveH(){
        if(moveH==0) {
            updateImageView(getImageX()-1*Main.getVitesse(), getImageY());
            if(imageX<=0)
                moveH=1;
        }
        else{
            updateImageView(getImageX()+1*Main.getVitesse(), getImageY());
            int taille[] = tailleBoss();
            if(imageX+taille[0]>=Main.getTailleXS())
                moveH=0;
        }
    }

    public int[] tailleBoss(){
        String s = getClass().getName();
        int tailleX;
        int tailleY;
        int power;
        switch (s) {
            case "Modele.Metier.Goomboss":
                tailleX = Goomboss.getTailleImgX();
                tailleY = Goomboss.getTailleImgY();
                power=1;
                break;
            case "Modele.Metier.BowserJr":
                tailleX = BowserJr.getTailleImgX();
                tailleY = BowserJr.getTailleImgY();
                power=2;
                break;
            case "Modele.Metier.Bowser":
                tailleX = Bowser.getTailleImgX();
                tailleY = Bowser.getTailleImgY();
                power=3;
                break;
            default:
                tailleX=0;
                tailleY=0;
                power=1;
        }
        int taille[] = {tailleX,tailleY,power};
        return taille;

    }

    public int[] tailleMissile(){
        String s = getClass().getName();
        int tailleX;
        int tailleY;
        int power;
        switch (s) {
            case "Modele.Metier.Goomboss":
                tailleX = Dirt.getTailleImgX();
                tailleY = Dirt.getTailleImgY();
                power=2;
                break;
            case "Modele.Metier.BowserJr":
                tailleX = SmallFireBall.getTailleImgX();
                tailleY = SmallFireBall.getTailleImgY();
                power=3;
                break;
            case "Modele.Metier.Bowser":
                tailleX = BigFireBall.getTailleImgX();
                tailleY = BigFireBall.getTailleImgY();
                power=5;
                break;
            default:
                tailleX=0;
                tailleY=0;
                power=1;
        }
        int taille[] = {tailleX,tailleY,power};
        return taille;

    }

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
