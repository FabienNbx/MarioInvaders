package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import sample.Main;

public class ControllerView {
    @FXML private RadioButton LentRB;
    @FXML private RadioButton RapideRB;

    @FXML
    private void initialize(){
        if(Main.getVitesse()==2){
            RapideRB.setSelected(true);
        }
        else{
            LentRB.setSelected(true);
        }
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
}
