package com.company;

import javax.swing.*;

/**
 * Created by v on 4/4/16.
 */
public class Gui extends JFrame {

    public Gui()
    {
        JFrame frame = new JFrame("BacktrackerLabyrinth");
        //KeyboardExample keyboardExample = new KeyboardExample();
        //frame.add(keyboardExample);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Draw draw = new Draw();
        // add the drawing to the frame.
        frame.add(draw);

        draw.drawing();
    }




}
