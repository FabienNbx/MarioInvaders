package Modele.Metier;

import javafx.scene.image.ImageView;

public abstract class Shell extends Projectile{

    // number of life
    private int nbLife;

    /**
     * Constructor
     * @param iV : image
     * @param imageX : X position
     * @param imageY : Y position
     * @param nbLife : number of lifes
     */
    public Shell(ImageView iV, int imageX, int imageY,int nbLife){
        super(iV,imageX,imageY);
        this.nbLife=nbLife;
    }

    /**
     * Need to be redefine, describe how the shell move
     */
    @Override
    public abstract void move();

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

    public int getNbLife() {
        return nbLife;
    }
}
