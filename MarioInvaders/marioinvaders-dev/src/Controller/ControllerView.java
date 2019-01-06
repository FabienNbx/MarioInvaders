package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.Main;

public class ControllerView {

    @FXML
    public void ClickQuit(ActionEvent actionEvent) {
        Main.quit();
    }

    public void ClickStart(ActionEvent actionEvent) {
        Main.creerJeu();
    }
}
