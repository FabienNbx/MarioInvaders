package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import sample.Main;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.swing.text.html.ImageView;
import java.awt.*;

public class ControllerView {
    @FXML private RadioButton LentRB;
    @FXML private RadioButton RapideRB;
    @FXML private Button MarioButton;
    @FXML private Button LuigiButton;
    @FXML private ImageView marioChoix;
//    @FXML private ObservableList<String> options = FXCollections.observableArrayList("Mario","Luigi");
    @FXML private ComboBox<String> combo;
    private SimpleStringProperty choix = new SimpleStringProperty();


    @FXML
    private void initialize(){
        if(Main.getVitesse()==2){
            RapideRB.setSelected(true);
        }
        else{
            LentRB.setSelected(true);
        }
        choix.bindBidirectional(combo.valueProperty());
        choix.addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("Mario")){
                Main.setPerso(0);
                MarioButton.setStyle("-fx-background-color: rgba(255, 0, 0, 0.8) ");
                LuigiButton.setStyle("-fx-background-color: rgba(195, 195, 195, 1) ");
            }
            else{
                Main.setPerso(1);
                LuigiButton.setStyle("-fx-background-color: rgba(0, 200, 0, 0.8) ");
                MarioButton.setStyle("-fx-background-color: rgba(195, 195, 195, 1)");
            }
        });
    }

    @FXML
    public void ClickQuit(ActionEvent actionEvent) {
        Main.quit();
    }

    public void ClickStart(ActionEvent actionEvent) {
        if(LentRB.isSelected()){
            Main.setVitesse(1);
        }
        else if(RapideRB.isSelected()){
            Main.setVitesse(2);
        }
        Main.creerJeu();
    }

    public void onClickMario(){
        choix.setValue("Mario");
    }

    public void onClickLuigi(){
        choix.setValue("Luigi");
    }
}
