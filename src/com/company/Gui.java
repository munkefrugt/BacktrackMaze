package com.company;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by v on 4/4/16.
 */
public class Gui extends JFrame {

    public Gui(Minotaur minotaur, ArrayList<ArrayList<String>> maze, int totalHeight, int totalWidth, int startMinotaurRow, int startMinotaurColumn)
    {
        JFrame frame = new JFrame("BacktrackerLabyrinth");

        Draw draw = new Draw(minotaur,maze,totalHeight,totalWidth,startMinotaurRow,startMinotaurColumn);
        // add the drawing to the frame.
        frame.add(draw);


        //frame.add(keyboardExample);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(totalWidth*20, totalHeight*20);
        //draw.drawing();
    }




}
