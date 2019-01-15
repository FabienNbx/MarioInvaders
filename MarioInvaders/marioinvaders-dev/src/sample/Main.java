package sample;

import Controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    
    // window's size
    private static int tailleXS = 1440;     
    private static int tailleYS = 900;
    
    // speed different on a computer or on another
    private static int vitesse = 1;

    // 0 = Mario and 1 = Luigi
    private static int perso=0;
    private static String pseudo;

    private static final MediaPlayer startSound = new MediaPlayer(new Media(new File("src/sons/startSound.mp3").toURI().toString()));
    private static final MediaPlayer playSound = new MediaPlayer(new Media(new File("src/sons/playSound.mp3").toURI().toString()));
    private static final MediaPlayer loseSound = new MediaPlayer(new Media(new File("src/sons/loseSound.mp3").toURI().toString()));
    private static final MediaPlayer winSound = new MediaPlayer(new Media(new File("src/sons/winSound.mp3").toURI().toString()));
    private static final MediaPlayer niveauPlusSound = new MediaPlayer(new Media(new File("src/sons/niveauPlus.mp3").toURI().toString()));
    private static final MediaPlayer lifeLostSound = new MediaPlayer(new Media(new File("src/sons/lifeLost.mp3").toURI().toString()));

    private static Stage stageMain;

    /**
     * play the start sound
     */
    public static void playStartSound(){
        startSound.play();
    }

    /**
     * play the "in game" sound
     */
    public static void playPlaySound(){
        playSound.play();
        startSound.stop();
    }

    /**
     * play the lose sound
     */
    public static void playLoseSound(){
        playSound.stop();
        loseSound.play();
    }

    /**
     * play the win sound
     */
    public static void playWinSound(){
        playSound.stop();
        winSound.play();
    }

    /**
     * play the level sound
     */
    public static void playNiveauSound(){
        niveauPlusSound.play();
        playSound.pause();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(3000),
                c -> {Main.stopNiveauSound(); Main.playPlaySound();}));
        timeline.setCycleCount(1);
        timeline.play();
    }

    /**
     * play the life sound
     */
    public static void playLifeSound(){
        lifeLostSound.play();
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(3000),
                c -> {Main.stopLifeSound();}));
        timeline.setCycleCount(1);
        timeline.play();
    }
    /**
     * stop the life sound
     */
    public static void stopLifeSound() {
        lifeLostSound.stop();
    }
    /**
     * stop the level sound
     */
    public static void stopNiveauSound() {
        niveauPlusSound.stop();
    }

    /**
     * start the game
     * @param primaryStage : main stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        stageMain=primaryStage;
        creerMain();
    }

    /**
     * start the first window
     */
    public static void creerMain(){
        Main.playStartSound();
        affiche("/View/sample.fxml");
    }

    /**
     * when mario is dead
     */
    public static void gameover(){
        Main.playLoseSound();
        affiche("/View/gameover.fxml");
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished(event -> creerMain());
        delay.play();
    }

    public static void win(){
        Main.playWinSound();
        affiche("/View/win.fxml");
        PauseTransition delay = new PauseTransition(Duration.seconds(6));
        delay.setOnFinished(event -> creerMain());
        delay.play();
    }


    /**
     * display a view
     * @param name : view's name
     */
    public static void affiche(String name){
        try {
            final URL url = Main.class.getResource(name);
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            final AnchorPane root = fxmlLoader.load();
            final Scene scene = new Scene(root);
            scene.getStylesheets().add("View/css/style.css");
            stageMain.setScene(scene);
        }
        catch (IOException e){
            System.out.println(e);

        }
        stageMain.setTitle("Mario Invaders");
        stageMain.setResizable(false);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stageMain.setX((primScreenBounds.getWidth() - stageMain.getWidth()) / 2);
        stageMain.setY((primScreenBounds.getHeight() - stageMain.getHeight()) / 2);
        stageMain.show();
    }


    /**
     * start the game
     */
    public static void creerJeu(){
        Main.playPlaySound();
        Controller c = new Controller(stageMain);
    }

    /**
     * quit the game
     */
    public static void quit(){
        stageMain.close();
    }

    public static int getTailleXS() {
        return tailleXS;
    }

    public static int getTailleYS() {
        return tailleYS;
    }

    public static int getVitesse() {
        return vitesse;
    }

    public static void setVitesse(int vitesse) {
        Main.vitesse = vitesse;
    }

    public static int getPerso() { return perso; }

    public static void setPerso(int perso) { Main.perso = perso; }

    public static String getPseudo() {return pseudo;  }

    public static void setPseudo(String pseudo) {Main.pseudo = pseudo; }

    public static void main(String[] args) {
        launch(args);
    }
}
