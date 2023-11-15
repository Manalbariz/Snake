package Snake;

import java.util.ArrayList;
import java.util.Collections;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Scoreboard {
    private ArrayList<Integer> highScores;
    private boolean isNewBestScore; // Indicateur pour un nouveau meilleur score

    public Scoreboard() {
        
        highScores = new ArrayList<>();
        isNewBestScore = false; // Initialisé à faux
    }

    public void addScore(int score) {
        boolean wasFirst = highScores.isEmpty() || score > highScores.get(0);
        highScores.add(score);
        Collections.sort(highScores, Collections.reverseOrder());
        if (highScores.size() > 5) {
            highScores.remove(5); // Conserve uniquement les 5 meilleurs scores
        }
        isNewBestScore = wasFirst && score == highScores.get(0); // Vérifie si c'est un nouveau meilleur score
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
            if (isNewBestScore && highScores.indexOf(score) == 0) {
                scoreText += " - new best score!";
                isNewBestScore = false; // Réinitialiser après l'affichage
            }
            g.drawString(scoreText, (screenWidth - g.getFontMetrics().stringWidth(scoreText)) / 2, y);
            y += g.getFontMetrics().getHeight();
        }
        return y; // Retourne la position y finale
    }
    
}
