package com.company;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by v on 4/4/16.
 */
public class Gui extends JFrame {

    public Gui(ArrayList<ArrayList<String>> maze, int totalHeight, int totalWidth)
    {
        JFrame frame = new JFrame("BacktrackerLabyrinth");
        Draw draw = new Draw(maze,totalHeight,totalWidth);
        // add the drawing to the frame.
        frame.add(draw);


        //frame.add(keyboardExample);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(800, 600);
        //draw.drawing();
    }




}
