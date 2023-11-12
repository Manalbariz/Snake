package Snake;

import java.util.ArrayList;
import java.util.Collections;

public class Scoreboard {
    private ArrayList<Integer> highScores;

    public Scoreboard() {
        highScores = new ArrayList<>();
    }

    public void addScore(int score) {
        highScores.add(score);
        Collections.sort(highScores, Collections.reverseOrder());
        if (highScores.size() > 5) {
            highScores.remove(5); // Conserve uniquement les 5 meilleurs scores
        }
    }

    public ArrayList<Integer> getHighScores() {
        return new ArrayList<>(highScores); // Retourne une copie de la liste des scores
    }
}