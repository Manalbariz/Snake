package Snake;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private Scoreboard scoreboard;
    private StartMenu startMenu;
    private Level1 level1;
    private Level2 level2;
    private GamePanel gamePanel;
    public static boolean isLevel2 = false;
    public static boolean isLevel1 = false;

    private AudioInputStream audioStream5;

    private Clip clip5;


    GameFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        scoreboard = new Scoreboard(); // Initialisation de Scoreboard
        gamePanel = new GamePanel(this, scoreboard);
        startMenu = new StartMenu(this, gamePanel, scoreboard);
        level1 = new Level1(this);
        level2 = new Level2(this);


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
    public void showLevel1Challenge() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.setContentPane(level1);
        level1.requestFocusInWindow();

        this.setVisible(true);
        // add sound
        audioStream5 = AudioSystem.getAudioInputStream(
                new File(".\\T-JAV-501-MPL_5\\Snake\\ressources\\level1.wav").getAbsoluteFile());
        clip5 = AudioSystem.getClip();

        if (isLevel1) {
            clip5.open(audioStream5);

            clip5.loop(0);
        }
    }
    public void showLevel2Challenge() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.setContentPane(level2);
        level2.requestFocusInWindow();

        this.setVisible(true);
        // add sound
        audioStream5 = AudioSystem.getAudioInputStream(
                new File(".\\T-JAV-501-MPL_5\\Snake\\ressources\\level2.wav").getAbsoluteFile());
        clip5 = AudioSystem.getClip();

            clip5.open(audioStream5);

            clip5.loop(0);
            
    }

    public void startGame() {
        gamePanel.initializeGame(); // Réinitialise le jeu
        this.setContentPane(gamePanel); // Utilise l'instance de GamePanel stockée
        gamePanel.startGame();
        gamePanel.requestFocusInWindow();
        this.revalidate();
    }
   
    public void Level2() {
        gamePanel.initializeGame();
        this.setContentPane(gamePanel);
        gamePanel.requestFocusInWindow();
        this.revalidate();
        isLevel2 = true;
    
    }

    public void returnToMenu() {
        showStartMenu();

    }

    public void updateHighScores(int score) {
        scoreboard.addScore(score);
    }
}
