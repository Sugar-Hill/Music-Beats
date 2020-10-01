package com.SammyOcean;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel implements ControllerEventListener {

    boolean message = false;

    public void controlChange(ShortMessage event) {
        message = true;
        repaint();
    }

    public void paintComponent(Graphics g) {
        if (message) {
            int numberOfShapes = (int) (Math.random() * 20);

            for (int i = 3; i < numberOfShapes; i++) {
                Graphics2D graphics2D = (Graphics2D) g;

                int red = (int) (Math.random() * 250);
                int green = (int) (Math.random() * 250);
                int blue = (int) (Math.random() * 250);

                graphics2D.setColor(new Color(red, green, blue));

                Color startColor = new Color(red, green, blue);

                red = (int) (Math.random() * 250);
                green = (int) (Math.random() * 250);
                blue = (int) (Math.random() * 250);

                int height = (int) ((Math.random() * 120) + 10);
                int width = (int) ((Math.random() * 120) + 10);

                int x = (int) ((Math.random() * 150) + 10);
                int y = (int) ((Math.random() * 150) + 10);
                int x1 = (int) ((Math.random() * 150) + 10);
                int y1 = (int) ((Math.random() * 150) + 10);

                Color endColor = new Color(red, green, blue);

                GradientPaint gradient = new GradientPaint(x, y, startColor, x1, y1, endColor);
                graphics2D.setPaint(gradient);
                graphics2D.fillRect(x,y,width,height);
            }
            message = false;
        }
    }
}
