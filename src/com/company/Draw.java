package com.company;

import javax.swing.*;

import java.awt.*;
/**
 * Created by v on 4/4/16.
 */
// extends Jpanel, because we draw on the Jpanel, not on the frame.
public class Draw extends JPanel
{

    public void drawing()
    {
        // repaint() calls the paintComponent(Graphics g) methode.
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        // int x, int y, int width, int height.
        g.fillRect(0,0,20,20);
        g.fillRect(20,20,20,20);
        g.setColor(Color.ORANGE);
        g.fillRect(40,40,20,20);
        g.setColor(Color.CYAN);
        g.fillOval(60,60,20,20);

        

    }
}
