package Snake;

import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Scoreboard {
    private ArrayList<Integer> highScores;
    private boolean isNewBestScore; // Indicateur pour un nouveau meilleur score
    private static final String SCORES_FILE = "highscores.csv";

    public Scoreboard() {
        highScores = new ArrayList<>();
        isNewBestScore = false; // Initialisé à faux
        loadScoresFromFile();
    }

    public void addScore(int score) {
        boolean wasFirst = highScores.isEmpty() || score > highScores.get(0);
        highScores.add(score);
        Collections.sort(highScores, Collections.reverseOrder());
        if (highScores.size() > 5) {
            highScores.remove(5); // Conserve uniquement les 5 meilleurs scores
        }
        isNewBestScore = wasFirst && score == highScores.get(0); // Vérifie si c'est un nouveau meilleur score
        saveScoresToFile();
    }

    private void saveScoresToFile() {
        try (FileWriter writer = new FileWriter(SCORES_FILE)) {
            for (int score : highScores) {
                writer.write(score + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadScoresFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(Integer.parseInt(line));
            }
            Collections.sort(highScores, Collections.reverseOrder());
        } catch (IOException e) {
            // Fichier non trouvé ou autre erreur de lecture
        }
    }

    public ArrayList<Integer> getHighScores() {
        return new ArrayList<>(highScores); // Retourne une copie de la liste des scores
    }

    public int afficherScores(Graphics g, int screenWidth, int yStart) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));

        final int ESPACEMENT_FIXE = 300; // Ajustez cette valeur selon vos besoins

        int y = yStart;
        for (int score : highScores) {
            if (isNewBestScore && highScores.indexOf(score) == 0) {
                String scoreText = "Score: " + score;
                scoreText += " - new best score!";
                isNewBestScore = false; // Réinitialiser après l'affichage
                g.drawString(scoreText, (screenWidth - g.getFontMetrics().stringWidth(scoreText)) / 2, y);
            }

            y = ESPACEMENT_FIXE;
        }
        return y;
    }
    
}
