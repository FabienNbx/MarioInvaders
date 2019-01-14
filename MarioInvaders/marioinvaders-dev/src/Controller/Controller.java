package Controller;

import Modele.Metier.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Main;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.*;
import static sample.Main.getTailleXS;

public class Controller {
    private Timeline master;
    private int cpt = 0;
    private AnchorPane root;
    private Scene scene;
    private Mario mario;
    private ArrayList<Projectile> listMissile;
    private ArrayList<Projectile> listShell;
    private ArrayList<Projectile> listMissBoss;
    private ArrayList<Projectile> listMissiledead;
    private ArrayList<Projectile> listShelldead;
    private ArrayList<Projectile> listMissBossdead;
    private int[] tabRand = {0,0,0,0,0,0,0,1,1,1,1,2};
    private Random randomS = new Random();
    private int boss = 0;   // 0 = pas de boss / 1 = boss move / 2 = boss shoot / 3 = boss mort
    private int nbBoss = 0;
    private int cptB = 0;
    private Boss b;
    private Timeline timelineBoss;
    private static int cptTemps=0;

    public Controller(Stage primaryStage) {
        cptTemps=0;
        root = new AnchorPane();
        scene = new Scene(root, getTailleXS(), Main.getTailleYS());
        primaryStage.setScene(scene);
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        ImageView fond = new ImageView(new Image("file:src/images/fond.jpeg",(double)Main.getTailleXS(),(double)Main.getTailleYS(),true,true));
        root.getChildren().add(fond);
        if(Main.getPerso()==0){
            mario = new Mario(new Image("file:src/images/mario.png"));
        }
        else{
            mario = new Mario(new Image("file:src/images/marioVert.png"));
        }
        root.getChildren().add(mario.isIV());
        listMissile = new ArrayList<>();
        listShell = new ArrayList<>();
        listMissBoss = new ArrayList<>();
        listMissiledead = new ArrayList<>();
        listShelldead = new ArrayList<>();
        listMissBossdead = new ArrayList<>();
        start();

        timelineBoss = new Timeline(new KeyFrame(
                Duration.millis(10000),
                ae -> lancerBoss()));
        timelineBoss.setCycleCount(Animation.INDEFINITE);
        timelineBoss.play();
        Label vie = new Label();
        vie.setText("Vies :");
        vie.setFont(new Font("System",20.0));
        vie.setStyle("-fx-font-weight: bold");
        AnchorPane.setRightAnchor(vie,30.0);
        root.getChildren().add(vie);
        Label l = new Label();
        l.textProperty().bind(mario.nbLife.asString());
        l.setFont(new Font("System",20.0));
        l.setStyle("-fx-font-weight: bold");
        AnchorPane.setRightAnchor(l,10.0);
        root.getChildren().add(l);
    }

    private void start(){
        scene.setOnKeyPressed(key -> mario.actions(key));
        scene.setOnKeyReleased(key -> mario.stop(key));
        master = new Timeline(new KeyFrame(
                Duration.millis(10),
                ae -> boucle()));
        master.setCycleCount(Animation.INDEFINITE);
        master.play();
    }

    private void boucle() {
        cptTemps++;
        int imageX = mario.isImageX(), imageY = mario.isImageY();
        Collisions(imageX, imageY);
        if (mario.isDead()) {
            MarioDead(imageX, imageY);
        }
        else {
            MarioMove(imageX, imageY);
            MarioShoot(imageX, imageY);
            if(boss!=0) {
                ColliMissBoss(imageX,imageY);
                if (boss == 1) {
                    BossMoveDown();
                }
                else if(boss == 2){
                    BossMoveH();
                    BossShoot();
                    ColliBoss(imageX,imageY);
                    cptB++;
                }
                else{
                    PurgeMissBoss();
                    BossMoveUp();
                    cpt++;
                }
            }
            else{
                cpt++;
            }
            if (cpt == 50/(nbBoss+1) / Main.getVitesse()) {
                Shells();
                cpt = 0;
            }
            ShellsFall();
        }
    }

    private void ColliMissBoss(int imageX, int imageY) {
        int centreMario[] = {imageX + 25, imageY + 25};
        if(!mario.isFlashing()) {
            for(Projectile m : listMissBoss) {
                int tailleMiss[] = b.tailleMissile();
                int centreMiss[] = {m.getImageX() + tailleMiss[0] / 2, m.getImageY() + tailleMiss[1] / 2};
                int mini = min(tailleMiss[0] / 2, tailleMiss[1] / 2);
                int distMario = (int) sqrt(pow(centreMiss[0] - centreMario[0], 2) + pow(centreMiss[1] - centreMario[1], 2));
                if (distMario <= 25 + mini) {
                    mario.beHitted(tailleMiss[2]);
                    root.getChildren().remove(m.getiV());
                    listMissBossdead.add(m);
                }
            }
        }
        for (Projectile p: listMissBossdead) {
            listMissBoss.remove(p);
        }
    }

