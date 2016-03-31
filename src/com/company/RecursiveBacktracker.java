package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Martin Moltke Wozniak on 3/4/16.
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
    //int[] triedBackTrackingArray ={0,0,0,0};

    // used by the backtracker.
    int previousRowPosition;
    int previousColumnPosition;


    // have to have a start value that is not 0,1,2 0r 3. otherwise a direction will be blocked.
    int blockedDirection=-1;


    // if it reaches a deadend make this true
    //boolean hasReachedDeadEnd;
    int BackTrackTestCount;

    ArrayList<ArrayList<String>> maze = new ArrayList<ArrayList<String>>();

    public RecursiveBacktracker()
    {
        System.out.println("Welcome to my backtracking maze algorithm. \n" +
                "\"" +
                "It cant make so big mazes (over 8 X 8 mazes starts to be a problem for the algorithm.)\n" +
                "then they start to make errors a lot. \n" +
                "hope its good enough." +
                "REASON: it is properly because the mekanism that makes the random numbers for finding the backtracking direction isen't working so well. \n" +
                "So Please try with numbers like 3X3, 4X4, 6X6, 8X8. (8X6) dose work sometimes. even 12X 12 \n"+
                "but i have to run it at least 5 times to get lucky. \n" +
                "if it dosen't work the first time you will have to run the code again a couple of times.\n" +
                "Good luck and happy mazing :)");

        // set size
        System.out.println("enter width, only EVEN NUMBERS");
        int width = input.nextInt();
        System.out.println("your  width is: " + width);

        System.out.println("enter height only EVEN NUMBERS");
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


    }

    // makes a maze.
    public void generateMaze()
    {
        System.out.println(width);
        System.out.println(height);
        /*
        steps:
        1. make a fillid space with a make a barrier and "W"
        */
        System.out.println("fill maze");
        fillMaze();// prints maze.
        System.out.println("make rand start pos");
        makeRandomStartPosition();
        System.out.println("Print start position");
        //printMaze();
        //System.out.println("find RandDirection  :" + randomDirection);


        ChoosePathAndBuild();// chose a path randomly and try to make a path.

        System.out.println("END*******************************************************************************");

        printMaze();


    }



    // this is the recursive method
    private void ChoosePathAndBuild()
    {

        System.out.println("currentColumnPosition : " + currentColumnPosition);
        System.out.println("currentRowPosition : " + currentRowPosition);

                // coment this line out in test mode.


       // while (pathBuild<(width/2)*(height/2)-1)
        //{

            printMaze();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        randomDirection = (int)(Math.random()*4);

        System.out.println("randomDirection:  "+ randomDirection);


        // TEST START
            /*
            System.out.println("amount pathbuild  :"+pathBuild);
            System.out.println("previus rand direction " + randomDirection );
            System.out.println("enter randomDirection");
            randomDirection = input.nextInt();

            System.out.println("you entered New random direction : " + randomDirection);
            */
            // TEST END





                //Try to build path  UP, if succes take 2 steps up
                if(randomDirection == 0)
                {
                        //testMode System.out.println("up");



                        // NO SUCCES.
                        // Its already blank, or there is a path, or a border, Don't make path!
                        if (maze.get(currentRowPosition-2).get(currentColumnPosition).equals("0")||
                                maze.get(currentRowPosition-2).get(currentColumnPosition).equals("¤") ||
                                maze.get(currentRowPosition-2).get(currentColumnPosition).equals(" ")||

                                (
                                        maze.get(currentRowPosition-1).get(currentColumnPosition).equals(" ")
                                                &&
                                                maze.get(currentRowPosition-2).get(currentColumnPosition).equals(" ")
                                )
                            )
                        {
                            // try an other direction
                            //testMode    System.out.println("cant take this way up!");

                            //if all directions have been tried. start BACK TRACKING!
                            if(failedPathDirections[0]==1 && failedPathDirections[1]==1 && failedPathDirections[2]==1 &&
                                    failedPathDirections[3]==1)
                            {
                                //testMode System.out.println("all directions have failed. start backtracking!");

                                //printMaze();

                                //reset failedPathDirections array,
                                // because it has to be reset for next time we need try to build a path.
                                resetFailedPathDirectionsArray();
                                // activate backtracking
                                backtracking();

                            }
                            else
                            {
                                //testMode System.out.println("try an other direction");

                            // tell the "failedPathDirections" array that the path couldent be made.
                            failedPathDirections[0]=1;
                            ChoosePathAndBuild();
                            }

                        }


                        // SUCCES! Take to steps UP. Make a path!

                        // if the next position in the ext 2 step above is equal to a wall piece. then make a path.
                        else// if (maze.get(currentRowPosition -1).get(currentColumnPosition).equals("+")
                            //    && maze.get(currentRowPosition -2).get(currentColumnPosition).equals("+") )
                        {
                            // make path
                            // -2 is because we go up so we have to use an "earlier"creaated aray
                            // clear path 1. step
                            maze.get(currentRowPosition-1).set(currentColumnPosition,"0");

                            // clear path 2. step
                            maze.get(currentRowPosition-2).set(currentColumnPosition,"0");
                            //testMode System.out.println("make path up to row , column"+ (currentRowPosition-3)+","+(currentColumnPosition-1) );

                            // save the position we just came from. will be used in back tracking:
                            // Set  previous position to current:
                            previousRowPosition = currentRowPosition;
                            previousColumnPosition= currentColumnPosition;



                            // set new position:
                            // we move 2 steps up to our new position.
                            currentRowPosition=currentRowPosition-2;
                            //testMode System.out.println("currentColumnPosition "+currentColumnPosition + " should be as original");
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
                    //testMode System.out.println("down");

                    // NO SUCCES.Its already blank, Don't make path!
                    if (maze.get(currentRowPosition+2).get(currentColumnPosition).equals("0")||
                            maze.get(currentRowPosition+2).get(currentColumnPosition).equals("¤") ||
                            maze.get(currentRowPosition+2).get(currentColumnPosition).equals(" ")||
                            (
                                    maze.get(currentRowPosition+1).get(currentColumnPosition).equals(" ")
                                    &&
                                    maze.get(currentRowPosition+2).get(currentColumnPosition).equals(" ")
                            )
                        )
                    {
                        // try an other direction
                        //testMode System.out.println("cant take this way down!");
                        //if all directions have been tried. start BACK TRACKING!
                        if(failedPathDirections[0]==1 && failedPathDirections[1]==1 && failedPathDirections[2]==1 && failedPathDirections[3]==1)
                        {
                            //testMode System.out.println("all directions have failed. start backtracking!");
                            //printMaze();
                            //reset failedPathDirections array,
                            // because it has to be reset for next time we need try to build a path.
                            resetFailedPathDirectionsArray();
                            // activate backtracking
                            backtracking();

                        }
                        else
                        {
                            //testMode System.out.println("try an other direction");
                            // tell the "failedPathDirections" array that the path couldent be made.
                            failedPathDirections[1]=1;
                            ChoosePathAndBuild();
                        }

                    }

                    // SUCCES! Take to steps DOWN. Make a path!
                    // if there is already a wall.
                    else // if (maze.get(currentRowPosition +1).get(currentColumnPosition).equals("+")
                         //   && maze.get(currentRowPosition +2).get(currentColumnPosition).equals("+") )

                    {
                        // make path
                        // +,1+2 is because we go up so we have to use an "earlier"creaated row
                        // clear path 1. step
                        maze.get(currentRowPosition+1).set(currentColumnPosition,"0");

                        // clear path 2. step
                        maze.get(currentRowPosition+2).set(currentColumnPosition,"0");
                        // +3 and +1 is just to get the names right.
                        //testMode System.out.println("make path DOWN to : roww,column("+ (currentRowPosition+3)+","+(currentColumnPosition+1)+")" );

                        // save the position we just came from. will be used in back tracking:
                        // Set  previous position to current:
                        previousRowPosition = currentRowPosition;
                        previousColumnPosition= currentColumnPosition;


                        // set new position:
                        // we move 2 steps up to our new position.
                        // Notice only currentColumnPosition is changed
                        currentRowPosition=currentRowPosition+2;
                        //testMode System.out.println("currentColumnPosition :"+currentColumnPosition +"currentRowPosition :" + currentRowPosition);
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
                    //testMode System.out.println("left");

                    // NO SUCCES.Its already 2 blank or theres the board ends or there is a path already,
                    // then Don't make path! just backtrack
                    if (
                            maze.get(currentRowPosition).get(currentColumnPosition-2).equals("0") ||
                                    maze.get(currentRowPosition).get(currentColumnPosition-2).equals("¤") ||

                                    maze.get(currentRowPosition).get(currentColumnPosition-2).equals(" ")||


                                    // and if there are already 2 blanck pieces.
                                    (
                                            maze.get(currentRowPosition).get(currentColumnPosition-1).equals(" ") &&
                                                    maze.get(currentRowPosition).get(currentColumnPosition-2).equals(" ")

                                    )
                            )


                    {
                        // try an other direction
//testMode                         System.out.println("cant take this way LEFT!");
                        //if all directions have been tried. start BACK TRACKING!
                        if(failedPathDirections[0]==1 && failedPathDirections[1]==1 && failedPathDirections[2]==1 && failedPathDirections[3]==1)
                        {
                            //testMode System.out.println("all directions have failed. start backtracking!");

                            //printMaze();
                            //reset failedPathDirections array,
                            // because it has to be reset for next time we need try to build a path.
                            resetFailedPathDirectionsArray();
                            // activate backtracking

                            backtracking();

                        }
                        else
                        {
                            //testMode                  System.out.println("try an other direction");
                            // tell the "failedPathDirections" array that the path couldent be made.
                            failedPathDirections[2]=1;
                            ChoosePathAndBuild();
                        }

                    }

                    // SUCCES Make a path! LEFT
                    else //if (maze.get(currentRowPosition).get(currentColumnPosition-1).equals("+")&&
                         //   maze.get(currentRowPosition).get(currentColumnPosition-2).equals("+"))
                    {
                        // make path
                        // -2 is because we go up so we have to use an "earlier"creaated aray
                        // clear path 1. step
                        maze.get(currentRowPosition).set(currentColumnPosition-1,"0");

                        // clear path 2. step
                        maze.get(currentRowPosition).set(currentColumnPosition-2,"0");
                        //testMode System.out.println("make path LEFT to : ("+ (currentRowPosition-2)+","+(currentColumnPosition-1)+")" );

                        // save the position we just came from. will be used in back tracking: or is it?????????
                        // Set  previous position to current:
                        previousRowPosition = currentRowPosition;
                        previousColumnPosition= currentColumnPosition;


                        // set new position:
                        // we move 2 steps up to our new position.
                        // row position should not be changed! only currentColumnPosition
                        currentColumnPosition=currentColumnPosition-2;
                        //testMode System.out.println("currentColumnPosition :"+currentColumnPosition +"currentRowPosition :" +currentRowPosition);

                        pathBuild++;
                        resetFailedPathDirectionsArray();
                        ChoosePathAndBuild();
                    }
                }



                // RIGHT

                else if(randomDirection == 3)
                {
                    //testMode System.out.println("right");

                    // NO SUCCES.Its already 2 blank or theres the board ends or there is a path already,
                    // then Don't make path! just backtrack
                    if (
                            maze.get(currentRowPosition).get(currentColumnPosition+2).equals("0") ||
                            maze.get(currentRowPosition).get(currentColumnPosition+2).equals("¤") ||
                                    maze.get(currentRowPosition).get(currentColumnPosition+2).equals(" ") ||
                                   // and if there are already 2 blanck pieces.
                                   (
                                       maze.get(currentRowPosition).get(currentColumnPosition+1).equals(" ") &&
                                        maze.get(currentRowPosition).get(currentColumnPosition+2).equals(" ")

                                   )
                            )
                    {
                        // try an other direction
                        //testMode System.out.println("cant take this way right!");
                        //if all directions have been tried. start BACK TRACKING!
                        if(failedPathDirections[0]==1 && failedPathDirections[1]==1 && failedPathDirections[2]==1 && failedPathDirections[3]==1)
                        {
                            //testMode System.out.println("all directions have failed. start backtracking!");

                            //printMaze();
                            //reset failedPathDirections array,
                            // because it has to be reset for next time we need try to build a path.
                            resetFailedPathDirectionsArray();
                            // activate backtracking
                            backtracking();

                        }
                        else
                        {

                            // tell the "failedPathDirections" array that the path couldent be made.
                            failedPathDirections[3]=1;
                            ChoosePathAndBuild();
                        }

                    }

                    // SUCCES Make a path! Right
                    else //if (maze.get(currentRowPosition).get(currentColumnPosition+1).equals("+")&&
                         //   maze.get(currentRowPosition).get(currentColumnPosition+2).equals("+"))
                    {
                        // make path
                        // +2 is because we go 2 to the right so we have to use an the same row
                        // clear path 1. step
                        maze.get(currentRowPosition).set(currentColumnPosition+1,"0");

                        // clear path 2. step
                        maze.get(currentRowPosition).set(currentColumnPosition+2,"0");
                        //testMode              System.out.println("make path RIGHT to : ("+ (currentRowPosition+2)+","+(currentColumnPosition+1)+")" );

                        // save the position we just came from. will be used in back tracking:
                        // Set  previous position to current:
                        previousRowPosition = currentRowPosition;
                        previousColumnPosition= currentColumnPosition;


                        // set new position:
                        // we move 2 steps up to our new position.
                        // Column position should not be changed!
                        currentColumnPosition=currentColumnPosition+2;
//testMode                         System.out.println("currentColumnPosition :"+currentColumnPosition +"currentRowPosition :" +currentRowPosition);

                        pathBuild++;
                        resetFailedPathDirectionsArray();
                        ChoosePathAndBuild();
                    }

                }

                else
                {
                    System.out.println("error in ChoosePathAndBuild()!");
                    ChoosePathAndBuild();
                }





        //}
        printMaze();
        // TESTING
        System.out.println("sleep ! end of choose path methode");
        // sleeptest
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("4 sec.");

        //System.out.println(pathBuild +" path  were build");



    }

    private void backtracking()
    {   // follow the path back again.
        // for each time there is a path its marked with "p"
        // what happens in sudo code:

            //1. see if there are a path of 2 steps where there is marked with p
                //if yes
                    //change this path to "d" for done.
                    // and now go and check again if you can make a new path in wall.->
                    // ChoosePathAndBuild();


        System.out.println("currentColumnPosition : " + currentColumnPosition);
        System.out.println("currentRowPosition : " + currentRowPosition);

        System.out.println("Backtracking");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //testMode  System.out.println("***********BT**********************************************************************************************************");
        //testMode System.out.println("Blocked bactracking; blockedDirection = " + blockedDirection);
        //while(10>BackTrackTestCount)
        //{
            int randomBackTrackDirection = (int) (Math.random()*4);
        System.out.println("random backtrack direction : " + randomBackTrackDirection);


            //testMode System.out.println("randomBackTrackDirection :  "+randomBackTrackDirection);
            // SART TEST INPUT
            /*
            System.out.println("currentColumnPosition :" + currentColumnPosition + "currentRowPosition :" + currentRowPosition);


            System.out.println("enter BackTrack Direction");
            int backTrackDirection = input.nextInt();

            System.out.println("BackTrack Direction : " + backTrackDirection);
            */
            // TEST END


            //Is there any "p"  road i can walk on?

            // walk the old path and try each new old step to make a path.


            // BACKTRACK UP  (0) // to go up to the next array -1,-2 are needed.
            //try Up 2 blank road steps.
            //if there are 2 "p" road steps up and we dident just come from that direction
            // then change the position to that new location.
                //(-1), is for seeing the position 1 step above, and (-2) is to see 2 steps above.
            if (maze.get(currentRowPosition - 1).get(currentColumnPosition).equals("0") &&
                    maze.get(currentRowPosition - 2).get(currentColumnPosition).equals("0")
                    && randomBackTrackDirection == 0
                    )
            {
                // if the direction 0 is blocked try an other backtrack direction
                if(blockedDirection==0)
                {
                    //testMode
                    System.out.println("cant backtrack UP just came from there");
                    backtracking();
                }

                // if the road is not blocked, blockedDirection is not 0;
                else if(blockedDirection!=0)
                {
                    //testMode
                    System.out.println("BACKTRACK UP");
                    // block this direction you just came from


                    // Change the path "p" to blank " "

                    maze.get(currentRowPosition).set(currentColumnPosition," ");
                    // clear path 1. step
                    maze.get(currentRowPosition - 1).set(currentColumnPosition," ");
                    // clear path 2. step
                    //maze.get(currentRowPosition - 2).set(currentColumnPosition," ");
                    printMaze();

                    // change position up the  old road!

                    // set new position:
                    // we move 2 steps up to our new position.
                    // Notice only currentColumnPosition is changed
                    currentRowPosition = currentRowPosition - 2;
                    // and now try to make a path.
                    //testMode System.out.println("Backtracking .. UP THE OLD PATH");
                    //testMode System.out.println("currentColumnPosition :" + currentColumnPosition + " currentRowPosition :" + currentRowPosition);
                    BackTrackTestCount++;
                    //maze.get(currentRowPosition).set(currentColumnPosition, "X");


                    // block direction, 0 so we cant go down.
                    blockedDirection = 1;

                    //printMaze();





                    ChoosePathAndBuild();
                }
                else
                 System.out.println("FAIL BACKTRACK");

            }
            // blockedDirection !=1  means the path is not blocked.



            //BACKTRACK DOWN


            else if (maze.get(currentRowPosition + 1).get(currentColumnPosition).equals("0") &&
                    maze.get(currentRowPosition + 2).get(currentColumnPosition).equals("0")
                    && randomBackTrackDirection == 1
                    )
            {
                if(blockedDirection == 1)
                {
                    //testMode
                    System.out.println("cant backtrack DOWN just came from there");
                    backtracking();
                }
                // if the road is not blocked, smame as blockedDirection is not 1;
                else if(blockedDirection!=1)
                {
                    System.out.println("backtrack down");


                    System.out.println("currentColumnPosition : " + currentColumnPosition);
                    System.out.println("currentRowPosition : " + currentRowPosition);


                    maze.get(currentRowPosition).set(currentColumnPosition," ");
                    maze.get(currentRowPosition + 1).set(currentColumnPosition," ");
                    //maze.get(currentRowPosition + 2).set(currentColumnPosition," ");
                    printMaze();
                    // change position down the  old road!
                    // set new position:
                    // we move 2 steps up to our new position.
                    // Notice only currentColumnPosition is changed
                    currentRowPosition = currentRowPosition + 2;
                    // and now try to make a path.

                    //testMode System.out.println("Backtracking .. DOWN THE OLD PATH");
                    //testMode System.out.println("currentColumnPosition :" + currentColumnPosition + " currentRowPosition :" + currentRowPosition);
                    //maze.get(currentRowPosition).set(currentColumnPosition, "y");
                    //printMaze();
                    //System.out.println("you just took one step down, now block down going direction");

                    // block direction , 0 for Up.
                    // this might seem a bit bit tricky remember we have to be able to block the opposite direction of where we came

                    blockedDirection =0;
                    //testMode System.out.println("we just backtracked one step down now we cant go up.");
                    BackTrackTestCount++;
                    ChoosePathAndBuild();
                }
                else
                    System.out.println("FAIL BACKTRACK");

            }

            //BACKTRACK LEFT (minus)
            else if (maze.get(currentRowPosition).get(currentColumnPosition - 1).equals("0") &&
                    maze.get(currentRowPosition).get(currentColumnPosition - 2).equals("0")
                    && randomBackTrackDirection == 2
                    )
            {
                if(blockedDirection ==2)
                {
                    //testMode
                    System.out.println("cant backtrack LEFT just came from there");
                    backtracking();
                }

                // if the road is not blocked, smame as blockedDirection is not 1;
                else if(blockedDirection!=2)
                {
                    System.out.println("backtrack left");
                    // change p to " "


                    maze.get(currentRowPosition).set(currentColumnPosition," ");
                    maze.get(currentRowPosition).set(currentColumnPosition - 1," ");
                    //maze.get(currentRowPosition).set(currentColumnPosition - 2," ");
                    printMaze();


                    // change position down the  old road!
                    // set new position:
                    // we move 2 steps up to our new position.
                    // Notice only currentColumnPosition is changed
                    currentColumnPosition = currentColumnPosition - 2;
                    // and now try to make a path.

                    //testMode System.out.println("Backtracking ..LEFT on THE OLD PATH");
                    //testMode System.out.println("currentColumnPosition :" + currentColumnPosition + " currentRowPosition :" + currentRowPosition);

                    //maze.get(currentRowPosition).set(currentColumnPosition, "z");
                    //printMaze();

                    // block direction, 3 so we cant go left we just came from left
                    blockedDirection = 3;

                    BackTrackTestCount++;
                    ChoosePathAndBuild();
                }
                else
                    System.out.println("FAIL BACKTRACK");


            }
            //BACKTRACK RIGHT
            else if (maze.get(currentRowPosition).get(currentColumnPosition + 1).equals("0") &&
                    maze.get(currentRowPosition).get(currentColumnPosition + 2).equals("0")
                    && randomBackTrackDirection == 3)
            {
                
                if(blockedDirection ==3)
                {
                    //testMode
                    System.out.println("cant backtrack right just came from there");
                    backtracking();
                }
                // if the road is not blocked, smame as blockedDirection is not 1;
                else if(blockedDirection!=3)
                {
                    System.out.println("backtrack right");

                    maze.get(currentRowPosition).set(currentColumnPosition," ");
                    maze.get(currentRowPosition).set(currentColumnPosition + 1," ");
                    //maze.get(currentRowPosition).set(currentColumnPosition + 2," ");
                    printMaze();
                    // change position down the  old road!
                    // set new position:
                    // we move 2 steps up to our new position.
                    // Notice only currentColumnPosition is changed
                    currentColumnPosition = currentColumnPosition + 2;
                    // and now try to make a path.

                    //testMode System.out.println("Backtracking ..RIGHT on THE OLD PATH");
                    //testMode System.out.println("currentColumnPosition :" + currentColumnPosition + " currentRowPosition :" + currentRowPosition);
                    //maze.get(currentRowPosition).set(currentColumnPosition, "A");
                    //printMaze();

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
                //testMode
                System.out.println("BACK TRACKING trying to find New direction randomly. ");
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
        // startRow=2;
        //startColumn=2;

        maze.get(startRow).set(startColumn,"0");
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
            border.add("¤");

        }
        maze.add(border);
        maze.add(border);

        for (int i = 0; i < height ; i++)
        {

            ArrayList<String> list = new ArrayList<String>();
            list.add("¤");
            list.add("¤");
            for (int j = 0; j <width ; j++)
            {
                list.add("+");

            }
            list.add("¤");
            list.add("¤");
            maze.add(list);
        }

        maze.add(border);
        maze.add(border);
    }

}
