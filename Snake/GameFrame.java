package Snake;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private Scoreboard scoreboard;
    private StartMenu startMenu;
    private GamePanel gamePanel;

    GameFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        scoreboard = new Scoreboard(); // Initialisation de Scoreboard
        startMenu = new StartMenu(this, scoreboard);
        gamePanel = new GamePanel(this, scoreboard);

        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.setResizable(false);
        this.pack();

        this.setLocationRelativeTo(null);
    }

    public void showStartMenu() {
        this.setContentPane(startMenu);
        startMenu.requestFocusInWindow();

        this.setVisible(true);
    }

    public void startGame() {
        gamePanel.initializeGame(); // Réinitialise le jeu
        this.setContentPane(gamePanel); // Utilise l'instance de GamePanel stockée
        gamePanel.startGame();
        gamePanel.requestFocusInWindow();
        this.revalidate();
    }

    public void returnToMenu() {
        showStartMenu();
 
        
    }

    public void updateHighScores(int score) {
        scoreboard.addScore(score);
    }
}
