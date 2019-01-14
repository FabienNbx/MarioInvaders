package Controller;

import javafx.fxml.FXML;
import sample.Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ControllerWin {
    @FXML
    private void initialize(){
        try {
            File f = new File("resultats.dat");
            FileWriter fw = new FileWriter(f,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Main.getPseudo()+" "+Controller.getCptTemps()*10/1000);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
