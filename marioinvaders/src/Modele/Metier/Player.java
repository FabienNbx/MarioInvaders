package Modele.Metier;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Player {
    private final SimpleStringProperty pseudo;
    private final SimpleIntegerProperty tps;

    public Player(String pseudo,int tps) {
        this.pseudo = new SimpleStringProperty(pseudo);
        this.tps = new SimpleIntegerProperty(tps);
    }

    public String getPseudo() {
        return pseudo.get();
    }

    public void setPseudo(String pseudo) {
        this.pseudo.set(pseudo);
    }

    public int getTps() {
        return tps.get();
    }

    public void setTps(int tps) {
        this.tps.set(tps);
    }

}