    private void ColliBoss(int imageX, int imageY) {
        int centreMario[] = {imageX + 25, imageY + 25};
        if(!mario.isFlashing()) {
            int tailleBoss[] = b.tailleBoss();
            int centreBoss[] = {b.getImageX() + tailleBoss[0]/2, b.getImageY() +  tailleBoss[1]/2};
            int mini = min( tailleBoss[0]/2,  tailleBoss[1]/2);
            int distMario = (int) sqrt(pow(centreBoss[0] - centreMario[0], 2) + pow(centreBoss[1] - centreMario[1], 2));
            if (distMario <= 25 + mini) {
                mario.beHitted( tailleBoss[2]);
            }
        }
        ArrayList<Projectile> listMissiledead = new ArrayList<>();
        for(Projectile m : listMissile) {
            int centreMissile[] = {m.getImageX()+Missile.getTailleImgX()/2, m.getImageY()+Missile.getTailleImgY()/2};

            int tailleBoss[] = b.tailleBoss();
            int centreBoss[] = {b.getImageX() + tailleBoss[0]/2, b.getImageY() + tailleBoss[1]/2};
            int mini = min(tailleBoss[0]/2, tailleBoss[1]/2);
            int distMissile = (int) sqrt(pow(centreMissile[0] - centreBoss[0], 2) + pow(centreMissile[1] - centreBoss[1], 2));
            if (distMissile <= Missile.getTailleImgX()/2 + mini ){
                b.beHitted(1);
                if(b.getNbLife()==0){
                    if(nbBoss!=3)
                        Main.playNiveauSound();
                    boss=3;
                }
                root.getChildren().remove(m.getiV());
                listMissiledead.add(m);
                if(boss==3)
                    break;
            }

        }
        for (Projectile p: listMissiledead) {
            listMissile.remove(p);
        }
    }

    private void BossShoot() {
        double tps;
        tps = 50 + (Math.random() * (300 - 50));
        if(cptB>= (int) tps){
            cptB=0;
            Projectile p;
            int tab[] = b.tailleBoss();
            if(nbBoss==1){
                p = new Dirt(b.getImageX()+tab[0]/2,b.getImageY()+tab[1]);
            }
            else if (nbBoss==2){
                p = new SmallFireBall(b.getImageX()+tab[0]/2,b.getImageY()+tab[1]);
            }
            else{
                p = new BigFireBall(b.getImageX()+tab[0]/2,b.getImageY()+tab[1]);
            }
            listMissBoss.add(p);
            root.getChildren().add(p.getiV());
        }
        if(listMissBoss.size() != 0){
            int i = 0;
            while (i < listMissBoss.size()) {
                Projectile m = listMissBoss.get(i);
                if (m.isH()) {
                    root.getChildren().remove(m.getiV());
                    listMissBoss.remove(i);
                    continue;
                }
                m.move();
                m.isHitting();
                i++;
            }
        }
    }

    private void PurgeMissBoss(){
        int i=0;
        while(listMissBoss.size() > 0){
            Projectile m = listMissBoss.get(i);
            root.getChildren().remove(m.getiV());
            listMissBoss.remove(i);
        }
    }

    private void BossMoveDown() {
        b.moveDown();
        if (b.getImageY() >= Main.getTailleYS() / 2 - b.tailleBoss()[1]) {
            boss = 2;
        }
    }

    private void BossMoveH(){
        try {
            b.moveH();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void BossMoveUp(){
        b.moveUp();
        int taille[] = b.tailleBoss();
        if(b.getImageY()+taille[1]<=0){
            root.getChildren().remove(b.getiV());
            if(nbBoss==3) {
                master.stop();
                Purge();
                Main.win();
            }
            else {
                cptB=0;
                boss = 0;
                timelineBoss.play();
            }
        }
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
                    root.getChildren().remove(p.getiV());
                    listShelldead.add(p);
                }
            }
        }
        for (Projectile p: listShelldead) {
            listShell.remove(p);
        }

        for(Projectile m : listMissile) {
            int centreMissile[] = {m.getImageX()+Missile.getTailleImgX()/2, m.getImageY()+Missile.getTailleImgY()/2};
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
                if (distMissile <= Missile.getTailleImgX()/2 + mini ){
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
                randX = randomS.nextInt(getTailleXS()-SmallShell.getTailleImgX());
                s = new SmallShell(randX , -SmallShell.getTailleImgY() );
                listShell.add(s);
                root.getChildren().add(s.getiV());
                break;
            case 1:
                randX = randomS.nextInt(getTailleXS()-MediumShell.getTailleImgX());
                s = new MediumShell(randX , -MediumShell.getTailleImgY() );
                listShell.add(s);
                root.getChildren().add(s.getiV());
                break;
            case 2:
                randX = randomS.nextInt(getTailleXS()-BigShell.getTailleImgX());
                s = new BigShell(randX , -BigShell.getTailleImgY() );
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
            mario.updateImageView(imageX, imageY + (1*Main.getVitesse()));
        }
        else{
            root.getChildren().remove(mario.isIV());
            master.stop();
            Purge();
            Main.gameover();
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
        int vitesse;
        if(Main.getVitesse()==2)
            vitesse=3;
        else
            vitesse=1;
        if (mario.isGoRight() && imageX < getTailleXS() - 50) {
            imageX += 2*vitesse;
        }
        if (mario.isGoLeft() && imageX > 0) {
            imageX -= 2*vitesse;
        }
        if (mario.isGoDown() && imageY < Main.getTailleYS() - 50) {
            imageY += 2*vitesse;
        }
        if (mario.isGoUp() && imageY > 0) {
            imageY -= 2*vitesse;
        }
        mario.updateImageView(imageX, imageY);
    }


    private void lancerBoss() {
        boss=1;
        nbBoss++;
        switch (nbBoss){
            case 1:
                b = new Goomboss(Main.getTailleXS()/2-Goomboss.getTailleImgX()/2,-Goomboss.getTailleImgY());
                break;
            case 2:
                b = new BowserJr(Main.getTailleXS()/2-BowserJr.getTailleImgX()/2,-BowserJr.getTailleImgY());
                break;
            case 3:
                b = new Bowser(Main.getTailleXS()/2-Bowser.getTailleImgX()/2,-Bowser.getTailleImgY());
                break;
            default:
                break;
        }
        root.getChildren().add(b.getiV());
        timelineBoss.stop();
    }

    private void Purge(){
        root.getChildren().removeAll();
    }


    public static int getCptTemps() {
        return cptTemps;
    }
}
