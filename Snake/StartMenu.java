package Snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class StartMenu extends JPanel implements KeyListener {
    private GameFrame gameFrame; // Référence à GameFrame
    private int currentSelection = 0; // 0 pour "Jouer", 1 pour "Quitter"
    private String[] options = {"Jouer", "Score", "Quitter" };

    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 700;

    private Image background;
    private Font menuFont;
    public StartMenu(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        // read img
        try {
            background = ImageIO.read(new File(
                    "C:\\Users\\bariz\\OneDrive\\Bureau\\Nouveau dossier (3)\\T-JAV-501-MPL_5\\Snake\\ressources\\Background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        //charger font
        try{
        Font jimFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\bariz\\OneDrive\\Bureau\\Nouveau dossier (3)\\T-JAV-501-MPL_5\\Snake\\ressources\\JimNightshade-Regular.ttf"));
        jimFont = jimFont.deriveFont(80f);
        menuFont = jimFont;

        }catch (IOException | FontFormatException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw img
        Image scaledBackground = background.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_DEFAULT);
        g.drawImage(scaledBackground, 0, 0, null);

        // Définir la taille et le style de la police
        g.setFont(menuFont);
        // Obtenir les métriques de la police pour centrer le texte
        FontMetrics metrics = g.getFontMetrics(menuFont);
        int height = metrics.getHeight();

        // Dessiner les options du menu
        g.setColor(new Color(133,4,4));
        g.drawString("Ssss-NAKE", 450, 100);


        for (int i = 0; i < options.length; i++) {
            String option = options[i];

            int width = metrics.stringWidth(option);

            if (i == currentSelection) {
                g.setColor(new Color(19,124,5));
            } else {
                g.setColor(Color.BLACK);
            }

            // Calculer la position x pour centrer le texte
            int x = (SCREEN_WIDTH - width) / 2;
            int y = ((SCREEN_HEIGHT - height * options.length) / 2 + height * i)+100;

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
