package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import sample.Main;

import javax.swing.text.html.ImageView;

public class ControllerView {
    @FXML private RadioButton LentRB;
    @FXML private RadioButton RapideRB;
    @FXML private ImageView marioChoix;
//    @FXML private ObservableList<String> options = FXCollections.observableArrayList("Mario","Luigi");
    @FXML private final ComboBox combo = new ComboBox();
    private SimpleStringProperty selectedItem = new SimpleStringProperty();
    private SimpleStringProperty choix = new SimpleStringProperty();


    @FXML
    private void initialize(){
        if(Main.getVitesse()==2){
            RapideRB.setSelected(true);
        }
        else{
            LentRB.setSelected(true);
        }
        //selectedItem.bindBidirectional(choix);
        //combo.getSelectionModel().selectedItemProperty().getName();
    }
    @FXML
    public void ClickQuit(ActionEvent actionEvent) {
        Main.quit();
    }

    @FXML
    public void onChange(){
        //System.out.println(selectedItem);
        //choix.setValue(combo.getSelectionModel().getSelectedItem().toString());
        //System.out.println(choix);
    }

    public void ClickStart(ActionEvent actionEvent) {
        if(LentRB.isSelected()){
            Main.setVitesse(1);
        }
        else if(RapideRB.isSelected()){
            Main.setVitesse(2);
        }
        Main.creerJeu();
        //selectedItem.set("Luigi");

    }
}
