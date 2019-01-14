package sample;

import Controller.Controller;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    
    // window's size
    private static int tailleXS = 1440;     
    private static int tailleYS = 900;
    
    // speed different on a computer or on another
    private static int vitesse = 1;
    
    private static Stage stageMain;
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

    @Override
    public void start(Stage primaryStage) throws Exception{
        stageMain=primaryStage;
        creerMain();
    }

    public static void creerMain(){
        affiche("/View/sample.fxml");
    }

    public static void gameover(){
        affiche("/View/gameover.fxml");
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> creerMain());
        delay.play();
    }

    public static void win(){
        affiche("/View/win.fxml");
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> creerMain());
        delay.play();
    }


    /**
     *
     * @param name
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


    public static void creerJeu(){
        Controller c = new Controller(stageMain);
    }

    public static void quit(){
        stageMain.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
