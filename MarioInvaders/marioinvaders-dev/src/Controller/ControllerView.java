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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;

public class ControllerView {
    @FXML private RadioButton LentRB;
    @FXML private RadioButton RapideRB;
    @FXML private Button MarioButton;
    @FXML private Button LuigiButton;
    @FXML private TextField pseudo;
    @FXML private ComboBox<String> combo;
    private SimpleStringProperty choix = new SimpleStringProperty();
    private final ObservableList<Player> data = FXCollections.observableArrayList();
    @FXML private TableView scores = new TableView();
    @FXML private TableColumn pseudocol;
    @FXML private TableColumn tempscol;

    @FXML
    private void initialize(){
        try{
            InputStream flux=new FileInputStream("resultats.dat");
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){
                String[] parts = ligne.split(" ");
                data.add(new Player(parts[0],Integer.parseInt(parts[1])));
            }
            buff.close();
            lecture.close();
            flux.close();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        pseudocol.setCellValueFactory(
                new PropertyValueFactory<>("pseudo"));
        tempscol.setCellValueFactory(
                new PropertyValueFactory<>("tps"));
        Comparator<Player> c = Comparator.comparingInt(Player::getTps);
        data.sort(c);
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
        else{
            pseudo.setStyle("-fx-border-color: red; -fx-border-width: 3px; -fx-border-radius: 4px;");
        }
    }

    public void onClickMario(){
        choix.setValue("Mario");
    }

    public void onClickLuigi(){
        choix.setValue("Luigi");
    }
}
