package Snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 20; // Apples
    public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE; // Numbr of apples in the screen
    public static final int DELAY = 75; // miniteur : délai plus élevé = jeu plus lent

    // the body of the snake
    public final int x[] = new int[GAME_UNITS]; // the size is GAME_UNITS because the snake can't be bigger than the
                                                // nmbr of units
    public final int y[] = new int[GAME_UNITS];
    //

    // The nmbr of snake part in the beginning
    public int bodyParts = 5;
    public int applesEaten = 0;
    //

    // position of apples
    public int appleX;
    public int appleY;
    //

    // Snake direction
    char direction = 'R'; // beging by going right
    //

    public boolean running = false;
    public Timer timer;
    public Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // modif size game panel
        this.setBackground(Color.black); // add a pic later
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newApple(); // create a new apple on screen
        running = true;
        timer = new Timer(DELAY, this); // we use "this" because we are using the ActionListener interface
        timer.start();
    }

    public void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        Drawer.draw(graph, SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE, appleX, appleY, x, y, bodyParts);
    }

    public void newApple() { // add an apple
        // generate a random apple in one of the case (on grid)
        appleX = random.nextInt(1,(int) (SCREEN_WIDTH / UNIT_SIZE)-1) * UNIT_SIZE;
        appleY = random.nextInt(1,(int) (SCREEN_HEIGHT / UNIT_SIZE)-1) * UNIT_SIZE;
        //
    }

    public void checkApple() {
        if((x[0] == appleX)&&(y[0] == appleY) ){
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        // check if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        //
        // check if head collides with borders
        // check if head collides with left border
        if (x[0] <= 0) {
            running = false;
        }
        //
        // check if head collides with right border
        if (x[0] > SCREEN_WIDTH - UNIT_SIZE) { // add UNIT_SIZE*2 to keep the head of the snake on screen  (SCREEN_WIDTH - UNIT_SIZE * 3)
            running = false;
        }
        //
        // check if head collides with up border
        if (y[0] < 0) {
            running = false;
        }
        //
        // check if head collides with down border
        if (y[0] > SCREEN_HEIGHT - UNIT_SIZE) {    //(SCREEN_HEIGHT - UNIT_SIZE * 3)
            running = false;
        }
        //
        //

        // stop timer
        if (!running) {
            timer.stop();
        }
        //

    }

    public void gameOver(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            SnakeMove.move(bodyParts, x, y, UNIT_SIZE, direction);
            checkApple();
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
                default:
                    break;
            }
        }

    }

}
