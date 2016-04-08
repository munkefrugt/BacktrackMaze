package com.company;

/**
 * Created by v on 4/8/16.
 */
public class Setup {
    public Setup()
    {
        RecursiveBacktracker recursiveBacktracker = new RecursiveBacktracker(this);
        
        recursiveBacktracker.generateMaze();

        recursiveBacktracker.passInstanceOfRecursiveBacktracker(recursiveBacktracker);
    }
}
