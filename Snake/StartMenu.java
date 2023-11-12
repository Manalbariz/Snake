package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartMenu extends JPanel implements KeyListener {
    private GameFrame gameFrame; // Référence à GameFrame
    private int currentSelection = 0; // 0 pour "Jouer", 1 pour "Quitter"
    private String[] options = {"Jouer", "Quitter"};

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;

    public StartMenu(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Définir la taille et le style de la police
        Font menuFont = new Font("Arial", Font.BOLD, 40);
        g.setFont(menuFont);

        // Obtenir les métriques de la police pour centrer le texte
        FontMetrics metrics = g.getFontMetrics(menuFont);
        int height = metrics.getHeight();

        // Dessiner les options du menu
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            int width = metrics.stringWidth(option);

            if (i == currentSelection) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.WHITE);
            }

            // Calculer la position x pour centrer le texte
            int x = (SCREEN_WIDTH - width) / 2;
            int y = (SCREEN_HEIGHT - height * options.length) / 2 + height * i;

            g.drawString(option, x, y);
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
            } else if (currentSelection == 1) {
                System.exit(0);
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
