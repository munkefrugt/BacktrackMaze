package com.company;

import javafx.scene.shape.Ellipse;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

/**
 * Created by v on 4/4/16.
 *
 * inspired by : https://www.youtube.com/watch?v=p9Y-NBg8eto
 */
// extends Jpanel, because we draw on the Jpanel, not on the frame.
public class Draw extends JPanel implements ActionListener,KeyListener
{
    ArrayList<ArrayList<String>> maze;
    // speed of the repaint
    Timer t = new Timer(10,this);
    int x= 0, y=0 , velx = 0, vely = 0;
    private int totalHeight;
    private int totalWidth;

    public Draw(ArrayList<ArrayList<String>> maze, int totalHeight, int totalWidth)
    {
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



    public void drawing()
    {
        // repaint() calls the paintComponent(Graphics g) methode.

        repaint();
    }


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
                    g.fillRect(i * 10, j * 10, 10, 10);
                }
                else if(maze.get(j).get(i).equals("+"))
                {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(i * 10, j * 10, 10, 10);
                }
                else if (maze.get(j).get(i).equals("M"))
                {
                    g.setColor(Color.ORANGE);
                    g.fillRect(i * 10, j * 10, 10, 10);
                }
                else if (maze.get(j).get(i).equals("¤"))
                {
                    g.setColor(Color.BLACK);
                    g.fillRect(i * 10, j * 10, 10, 10);
                }


            }
            //System.out.println();
        }

        /*for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 6; j++) {

                if (maze.get(i).get(j).equals(" "))
                {
                    g.setColor(Color.PINK);
                    g.fillRect(i * 10, j * 10, 10, 10);
                }
                else if (maze.get(i).get(j).equals("+")) ;
                {
                    g.setColor(Color.WHITE);
                    g.fillRect(i * 10, j * 10, 10, 10);
                }
            }
        }*/
        //System.out.println(maze.get(1).get(1));

        //Graphics2D g2 = (Graphics2D) g;
        //g2.fillOval(x, 40,y,  40);
                //(new Ellipse2D.Double(x,y,40,40));
        // int x, int y, int width, int height.
        g.fillRect(x,y,20,20);
        //g.fillRect(20,20,20,20);
        /*g.setColor(Color.ORANGE);
        g.fillRect(40,40,20,20);
        g.setColor(Color.CYAN);
        g.fillOval(60,60,20,20);
        */
    }

    public void actionPerformed(ActionEvent e)
    {
        //System.out.println("repaint");
        // paint again
        repaint();

        // change koordinates.
        x+= velx;
        y+= vely;

    }

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
        }
        if(code == KeyEvent.VK_DOWN)
        {
            down();
        }
        if(code == KeyEvent.VK_LEFT)
        {
            left();
        }
        if(code == KeyEvent.VK_RIGHT)
        {
            right();
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
