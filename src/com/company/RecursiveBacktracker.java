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
    int currentRowPosition;
    int currentColumnPosition;


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


        setSize(width,height);

    }

    // stores the size in global veriables.
    private void setSize(int width, int height)
    {
        this.width=width;
        this.height=height;
        // we add 4 because of the border takes up 4 spaces.
        this.totalWidth=width+4;
        this.totalHeight=height+4;

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
        makeRandomStartPosition();
        ChooseANewRandomDirection();//
        printMaze();
        //rebuild();
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

    private void ChooseANewRandomDirection()
    {

            int randomDirection = (int)(Math.random() *4 );
                randomDirection= 0;
            if(randomDirection == 0)
            {
                    System.out.println("up");
                    // if its already blank
                    if (maze.get(currentRowPosition-2).get(currentColumnPosition).equals(" ")||
                            maze.get(currentRowPosition-2).get(currentColumnPosition).equals("#")) {
                        // try an other direction
                        ChooseANewRandomDirection();
                    }

                    // there is a wall to smash
                    else {
                        // make path
                        // -2 is because we go up so we have to use an "earlier"creaated aray
                        maze.get(currentRowPosition-2).set(currentColumnPosition," ");
                        System.out.println("make path up to"+ (currentRowPosition-2)+","+(currentColumnPosition-1) );
                    }



            }
            else if(randomDirection == 1)
            {
                System.out.println("down");
            }
            else if(randomDirection == 2)
            {
                System.out.println("left");
            }
            else if(randomDirection == 3)
            {
                System.out.println("right");
            }
            else
                System.out.println("error!");





    }

    private void rebuild()
    {

            System.out.println("rebuild a new one? y? n? ");



            System.out.println("enter answer");
            String answer = input.nextLine();
            System.out.println("your  answer is: " + answer);

            try
            {
                if(answer.equals("y"))
                {
                    generateMaze();
                    rebuild();
                }
                else if(answer.equals("n"))
                {
                    System.out.println(" now exit");
                }
                else
                {
                    System.out.println("try again, only  small letters!");
                    rebuild();
                    System.out.println("no rebuild generation happened");
                }

            }
            catch (IndexOutOfBoundsException e)
            {
                System.err.println("IndexOutOfBoundsException: " + e.getMessage());
                rebuild();
            }


    }

    private void makeRandomStartPosition()
    {

        // if startRow=2 and startColumn=2 its like 1,1 like a coordinate system that starts in the left corner.
        // makes a number 0-height or width (it also makes 0)
        int startRow = (int)(Math.random() *height )+2; // the higher the number the further down the board you go
        int startColumn = (int)(Math.random() *width )+2;// the higher the number the further right the board you go

        // vil være første koordinatet:
        // startRow=2;
        //startColumn=2;

        maze.get(startRow).set(startColumn," ");
        // husk
        System.out.println("start position: ("+(startRow-1)+","+(startColumn-1)+")");
        currentRowPosition=startRow;
        currentColumnPosition=startColumn;



    }

    private void printMaze()
    {


        for (int j = 0; j <totalHeight ; j++)
        {
            System.out.print(j+"\t");
            for (int i = 0; i <totalWidth ; i++)
            {

                System.out.print(maze.get(j).get(i));

            }
            System.out.println();
        }
        System.out.println();

    }

    public void fillMaze()
    {

        ArrayList<String> border = new ArrayList<String>();
        for (int j = 0; j <totalWidth ; j++)
        {
            border.add("#");

        }
        maze.add(border);
        maze.add(border);

        for (int i = 0; i < height ; i++)
        {


            ArrayList<String> list = new ArrayList<String>();
            list.add("#");
            list.add("#");
            for (int j = 0; j <width ; j++)
            {
                list.add("@");

            }
            list.add("#");
            list.add("#");
            maze.add(list);
        }

        maze.add(border);
        maze.add(border);
    }

}
