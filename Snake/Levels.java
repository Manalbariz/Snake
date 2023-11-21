package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class Levels extends JPanel implements KeyListener {
    protected GameFrame gameFrame;
    protected static GamePanel gamePanel;

    protected BufferedImage level;

    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 700;

    Levels(GameFrame gameFrame, GamePanel gamePanel) {
        this.gameFrame = gameFrame;
        this.gamePanel = gamePanel;
        Img();
        this.setBackground(Color.black);
        addKeyListener(this);

    }

    public void Img() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw img
        Image scaledLevel = level.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_DEFAULT);
        g.drawImage(scaledLevel, 0, 0, null);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
            if (key == KeyEvent.VK_ENTER) {
                if(gamePanel.bodyParts != 15){
                    gameFrame.startGame();
                }else if(gamePanel.bodyParts == 15){
                    gameFrame.startGame();
                    gameFrame.Level2();
                }
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
