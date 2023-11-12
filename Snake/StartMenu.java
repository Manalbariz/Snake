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
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // Utilise les constantes définies
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessiner le menu ici
        for (int i = 0; i < options.length; i++) {
            if (i == currentSelection) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.WHITE);
            }
            // Dessiner chaque option
            g.drawString(options[i], 50, 50 + i * 30); // Position à ajuster
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
