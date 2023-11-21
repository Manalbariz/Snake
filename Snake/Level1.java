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

public class Level1 extends  Levels   {

    Level1(GameFrame gameFrame) {
        super(gameFrame, gamePanel);
        
    }
   
    @Override
    public void Img() {
        // read img
        try {
            level = ImageIO.read(new File(
                    ".\\T-JAV-501-MPL_5\\Snake\\ressources\\level1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
}
