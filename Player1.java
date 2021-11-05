/**
 * Name: STUDENT #1
 * Course: CSCI 241 - Computer Science I
 * Section: 001
 * Assignment Number: 8
 * 
 * Project/Class Descritpion: To model the children's game of Rock-Paper-Scissors.
 * 
 * Known Bugs:
 */
import java.text.*;

public class Player 
{
//Instance Variables
private String name; // String for players name.
private int element;// element using rocks,paper,scissors.
private int wins; // variable number of wins.

    public Player (String name)
    {
        this.name  = name;
        element = 0;
        wins    = 0;
    }
    // Sets the elements to determine rock,paper,scissors.
    public void setElement (int elementArg)
    {
        element = elementArg;
    }
    // Getting the name of the player.
    
    public String getName()
    {
        // Return Name
        return name;
    }
    // Gets the element that the player will be using.
    public int getElement()
    {
        // Returns the element that the player got.
        return element;
    }
    // Gets how many wins the player has won.
    public int getWins()
    {
        // Returns the wins that the player has won.
        return wins;
    }
    // Adds the wins that the player has won.
    public void addWin()
    {
        // Adding a win with another win that the player got.
        wins++;
    }
}