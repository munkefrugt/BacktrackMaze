package com.company;

import javafx.scene.shape.Ellipse;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

/**
 * Created by v on 4/4/16.
 *
 * inspired by : https://www.youtube.com/watch?v=p9Y-NBg8eto
 */
// extends Jpanel, because we draw on the Jpanel, not on the frame.
public class Draw extends JPanel implements ActionListener,KeyListener
{
    Minotaur minotaur;
    int minotaurrow;//x
    int minotaurColumn;// y

    ArrayList<ArrayList<String>> maze;
    // speed of the repaint
    Timer t = new Timer(10,this);
    //velx , stands for velocity in x direction ect.
    int x, y , velx = 0, vely = 0;
    int totalHeight;
    int totalWidth;

    public Draw(Minotaur minotaur, ArrayList<ArrayList<String>> maze, int totalHeight, int totalWidth, int startMinotaurRow, int startMinotaurColumn)
    {   this.minotaur= minotaur;
        this.minotaurrow=startMinotaurRow;
        this.minotaurColumn=startMinotaurColumn;
        this.totalWidth=totalWidth;
        this.totalHeight=totalHeight;
        this.maze = maze;
        t.start();
        addKeyListener(this);
        // allows us to use the keyListener.
        setFocusable(true);
        // make other keys act normal. like the tab key.
        setFocusTraversalKeysEnabled(false);
    }



    /*public void drawing()
    {
        // repaint() calls the paintComponent(Graphics g) methode.

        repaint();
    }*/


    // allows us to draw graphics on the screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        //print the maze:

        for (int j = 0; j <totalHeight ; j++)
        {

            for (int i = 0; i <totalWidth ; i++)
            {
                if(maze.get(j).get(i).equals(" "))
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(i * 20, j * 20, 20, 20);
                }
                else if(maze.get(j).get(i).equals("+"))
                {
                    g.setColor(Color.GREEN);
                    g.fillRect(i * 20, j * 20, 20, 20);
                }
                else if (maze.get(j).get(i).equals("M"))
                {
                    g.setColor(Color.ORANGE);
                    g.fillOval(i * 20, j * 20, 20, 20);
                }
                else if (maze.get(j).get(i).equals("¤"))
                {
                    g.setColor(Color.BLACK);
                    g.fillRect(i * 20, j * 20, 20, 20);
                }
                else if (maze.get(j).get(i).equals("@"))
                {
                    g.setColor(Color.BLUE);
                    g.fillRect(i * 20, j * 20, 20, 20);
                }
                else if (maze.get(j).get(i).equals("S"))
                {
                    g.setColor(Color.MAGENTA);
                    g.fillRect(i * 20, j * 20, 20, 20);
                }
                else if (maze.get(j).get(i).equals("F"))
                {
                    g.setColor(Color.RED);
                    g.fillRect(i * 20, j * 20, 20, 20);
                }



            }
        }



    }

    public void actionPerformed(ActionEvent e)
    {
        //System.out.println("repaint");
        // paint again
        repaint();

        // change koordinates.
        minotaurrow+= velx;
        minotaurColumn+= vely;

    }
    // move the minotaur.
    public void up()
    {

        vely= -1;
        velx= 0;
        System.out.println("up");
        repaint();
    }

    public void down()
    {
        vely= 1;
        velx= 0;
        System.out.println("down");
        repaint();
    }

    public void left()
    {
        vely= 0;
        velx= -1;
        System.out.println("left");
        repaint();
    }

    public void right()
    {
        vely= 0;
        velx= 1;
        System.out.println("right");
        repaint();

    }


        // inspired by : https://www.youtube.com/watch?v=p9Y-NBg8eto

    @Override
    public void keyPressed(KeyEvent e)
    {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP)
        {
            up();
            minotaur.minotaurUp();

        }
        if(code == KeyEvent.VK_DOWN)
        {
            minotaur.minotaurDown();
            down();
        }
        if(code == KeyEvent.VK_LEFT)
        {
            left();
            minotaur.minotaurLeft();
        }
        if(code == KeyEvent.VK_RIGHT)
        {
            right();
            minotaur.minotaurRight();

        }
    }
    // the two methodes below needs to be included because we implemented Keylistener.
    // but can stay empty, since we dont use them.
    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
