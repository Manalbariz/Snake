package Snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Drawer {
    public static void draw(Graphics graph, int SCREEN_WIDTH, int SCREEN_HEIGHT, int UNIT_SIZE, int appleX, int appleY,
            int[] x, int[] y, int bodyParts) {
        // turn into grid
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            graph.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            graph.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
        //

        // draw the apple
        graph.setColor(Color.BLUE);
        graph.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

        //

        // draw the snake
        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) { // snake head
                graph.setColor(Color.WHITE);
                graph.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                graph.setColor(Color.gray);
                graph.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

        }
        //
        // draw the wall
        for (int i = 0; i < SCREEN_WIDTH; i++) {
            graph.setColor(new Color(83, 179, 203));
            graph.fillRect(i, 0, UNIT_SIZE, UNIT_SIZE);
            graph.fillRect(i, (SCREEN_HEIGHT - UNIT_SIZE), UNIT_SIZE, UNIT_SIZE);
            graph.fillRect(0, i, UNIT_SIZE, UNIT_SIZE);
            graph.fillRect((SCREEN_WIDTH - UNIT_SIZE), i, UNIT_SIZE, UNIT_SIZE);

        }
    }
}
