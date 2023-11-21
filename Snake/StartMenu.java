package Snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class StartMenu extends JPanel implements KeyListener {
    private GameFrame gameFrame; // Référence à GameFrame
    private Scoreboard scoreboard;
    private boolean afficherLesScores = false;
    private int currentSelection = 0; // 0 pour "Jouer", 1 pour "Quitter"

    private String[] options = { "Jouer", "Score", "Quitter" };

    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 700;

    private Image background;
    private Font menuFont;
    private Clip clip;
    private AudioInputStream audioStream;

    public StartMenu(GameFrame gameFrame, Scoreboard scoreboard)throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.gameFrame = gameFrame;
        this.scoreboard = scoreboard;
        // read img
        try {
            background = ImageIO.read(new File(
                    "C:\\Users\\antho\\Desktop\\T-JAV-501-MPL_5\\Snake\\ressources\\Background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());

        // add sound
        audioStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\antho\\Desktop\\T-JAV-501-MPL_5\\Snake\\ressources\\risk-136788.wav").getAbsoluteFile());
        clip = AudioSystem.getClip();
        
        
        clip.open(audioStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        // charger font
        try {
            Font jimFont = Font.createFont(Font.TRUETYPE_FONT, new File(
                    "C:\\Users\\antho\\Desktop\\T-JAV-501-MPL_5\\Snake\\ressources\\JimNightshade-Regular.ttf"));
            jimFont = jimFont.deriveFont(80f);
            menuFont = jimFont;

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!afficherLesScores) {
            // draw img
            Image scaledBackground = background.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_DEFAULT);
            g.drawImage(scaledBackground, 0, 0, null);

            // Définir la taille et le style de la police
            g.setFont(menuFont);
            // Obtenir les métriques de la police pour centrer le texte
            FontMetrics metrics = g.getFontMetrics(menuFont);
            int height = metrics.getHeight();

            // Dessiner les options du menu
            g.setColor(new Color(133, 4, 4));
            g.drawString("Ssss-NAKE", 450, 100);

            for (int i = 0; i < options.length; i++) {
                String option = options[i];

                int width = metrics.stringWidth(option);

                if (i == currentSelection) {
                    g.setColor(new Color(19, 124, 5));
                } else {
                    g.setColor(Color.BLACK);
                }

                // Calculer la position x pour centrer le texte
                int x = (SCREEN_WIDTH - width) / 2;
                int y = ((SCREEN_HEIGHT - height * options.length) / 2 + height * i) + 100;

                g.drawString(option, x, y);
            }
        } else {
                // Code pour afficher le tableau des scores
                if (scoreboard != null && !scoreboard.getHighScores().isEmpty()) {
                    scoreboard.afficherScores(g, getWidth(), 100);
            } else {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 40));
                String noScoreText = "Aucun score disponible";
                g.drawString(noScoreText, (getWidth() - g.getFontMetrics().stringWidth(noScoreText)) / 2, getHeight() / 2);
            }
        }
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            currentSelection = (currentSelection - 1 + options.length) % options.length;
        } else if (key == KeyEvent.VK_DOWN) {
            currentSelection = (currentSelection + 1) % options.length;
        } else if (key == KeyEvent.VK_ENTER) {
            if (currentSelection == 0) {
                gameFrame.startGame();
                
                clip.stop();
              
                afficherLesScores = false;

            } else if (currentSelection == 1) {
                afficherLesScores = true;
                repaint(); // Redessine le composant pour afficher les scores
            } else if (currentSelection == 2) {
                System.exit(0);
                clip.stop();
            }
        } else if (key == KeyEvent.VK_ESCAPE) { // Ajout d'une touche pour revenir au menu
            afficherLesScores = false;
        }
        repaint();
    }

    

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
