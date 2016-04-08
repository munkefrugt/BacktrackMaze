package com.company;



import java.util.ArrayList;

public class Minotaur {


    private final ArrayList<ArrayList<String>> maze;
    int currentColumnPosition;
    int currentRowPosition;

    private Gui gui;

    public Minotaur(ArrayList<ArrayList<String>> maze, int startMinotaurRow, int startMinotaurColumn)
    {
        this.maze=maze;
        this.currentRowPosition =startMinotaurRow;
        this.currentColumnPosition =startMinotaurColumn;
        System.out.println("create minotaur");
    }
    public void minotaurUp()
    {
        if (maze.get(currentRowPosition - 1).get(currentColumnPosition).equals("+")||
                maze.get(currentRowPosition - 1).get(currentColumnPosition).equals("¤"))
        {
            System.out.println("Wall.. cant move through here");
        }
        else if (maze.get(currentRowPosition - 1).get(currentColumnPosition).equals("F"))
        {
            System.out.println("congratilations, you got out");

            gui.makeWinnerFrame();

        }
        else if (maze.get(currentRowPosition-1).get(currentColumnPosition).equals("S"))
        {
            System.out.println("the game starts here, find the exit.");
        }

        else
        {

        System.out.println("move minotaur up");

        maze.get(currentRowPosition).set(currentColumnPosition, " ");

        // clear path 2. step
        maze.get(currentRowPosition - 1).set(currentColumnPosition, "M");
        //testMode System.out.println("make path up to row , column"+ (currentRowPosition-3)+","+(currentColumnPosition-1) );




        // set new position:
        // we move 2 steps up to our new position.
        currentRowPosition = currentRowPosition - 1;
        }
    }
    public void minotaurDown()
    {
        if (maze.get(currentRowPosition + 1).get(currentColumnPosition).equals("+")||
                maze.get(currentRowPosition + 1).get(currentColumnPosition).equals("¤"))
        {
            System.out.println("Wall.. cant move through here");
        }
        else if (maze.get(currentRowPosition + 1).get(currentColumnPosition).equals("F"))
        {
            System.out.println("congratilations, you got out");
        }
        else if (maze.get(currentRowPosition+1).get(currentColumnPosition).equals("S"))
        {
            System.out.println("the game starts here, find the exit.");
        }
        else
        {
        System.out.println("move minotaur Down");

        maze.get(currentRowPosition).set(currentColumnPosition, " ");

        // clear path 2. step
        maze.get(currentRowPosition + 1).set(currentColumnPosition, "M");
        //testMode System.out.println("make path up to row , column"+ (currentRowPosition-3)+","+(currentColumnPosition-1) );




        // set new position:
        // we move 2 steps up to our new position.
        currentRowPosition = currentRowPosition + 1;
        }
    }
    public void minotaurLeft()
    {
        if (maze.get(currentRowPosition ).get(currentColumnPosition- 1).equals("+")||
                maze.get(currentRowPosition).get(currentColumnPosition- 1).equals("¤"))
        {
            System.out.println("Wall.. cant move through here");
        }
        else if (maze.get(currentRowPosition).get(currentColumnPosition- 1).equals("F"))
        {
            System.out.println("congratilations, you got out");
            
        }
        else if (maze.get(currentRowPosition).get(currentColumnPosition-1).equals("S"))
        {
            System.out.println("the game starts here, find the exit.");
        }
        else
        {
        System.out.println("move minotaur Left");

        maze.get(currentRowPosition).set(currentColumnPosition, " ");

        // clear path 2. step
        maze.get(currentRowPosition).set(currentColumnPosition-1, "M");
        //testMode System.out.println("make path up to row , column"+ (currentRowPosition-3)+","+(currentColumnPosition-1) );




        // set new position:
        // we move 2 steps up to our new position.
        currentColumnPosition = currentColumnPosition-1;
        }
    }

    public void minotaurRight()
    {
        if (maze.get(currentRowPosition).get(currentColumnPosition+1).equals("+")||
                maze.get(currentRowPosition).get(currentColumnPosition+1).equals("¤"))
        {
            System.out.println("Wall.. cant move through here");
        }
        else if (maze.get(currentRowPosition).get(currentColumnPosition+1).equals("F"))
        {
            System.out.println("congratilations, you got out");
            gui.makeWinnerFrame();
        }
        else if (maze.get(currentRowPosition).get(currentColumnPosition+1).equals("S"))
        {
            System.out.println("the game starts here, find the exit.");
        }
        else
        {
        System.out.println("move minotaur Left");

        maze.get(currentRowPosition).set(currentColumnPosition, " ");

        // clear path 2. step
        maze.get(currentRowPosition).set(currentColumnPosition+1, "M");
        //testMode System.out.println("make path up to row , column"+ (currentRowPosition-3)+","+(currentColumnPosition-1) );




        // set new position:
        // we move 2 steps up to our new position.
        currentColumnPosition = currentColumnPosition+1;

        }
    }


    public void passInstance(Gui gui)
    {
        this.gui=gui;
    }
}
