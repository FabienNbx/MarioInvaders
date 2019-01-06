package Modele.Metier;

import javafx.scene.image.ImageView;

public abstract class Shell extends Projectile{

    private int nbLife;

    public Shell(ImageView iV, int imageX, int imageY,int nbLife){
        super(iV,imageX,imageY);
        this.nbLife=nbLife;
    }

    @Override
    public abstract void move();

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
