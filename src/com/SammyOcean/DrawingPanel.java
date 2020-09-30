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
            Graphics2D graphics2D = (Graphics2D) g;

            int red = (int) (Math.random() * 250);
            int green = (int) (Math.random() * 250);
            int blue = (int) (Math.random() * 250);

            graphics2D.setColor(new Color(red, green, blue));

            int height = (int) ((Math.random() * 120) + 10);
            int width = (int) ((Math.random() * 120) + 10);
            int x = (int) ((Math.random() * 40) + 10);
            int y = (int) ((Math.random() * 40) + 10);

            graphics2D.fill3DRect(x, y, width, height, true);

            message = false;
        }
    }
}
