package Controller;

import Modele.Metier.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sample.Main;


import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.*;

public class Controller {
    private AnimationTimer aT;
    private AnchorPane root;
    private Scene scene;
    private Mario mario;
    private ArrayList<Projectile> listMissile;
    private ArrayList<Projectile> listShell;
    private int[] tabRand = {0,0,0,0,0,0,0,1,1,1,1,2};
    Random randomS = new Random();

    public Controller(Stage primaryStage) {
        root = new AnchorPane();
        scene = new Scene(root, Main.getTailleXS(), Main.getTailleYS());
        primaryStage.setScene(scene);
        primaryStage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

        mario = new Mario();
        root.getChildren().add(mario.isIV());
        listMissile = new ArrayList<>();
        listShell = new ArrayList<>();
        start();
    }

    private void start(){
        scene.setOnKeyPressed(key -> mario.actions(key));
        scene.setOnKeyReleased(key -> mario.stop(key));

        aT = new AnimationTimer(){
            int cpt=0;
            @Override
            public void handle(long now) {
                cpt++;
                int imageX = mario.isImageX(), imageY = mario.isImageY();
                Collisions(imageX,imageY);
                if (mario.isDead()){
                    MarioDead(imageX,imageY);
                }
                else {
                    MarioMove(imageX,imageY);
                    MarioShoot(imageX,imageY);
                    if(cpt==50) {
                        Shells();
                        cpt=0;
                    }
                    ShellsFall();
                }
            }
        };
        aT.start();
    }

    private void Collisions(int imageX, int imageY) {
        int centreMario[] = {imageX + 25, imageY + 25};
        if(!mario.isFlashing()) {
            for (Projectile p : listShell) {
                String s = p.getClass().getName();
                int x;
                int y;
                int power;
                switch (s) {
                    case "Modele.Metier.SmallShell":
                        x = SmallShell.getTailleImgX() / 2;
                        y = SmallShell.getTailleImgY() / 2;
                        power = 1;
                        break;
                    case "Modele.Metier.MediumShell":
                        x = MediumShell.getTailleImgX() / 2;
                        y = MediumShell.getTailleImgY() / 2;
                        power = 2;
                        break;
                    case "Modele.Metier.BigShell":
                        x = BigShell.getTailleImgX() / 2;
                        y = BigShell.getTailleImgY() / 2;
                        power = 3;
                        break;
                    default:
                        power = 1;
                        x = 0;
                        y = 0;
                }
                int centreProjectile[] = {p.getImageX() + x, p.getImageY() + y};
                int mini = min(x, y);
                int distMario = (int) sqrt(pow(centreProjectile[0] - centreMario[0], 2) + pow(centreProjectile[1] - centreMario[1], 2));
                if (distMario <= 25 + mini) {
                    mario.beHitted(power);
                }
            }
        }
        ArrayList<Projectile> listMissiledead = new ArrayList<>();
        ArrayList<Projectile> listShelldead = new ArrayList<>();
        for(Projectile m : listMissile) {
            int centreMissile[] = {m.getImageX()+15, m.getImageY()+15};
            for (Projectile p : listShell) {
                String s = p.getClass().getName();
                int x;
                int y;
                switch (s) {
                    case "Modele.Metier.SmallShell":
                        x = SmallShell.getTailleImgX() / 2;
                        y = SmallShell.getTailleImgY() / 2;
                        break;
                    case "Modele.Metier.MediumShell":
                        x = MediumShell.getTailleImgX() / 2;
                        y = MediumShell.getTailleImgY() / 2;
                        break;
                    case "Modele.Metier.BigShell":
                        x = BigShell.getTailleImgX() / 2;
                        y = BigShell.getTailleImgY() / 2;
                        break;
                    default:
                        x = 0;
                        y = 0;
                }
                int centreProjectile[] = {p.getImageX() + x, p.getImageY() + y};
                int mini = min(x, y);
                int distMissile = (int) sqrt(pow(centreMissile[0] - centreProjectile[0], 2) + pow(centreMissile[1] - centreProjectile[1], 2));
                if (distMissile <= 15 + mini ){
                    Shell sh = (Shell) p;
                    sh.beHitted(1);
                    if(sh.getNbLife()==0){
                        root.getChildren().remove(sh.getiV());
                        listShelldead.add(p);
                    }
                    root.getChildren().remove(m.getiV());
                    listMissiledead.add(m);
                }
            }
        }
        for (Projectile p: listShelldead) {
            listShell.remove(p);
        }
        for (Projectile p: listMissiledead) {
            listMissile.remove(p);
        }
    }

    private void Shells(){
        int num = tabRand[randomS.nextInt(12)];
        int randX;
        Projectile s;
        switch(num){
            case 0:
                randX = randomS.nextInt(Main.getTailleXS()-SmallShell.getTailleImgX());
                s = new SmallShell(randX , 0 );
                listShell.add(s);
                root.getChildren().add(s.getiV());
                break;
            case 1:
                randX = randomS.nextInt(Main.getTailleXS()-MediumShell.getTailleImgX());
                s = new MediumShell(randX , 0 );
                listShell.add(s);
                root.getChildren().add(s.getiV());
                break;
            case 2:
                randX = randomS.nextInt(Main.getTailleXS()-BigShell.getTailleImgX());
                s = new BigShell(randX , 0 );
                listShell.add(s);
                root.getChildren().add(s.getiV());
                break;
            default:
                System.out.println("ERREUR DEFAULT");
        }



    }

    private void ShellsFall(){
        if (listShell.size() != 0) {
            int i = 0;
            while (i < listShell.size()) {
                Projectile s = listShell.get(i);
                if (s.isH()) {
                    root.getChildren().remove(s.getiV());
                    listShell.remove(i);
                    continue;
                }
                s.move();
                s.isHitting();
                i++;
            }
        }
    }

    private void MarioDead(int imageX, int imageY){
        if(imageY< Main.getTailleYS()) {
            mario.updateImageView(imageX, imageY + 5);
        }
        else{
            root.getChildren().remove(mario.isIV());
            aT.stop();
            Main.creerMain();
        }
    }

    private void MarioShoot(int imageX, int imageY){
        if (mario.isMissiles()) {
            Projectile m = new Missile(imageX + 10, imageY - 30);
            listMissile.add(m);
            root.getChildren().add(m.getiV());
            mario.stopMissiles();
        }
        if (listMissile.size() != 0) {
            int i = 0;
            while (i < listMissile.size()) {
                Projectile m = listMissile.get(i);
                if (m.isH()) {
                    root.getChildren().remove(m.getiV());
                    listMissile.remove(i);
                    continue;
                }
                m.move();
                m.isHitting();
                i++;
            }
        }
    }

    private void MarioMove(int imageX, int imageY){
        if (mario.isGoRight() && imageX < Main.getTailleXS() - 50) {
            imageX += 10;
        }
        if (mario.isGoLeft() && imageX > 0) {
            imageX -= 10;
        }
        if (mario.isGoDown() && imageY < Main.getTailleYS() - 50) {
            imageY += 10;
        }
        if (mario.isGoUp() && imageY > 0) {
            imageY -= 10;
        }
        mario.updateImageView(imageX, imageY);
    }
}
