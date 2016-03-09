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
    // this array changes if therehas been made a try to go down a certain path. 0->1
    // this will start backtraking is they are all one
    int failedPathDirections[] = {0,0,0,0,};
    int[] triedBackTrackingArray ={0,0,0,0};

    // used by the backtracker.
    int previousRowPosition;
    int previousColumnPosition;


    // have to have a start value that is not 0,1,2 0r 3. otherwise a direction will be blocked.
    int blockedDirection=-1;


    // if it reaches a deadend make this true
    boolean hasReachedDeadEnd;
    int BackTrackTestCount;

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
        //printMaze();
        System.out.println("find RandDirection  :" + randomDirection);


        ChoosePathAndBuild();// chose a path randomly and try to make a path.

        System.out.println("END*******************************************************************************");

        printMaze();

        //rebuild();
        /*

            fill out all the space with "@"
        2. make a random starting point. make it the position.
        3. ChoosePathAndBuild() //chose a random direction, up, down, left, right . make a direction selector.
            (1= up, 2 = down, 3 = left, 4= right)
            if (randomdirection == 1) //up

                check if the index of that row to step higher up is blank (" ")

                    // if ALL READY EMPTY choose new direction
                    if (mazeList.get(row).get(column).equals(" ")||mazeList.get(row).get(column).equals("*") )//
                        if the position is blank then
                        // go back to ChoosePathAndBuild()
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
    private void ChoosePathAndBuild()
    {

                //randomDirection = (int)(Math.random() *4 );
                    //randomDirection=0;

                // UP:
        // sæt den ikke for højt, 70 begynder at blive svært..
        while (pathBuild<80)
        {
            printMaze();
            // TEST START
            System.out.println("amount pathbuild  :"+pathBuild);
            System.out.println("previus rand direction " + randomDirection );
            System.out.println("enter randomDirection");
            randomDirection = input.nextInt();

            System.out.println("you entered New random direction : " + randomDirection);

            // TEST END





                //Try to build path to the UP, if succes take to steps up
                if(randomDirection == 0)
                {
                        System.out.println("up");

                        // NO SUCCES.Its already blank, Don't make path! or its the way you came from when you are backtracking
                        if (maze.get(currentRowPosition-2).get(currentColumnPosition).equals(" ")||
                                maze.get(currentRowPosition-2).get(currentColumnPosition).equals("#"))
                        {
                            // try an other direction
                            System.out.println("cant take this way up!");

                            //if all directions have been tried. start BACK TRACKING!
                            if(failedPathDirections[0]==1 && failedPathDirections[1]==1 && failedPathDirections[2]==1 && failedPathDirections[3]==1)
                            {
                                System.out.println("all directions have failed. start backtracking!");

                                //printMaze();

                                //reset failedPathDirections array,
                                // because it has to be reset for next time we need try to build a path.
                                resetFailedPathDirectionsArray();
                                // activate backtracking
                                backtracking();

                            }
                            else
                            {
                            System.out.println("try an other direction");
                            // tell the "failedPathDirections" array that the path couldent be made.
                            failedPathDirections[0]=1;
                            ChoosePathAndBuild();
                            }

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

                            // save the position we just came from. will be used in back tracking:
                            // Set  previous position to current:
                            previousRowPosition = currentRowPosition;
                            previousColumnPosition= currentColumnPosition;

                            // set new position:
                            // we move 2 steps up to our new position.
                            currentRowPosition=currentRowPosition-2;
                            System.out.println("currentColumnPosition "+currentColumnPosition + " should be as original");
                            // Column position is not changed!
                            //test
                            //randomDirection=3;  //left
                            pathBuild++;
                            //reset array "failedPathDirections" array set all to 0
                            resetFailedPathDirectionsArray();

                            ChoosePathAndBuild();
                        }



                }

                // DOWN
                //Try to build path to the DOWN
                else if(randomDirection == 1)
                {
                    System.out.println("down");

                    // NO SUCCES.Its already blank, Don't make path!
                    if (maze.get(currentRowPosition+2).get(currentColumnPosition).equals(" ")||
                            maze.get(currentRowPosition+2).get(currentColumnPosition).equals("#"))
                    {
                        // try an other direction
                        System.out.println("cant take this way down!");
                        //if all directions have been tried. start BACK TRACKING!
                        if(failedPathDirections[0]==1 && failedPathDirections[1]==1 && failedPathDirections[2]==1 && failedPathDirections[3]==1)
                        {
                            System.out.println("all directions have failed. start backtracking!");
                            //printMaze();
                            //reset failedPathDirections array,
                            // because it has to be reset for next time we need try to build a path.
                            resetFailedPathDirectionsArray();
                            // activate backtracking
                            backtracking();

                        }
                        else
                        {
                            System.out.println("try an other direction");
                            // tell the "failedPathDirections" array that the path couldent be made.
                            failedPathDirections[1]=1;
                            ChoosePathAndBuild();
                        }

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

                        // save the position we just came from. will be used in back tracking:
                        // Set  previous position to current:
                        previousRowPosition = currentRowPosition;
                        previousColumnPosition= currentColumnPosition;


                        // set new position:
                        // we move 2 steps up to our new position.
                        // Notice only currentColumnPosition is changed
                        currentRowPosition=currentRowPosition+2;
                        System.out.println("currentColumnPosition :"+currentColumnPosition +"currentRowPosition :" + currentRowPosition);
                        // Row position should not be changed!
                        pathBuild++;
                        resetFailedPathDirectionsArray();
                        ChoosePathAndBuild();
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
                        //if all directions have been tried. start BACK TRACKING!
                        if(failedPathDirections[0]==1 && failedPathDirections[1]==1 && failedPathDirections[2]==1 && failedPathDirections[3]==1)
                        {
                            System.out.println("all directions have failed. start backtracking!");

                            //printMaze();
                            //reset failedPathDirections array,
                            // because it has to be reset for next time we need try to build a path.
                            resetFailedPathDirectionsArray();
                            // activate backtracking
                            backtracking();

                        }
                        else
                        {
                            System.out.println("try an other direction");
                            // tell the "failedPathDirections" array that the path couldent be made.
                            failedPathDirections[2]=1;
                            ChoosePathAndBuild();
                        }

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

                        // save the position we just came from. will be used in back tracking: or is it?????????
                        // Set  previous position to current:
                        previousRowPosition = currentRowPosition;
                        previousColumnPosition= currentColumnPosition;


                        // set new position:
                        // we move 2 steps up to our new position.
                        // row position should not be changed! only currentColumnPosition
                        currentColumnPosition=currentColumnPosition-2;
                        System.out.println("currentColumnPosition :"+currentColumnPosition +"currentRowPosition :" +currentRowPosition);

                        pathBuild++;
                        resetFailedPathDirectionsArray();
                        ChoosePathAndBuild();
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
                        System.out.println("cant take this way right!");
                        //if all directions have been tried. start BACK TRACKING!
                        if(failedPathDirections[0]==1 && failedPathDirections[1]==1 && failedPathDirections[2]==1 && failedPathDirections[3]==1)
                        {
                            System.out.println("all directions have failed. start backtracking!");

                            //printMaze();
                            //reset failedPathDirections array,
                            // because it has to be reset for next time we need try to build a path.
                            resetFailedPathDirectionsArray();
                            // activate backtracking
                            backtracking();

                        }
                        else
                        {
                            System.out.println("try an other direction");
                            // tell the "failedPathDirections" array that the path couldent be made.
                            failedPathDirections[3]=1;
                            ChoosePathAndBuild();
                        }

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

                        // save the position we just came from. will be used in back tracking:
                        // Set  previous position to current:
                        previousRowPosition = currentRowPosition;
                        previousColumnPosition= currentColumnPosition;


                        // set new position:
                        // we move 2 steps up to our new position.
                        // Column position should not be changed!
                        currentColumnPosition=currentColumnPosition+2;
                        System.out.println("currentColumnPosition :"+currentColumnPosition +"currentRowPosition :" +currentRowPosition);

                        pathBuild++;
                        resetFailedPathDirectionsArray();
                        ChoosePathAndBuild();
                    }

                }

                else
                {
                    System.out.println("error!");
                    ChoosePathAndBuild();
                }





        }
        //printMaze();
        System.out.println(pathBuild +" path  were build");



    }

    private void backtracking()
    {
        System.out.println("***********BT**********************************************************************************************************");
        System.out.println("Blocked bactracking; blockedDirection = " + blockedDirection);
        //while(10>BackTrackTestCount)
        //{
            int randomBackTrackDirection = (int) (Math.random()*4);
        System.out.println("randomBackTrackDirection :  "+randomBackTrackDirection);
            // SART TEST INPUT
            /*
            System.out.println("currentColumnPosition :" + currentColumnPosition + "currentRowPosition :" + currentRowPosition);


            System.out.println("enter BackTrack Direction");
            int backTrackDirection = input.nextInt();

            System.out.println("BackTrack Direction : " + backTrackDirection);
            */
            // TEST END

            //Is there any blank road i can walk down?


            // walk the old path and try each new old step to make a path.


            // BACKTRACK UP (minus)
            //try Up 2 blank road steps.
            //if there are to blank road steps up and we dident just come from that direction
            // then change the position to that new location.
            if (maze.get(currentRowPosition - 1).get(currentColumnPosition).equals(" ") &&
                    maze.get(currentRowPosition - 2).get(currentColumnPosition).equals(" ")
                    && randomBackTrackDirection == 0
                    )
            {
                // if the direction 0 is blocked try an other backtrack direction
                if(blockedDirection==0)
                {
                    System.out.println("cant backtrack UP just came from there");
                    backtracking();
                }

                // if the road is not blocked, blockedDirection is not 0;
                else if(blockedDirection!=0)
                {
                    System.out.println("ALLOWED TO BACKTRACK UP");
                    // block this direction you just came from

                    int blockedRowPosition = currentRowPosition;

                    int blockedColumnosition = currentColumnPosition;


                    // change position up the  old road!

                    // set new position:
                    // we move 2 steps up to our new position.
                    // Notice only currentColumnPosition is changed
                    currentRowPosition = currentRowPosition - 2;
                    // and now try to make a path.
                    System.out.println("Backtracking .. UP THE OLD PATH");
                    System.out.println("currentColumnPosition :" + currentColumnPosition + " currentRowPosition :" + currentRowPosition);
                    BackTrackTestCount++;
                    //maze.get(currentRowPosition).set(currentColumnPosition, "X");


                    // block direction, 0 so we cant go down.
                    blockedDirection = 1;

                    printMaze();

                    ChoosePathAndBuild();
                }
                else
                    System.out.println("FAIL BACKTRACK");

            }
            // blockedDirection !=1  means the path is not blocked.

            //BACKTRACK DOWN


            else if (maze.get(currentRowPosition + 1).get(currentColumnPosition).equals(" ") &&
                    maze.get(currentRowPosition + 2).get(currentColumnPosition).equals(" ")
                    && randomBackTrackDirection == 1
                    )
            {
                if(blockedDirection == 1)
                {
                    System.out.println("cant backtrack DOWN just came from there");
                    backtracking();
                }
                // if the road is not blocked, smame as blockedDirection is not 1;
                else if(blockedDirection!=1)
                {

                    // change position down the  old road!
                    // set new position:
                    // we move 2 steps up to our new position.
                    // Notice only currentColumnPosition is changed
                    currentRowPosition = currentRowPosition + 2;
                    // and now try to make a path.

                    System.out.println("Backtracking .. DOWN THE OLD PATH");
                    System.out.println("currentColumnPosition :" + currentColumnPosition + " currentRowPosition :" + currentRowPosition);
                    //maze.get(currentRowPosition).set(currentColumnPosition, "y");
                    printMaze();
                    //System.out.println("you just took one step down, now block down going direction");

                    // block direction , 0 for Up.
                    // this might seem a bit bit tricky remember we have to be able to block the opposite direction of where we came

                    blockedDirection =0;
                    System.out.println("we just backtracked one step down now we cant go up.");
                    BackTrackTestCount++;
                    ChoosePathAndBuild();
                }
                else
                    System.out.println("FAIL BACKTRACK");

            }

            //BACKTRACK LEFT (minus)
            else if (maze.get(currentRowPosition).get(currentColumnPosition - 1).equals(" ") &&
                    maze.get(currentRowPosition).get(currentColumnPosition - 2).equals(" ")
                    && randomBackTrackDirection == 2
                    )
            {
                if(blockedDirection ==2)
                {
                    System.out.println("cant backtrack LEFT just came from there");
                    backtracking();
                }

                // if the road is not blocked, smame as blockedDirection is not 1;
                else if(blockedDirection!=2)
                {
                    // change position down the  old road!
                    // set new position:
                    // we move 2 steps up to our new position.
                    // Notice only currentColumnPosition is changed
                    currentColumnPosition = currentColumnPosition - 2;
                    // and now try to make a path.

                    System.out.println("Backtracking ..LEFT on THE OLD PATH");
                    System.out.println("currentColumnPosition :" + currentColumnPosition + " currentRowPosition :" + currentRowPosition);

                    //maze.get(currentRowPosition).set(currentColumnPosition, "z");
                    printMaze();

                    // block direction, 3 so we cant go left we just came from left
                    blockedDirection = 3;

                    BackTrackTestCount++;
                    ChoosePathAndBuild();
                }
                else
                    System.out.println("FAIL BACKTRACK");


            }
            //BACKTRACK RIGHT
            else if (maze.get(currentRowPosition).get(currentColumnPosition + 1).equals(" ") &&
                    maze.get(currentRowPosition).get(currentColumnPosition + 2).equals(" ")
                    && randomBackTrackDirection == 3)
            {
                if(blockedDirection ==3)
                {
                    System.out.println("cant backtrack right just came from there");
                    backtracking();
                }
                // if the road is not blocked, smame as blockedDirection is not 1;
                else if(blockedDirection!=3)
                {
                    // change position down the  old road!
                    // set new position:
                    // we move 2 steps up to our new position.
                    // Notice only currentColumnPosition is changed
                    currentColumnPosition = currentColumnPosition + 2;
                    // and now try to make a path.

                    System.out.println("Backtracking ..RIGHT on THE OLD PATH");
                    System.out.println("currentColumnPosition :" + currentColumnPosition + " currentRowPosition :" + currentRowPosition);
                    //maze.get(currentRowPosition).set(currentColumnPosition, "A");
                    printMaze();

                    // block direction, 2 so we cant go right we just came from right
                    blockedDirection = 2;

                    BackTrackTestCount++;
                    ChoosePathAndBuild();
                }
                else
                    System.out.println("FAIL BACKTRACK");

            }
            else
            {
                System.out.println("BACK TRACKING trying to find direction randomly. ");
                backtracking();
            }
        //}


        //System.out.println("no more tries");
        //maze.get(currentRowPosition).set(currentColumnPosition, "A");
        //printMaze();





    }

    private void resetFailedPathDirectionsArray()

    {
        // reset all 4 places in the array so we can try to make an other path.
        for (int i = 0; i <4 ; i++)
        {
        failedPathDirections[i]=0;
        }
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
