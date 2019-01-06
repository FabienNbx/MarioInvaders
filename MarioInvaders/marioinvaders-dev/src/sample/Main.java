package sample;

import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private static int tailleXS = 1290;
    private static int tailleYS = 900;
    private static Stage stageMain;
    public static int getTailleXS() {
        return tailleXS;
    }

    public static int getTailleYS() {
        return tailleYS;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stageMain=primaryStage;
        creerMain();
    }

    public static void creerMain(){
        try {
            final URL url = Main.class.getResource("/View/sample.fxml");
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            final AnchorPane root = fxmlLoader.load();
            final Scene scene = new Scene(root, 200, 200);
            stageMain.setScene(scene);
        }
        catch (IOException e){
            System.out.println(e);

        }
        stageMain.setTitle("Mario Invaders");
        stageMain.setResizable(false);
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
