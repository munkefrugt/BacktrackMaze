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
    // count amount of path build
    int pathBuild;
    int randomDirection;


    ArrayList<ArrayList<String>> maze = new ArrayList<ArrayList<String>>();

    public RecursiveBacktracker()
    {
        /*
        // set size
        System.out.println("enter width");
        int width = input.nextInt();
        System.out.println("your  width is: " + width);

        System.out.println("enter height");
        int height = input.nextInt();
        System.out.println("your  height is: " + height);
        */
        //CHANGE BACK !!!!!!!!!!!!!!!!!!!!!! to:
        //setSize(width,height);
        setSize(30,30);


    }

    // stores the size in global veriables.
    private void setSize(int width, int height)
    {
        this.width=width;
        this.height=height;
        // we add 4 because of the border takes up 4 spaces.
        this.totalWidth=width+4;
        this.totalHeight=height+4;


    }

    // makes a maze.
    public void generateMaze()
    {
        System.out.println(width);
        System.out.println(height);
        /*
        steps:
        1. make a fillid space with a make a barrier and "@"
        */
        System.out.println("fill maze");
        fillMaze();// prints maze.
        System.out.println("make rand start pos");
        makeRandomStartPosition();
        System.out.println("Print start position");
        printMaze();
        System.out.println("find RandDirection  :" + randomDirection);


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

    // 0 = virker helt
    // 1 = virker
    // 2 = no

    // this is the recursive method
    private void ChooseANewRandomDirection()
    {

                //randomDirection = (int)(Math.random() *2 );
                //    randomDirection= 0;

                // UP:

        //while (pathBuild<10)
        //{
                //Try to build path to the UP, if succes take to steps up
                if(randomDirection == 0)
                {
                        System.out.println("up");
                        // NO SUCCES.Its already blank, Don't make path!
                        if (maze.get(currentRowPosition-2).get(currentColumnPosition).equals(" ")||
                                maze.get(currentRowPosition-2).get(currentColumnPosition).equals("#")) {
                            // try an other direction
                            System.out.println("cant take this way up!");

                            //ChooseANewRandomDirection();

                        }

                        // SUCCES! Take to steps UP. Make a path!
                        else {
                            // make path
                            // -2 is because we go up so we have to use an "earlier"creaated aray
                            // clear path 1. step
                            maze.get(currentRowPosition-1).set(currentColumnPosition," ");

                            // clear path 2. step
                            maze.get(currentRowPosition-2).set(currentColumnPosition," ");
                            System.out.println("make path up to row , column"+ (currentRowPosition-3)+","+(currentColumnPosition-1) );

                            // set new position:
                            // we move 2 steps up to our new position.
                            currentRowPosition=currentRowPosition-2;
                            System.out.println("currentColumnPosition "+currentColumnPosition + " should be as original");
                            // Column position is not changed!
                            //test
                            //randomDirection=3;  //left
                            pathBuild++;
                            //ChooseANewRandomDirection();
                        }



                }

                // DOWN
                //Try to build path to the DOWN
                else if(randomDirection == 1)
                {
                    System.out.println("down");

                    // NO SUCCES.Its already blank, Don't make path!
                    if (maze.get(currentRowPosition+2).get(currentColumnPosition).equals(" ")||
                            maze.get(currentRowPosition+2).get(currentColumnPosition).equals("#")) {
                        // try an other direction
                        System.out.println("cant take this way down!");

                        // test:

                        //ChooseANewRandomDirection();

                    }

                    // SUCCES! Take to steps DOWN. Make a path!
                    else {
                        // make path
                        // +,1+2 is because we go up so we have to use an "earlier"creaated row
                        // clear path 1. step
                        maze.get(currentRowPosition+1).set(currentColumnPosition," ");

                        // clear path 2. step
                        maze.get(currentRowPosition+2).set(currentColumnPosition," ");
                        // +3 and +1 is just to get the names right.
                        System.out.println("make path DOWN to : roww,column("+ (currentRowPosition+3)+","+(currentColumnPosition+1)+")" );

                        // set new position:
                        // we move 2 steps up to our new position.
                        // Notice only currentColumnPosition is changed
                        currentRowPosition=currentRowPosition+2;
                        System.out.println("currentColumnPosition :"+currentColumnPosition +"currentRowPosition :" + currentRowPosition);
                        // Row position should not be changed!
                        pathBuild++;
                        //ChooseANewRandomDirection();
                    }
                }

                // LEFT:
                //Try to build path to the LEFT
                else if(randomDirection == 2)
                {
                    System.out.println("left");

                    // NO SUCCES.Its already blank or theres the board ends, Don't make path!
                    if (maze.get(currentRowPosition).get(currentColumnPosition-2).equals(" ")||
                            maze.get(currentRowPosition).get(currentColumnPosition-2).equals("#"))
                    {
                        // try an other direction
                        System.out.println("cant take this way LEFT!");

                        //ChooseANewRandomDirection();

                    }

                    // Make a path!
                    else {
                        // make path
                        // -2 is because we go up so we have to use an "earlier"creaated aray
                        // clear path 1. step
                        maze.get(currentRowPosition).set(currentColumnPosition-1," ");

                        // clear path 2. step
                        maze.get(currentRowPosition).set(currentColumnPosition-2," ");
                        System.out.println("make path LEFT to : ("+ (currentRowPosition-2)+","+(currentColumnPosition-1)+")" );

                        // set new position:
                        // we move 2 steps up to our new position.
                        // row position should not be changed! only currentColumnPosition
                        currentColumnPosition=currentColumnPosition-2;
                        System.out.println("currentColumnPosition :"+currentColumnPosition +"currentRowPosition :" +currentRowPosition);

                        pathBuild++;
                        //ChooseANewRandomDirection();
                    }
                }

                // RIGHT


                else if(randomDirection == 3)
                {
                    System.out.println("right");
                    // NO SUCCES.Its already blank or theres the board ends, Don't make path!
                    if (maze.get(currentRowPosition).get(currentColumnPosition+2).equals(" ")||
                            maze.get(currentRowPosition).get(currentColumnPosition+2).equals("#"))
                    {
                        // try an other direction
                        System.out.println("cant take this way LEFT!");

                        //ChooseANewRandomDirection();

                    }

                    // Make a path!
                    else {
                        // make path
                        // +2 is because we go 2 to the right so we have to use an the same row
                        // clear path 1. step
                        maze.get(currentRowPosition).set(currentColumnPosition+1," ");

                        // clear path 2. step
                        maze.get(currentRowPosition).set(currentColumnPosition+2," ");
                        System.out.println("make path RIGHT to : ("+ (currentRowPosition+2)+","+(currentColumnPosition+1)+")" );

                        // set new position:
                        // we move 2 steps up to our new position.
                        // Column position should not be changed!
                        currentColumnPosition=currentColumnPosition+2;
                        System.out.println("currentColumnPosition :"+currentColumnPosition +"currentRowPosition :" +currentRowPosition);

                        pathBuild++;
                        //ChooseANewRandomDirection();
                    }

                }

                else
                {
                    System.out.println("error!");
                }

        //}
        printMaze();
        System.out.println("previus rand direction " + randomDirection );
        System.out.println("enter randomDirection");
        randomDirection = input.nextInt();

        System.out.println("you entered New random direction : " + randomDirection);

        ChooseANewRandomDirection();

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
         startRow=15;
        startColumn=15;

        maze.get(startRow).set(startColumn," ");
        // husk
        System.out.println("start position: ("+(startRow-1)+","+(startColumn-1)+")");
        currentRowPosition=startRow;
        currentColumnPosition=startColumn;
        System.out.println("currentRowPosition  "+currentRowPosition);
        System.out.println("currentColumnPosition  "+currentColumnPosition);



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
