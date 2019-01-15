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

    // FXML objects

    // Buttons to choose the speed
    @FXML private RadioButton LentRB;
    @FXML private RadioButton RapideRB;

    // Buttons to choose the character
    @FXML private Button MarioButton;
    @FXML private Button LuigiButton;

    // TextField to give the player's pseudo
    @FXML private TextField pseudo;

    // Combobox to choose the character
    @FXML private ComboBox<String> combo;

    // Character chosen
    private SimpleStringProperty choix = new SimpleStringProperty();

    // Table with players' scores
    private final ObservableList<Player> data = FXCollections.observableArrayList();
    @FXML private TableView scores = new TableView();
    @FXML private TableColumn pseudocol;
    @FXML private TableColumn tempscol;

    /**
     * Launched when the window is loading
     */
    @FXML
    private void initialize(){
        // Keep the pseudo between consecutive games
        if(Main.getPseudo()!=null) {
            pseudo.textProperty().setValue(Main.getPseudo());
        }

        // Collect registered scores with pseudos
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

        // Sort players by scores
        Comparator<Player> c = Comparator.comparingInt(Player::getTps);
        data.sort(c);

        // Show scores
        scores.setItems(data);

        // Keep the speed between consecutive games
        if(Main.getVitesse()==2){
            RapideRB.setSelected(true);
        }
        else{
            LentRB.setSelected(true);
        }

        // Bind the selected value of the combobox with the "choix" variable
        choix.bindBidirectional(combo.valueProperty());

        // Change buttons' color depending on the character('s choice
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

    /**
     * Click on the Quit button
     */
    @FXML
    public void ClickQuit() {
        Main.quit();
    }

    /**
     * Click on the Start button
     */
    public void ClickStart() {
        // check if the pseudo is correct
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

    /**
     * Click on the Mario Button : changes the character's choice to Mario
     */
    public void onClickMario(){
        choix.setValue("Mario");
    }

    /**
     * Click on the Luigi Button : changes the character's choice to Luigi
     */
    public void onClickLuigi(){
        choix.setValue("Luigi");
    }
}
