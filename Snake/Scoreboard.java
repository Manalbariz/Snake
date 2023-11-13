package Snake;

import java.util.ArrayList;
import java.util.Collections;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

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

    public int afficherScores(Graphics g, int screenWidth, int yStart) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        int y = yStart;
        for (int score : highScores) {
            String scoreText = "Score: " + score;
            g.drawString(scoreText, (screenWidth - g.getFontMetrics().stringWidth(scoreText)) / 2, y);
            y += g.getFontMetrics().getHeight();
        }
        return y; // Retourne la position y finale
    }
    
}