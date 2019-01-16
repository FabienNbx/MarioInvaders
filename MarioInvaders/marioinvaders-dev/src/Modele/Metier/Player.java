package Modele.Metier;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Player {
    //pseudo of the player
    private String pseudo;
    //time to win
    private int tps;

    /**
     * Constructot
     * @param pseudo : Player's pseudo
     * @param tps : time to win
     */
    public Player(String pseudo,int tps) {
        this.pseudo = pseudo;
        this.tps = tps;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo=pseudo;
    }

    public int getTps() {
        return tps;
    }

    public void setTps(int tps) {
        this.tps=tps;
    }

}

