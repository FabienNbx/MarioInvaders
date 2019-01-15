package Modele.Metier;

import javafx.scene.image.ImageView;
import sample.Main;

public abstract class Boss {
    private int nbLife;
    private ImageView iV;

    // boss's coordinates X,Y
    private int imageX;
    private int imageY;

    // to know in which direction the boss will go
    protected int moveH = 0; // 0 = gauche / 1 = droite

    /**
     * Constructor
     * @param iV : image
     * @param imageX : X position
     * @param imageY : Y position
     * @param nbLife : number of lifes
     */
    public Boss (ImageView iV, int imageX, int imageY, int nbLife){
        this.iV=iV;
        this.imageX=imageX;
        this.imageY=imageY;
        this.nbLife=nbLife;
        this.iV.setX(imageX);
        this.iV.setY(imageY);
    }


    /**
     * Leave the screen by the top when died
     */
    public void moveUp(){
        updateImageView(getImageX(),getImageY()-1* Main.getVitesse());
    }


    /**
     * Come in the screen by the top to the middle
     */
    public void moveDown(){
        updateImageView(getImageX(),getImageY()+1* Main.getVitesse());
    }


    /**
     * Move horizontally
     * @throws Exception
     */
    public abstract void moveH() throws Exception;

    /**
     * Give the sizes and the power of the boss
     * @return table with X and Y sizes and power
     */
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


    /**
     * Give missile's sizes and power
     * @return table with X and Y sizes and the power
     */
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


    /**
     * Update position of the boss
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
     * Decrease number of lifes by power and check if dead
     * @param power : damages
     */
    public void beHitted(int power){
        if(power>nbLife){
            nbLife=0;
        }
        else {
            nbLife = nbLife - power;
        }
    }


    /**
     * Set Y position and check if possible
     * @param imageY : new position
     */
    public void setImageY(int imageY){
        if(imageY>(int) (Main.getTailleYS()-iV.getFitHeight())){
            imageY= (int) (Main.getTailleYS()-iV.getFitHeight());
        }
        else if (imageY<0)
            imageY=0;
        this.imageY=imageY;
    }

    /**
     * Set X position and check if possible
     * @param imageX : new position
     */
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

    public int getNbLife() {
        return nbLife;
    }
    public ImageView getiV() {
        return iV;
    }
    public int getImageX(){
        return imageX;
    }
}
