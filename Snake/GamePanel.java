package Snake;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.text.StyledEditorKit.BoldAction;

import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    private GameFrame gameFrame; // Ajoute une référence à GameFrame
    private Scoreboard scoreboard;

    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 700;
    public static final int UNIT_SIZE = 30; // Apples
    public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE; // Numbr of apples in the screen
    public static final int DELAY = 75; // miniteur : délai plus élevé = jeu plus lent
    public static int currentDelay = DELAY;

    // public static boolean Nscore;

    private Clip clip;
    private AudioInputStream audioStream;
    private Clip clip2;
    private AudioInputStream audioStream2;
    private Clip clip3;
    private AudioInputStream audioStream3;
    private Clip clip4;
    private AudioInputStream audioStream4;
    private boolean clipOpen = false;
    public boolean eaten = false;

    // the body of the snake1
    public final int x[] = new int[GAME_UNITS]; // the size is GAME_UNITS because the snake can't be bigger than the
                                                // nmbr of units
    public final int y[] = new int[GAME_UNITS];
    //
    // the body of the snake2
    public final int x2[] = new int[GAME_UNITS];
    public final int y2[] = new int[GAME_UNITS];
    //

    // The nmbr of snake part in the beginning
    public static int bodyParts;
    public static int bodyParts2;
    public int applesEaten;
    //

    // position of apples
    public int appleX;
    public int appleY;
    public int appleXx;
    public int appleYy;
    //

    // position of Bombs
    public int bombX[] = new int[20];
    public int bombY[] = new int[20];

    //

    // Snake direction
    static char direction; // beging by going right
    static char direction2;
    //

    public boolean running;
    public Timer timer;
    public Random random;

    // private Image backgroundGame;

    GamePanel(GameFrame gameFrame, Scoreboard scoreboard)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.gameFrame = gameFrame; // Initialise la référence à GameFrame
        this.scoreboard = scoreboard;
        // // read img
        // try {
        // backgroundGame = ImageIO.read(new File(
        // "C:\\Users\\bariz\\OneDrive\\Bureau\\Nouveau dossier
        // (3)\\T-JAV-501-MPL_5\\Snake\\ressources\\Game3.jpg"));
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // modif size game panel
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        clipOpen = false;
        // add sound 1
        audioStream = AudioSystem.getAudioInputStream(new File(
                "C:\\Users\\antho\\Desktop\\T-JAV-501-MPL_5\\T-JAV-501-MPL_5\\Snake\\ressources\\punch.wav")
                .getAbsoluteFile());
        clip = AudioSystem.getClip();
        // add sound 2
        audioStream2 = AudioSystem.getAudioInputStream(new File(
                "C:\\Users\\antho\\Desktop\\T-JAV-501-MPL_5\\T-JAV-501-MPL_5\\Snake\\ressources\\eat.wav")
                .getAbsoluteFile());
        clip2 = AudioSystem.getClip();

    }

    public void startGame() {

        newApple(); // create a new apple on screen

        running = true;
        timer = new Timer(DELAY, this); // we use "this" because we are using the ActionListener interface
        timer.start();
    }

    public void initializeGame() {

        // Réinitialise les variables d'état
        bodyParts = 5;
        if (gameFrame.Nscore == true) {
            applesEaten = 0;
        }
        direction = 'R';
        bodyParts2 = 5;
        direction2 = 'R';
        // Réinitialise la position du serpent 1
        for (int i = 0; i < bodyParts; i++) {
            x[i] = UNIT_SIZE * 3 - i * UNIT_SIZE; // Assure-toi que cela commence dans une case
            y[i] = UNIT_SIZE * 3; // Cette valeur doit également être un multiple de UNIT_SIZE
        }
        // Réinitialise la position du serpent 2
        for (int i = 0; i < bodyParts2; i++) {
            x2[i] = UNIT_SIZE * 3 - i * UNIT_SIZE;
            y2[i] = UNIT_SIZE * 3;
        }
        newApple();
        running = true;
    }

    public void paintComponent(Graphics graph) {

        super.paintComponent(graph);
        // // draw img
        // Image scaledBackgroundGame = backgroundGame.getScaledInstance(SCREEN_WIDTH,
        // SCREEN_HEIGHT, Image.SCALE_DEFAULT);
        // graph.drawImage(scaledBackgroundGame, 0, 0, null);
        if (running) {
            if (gameFrame.isLevel3) {
                Drawer.draw2(graph, SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE, appleX, appleY, x, y, x2, y2, bodyParts,
                        bodyParts2, eaten, appleXx, appleYy, bombX, bombY);
            } else {
                Drawer.draw(graph, SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE, appleX, appleY, x, y, bodyParts, eaten,
                        appleXx,
                        appleYy, bombX, bombY);
            }

        } else {
            gameOver(graph);
        }
    }

    public void newApple() { // add an apple

        // generate a random apple in one of the case (on grid)
        appleX = random.nextInt(1, (int) (SCREEN_WIDTH / UNIT_SIZE) - 1) * UNIT_SIZE;
        appleY = random.nextInt(1, (int) (SCREEN_HEIGHT / UNIT_SIZE) - 1) * UNIT_SIZE;

        //
    }

    public void newBomb() { // add a bomb
        for (int i = 19; i > 0; i--) {
            if ((bombX[i] != appleX) & (bombY[i] != appleY)) {
                bombX[i] = random.nextInt(1, (int) (SCREEN_WIDTH / UNIT_SIZE) - 1) * UNIT_SIZE;
                bombY[i] = random.nextInt(1, (int) (SCREEN_HEIGHT / UNIT_SIZE) - 1) * UNIT_SIZE;
            } else {
                bombX[i] += UNIT_SIZE;
                bombY[i] += UNIT_SIZE;
            }
        }
    }

    public void suppBomb() { // add a bomb
        for (int i = 19; i > 0; i--) {

            bombX[i] = -40;
            bombY[i] = -40;

        }
    }

    public void checkApple() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            appleXx = appleX;
            appleYy = appleY;
            eaten = true;
            if (!clip2.isRunning()) {
                try {
                    audioStream2 = AudioSystem.getAudioInputStream(new File(
                            "C:\\Users\\antho\\Desktop\\T-JAV-501-MPL_5\\T-JAV-501-MPL_5\\Snake\\ressources\\eat.wav")
                            .getAbsoluteFile());
                    clip2 = AudioSystem.getClip();
                    clip2.open(audioStream2);
                    clip2.loop(0);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
            }

            bodyParts++;
            applesEaten++;
            if (clip2.isRunning()) {
                clip2.stop();
            }
            if (gameFrame.isLevel1) {
                if (bodyParts == 7) {
                    running = false;
                    gameFrame.showLevel2Challenge();
                    newBomb();
                }

            }
            if (gameFrame.isLevel2) {
                if (bodyParts == 8) {

                    running = false;
                    gameFrame.showLevel3Challenge();
                    suppBomb();

                }

            }

            newApple();

        }
        if ((x2[0] == appleX) && (y2[0] == appleY)) {
            appleXx = appleX;
            appleYy = appleY;
            eaten = true;
            if (!clip2.isRunning()) {
                try {
                    audioStream2 = AudioSystem.getAudioInputStream(new File(
                            "C:\\Users\\antho\\Desktop\\T-JAV-501-MPL_5\\T-JAV-501-MPL_5\\Snake\\ressources\\eat.wav")
                            .getAbsoluteFile());
                    clip2 = AudioSystem.getClip();
                    clip2.open(audioStream2);
                    clip2.loop(0);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
            }
            bodyParts2++;
            applesEaten++;
            newApple();

        }

    }

    public void checkCollisions() {
        // snake1
        // check if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                handleCollision();
                running = false;

            }
        }
        //
        // check if head collides with borders
        // check if head collides with left border
        if (x[0] <= 0) {
            handleCollision();
            suppBomb();
            running = false;

        }
        //
        // check if head collides with right border
        if (x[0] > SCREEN_WIDTH - UNIT_SIZE) { // add UNIT_SIZE*2 to keep the head of the snake on screen (SCREEN_WIDTH
            handleCollision();
            suppBomb();
            running = false;

        }
        //
        // check if head collides with up border
        if (y[0] < 0) {
            handleCollision();
            suppBomb();
            running = false;
        }
        //
        // check if head collides with down border
        if (y[0] > SCREEN_HEIGHT - UNIT_SIZE) { // (SCREEN_HEIGHT - UNIT_SIZE * 3)
            handleCollision();
            suppBomb();
            running = false;

        }
        //
        //

        // check collision with bombs
        for (int i = 19; i > 0; i--) {
            if ((x[0] == bombX[i]) && (y[0] == bombY[i])) {

                try {
                    audioStream4 = AudioSystem.getAudioInputStream(new File(
                            "C:\\Users\\antho\\Desktop\\T-JAV-501-MPL_5\\T-JAV-501-MPL_5\\Snake\\ressources\\explosion.wav")
                            .getAbsoluteFile());
                    clip4 = AudioSystem.getClip();
                    clip4.open(audioStream4);
                    clip4.loop(0);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
                suppBomb();
                running = false;

            }
        }

        //
        // snake2
        // check if head collides with body
        for (int i = bodyParts2; i > 0; i--) {
            if ((x2[0] == x2[i]) && (y2[0] == y2[i])) {
                handleCollision();
                running = false;

            }
        }
        //
        // check if head collides with borders
        // check if head collides with left border
        if (x2[0] <= 0) {
            handleCollision();
            running = false;

        }
        //
        // check if head collides with right border
        if (x2[0] > SCREEN_WIDTH - UNIT_SIZE) { // add UNIT_SIZE*2 to keep the head of the snake on screen (SCREEN_WIDTH
            handleCollision();
            running = false;

        }
        //
        // check if head collides with up border
        if (y2[0] < 0) {
            handleCollision();
            running = false;
        }
        //
        // check if head collides with down border
        if (y2[0] > SCREEN_HEIGHT - UNIT_SIZE) { // (SCREEN_HEIGHT - UNIT_SIZE * 3)
            handleCollision();
            running = false;

        }
        //
        //

        //

        // stop timer
        if (!running) {
            timer.stop();

        }

        //

    }

    private void handleCollision() {
        if (clipOpen) {
            clip.stop();
            clip.close();
        }

        try {
            clip.open(audioStream);
            clip.loop(0);
            clipOpen = true;
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        gameFrame.isLevel3 = false;

        running = false;
    }

    // public void Level1Challenge(Graphics g) {
    // while(!running) {
    // g.setColor(Color.RED);
    // g.setFont(new Font("Arial", Font.BOLD, 100));
    // FontMetrics metrics1 = getFontMetrics(g.getFont());
    // String gameOverText = "Level 1";
    // g.drawString(gameOverText, (SCREEN_WIDTH -
    // metrics1.stringWidth(gameOverText)) / 2, SCREEN_HEIGHT / 4);
    // }

    // }

    public void gameOver(Graphics g) {
        gameFrame.Nscore = true;
        
        try {
            audioStream3 = AudioSystem.getAudioInputStream(new File(
                "C:\\Users\\antho\\Desktop\\T-JAV-501-MPL_5\\T-JAV-501-MPL_5\\Snake\\ressources\\failure.wav")
                    .getAbsoluteFile());
            clip3 = AudioSystem.getClip();
            if (!clip3.isRunning()) {
                clip3.open(audioStream3);
                clip3.loop(0);
            }
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        
        // Ajout du score au tableau
        scoreboard.addScore(applesEaten);
        
        // Affichage de "Game Over" en plus gros et plus haut
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 100));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        String gameOverText = "Game Over";
        
        g.drawString(gameOverText, (SCREEN_WIDTH - metrics1.stringWidth(gameOverText)) / 2, SCREEN_HEIGHT / 4); // Position
        // plus
        // haute
        
        // Position de départ pour le tableau des scores
        int yStart = SCREEN_HEIGHT / 3;
        int y = scoreboard.afficherScores(g, SCREEN_WIDTH, yStart);
        
        // Affichage du tableau des scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        for (int score : scoreboard.getHighScores()) {
            g.drawString("Score: " + score, (SCREEN_WIDTH - g.getFontMetrics().stringWidth("Score: " + score)) / 2, y);
            y += g.getFontMetrics().getHeight();
        }
        
        // Espace entre le tableau des scores et le score du joueur
        y += g.getFontMetrics().getHeight(); // Double saut de ligne
        
        // Affichage du score actuel du joueur
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String scoreText = "Votre Score: " + applesEaten;
        g.drawString(scoreText, (SCREEN_WIDTH - g.getFontMetrics().stringWidth(scoreText)) / 2, y);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            SnakeMove.move(bodyParts, x, y, UNIT_SIZE, direction);
            if (gameFrame.isLevel3) {
                SnakeMove.move(bodyParts2, x2, y2, UNIT_SIZE, direction2);
            }
            try {
                checkApple();
            } catch (UnsupportedAudioFileException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (LineUnavailableException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') { // because the snake can turn only 90deg
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_Q:
                    if (direction2 != 'R') { // because the snake can turn only 90deg
                        direction2 = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                    if (direction2 != 'L') {
                        direction2 = 'R';
                    }
                    break;
                case KeyEvent.VK_Z:
                    if (direction2 != 'D') {
                        direction2 = 'U';
                    }
                    break;
                case KeyEvent.VK_S:
                    if (direction2 != 'U') {
                        direction2 = 'D';
                    }
                    break;
                default:
                    break;
            }
            if (!running && e.getKeyCode() == KeyEvent.VK_ENTER) {
                // Inside the gameOver method
                // if (clip.isRunning()) {
                // clip.stop();
                // }

                // if (clip2.isRunning()) {
                // clip2.stop();
                // }

                // if (clip3.isRunning()) {
                // clip3.stop();
                // }

                gameFrame.returnToMenu(); // Appelle la méthode de GameFrame pour retourner au menu

            }
        }

    }

}
