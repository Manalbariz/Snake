package Snake;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private StartMenu startMenu;
    private GamePanel gamePanel;

    GameFrame() {
        startMenu = new StartMenu(this);
        gamePanel = new GamePanel(this);

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
}
