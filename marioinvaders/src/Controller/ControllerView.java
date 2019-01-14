package Controller;

import Modele.Metier.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;

import javax.swing.text.html.ImageView;

public class ControllerView {
    @FXML private RadioButton LentRB;
    @FXML private RadioButton RapideRB;
    @FXML private Button MarioButton;
    @FXML private Button LuigiButton;
    @FXML private TextField pseudo;
    @FXML private ComboBox<String> combo;
    private SimpleStringProperty choix = new SimpleStringProperty();
    private final ObservableList<Player> data = FXCollections.observableArrayList(new Player("test1",82),new Player("test2",67));
    @FXML private TableView scores = new TableView();
    @FXML private TableColumn pseudocol;
    @FXML private TableColumn tempscol;

    @FXML
    private void initialize(){
        pseudocol.setCellValueFactory(
                new PropertyValueFactory<>("pseudo"));
        tempscol.setCellValueFactory(
                new PropertyValueFactory<>("tps"));
        scores.setItems(data);
        if(Main.getVitesse()==2){
            RapideRB.setSelected(true);
        }
        else{
            LentRB.setSelected(true);
        }
        MarioButton.setStyle("-fx-background-image: url('file:src/images/marioChoix.png')");
        LuigiButton.setStyle("-fx-background-image: url('file:src/images/luigiChoix.png')");
        choix.bindBidirectional(combo.valueProperty());
        choix.addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("Mario")){
                Main.setPerso(0);
                MarioButton.setStyle("-fx-background-image: url('file:src/images/marioChoix.png'); -fx-border-color: red; -fx-border-width: 3px");
                LuigiButton.setStyle("-fx-background-image: url('file:src/images/luigiChoix.png'); -fx-border-color: black");
            }
            else{
                Main.setPerso(1);
                MarioButton.setStyle("-fx-background-image: url('file:src/images/marioChoix.png'); -fx-border-color: black");
                LuigiButton.setStyle("-fx-background-image: url('file:src/images/luigiChoix.png'); -fx-border-color: red; -fx-border-width: 3px");
            }
        });
    }

    @FXML
    public void ClickQuit(ActionEvent actionEvent) {
        Main.quit();
    }

    public void ClickStart(ActionEvent actionEvent) {
        if(!pseudo.getText().equals("")) {
            if (LentRB.isSelected()) {
                Main.setVitesse(1);
            } else if (RapideRB.isSelected()) {
                Main.setVitesse(2);
            }
            Main.setPseudo(pseudo.getText());
            Main.creerJeu();
        }
    }

    public void onClickMario(){
        choix.setValue("Mario");
    }

    public void onClickLuigi(){
        choix.setValue("Luigi");
    }
}
