package Snake;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.Random;

public class Drawer {

    private static Image lapin;
    public static Image headSnake;
    public static Image wall1;




    public static void draw(Graphics graph, int SCREEN_WIDTH, int SCREEN_HEIGHT, int UNIT_SIZE, int appleX, int appleY,
            int[] x, int[] y, int bodyParts) {
        // // turn into grid
        // for (int i = 0; i < SCREEN_HEIGHT * 2 / UNIT_SIZE; i++) {
        //     graph.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
        //     graph.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        // }
        // //

        // read img
        switch (GamePanel.direction) {
            case 'U':
                try {
                    Drawer.headSnake = ImageIO.read(new File(
                            ".\\T-JAV-501-MPL_5\\Snake\\ressources\\HeadSnake2.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case 'D':
                try {
                    headSnake = ImageIO.read(new File(
                            ".\\T-JAV-501-MPL_5\\Snake\\ressources\\HeadSnake3.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 'L':
                try {
                    headSnake = ImageIO.read(new File(
                            ".\\T-JAV-501-MPL_5\\Snake\\ressources\\HeadSnake4.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 'R':
                try {
                    headSnake = ImageIO.read(new File(
                            ".\\T-JAV-501-MPL_5\\Snake\\ressources\\HeadSnake1.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        try {
            lapin = ImageIO.read(new File(
                    ".\\T-JAV-501-MPL_5\\Snake\\ressources\\Lapin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // draw the apple
        // graph.setColor(Color.BLUE);
        // graph.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
        Image scaledlapin = lapin.getScaledInstance(UNIT_SIZE, UNIT_SIZE, Image.SCALE_DEFAULT);
        graph.drawImage(scaledlapin, appleX, appleY, null);

        //

        // draw the snake
        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) { // snake head
                graph.setColor(Color.WHITE);
                Image scaledheadSnake = headSnake.getScaledInstance(UNIT_SIZE, UNIT_SIZE, Image.SCALE_DEFAULT);
                graph.drawImage(scaledheadSnake, x[i], y[i], null);
                // graph.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                graph.setColor(new Color(128, 64, 8));
                graph.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

        }
        //
        // draw the wall
        try {
            wall1 = ImageIO.read(new File(
                    ".\\T-JAV-501-MPL_5\\Snake\\ressources\\wall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledwall1 = wall1.getScaledInstance(UNIT_SIZE, UNIT_SIZE, Image.SCALE_DEFAULT);
        

        for (int j=0 ; j < SCREEN_WIDTH*UNIT_SIZE ; j++) {
            // graph.setColor(new Color(83, 179, 203));
            
            // graph.drawImage(scaledwall1, i, 0, null);
            graph.drawImage(scaledwall1, j, 0, UNIT_SIZE, UNIT_SIZE, null);
            graph.drawImage(scaledwall1, j, (SCREEN_HEIGHT - UNIT_SIZE), UNIT_SIZE, UNIT_SIZE, null);
            graph.drawImage(scaledwall1, 0, j, UNIT_SIZE, UNIT_SIZE, null);
            graph.drawImage(scaledwall1, (SCREEN_WIDTH - UNIT_SIZE), j, UNIT_SIZE, UNIT_SIZE, null);

            
            
            // graph.fillRect(j, 0, UNIT_SIZE, UNIT_SIZE);
            // graph.fillRect(j, (SCREEN_HEIGHT - UNIT_SIZE), UNIT_SIZE, UNIT_SIZE);
            // graph.fillRect(0, i, UNIT_SIZE, UNIT_SIZE);
            // graph.fillRect((SCREEN_WIDTH - UNIT_SIZE), i, UNIT_SIZE, UNIT_SIZE);

            j += 19 ;

        }
    }
}
