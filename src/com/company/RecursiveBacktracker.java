package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by v on 3/4/16.
 */
public class RecursiveBacktracker
{
    Scanner input = new Scanner(System.in);
    int totalWidth;
    int totalHeight;
    int width;
    int height;
    ArrayList<ArrayList<String>> maze = new ArrayList<ArrayList<String>>();

    public RecursiveBacktracker()
    {
        // set size
        System.out.println("enter width");
        int width = input.nextInt();
        System.out.println("your  width is: " + width);

        System.out.println("enter height");
        int height = input.nextInt();
        System.out.println("your  height is: " + height);
        setSize(5,6);

    }

    // stores the size in global veriables.
    private void setSize(int width, int height)
    {
        this.width=width;
        this.height=height;
        System.out.println(width+height);
    }

    public void generateMaze()
    {
        System.out.println(width);
        System.out.println(height);
        // fill a arraylist. with "W"  (wall)

        /*
        steps:
        1. make a fillid space with a make a barrier
        */
        fillMaze();
        printMaze();

        /*

            fill out all the space with "@"
        2. make a random starting point. make it the position.
        3. ChooseANewRandomDirection() //chose a random direction, up, down, left, right . make a direction selector.
            (1= up, 2 = down, 3 = left, 4= right)
            if (randomdirection == 1) //up

                check if the index of that row to step higher up is blank (" ")

                    // if ALL READY EMPTY choose new direction
                    if (mazeList.get(row).get(column).equals(" ")||mazeList.get(row).get(column).equals("*") )//
                        if the position is blank then
                        // go back to ChooseANewRandomDirection()
                        // note that the direction has been tried.
                    else // CARVE
                        carveAPath() // replace the 2 elements to reach the next position.
                        and save the new position in "current position"



                do the same i 4 cases in total.


            BackTrack()






         */
        // carvePath() out


        // checkIfcarvedAround() if all wall in 2 blocks radius is carved.
            // if all carved. go back one step and check again
            // if not carved around -  carvePath()


        // go back one step






    }

    private void printMaze()
    {


        for (int j = 0; j <16 ; j++)
        {

            for (int i = 0; i <28 ; i++)
            {

                System.out.print(maze.get(j).get(i));

            }
            System.out.println();
        }
        System.out.println();

    }

    public void fillMaze()
    {

        for (int i = 0; i < 16 ; i++)
        {


            ArrayList<String> list = new ArrayList<String>();
            list.add("#");
            list.add("#");
            for (int j = 0; j <24 ; j++)
            {
                list.add("@");

            }
            list.add("#");
            list.add("#");
            maze.add(list);
        }
    }

}
