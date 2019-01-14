package Controller;

import Modele.Metier.Player;
import javafx.fxml.FXML;
import sample.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerWin {
    @FXML
    private void initialize() {
        try {
            List<Player> data = new ArrayList();
            File f = new File("resultats.dat");
            FileReader lecture = new FileReader(f);
            BufferedReader buff = new BufferedReader(lecture);
            String ligne;
            while ((ligne = buff.readLine()) != null) {
                String[] parts = ligne.split(" ");
                data.add(new Player(parts[0], Integer.parseInt(parts[1])));
            }
            buff.close();
            lecture.close();

            for (Player p : data) {
                if (p.getPseudo().equals(Main.getPseudo())) {
                    if (p.getTps() > Controller.getCptTemps() * 10 / 1000)
                        p.setTps(Controller.getCptTemps() * 10 / 1000);
                }
            }
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Player p : data) {
                bw.write(p.getPseudo() + " " + p.getTps());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
