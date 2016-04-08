package com.company;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Created by v on 4/4/16.
 */
public class Gui extends JFrame {

    private final JFrame mazeFrame;
    Minotaur minotaur;
    RecursiveBacktracker recursiveBacktracker;


    public Gui(RecursiveBacktracker recursiveBacktracker,Minotaur minotaur, ArrayList<ArrayList<String>> maze, int totalHeight, int totalWidth, int startMinotaurRow, int startMinotaurColumn)
    {
        this.minotaur=minotaur;
        this.recursiveBacktracker = recursiveBacktracker;



        //this.recursiveBacktracker=recursiveBacktracker;
        mazeFrame = new JFrame("BacktrackerLabyrinth");

        setLocationRelativeTo(null);
        Draw draw = new Draw(minotaur,maze,totalHeight,totalWidth,startMinotaurRow,startMinotaurColumn);
        // add the drawing to the mazeFrame.
        mazeFrame.add(draw);

        //mazeFrame.add(keyboardExample);
        mazeFrame.setVisible(true);
        mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //center mazeFrame
        setLocationRelativeTo(null);
        mazeFrame.setSize(totalWidth*20, totalHeight*20);
        setLocationRelativeTo(null);
    }

    public void createFrame()
    {

    }

    public void makeWinnerFrame()
    {
        // make a dialog. for rebuilding the maze
        // inspired by : http://stackoverflow.com/questions/8396870/joptionpane-yes-or-no-window
        //date 8/6/2016
        // null means nothing i think. but it works, it can replace any data type.. maybe?

        int reply = JOptionPane.showConfirmDialog(null, "You won. make new labyrinth?","Question", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,"Yes, rebuild");
            //mazeFrame.dispatchEvent(new WindowEvent(mazeFrame, WindowEvent.WINDOW_CLOSING));
            mazeFrame.setVisible(false); //you can't see me!
            mazeFrame.dispose(); //Destroy the JFrame object
            System.out.println("new");






        }
        else {
            JOptionPane.showMessageDialog(null, "GOODBYE");
            System.exit(0);
        }
    }

    public void passInstance(RecursiveBacktracker recursiveBacktracker) {
        this.recursiveBacktracker=recursiveBacktracker;
    }

}
